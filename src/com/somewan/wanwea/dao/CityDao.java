package com.somewan.wanwea.dao;

import java.util.List;

import com.somewan.common.dao.BaseDao;
import com.somewan.wanwea.domin.City;
import com.somewan.wanwea.domin.Province;

public interface CityDao extends BaseDao<City>{
	City findByCode(String code);
	List<City> findByProvince(Province province);
}
