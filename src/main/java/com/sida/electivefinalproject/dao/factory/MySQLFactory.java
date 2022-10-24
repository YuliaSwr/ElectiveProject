package com.sida.electivefinalproject.dao.factory;

import com.sida.electivefinalproject.dao.EnrollmentDAO;
import com.sida.electivefinalproject.dao.FinalGradeDAO;
import com.sida.electivefinalproject.dao.UserDao;
import com.sida.electivefinalproject.dao.mysql.MySqlCourseDao;
import com.sida.electivefinalproject.dao.mysql.MySqlEnrollmentDao;
import com.sida.electivefinalproject.dao.mysql.MySqlFinalGradeDao;
import com.sida.electivefinalproject.dao.mysql.MySqlUserDao;
import com.sida.electivefinalproject.pool.ConnectionPool;
import com.sida.electivefinalproject.util.LoggerManager;
import org.slf4j.Logger;

import java.sql.Connection;

/**
 * MySQL Factory from AbstractFactory pattern.
 * Create DAOs
 */
public class MySQLFactory implements DaoFactory {
    private static final Logger logger = LoggerManager.getLogger(MySQLFactory.class);
    private Connection connection;

    public MySQLFactory() {
        try {
            this.connection = ConnectionPool.getInstance().getConnection();
            logger.debug("Set connection to MySQLFactory via constructor");
        } catch (Exception e) {
            logger.error("Can't get connection in MySQLFactory constructor", e);
        }
    }


    @Override
    public MySqlCourseDao getCourseDAO() {
        MySqlCourseDao courseDao = new MySqlCourseDao();
        courseDao.setConnection(connection);
        return courseDao;
    }

    @Override
    public FinalGradeDAO getFinalGradeDAO() {
        MySqlFinalGradeDao mySqlFinalGradeDao = new MySqlFinalGradeDao();
        mySqlFinalGradeDao.setConnection(connection);
        return mySqlFinalGradeDao;
    }

    @Override
    public EnrollmentDAO getEnrollmentDAO() {
        MySqlEnrollmentDao enrollmentDao = new MySqlEnrollmentDao();
        enrollmentDao.setConnection(connection);
        return enrollmentDao;
    }

    @Override
    public UserDao getUserDAO() {
        MySqlUserDao userDao = new MySqlUserDao();
        userDao.setConnection(connection);
        return userDao;
    }

    @Override
    public void close() throws Exception {

    }
}
