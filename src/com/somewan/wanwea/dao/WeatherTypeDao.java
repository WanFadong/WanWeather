package com.somewan.wanwea.dao;

import com.somewan.common.dao.BaseDao;
import com.somewan.wanwea.domin.WeatherType;

public interface WeatherTypeDao extends BaseDao<WeatherType> {
	WeatherType findByCode(String code);
}
