package com.matusvanco.weather.android.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.matusvanco.weather.android.R;

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener
{
	/**
	 * ForecastItemEntity preference key for storing of currently selected length unit.
	 */
	public static final String LENGTH_LIST_PREFERENCE_KEY = "pref_key_length_list";

	/**
	 * ForecastItemEntity preference key for storing of currently selected temeperature unit.
	 */
	public static final String TEMPERATURE_LIST_PREFERENCE_KEY = "pref_key_temperature_list";


	public static SettingsFragment newInstance()
	{
		return new SettingsFragment();
	}


	@Override
	public void onCreatePreferences(Bundle savedInstanceState, String rootKey)
	{
		// Load the preferences from an XML resource
		setPreferencesFromResource(R.xml.preferences, rootKey);
	}


	@Override
	public void onResume()
	{
		super.onResume();
		getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);

	}


	@Override
	public void onPause()
	{
		getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
		super.onPause();
	}


	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key)
	{
	}
}
