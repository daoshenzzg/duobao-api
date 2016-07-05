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
	@Ok("raw")
	public Object exception(HttpServletRequest request) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("code", ResultCode.EXCEPTION);
		data.put("info", ResultCode.getErrorMessage(ResultCode.EXCEPTION));
		return TemplateUtil.format("/common/common_error.ftl", data);
	}

	@Allow
	@At("/invalid_sign")
	@Ok("raw")
	public Object invalidSign() {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("code", ResultCode.ERROR_1000);
		data.put("info", ResultCode.getErrorMessage(ResultCode.ERROR_1000));
		return TemplateUtil.format("/common/common_error.ftl", data);
	}

	@Allow
	@At("/invalid_token")
	@Ok("raw")
	public Object invalidToken() {
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

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("code", ResultCode.SUCCESS);
		data.put("info", "templates reload success!");
		data.put("data", "");
		return data;
	}
}
