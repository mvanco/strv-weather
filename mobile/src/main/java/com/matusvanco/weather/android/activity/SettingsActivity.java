package com.matusvanco.weather.android.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import com.matusvanco.weather.android.R;

public class SettingsActivity extends BaseActivity
{
	public static Intent newIntent(Context context)
	{
		Intent intent = new Intent(context, SettingsActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		return intent;
	}


	@Override
	public void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);

		setupActionBar(INDICATOR_BACK);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		int id = item.getItemId();

		if (id == android.R.id.home)
		{
			onBackPressed(); // Default behaviour.
		}
		return super.onOptionsItemSelected(item);
	}
}
