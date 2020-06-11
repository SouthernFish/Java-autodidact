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
			// 各层级权限列表
			for(Map<String,Object> role : roleList){
				List<Map<String,Object>> firstLevel =  authorityManageProxy.getRoleAuthorityList(role.get("roleId").toString(),"0",null);
				for(Map<String,Object> first : firstLevel){
					List<Map<String,Object>> secondLevel =  authorityManageProxy.getRoleAuthorityList(role.get("roleId").toString(),"1",first.get("authorityId").toString());
					for(Map<String,Object> second : secondLevel){
						List<Map<String,Object>> thirdLevel =  authorityManageProxy.getRoleAuthorityList(role.get("roleId").toString(),"2",second.get("authorityId").toString());
						second.put("children",thirdLevel);
					}
					first.put("children",secondLevel);
				}
				role.put("children",firstLevel);
			}
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
		String authorityId;
		Integer roleId;

		// 参数
		try {
			if(params.get("authorityId") == null || params.get("roleId") == null){
				return errorParamsResult();
			}
			authorityId = params.getString("authorityId");
			roleId = Integer.parseInt(params.getString("roleId"));
		}catch (Exception e){
			e.printStackTrace();
			return errorParamsResult();
		}

		// 数据处理
		try {
			Map<String,Object> authorityInfo = authorityManageProxy.getAuthorityInfo(roleId,authorityId);
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
			return successResult("获取权限列表成功", roleList);
		}catch (Exception e){
			e.printStackTrace();
			return errorExceptionResult();
		}
	}

	/**
	 * @Author TR
	 * @Create 2020/6/9 13:59
	 * @Title: addRoles
	 * @Description: 添加角色
	 */
	public BaseResult addRole(SystemOperatorEntity operator, JSONObject params) {

		String roleName;
		String roleDescription;
		// 参数
		try {
			roleDescription = params.getString("roleDescription");
			roleName = params.getString("roleName");
		}catch (Exception e){
			e.printStackTrace();
			return errorParamsResult();
		}

		// 数据处理
		try {
			Integer total = authorityManageProxy.addRoles(roleName, roleDescription);
			if (total.intValue() > 0) {
				return successResult("角色添加成功");
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
	 * @Create 2020/6/9 14:01
	 * @Title: deleteRoles
	 * @Description: 删除角色
	 */
	public BaseResult deleteRole(SystemOperatorEntity operator, JSONObject params) {
		Integer roleId;
		// 参数
		try {
			roleId = Integer.parseInt(params.getString("roleId"));
		}catch (Exception e){
			e.printStackTrace();
			return errorParamsResult();
		}

		// 数据处理
		try {
			Integer total = authorityManageProxy.deleteRoles(roleId);
			if (total.intValue() > 0) {
				return successResult("角色删除成功");
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
	 * @Create 2020/6/9 14:01
	 * @Title: modifyRoles
	 * @Description: 编辑角色信息
	 */
	public BaseResult modifyRole(SystemOperatorEntity operator, JSONObject params) {
		Integer roleId;
		String roleName;
		String roleDescription;
		// 参数
		try {
			roleId = Integer.parseInt(params.getString("roleId"));
			roleDescription = params.getString("roleDescription");
			roleName = params.getString("roleName");
		}catch (Exception e){
			e.printStackTrace();
			return errorParamsResult();
		}

		// 数据处理
		try {
			Integer total = authorityManageProxy.modifyRoles(roleId,roleDescription,roleName);
			if (total.intValue() > 0) {
				return successResult("角色信息修改成功");
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
	 * @Create 2020/6/9 14:16
	 * @Title: getRoleDetail
	 * @Description: 获取角色详细信息
	 */
	public BaseResult getRoleDetail(SystemOperatorEntity operator, JSONObject params) {

		Integer roleId;

		// 参数
		try {
			roleId = Integer.parseInt(params.getString("roleId"));
		}catch (Exception e){
			e.printStackTrace();
			return errorParamsResult();
		}

		// 数据处理
		try {
			Integer total = 0; //authorityManageProxy.getRoleDetail(roleId);
			if (total.intValue() > 0) {
				return successResult("角色信息获取成功");
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
	 * @Create 2020/6/9 14:18
	 * @Title: deleteRoleAuthority
	 * @Description: 删除角色权限
	 */
	public BaseResult deleteRoleAuthority(SystemOperatorEntity operator, JSONObject params) {
		Integer authorityId;
		Integer roleId;
		// 参数
		try {
			roleId = Integer.parseInt(params.getString("roleId"));
			authorityId = Integer.parseInt(params.getString("authorityId"));
		}catch (Exception e){
			e.printStackTrace();
			return errorParamsResult();
		}

		// 数据处理
		try {
			Integer total = 0; //authorityManageProxy.deleteRoleAuthority(roleId,authorityId);
			if (total.intValue() > 0) {
				return successResult("角色权限删除成功");
			} else {
				return errorResult("操作失败，请稍后重试");
			}
		}catch (Exception e){
			e.printStackTrace();
			return errorExceptionResult();
		}
	}
}
