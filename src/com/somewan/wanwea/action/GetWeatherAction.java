package com.somewan.wanwea.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.somewan.wanwea.domin.County;
import com.somewan.wanwea.domin.DayWeather;
import com.somewan.wanwea.service.WeatherService;

public class GetWeatherAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private WeatherService weatherService;
	private County county;
	private List<DayWeather> dayWeathers;

	public void setWeatherService(WeatherService service) {
		this.weatherService = service;
	}

	public County getCounty() {
		return county;
	}

	public void setCounty(County county) {
		this.county = county;
	}

	public List<DayWeather> getDayWeathers() {
		return dayWeathers;
	}

	public void setDayWeathers(List<DayWeather> dayWeathers) {
		this.dayWeathers = dayWeathers;
	}

	public String execute() {
		if (county == null) {
			// county默认值是北京
			county = weatherService.getDefaultCounty();
		}
		setDayWeathers(weatherService.getAllDayWeather(county));
		return SUCCESS;
	}

}
