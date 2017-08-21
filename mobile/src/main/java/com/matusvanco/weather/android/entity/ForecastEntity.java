package com.matusvanco.weather.android.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForecastEntity
{
	@SerializedName("city")
	@Expose
	private CityEntity city;

	@SerializedName("cod")
	@Expose
	private String cod;

	@SerializedName("message")
	@Expose
	private Double message;

	@SerializedName("cnt")
	@Expose
	private Integer cnt;

	@SerializedName("list")
	@Expose
	private java.util.List<ForecastItemEntity> list = null;


	@Override
	public int hashCode()
	{
		int result = city != null ? city.hashCode() : 0;
		result = 31 * result + (cod != null ? cod.hashCode() : 0);
		result = 31 * result + (message != null ? message.hashCode() : 0);
		result = 31 * result + (cnt != null ? cnt.hashCode() : 0);
		result = 31 * result + (list != null ? list.hashCode() : 0);
		return result;
	}


	@Override
	public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		ForecastEntity forecast = (ForecastEntity) o;

		if (city != null ? !city.equals(forecast.city) : forecast.city != null)
			return false;
		if (cod != null ? !cod.equals(forecast.cod) : forecast.cod != null)
			return false;
		if (message != null ? !message.equals(forecast.message) : forecast.message != null)
			return false;
		if (cnt != null ? !cnt.equals(forecast.cnt) : forecast.cnt != null)
			return false;
		return list != null ? list.equals(forecast.list) : forecast.list == null;

	}


	public CityEntity getCity()
	{
		return city;
	}


	public void setCity(CityEntity city)
	{
		this.city = city;
	}


	public String getCod()
	{
		return cod;
	}


	public void setCod(String cod)
	{
		this.cod = cod;
	}


	public Double getMessage()
	{
		return message;
	}


	public void setMessage(Double message)
	{
		this.message = message;
	}


	public Integer getCnt()
	{
		return cnt;
	}


	public void setCnt(Integer cnt)
	{
		this.cnt = cnt;
	}


	public java.util.List<ForecastItemEntity> getList()
	{
		return list;
	}


	public void setList(java.util.List<ForecastItemEntity> list)
	{
		this.list = list;
	}
}
