package com.aibinong.api.test;

import org.nutz.dao.QueryResult;
import org.nutz.mvc.Mvcs;

import com.aibinong.api.dao.BasicDao;
import com.aibinong.api.util.NutzUtil;
import com.aibinong.api.web.MainModule;

/**
 * 测试带有分页的自定义查询
 * @author zhang_zg 
 * @version 1.0    
 * @created 2016年7月4日 下午7:18:41
 */
public class DaoTest {
	public static void main(String[] args) {
		NutzUtil.init(MainModule.class);
		BasicDao basicDao = Mvcs.getIoc().get(BasicDao.class, "basicDao");

		StringBuilder sb = new StringBuilder();
		sb.append("SELECT id, client_id, app_code, channel, os, nick, portrait\n");
		sb.append("FROM users\n");
		sb.append("WHERE 1 = 1\n");
		QueryResult queryResult = basicDao.querySqlResult(sb.toString(), 1, 1);
		System.out.println(queryResult.getList());
		System.out.println(queryResult.getPager().getPageCount());
	}
}
