package com.sida.electivefinalproject.service;

import com.sida.electivefinalproject.entity.Course;
import com.sida.electivefinalproject.entity.Enrollment;

import java.util.List;

public interface EnrollmentService {

    void addEnrollment(Enrollment enrollment);
    List<Integer> getAllCoursesIdsByUser(int userId);
    List<Integer> getCompletedCoursesIdsByUser(int userId);
    List<Integer> getAllStudentsIdsByCourse(int courseId);
    void deleteEnrollmentByStudentIdAndCourseId(int studentId, int courseId);
    void startCourse(int studentId, int courseId);
    Enrollment findByStudentAndCourse(int studentId, int courseId);
}
