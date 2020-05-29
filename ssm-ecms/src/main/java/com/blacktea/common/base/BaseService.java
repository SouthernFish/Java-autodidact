package com.blacktea.common.base;

import com.blacktea.utils.MD5Utils;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * @Author TR
 * @Create 2020年05月26日
 * @Title: BaseService
 * @Description: Service基础类
 */
public class BaseService extends Base {

	private static final String YAN = "YueJiaApp$789787mnbqwe@//*-+'[]ctj;384785*^*&%^%$%";// 加盐

	protected BaseResult successResult() {
		return successResult(null, null);
	}

	protected BaseResult successResult(String msg) {
		return successResult(msg, null);
	}

	protected BaseResult successResult(String msg, Object result) {
		return generateResult(BaseCode.SUCCESS, msg, result);
	}

	protected BaseResult errorResult() {
		return errorResult(null, null);
	}

	protected BaseResult errorResult(String msg) {
		return errorResult(msg, null);
	}

	protected BaseResult errorResult(String msg, Object result) {
		return generateResult(BaseCode.ERROR, msg, result);
	}

	protected BaseResult errorExceptionResult() {
		return errorResult("抱歉！系统未能及时响应，请联系管理员！", null);
	}

	protected BaseResult errorParamsResult() {
		return generateResult(BaseCode.ERROR_PARAM, "传递参数或者类型不正确，或者必填参数有空值!", null);
	}

	protected BaseResult errorParamsResult(String resultMsg) {
		return generateResult(BaseCode.ERROR_PARAM, resultMsg, null);
	}

	protected BaseResult errorBusinessTypeResult(String resultMsg, String businessType) {
		return generateResult(BaseCode.ERROR_PARAM, resultMsg, businessType, null);
	}

	protected BaseResult errorBusinessTypeResult(String resultMsg, String businessType, Object object) {
		return generateResult(BaseCode.ERROR_PARAM, resultMsg, businessType, object);
	}

	/**
	 * 生成token
	 *
	 * @return String
	 * @author chentianjin
	 * @date 2017年4月25日
	 */
	protected String generateToken(Integer operatorId) {
		return MD5Utils.Md532(System.currentTimeMillis() + YAN + operatorId);
	}

	/**
	 * 将传入的一个时间转为 x小时y分钟z秒
	 * 
	 * @param longTime 传入一个的时间（单位为毫秒）
	 * 
	 */
	protected String longTimeToHMS(int longTime) {
		String rtnStr = "";
		int seconds = 0;// 秒
		int minues = 0;
		int hour = 0;
		try {
			float floatSeconds = new BigDecimal(longTime).divide(new BigDecimal(1000), new MathContext(2, RoundingMode.HALF_UP))
					.floatValue();
			// 小于1秒的值
			if (floatSeconds < 1) {
				rtnStr = floatSeconds < 0.1 ? ("0秒") : (String.valueOf(floatSeconds) + "秒");
				// 大于1秒的值
			} else {
				seconds = longTime / 1000; // 大于1秒就只取秒 不取毫秒
				// 大于1分钟
				if ((seconds / 60) > 0) {
					minues = (seconds / 60);
					seconds = (seconds % 60);
					// 大于1小时
					if ((minues / 60) > 0) {
						hour = (minues / 60);
						minues = (minues % 60);
						rtnStr = String.valueOf(hour) + "小时" + String.valueOf(minues) + "分钟";
						// 小于1小时
					} else {
						rtnStr = String.valueOf(minues) + "分钟" + String.valueOf(seconds) + "秒";
					}
					// 小于1分钟
				} else {
					rtnStr = String.valueOf(seconds) + "秒";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			rtnStr = "时间计算出错";
		}
		return rtnStr;
	}
}
