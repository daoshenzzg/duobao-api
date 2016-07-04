package com.aibinong.api.test;

import org.nutz.mvc.Mvcs;

import com.aibinong.api.dao.RedisDao;
import com.aibinong.api.util.NutzUtil;
import com.aibinong.api.web.MainModule;

/**
 * 测试RedisDao
 * @author zhang_zg 
 * @version 1.0    
 * @created 2016年7月4日 下午7:21:58
 */
public class RedisDaoTest {
	public static void main(String[] args) {
		NutzUtil.init(MainModule.class);
		RedisDao redisDao = Mvcs.getIoc().get(RedisDao.class, "redisDao");
		redisDao.setex("aaa", "{'title': 'hello aibinong!'}", 30);
		String value = redisDao.get("aaa");
		System.out.println(value);
	}
}
