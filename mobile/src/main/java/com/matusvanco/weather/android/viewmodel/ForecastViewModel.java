package com.matusvanco.weather.android.viewmodel;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableInt;
import android.support.v7.preference.PreferenceManager;

import com.matusvanco.weather.android.STRVWeatherApplication;
import com.matusvanco.weather.android.STRVWeatherConfig;
import com.matusvanco.weather.android.entity.ForecastEntity;
import com.matusvanco.weather.android.entity.ForecastItemEntity;
import com.matusvanco.weather.android.fragment.SettingsFragment;
import com.matusvanco.weather.android.rest.RestHttpLogger;
import com.matusvanco.weather.android.rest.RestResponseHandler;
import com.matusvanco.weather.android.rest.WeatherServiceProvider;
import com.matusvanco.weather.android.ui.ForecastView;
import com.matusvanco.weather.android.utility.ConversionUtility;

import org.alfonz.rest.rx.RestRxManager;
import org.alfonz.rx.AlfonzDisposableSingleObserver;
import org.alfonz.utility.NetworkUtility;
import org.alfonz.view.StatefulLayout;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.observers.DisposableSingleObserver;
import retrofit2.Response;

public class ForecastViewModel extends BaseViewModel<ForecastView>
{
	public final ObservableInt state = new ObservableInt();
	public final ObservableArrayList<ForecastItemEntity> forecastItems = new ObservableArrayList<>();
	public final ObservableInt temperatureUnit = new ObservableInt();

	private RestRxManager mRestRxManager = new RestRxManager(new RestResponseHandler(), new RestHttpLogger());


	@Override
	public void onStart()
	{
		super.onStart();

		SharedPreferences mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		int currentTemperatureUnit = Integer.valueOf(mSharedPreferences.getString(SettingsFragment.TEMPERATURE_LIST_PREFERENCE_KEY, "0"));
		if (forecastItems.isEmpty() || currentTemperatureUnit != temperatureUnit.get())
		{
			temperatureUnit.set(currentTemperatureUnit);
			loadData();
		}
	}


	@Override
	public void onDestroy()
	{
		super.onDestroy();

		// unsubscribe
		mRestRxManager.disposeAll();
	}


	private void loadData()
	{
		if (NetworkUtility.isOnline(getApplicationContext()))
		{
			String callType = WeatherServiceProvider.FORECAST_CALL_TYPE;
			if (!mRestRxManager.isRunning(callType))
			{
				state.set(StatefulLayout.PROGRESS); // Show progress.

				String measurementSystem = (temperatureUnit.get() == ConversionUtility.TEMPERATURE_UNIT_CELSIUS)
						? WeatherServiceProvider.MEASUREMENT_SYSTEM_METRIC
						: WeatherServiceProvider.MEASUREMENT_SYSTEM_IMPERIAL;

				// Subscribe
				Single<Response<ForecastEntity>> rawSingle = WeatherServiceProvider.getService()
						.getForecast(STRVWeatherConfig.CITY, measurementSystem,
								STRVWeatherConfig.QUERY_PARAMETER_FORECAST_COUNT,
								STRVWeatherConfig.WEATHER_SERVICE_APP_ID);
				Single<Response<ForecastEntity>> single = mRestRxManager.setupRestSingleWithSchedulers(rawSingle, callType);
				single.subscribeWith(createForecastObserver());
			}
		}
		else
		{
			// show offline
			state.set(StatefulLayout.OFFLINE);
		}
	}


	public void onTemperatureChanged(int temperatureUnit)
	{
		this.temperatureUnit.set(temperatureUnit);
		loadData();
	}


	private Context getApplicationContext()
	{
		return STRVWeatherApplication.getContext();
	}


	private DisposableSingleObserver<Response<ForecastEntity>> createForecastObserver()
	{
		return AlfonzDisposableSingleObserver.newInstance(
				response ->
				{

					List<ForecastItemEntity> list = response.body().getList();

					forecastItems.clear();
					forecastItems.addAll(list.subList(1, list.size()));
					getView().onTextDataLoaded();

					state.set(StatefulLayout.CONTENT);
				},
				throwable ->
				{
					state.set(StatefulLayout.EMPTY);
				}
		);
	}
}
