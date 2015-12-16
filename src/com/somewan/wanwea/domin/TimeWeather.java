package com.somewan.wanwea.domin;

/**
 * 由于获取不到数据，此类暂时没有用处
 */
import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "time_weather")
public class TimeWeather implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private Integer temp;

	@ManyToOne(targetEntity = DayWeather.class)
	@JoinColumn(name = "day_weather_id")
	private DayWeather dayWeather;

	@ManyToOne(targetEntity = TimeBucket.class)
	@JoinColumn(name = "time_bucket_id")
	private TimeBucket timeBucket;

	public TimeWeather() {
		super();
	}

	public TimeWeather(Integer temp, DayWeather dayWeather, TimeBucket timeBucket) {
		super();
		this.temp = temp;
		this.dayWeather = dayWeather;
		this.timeBucket = timeBucket;
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

	public DayWeather getDayWeather() {
		return dayWeather;
	}

	public void setDayWeather(DayWeather dayWeather) {
		this.dayWeather = dayWeather;
	}

	public TimeBucket getTimeBucket() {
		return timeBucket;
	}

	public void setTimeBucket(TimeBucket timeBucket) {
		this.timeBucket = timeBucket;
	}

	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = result * prime + dayWeather.hashCode();
		result = result * prime + timeBucket.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}

		if (obj != null) {
			if (obj.getClass() == this.getClass()) {
				TimeWeather another = (TimeWeather) obj;
				boolean equal = false;
				// 条件1
				if (this.dayWeather == null && another.dayWeather == null) {
					equal = true;
				}
				if (another.dayWeather.equals(this.dayWeather)) {
					equal = true;
				}
				// 条件2
				if (equal) {
					if (this.timeBucket == null && another.timeBucket == null) {
						return true;
					}
					if (another.dayWeather.equals(this.dayWeather)) {
						return true;
					}
				}
			}

		}
		return false;
	}
}
