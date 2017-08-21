package com.matusvanco.weather.android.utility;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.matusvanco.weather.android.R;
import com.matusvanco.weather.android.rest.WeatherServiceProvider;

public final class BindingUtility
{
	public BindingUtility()
	{
	}


	@BindingAdapter("weatherImageUrl")
	public static void setWeatherImageUrl(ImageView imageView, String iconTitle)
	{
		GlideUtility.loadImage(imageView, R.drawable.ic_cloud_off, iconTitle, new WeatherServiceProvider.OnPrecipitationIconLoadedListener()
		{
			@Override
			public void onPrecipitationIconLoaded()
			{
				//nothing to do yet.
			}
		});
	}
}
