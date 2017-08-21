package com.matusvanco.weather.android;

import android.support.annotation.IdRes;

import com.matusvanco.weather.android.utility.ConversionUtility;
import com.matusvanco.weather.android.utility.ConversionUtility.LengthUnit;
import com.matusvanco.weather.android.utility.ConversionUtility.TemperatureUnit;

public class STRVWeatherConfig
{
	public static final boolean LOGS = BuildConfig.LOGS;

	public static final String REST_BASE_URL = "http://api.openweathermap.org";

	public static final String CITY = "Brno";

	/**
	 * Count of requested item in the current weather query.
	 */
	public static final int QUERY_PARAMETER_CURRENT_WEATHER_COUNT = 1;

	/**
	 * Count of forecast items which are requested.
	 */
	public static final int QUERY_PARAMETER_FORECAST_COUNT = 8;

	public static final String WEATHER_SERVICE_APP_ID = "1c8254bc0e4c06431648f7aa6d641537";

	/**
	 * Base URL for loading images from OpenWeatherMap.
	 */
	public static final String WEATHER_SERVICE_ICON_BASE_URL = "http://openweathermap.org/img/w/";

	/**
	 * Suffix used to build the proper URL for loading images from OpenWeatherMap.
	 */
	public static final String WEATHER_ICON_SUFFIX = ".png";

	public static final @IdRes int MAIN_ACTIVITY_MENU_ITEM_ID_DEFAULT = R.id.nav_today;

	public static final @TemperatureUnit int TEMPERATURE_UNIT_DEFAULT = ConversionUtility.TEMPERATURE_UNIT_CELSIUS;

	public static final @LengthUnit int LENGTH_UNIT_DEFAULT = ConversionUtility.LENGTH_UNIT_METER;
}