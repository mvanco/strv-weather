package com.matusvanco.weather.android.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RainEntity
{
	@SerializedName("3h")
	@Expose
	private Double _3h;


	@Override
	public int hashCode()
	{
		return _3h != null ? _3h.hashCode() : 0;
	}


	@Override
	public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		RainEntity rain = (RainEntity) o;

		return _3h != null ? _3h.equals(rain._3h) : rain._3h == null;

	}


	public Double get3h()
	{
		return _3h;
	}


	public void set3h(Double _3h)
	{
		this._3h = _3h;
	}


	public Double getRainForecast()
	{
		return _3h;
	}
}
