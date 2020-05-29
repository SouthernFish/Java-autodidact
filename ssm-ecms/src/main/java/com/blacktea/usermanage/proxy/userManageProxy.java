package com.blacktea.usermanage.proxy;

import com.blacktea.common.page.PageForSystem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author TR
 * @Create 2020年05月28日
 * @Title: userManageProxy
 * @Description:
 */
@Repository("userManageProxy")
public interface userManageProxy {

	/**
	 * @Author TR
	 * @Create 2020/5/28 8:44
	 * @Title: getUserList
	 * @Params: [page]
	 * @Description: 查询用户列表
	 */
	List<Map<String,Object>> getUserList(@Param("page") PageForSystem page,
										 @Param("searchText") String searchText);

	/**
	 * @Author TR
	 * @Create 2020/5/28 10:33
	 * @Title: updateUserStatus
	 * @Params: [userStatus]
	 * @Description: 修改用户状态
	 */
	Integer updateUserStatus(@Param("userStatus") Integer userStatus,
							 @Param("operatorId") Integer operatorId);
}
