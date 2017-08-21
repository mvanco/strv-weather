package com.matusvanco.weather.android.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CurrentWeatherEntity
{
	@SerializedName("coord")
	@Expose
	private CoordEntity coord;

	@SerializedName("weather")
	@Expose
	private List<WeatherEntity> weather = null;

	@SerializedName("base")
	@Expose
	private String base;

	@SerializedName("main")
	@Expose
	private MainEntity main;

	@SerializedName("visibility")
	@Expose
	private Integer visibility;

	@SerializedName("wind")
	@Expose
	private WindEntity wind;

	@SerializedName("rain")
	@Expose
	private RainEntity rain;

	@SerializedName("clouds")
	@Expose
	private CloudsEntity clouds;

	@SerializedName("dt")
	@Expose
	private Integer dt;

	@SerializedName("sys")
	@Expose
	private SysEntity sys;

	@SerializedName("id")
	@Expose
	private Integer id;

	@SerializedName("name")
	@Expose
	private String name;

	@SerializedName("cod")
	@Expose
	private Integer cod;


	@Override
	public int hashCode()
	{
		int result = coord != null ? coord.hashCode() : 0;
		result = 31 * result + (weather != null ? weather.hashCode() : 0);
		result = 31 * result + (base != null ? base.hashCode() : 0);
		result = 31 * result + (main != null ? main.hashCode() : 0);
		result = 31 * result + (visibility != null ? visibility.hashCode() : 0);
		result = 31 * result + (wind != null ? wind.hashCode() : 0);
		result = 31 * result + (rain != null ? rain.hashCode() : 0);
		result = 31 * result + (clouds != null ? clouds.hashCode() : 0);
		result = 31 * result + (dt != null ? dt.hashCode() : 0);
		result = 31 * result + (sys != null ? sys.hashCode() : 0);
		result = 31 * result + (id != null ? id.hashCode() : 0);
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (cod != null ? cod.hashCode() : 0);
		return result;
	}


	@Override
	public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		CurrentWeatherEntity that = (CurrentWeatherEntity) o;

		if (coord != null ? !coord.equals(that.coord) : that.coord != null)
			return false;
		if (weather != null ? !weather.equals(that.weather) : that.weather != null)
			return false;
		if (base != null ? !base.equals(that.base) : that.base != null)
			return false;
		if (main != null ? !main.equals(that.main) : that.main != null)
			return false;
		if (visibility != null ? !visibility.equals(that.visibility) : that.visibility != null)
			return false;
		if (wind != null ? !wind.equals(that.wind) : that.wind != null)
			return false;
		if (rain != null ? !rain.equals(that.rain) : that.rain != null)
			return false;
		if (clouds != null ? !clouds.equals(that.clouds) : that.clouds != null)
			return false;
		if (dt != null ? !dt.equals(that.dt) : that.dt != null)
			return false;
		if (sys != null ? !sys.equals(that.sys) : that.sys != null)
			return false;
		if (id != null ? !id.equals(that.id) : that.id != null)
			return false;
		if (name != null ? !name.equals(that.name) : that.name != null)
			return false;
		return cod != null ? cod.equals(that.cod) : that.cod == null;

	}


	public CoordEntity getCoord()
	{
		return coord;
	}


	public void setCoord(CoordEntity coord)
	{
		this.coord = coord;
	}


	public List<WeatherEntity> getWeather()
	{
		return weather;
	}


	public void setWeather(List<WeatherEntity> weather)
	{
		this.weather = weather;
	}


	public String getBase()
	{
		return base;
	}


	public void setBase(String base)
	{
		this.base = base;
	}


	public MainEntity getMain()
	{
		return main;
	}


	public void setMain(MainEntity main)
	{
		this.main = main;
	}


	public Integer getVisibility()
	{
		return visibility;
	}


	public void setVisibility(Integer visibility)
	{
		this.visibility = visibility;
	}


	public WindEntity getWind()
	{
		return wind;
	}


	public void setWind(WindEntity wind)
	{
		this.wind = wind;
	}


	public RainEntity getRain()
	{
		return rain;
	}


	public void setRain(RainEntity rain)
	{
		this.rain = rain;
	}


	public CloudsEntity getClouds()
	{
		return clouds;
	}


	public void setClouds(CloudsEntity clouds)
	{
		this.clouds = clouds;
	}


	public Integer getDt()
	{
		return dt;
	}


	public void setDt(Integer dt)
	{
		this.dt = dt;
	}


	public SysEntity getSys()
	{
		return sys;
	}


	public void setSys(SysEntity sys)
	{
		this.sys = sys;
	}


	public Integer getId()
	{
		return id;
	}


	public void setId(Integer id)
	{
		this.id = id;
	}


	public String getName()
	{
		return name;
	}


	public void setName(String name)
	{
		this.name = name;
	}


	public Integer getCod()
	{
		return cod;
	}


	public void setCod(Integer cod)
	{
		this.cod = cod;
	}
}
