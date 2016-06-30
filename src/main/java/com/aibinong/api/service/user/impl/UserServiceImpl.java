package com.aibinong.api.service.user.impl;

import java.util.List;

import org.nutz.aop.interceptor.ioc.TransAop;
import org.nutz.ioc.aop.Aop;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.aibinong.api.dao.BasicDao;
import com.aibinong.api.pojo.UserDO;
import com.aibinong.api.service.user.UserService;

@IocBean(name = "userService")
public class UserServiceImpl implements UserService {

	@Inject
	private BasicDao basicDao;
	
	public List<UserDO> getUsers() {
		return basicDao.search(UserDO.class, "id");
	}
	
	public UserDO getUser(Long id, String clientId) {
		return basicDao.find(id, UserDO.class);
	}

	@Aop(TransAop.READ_COMMITTED)
	public void addUsers(List<UserDO> users) {
		for (UserDO user : users) {
			basicDao.save(user);
		}
	}
	
}
