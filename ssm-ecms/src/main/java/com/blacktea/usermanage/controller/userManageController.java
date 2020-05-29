package com.blacktea.usermanage.controller;

import com.blacktea.common.base.BaseController;
import com.blacktea.common.base.BaseResult;
import com.blacktea.common.config.CheckParam;
import com.blacktea.common.config.CheckToken;
import com.blacktea.usermanage.service.userManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author TR
 * @Create 2020年05月28日
 * @Title: userManageController
 * @Description: 用户管理控制层
 */
@RestController
@RequestMapping("user")
public class userManageController extends BaseController {

	@Autowired
	private userManageService userManageService;

	/**
	 * @Author TR
	 * @Create 2020/5/28 9:27
	 * @Title: getUserList
	 * @Params: [token, params]
	 * @Description: 获取用户列表
	 */
	@CheckToken()
	@RequestMapping(value = "/getuserlist",method = RequestMethod.POST)
	public BaseResult getUserList(@CheckParam() String token, String params){
		return userManageService.getUserList(getOperatorByToken(token), getJSONObjectParams(params));
	}

	/**
	 * @Author TR
	 * @Create 2020/5/28 9:27
	 * @Title: UpdateUserStatus
	 * @Params: [token, params]
	 * @Description: 修改用户状态
	 */
	@CheckToken()
	@RequestMapping(value = "/updateuserstatus",method = RequestMethod.POST)
	public BaseResult updateUserStatus(@CheckParam() String token, String params){
		return userManageService.updateUserStatus(getOperatorByToken(token), getJSONObjectParams(params));
	}

	/**
	 * @Author TR
	 * @Create 2020/5/28 11:07
	 * @Title: DeleteUser
	 * @Params: [token, params]
	 * @Description: 删除用户
	 */
	@CheckToken()
	@RequestMapping(value = "/deleteuser",method = RequestMethod.POST)
	public BaseResult deleteUser(String params){
		return userManageService.deleteUser(getJSONObjectParams(params));
	}

	/**
	 * @Author TR
	 * @Create 2020/5/28 11:13
	 * @Title: distributeUser
	 * @Params: [params]
	 * @Description: 分配用户
	 */
	@CheckToken()
	@RequestMapping(value = "/distributeuser",method = RequestMethod.POST)
	public BaseResult distributeUser( String params){
		return userManageService.distributeUser( getJSONObjectParams(params));
	}


	/**
	 * @Author TR
	 * @Create 2020/5/28 13:34
	 * @Title: addUser
	 * @Params: []
	 * @Description: 添加用户
	 */
	@CheckToken
	@RequestMapping(value = "/adduser",method = RequestMethod.POST)
	public BaseResult addUser(@CheckParam() String token, String params){
		return userManageService.addUser( getJSONObjectParams(params));
	}
}
