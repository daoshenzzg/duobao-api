package com.aibinong.api.web.module;

import java.util.HashMap;
import java.util.Map;

import org.nutz.dao.QueryResult;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.aibinong.api.service.product.ProductService;
import com.aibinong.api.util.TemplateUtil;
import com.aibinong.api.web.ResultCode;

/**
 * 商品类接口
 * @author zhang_zg 
 * @version 1.0    
 * @created 2016年7月4日 下午6:20:18
 */
@At("/api")
@IocBean
public class ProductModule {

	@Inject
	private ProductService productService;

	@GET
	@At("/product/period_list")
	@Ok("raw")
	public Object getUser(@Param("category_id") Long categoryId, @Param("tab_id") Integer tabId, @Param("toPage") Integer toPage) {
		Map<String, Object> data = new HashMap<String, Object>();

		if (tabId == null) {
			data.put("code", ResultCode.ERROR_1002);
			data.put("info", String.format("%s[tab_id=%s]", ResultCode.getErrorMessage(ResultCode.ERROR_1002), tabId));
			return TemplateUtil.format("/common/common_error.ftl", data);
		}

		if (toPage == null) {
			data.put("code", ResultCode.ERROR_1002);
			data.put("info", String.format("%s[toPage=%s]", ResultCode.getErrorMessage(ResultCode.ERROR_1002), toPage));
			return TemplateUtil.format("/common/common_error.ftl", data);
		}

		QueryResult result = productService.getPeriodList(categoryId, tabId, toPage);
		data.put("code", ResultCode.SUCCESS);
		data.put("result", result);
		return TemplateUtil.format("/product/period_list.ftl", data);
	}
}
