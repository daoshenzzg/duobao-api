package com.aibinong.api.service.user.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.nutz.aop.interceptor.ioc.TransAop;
import org.nutz.ioc.aop.Aop;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;

import com.aibinong.api.dao.BasicDao;
import com.aibinong.api.dao.RedisDao;
import com.aibinong.api.pojo.UserDO;
import com.aibinong.api.service.user.UserService;
import com.alibaba.fastjson.JSON;

@IocBean(name = "userService")
public class UserServiceImpl implements UserService {
	private final static Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

	@Inject
	private BasicDao basicDao;

	@Inject
	private RedisDao redisDao;

	public List<UserDO> getUsers() {
		return basicDao.search(UserDO.class, "id");
	}

	public UserDO getUser(Long id, String clientId) {
		String key = String.format("user:get_user:id:%d:client_id:%s", id, clientId);
		UserDO userDO = null;
		Jedis jedis = redisDao.getJedis();
		try {
			String json = jedis.get(key);
			if (StringUtils.isNotBlank(json)) {
				userDO = JSON.parseObject(json, UserDO.class);
				LOG.debug("_____get json from redis:" + json);
			} else {
				userDO = basicDao.find(id, UserDO.class);
				jedis.set(key, JSON.toJSONString(userDO));
				jedis.expire(key, 30);
				LOG.debug("_____set json to redis:" + JSON.toJSONString(userDO));
			}
		} finally {
			redisDao.closeJedis(jedis);
		}
		return userDO;
	}

	@Aop(TransAop.READ_COMMITTED)
	public void addUsers(List<UserDO> users) {
		for (UserDO user : users) {
			basicDao.save(user);
		}
	}

}
