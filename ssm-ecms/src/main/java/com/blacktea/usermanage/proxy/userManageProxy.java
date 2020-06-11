package com.blacktea.usermanage.proxy;

import com.blacktea.common.page.PageForSystem;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.method.P;
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

	/**
	 * @Author TR
	 * @Create 2020/6/9 8:46
	 * @Title: addUser
	 * @Description: 添加用户
	 */
	Integer addUser(@Param("userName") String userName,
					@Param("userPwd") String userPwd,
					@Param("userTel") String userTel,
					@Param("userEmail") String userEmail);

	/**
	 * @Author TR
	 * @Create 2020/6/9 8:59
	 * @Title: deleteUser
	 * @Description:
	 */
	Integer deleteUser(@Param("userId") Integer userId);

	/**
	 * @Author TR
	 * @Create 2020/6/9 9:05
	 * @Title: modifyUser
	 * @Description: 编辑用户信息
	 */
	Integer modifyUser(@Param("userTel") String userTel,
					   @Param("userEmail") String userEmail,
					   @Param("userId") Integer userId);

	/**
	 * @Author TR
	 * @Create 2020/6/9 10:08
	 * @Title: judgeExist
	 * @Description: 判断用户是否已经存在
	 */
	Integer judgeUserExist(@Param("operatorName") String operatorName);
}
