package com.somewan.wanwea.dao.impl;

import java.util.List;

import com.somewan.common.dao.impl.BaseDaoHibernate4;
import com.somewan.wanwea.dao.CountyDao;
import com.somewan.wanwea.domin.City;
import com.somewan.wanwea.domin.County;

public class CountyDaoHibernate4 extends BaseDaoHibernate4<County> implements CountyDao {

	@Override
	public County findByWeatherCode(String code) {
		List<County> counties = find("select c from County as c where c.weatherCode=?0", code);
		if (counties.size() > 0) {
			return counties.get(0);
		}
		return null;
	}

	@Override
	public List<County> findByCity(City city) {
		return find("select c from County as c where c.city=?0", city);
	}

}
