package com.blacktea.commoditymanage.service;

import com.blacktea.commoditymanage.proxy.commodityManageProxy;
import com.blacktea.common.base.BaseResult;
import com.blacktea.common.base.BaseService;
import com.blacktea.common.page.PageForSystem;
import com.blacktea.entity.SystemOperatorEntity;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author TR
 * @Create 2020年05月28日
 * @Title: commodityManageService
 * @Description: 商品管理业务层
 */
@Service("commodityManageService")
public class commodityManageService extends BaseService {
	@Autowired
	private commodityManageProxy commodityManageProxy;

	/**
	 * @Author TR
	 * @Create 2020/5/28 15:53
	 * @Title: getCommodityList
	 * @Params: [operator, params]
	 * @Description: 商品列表
	 */
	public BaseResult getCommodityList(SystemOperatorEntity operator, JSONObject params){
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
			List<Map<String,Object>> commodityList = commodityManageProxy.getCommodityList(page,searchText);
			return successResult("获取商品列表成功", commodityList);
		}catch (Exception e){
			e.printStackTrace();
			return errorExceptionResult();
		}
	}

	/**
	 * @Author TR
	 * @Create 2020/5/28 16:08
	 * @Title: deleteCommodity
	 * @Params: [operator, params]
	 * @Description: 删除商品
	 */
	public BaseResult deleteCommodity(SystemOperatorEntity operator, JSONObject params) {
		Integer commodityId;
		// 参数
		try {
			commodityId =Integer.parseInt(params.getString("commodityId")) ;
		}catch (Exception e){
			e.printStackTrace();
			return errorParamsResult();
		}

		// 数据处理
		try {
			Integer total = commodityManageProxy.deleteCommodity(commodityId);
			if (total.intValue() > 0) {
				return successResult("商品删除成功");
			} else {
				return errorResult("操作失败，请稍后重试");
			}
		}catch (Exception e){
			e.printStackTrace();
			return errorExceptionResult();
		}
	}

	/**
	 * @Author TR
	 * @Create 2020/5/28 16:13
	 * @Title: addCommodity
	 * @Params: [operator, params]
	 * @Description: 添加商品
	 */
	public BaseResult addCommodity(SystemOperatorEntity operator, JSONObject params) {

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
				return successResult("商品添加成功");
			} else {
				return errorResult("操作失败，请稍后重试");
			}


		}catch (Exception e){
			e.printStackTrace();
			return errorExceptionResult();
		}
	}

	/**
	 * @Author TR
	 * @Create 2020/5/28 16:37
	 * @Title: modifyCommodity
	 * @Params: [operator, params]
	 * @Description: 编辑分类
	 */
	public BaseResult modifyCommodity(SystemOperatorEntity operator, JSONObject params) {

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
				return successResult("商品修改成功");
			} else {
				return errorResult("操作失败，请稍后重试");
			}


		}catch (Exception e){
			e.printStackTrace();
			return errorExceptionResult();
		}
	}

	/**
	 * @Author TR
	 * @Create 2020/5/28 16:31
	 * @Title: getCategoryList
	 * @Params: [operatorByToken, params]
	 * @Description: 分类列表
	 */
	public BaseResult getCategoryList(SystemOperatorEntity operatorByToken, JSONObject params) {
		PageForSystem page = null;

		// 参数
		try {
			page = getPageEntity(params);

		}catch (Exception e){
			e.printStackTrace();
			return errorParamsResult();
		}

		// 数据处理
		try {
			List<Map<String,Object>> commodityList = commodityManageProxy.getCategoryList(page);
			return successResult("获取角色列表成功", commodityList);
		}catch (Exception e){
			e.printStackTrace();
			return errorExceptionResult();
		}
	}


	/**
	 * @Author TR
	 * @Create 2020/5/28 16:35
	 * @Title: deleteCategory
	 * @Params: [operatorByToken, params]
	 * @Description: 删除分类
	 */
	public BaseResult deleteCategory(SystemOperatorEntity operatorByToken, JSONObject params){
		Integer categoryId;

		// 参数
		try {
			categoryId =Integer.parseInt(params.getString("categoryId")) ;
		}catch (Exception e){
			e.printStackTrace();
			return errorParamsResult();
		}

		// 数据处理
		try {
			Integer total = commodityManageProxy.deleteCategory(categoryId);
			if (total.intValue() > 0) {
				return successResult("分类删除成功");
			} else {
				return errorResult("操作失败，请稍后重试");
			}
		}catch (Exception e){
			e.printStackTrace();
			return errorExceptionResult();
		}
	}


	/**
	 * @Author TR
	 * @Create 2020/5/28 16:34
	 * @Title: addCategory
	 * @Params: [operatorByToken, params]
	 * @Description: 添加分类
	 */
	public BaseResult addCategory(SystemOperatorEntity operatorByToken, JSONObject params){

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
				return successResult("分类添加成功");
			} else {
				return errorResult("操作失败，请稍后重试");
			}
		}catch (Exception e){
			e.printStackTrace();
			return errorExceptionResult();
		}
	}

	/**
	 * @Author TR
	 * @Create 2020/5/28 16:36
	 * @Title: modifyCategory
	 * @Params: [operatorByToken, params]
	 * @Description: 编辑分类
	 */
	public BaseResult modifyCategory(SystemOperatorEntity operatorByToken, JSONObject params){

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
				return successResult("分类修改成功");
			} else {
				return errorResult("操作失败，请稍后重试");
			}
		}catch (Exception e){
			e.printStackTrace();
			return errorExceptionResult();
		}
	}
}
