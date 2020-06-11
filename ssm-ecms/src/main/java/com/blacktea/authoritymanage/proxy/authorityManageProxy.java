package com.blacktea.authoritymanage.proxy;

import com.blacktea.common.page.PageForSystem;
import com.sdicons.json.validator.impl.predicates.Str;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author TR
 * @Create 2020年05月28日
 * @Title: authorityManageProxy
 * @Description:
 */
@Repository("authorityManageProxy")
public interface authorityManageProxy {

	/**
	 * @Author TR
	 * @Create 2020/5/28 12:07
	 * @Title: getRoleTypeList
	 * @Params: [page]
	 * @Description:  获取角色列表
	 */
	List<Map<String,Object>> getRoleTypeList(@Param("page")PageForSystem page);

	/**
	 * @Author TR
	 * @Create 2020/6/9 14:45
	 * @Title: getRoleAuthorityList
	 * @Description: 角色权限列表
	 */
	List<Map<String,Object>> getRoleAuthorityList(@Param("roleId") String roleId,
												  @Param("level") String level,
												  @Param("parentId") String parentId);

	/**
	 * @Author TR
	 * @Create 2020/5/28 14:02
	 * @Title: getAuthorityInfo
	 * @Params: [authorityName]
	 * @Description: 查询权限信息
	 */
	Map<String, Object> getAuthorityInfo(@Param("roleId") Integer roleId, @Param("authorityName") String authorityName);

	/**
	 * @Author TR
	 * @Create 2020/5/28 14:02
	 * @Title: distributeAuthority
	 * @Params: [roleId,authorityIds,controllerMethod]
	 * @Description:  分配权限
	 */
	Integer distributeAuthority(@Param("roleId") Integer roleId,
								@Param("authorityIds") String authorityIds,
								@Param("controllerMethod") String controllerMethod);

	/**
	 * @Author TR
	 * @Create 2020/5/28 14:40
	 * @Title: getAuthorityList
	 * @Params: [page]
	 * @Description: 权限列表
	 */
	List<Map<String,Object>> getAuthorityList(@Param("page") PageForSystem page);

	/**
	 * @Author TR
	 * @Create 2020/6/9 15:18
	 * @Title: addRoles
	 * @Description: 添加角色
	 */
	Integer addRoles(@Param("roleName") String roleName,
					 @Param("roleDescription") String roleDescription);

	/**
	 * @Author TR
	 * @Create 2020/6/9 15:23
	 * @Title: deleteRoles
	 * @Description: 删除角色
	 */
	Integer deleteRoles(@Param("roleId") Integer roleId);

	/**
	 * @Author TR
	 * @Create 2020/6/9 15:25
	 * @Title: modifyRoles
	 * @Description: 编辑角色信息
	 */
	Integer modifyRoles(@Param("roleId") Integer roleId,
						@Param("roleDescription") String roleDescription,
						@Param("roleName") String roleName);
}
