package com.aibinong.api.service.user;

import java.util.List;

import com.aibinong.api.pojo.UserDO;

public interface UserService {
	
	public List<UserDO> getUsers();
	
	public UserDO getUser(Long id, String clientId);

	public void addUsers(List<UserDO> users);

}
