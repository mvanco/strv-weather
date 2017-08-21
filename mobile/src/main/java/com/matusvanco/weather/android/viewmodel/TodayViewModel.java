package com.matusvanco.weather.android.viewmodel;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.preference.PreferenceManager;

import com.matusvanco.weather.android.STRVWeatherApplication;
import com.matusvanco.weather.android.STRVWeatherConfig;
import com.matusvanco.weather.android.entity.CurrentWeatherEntity;
import com.matusvanco.weather.android.fragment.SettingsFragment;
import com.matusvanco.weather.android.rest.RestHttpLogger;
import com.matusvanco.weather.android.rest.RestResponseHandler;
import com.matusvanco.weather.android.rest.WeatherServiceProvider;
import com.matusvanco.weather.android.ui.TodayView;
import com.matusvanco.weather.android.utility.ConversionUtility;

import org.alfonz.rest.rx.RestRxManager;
import org.alfonz.rx.AlfonzDisposableSingleObserver;
import org.alfonz.utility.NetworkUtility;
import org.alfonz.view.StatefulLayout;

import io.reactivex.Single;
import io.reactivex.observers.DisposableSingleObserver;
import retrofit2.Response;

public class TodayViewModel extends BaseViewModel<TodayView>
{
	public final ObservableInt state = new ObservableInt();
	public final ObservableField<CurrentWeatherEntity> currentWeather = new ObservableField<>();
	public final ObservableInt temperatureUnit = new ObservableInt(STRVWeatherConfig.TEMPERATURE_UNIT_DEFAULT);
	public final ObservableInt lengthUnit = new ObservableInt(STRVWeatherConfig.LENGTH_UNIT_DEFAULT);

	private RestRxManager mRestRxManager = new RestRxManager(new RestResponseHandler(), new RestHttpLogger());

	private SharedPreferences.OnSharedPreferenceChangeListener mOnSharedPreferenceChangeListener =
			(prefs, key) ->
			{
				if (key.equals(SettingsFragment.TEMPERATURE_LIST_PREFERENCE_KEY))
				{
					onTemperatureChanged(Integer.valueOf(prefs.getString(SettingsFragment.TEMPERATURE_LIST_PREFERENCE_KEY, "0")));
				}
				else if (key.equals(SettingsFragment.LENGTH_LIST_PREFERENCE_KEY))
				{
					onLengthChanged(Integer.valueOf(prefs.getString(SettingsFragment.LENGTH_LIST_PREFERENCE_KEY, "0")));
				}
			};


	@Override
	public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState)
	{
		super.onCreate(arguments, savedInstanceState);
		SharedPreferences mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		mSharedPreferences.registerOnSharedPreferenceChangeListener(mOnSharedPreferenceChangeListener);
	}


	@Override
	public void onStart()
	{
		super.onStart();

		SharedPreferences mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		int currentTemperatureUnit = Integer.valueOf(mSharedPreferences.getString(SettingsFragment.TEMPERATURE_LIST_PREFERENCE_KEY, "0"));
		lengthUnit.set(Integer.valueOf(mSharedPreferences.getString(SettingsFragment.LENGTH_LIST_PREFERENCE_KEY, "0")));
		if (currentWeather.get() == null || currentTemperatureUnit != temperatureUnit.get())
		{
			temperatureUnit.set(currentTemperatureUnit);
			loadData();
		}
	}


	public String getCity()
	{
		return STRVWeatherConfig.CITY;
	}


	private void loadData()
	{
		if (NetworkUtility.isOnline(getApplicationContext()))
		{
			String callType = WeatherServiceProvider.CURRENT_WEATHER_CALL_TYPE;
			if (!mRestRxManager.isRunning(callType))
			{
				state.set(StatefulLayout.PROGRESS); // Show progress.

				String measurementSystem = (temperatureUnit.get() == ConversionUtility.TEMPERATURE_UNIT_CELSIUS)
						? WeatherServiceProvider.MEASUREMENT_SYSTEM_METRIC
						: WeatherServiceProvider.MEASUREMENT_SYSTEM_IMPERIAL;

				// Subscribe
				Single<Response<CurrentWeatherEntity>> rawSingle = WeatherServiceProvider.getService()
						.getCurrentWeather(STRVWeatherConfig.CITY, measurementSystem,
								STRVWeatherConfig.QUERY_PARAMETER_CURRENT_WEATHER_COUNT,
								STRVWeatherConfig.WEATHER_SERVICE_APP_ID);
				Single<Response<CurrentWeatherEntity>> single = mRestRxManager.setupRestSingleWithSchedulers(rawSingle, callType);
				single.subscribeWith(createCurrentWeatherObserver());
			}
		}
		else
		{
			// show offline
			state.set(StatefulLayout.OFFLINE);
		}
	}


	private void onTemperatureChanged(int temperatureUnit)
	{
		this.temperatureUnit.set(temperatureUnit);
		loadData();
	}


	private void onLengthChanged(int lengthUnit)
	{
		this.lengthUnit.set(lengthUnit);
	}


	private Context getApplicationContext()
	{
		return STRVWeatherApplication.getContext();
	}


	private DisposableSingleObserver<Response<CurrentWeatherEntity>> createCurrentWeatherObserver()
	{
		return AlfonzDisposableSingleObserver.newInstance(
				response ->
				{
					currentWeather.set(response.body());
					state.set(StatefulLayout.CONTENT);
				},
				throwable ->
				{
					state.set(StatefulLayout.EMPTY);
				}
		);
	}
}