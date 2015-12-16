package com.somewan.wanwea.domin;

/**
 * 由于获取不到数据，此类暂时没有用处
 */
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "time_bucket")
public class TimeBucket implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, length = 50, unique = true)
	private String time;

	@OneToMany(targetEntity = TimeWeather.class, mappedBy = "timeBucket")
	private Set<TimeWeather> timeWeathers = new HashSet<TimeWeather>();

	public TimeBucket() {
		super();
	}

	public TimeBucket(String time) {
		super();
		this.time = time;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
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
		result = result * prime + time.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj != null) {
			if (obj.getClass() == this.getClass()) {
				TimeBucket another = (TimeBucket) obj;
				if (getTime() == null && another.getTime() == null)
					return true;
				if (another.getTime().equals(this.getTime()))
					return true;
			}
		}
		return false;
	}

}
