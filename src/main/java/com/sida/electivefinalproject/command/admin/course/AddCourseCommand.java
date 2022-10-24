package com.sida.electivefinalproject.command.admin.course;

import com.sida.electivefinalproject.command.Command;
import com.sida.electivefinalproject.command.RouteType;
import com.sida.electivefinalproject.command.Router;
import com.sida.electivefinalproject.entity.Course;
import com.sida.electivefinalproject.entity.Specialization;
import com.sida.electivefinalproject.entity.User;
import com.sida.electivefinalproject.service.CourseService;
import com.sida.electivefinalproject.service.UserService;
import com.sida.electivefinalproject.util.ConfigurationManager;
import com.sida.electivefinalproject.util.LoggerManager;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddCourseCommand implements Command {
    private static final Logger logger = LoggerManager.getLogger(AddCourseCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("Called execute() in " + AddCourseCommand.class);

        Router router = new Router();

        CourseService courseService = (CourseService) request.getServletContext().getAttribute("courseService");
        Course course = new Course(
                request.getParameter("title"),
                request.getParameter("description"),
                Specialization.getName(Integer.parseInt(request.getParameter("specialization"))),
                Integer.parseInt(request.getParameter("durationInWeeks")),
                Integer.parseInt(request.getParameter("teacherId")));
        courseService.addCourse(course);

        router.setType(RouteType.REDIRECT);
        router.setPagePath(ConfigurationManager.getProperty("path.page.redirect.showCourses"));

        return router;
    }
}
