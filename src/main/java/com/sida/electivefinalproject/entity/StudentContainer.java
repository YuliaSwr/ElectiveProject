package com.sida.electivefinalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class StudentContainer {
    private User student;
    private Enrollment enrollment;
    private FinalGrade finalGrade;
}
