package com.ruscorporation.dao;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ruscorporation.model.Contact;

@Repository("contactDAO")
public class ContactDAOImpl implements ContactDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Contact> list() {

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Contact.class);
		//criteria.crea

		//Set projection to Contact table 
		
//		ProjectionList projectionList = Projections.projectionList();
//		projectionList.add(Projections.property("name"));
//		criteria.setProjection(projectionList);
		return criteria.list();
	}

	@Transactional
	public void saveOrUpdate(Contact contact) {
		sessionFactory.getCurrentSession().saveOrUpdate(contact);

	}

	@Transactional
	public Contact get(Integer id) {
		return (Contact) sessionFactory.getCurrentSession().get(Contact.class,
				id);

	}

	@Transactional
	public void delete(Contact contact) {
		sessionFactory.getCurrentSession().delete(contact);
	}

	@Transactional
	public Contact getByName(String name) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Contact.class);
		criteria.add(Restrictions.eq("name", name));
		return (Contact) criteria.uniqueResult();
	}

}
