package com.matusvanco.weather.android.activity;

import android.databinding.ViewDataBinding;
import android.os.Bundle;

import org.alfonz.mvvm.AlfonzBindingActivity;
import org.alfonz.mvvm.AlfonzView;
import org.alfonz.mvvm.AlfonzViewModel;

public abstract class BaseBindingActivity<T extends AlfonzView, R extends AlfonzViewModel<T>, B extends ViewDataBinding> extends AlfonzBindingActivity<T, R, B>
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}


	@Override
	public void onStart()
	{
		super.onStart();
	}


	@Override
	public void onStop()
	{
		super.onStop();
	}


	@Override
	public void onDestroy()
	{
		super.onDestroy();
	}


	@Override
	public void onPause()
	{
		super.onPause();
	}


	@Override
	public void onResume()
	{
		super.onResume();
	}
}
