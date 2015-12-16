package com.somewan.wanwea.domin;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "city")
public class City implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, length = 50, unique = true)
	private String code;

	@Column(nullable = false, length = 50)
	private String name;

	@ManyToOne(targetEntity = Province.class)
	@JoinColumn(name = "province_id")
	private Province province;

	@OneToMany(targetEntity = County.class, mappedBy = "city")
	private Set<County> counties = new HashSet<County>();

	public City() {
		super();
	}

	public City(String code, String name, Province province) {
		super();
		this.code = code;
		this.name = name;
		this.province = province;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = result * prime + code.hashCode();
		result = result * prime + name.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj != null) {
			if (obj.getClass() == this.getClass()) {
				City another = (City) obj;
				if (code == null && another.getCode() == null) {
					return true;
				}
				if (another.getCode().equals(this.getCode())) {
					return true;
				}
			}
		}
		return false;
	}

	public Set<County> getCounties() {
		return counties;
	}

	public void setCounties(Set<County> counties) {
		this.counties = counties;
	}
}
