package com.blacktea.common.base;


import com.blacktea.common.page.PageForSystem;
import com.blacktea.entity.SystemOperatorEntity;
import com.blacktea.utils.*;
import com.google.gson.Gson;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

/**
 * @Author TR
 * @Create 2020年05月26日
 * @Title: Base
 * @Description: 公共基础类
 */
public class Base {
	// tonken过期时间 默认7天 单位毫秒
	protected static final Integer TOKEN_TIME = 604800;


	@Autowired
	public RedisUtil redisUtil;



	/**
	 * @Author TR
	 * @Create 2020/5/28 9:15
	 * @Title: getJSONObjectParams
	 * @Params: [params]
	 * @Description: 将传入的参数解析成JSONObject
	 */
	protected JSONObject getJSONObjectParams(String params) {
		if (StringUtils.isEmpty(params))
			return new JSONObject();
		if (!params.contains(":")) {
			try {
				params = EncryptionUtil.decrypt2Str(params);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		JSONObject obj = JSONObject.fromObject(params);
		if (obj == null || obj.isNullObject()) {
			obj = new JSONObject();
		}
		return obj;
	}


	/**
	 * @Author TR
	 * @Create 2020/5/28 9:14
	 * @Title: cacheOperator
	 * @Params: [token,SystemOperatorEntity]
	 * @Description: 重新设置缓存用户V2
	 */
	protected SystemOperatorEntity cacheOperator(String token, SystemOperatorEntity operator) {
		redisUtil.setex(token, TOKEN_TIME, new Gson().toJson(operator));
		return operator;
	}

	// 生成返回结果集
	protected BaseResult generateResult(Integer code, String msg) {
		return generateResult(code, msg, null);
	}

	// 生成返回结果集
	protected BaseResult generateResult(Integer code, String msg, Object result) {
		boolean isEncryption = false;
		File file = new File(ClassPathUtil.getPath() + "resource/appservice.properties");
		ProFileReader uploadPropFile;
		try {
			uploadPropFile = new ProFileReader(new FileInputStream(file));
			isEncryption = "true".equals(uploadPropFile.getParamValue("IS_ENCRYPTION"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			BaseResult br = new BaseResult();
			br.setCode(code);
			br.setMsg(msg);

			if (isEncryption && null != result) {
				br.setResult(EncryptionUtil.encrypt2Str(JsonUtil.objectToJsonStr(result)));
			} else {
				br.setResult(result);
			}

			return br;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	protected BaseResult generateResult(Integer eRROR_PARAM, String resultMsg, String businessType, Object object) {
		BaseResult result = generateResult(BaseCode.ERROR_PARAM, resultMsg, object);
		result.setBusinessType(businessType);
		return result;
	}

	
	/**
	 * @Author TR
	 * @Create 2020/5/27 17:27
	 * @Title: getOperatorByToken
	 * @Params: [token]
	 * @Description: 根据token获取当前用户
	 */
	protected SystemOperatorEntity getOperatorByToken(String token) {
		String operatorStr = redisUtil.get(token);
		if (StringUtils.isEmpty(operatorStr)) {
			return null;
		}
		SystemOperatorEntity operator = new Gson().fromJson(operatorStr, SystemOperatorEntity.class);

		return operator;
	}

	/**
	 * @Author TR
	 * @Create 2020/5/28 9:07
	 * @Title: getPageEntity
	 * @Params: [token]
	 * @Description: 获得分页实体
	 */
	protected PageForSystem getPageEntity(JSONObject params) throws Exception {
		PageForSystem pageInfo = new PageForSystem();
		if (params.get(BaseCode.PARAM_NAME_PAGE_INDEX) != null) {
			if (params.getInt(BaseCode.PARAM_NAME_PAGE_INDEX) <= 0)
				throw new Exception();
			pageInfo.setPage(params.getInt(BaseCode.PARAM_NAME_PAGE_INDEX));
		}
		if (params.get(BaseCode.PARAM_NAME_PAGE_ROWS) != null) {
			if (params.getInt(BaseCode.PARAM_NAME_PAGE_ROWS) <= 0)
				throw new Exception();
			pageInfo.setRows(params.getInt(BaseCode.PARAM_NAME_PAGE_ROWS));
		}
		return pageInfo;
	}
}
