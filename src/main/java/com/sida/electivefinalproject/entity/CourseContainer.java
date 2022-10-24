package com.sida.electivefinalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourseContainer {
    private Course course;
    private Enrollment enrollment;
    private long studentsRegistered;
    private String teacherName;

    public CourseContainer(Course course, long studentsRegistered, String teacherName) {
        this.course = course;
        this.studentsRegistered = studentsRegistered;
        this.teacherName = teacherName;
    }
}
