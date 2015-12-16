package com.somewan.wanwea.dao.impl;

import java.util.List;

import com.somewan.common.dao.impl.BaseDaoHibernate4;
import com.somewan.wanwea.dao.DayWeatherDao;
import com.somewan.wanwea.domin.County;
import com.somewan.wanwea.domin.DayWeather;

public class DayWeatherDaoHibernate4 extends BaseDaoHibernate4<DayWeather> implements DayWeatherDao {

	@Override
	public List<DayWeather> findByCounty(County county) {
		return find("select d from DayWeather as d where d.county=?0", county);
	}

}
