package com.somewan.wanwea.data;

import com.somewan.common.util.HttpCallbackListener;
import com.somewan.common.util.HttpHelper;
import com.somewan.wanwea.dao.CityDao;
import com.somewan.wanwea.dao.CountyDao;
import com.somewan.wanwea.dao.DayWeatherDao;
import com.somewan.wanwea.dao.ProvinceDao;
import com.somewan.wanwea.dao.WeatherTypeDao;
import com.somewan.wanwea.domin.Province;

public class ProvinceGet {
	private final String province_url = "http://www.weather.com.cn/data/list3/city.xml";
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
	public void getProvince() {
		HttpHelper.sendHttpGet(province_url, new HttpCallbackListener() {

			@Override
			public void onFinish(String response) {
				String[] parts = response.split(",");
				for (int i = 0; i < parts.length; i++) {
					String part = parts[i];
					String[] array = part.split("\\|");
					Province p = new Province(array[0], array[1]);
					provinceDao.save(p);
				}
			}

			@Override
			public void onError(Exception e) {
				e.printStackTrace();
			}

		});
	}
}
