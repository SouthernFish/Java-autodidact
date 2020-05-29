package com.blacktea.commoditymanage.controller;

import com.blacktea.commoditymanage.service.commodityManageService;
import com.blacktea.common.base.BaseController;
import com.blacktea.common.base.BaseResult;
import com.blacktea.common.config.CheckParam;
import com.blacktea.common.config.CheckToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author TR
 * @Create 2020年05月28日
 * @Title: commodityManageController
 * @Description: 商品管理控制层
 */
@RestController
@RequestMapping("commodity")
public class commodityManageController extends BaseController {

	@Autowired
	private commodityManageService commodityManageService;

	/**
	 * @Author TR
	 * @Create 2020/5/28 15:53
	 * @Title: getCommodityList
	 * @Params: [token, params]
	 * @Description: 商品列表
	 */
	@CheckToken
	@RequestMapping(value = "/getcommoditylist", method = RequestMethod.POST)
	public BaseResult getCommodityList(@CheckParam() String token, String params){
		return commodityManageService.getCommodityList(getOperatorByToken(token), getJSONObjectParams(params));
	}

	/**
	 * @Author TR
	 * @Create 2020/5/28 16:11
	 * @Title: deleteCommodity
	 * @Params: [token, params]
	 * @Description: 删除商品
	 */
	@CheckToken
	@RequestMapping(value = "/deletecommodity", method = RequestMethod.POST)
	public BaseResult deleteCommodity(@CheckParam() String token, String params){
		return commodityManageService.deleteCommodity(getOperatorByToken(token),getJSONObjectParams(params));
	}


	/**
	 * @Author TR
	 * @Create 2020/5/28 16:13
	 * @Title: addCommodity
	 * @Params: [token, params]
	 * @Description: 添加商品
	 */
	@CheckToken
	@RequestMapping(value = "/addcommodity", method = RequestMethod.POST)
	public BaseResult addCommodity(@CheckParam() String token, String params){
		return commodityManageService.addCommodity(getOperatorByToken(token),getJSONObjectParams(params));
	}


	/**
	 * @Author TR
	 * @Create 2020/5/28 16:40
	 * @Title: getCategoryList
	 * @Params: [token, params]
	 * @Description: 分类列表
	 */
	@CheckToken
	@RequestMapping(value = "/getcategorylist", method = RequestMethod.POST)
	public BaseResult getCategoryList(@CheckParam() String token, String params){
		return commodityManageService.getCategoryList(getOperatorByToken(token),getJSONObjectParams(params));
	}

	/**
	 * @Author TR
	 * @Create 2020/5/28 16:58
	 * @Title: deleteCategory
	 * @Params: [token, params]
	 * @Description: 删除分类
	 */
	@CheckToken
	@RequestMapping(value = "/deletecategory", method = RequestMethod.POST)
	public BaseResult deleteCategory(@CheckParam() String token, String params){
		return commodityManageService.deleteCategory(getOperatorByToken(token),getJSONObjectParams(params));
	}

	/**
	 * @Author TR
	 * @Create 2020/5/28 16:58
	 * @Title: addCategory
	 * @Params: [token, params]
	 * @Description: 添加分类
	 */
	@CheckToken
	@RequestMapping(value = "/addcategory", method = RequestMethod.POST)
	public BaseResult addCategory(@CheckParam() String token, String params){
		return commodityManageService.addCategory(getOperatorByToken(token),getJSONObjectParams(params));
	}

	// 分类参数
}
