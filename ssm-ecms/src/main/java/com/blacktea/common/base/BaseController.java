package com.blacktea.common.base;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @Author TR
 * @Create 2020年05月26日
 * @Title: BaseController
 * @Description: 基础控制类
 */
public class BaseController extends Base {

	/**
	 * 得到request对象
	 */
	protected HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	/**
	 * 得到response对象
	 */
	protected HttpServletResponse getResponse() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
	}

	public BaseResult errorExceptionResult() {
		return generateResult(BaseCode.ERROR,"抱歉！系统未能及时响应，请联系管理员！");
	}
	
	protected BaseResult successResult(String msg) {
		return generateResult(BaseCode.SUCCESS, msg, null);
	}
}
