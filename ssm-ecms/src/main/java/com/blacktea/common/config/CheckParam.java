package com.blacktea.common.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @Author TR
 * @Create 2020年05月27日
 * @Title: CheckParam
 * @Description:  验证登录信息用
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.PARAMETER })
public @interface CheckParam {
	public String value() default "token";
}
