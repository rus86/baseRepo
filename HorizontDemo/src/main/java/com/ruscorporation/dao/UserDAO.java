package com.ruscorporation.dao;

import com.ruscorporation.model.SystemUser;

public interface UserDAO extends BaseDAO<SystemUser>{

	SystemUser getUserByUserName(String userName);
}
