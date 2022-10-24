package com.sida.electivefinalproject.command.student;

import com.sida.electivefinalproject.command.Command;
import com.sida.electivefinalproject.command.Router;
import com.sida.electivefinalproject.entity.*;
import com.sida.electivefinalproject.service.CourseService;
import com.sida.electivefinalproject.service.EnrollmentService;
import com.sida.electivefinalproject.service.FinalGradeService;
import com.sida.electivefinalproject.service.UserService;
import com.sida.electivefinalproject.util.ConfigurationManager;
import com.sida.electivefinalproject.util.LoggerManager;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MyGradesCommand implements Command {
    private static final Logger logger = LoggerManager.getLogger(MyGradesCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("Called execute() in " + MyGradesCommand.class);

        Router router = new Router();

        CourseService courseService = (CourseService) request.getServletContext().getAttribute("courseService");
        FinalGradeService finalGradeService = (FinalGradeService) request.getServletContext().getAttribute("finalGradeService");
        UserService userService = (UserService) request.getServletContext().getAttribute("userService");

        int studentId = (int) request.getSession(true).getAttribute("user_id");

        List<GradeContainer> gradeContainers = new ArrayList<>();
        List<FinalGrade> finalGrades = finalGradeService.findByStudentId(studentId);
        for (FinalGrade finalGrade : finalGrades) {
            Course course = courseService.findById(finalGrade.getCourseId());
            User teacher = userService.findById(course.getTeacher());
            String teacherName = teacher.getFirstName() + " " + teacher.getLastName();
            GradeContainer gradeContainer = new GradeContainer(course, finalGrade, teacherName);
            gradeContainers.add(gradeContainer);
        }
        request.setAttribute("gradeContainers", gradeContainers);

        router.setPagePath(ConfigurationManager.getProperty("path.page.student.myGrades"));
        return router;
    }
}
