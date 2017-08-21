package com.matusvanco.weather.android.utility;

import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.matusvanco.weather.android.STRVWeatherConfig;
import com.matusvanco.weather.android.rest.WeatherServiceProvider;

public final class GlideUtility
{
	public GlideUtility()
	{
	}


	public static void loadImage(ImageView imageView, int placeholder, String iconTitle, final WeatherServiceProvider.OnPrecipitationIconLoadedListener callback)
	{
		if (imageView != null && imageView.getContext() != null)
		{
			Glide.with(imageView.getContext())
					.load(STRVWeatherConfig.WEATHER_SERVICE_ICON_BASE_URL + iconTitle + STRVWeatherConfig.WEATHER_ICON_SUFFIX)
					.placeholder(placeholder)
					.listener(new RequestListener<String, GlideDrawable>()
					{
						@Override
						public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource)
						{
							return false;
						}


						@Override
						public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource)
						{
							callback.onPrecipitationIconLoaded();
							return false;
						}
					})
					.into(imageView);
		}
	}
}
