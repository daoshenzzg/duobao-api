package com.aibinong.api.web.module;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.aibinong.api.annotation.Allow;
import com.aibinong.api.util.TemplateUtil;
import com.aibinong.api.web.ResultCode;

@At("/api")
@IocBean
public class CommonModule {

	@Allow
	@At("/reload_template")
	@Ok("json")
	public Object reloadTemplate() {
		TemplateUtil.initTemplate();
		
		// 重定向的内容可以放到模板里
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", ResultCode.SUCCESS);
		map.put("info", "");
		map.put("data", "");
		return map;
	}

	@Allow
	@At("/exception")
	@Ok("json")
	public Object exception(HttpServletRequest request) {
		// 参考:http://nutzam.com/core/mvc/view.html
		Object err = request.getAttribute("obj");
		String message = "";
		if(err instanceof RuntimeException) {
			message = ((RuntimeException) err).getMessage();
		}
		// 重定向的内容可以放到模板里
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", ResultCode.EXCEPTION);
		map.put("info", ResultCode.getErrorMessage(ResultCode.EXCEPTION) + message);
		map.put("data", "");
		return map;
	}

	@Allow
	@At("/invalid_sign")
	@Ok("json")
	public Object invalidSign() {
		// 重定向的内容可以放到模板里
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", ResultCode.ERROR_1000);
		map.put("info", ResultCode.getErrorMessage(ResultCode.ERROR_1000));
		map.put("data", "");
		return map;
	}

	@Allow
	@At("/invalid_token")
	@Ok("json")
	public Object invalidToken() {
		// 重定向的内容可以放到模板里
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", ResultCode.ERROR_1001);
		map.put("info", ResultCode.getErrorMessage(ResultCode.ERROR_1001));
		map.put("data", "");
		return map;
	}
}
