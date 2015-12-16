package com.somewan.wanwea.domin;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "county")
public class County implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;

	@Column(length = 50, nullable = false, unique = true)
	private String code;// 县code,

	@Column(name = "weather_code", length = 50, nullable = false, unique = true)
	private String weatherCode;// 县天气code；

	public String getWeatherCode() {
		return weatherCode;
	}

	public void setWeatherCode(String weatherCode) {
		this.weatherCode = weatherCode;
	}

	@Column(nullable = false, length = 50)
	private String name;

	@ManyToOne(targetEntity = City.class)
	@JoinColumn(name = "city_id")
	private City city;

	@OneToMany(targetEntity = DayWeather.class, mappedBy = "county")
	private Set<DayWeather> dayWeathers = new HashSet<DayWeather>();

	public County() {
		super();
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

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Set<DayWeather> getDayWeathers() {
		return dayWeathers;
	}

	public void setDayWeathers(Set<DayWeather> dayWeathers) {
		this.dayWeathers = dayWeathers;
	}

	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + code.hashCode();
		result = prime * result + code.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj != null) {
			if (obj.getClass() == this.getClass()) {
				County another = (County) obj;
				if (another.getCode() == null && this.getCode() == null) {
					return true;
				}
				if (another.getCode().equals(another.getCode())) {
					return true;
				}
			}
		}
		return false;
	}

}
