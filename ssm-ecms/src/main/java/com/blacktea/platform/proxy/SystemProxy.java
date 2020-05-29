package com.blacktea.platform.proxy;

import com.blacktea.entity.SystemOperatorEntity;
import com.sdicons.json.validator.impl.predicates.Str;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @Author TR
 * @Create 2020年05月26日
 * @Title: 代理层
 * @Description:
 */
@Repository("systemProxy")
public interface SystemProxy {

	/**
	 * @Author TR
	 * @Create 2020/5/26 13:59
	 * @Title: getOperatorInfoByAccount
	 * @Params: operatorAccount
	 * @Description: 查询操作员信息
	 */
	SystemOperatorEntity getOperatorInfoByAccount(@Param("operatorAccount") String operatorAccount);

	/**
	 * @Author TR
	 * @Create 2020/5/28 10:49
	 * @Title: getOperatorRoleType
	 * @Params: [roleId]
	 * @Description: 查询用户角色类型
	 */
	String getOperatorRoleType(@Param("roleId") Integer roleId);

	/**
	 * @Author TR
	 * @Create 2020/5/28 10:49
	 * @Title: updateUserPassword
	 * @Params: [operatorId]
	 * @Description: 修改用户密码
	 */
	Integer updateUserPassword(@Param("operatorId") Integer operatorId,
							   @Param("operatorPwd") String operatorPwd);
}
