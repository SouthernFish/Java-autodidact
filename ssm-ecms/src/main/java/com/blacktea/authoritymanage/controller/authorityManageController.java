package com.blacktea.authoritymanage.controller;

import com.blacktea.authoritymanage.service.authorityManageService;
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
 * @Title: authorityManageController
 * @Description: 权限管理控制层
 */
@RestController
@RequestMapping("authority")
public class authorityManageController extends BaseController {

	@Autowired
	private authorityManageService authorityManageService;

	/**
	 * @Author TR
	 * @Create 2020/5/28 12:06
	 * @Title: getRoleTypeList
	 * @Params: [token, params]
	 * @Description: 获取角色列表
	 */
	@CheckToken
	@RequestMapping(value = "/getroletypelist",method = RequestMethod.POST)
	public BaseResult getRoleTypeList(@CheckParam() String token, String params){
		return authorityManageService.getRoleTypeList(getOperatorByToken(token), getJSONObjectParams(params));
	}


	/**
	 * @Author TR
	 * @Create 2020/5/28 14:35
	 * @Title: distributeAuthority
	 * @Params: [token, params]
	 * @Description: 分配权限
	 */
	@CheckToken
	@RequestMapping(value = "/distributeauthority",method = RequestMethod.POST)
	public BaseResult distributeAuthority(@CheckParam() String token, String params){
		return authorityManageService.distributeAuthority(getOperatorByToken(token), getJSONObjectParams(params));
	}

	/**
	 * @Author TR
	 * @Create 2020/5/28 14:38
	 * @Title: getAuthorityList
	 * @Params: [token, params]
	 * @Description: 权限列表
	 */
	@CheckToken
	@RequestMapping(value = "/getauthoritylist", method = RequestMethod.POST)
	public BaseResult getAuthorityList(@CheckParam() String token, String params){
		return authorityManageService.getAuthorityList(getOperatorByToken(token), getJSONObjectParams(params));
	}

}
