package com.blacktea.platform.service;

import com.blacktea.common.base.BaseCode;
import com.blacktea.common.base.BaseResult;
import com.blacktea.common.base.BaseService;
import com.blacktea.common.base.Constant;
import com.blacktea.entity.SystemOperatorEntity;
import com.blacktea.platform.proxy.SystemProxy;
import com.blacktea.utils.MD5Utils;
import jodd.util.StringUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author TR
 * @Create 2020年05月26日
 * @Title: SystemService
 * @Description: 业务层
 */
@Service("systemService")
public class SystemService extends BaseService {

	@Autowired
	private SystemProxy systemProxy;

	/**
	 * @Author TR
	 * @Create 2020/5/26 13:54
	 * @Title: login
	 * @Params: []
	 * @Description: 用户登录
	 */
	public BaseResult login(JSONObject params){
		String operatorAccount;
		String operatorPwd;

		// 参数
		try {
			   operatorAccount = params.getString("operatorAccount");
			   operatorPwd = params.getString("operatorPwd");
			   if(StringUtil.isEmpty(operatorAccount) || StringUtil.isEmpty(operatorPwd)){
			   		return errorParamsResult();
			   }
		}catch (Exception e){
			e.printStackTrace();
			return errorParamsResult();
		}

		// 处理
		try {
			JSONObject result = new JSONObject();
			SystemOperatorEntity operator = systemProxy.getOperatorInfoByAccount(operatorAccount);
			if(operator != null){
				if(operator.getOperatorPwd().equals(MD5Utils.MD5EncodePassword(operatorPwd))){
					 if(operator.getOperatorStatus().equals("0")){
					 	return errorResult("当前用户已被禁用");
					 }else{
						 Integer operatorId = operator.getOperatorId();
						 String token = generateToken(operatorId);
						 // 重新缓存用户信息
						 operator = cacheOperator(token, operator);
						 // 变更用户原来记录的token
						 redisUtil.set(BaseCode.TOKEN_LOGIN_OPERAOTR_GW + operator.getOperatorId(), token);

						 result.put("token", token);
						 result.put("operatorTel", operator.getOperatorTel());
						 result.put("operatorName", operator.getOperatorName());
						 result.put("operatorEmail", operator.getOperatorEmail());
						 result.put("registerTime", operator.getRegisterTime());
						 result.put("roleType", systemProxy.getOperatorRoleType(operator.getRoleId()));
						 result.put("pwdFlag", Constant.DEFF_PWD.equals(operator.getOperatorPwd()) ? 1 : 0);

						 return successResult("登录成功", result);
					 }
				}else{
					return errorResult("密码错误");
				}

			}else{
				return errorResult("当前用户未认证");
			}
		}catch (Exception e){
			e.printStackTrace();
			return errorExceptionResult();
		}
	}


	/**
	 * @Author TR
	 * @Create 2020/5/26 13:54
	 * @Title: logout
	 * @Params: [token]
	 * @Description: 退出登录
	 */
	public BaseResult loginOut(String token) {
		Map<String, Object> map = new HashMap<String, Object>();
		SystemOperatorEntity operator = null;
		try {
			operator = getOperatorByToken(token);
			map.put("operatorId", operator.getOperatorId());
			map.put("roleType", systemProxy.getOperatorRoleType(operator.getRoleId()));
			
			redisUtil.delete(token);
			return successResult("退出成功", map);
		} catch (Exception e) {
			e.printStackTrace();
			return errorExceptionResult();
		}
	}

	/**
	 * @Author TR
	 * @Create 2020/5/28 10:58
	 * @Title: updateUserPassword
	 * @Params: [token, params]
	 * @Description: 修改用户密码
	 */
	public BaseResult updateUserPassword(String token, JSONObject params){
		String oldPwd;
		String newPwd;
		// 参数
		try {
			oldPwd = params.getString("oldPwd");
			newPwd = params.getString("newPwd");
			if (StringUtil.isEmpty(oldPwd) || StringUtil.isEmpty(newPwd))
				return errorParamsResult();
			if(Constant.DEFF_PWD.equals(newPwd)) {
				return errorResult("新密码不能与默认密码相同!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return errorParamsResult();
		}
		// 数据处理
		try {
			SystemOperatorEntity operator = getOperatorByToken(token);
			if (!operator.getOperatorPwd().equals(MD5Utils.MD5EncodePassword(oldPwd)))
				return errorResult("原密码错误，请确认");

			if (newPwd.length() < 6)
				return errorResult("密码长度至少6位");

			// 修改密码
			Integer total = systemProxy.updateUserPassword( operator.getOperatorId(), MD5Utils.MD5EncodePassword(newPwd));
			if (total.intValue() > 0) {
				// 重新缓存当前用户
				cacheOperator(token, operator);
				return successResult("密码修改成功");
			} else {
				return errorResult("操作失败，请稍后重试");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return errorExceptionResult();
		}
	}
}
