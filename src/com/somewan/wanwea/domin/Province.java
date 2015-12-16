package com.somewan.wanwea.domin;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "province")
public class Province implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, length = 50, unique = true)
	private String code;

	@Column(nullable = false, length = 50)
	private String name;

	@OneToMany(targetEntity = City.class, mappedBy = "province")
	private Set<City> cities = new HashSet<City>();

	public Province() {
		super();
	}

	public Province(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<City> getCities() {
		return cities;
	}

	public void setCities(Set<City> cities) {
		this.cities = cities;
	}

	/**
	 * 这方法应该不是给开发者调用的，而是给系统调用的。一般情况下，只要code相等就可以 但不能用来判断两个对象是否真实相等。
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		// if(obj==null){
		// return true;
		// }不需要考虑obj为null，此时必不相等。如果相等的话，那么obj就=this了
		if (obj != null) {
			if (obj.getClass() == this.getClass()) {
				Province another = (Province) obj;
				if (this.code == null && another.code == null) {
					return true;
				}
				if (code.equals(another.code)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = result * prime + code.hashCode();
		result = result * prime + name.hashCode();
		return result;
	}

}
