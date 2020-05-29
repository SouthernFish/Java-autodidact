package com.blacktea.datastatistics.proxy;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author TR
 * @Create 2020年05月28日
 * @Title: dataStatisticsProxy
 * @Description: 数据统计代理层
 */
@Repository("dataStatisticsProxy")
public interface dataStatisticsProxy {


	List<Map<String, Object>> getAreaStatistics(@Param("starTime") String starTime,
												@Param("endTime") String endTime);

	/**
	 * @Author TR
	 * @Create 2020/5/28 17:23
	 * @Title: getDataStatistics
	 * @Params: [startTime， endTime]
	 * @Description: 数据统计
	 */
	List<Map<String,Object>> getDataStatistics(@Param("address") String address,
											   @Param("starTime") String starTime,
											   @Param("endTime") String endTime);
}
