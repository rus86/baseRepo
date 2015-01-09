package com.ruscorporation.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ruscorporation.dao.BaseDAO;
@Transactional
public abstract class BaseDAOImpl<T extends Serializable> implements BaseDAO<T> {

	@Autowired
	protected SessionFactory sessionFactory;

	protected final Class entityClass;

	public BaseDAOImpl(Class entityClass) {
		this.entityClass = entityClass;
	}

	@Transactional
	public void saveOrUpdate(T entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
		sessionFactory.getCurrentSession().flush();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<T> list() {
		return sessionFactory.getCurrentSession().createCriteria(entityClass)
				.list();
	}

	@Transactional
	public void delete(T entity) {
		sessionFactory.getCurrentSession().delete(entity);
		sessionFactory.getCurrentSession().flush();
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public T get(Serializable id) {
		return (T) sessionFactory.getCurrentSession().get(entityClass, id);
	}

	@Transactional	
	public void merge(T entity) {
		sessionFactory.getCurrentSession().merge(entity);
	}

	public AuditReader getAuditReader() {
		AuditReader auditReader = AuditReaderFactory.get(sessionFactory.getCurrentSession());
		return auditReader;
	}

}
