package com.atn.demo.mvc.module.user.service;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.atn.commons.persistence.BaseDao;
import com.atn.commons.service.MyAbstractService;
import com.atn.demo.mvc.module.user.dao.UserDao;
import com.atn.demo.mvc.module.user.entity.Role;
import com.atn.demo.mvc.module.user.entity.Role.ROLE;
import com.atn.demo.mvc.module.user.entity.User;

@Component("UserService")
public class UserServiceImpl extends MyAbstractService<User, Long> implements UserService, UserDetailsService {

	
	public UserServiceImpl(UserDao dataAccessObject) {
		super(dataAccessObject);
	}

	
	@Override
	public UserDao getDataAccessObject() {
		return (UserDao) this.dataAccessObject;
	}

	
	@Override
	public User findByEmail(String email) {
		Map<String, Object> map = new HashMap<>();
		map.put("=userEmail", email);
		List<User> result = findListByCriteria(map, 1, 0, BaseDao.DELETION_STATUS.ACTIVE);
		if (result.size() > 0) {
			return result.get(0);
		} else {
			return null;
		}
	}

	@PreAuthorize("authenticated")
	public String getMessage() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return "Hello " + authentication;
	}

	@PreAuthorize("authenticated")
	public User getConnectedUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userConnected = (UserDetails) auth.getPrincipal();
			User user = findByEmail(userConnected.getUsername());
			return user;
		}
		return null;
	}

	@PreAuthorize("hasRole('ADMIN')")
	public String getAdminMessage() {
		return " I am Admin";
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("L'adresse email '" + username + "' est introuvale ou désactivé !!!");
		}
		String password = user.getUserPassword();
		boolean enabled = user.getDeletingDate() == null;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		Collection<? extends GrantedAuthority> authorities = user.getRoles();
		org.springframework.security.core.userdetails.User connectedUser = new org.springframework.security.core.userdetails.User(
				username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		return connectedUser;
	}


	@Override
	public Role createRole(User user, ROLE name) {
		Role role = new Role();
		role.setUser(user);
		role.setRole(name);
		role.setCreationDate(new Date());
		role.setUpdatingDate(new Date());
		return role;
	}
}
