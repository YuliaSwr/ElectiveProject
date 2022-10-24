package com.sida.electivefinalproject.dao;

import com.sida.electivefinalproject.entity.User;
import com.sida.electivefinalproject.entity.UserType;
import com.sida.electivefinalproject.exception.DatabaseException;

import java.util.List;

public interface UserDao extends Dao<User> {
   List<User> findAllByType(UserType userType) throws DatabaseException;
   void unblockById(int id) throws DatabaseException;
    User findByEmail(String email) throws DatabaseException;
}
