package com.sida.electivefinalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GradeContainer {
    private Course course;
    private FinalGrade finalGrade;
    private String teacherName;
}
