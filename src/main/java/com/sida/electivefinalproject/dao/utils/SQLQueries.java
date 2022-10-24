package com.sida.electivefinalproject.dao.utils;

import static com.sida.electivefinalproject.dao.utils.ColumnName.*;

public class SQLQueries {

    // COURSE TABLE
    public static final String SELECT_ALL_COURSES = "SELECT * FROM course";
    public static final String SELECT_COURSE_BY_ID = "SELECT * FROM course WHERE id = ?";
    public static final String SELECT_ALL_COURSES_BY_TEACHER_ID = "SELECT * FROM course WHERE teacher_id = ?";
    public static final String SELECT_ALL_COURSES_BY_SPECIALIZATION = "SELECT * FROM course WHERE " + COURSE_SPECIALIZATION_ID + " = ?";
    public static final String INSERT_NEW_COURSE = "INSERT INTO course VALUES(null, ?, ?, ?, ?, ?)";
    public static final String UPDATE_COURSE_BY_ID = "UPDATE course SET "
            + COURSE_TITLE + " = ?, "
            + COURSE_DESCRIPTION + " = ?, "
            + COURSE_DURATION + " = ?, "
            + COURSE_SPECIALIZATION_ID + " = ?, "
            + COURSE_TEACHER_ID + " = ? WHERE " + COURSE_ID + " = ?";
    public static final String DELETE_COURSE_BY_ID = "DELETE FROM course WHERE " + COURSE_ID + " = ?";

    // USER TABLE
    public static final String SELECT_ALL_USERS = "SELECT * FROM user";
    public static final String SELECT_ALL_USERS_BY_TYPE = "SELECT * FROM user WHERE user_type = ?";
    public static final String SELECT_USER_BY_ID = "SELECT * FROM user WHERE id = ?";
    public static final String SELECT_USER_BY_EMAIL = "SELECT * FROM user WHERE email = ?";
    public static final String INSERT_NEW_USER = "INSERT INTO user VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_USER_BY_ID = "UPDATE user SET first_name=?, last_name=?, gender=?, email=?, pass=?, user_type=?, account_status=?, photo=? WHERE id=?";
    public static final String UNBLOCK_USER_BY_ID = "UPDATE user SET account_status='ACTIVE' WHERE id=?";
    public static final String DELETE_USER_BY_ID = "DELETE FROM user WHERE " + USER_ID + " = ?";

    // ENROLLMENT TABLE
    public static final String SELECT_ALL_ENROLLMENTS = "SELECT enrollment.id, course_id, student_id, enrollment_date, start_date, end_date, progress_status.status_name FROM enrollment \n" +
            "INNER JOIN progress_status ON enrollment.progress_status_id=progress_status.id";
    public static final String SELECT_ENROLLMENT_BY_ID = "SELECT enrollment.id, course_id, student_id, enrollment_date, start_date, end_date, progress_status.status_name FROM enrollment \n" +
            "INNER JOIN progress_status ON enrollment.progress_status_id=progress_status.id WHERE enrollment.id = ?";
    public static final String SELECT_STUDENTS_ENROLLED_TO_COURSE = "SELECT student_id FROM enrollment WHERE course_id = ?";
    public static final String SELECT_ENROLLMENT_BY_STUDENT_ID_AND_COURSE_ID = "SELECT * FROM enrollment WHERE student_id = ? AND course_id = ?";
    public static final String SELECT_ENROLLED_COURSES_IDS_BY_STUDENT_ID = "SELECT course_id FROM enrollment WHERE student_id = ?";
    public static final String SELECT_COMPLETED_COURSES_IDS_BY_STUDENT_ID = "SELECT course_id FROM enrollment WHERE student_id = ? AND progress_status_id = 3";
    public static final String INSERT_NEW_ENROLLMENT = "INSERT INTO enrollment VALUES(null, ?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_ENROLLMENT_AS_STARTED = "UPDATE enrollment \n" +
            "INNER JOIN course on enrollment.course_id = course.id\n" +
            "SET start_date = current_date(), end_date = date_add(current_date(), INTERVAL course.duration_in_weeks WEEK), progress_status_id = 2 \n" +
            "WHERE student_id = ? AND course_id = ?";
    public static final String DELETE_ENROLLMENT_BY_STUDENT_ID_AND_COURSE_ID = "DELETE FROM enrollment WHERE student_id = ? AND course_id = ?";
    public static final String DELETE_ENROLLMENT_BY_ID = "DELETE FROM enrollment WHERE student_id = ? AND course_id = ?";

    // FINAL_GRADE TABLE
    public static final String SELECT_ALL_FINAL_GRADES = "SELECT * FROM final_grade";
    public static final String SELECT_FINAL_GRADE_BY_ID = "SELECT * FROM final_grade WHERE id = ?";
    public static final String SELECT_FINAL_GRADE_BY_STUDENT_AND_COURSE_ID = "SELECT * FROM final_grade WHERE student_id = ? AND course_id = ?";
    public static final String SELECT_FINAL_GRADE_BY_STUDENT = "SELECT * FROM final_grade WHERE student_id = ?";
    public static final String INSERT_NEW_FINAL_GRADE = "INSERT INTO final_grade VALUES(null, ?, ?, ?)";
    public static final String UPDATE_FINAL_GRADE_BY_ID = "UPDATE final_grade SET course_id = ?, student_id = ?, grade = ? WHERE id = ?";
    public static final String DELETE_FINAL_GRADE_BY_ID = "DELETE FROM final_grade WHERE id = ?";
}
