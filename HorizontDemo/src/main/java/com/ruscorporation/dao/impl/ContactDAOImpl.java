package com.ruscorporation.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ruscorporation.dao.ContactDAO;
import com.ruscorporation.model.Contact;

@Repository("contactDAO")
public class ContactDAOImpl extends BaseDAOImpl<Contact> implements ContactDAO {

	public ContactDAOImpl() {
		super(Contact.class);
	}

	public Contact getContactByDriverLicense(String driverLicense) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				entityClass);
		criteria.add(Restrictions.eq("driverLicense", driverLicense));
		return (Contact) criteria.uniqueResult();
	}

	@Override
	public List<Number> getRevisions(Integer userId) {
		return getAuditReader().getRevisions(Contact.class, userId);
	}

}
