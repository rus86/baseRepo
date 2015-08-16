package com.ruscorporation.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ruscorporation.dao.UserDAO;
import com.ruscorporation.model.User;

@Repository("userDao")
public class UserDaoImpl /*implements UserDAO*/{
	
	@Autowired
	private SessionFactory sessionFactory; 
	
	@PostConstruct
	public void init(){
		System.out.println("========User DAO impl was initialized=========");
	}

	@Transactional
	public User getUser(String userName) {
		Session currentSession = sessionFactory.getCurrentSession();
		Criteria criteria = currentSession.createCriteria(User.class);
		criteria.add(Restrictions.eq("username", userName));
		return (User) criteria.uniqueResult();
	}

	@Transactional
	public void saveOrUpdate(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
		
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<User> list() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
		return criteria.list();
	}

}
