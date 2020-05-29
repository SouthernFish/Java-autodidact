package com.blacktea.common.base;


/**
 * @Author TR
 * @Create 2020年05月26日
 * @Title: BaseCode
 * @Description: 前台状态码
 */
public class BaseCode {
	/**
	 * ===============================================参数 start==========================================================
	 */
	public static String PARAM_NAME = "params";// params 必传JSON格式
	public static String PARAM_NAME_TOKEN = "token";// token 必传
	public static String HEADERS_KEY = "headers";
	public static String PARAM_NAME_VERSION = "version";// params中的参数 必传:当前手机安装应用所用接口版本号
	public static String PARAM_NAME_APP_VERSION = "appVersion";// params中的参数 必传:当前手机安装应用版本号
	public static String PARAM_NAME_SYSTEM_TYPE = "systemType";// params中的参数 必传:当前手机操作系统类型（1安卓、2苹果）

	public static Integer Android = 1;// （1安卓、2苹果）
	public static Integer IOS = 2;// （1安卓、2苹果）
	
	public static String PARAM_NAME_MOBILE_CODE = "mobileCode";// params中的参数 必传:当前手机唯一ID
	public static String PARAM_NAME_REGISTRATION_ID = "registrationID";//极光注册码
	
	public static String TOKEN_LOGIN_OPERAOTR_GW = "TOKEN_LOGIN_OPERAOTR_GW_";
	
	public static String PARAM_NAME_PAGE_INDEX = "pageIndex";
	public static String PARAM_NAME_PAGE_ROWS = "pageRows";
	/**
	 * ===============================================参数 end==========================================================
	 */

	// 成功状态码
	public static Integer SUCCESS = 200;

	// 入参错误
	public static Integer ERROR_PARAM = 400;

	// 错误状态码
	public static Integer ERROR = 500;

	// 未登录状态码
	public static Integer NOT_LOGIN = 101;
	// 未登录提示信息
	public static String NOT_LOGIN_MSG = "对不起，您的登录已过期，请重新登录!";

	// 未登录状态码
	public static Integer TOKEN_CHANGE = 102;
	// 未登录提示信息
	public static String TOKEN_CHANGE_MSG = "您的账号在其他手机登录，是否重新登录?";

	//楼盘禁用提示信息
	public static Integer BUILDING_CLOSE = 103;
	// 楼盘禁用提示信息
	public static String BUILDING_CLOSE_MSG = "对不起，您的默认楼盘已经禁用，请重新登录选择楼盘!";
	
	// 版本错误状态码
	public static Integer VERSION_ERROR = 901;
	// 未登录提示信息
	public static String VERSION_ERROR_MSG = "你安装的版本不正确，请重新下载安装.";

	// 版本作废提示状态码
	public static Integer VERSION_ABOLISH = 902;
	// 未登录提示信息
	public static String VERSION_ABOLISH_MSG = "你安装的版本已经废除，请更新最新版本.";

	// 版本作废提示状态码
	public static Integer HEADER_NULL_ERROR = 903;
	// 未登录提示信息
	public static String HEADER_NULL_ERROR_MSG = "传入的请求头参数存在内容为空.";
}
