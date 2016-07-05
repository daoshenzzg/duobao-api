package com.aibinong.api.service.product;

import org.nutz.dao.QueryResult;

public interface ProductService {
	
	/**
	 * 获取期次列表
	 * @param categoryId
	 * @param tabId
	 * @param toPage
	 * @return
	 */
	public QueryResult getPeriodList(Long categoryId, int tabId, int toPage);
}
