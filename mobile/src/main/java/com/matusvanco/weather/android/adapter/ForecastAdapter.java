package com.matusvanco.weather.android.adapter;

import android.databinding.ObservableInt;

import com.matusvanco.weather.android.R;
import com.matusvanco.weather.android.databinding.FragmentForecastItemBinding;
import com.matusvanco.weather.android.ui.ForecastView;
import com.matusvanco.weather.android.viewmodel.ForecastViewModel;

import org.alfonz.adapter.BR;
import org.alfonz.adapter.BaseDataBoundRecyclerViewHolder;
import org.alfonz.adapter.SimpleDataBoundRecyclerAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ForecastAdapter extends SimpleDataBoundRecyclerAdapter<FragmentForecastItemBinding>
{
	private ObservableInt mTemperatureUnit;


	public ForecastAdapter(ForecastView view, ForecastViewModel viewModel)
	{
		super(
				R.layout.fragment_forecast_item,
				view,
				viewModel.forecastItems
		);

		mTemperatureUnit = viewModel.temperatureUnit;
	}


	public static String getDayOfTheWeek(int position)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, position + 1); // First (0 index) is 'tomorrow' in this list.
		Date date = calendar.getTime();
		String dayOfTheWeek = dateFormat.format(date);
		return dayOfTheWeek;
	}


	@Override
	protected void bindItem(BaseDataBoundRecyclerViewHolder holder, int position, List payloads)
	{
		super.bindItem(holder, position, payloads);
		holder.binding.setVariable(BR.position, position);
		holder.binding.setVariable(BR.temperatureUnit, mTemperatureUnit);
	}
}
