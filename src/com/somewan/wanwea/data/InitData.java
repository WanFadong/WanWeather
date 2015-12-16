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
		ctx = new ClassPathXmlApplicationContext("daoContext.xml", "applicationContext.xml");
	}

	public static void main(String[] args) {
		InitData initData = new InitData();
		initData.initDayWeather();
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
			if (DebugHelper.getDebugState()) {
				if (i != 0 && i % 500 == 0) {
					System.out.println("正在获取第" + i + "个县的天气");
				}
			}
			String code = counties.get(i).getWeatherCode();
			dayWeatherGet.getDayWeather(code);
		}
	}
}