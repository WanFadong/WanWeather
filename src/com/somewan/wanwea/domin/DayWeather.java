package com.somewan.wanwea.domin;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "day_weather")
public class DayWeather implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private Integer temp;

	@Column(nullable = false)
	private Date date;

	@ManyToOne(targetEntity = WeatherType.class)
	@JoinColumn(name = "weather_type_id")
	private WeatherType weatherType;

	@ManyToOne(targetEntity = County.class)
	@JoinColumn(name = "county_id")
	private County county;

	@OneToMany(targetEntity = TimeWeather.class, mappedBy = "dayWeather")
	private Set<TimeWeather> timeWeathers = new HashSet<TimeWeather>();

	public DayWeather() {
		super();
	}

	public DayWeather(Integer temp, Date date, WeatherType weatherType, County county) {
		super();
		this.temp = temp;
		this.date = date;
		this.weatherType = weatherType;
		this.county = county;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTemp() {
		return temp;
	}

	public void setTemp(Integer temp) {
		this.temp = temp;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public WeatherType getWeatherType() {
		return weatherType;
	}

	public void setWeatherType(WeatherType weatherType) {
		this.weatherType = weatherType;
	}

	public County getCounty() {
		return county;
	}

	public void setCounty(County county) {
		this.county = county;
	}

	public Set<TimeWeather> getTimeWeathers() {
		return timeWeathers;
	}

	public void setTimeWeathers(Set<TimeWeather> timeWeathers) {
		this.timeWeathers = timeWeathers;
	}

	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = result * prime + date.hashCode();
		result = result * prime + county.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj != null) {
			if (obj.getClass() == this.getClass()) {
				DayWeather another = (DayWeather) obj;
				if (another.getDate().equals(this.getDate())) {
					if (another.getCounty().equals(this.getCounty())) {
						return true;
					}
				}
			}
		}
		return false;
	}

}
