package com.blacktea.common.base;

import java.io.Serializable;

/**
 * @Author TR
 * @Create 2020年05月26日
 * @Title: BaseResult
 * @Description: 结果集基础类
 */
public class BaseResult implements Serializable {
	private static final long serialVersionUID = -6711131180313904332L;

	private Integer code; // 状态码

	private String msg; // 返回消息

	private Object result; // 结果对象

	private String businessType;// 业务类型

	protected BaseResult() {
		super();
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

}
