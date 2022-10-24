package com.sida.electivefinalproject.command.student;

import com.sida.electivefinalproject.command.Command;
import com.sida.electivefinalproject.command.Router;
import com.sida.electivefinalproject.entity.Course;
import com.sida.electivefinalproject.entity.Enrollment;
import com.sida.electivefinalproject.entity.ProgressStatus;
import com.sida.electivefinalproject.service.CourseService;
import com.sida.electivefinalproject.service.EnrollmentService;
import com.sida.electivefinalproject.util.ConfigurationManager;
import com.sida.electivefinalproject.util.LoggerManager;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.time.LocalDate;

public class EnrollCourseCommand implements Command {
    private static final Logger logger = LoggerManager.getLogger(EnrollCourseCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("Called execute() in " + EnrollCourseCommand.class);

        Router router = new Router();

        CourseService courseService = (CourseService) request.getServletContext().getAttribute("courseService");

        int studentId = (int) request.getSession(true).getAttribute("user_id");
        int courseId = Integer.parseInt(request.getParameter("id"));
        Course course = courseService.findById(courseId);

        Enrollment enrollment = new Enrollment(
                courseId,
                studentId,
                Date.valueOf(LocalDate.now()),
                null,
                null,
                ProgressStatus.UNSTARTED
        );

        EnrollmentService enrollmentService = (EnrollmentService) request.getServletContext().getAttribute("enrollmentService");
        enrollmentService.addEnrollment(enrollment);

        router.setPagePath(ConfigurationManager.getProperty("path.page.student.success"));
        return router;
    }
}
