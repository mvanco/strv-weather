package com.matusvanco.weather.android.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CityEntity
{
	@SerializedName("id")
	@Expose
	private Integer id;

	@SerializedName("name")
	@Expose
	private String name;

	@SerializedName("coord")
	@Expose
	private CoordEntity coord;

	@SerializedName("country")
	@Expose
	private String country;

	@SerializedName("population")
	@Expose
	private Integer population;


	@Override
	public int hashCode()
	{
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (coord != null ? coord.hashCode() : 0);
		result = 31 * result + (country != null ? country.hashCode() : 0);
		result = 31 * result + (population != null ? population.hashCode() : 0);
		return result;
	}


	@Override
	public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		CityEntity city = (CityEntity) o;

		if (id != null ? !id.equals(city.id) : city.id != null)
			return false;
		if (name != null ? !name.equals(city.name) : city.name != null)
			return false;
		if (coord != null ? !coord.equals(city.coord) : city.coord != null)
			return false;
		if (country != null ? !country.equals(city.country) : city.country != null)
			return false;
		return population != null ? population.equals(city.population) : city.population == null;

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


	public CoordEntity getCoord()
	{
		return coord;
	}


	public void setCoord(CoordEntity coord)
	{
		this.coord = coord;
	}


	public String getCountry()
	{
		return country;
	}


	public void setCountry(String country)
	{
		this.country = country;
	}


	public Integer getPopulation()
	{
		return population;
	}


	public void setPopulation(Integer population)
	{
		this.population = population;
	}
}
