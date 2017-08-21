package com.matusvanco.weather.android.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherEntity
{
	@SerializedName("id")
	@Expose
	private Integer id;

	@SerializedName("main")
	@Expose
	private String main;

	@SerializedName("description")
	@Expose
	private String description;

	@SerializedName("icon")
	@Expose
	private String icon;


	@Override
	public int hashCode()
	{
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (main != null ? main.hashCode() : 0);
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + (icon != null ? icon.hashCode() : 0);
		return result;
	}


	@Override
	public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		WeatherEntity weather = (WeatherEntity) o;

		if (id != null ? !id.equals(weather.id) : weather.id != null)
			return false;
		if (main != null ? !main.equals(weather.main) : weather.main != null)
			return false;
		if (description != null ? !description.equals(weather.description) : weather.description != null)
			return false;
		return icon != null ? icon.equals(weather.icon) : weather.icon == null;

	}


	public Integer getId()
	{
		return id;
	}


	public void setId(Integer id)
	{
		this.id = id;
	}


	public String getMain()
	{
		return main;
	}


	public void setMain(String main)
	{
		this.main = main;
	}


	public String getDescription()
	{
		return description;
	}


	public void setDescription(String description)
	{
		this.description = description;
	}


	public String getIcon()
	{
		return icon;
	}


	public void setIcon(String icon)
	{
		this.icon = icon;
	}
}
