package com.somewan.wanwea.data;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.somewan.common.util.DebugHelper;
import com.somewan.wanwea.dao.CountyDao;
import com.somewan.wanwea.domin.County;

public class InitData {
	private ApplicationContext ctx;

	public InitData() {
		ctx = new ClassPathXmlApplicationContext("daoContext.xml", "dataContext.xml");
	}

	public static void main(String[] args) {
		InitData initData = new InitData();
		initData.initDayWeather();
		// initData.deletInvalidCounty();
	}

	private void initProvince() {

	}

	private void initCity() {

	}

	private void initCounty() {

	}

	private void initDayWeather() {
		CountyDao countyDao = ctx.getBean("countyDao", CountyDao.class);
		DayWeatherGet dayWeatherGet = ctx.getBean("dayWeatherGet", DayWeatherGet.class);
		List<County> counties = countyDao.findAll(County.class);
		for (int i = 0; i < counties.size(); i++) {
			String code = counties.get(i).getWeatherCode();
			dayWeatherGet.getDayWeather(code);
		}
	}

	private void deletInvalidCounty() {
		CountyData countyData = ctx.getBean("countyData", CountyData.class);
		countyData.deleteInvalidCounty();
	}
}