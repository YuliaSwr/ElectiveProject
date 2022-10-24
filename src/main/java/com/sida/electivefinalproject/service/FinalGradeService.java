package com.sida.electivefinalproject.service;

import com.sida.electivefinalproject.entity.Course;
import com.sida.electivefinalproject.entity.FinalGrade;

import java.util.List;

public interface FinalGradeService {
    List<FinalGrade> findAll();

    FinalGrade findById(int id);

    void addFinalGrade(FinalGrade finalGrade);

    void deleteFinalGradeById(int id);

    void updateFinalGrade(FinalGrade finalGrade);

    FinalGrade findByStudentIdAndCourseId(int studentId, int courseId);

    List<FinalGrade> findByStudentId(int studentId);
}
