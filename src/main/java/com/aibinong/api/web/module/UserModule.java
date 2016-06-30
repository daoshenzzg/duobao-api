package com.aibinong.api.web.module;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;

import com.aibinong.api.pojo.UserDO;
import com.aibinong.api.service.user.UserService;
import com.aibinong.api.util.TemplateUtil;

@At("/api")
@IocBean
public class UserModule {
	
	@Inject
	private UserService userService;
	
	@GET
	@At("/user/get_users")
	@Ok("raw")
	public Object getUsers() {
		List<UserDO> users = userService.getUsers();
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("users", users);
		
		return TemplateUtil.format("/user/get_users.ftl", data);
	}
	
	@GET
	@At("/user/get_user")
	@Ok("raw")
	public Object getUser(@Param("id") Long id, @Param("client_id") String clientId) {
		UserDO user = userService.getUser(id, clientId);

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("user", user);

		return TemplateUtil.format("/user/get_user.ftl", data);
	}

	@POST
	@At("/user/add_users")
	@Ok("raw")
	public Object addUsers(@Param("id") String id) {
		
		List<UserDO> users = new ArrayList<UserDO>();
		for(int i = 0; i < 2; i ++) {
			UserDO userDO = new UserDO();
			userDO.setAppCode("X00" + i);
			userDO.setChannel("IOS");
			userDO.setNick("小明" + i);
			userDO.setClientId("c1:fs:01:4b:11");
			userDO.setMobile("15888888886");
			userDO.setPortrait("https://www.baidu.com");
			userDO.setGmtCreate(new Date());
			users.add(userDO);
		}
		
		userService.addUsers(users);
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("add_users", "success");
		
		return TemplateUtil.format("/user/add_user.ftl", data);
	}
}
