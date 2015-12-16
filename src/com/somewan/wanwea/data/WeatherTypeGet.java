package com.somewan.wanwea.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import com.somewan.wanwea.dao.CityDao;
import com.somewan.wanwea.dao.CountyDao;
import com.somewan.wanwea.dao.DayWeatherDao;
import com.somewan.wanwea.dao.ProvinceDao;
import com.somewan.wanwea.dao.WeatherTypeDao;
import com.somewan.wanwea.domin.WeatherType;

/**
 * 从文件中读取
 * 
 * @author fadon
 *
 */

public class WeatherTypeGet {
	private final String filePath = "WebContent\\weatherType.txt";
	
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

	public void getWeatherType() {
		File file = new File(filePath);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line = null;
			line = reader.readLine();
			if (line != null) {
				String[] parts = line.split(",");
				for (int i = 0; i < parts.length; i++) {
					String part = parts[i];
					String[] array = part.split(":");
					String code = array[0].substring(1, array[0].length() - 1);
					String name = array[1].substring(1, array[1].length() - 1);
					// 存入数据库
					WeatherType weatherType = new WeatherType(code, name);
					weatherTypeDao.save(weatherType);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
}
