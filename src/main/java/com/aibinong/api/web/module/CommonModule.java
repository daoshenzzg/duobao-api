package com.aibinong.api.web.module;

import java.util.HashMap;
import java.util.Map;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.aibinong.api.util.TemplateUtil;
import com.aibinong.api.web.ResultCode;

@At("/api")
@IocBean
public class CommonModule {

	@At("/reload_template")
	@Ok("json")
	public Object reloadTemplate() {
		TemplateUtil.initTemplate();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", ResultCode.SUCCESS);
		map.put("info", "");
		map.put("data", "");
		return map;
	}

	@At("/exception")
	@Ok("json")
	public Object exception() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", ResultCode.EXCEPTION);
		map.put("info", ResultCode.ERROR.get(ResultCode.EXCEPTION));
		map.put("data", "");
		return map;
	}

	@At("/invalid_sign")
	@Ok("json")
	public Object invalidSign() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", ResultCode.ERROR_1000);
		map.put("info", ResultCode.ERROR.get(ResultCode.ERROR_1000));
		map.put("data", "");
		return map;
	}

	@At("/invalid_token")
	@Ok("json")
	public Object invalidToken() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", ResultCode.ERROR_1001);
		map.put("info", ResultCode.ERROR.get(ResultCode.ERROR_1001));
		map.put("data", "");
		return map;
	}
}
