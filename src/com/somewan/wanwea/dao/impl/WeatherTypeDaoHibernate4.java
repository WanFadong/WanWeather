package com.somewan.wanwea.dao.impl;

import java.util.List;

import com.somewan.common.dao.impl.BaseDaoHibernate4;
import com.somewan.wanwea.dao.WeatherTypeDao;
import com.somewan.wanwea.domin.WeatherType;

public class WeatherTypeDaoHibernate4 extends BaseDaoHibernate4<WeatherType> implements WeatherTypeDao {

	@Override
	public WeatherType findByCode(String code) {
		List<WeatherType> weatherTypes = find("select w from WeatherType as w where w.code=?0", code);
		if (weatherTypes.size() > 0) {
			return weatherTypes.get(0);
		}
		return null;
	}

}
