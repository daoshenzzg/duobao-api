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

	/**
	 * 获取期次列表
	 * @param categoryId 商品类目ID
	 * @param tabId 分类ID
	 * @param toPage  页码
	 * @return
	 */
	@GET
	@At("/product/period_list")
	@Ok("raw")
	public Object getPeriodList(@Param("category_id") Long categoryId, @Param("tab_id") Integer tabId, @Param("toPage") Integer toPage) {
		Map<String, Object> data = new HashMap<String, Object>();

		if (tabId == null) {
			data.put("code", ResultCode.ERROR_1002);
			data.put("info", String.format("%s[tab_id=%s]", ResultCode.getErrorMessage(ResultCode.ERROR_1002), tabId));
			return TemplateUtil.format("/common/common_error.ftl", data);
		}

		if (toPage == null) {
			toPage = 1;
		}

		QueryResult result = productService.getPeriodList(categoryId, tabId, toPage);
		data.put("code", ResultCode.SUCCESS);
		data.put("result", result);
		return TemplateUtil.format("/product/period_list.ftl", data);
	}

	/**
	 * 最新揭晓
	 * @param limit 总条数，首页传3
	 * @param toPage limit不传时有效，默认为1
	 * @return
	 */
	@GET
	@At("/product/finish_period_list")
	@Ok("raw")
	public Object getFinishPeriodList(@Param("limit") Integer limit, @Param("toPage") Integer toPage) {
		Map<String, Object> data = new HashMap<String, Object>();

		data.put("code", ResultCode.SUCCESS);
		return TemplateUtil.format("/product/finish_period_list.ftl", data);
	}

	/**
	 * 商品详情
	 * @param periodId 期次ID
	 * @param clientId 客户端ID
	 * @param productId 产品ID 如果传productId，则取该产品最新一期
	 * @return
	 */
	@GET
	@At("/product/period_info")
	@Ok("raw")
	public Object getPeriodInfo(@Param("periodId") Long periodId, @Param("clientId") String clientId, @Param("productId") Long productId) {
		Map<String, Object> data = new HashMap<String, Object>();

		data.put("code", ResultCode.SUCCESS);
		return TemplateUtil.format("/product/period_info.ftl", data);
	}

	/**
	 * 往期揭晓
	 * @param clientId 客户端ID
	 * @param productId 产品ID
	 * @return
	 */
	@GET
	@At("/product/past_announce_list")
	@Ok("raw")
	public Object getPastAnnounceList(@Param("clientId") String clientId, @Param("productId") Long productId) {
		Map<String, Object> data = new HashMap<String, Object>();

		data.put("code", ResultCode.SUCCESS);
		return TemplateUtil.format("/product/past_announce_list.ftl", data);
	}

	/**
	 * 参与记录
	 * @param periodId 期次ID
	 * @param clientId 客户端ID
	 * @param toPage 页码
	 * @return
	 */
	@GET
	@At("/product/partake_record_list")
	@Ok("raw")
	public Object getPartakeRecordList(@Param("periodId") Long periodId, @Param("clientId") String clientId, @Param("toPage") Integer toPage) {
		Map<String, Object> data = new HashMap<String, Object>();

		data.put("code", ResultCode.SUCCESS);
		return TemplateUtil.format("/product/partake_record_list.ftl", data);
	}

	/**
	 * 晒单历史
	 * @param productId 产品ID
	 * @param type 类型1-所有; 2-我的晒单;3-产品晒单
	 * @param toPage 页码
	 * @return
	 */
	@GET
	@At("/product/show_list")
	@Ok("raw")
	public Object getShowList(@Param("productId") Long productId, @Param("type") Integer type, @Param("toPage") Integer toPage) {
		Map<String, Object> data = new HashMap<String, Object>();

		data.put("code", ResultCode.SUCCESS);
		return TemplateUtil.format("/product/show_list.ftl", data);
	}
}
