package com.sida.electivefinalproject.entity;

import lombok.*;

import java.io.Serializable;
import java.sql.Date;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Enrollment extends BaseEntity implements Serializable {
    private int id;
    private int courseId;
    private int studentId;
    private Date enrollmentDate;
    private Date startDate;
    private Date endDate;
    private ProgressStatus progressStatus;

    public Enrollment(int courseId, int studentId, Date enrollmentDate, Date startDate, Date endDate, ProgressStatus progressStatus) {
        this.courseId = courseId;
        this.studentId = studentId;
        this.enrollmentDate = enrollmentDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.progressStatus = progressStatus;
    }
}
