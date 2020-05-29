package com.blacktea.authoritymanage.service;

import com.blacktea.authoritymanage.proxy.authorityManageProxy;
import com.blacktea.common.base.BaseResult;
import com.blacktea.common.base.BaseService;
import com.blacktea.common.page.PageForSystem;
import com.blacktea.entity.SystemOperatorEntity;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author TR
 * @Create 2020年05月28日
 * @Title: authorityManageService
 * @Description: 权限管理业务层
 */
@Service("authorityManageService")
public class authorityManageService extends BaseService {

	@Autowired
	private authorityManageProxy authorityManageProxy;

	/**
	 * @Author TR
	 * @Create 2020/5/28 12:06
	 * @Title: getRoleTypeList
	 * @Params: [operator, params]
	 * @Description: 获取角色列表
	 */
	public BaseResult getRoleTypeList(SystemOperatorEntity operator, JSONObject params){
		PageForSystem page = null;

		// 参数
		try {
			page = getPageEntity(params);

		}catch (Exception e){
			e.printStackTrace();
			return errorParamsResult();
		}

		// 数据处理
		try {
			List<Map<String,Object>> roleList = authorityManageProxy.getRoleTypeList(page);
			return successResult("获取角色列表成功", roleList);
		}catch (Exception e){
			e.printStackTrace();
			return errorExceptionResult();
		}
	}

	/**
	 * @Author TR
	 * @Create 2020/5/28 14:35
	 * @Title: distributeAuthority
	 * @Params: [operator, params]
	 * @Description: 分配权限
	 */
	public BaseResult distributeAuthority(SystemOperatorEntity operator, JSONObject params) {
		String authorityName;
		Integer roleId;

		// 参数
		try {
			if(params.get("authorityName") == null || params.get("roleId") == null){
				return errorParamsResult();
			}
			authorityName = params.getString("authorityName");
			roleId = Integer.parseInt(params.getString("roleId"));
		}catch (Exception e){
			e.printStackTrace();
			return errorParamsResult();
		}

		// 数据处理
		try {
			Map<String,Object> authorityInfo = authorityManageProxy.getAuthorityInfo(roleId,authorityName);
			if(authorityInfo != null){
				if(Integer.parseInt(authorityInfo.get("isAuthority").toString()) <= 0 ){
					String authorityIds = authorityInfo.get("authorityIds").toString() + " " +
											authorityInfo.get("authorityId").toString();
					String controllerMethod = authorityInfo.get("authorityController").toString() + " " +
												authorityInfo.get("authorityMethod").toString();
					Integer total = authorityManageProxy.distributeAuthority(roleId, authorityIds,controllerMethod);
					if(total.intValue() > 0){
						return successResult("分配权限成功");
					}else{
						return errorResult("操作失败，请稍后重试");
					}
				}else{
					return errorResult("角色权限已存在");
				}
			}else{
				return errorResult("此权限不存在");
			}
		}catch (Exception e){
			e.printStackTrace();
			return errorExceptionResult();
		}
	}

	/**
	 * @Author TR
	 * @Create 2020/5/28 14:39
	 * @Title: getAuthorityList
	 * @Params: [operator, params]
	 * @Description: 权限列表
	 */
	public BaseResult getAuthorityList(SystemOperatorEntity operator, JSONObject params) {
		PageForSystem page = null;
		
		// 参数
		try {
			page = getPageEntity(params);

		}catch (Exception e){
			e.printStackTrace();
			return errorParamsResult();
		}

		// 数据处理
		try {
			List<Map<String,Object>> roleList = authorityManageProxy.getAuthorityList(page);
			return successResult("获取角色列表成功", roleList);
		}catch (Exception e){
			e.printStackTrace();
			return errorExceptionResult();
		}
	}
}
