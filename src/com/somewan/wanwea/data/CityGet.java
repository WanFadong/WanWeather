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
import com.somewan.wanwea.domin.Province;

public class CityGet {
	private final String city_url = "http://www.weather.com.cn/data/list3/city";
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
	}	public void getCity(String provinceCode) {
		String address = city_url + provinceCode + ".xml";
		HttpHelper.sendHttpGet(address, new HttpCallbackListener() {

			@Override
			public void onFinish(String response) {
				String[] parts = response.split(",");
				for (int i = 0; i < parts.length; i++) {
					String[] array = parts[i].split("\\|");
					City city = new City();
					city.setCode(array[0]);
					city.setName(array[1]);
					if (DebugHelper.getDebugState()) {
						if (provinceDao == null)
							System.out.println("provinceDao is null");
					}
					Province province = provinceDao.findByCode(provinceCode);
					city.setProvince(province);
					cityDao.save(city);
				}
			}

			@Override
			public void onError(Exception e) {
				e.printStackTrace();
			}

		});
	}
}
