package com.sida.electivefinalproject.command.teacher;

import com.sida.electivefinalproject.command.Command;
import com.sida.electivefinalproject.command.Router;
import com.sida.electivefinalproject.command.student.StudentHomePageCommand;
import com.sida.electivefinalproject.service.CourseService;
import com.sida.electivefinalproject.util.ConfigurationManager;
import com.sida.electivefinalproject.util.LoggerManager;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class TeacherHomePageCommand implements Command {
    private static final Logger logger = LoggerManager.getLogger(TeacherHomePageCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("Called execute() in " + TeacherHomePageCommand.class);

        Router router = new Router();

        int teacherId = (int) request.getSession(true).getAttribute("user_id");
        CourseService courseService = (CourseService) request.getServletContext().getAttribute("courseService");
        int allCourses = courseService.findAllByTeacherId(teacherId).size();

        request.setAttribute("allCourses", allCourses);

        router.setPagePath(ConfigurationManager.getProperty("path.page.teacher.homePage"));

        return router;
    }
}
