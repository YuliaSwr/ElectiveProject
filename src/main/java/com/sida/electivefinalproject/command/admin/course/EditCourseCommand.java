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

public class EditCourseCommand implements Command {
    private static final Logger logger = LoggerManager.getLogger(EditCourseCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("Called execute() in " + EditCourseCommand.class);

        Router router = new Router();

        CourseService courseService = (CourseService) request.getServletContext().getAttribute("courseService");
        Specialization specialization;
        if (request.getParameter("specialization").length() > 1) {
            specialization = Specialization.valueOf(Specialization.class, request.getParameter("specialization"));
        } else {
            specialization = Specialization.getName(Integer.parseInt(request.getParameter("specialization")));
        }

        UserService userService = (UserService) request.getServletContext().getAttribute("userService");
        User teacher = userService.findById(Integer.parseInt(request.getParameter("teacherId")));

        Course course = new Course(
                Integer.parseInt(request.getParameter("id")),
                request.getParameter("title"),
                request.getParameter("description"),
                specialization,
                Integer.parseInt(request.getParameter("durationInWeeks")),
                Integer.parseInt(request.getParameter("teacherId"))
        );
        courseService.updateCourse(course);

        request.setAttribute("id", null);
        router.setType(RouteType.REDIRECT);
        router.setPagePath(ConfigurationManager.getProperty("path.page.redirect.showCourses"));

        return router;
    }
}
