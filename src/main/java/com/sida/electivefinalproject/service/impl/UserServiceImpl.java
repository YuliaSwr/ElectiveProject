package com.sida.electivefinalproject.service.impl;

import com.sida.electivefinalproject.dao.UserDao;
import com.sida.electivefinalproject.dao.factory.DaoFactoryDeliver;
import com.sida.electivefinalproject.dao.factory.MySQLFactory;
import com.sida.electivefinalproject.entity.User;
import com.sida.electivefinalproject.entity.UserType;
import com.sida.electivefinalproject.service.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    @Override
    public List<User> findAllTeachers() {
        try (MySQLFactory factory = DaoFactoryDeliver.getInstance().createMySQLFactory()) {
            UserDao userDAO = factory.getUserDAO();
            return userDAO.findAllByType(UserType.TEACHER);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<User> findAllStudents() {
        try (MySQLFactory factory = DaoFactoryDeliver.getInstance().createMySQLFactory()) {
            UserDao userDAO = factory.getUserDAO();
            return userDAO.findAllByType(UserType.STUDENT);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void addUser(User user) {
        try (MySQLFactory factory = DaoFactoryDeliver.getInstance().createMySQLFactory()) {
            UserDao userDAO = factory.getUserDAO();
            userDAO.insert(user);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteUser(int id) {
        try (MySQLFactory factory = DaoFactoryDeliver.getInstance().createMySQLFactory()) {
            UserDao userDAO = factory.getUserDAO();
            userDAO.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateUser(User user) {
        try (MySQLFactory factory = DaoFactoryDeliver.getInstance().createMySQLFactory()) {
            UserDao userDAO = factory.getUserDAO();
            userDAO.update(user);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findById(int id) {
        try (MySQLFactory factory = DaoFactoryDeliver.getInstance().createMySQLFactory()) {
            UserDao userDAO = factory.getUserDAO();
            return userDAO.findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void unblockUser(int id) {
        try (MySQLFactory factory = DaoFactoryDeliver.getInstance().createMySQLFactory()) {
            UserDao userDAO = factory.getUserDAO();
            userDAO.unblockById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findByEmail(String email) {
        try (MySQLFactory factory = DaoFactoryDeliver.getInstance().createMySQLFactory()) {
            UserDao userDAO = factory.getUserDAO();
            return userDAO.findByEmail(email);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
