package com.sida.electivefinalproject.service;

import com.sida.electivefinalproject.entity.Course;
import com.sida.electivefinalproject.entity.Specialization;

import java.util.List;

public interface CourseService {
    List<Course> findAll();

    List<Course> findAllByTeacherId(int teacherId);

    void addCourse(Course course);

    void deleteCourse(int courseId);

    void updateCourse(Course course);

    Course findById(int id);

    List<Course> findAllBySpecialization(Specialization specialization);
}
