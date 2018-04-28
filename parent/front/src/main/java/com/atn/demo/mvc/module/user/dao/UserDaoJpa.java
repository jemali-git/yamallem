package com.atn.demo.mvc.module.user.dao;


import org.springframework.stereotype.Component;

import com.atn.commons.persistence.jpa.BaseDaoJpa;
import com.atn.demo.mvc.module.user.entity.User;
@Component
public class UserDaoJpa extends BaseDaoJpa<User,Long>implements UserDao {
	public UserDaoJpa() {
		super(User.class);
		
	}
}
