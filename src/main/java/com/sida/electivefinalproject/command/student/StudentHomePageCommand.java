package com.sida.electivefinalproject.command.student;

import com.sida.electivefinalproject.command.Command;
import com.sida.electivefinalproject.command.Router;
import com.sida.electivefinalproject.command.admin.AdminHomePageCommand;
import com.sida.electivefinalproject.service.EnrollmentService;
import com.sida.electivefinalproject.util.ConfigurationManager;
import com.sida.electivefinalproject.util.LoggerManager;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

public class StudentHomePageCommand implements Command {
    private static final Logger logger = LoggerManager.getLogger(StudentHomePageCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("Called execute() in " + StudentHomePageCommand.class);

        Router router = new Router();

        int studentId = (int) request.getSession(true).getAttribute("user_id");
        EnrollmentService enrollmentService = (EnrollmentService) request.getServletContext().getAttribute("enrollmentService");

        int allCourses = enrollmentService.getAllCoursesIdsByUser(studentId).size();
        int completedCourses = enrollmentService.getCompletedCoursesIdsByUser(studentId).size();

        request.setAttribute("allCourses", allCourses);
        request.setAttribute("completedCourses", completedCourses);

        router.setPagePath(ConfigurationManager.getProperty("path.page.student.homePage"));

        return router;
    }
}
