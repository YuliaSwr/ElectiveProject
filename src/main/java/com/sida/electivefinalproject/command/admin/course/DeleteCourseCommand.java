package com.sida.electivefinalproject.command.admin.course;

import com.sida.electivefinalproject.command.Command;
import com.sida.electivefinalproject.command.RouteType;
import com.sida.electivefinalproject.command.Router;
import com.sida.electivefinalproject.service.CourseService;
import com.sida.electivefinalproject.util.ConfigurationManager;
import com.sida.electivefinalproject.util.LoggerManager;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DeleteCourseCommand implements Command {
    private static final Logger logger = LoggerManager.getLogger(EditCourseCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("Called execute() in " + DeleteCourseCommand.class);

        Router router = new Router();

        CourseService courseService = (CourseService) request.getServletContext().getAttribute("courseService");
        courseService.deleteCourse(Integer.parseInt(request.getParameter("id")));

        router.setType(RouteType.REDIRECT);
        router.setPagePath(ConfigurationManager.getProperty("path.page.redirect.showCourses"));

        return router;
    }
}
