package com.somewan.wanwea.dao.impl;

import com.somewan.common.dao.impl.BaseDaoHibernate4;
import com.somewan.wanwea.dao.ProvinceDao;
import com.somewan.wanwea.domin.Province;

public class ProvinceDaoHibernate4 extends BaseDaoHibernate4<Province> implements ProvinceDao {

	@Override
	public Province findByCode(String code) {
		java.util.List<Province> provinces = find("select p from Province as p where p.code=?0", code);
		if (provinces.size() > 0) {
			return provinces.get(0);
		}
		return null;
	}

}
