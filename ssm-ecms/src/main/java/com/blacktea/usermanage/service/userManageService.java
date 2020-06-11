package com.blacktea.usermanage.service;

import com.blacktea.common.base.BaseResult;
import com.blacktea.common.base.BaseService;
import com.blacktea.common.page.PageForSystem;
import com.blacktea.entity.SystemOperatorEntity;
import com.blacktea.usermanage.proxy.userManageProxy;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author TR
 * @Create 2020年05月28日
 * @Title: userManageService
 * @Description: 用户管理业务层
 */
@Service("userManageService")
public class userManageService extends BaseService {

	@Autowired
	private userManageProxy userManageProxy;

	/**
	 * @Author TR
	 * @Create 2020/5/28 9:19
	 * @Title: getUserList
	 * @Params: [operator, params]
	 * @Description: 获取用户列表
	 */
	public BaseResult getUserList(SystemOperatorEntity operator, JSONObject params){
		PageForSystem page = null;
		String searchText;

		// 参数
		try {
			page = getPageEntity(params);
			searchText = params.get("searchText") == null ? null : params.getString("searchText");
		}catch (Exception e){
			e.printStackTrace();
			return errorParamsResult();
		}

		// 数据处理
		try {
			
			List<Map<String,Object>> list = userManageProxy.getUserList(page,searchText);
			return successResult("用户列表获取成功", list);
		}catch (Exception e){
			e.printStackTrace();
			return errorExceptionResult();
		}
	}

	/**
	 * @Author TR
	 * @Create 2020/5/28 10:38
	 * @Title: updateUserStatus
	 * @Params: [operator, params]
	 * @Description: 修改用户状态
	 */
	public BaseResult updateUserStatus(SystemOperatorEntity operator, JSONObject params){
		Integer userStatus;
		Integer operatorId;
		// 参数
		try {
			if(params.get("userStatus") == null){
				return errorParamsResult();
			}
			userStatus = Integer.parseInt(params.get("userStatus").toString());
			operatorId = operator.getOperatorId();
		}catch (Exception e){
			e.printStackTrace();
			return errorParamsResult();
		}

		// 数据处理
		try {
			Integer total = userManageProxy.updateUserStatus(userStatus,operatorId);
			// 修改密码
			if (total.intValue() > 0) {
				return successResult("用户状态修改成功");
			} else {
				return errorResult("操作失败，请稍后重试");
			}
		}catch (Exception e){
			e.printStackTrace();
			return errorExceptionResult();
		}
	}

	/**
	 * @Author TR
	 * @Create 2020/5/28 10:42
	 * @Title: distributeUser
	 * @Description: 分配用户
	 */
	public BaseResult distributeUser(JSONObject params){
		Integer operatorId;
		// 参数
		try {
			if(params.get("operatorId") == null){
				return errorParamsResult();
			}
			operatorId = Integer.parseInt(params.get("operatorId").toString());
		}catch (Exception e){
			e.printStackTrace();
			return errorParamsResult();
		}

		// 数据处理
		try {
			Integer total = 0; //userManageProxy.distributeUser(operatorId);
			if (total.intValue() > 0) {
				return successResult("用户分配成功");
			} else {
				return errorResult("操作失败，请稍后重试");
			}
		}catch (Exception e){
			e.printStackTrace();
			return errorExceptionResult();
		}
	}

	public BaseResult deleteUser(JSONObject params) {
		Integer operatorId;

		try {
			operatorId = Integer.parseInt(params.getString("operatorId"));
		}catch (Exception e){
			e.printStackTrace();
			return errorParamsResult();
		}

		// 数据处理
		try {
			Integer total = userManageProxy.deleteUser(operatorId);
			if (total.intValue() > 0) {
				return successResult("用户删除成功");
			} else {
				return errorResult("操作失败，请稍后重试");
			}
		}catch (Exception e){
			e.printStackTrace();
			return errorExceptionResult();
		}
	}

	/**
	 * @Author TR
	 * @Create 2020/5/28 16:38
	 * @Title: addUser
	 * @Description: 添加用户
	 */
	public BaseResult addUser(JSONObject params) {
		String operatorPwd;
		String operatorName;
		String operatorTel;
		String operatorEmail;
		// 参数
		try {
			operatorName = params.getString("operatorName");
			operatorPwd = params.getString("operatorPwd");
			operatorTel = params.getString("operatorTel");
			operatorEmail = params.getString("operatorEmail");
		}catch (Exception e){
			e.printStackTrace();
			return errorParamsResult();
		}

		// 数据处理
		try {
			// 查询用户名是否已经存在
			Integer flag = userManageProxy.judgeUserExist(operatorName);
			if(flag != null){
				return errorResult("用户名已存在");
			}
			Integer total = userManageProxy.addUser(operatorName,operatorPwd,operatorTel,operatorEmail);
			if (total.intValue() > 0) {
				return successResult("用户添加成功");
			} else {
				return errorResult("操作失败，请稍后重试");
			}
		}catch (Exception e){
			e.printStackTrace();
			return errorExceptionResult();
		}
	}

	/**
	 * @Author TR
	 * @Create 2020/5/28 16:39
	 * @Title: modifyUser
	 * @Description: 编辑用户
	 */
	public BaseResult modifyUser(JSONObject params) {
		String operatorTel;
		String operatorEmail;
		Integer operatorId;
		// 参数
		try {
			operatorTel = params.getString("operatorTel");
			operatorEmail = params.getString("operatorEmail");
			operatorId = Integer.parseInt(params.getString("operatorId"));
		}catch (Exception e){
			e.printStackTrace();
			return errorParamsResult();
		}

		// 数据处理
		try {
			Integer total = userManageProxy.modifyUser(operatorTel,operatorEmail,operatorId);
			if (total.intValue() > 0) {
				return successResult("用户信息修改成功");
			} else {
				return errorResult("操作失败，请稍后重试");
			}
		}catch (Exception e){
			e.printStackTrace();
			return errorExceptionResult();
		}
	}
}
