package com.aibinong.api.web;

import java.util.HashMap;
import java.util.Map;

public class ResultCode {
	public final static String SUCCESS = "0";
	
	public final static String ERROR_1000 = "1000";
	public final static String ERROR_1001 = "1001";

	public final static String EXCEPTION = "9999";

	private final static Map<String, String> ERROR = new HashMap<String, String>();
	
	public static String getErrorMessage(String key) {
		return ERROR.get(key);
	}

	static {
		ERROR.put(ERROR_1000, "无效签名");
		ERROR.put(ERROR_1001, "token失效, 请重新登录");
		ERROR.put(EXCEPTION, "系统异常");
	}
}
