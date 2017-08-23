package com.matusvanco.weather.android.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.matusvanco.weather.android.R;
import com.matusvanco.weather.android.STRVWeatherConfig;
import com.matusvanco.weather.android.databinding.ActivityDashboardBinding;
import com.matusvanco.weather.android.dialog.AboutDialogFragment;
import com.matusvanco.weather.android.fragment.ForecastFragment;
import com.matusvanco.weather.android.fragment.TodayFragment;
import com.matusvanco.weather.android.ui.DashboardView;
import com.matusvanco.weather.android.viewmodel.DashboardViewModel;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class MainActivity extends BaseBindingActivity<DashboardView, DashboardViewModel, ActivityDashboardBinding> implements DashboardView
{
	/**
	 * Key for the {@code mFragmentType} field.
	 */
	private static final String FRAGMENT_TYPE_KEY = "com.matusvanco.weather.android.mFragmentType";
	/**
	 * Handles synchronization between ActionBar hamburger button and side menu.
	 */
	private ActionBarDrawerToggle mToggle;
	/**
	 * Type of fragment which should be currently shown.
	 */
	private @MainActivityFragmentType int mFragmentType;

	@Retention(RetentionPolicy.SOURCE)
	@IntDef({R.id.nav_today, R.id.nav_forecast})
	private @interface MainActivityFragmentType
	{
	}


	public static Intent newIntent(Context context)
	{
		Intent intent = new Intent(context, MainActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		return intent;
	}


	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setupActionBar();
		getBinding().activityMainNavView.setNavigationItemSelectedListener
				(
						(menuItem) ->
						{
							selectDrawerItem(menuItem.getItemId());
							return true;
						}
				);

		if (savedInstanceState == null)
		{
			selectDrawerItem(getDefaultMenuItemId());
		}
		else if (savedInstanceState.containsKey(FRAGMENT_TYPE_KEY))
		{
			//noinspection WrongConstant
			mFragmentType = savedInstanceState.getInt(FRAGMENT_TYPE_KEY);
			selectDrawerItem(mFragmentType);
		}
	}


	@Override
	public void onResume()
	{
		super.onResume();
		setTitle();
	}


	@Override
	public ActivityDashboardBinding inflateBindingLayout(LayoutInflater inflater)
	{
		return ActivityDashboardBinding.inflate(inflater);
	}


	@Override
	public void onSaveInstanceState(@NonNull Bundle outState)
	{
		super.onSaveInstanceState(outState);
		outState.putInt(FRAGMENT_TYPE_KEY, mFragmentType);
	}


	@Nullable
	@Override
	public Class getViewModelClass()
	{
		return DashboardViewModel.class;
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.activity_dashboard_toolbar, menu);
		return true;
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.action_settings:
				startActivity(SettingsActivity.newIntent(this));
				break;
			case R.id.action_about:
				showAboutDialogFragment();
				break;
		}
		return super.onOptionsItemSelected(item);
	}


	@Override
	public void onBackPressed()
	{
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_main_drawer_layout);
		if (drawer.isDrawerOpen(GravityCompat.START))
		{
			drawer.closeDrawer(GravityCompat.START);
		}
		else
		{
			super.onBackPressed();
		}
	}


	@Override
	public void onConfigurationChanged(Configuration newConfig)
	{
		super.onConfigurationChanged(newConfig);
		mToggle.onConfigurationChanged(newConfig);
	}


	private int getDefaultMenuItemId()
	{
		return getBinding().activityMainNavView.getMenu().findItem(STRVWeatherConfig.MAIN_ACTIVITY_MENU_ITEM_ID_DEFAULT).getItemId();
	}


	private void selectDrawerItem(int itemId)
	{
		if (itemId != mFragmentType)
		{
			mFragmentType = itemId;

			Fragment fragment = null;
			Class fragmentClass;
			//noinspection WrongConstant
			switch (mFragmentType = itemId)
			{
				case R.id.nav_today:
					fragmentClass = TodayFragment.class;
					break;
				case R.id.nav_forecast:
					fragmentClass = ForecastFragment.class;
					break;
				default:
					fragmentClass = TodayFragment.class;
					break;
			}

			try
			{
				fragment = (Fragment) fragmentClass.newInstance();
			} catch (Exception e)
			{
				e.printStackTrace();
			}

			FragmentManager fragmentManager = getSupportFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.activity_main_content_fragment_container, fragment)
					.commit();
			MenuItem menuItem = getBinding().activityMainNavView.getMenu().findItem(itemId);
			menuItem.setChecked(true);
			setTitle();
		}
		getBinding().activityMainDrawerLayout.closeDrawer(GravityCompat.START);
	}


	private void setupActionBar()
	{
		setSupportActionBar(getBinding().activityMainToolbar);

		mToggle = new ActionBarDrawerToggle(
				this, getBinding().activityMainDrawerLayout, getBinding().activityMainToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		getBinding().activityMainDrawerLayout.addDrawerListener(mToggle);
		getBinding().activityMainDrawerLayout.setScrimColor(Color.TRANSPARENT);
		getBinding().activityMainDrawerLayout.addDrawerListener(createDrawerListener());
		mToggle.syncState();
	}


	private DrawerListener createDrawerListener()
	{
		return new DrawerListener()
		{
			@Override
			public void onDrawerSlide(View drawerView, float slideOffset)
			{
			}


			@Override
			public void onDrawerOpened(View drawerView)
			{
				getBinding().activityMainToolbar.setTitle(R.string.toolbar_title_weather);
			}


			@Override
			public void onDrawerClosed(View drawerView)
			{
				getBinding().activityMainToolbar.setTitle(getTitleRes());
			}


			@Override
			public void onDrawerStateChanged(int newState)
			{
			}
		};
	}


	private void setTitle()
	{
		if (getSupportActionBar() == null)
		{
			return;
		}

		if (getBinding().activityMainDrawerLayout.isDrawerOpen(GravityCompat.START))
		{
			getSupportActionBar().setTitle(R.string.toolbar_title_weather);
		}
		else
		{
			getSupportActionBar().setTitle(getTitleRes());
		}
	}


	private
	@StringRes
	int getTitleRes()
	{
		switch (mFragmentType)
		{
			case R.id.nav_today:
				return R.string.activity_main_drawer_today;
			case R.id.nav_forecast:
				return R.string.activity_main_drawer_forecast;
		}
		return 0;
	}


	private void showAboutDialogFragment()
	{
		DialogFragment fragment = AboutDialogFragment.newInstance();
		fragment.show(getSupportFragmentManager(), AboutDialogFragment.class.getName());
	}
}