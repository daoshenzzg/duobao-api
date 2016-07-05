package com.aibinong.api.service.product.impl;

import org.nutz.dao.QueryResult;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.aibinong.api.dao.BasicDao;
import com.aibinong.api.service.product.ProductService;
import com.aibinong.api.web.Constants;

@IocBean(name = "productService")
public class ProductServiceImpl implements ProductService {

	@Inject
	private BasicDao basicDao;

	@Override
	public QueryResult getPeriodList(Long categoryId, int tabId, int toPage) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT t1.category_id, t2.id, t2.title, t2.product_id, t2.total_count\n");
		sb.append("	, t2.cur_count, t2.limit_count, t2.duobao_type, t2.start_time, t2.status\n");
		sb.append("	, t2.award_time, t2.list_img, t2.loop_img, t2.is_new, t2.is_hot\n");
		sb.append("FROM product_category t1, period t2\n");
		sb.append("WHERE t1.product_id = t2.product_id\n");
		if (tabId == 0) { // 人气
			sb.append("AND t2.status = 0\n");
			sb.append("AND t2.is_hot = 1\n");
		} else if (tabId == 1) { // 最新
			sb.append("AND t2.status = 0\n");
			sb.append("AND t2.is_new = 1\n");
		} else if (tabId == 2) { // 即将揭晓
			sb.append("AND t2.status = 1\n");
		}
		if (categoryId != null) {
			sb.append("AND t1.category_id = " + categoryId + "\n");
		}
		sb.append("ORDER BY t2.weight DESC\n");

		QueryResult result = basicDao.querySqlResult(sb.toString(), toPage, Constants.DEFAULT_PAGE_SIZE);
		return result;
	}

}
