package com.matusvanco.weather.android;

import android.app.Application;
import android.content.Context;

import org.alfonz.utility.Logcat;

public class STRVWeatherApplication extends Application
{
	private static STRVWeatherApplication sInstance;


	public STRVWeatherApplication()
	{
		sInstance = this;
	}


	public static Context getContext()
	{
		return sInstance;
	}


	@Override
	public void onCreate()
	{
		super.onCreate();
		Logcat.init(STRVWeatherConfig.LOGS, "STRVWeather");
	}
}
