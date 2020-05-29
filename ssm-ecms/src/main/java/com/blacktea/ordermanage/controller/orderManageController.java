package com.blacktea.ordermanage.controller;

import com.blacktea.common.base.BaseController;
import com.blacktea.common.base.BaseResult;
import com.blacktea.common.config.CheckParam;
import com.blacktea.common.config.CheckToken;
import com.blacktea.ordermanage.service.orderManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author TR
 * @Create 2020年05月28日
 * @Title: orderManageController
 * @Description: 订单管理控制层
 */
@RestController
@RequestMapping("order")
public class orderManageController extends BaseController {

	@Autowired
	private orderManageService orderManageService;

	/**
	 * @Author TR
	 * @Create 2020/5/28 17:05
	 * @Title: getOrderList
	 * @Params: [token, params]
	 * @Description:  订单列表
	 */
	@CheckToken
	@RequestMapping(value = "/getorderlist", method = RequestMethod.POST)
	public BaseResult getOrderList(@CheckParam() String token, String params){
		return orderManageService.getOrderList(getOperatorByToken(token), getJSONObjectParams(params));
	}
}
