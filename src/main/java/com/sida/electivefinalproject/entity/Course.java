package com.sida.electivefinalproject.entity;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class Course extends BaseEntity implements Serializable {
    private int id;
    private String title;
    private String description;
    private Specialization specialization;
    private int durationInWeeks;
    private int teacher;

    public Course(int id, String title, String description, Specialization specialization, int durationInWeeks, int teacher) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.specialization = specialization;
        this.durationInWeeks = durationInWeeks;
        this.teacher = teacher;
    }

    public Course(String title, String description, Specialization specialization, int durationInWeeks, int teacher) {
        this.title = title;
        this.description = description;
        this.specialization = specialization;
        this.durationInWeeks = durationInWeeks;
        this.teacher = teacher;
    }
}

