package com.aibinong.api.web.module;

import java.util.HashMap;
import java.util.Map;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.aibinong.api.util.TemplateUtil;

/**
 * 商品类接口
 * @author zhang_zg 
 * @version 1.0    
 * @created 2016年7月4日 下午6:20:18
 */
@At("/api")
@IocBean
public class ProductModule {

	@GET
	@At("/product/period_list")
	@Ok("raw")
	public Object getUser(@Param("category_id") Long categoryId, @Param("tab_id") Integer tabId, @Param("toPage") Integer toPage) {

		Map<String, Object> data = new HashMap<String, Object>();
		return TemplateUtil.format("/product/period_list.ftl", data);
	}
}
