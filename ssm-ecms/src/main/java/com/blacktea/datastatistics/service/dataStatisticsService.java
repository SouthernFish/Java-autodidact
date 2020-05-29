package com.blacktea.datastatistics.service;

import com.blacktea.common.base.BaseResult;
import com.blacktea.common.base.BaseService;
import com.blacktea.common.page.PageForSystem;
import com.blacktea.datastatistics.proxy.dataStatisticsProxy;
import com.blacktea.entity.SystemOperatorEntity;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author TR
 * @Create 2020年05月28日
 * @Title: dataStatisticsService
 * @Description: 数据统计业务层
 */
@Service("dataStatisticsService")
public class dataStatisticsService extends BaseService {

	@Autowired
	private dataStatisticsProxy dataStatisticsProxy;

	public BaseResult getDataStatistics(SystemOperatorEntity operator, JSONObject params) {
		PageForSystem page = null;
		String starTime;
		String endTime;

		// 参数
		try {
			page = getPageEntity(params);
			starTime = params.get("starTime") == null ? null : params.getString("starTime");
			endTime = params.get("endTime") == null ? null : params.getString("endTime");
		}catch (Exception e){
			e.printStackTrace();
			return errorParamsResult();
		}

		// 数据处理
		try {
			List<Map<String,Object>> result = new ArrayList<>();
			// 地区列表
			List<Map<String,Object>> areaList = dataStatisticsProxy.getAreaStatistics(starTime,endTime);
			for(Map area : areaList){
				Map<String,Object> temp = new HashMap<>();
				temp.put("address",area.get("address").toString());
				List<Map<String,Object>> dataList = dataStatisticsProxy.getDataStatistics(area.get("address").toString(),starTime,endTime);
				temp.put("data", dataList);
				result.add(temp);
			}
			return successResult("获取商品列表成功", result);
		}catch (Exception e){
			e.printStackTrace();
			return errorExceptionResult();
		}
	}
}
