package com.somewan.wanwea.dao;

import java.util.List;

import com.somewan.common.dao.BaseDao;
import com.somewan.wanwea.domin.City;
import com.somewan.wanwea.domin.County;

public interface CountyDao extends BaseDao<County> {
	County findByWeatherCode(String code);

	List<County> findByCity(City city);
}
