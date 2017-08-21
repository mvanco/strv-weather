package com.matusvanco.weather.android.fragment;

import android.databinding.ViewDataBinding;

import com.matusvanco.weather.android.ui.BaseView;
import com.matusvanco.weather.android.viewmodel.BaseViewModel;

import org.alfonz.mvvm.AlfonzBindingFragment;

public abstract class BaseFragment<T extends BaseView, R extends BaseViewModel<T>, B extends ViewDataBinding> extends AlfonzBindingFragment<T, R, B> implements BaseView
{
}
