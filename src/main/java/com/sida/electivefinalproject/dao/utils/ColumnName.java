package com.sida.electivefinalproject.dao.utils;

public class ColumnName {

    // table COURSE
    public static final String COURSE_ID = "id";
    public static final String COURSE_TITLE = "title";
    public static final String COURSE_DESCRIPTION = "descrip";
    public static final String COURSE_DURATION = "duration_in_weeks";
    public static final String COURSE_SPECIALIZATION_ID = "specialization_id";
    public static final String COURSE_TEACHER_ID = "teacher_id";

    // table USER
    public static final String USER_ID = "id";
    public static final String USER_FIRSTNAME = "first_name";
    public static final String USER_LASTNAME = "last_name";
    public static final String USER_GENDER = "gender";
    public static final String USER_EMAIL = "email";
    public static final String USER_PASSWORD = "pass";
    public static final String USER_TYPE = "user_type";
    public static final String USER_ACCOUNT_STATUS = "account_status";
    public static final String USER_PHOTO = "photo";

    // table SPECIALIZATION
    public static final String SPECIALIZATION_ID = "id";
    public static final String SPECIALIZATION_NAME = "spec_name";

    // table ACCOUNT_STATUS
    public static final String ACCOUNT_STATUS_ID = "id";
    public static final String ACCOUNT_STATUS_NAME = "status_name";

    // table FINAL_GRADE
    public static final String FINAL_GRADE_ID = "id";
    public static final String FINAL_GRADE_COURSE_ID = "course_id";
    public static final String FINAL_GRADE_STUDENT_ID = "course_id";
    public static final String FINAL_GRADE_GRADE = "grade";

    // table ENROLLMENT
    public static final String ENROLLMENT_ID = "id";
    public static final String ENROLLMENT_COURSE_ID = "course_id";
    public static final String ENROLLMENT_STUDENT_ID = "student_id";
    public static final String ENROLLMENT_DATE = "enrollment_date";
    public static final String ENROLLMENT_START_DATE = "start_date";
    public static final String ENROLLMENT_END_DATE = "end_date";
    public static final String ENROLLMENT_PROGRESS_STATUS_ID = "progress_status_id";

    // table PROGRESS_STATUS
    public static final String PROGRESS_STATUS_ID = "id";
    public static final String PROGRESS_STATUS_NAME = "status_name";
}
