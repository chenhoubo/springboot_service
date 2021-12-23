/**  
 * ---------------------------------------------------------------------------
 * Copyright (c) 2018, xsjt- All Rights Reserved.
 * Project Name:xsjt-manager  
 * File Name:RetResult.java  
 * Package Name:com.xsjt.core.ret
 * Author   Joe
 * Date:2018年6月5日下午10:38:10
 * ---------------------------------------------------------------------------  
*/  
  
package com.xsjt.core.ret;

import com.xsjt.core.jackson.JsonUtil;
import lombok.ToString;

/**
 * ClassName:RetResult 
 * 返回对象实体
 * Date:     2021年10月21日 上午10:38:10
 * @author     harriss
 * @version
 */
//@ToString
public class RetResult<T> {

	public int code;

	private String msg;

	private T data;

	public RetResult<T> setCode(RetCode retCode) {
		this.code = retCode.code;
		this.msg = retCode.getMsg();
		return this;
	}

	public int getCode() {
		return code;
	}

	public RetResult<T> setCode(int code) {
		this.code = code;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public RetResult<T> setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public T getData() {
		return data;
	}

	public RetResult<T> setData(T data) {
		this.data = data;
		return this;
	}

	@Override
	public String toString() {
		return "RetResult{" +
				"code=" + code +
				", msg='" + msg + '\'' +
				", data=" + JsonUtil.toJson(data) +
				'}';
	}
}
  
