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
}
