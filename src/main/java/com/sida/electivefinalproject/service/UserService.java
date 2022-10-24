package com.sida.electivefinalproject.service;

import com.sida.electivefinalproject.dao.Dao;
import com.sida.electivefinalproject.entity.Course;
import com.sida.electivefinalproject.entity.User;

import java.util.List;

public interface UserService {
   List<User> findAllTeachers();
   List<User> findAllStudents();

    void addUser(User user);
    void deleteUser(int id);

    void updateUser(User user);

    User findById(int id);

    void unblockUser(int id);

    User findByEmail(String email);
}
