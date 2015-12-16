package com.somewan.wanwea.domin;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "weather_type")
public class WeatherType implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, length = 50, unique = true)
	private String code;

	@Column(nullable = false, length = 50, unique = true)
	private String name;

	@OneToMany(targetEntity = DayWeather.class, mappedBy = "weatherType")
	private Set<DayWeather> dayWeathers = new HashSet<DayWeather>();

	public WeatherType() {
		super();
	}

	public WeatherType(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<DayWeather> getDayWeathers() {
		return dayWeathers;
	}

	public void setDayWeathers(Set<DayWeather> dayWeathers) {
		this.dayWeathers = dayWeathers;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
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
				WeatherType another = (WeatherType) obj;
				if (name == null && another.getName() == null) {
					return true;
				}
				if (another.getName().equals(this.getName())) {
					return true;
				}
			}
		}
		return false;
	}
}
