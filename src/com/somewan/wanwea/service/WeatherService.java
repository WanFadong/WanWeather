package com.somewan.wanwea.service;

import java.util.List;

import com.somewan.wanwea.domin.City;
import com.somewan.wanwea.domin.County;
import com.somewan.wanwea.domin.DayWeather;
import com.somewan.wanwea.domin.Province;

public interface WeatherService {
	//获取默认县
	public County getDefaultCounty();
	
	// 获取城市
	public List<Province> getProvinces();

	public List<City> getCities(Province province);

	public List<County> getCounties(City city);

	// 获取天气
	public List<DayWeather> getAllDayWeather(County county);
}
