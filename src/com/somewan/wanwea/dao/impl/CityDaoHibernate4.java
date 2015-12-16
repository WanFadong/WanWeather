package com.somewan.wanwea.dao.impl;

import java.util.List;

import com.somewan.common.dao.impl.BaseDaoHibernate4;
import com.somewan.wanwea.domin.City;
import com.somewan.wanwea.domin.Province;
import com.somewan.wanwea.dao.CityDao;

public class CityDaoHibernate4 extends BaseDaoHibernate4<City> implements CityDao {

	@Override
	public List<City> findByProvince(Province province) {
		return find("select c from City as c where c.province=?0", province);
	}

	@Override
	public City findByCode(String code) {
		List<City> cities = find("select c from City as c where c.code=?0", code);
		if (cities.size() >= 0) {
			return cities.get(0);
		}
		return null;
	}

}
