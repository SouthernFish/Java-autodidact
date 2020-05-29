package com.blacktea.common.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @Author TR
 * @Create 2020年05月27日
 * @Title: CheckToken
 * @Description: 验证token注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface CheckToken {
	public String value() default "token";
}
