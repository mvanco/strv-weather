package com.matusvanco.weather.android.utility;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class ConversionUtility
{
	public static final int TEMPERATURE_UNIT_CELSIUS = 0;
	public static final int TEMPERATURE_UNIT_FAHRENHEIT = 1;
	public static final int LENGTH_UNIT_METER = 0;
	public static final int LENGTH_UNIT_MILE = 1;

	/**
	 * Constant for the conversion between mi/h and m/s.
	 */
	private static final Double METRIC_IMPERIAL_SPEED_PARAMETER_CONVERSION_COEFFICIENT = 2.23694;

	@Retention(RetentionPolicy.SOURCE)
	@IntDef({TEMPERATURE_UNIT_CELSIUS, TEMPERATURE_UNIT_FAHRENHEIT})
	public @interface TemperatureUnit
	{
	}

	@Retention(RetentionPolicy.SOURCE)
	@IntDef({LENGTH_UNIT_METER, LENGTH_UNIT_MILE})
	public @interface LengthUnit
	{
	}


	public ConversionUtility()
	{
	}


	public static String getTemperatureSign(@TemperatureUnit int unit)
	{
		if (unit == ConversionUtility.TEMPERATURE_UNIT_CELSIUS)
		{
			return "°C";
		}
		else
		{
			return "°F";
		}
	}


	/**
	 * @param speed
	 * @param temperatureUnit Used for detecting measurement system entered during query
	 * @param lengthUnit
	 * @return
	 */
	public static String convertSpeed(Double speed, int temperatureUnit, int lengthUnit)
	{
		if (speed == null)
		{
			return "";
		}

		Double result = speed;
		if ((lengthUnit == ConversionUtility.LENGTH_UNIT_MILE) && (temperatureUnit == ConversionUtility.TEMPERATURE_UNIT_CELSIUS))
		{ // Server has been queried in metric units so speed is in meters and must be converted.
			result = speed * METRIC_IMPERIAL_SPEED_PARAMETER_CONVERSION_COEFFICIENT;
		}
		else if ((lengthUnit == ConversionUtility.LENGTH_UNIT_METER) && (temperatureUnit == ConversionUtility.TEMPERATURE_UNIT_FAHRENHEIT))
		{ // Server has been queried in imperial units so speed is in miles and must be converted.
			result = speed / METRIC_IMPERIAL_SPEED_PARAMETER_CONVERSION_COEFFICIENT;

		}

		String unitSuffix = "";
		if (lengthUnit == ConversionUtility.LENGTH_UNIT_METER)
		{
			unitSuffix = "m/s";
		}
		else
		{
			unitSuffix = "mi/h";
		}

		return String.valueOf(Math.round(result)) + " " + unitSuffix;
	}
}
