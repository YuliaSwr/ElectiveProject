package com.sida.electivefinalproject.dao;

import com.sida.electivefinalproject.entity.FinalGrade;
import com.sida.electivefinalproject.exception.DatabaseException;

import java.util.List;

public interface FinalGradeDAO extends Dao<FinalGrade> {
    FinalGrade findByStudentIdAndCourseId(int studentId, int courseId) throws DatabaseException;
    List<FinalGrade> findByStudentId(int studentId) throws DatabaseException;
}
