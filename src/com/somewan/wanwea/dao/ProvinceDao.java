package com.somewan.wanwea.dao;

import com.somewan.common.dao.BaseDao;
import com.somewan.wanwea.domin.Province;

public interface ProvinceDao extends BaseDao<Province> {
	Province findByCode(String code);
}
