package com.ruscorporation.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.envers.AuditReader;

public interface BaseDAO<T extends Serializable> {

	void saveOrUpdate(T entity);

	List<T> list();

	void delete(T entity);

	T get(Serializable id);
	
	void merge(T entity);
	
	AuditReader getAuditReader();

}
