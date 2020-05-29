package com.blacktea.platform.controller;

import com.blacktea.common.base.BaseController;
import com.blacktea.common.base.BaseResult;
import com.blacktea.common.config.CheckParam;
import com.blacktea.common.config.CheckToken;
import com.blacktea.platform.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author TR
 * @Create 2020年05月26日
 * @Title: SystemController
 * @Description: 系统相关
 */
@RestController
@RequestMapping("platform")
public class SystemController extends BaseController {
	
	@Autowired
	private SystemService systemService;

	/**
	 * @Author TR
	 * @Create 2020/5/27 17:16
	 * @Title: login
	 * @Params: [params]
	 * @Description: 登录
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public BaseResult login(String params){
		return  systemService.login(getJSONObjectParams(params));
	}

	/**
	 * @Author TR
	 * @Create 2020/5/27 17:16
	 * @Title: logout
	 * @Params: [token]
	 * @Description: 登录
	 */
	@CheckToken()
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public BaseResult logout(@CheckParam() String token){
		return  systemService.logout(token);
	}

	/**
	 * @Author TR
	 * @Create 2020/5/28 11:00
	 * @Title: updateUserPassword
	 * @Params: [token, params]
	 * @Description: 修改用户密码
	 */
	@CheckToken()
	@RequestMapping(value = "/updateuserpassword", method = RequestMethod.POST)
	public BaseResult updateUserPassword(@CheckParam() String token, String params){
		return systemService.updateUserPassword(token,getJSONObjectParams(params));
	}

}
