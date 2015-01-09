package com.ruscorporation.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ruscorporation.dao.UserDAO;
import com.ruscorporation.model.SystemUser;

@Repository("userDAO")
public class UserDAOImpl extends BaseDAOImpl<SystemUser> implements UserDAO{

	public UserDAOImpl() {
		super(SystemUser.class);
	}

	@Override
	public SystemUser getUserByUserName(String userName) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				entityClass);
		criteria.add(Restrictions.eq("userName", userName));
		return (SystemUser) criteria.uniqueResult();
	}

}
