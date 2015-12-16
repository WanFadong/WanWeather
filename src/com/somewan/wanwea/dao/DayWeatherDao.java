package com.somewan.wanwea.dao;

import java.util.List;

import com.somewan.common.dao.BaseDao;
import com.somewan.wanwea.domin.County;
import com.somewan.wanwea.domin.DayWeather;

public interface DayWeatherDao extends BaseDao<DayWeather>{
	public List<DayWeather> findByCounty(County county);
}
