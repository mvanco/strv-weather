package com.matusvanco.weather.android.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CoordEntity
{
	@SerializedName("lon")
	@Expose
	private Double lon;

	@SerializedName("lat")
	@Expose
	private Double lat;


	@Override
	public int hashCode()
	{
		int result = lon != null ? lon.hashCode() : 0;
		result = 31 * result + (lat != null ? lat.hashCode() : 0);
		return result;
	}


	@Override
	public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		CoordEntity coord = (CoordEntity) o;

		if (lon != null ? !lon.equals(coord.lon) : coord.lon != null)
			return false;
		return lat != null ? lat.equals(coord.lat) : coord.lat == null;

	}


	public Double getLon()
	{
		return lon;
	}


	public void setLon(Double lon)
	{
		this.lon = lon;
	}


	public Double getLat()
	{
		return lat;
	}


	public void setLat(Double lat)
	{
		this.lat = lat;
	}
}
