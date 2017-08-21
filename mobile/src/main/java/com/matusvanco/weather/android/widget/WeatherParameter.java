package com.matusvanco.weather.android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.matusvanco.weather.android.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherParameter extends LinearLayout
{
	/**
	 * Constant for the conversion between mi/h and m/s.
	 */
	private static final Double METRIC_IMPERIAL_SPEED_PARAMETER_CONVERSION_COEFFICIENT = 2.23694;
	/**
	 * Matches with the {@code R.styleable.WeatherParameter_weatherUnit} id of attribute.
	 */
	private static final int WEATHER_UNIT_HUMIDITY = 0;
	private static final int WEATHER_UNIT_PRECIPITATION = 1;
	private static final int WEATHER_UNIT_PRESSURE = 2;
	private static final int WEATHER_UNIT_WIND = 3;
	private static final int WEATHER_UNIT_DIRECTION = 4;

	@BindView(R.id.fragment_today_weather_parameter_icon)
	ImageView imageView;

	@BindView(R.id.fragment_today_weather_parameter_text)
	TextView textView;

	private int weatherUnit = 0;


	public WeatherParameter(Context context)
	{
		super(context);
		init(context, null);
	}


	private void init(Context context, AttributeSet attrs)
	{
		setupParentView();
		inflate(context, R.layout.fragment_today_weather_parameter, this);
		ButterKnife.bind(this);
		setupChildrenViews(context, attrs);
	}


	private void setupParentView()
	{
		setOrientation(LinearLayout.HORIZONTAL);
		setGravity(Gravity.CENTER_VERTICAL);
	}


	private void setupChildrenViews(Context context, AttributeSet attrs)
	{
		if (attrs == null)
		{
			// Handle wrong state - print error message and end the application.
		}

		/**
		 * Get attributes from {@link AttributeSet}.
		 */
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.WeatherParameter, 0, 0);
		Drawable icon = a.getDrawable(R.styleable.WeatherParameter_weatherIcon);
		String text = a.getString(R.styleable.WeatherParameter_weatherText);
		weatherUnit = a.getInt(R.styleable.WeatherParameter_weatherUnitFor, 1);

		/**
		 * Bind data to the layout.
		 */
		if (text != null)
		{
			setWeatherText(text);
		}
		if (icon != null)
		{
			imageView = (ImageView) findViewById(R.id.fragment_today_weather_parameter_icon);
			imageView.setImageDrawable(icon);
		}
	}


	public void setWeatherText(String text)
	{
		if (text == null)
		{
			return;
		}
		String unitSuffix = "";
		switch (weatherUnit)
		{
			case WEATHER_UNIT_HUMIDITY:
				unitSuffix = "%";
				break;
			case WEATHER_UNIT_PRECIPITATION:
				unitSuffix = " mm";
				break;
			case WEATHER_UNIT_PRESSURE:
				unitSuffix = " hPa";
				break;
			case WEATHER_UNIT_WIND:
			case WEATHER_UNIT_DIRECTION:
				unitSuffix = "";
				break;
		}

		textView = (TextView) findViewById(R.id.fragment_today_weather_parameter_text);
		textView.setText(text + unitSuffix);
	}


	public WeatherParameter(Context context, @Nullable AttributeSet attrs)
	{
		super(context, attrs);
		init(context, attrs);
	}
}
