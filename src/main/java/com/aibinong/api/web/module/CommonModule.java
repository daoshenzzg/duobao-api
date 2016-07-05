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
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("code", ResultCode.EXCEPTION);
		data.put("info", ResultCode.getErrorMessage(ResultCode.EXCEPTION) + message);
		return TemplateUtil.format("/common/common_error.ftl", data);
	}

	@Allow
	@At("/invalid_sign")
	@Ok("json")
	public Object invalidSign() {
		// 重定向的内容可以放到模板里
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("code", ResultCode.ERROR_1000);
		data.put("info", ResultCode.getErrorMessage(ResultCode.ERROR_1000));
		return TemplateUtil.format("/common/common_error.ftl", data);
	}

	@Allow
	@At("/invalid_token")
	@Ok("json")
	public Object invalidToken() {
		// 重定向的内容可以放到模板里
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("code", ResultCode.ERROR_1001);
		data.put("info", ResultCode.getErrorMessage(ResultCode.ERROR_1001));
		return TemplateUtil.format("/common/common_error.ftl", data);
	}

	@Allow
	@At("/reload_template")
	@Ok("json")
	public Object reloadTemplate() {
		TemplateUtil.initTemplate();

		// 重定向的内容可以放到模板里
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("code", ResultCode.SUCCESS);
		data.put("info", "");
		data.put("data", "");
		return data;
	}
}
