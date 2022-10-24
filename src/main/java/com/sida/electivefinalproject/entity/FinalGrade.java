package com.sida.electivefinalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class FinalGrade extends BaseEntity implements Serializable {
    private int id;
    private int courseId;
    private int studentId;
    private int grade;

    public FinalGrade(int courseId, int studentId, int grade) {
        this.courseId = courseId;
        this.studentId = studentId;
        this.grade = grade;
    }
}
