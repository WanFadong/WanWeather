package com.somewan.wanwea.data;

import com.somewan.common.util.DebugHelper;
import com.somewan.common.util.FileHelper;
import com.somewan.common.util.HttpCallbackListener;
import com.somewan.common.util.HttpHelper;
import com.somewan.wanwea.dao.CityDao;
import com.somewan.wanwea.dao.CountyDao;
import com.somewan.wanwea.dao.DayWeatherDao;
import com.somewan.wanwea.dao.ProvinceDao;
import com.somewan.wanwea.dao.WeatherTypeDao;
import com.somewan.wanwea.domin.County;
import com.somewan.wanwea.domin.DayWeather;
import com.somewan.wanwea.domin.WeatherType;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class DayWeatherGet {
	private final String fileName = "day_weather_init_error_log.txt";
	private final String day_weather_url = "http://mobile.weather.com.cn/data/forecast/";
	private ProvinceDao provinceDao;
	private CityDao cityDao;
	private CountyDao countyDao;
	private DayWeatherDao dayWeatherDao;
	private WeatherTypeDao weatherTypeDao;

	public void setDayWeatherDao(DayWeatherDao dayWeatherDao) {
		this.dayWeatherDao = dayWeatherDao;
	}

	public void setWeatherTypeDao(WeatherTypeDao weatherTypeDao) {
		this.weatherTypeDao = weatherTypeDao;
	}

	public void setProvinceDao(ProvinceDao provinceDao) {
		this.provinceDao = provinceDao;
	}

	public void setCityDao(CityDao cityDao) {
		this.cityDao = cityDao;
	}

	public void setCountyDao(CountyDao countyDao) {
		this.countyDao = countyDao;
	}

	public void getDayWeather(String countyWeatherCode) {
		// 获取数据
		String address = day_weather_url + countyWeatherCode + ".html";
		HttpHelper.sendHttpGet(address, new HttpCallbackListener() {

			@Override
			public void onFinish(String response) {
				// 解析数据
				try {
					JSONObject jsonObject = JSONObject.fromObject(response);
					String f = jsonObject.getString("f");

					jsonObject = JSONObject.fromObject(f);
					String f1 = jsonObject.getString("f1");

					JSONArray jsonArray = JSONArray.fromString(f1);
					for (int i = 0; i < jsonArray.length(); i++) {
						jsonObject = jsonArray.getJSONObject(i);
						String fa = jsonObject.getString("fa");// 天气1
						String fb = jsonObject.getString("fb");// 天气2
						String fc = jsonObject.getString("fc");// 温度1
						String fd = jsonObject.getString("fd");// 温度2
						if (DebugHelper.getDebugState(false)) {
							System.out.println(fa + "|" + fb + "|" + fc + "|" + fd);
						}
						// 存储数据
						/**
						 * 目前存储的是：温度使用fc，天气使用fa；
						 */
						DayWeather dayWeather = new DayWeather();
						dayWeather.setTemp(Integer.valueOf(fc));

						java.util.Date date = new java.util.Date();
						java.sql.Date sqlDate = new java.sql.Date(date.getTime() + i * 24 * 60 * 60 * 1000);
						dayWeather.setDate(sqlDate);

						County county = countyDao.findByWeatherCode(countyWeatherCode);
						dayWeather.setCounty(county);
						WeatherType weatherType = weatherTypeDao.findByCode(fa);
						dayWeather.setWeatherType(weatherType);
						dayWeatherDao.save(dayWeather);
					}

				} catch (JSONException je) {
					String content = address + "不是Json数据";
					FileHelper.writeLog(fileName, content);
				} catch (Exception e) {
					System.out.println(address);
					e.printStackTrace();
				}

			}

			@Override
			public void onError(Exception e) {
				e.printStackTrace();
			}
		});

	}
}
