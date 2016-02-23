package com.somewan.wanwea.data;

import java.util.List;

import com.somewan.common.util.DebugHelper;
import com.somewan.common.util.FileHelper;
import com.somewan.wanwea.domin.County;

public class CountyData extends CountyGet {
	private final String fileName = "day_weather_init_error_log.txt";

	/**
	 * 根据日志文件中的数据删除无效的县
	 * 
	 * @param countyWeatherCode
	 */
	private void deleteCounty(String countyWeatherCode) {
		County county = countyDao.findByWeatherCode(countyWeatherCode);
		countyDao.delete(county);
	}

	public void deleteInvalidCounty() {
		List<String> logList = FileHelper.readLog(fileName);
		for (int i = 0; i < logList.size(); i++) {
			String logLine = logList.get(i);
			int begin = 43;
			int end = 52;
			String countyWeatherCode = logLine.substring(begin, end);
			if (DebugHelper.getDebugState()) {
				if (i % 10 == 0) {
					System.out.println(countyWeatherCode);
					System.out.println("正在处理log第" + i + "行");
				}
			}
			deleteCounty(countyWeatherCode);
		}
	}
}
