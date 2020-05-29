package com.blacktea.ordermanage.service;

import com.blacktea.common.base.BaseResult;
import com.blacktea.common.base.BaseService;
import com.blacktea.common.page.PageForSystem;
import com.blacktea.entity.SystemOperatorEntity;
import com.blacktea.ordermanage.proxy.orderManageProxy;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author TR
 * @Create 2020年05月28日
 * @Title:orderManageService
 * @Description: 订单管理业务层
 */
@Service("orderManageService")
public class orderManageService extends BaseService {

	@Autowired
	private orderManageProxy orderManageProxy;

	/**
	 * @Author TR
	 * @Create 2020/5/28 17:06
	 * @Title: getOrderList
	 * @Params: [operator, params]
	 * @Description: 订单列表
	 */
	public BaseResult getOrderList(SystemOperatorEntity operator, JSONObject params) {
		PageForSystem page = null;
		String searchText;

		// 参数
		try {
			page = getPageEntity(params);
			searchText = params.get("searchText") == null ? null : params.getString("searchText");
		}catch (Exception e){
			e.printStackTrace();
			return errorParamsResult();
		}

		// 数据处理
		try {
			List<Map<String,Object>> commodityList = orderManageProxy.getOrderList(page,searchText);
			return successResult("获取商品列表成功", commodityList);
		}catch (Exception e){
			e.printStackTrace();
			return errorExceptionResult();
		}
	}

	public BaseResult modifyOrder(SystemOperatorEntity operator, JSONObject params){

		// 参数
		try {

		}catch (Exception e){
			e.printStackTrace();
			return errorParamsResult();
		}

		// 数据处理
		try {
			Integer total = 0; //userManageProxy.deleteUser(userStatus,operatorId);

			if (total.intValue() > 0) {
				return successResult("订单修改成功");
			} else {
				return errorResult("操作失败，请稍后重试");
			}


		}catch (Exception e){
			e.printStackTrace();
			return errorExceptionResult();
		}
	}
}
