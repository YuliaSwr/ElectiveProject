package com.sida.electivefinalproject.command.student;

import com.sida.electivefinalproject.command.Command;
import com.sida.electivefinalproject.command.RouteType;
import com.sida.electivefinalproject.command.Router;
import com.sida.electivefinalproject.service.EnrollmentService;
import com.sida.electivefinalproject.util.ConfigurationManager;
import com.sida.electivefinalproject.util.LoggerManager;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class LeaveCourseCommand implements Command {
    private static final Logger logger = LoggerManager.getLogger(LeaveCourseCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("Called execute() in " + LeaveCourseCommand.class);

        Router router = new Router();

        int studentId = (int) request.getSession(true).getAttribute("user_id");
        int courseId = Integer.parseInt(request.getParameter("id"));

        EnrollmentService enrollmentService = (EnrollmentService) request.getServletContext().getAttribute("enrollmentService");
        enrollmentService.deleteEnrollmentByStudentIdAndCourseId(studentId, courseId);

        router.setType(RouteType.REDIRECT);
        router.setPagePath(ConfigurationManager.getProperty("path.page.student.redirect.myCourses"));
        return router;
    }
}
