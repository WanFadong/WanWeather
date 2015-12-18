package com.somewan.wanwea.service.impl;

import java.util.List;

import com.somewan.wanwea.dao.*;
import com.somewan.wanwea.domin.City;
import com.somewan.wanwea.domin.County;
import com.somewan.wanwea.domin.DayWeather;
import com.somewan.wanwea.domin.Province;
import com.somewan.wanwea.service.WeatherService;

public class WeatherServiceImpl implements WeatherService {
	private final String defaultCountyWeatherCode = "101010100";

	private ProvinceDao provinceDao;
	private CityDao cityDao;
	private CountyDao countyDao;
	private DayWeatherDao dayWeatherDao;
	private WeatherTypeDao weatherTypeDao;

	public void setProvinceDao(ProvinceDao provinceDao) {
		this.provinceDao = provinceDao;
	}

	public void setCityDao(CityDao cityDao) {
		this.cityDao = cityDao;
	}

	public void setCountyDao(CountyDao countyDao) {
		this.countyDao = countyDao;
	}

	public void setDayWeatherDao(DayWeatherDao dayWeatherDao) {
		this.dayWeatherDao = dayWeatherDao;
	}

	public void setWeatherTypeDao(WeatherTypeDao weatherTypeDao) {
		this.weatherTypeDao = weatherTypeDao;
	}

	@Override
	public List<Province> getProvinces() {
		List<Province> provinces = provinceDao.findAll(Province.class);
		return provinces;
	}

	@Override
	public List<City> getCities(Province province) {
		List<City> cities = cityDao.findByProvince(province);
		return cities;
	}

	@Override
	public List<County> getCounties(City city) {
		List<County> counties = countyDao.findByCity(city);
		return counties;
	}

	@Override
	public List<DayWeather> getAllDayWeather(County county) {
		List<DayWeather> dayWeathers = dayWeatherDao.findByCounty(county);
		return dayWeathers;
	}

	@Override
	public County getDefaultCounty() {
		return countyDao.findByWeatherCode(defaultCountyWeatherCode);
	}

}
