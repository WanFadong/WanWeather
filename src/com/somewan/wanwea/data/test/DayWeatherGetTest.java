package com.somewan.wanwea.data.test;

import com.somewan.wanwea.data.DayWeatherGet;

public class DayWeatherGetTest {
	public void testGetDayWeather() {
		String cityCode = "101010100";
		new DayWeatherGet().getDayWeather(cityCode);
	}

	public static void main(String[] args) {
		new DayWeatherGetTest().testGetDayWeather();
	}
}
