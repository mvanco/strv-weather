package com.matusvanco.weather.android.fragment;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.preference.PreferenceManager;
import android.view.LayoutInflater;

import com.matusvanco.weather.android.adapter.ForecastAdapter;
import com.matusvanco.weather.android.databinding.FragmentForecastBinding;
import com.matusvanco.weather.android.ui.ForecastView;
import com.matusvanco.weather.android.viewmodel.ForecastViewModel;

public class ForecastFragment extends BaseFragment<ForecastView, ForecastViewModel, FragmentForecastBinding> implements ForecastView
{
	private static final String RECYCLER_VIEW_STATE_KEY = "recyclerViewState";

	private ForecastAdapter mAdapter;

	private Parcelable recyclerViewState;

	private OnSharedPreferenceChangeListener mOnSharedPreferenceChangeListener = new OnSharedPreferenceChangeListener()
	{
		public void onSharedPreferenceChanged(SharedPreferences prefs, String key)
		{
			if (key.equals(SettingsFragment.TEMPERATURE_LIST_PREFERENCE_KEY))
			{
				getViewModel().onTemperatureChanged(Integer.valueOf(prefs.getString(SettingsFragment.TEMPERATURE_LIST_PREFERENCE_KEY, "0")));
			}
		}
	};


	public static ForecastFragment newInstance()
	{
		Bundle args = new Bundle();
		ForecastFragment fragment = new ForecastFragment();
		fragment.setArguments(args);
		return fragment;
	}


	@Override
	public FragmentForecastBinding inflateBindingLayout(LayoutInflater inflater)
	{
		return FragmentForecastBinding.inflate(inflater);
	}


	@Override
	public void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		if (savedInstanceState != null)
		{
			recyclerViewState = savedInstanceState.getParcelable(RECYCLER_VIEW_STATE_KEY);
		}
	}


	@Override
	public void onSaveInstanceState(@NonNull Bundle outState)
	{
		super.onSaveInstanceState(outState);
		outState.putParcelable(RECYCLER_VIEW_STATE_KEY,
				getBinding().fragmentForecastRecycler.getLayoutManager().onSaveInstanceState());
	}


	@Nullable
	@Override
	public Class<ForecastViewModel> getViewModelClass()
	{
		return ForecastViewModel.class;
	}


	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		getBinding().executePendingBindings(); // set layout manager in recycler via binding adapter
		setupAdapter();
		SharedPreferences mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
		mSharedPreferences.registerOnSharedPreferenceChangeListener(mOnSharedPreferenceChangeListener);
	}


	@Override
	public void onTextDataLoaded()
	{
		if (mAdapter != null)
		{
			mAdapter.notifyDataSetChanged();
		}
	}


	private void setupAdapter()
	{
		if (mAdapter == null)
		{
			mAdapter = new ForecastAdapter(this, getViewModel());
			getBinding().fragmentForecastRecycler.setAdapter(mAdapter);
		}

		if (recyclerViewState != null)
		{
			getBinding().fragmentForecastRecycler.getLayoutManager().onRestoreInstanceState(recyclerViewState);
		}
	}
}
