package com.matusvanco.weather.android.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CloudsEntity
{
	@SerializedName("all")
	@Expose
	private Integer all;


	@Override
	public int hashCode()
	{
		return all != null ? all.hashCode() : 0;
	}


	@Override
	public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		CloudsEntity clouds = (CloudsEntity) o;

		return all != null ? all.equals(clouds.all) : clouds.all == null;

	}


	public Integer getAll()
	{
		return all;
	}


	public void setAll(Integer all)
	{
		this.all = all;
	}
}
