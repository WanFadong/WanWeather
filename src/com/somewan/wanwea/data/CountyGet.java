package com.somewan.wanwea.data;

import com.somewan.common.util.DebugHelper;
import com.somewan.common.util.HttpCallbackListener;
import com.somewan.common.util.HttpHelper;
import com.somewan.wanwea.dao.CityDao;
import com.somewan.wanwea.dao.CountyDao;
import com.somewan.wanwea.dao.DayWeatherDao;
import com.somewan.wanwea.dao.ProvinceDao;
import com.somewan.wanwea.dao.WeatherTypeDao;
import com.somewan.wanwea.domin.City;
import com.somewan.wanwea.domin.County;

public class CountyGet {
	private final String county_url = "http://www.weather.com.cn/data/list3/city";
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

	public void getCounty(String cityCode) {
		String address = county_url + cityCode + ".xml";
		if (DebugHelper.getDebugState()) {
			System.out.println(address);
		}
		HttpHelper.sendHttpGet(address, new HttpCallbackListener() {

			@Override
			public void onFinish(String response) {
				String[] parts = response.split(",");
				for (int i = 0; i < parts.length; i++) {
					String[] array = parts[i].split("\\|");
					County county = new County();
					county.setCode(array[0]);
					county.setName(array[1]);
					City city = cityDao.findByCode(cityCode);
					county.setCity(city);
					getWeatherCode(array[0], county);
				}
			}

			/**
			 * 获取县天气代号
			 * 
			 * @param countyCode
			 * @param county
			 */
			private void getWeatherCode(String countyCode, County county) {
				String address = county_url + countyCode + ".xml";
				HttpHelper.sendHttpGet(address, new HttpCallbackListener() {

					@Override
					public void onFinish(String response) {
						String[] array = response.split("\\|");
						if (array.length < 2) {
							System.out.println(address);
						} else {
							String weatherCode = array[1];
							county.setWeatherCode(weatherCode);
							countyDao.save(county);
						}
					}

					@Override
					public void onError(Exception e) {
						e.printStackTrace();
					}

				});
			}

			@Override
			public void onError(Exception e) {
				e.printStackTrace();
			}

		});
	}

}
