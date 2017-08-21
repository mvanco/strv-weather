package com.matusvanco.weather.android.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;

import com.matusvanco.weather.android.databinding.FragmentTodayBinding;
import com.matusvanco.weather.android.ui.TodayView;
import com.matusvanco.weather.android.viewmodel.TodayViewModel;

public class TodayFragment extends BaseFragment<TodayView, TodayViewModel, FragmentTodayBinding> implements TodayView
{
	public static TodayFragment newInstance()
	{

		Bundle args = new Bundle();

		TodayFragment fragment = new TodayFragment();
		fragment.setArguments(args);
		return fragment;
	}


	@Override
	public FragmentTodayBinding inflateBindingLayout(LayoutInflater inflater)
	{
		return FragmentTodayBinding.inflate(inflater);
	}


	@Nullable
	@Override
	public Class<TodayViewModel> getViewModelClass()
	{
		return TodayViewModel.class;
	}


	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
	}
}
