package com.blacktea.datastatistics.controller;

import com.blacktea.common.base.BaseController;
import com.blacktea.common.base.BaseResult;
import com.blacktea.common.config.CheckParam;
import com.blacktea.common.config.CheckToken;
import com.blacktea.datastatistics.service.dataStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author TR
 * @Create 2020年05月28日
 * @Title: dataStatisticsController
 * @Description: 数据统计控制层
 */
@RestController
@RequestMapping("data")
public class dataStatisticsController extends BaseController {

	@Autowired
	private dataStatisticsService dataStatisticsService;

	@CheckToken
	@RequestMapping(value = "/getdatastatistics", method = RequestMethod.POST)
	public BaseResult getDataStatistics(@CheckParam() String token, String params){
		return dataStatisticsService.getDataStatistics(getOperatorByToken(token), getJSONObjectParams(params));
	}
}
