package com.atn.demo.mvc.module.user.service;

import com.atn.commons.service.MyService;
import com.atn.demo.mvc.module.user.entity.Role;
import com.atn.demo.mvc.module.user.entity.Role.ROLE;
import com.atn.demo.mvc.module.user.entity.User;

public interface UserService extends MyService<User, Long> {
	public User findByEmail(String email);

	public String getMessage();

	public User getConnectedUser();

	public String getAdminMessage();


	public Role createRole(User user, ROLE name);
}
