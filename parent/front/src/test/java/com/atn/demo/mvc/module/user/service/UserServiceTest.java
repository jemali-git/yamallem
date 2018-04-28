package com.atn.demo.mvc.module.user.service;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.atn.commons.persistence.BaseDao;
import com.atn.demo.config.TestsConfig;
import com.atn.demo.mvc.module.user.entity.Role;
import com.atn.demo.mvc.module.user.entity.Role.ROLE;
import com.atn.demo.mvc.module.user.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestsConfig.class })
@WebAppConfiguration
// @WithMockUser(username="admin",roles={"USER","ADMIN"})
public class UserServiceTest {
	@Autowired
	private UserService userService;

	@Test
	public void findOneTest() {
		assertNotNull(userService);
		assertNotNull(userService.getGmailSender());
		assertNotNull(userService.getLoggerService());
		User user = userService.findById(new Long(1));
		assertNotNull(user);
	}

	@Test
	public void findAllTest() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<User> list = userService.findListByCriteria(map, 10, 0,
				BaseDao.DELETION_STATUS.BOTH);
		assertNotNull(list);
		Assert.assertNotEquals(list.size(), 0);
	}

	@Test
	@Transactional
	public void hasRoleTest() {
		User user = userService.findById(new Long(1));
		Assert.assertNotEquals(user.getRoles().size(), 0);
		Role role = user.getRoles().iterator().next();
		Assert.assertEquals(role.getRole(), ROLE.ADMINISTRATOR);

	}

	@Test
	@WithUserDetails("adm@email.com")
	public void getConnectedUser() {
		String message = userService.getMessage();
		System.err.println(message);
		assertNotNull(userService.getConnectedUser());

	}
@Ignore("il fau corriger ce point walid")
	@Test(expected = AuthenticationCredentialsNotFoundException.class)
	// ca ne marchera qui si on enleve l'annotation sur la classe qui s'applique
	// a toute les methode
	public void getMessageUnauthenticated() {
		userService.getMessage();
	}

	// @Test//decommenter la methode en decommentant @WithMockUser au niveau de
	// la classe
	// public void getMessageWithMockUserAnnotationOnClass() {
	// String message = userService.getMessage();
	// System.err.println(message);
	// }
	@Test
	@WithMockUser
	public void getMessageWithMockUser() {
		String message = userService.getMessage();
		System.err.println(message);
	}

	@Test
	@WithMockUser("customUsername")
	public void getMessageWithMockUserCustomUsername() {
		String message = userService.getMessage();
		System.err.println(message);
	}

	@Test
	@WithMockUser(username = "admin", roles = { "USER", "ADMIN" })
	public void getMessageWithMockUserCustomUser() {
		String message = userService.getMessage();
		System.err.println(message);
	}
	@Test
	@WithMockUser(username = "normaluser", roles = { "ADMIN" })
	public void getMessageAdmin(){
		String message = userService.getAdminMessage();
		System.err.println(message);
	}
}
