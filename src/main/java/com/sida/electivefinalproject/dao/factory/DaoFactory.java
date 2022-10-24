package com.sida.electivefinalproject.dao.factory;

import com.sida.electivefinalproject.dao.CourseDAO;
import com.sida.electivefinalproject.dao.EnrollmentDAO;
import com.sida.electivefinalproject.dao.FinalGradeDAO;
import com.sida.electivefinalproject.dao.UserDao;

public interface DaoFactory extends AutoCloseable {
    /**
     * Get Course DAO
     *
     * @return Course DAO
     */
    CourseDAO getCourseDAO();

    /**
     * Get User DAO
     *
     * @return User DAO
     */
    UserDao getUserDAO();

    /**
     * Get FinalGrade DAO
     *
     * @return FinalGrade DAO
     */
    FinalGradeDAO getFinalGradeDAO();

    /**
     * Get Enrollment DAO
     *
     * @return Enrollment DAO
     */
    EnrollmentDAO getEnrollmentDAO();
}
