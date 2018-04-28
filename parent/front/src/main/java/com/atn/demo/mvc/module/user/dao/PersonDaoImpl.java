package com.atn.demo.mvc.module.user.dao;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.atn.demo.mvc.module.user.entity.User;



@Repository
public class PersonDaoImpl implements PersonDao {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonDaoImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void addPerson(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(user);
		logger.info("User saved successfully, User Details="+user);
	}

	@Override
	public void updatePerson(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(user);
		logger.info("User updated successfully, User Details="+user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> listPersons() {
		Session session = this.sessionFactory.getCurrentSession();
		List<User> personsList = session.createQuery("from User").list();
		for(User user : personsList){
			logger.info("User List::"+user);
		}
		return personsList;
	}

	@Override
	public User getPersonById(Long id) {
		Session session = this.sessionFactory.getCurrentSession();		
		User user = (User) session.load(User.class, new Long(id));
		logger.info("User loaded successfully, User details="+user);
		return user;
	}

	@Override
	public void removePerson(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		User user = (User) session.load(User.class, new Long(id));
		if(null != user){
			session.delete(user);
		}
		logger.info("User deleted successfully, person details="+user);
	}
}