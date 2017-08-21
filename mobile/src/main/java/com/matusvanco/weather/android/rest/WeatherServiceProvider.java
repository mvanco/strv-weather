package com.matusvanco.weather.android.rest;

import android.support.annotation.StringDef;

import com.matusvanco.weather.android.entity.CurrentWeatherEntity;
import com.matusvanco.weather.android.entity.ForecastEntity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class WeatherServiceProvider
{
	public static final String CURRENT_WEATHER_CALL_TYPE = "currentWeather";
	public static final String FORECAST_CALL_TYPE = "forecast";

	public static final String MEASUREMENT_SYSTEM_METRIC = "metric";
	public static final String MEASUREMENT_SYSTEM_IMPERIAL = "imperial";

	private static volatile WeatherService sService;

	/**
	 * Called when one more precipitation icon has beel successfully loaded.
	 */
	public interface OnPrecipitationIconLoadedListener
	{
		void onPrecipitationIconLoaded();
	}

	public interface WeatherService
	{
		/**
		 * @param city  Name of city
		 * @param units Metric or imperial
		 * @param count Number of required items
		 * @param appId Unique token for access
		 * @return Current weather as the {@link CurrentWeatherEntity} instance
		 */
		@GET("/data/2.5/weather?")
		Single<Response<CurrentWeatherEntity>> getCurrentWeather(@Query("q") String city, @Query("units") @MeasurementSystem String units, @Query("cnt") int count, @Query("APPID") String appId);

		/**
		 * @param city  Name of city
		 * @param units Metric or imperial
		 * @param count Number of required items
		 * @param appId Unique token for access
		 * @return ForecastEntity as the {@link ForecastEntity} instance
		 */
		@GET("/data/2.5/forecast/daily?")
		Single<Response<ForecastEntity>> getForecast(@Query("q") String city, @Query("units") @MeasurementSystem String units, @Query("cnt") int count, @Query("APPID") String appId);
	}

	@Retention(RetentionPolicy.SOURCE)
	@StringDef({MEASUREMENT_SYSTEM_METRIC, MEASUREMENT_SYSTEM_IMPERIAL})
	public @interface MeasurementSystem
	{
	}


	public WeatherServiceProvider()
	{
	}


	public static WeatherService getService()
	{
		if (sService == null)
		{
			synchronized (WeatherService.class)
			{
				if (sService == null)
				{
					sService = WeatherRetrofitClient.createService(WeatherService.class);
				}
			}
		}
		return sService;
	}
}