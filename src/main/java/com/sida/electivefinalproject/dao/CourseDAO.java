package com.sida.electivefinalproject.dao;

import com.sida.electivefinalproject.entity.Course;
import com.sida.electivefinalproject.entity.Specialization;
import com.sida.electivefinalproject.exception.DatabaseException;

import java.util.List;

public interface CourseDAO extends Dao<Course> {
    List<Course> findAllCoursesBySpecialization(Specialization specialization) throws DatabaseException;

    List<Course> findAllByTeacherId(int teacherId) throws DatabaseException;
}
