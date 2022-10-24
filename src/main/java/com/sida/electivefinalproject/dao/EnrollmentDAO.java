package com.sida.electivefinalproject.dao;

import com.sida.electivefinalproject.entity.Course;
import com.sida.electivefinalproject.entity.Enrollment;
import com.sida.electivefinalproject.entity.User;
import com.sida.electivefinalproject.exception.DatabaseException;

import java.util.List;

public interface EnrollmentDAO extends Dao<Enrollment> {
    List<Integer> findAllCoursesIdsByUserId(int userId) throws DatabaseException;

    List<Integer> findAllStudentsIdsEnrolledToCourse(int courseId) throws DatabaseException;

    boolean deleteByStudentIdAndCourseId(int studentId, int courseId) throws DatabaseException;

    boolean updateCourseStart(int studentId, int courseId) throws DatabaseException;

    Enrollment findByStudentIdAndCourseId(int studentId, int courseId) throws DatabaseException;

    List<Integer> findCompletedCoursesIdsByUserId(int userId) throws DatabaseException;
}
