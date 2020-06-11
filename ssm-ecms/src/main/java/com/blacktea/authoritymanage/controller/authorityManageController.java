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


	/**
	 * @Author TR
	 * @Create 2020/6/9 13:57
	 * @Title: addRoles
	 * @Description: 添加角色
	 */
	@CheckToken
	@RequestMapping(value = "/addrole",method = RequestMethod.POST)
	public BaseResult addRole(@CheckParam() String token, String params){
		return authorityManageService.addRole(getOperatorByToken(token), getJSONObjectParams(params));
	}


	/**
	 * @Author TR
	 * @Create 2020/6/9 13:57
	 * @Title: deleteRoles
	 * @Description: 删除角色
	 */
	@CheckToken
	@RequestMapping(value = "/deleterole",method = RequestMethod.POST)
	public BaseResult deleteRole(@CheckParam() String token, String params){
		return authorityManageService.deleteRole(getOperatorByToken(token), getJSONObjectParams(params));
	}

	/**
	 * @Author TR
	 * @Create 2020/6/9 13:57
	 * @Title: modifyRoles
	 * @Description: 编辑角色
	 */
	@CheckToken
	@RequestMapping(value = "/modifyrole",method = RequestMethod.POST)
	public BaseResult modifyRole(@CheckParam() String token, String params){
		return authorityManageService.modifyRole(getOperatorByToken(token), getJSONObjectParams(params));
	}

	/**
	 * @Author TR
	 * @Create 2020/6/9 14:15
	 * @Title: getRoleDetail
	 * @Description: 获取角色详细信息
	 */
	@CheckToken
	@RequestMapping(value = "/getroledetail",method = RequestMethod.POST)
	public BaseResult getRoleDetail(@CheckParam() String token, String params){
		return authorityManageService.getRoleDetail(getOperatorByToken(token), getJSONObjectParams(params));
	}

	@CheckToken
	@RequestMapping(value = "/deleteroleauthority",method = RequestMethod.POST)
	public BaseResult deleteRoleAuthority(@CheckParam() String token, String params){
		return authorityManageService.deleteRoleAuthority(getOperatorByToken(token), getJSONObjectParams(params));
	}
}
