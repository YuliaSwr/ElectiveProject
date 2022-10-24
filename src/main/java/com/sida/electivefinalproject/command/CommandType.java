package com.sida.electivefinalproject.command;

import com.sida.electivefinalproject.command.admin.AdminHomePageCommand;
import com.sida.electivefinalproject.command.admin.UnblockUserCommand;
import com.sida.electivefinalproject.command.admin.course.AddCourseCommand;
import com.sida.electivefinalproject.command.admin.course.DeleteCourseCommand;
import com.sida.electivefinalproject.command.admin.course.EditCourseCommand;
import com.sida.electivefinalproject.command.admin.course.ShowCoursesCommand;
import com.sida.electivefinalproject.command.admin.student.AddStudentCommand;
import com.sida.electivefinalproject.command.admin.student.DeleteStudentCommand;
import com.sida.electivefinalproject.command.admin.student.EditStudentCommand;
import com.sida.electivefinalproject.command.admin.student.ShowStudentsCommand;
import com.sida.electivefinalproject.command.admin.teacher.AddTeacherCommand;
import com.sida.electivefinalproject.command.admin.teacher.DeleteTeacherCommand;
import com.sida.electivefinalproject.command.admin.teacher.EditTeacherCommand;
import com.sida.electivefinalproject.command.admin.teacher.ShowTeachersCommand;
import com.sida.electivefinalproject.command.login.LoginCommand;
import com.sida.electivefinalproject.command.login.LogoutCommand;
import com.sida.electivefinalproject.command.login.RegisterCommand;
import com.sida.electivefinalproject.command.student.*;
import com.sida.electivefinalproject.command.teacher.JournalPageCommand;
import com.sida.electivefinalproject.command.teacher.SetGradeCommand;
import com.sida.electivefinalproject.command.teacher.TeacherHomePageCommand;
import com.sida.electivefinalproject.command.teacher.TeacherMyCoursesCommand;

public enum CommandType {

    // LOGIN COMMANDS
    LOGIN {{
        this.command = new LoginCommand();
    }},
    LOGOUT {{
        this.command = new LogoutCommand();
    }},
    REGISTER {{
        this.command = new RegisterCommand();
    }},

    // ADMIN COMMANDS
    ADMIN_HOME {{
        this.command = new AdminHomePageCommand();
    }},
    SHOW_STUDENTS {{
        this.command = new ShowStudentsCommand();
    }},
    SHOW_TEACHERS {{
        this.command = new ShowTeachersCommand();
    }},
    SHOW_COURSES {{
        this.command = new ShowCoursesCommand();
    }},
    ADD_COURSE {{
        this.command = new AddCourseCommand();
    }},
    ADD_STUDENT {{
        this.command = new AddStudentCommand();
    }},
    ADD_TEACHER {{
        this.command = new AddTeacherCommand();
    }},
    EDIT_COURSE {{
        this.command = new EditCourseCommand();
    }},
    EDIT_STUDENT {{
        this.command = new EditStudentCommand();
    }},
    EDIT_TEACHER {{
        this.command = new EditTeacherCommand();
    }},
    DELETE_COURSE {{
        this.command = new DeleteCourseCommand();
    }},
    DELETE_STUDENT {{
        this.command = new DeleteStudentCommand();
    }},
    DELETE_TEACHER {{
        this.command = new DeleteTeacherCommand();
    }},
    UNBLOCK_USER {{
        this.command = new UnblockUserCommand();
    }},

    // STUDENT COMMANDS
    STUDENT_HOME {{
        this.command = new StudentHomePageCommand();
    }},
    ALL_COURSES {{
        this.command = new AllCoursesCommand();
    }},
    MY_COURSES_ST {{
        this.command = new StudentMyCoursesCommand();
    }},
    ENROLL_COURSE {{
        this.command = new EnrollCourseCommand();
    }},
    START_COURSE {{
        this.command = new StartCourseCommand();
    }},
    LEAVE_COURSE {{
        this.command = new LeaveCourseCommand();
    }},
    MY_GRADES {{
        this.command = new MyGradesCommand();
    }},

    // TEACHER COMMANDS
    TEACHER_HOME {{
        this.command = new TeacherHomePageCommand();
    }},
    MY_COURSES_TE {{
        this.command = new TeacherMyCoursesCommand();
    }},
    JOURNAL_PAGE {{
        this.command = new JournalPageCommand();
    }},
    SET_GRADE {{
        this.command = new SetGradeCommand();
    }};

    Command command;

    public Command getCommand() {
        return command;
    }
}
