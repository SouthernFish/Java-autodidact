package com.blacktea.common.base;

import com.blacktea.utils.ClassPathUtil;
import com.blacktea.utils.ProFileReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * 常量
 * 
 * @author chentianjin
 * @date 2017年5月26日
 */
public class Constant {

	public static String HTTP = "http://";
	
	public static String HTTPS = "https://";

	public static String IMG_HTTP_PATH = "";

	public static final String OPENID_CACHE_INFO_APP = "OPENID_CACHE_INFO_APP_";

	/**
	 * 在线开盘地址
	 */
	public static String OPEN_HTTP_URL = "";

	/**
	 * 置业顾问临时客档详情跳转地址
	 */
	public static String CHANNEL_ABOUT_PATH_SELLER = "";

	/**
	 * 销经跳转渠道管理地址
	 */
	public static String CHANNEL_ABOUT_PATH_MANAGER = "";

	/**
	 * 楼盘分享地址
	 */
	public static String SHARE_BUILDING_HTTP_PATH = "";
	/**
	 * 楼盘分享的跳转地址参数
	 */
	public static String SHARE_BUILDING_URI_PARAM_PATH = "";

	public static String WECHAT_QRCODE_HTTPS_PATH = "";

	public static String REQUEST_SURVEY_URL = "";

	public static final int isOpenTPTK = 1;

	public static String WX_CONFIG_ID = "";
	public static String WX_CONFIG_ID_SC = "";//晟城微信ConfigId
	public static int CHANNEL_CUSTOM_FLOW_OPEN_TYPE;// 是否可以跳转

	public static String CHANNEL_CUSTOM_FLOW_OPEN_WEB_PATH;

	public static String  DEFF_PWD = "2ad441f69208aef37e18aa19d63afe5b";// 默认密码123456
	
	/**
	 * IM服务相关常量
	 */
	public static String RYIM_URL = "";
	public static String RYIM_PORT = "";
	public static String RYIM_NEARLIST_URL = "";
	public static String RYIM_LOGIN_URL = "";
	public static String RYIM_HISTORYMSG_URL = "";
	public static String RYIM_READMSG_URL = "";
	
	/**
	 * 小程序ID、路径
	 */
	public static String SMALL_ROUTINE_ID = "";
	public static String SMALL_ROUTINE_PATH = "";
	
	/**
	 * 微信登陆绑定相关参数:应用标识/应用秘钥
	 */
	public static String WX_APPID = "";
	public static String WX_SECRET = "";

	/**
	 * 惠选小程序id
	 */
	public static String HUIXUAN_SMALL_ROUTINE_ID = "";
	
	/**
	 * 云客在线认购接口
	 * YUNKE_ROOM_LIST_URL:房源列表
	 * YUNKE_CHECK_LIST_URL:审核列表
	 * YUNKE_ORDER_LIST_URL:订单列表
	 */
	public static String YUNKE_ROOM_LIST_URL = "";
	public static String YUNKE_CHECK_LIST_URL = "";
	public static String YUNKE_ORDER_LIST_URL = "";
	public static String YUNKE_ORGCODE = "";
	public static String SHARE_YUNKE_TRADE_URL = "";
	
	/**
	 * 外拓商户id
	 */
	public static int EXT_SC_MERCHANT_ID;
	public static int EXT_DY_MERCHANT_ID;
	public static int EXT_XPR_MERCHANT_ID;
	
	static {
		File file = new File(ClassPathUtil.getPath() + "resource/appservice.properties");
		ProFileReader uploadPropFile;
		try {
			uploadPropFile = new ProFileReader(new FileInputStream(file));
			REQUEST_SURVEY_URL = uploadPropFile.getParamValue("REQUEST_SURVEY_URL");
			OPEN_HTTP_URL = uploadPropFile.getParamValue("OPEN_HTTP_URL");
			CHANNEL_ABOUT_PATH_SELLER = uploadPropFile.getParamValue("CHANNEL_ABOUT_PATH_SELLER");
			CHANNEL_ABOUT_PATH_MANAGER = uploadPropFile.getParamValue("CHANNEL_ABOUT_PATH_MANAGER");
			SHARE_BUILDING_HTTP_PATH = uploadPropFile.getParamValue("SHARE_BUILDING_HTTP_PATH");
			SHARE_BUILDING_URI_PARAM_PATH = uploadPropFile.getParamValue("SHARE_BUILDING_URI_PARAM_PATH");
			IMG_HTTP_PATH = uploadPropFile.getParamValue("IMG_HTTP_PATH");
			WECHAT_QRCODE_HTTPS_PATH = uploadPropFile.getParamValue("WECHAT_QRCODE_HTTPS_PATH");
			WX_CONFIG_ID = uploadPropFile.getParamValue("WX_CONFIG_ID");
			WX_CONFIG_ID_SC = uploadPropFile.getParamValue("WX_CONFIG_ID_SC");
			CHANNEL_CUSTOM_FLOW_OPEN_TYPE = Integer.parseInt(uploadPropFile.getParamValue("CHANNEL_CUSTOM_FLOW_OPEN_TYPE"));
			CHANNEL_CUSTOM_FLOW_OPEN_WEB_PATH = uploadPropFile.getParamValue("CHANNEL_CUSTOM_FLOW_OPEN_WEB_PATH");
			
			RYIM_URL = uploadPropFile.getParamValue("RYIM_URL");
			RYIM_PORT = uploadPropFile.getParamValue("RYIM_PORT");
			RYIM_NEARLIST_URL = uploadPropFile.getParamValue("RYIM_NEARLIST_URL");
			RYIM_LOGIN_URL = uploadPropFile.getParamValue("RYIM_LOGIN_URL");
			RYIM_HISTORYMSG_URL = uploadPropFile.getParamValue("RYIM_HISTORYMSG_URL");
			RYIM_READMSG_URL = uploadPropFile.getParamValue("RYIM_READMSG_URL");
			
			SMALL_ROUTINE_ID = uploadPropFile.getParamValue("SMALL_ROUTINE_ID");
			SMALL_ROUTINE_PATH = uploadPropFile.getParamValue("SMALL_ROUTINE_PATH");
			
			WX_APPID = uploadPropFile.getParamValue("WX_APPID");
			WX_SECRET = uploadPropFile.getParamValue("WX_SECRET");

			HUIXUAN_SMALL_ROUTINE_ID = uploadPropFile.getParamValue("HUIXUAN_SMALL_ROUTINE_ID");
			
			YUNKE_ROOM_LIST_URL = uploadPropFile.getParamValue("YUNKE_ROOM_LIST_URL");
			YUNKE_CHECK_LIST_URL = uploadPropFile.getParamValue("YUNKE_CHECK_LIST_URL");
			YUNKE_ORDER_LIST_URL = uploadPropFile.getParamValue("YUNKE_ORDER_LIST_URL");
			YUNKE_ORGCODE = uploadPropFile.getParamValue("YUNKE_ORGCODE");
			SHARE_YUNKE_TRADE_URL = uploadPropFile.getParamValue("SHARE_YUNKE_TRADE_URL");
			
			EXT_SC_MERCHANT_ID = Integer.parseInt(uploadPropFile.getParamValue("EXT_SC_MERCHANT_ID"));
			EXT_DY_MERCHANT_ID = Integer.parseInt(uploadPropFile.getParamValue("EXT_DY_MERCHANT_ID"));
			EXT_XPR_MERCHANT_ID = Integer.parseInt(uploadPropFile.getParamValue("EXT_XPR_MERCHANT_ID"));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
