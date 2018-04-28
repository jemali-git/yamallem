package com.atn.demo.mvc.module.user.dao;
import java.util.List;

import com.atn.demo.mvc.module.user.entity.User;

public interface PersonDao {

	public void addPerson(User user);
	public void updatePerson(User user);
	public List<User> listPersons();
	public User getPersonById(Long id);
	public void removePerson(Long id);
}