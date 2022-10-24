package com.sida.electivefinalproject.command.admin.course;

import com.sida.electivefinalproject.command.Command;
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
import java.util.List;
import java.util.stream.Collectors;

public class ShowCoursesCommand implements Command {
    private static final Logger logger = LoggerManager.getLogger(ShowCoursesCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("Called execute() in " + ShowCoursesCommand.class);

        Router router = new Router();
        UserService userService = (UserService) request.getServletContext().getAttribute("userService");
        CourseService courseService = (CourseService) request.getServletContext().getAttribute("courseService");

        List<Course> courses = courseService.findAll();

        if (request.getParameter("id") != null) {
            Course course = courseService.findById(Integer.parseInt(request.getParameter("id")));
            User teacher = userService.findById(course.getTeacher());
            request.setAttribute("course", course);
            request.setAttribute("teacher", teacher);
        }

        if (request.getParameter("del_id") != null) {
            request.setAttribute("delid", Integer.parseInt(request.getParameter("del_id")));
        }

        request.setAttribute("numALL", courseService.findAll().size());
        request.setAttribute("numIT", courseService.findAllBySpecialization(Specialization.TECHNOLOGY).size());
        request.setAttribute("numART", courseService.findAllBySpecialization(Specialization.ART).size());
        request.setAttribute("numSCIENCE", courseService.findAllBySpecialization(Specialization.SCIENCE).size());
        request.setAttribute("numECONOMY", courseService.findAllBySpecialization(Specialization.ECONOMY).size());
        request.setAttribute("numHUMANITIES", courseService.findAllBySpecialization(Specialization.HUMANITIES).size());

        request.setAttribute("coursesList", courses);
        request.setAttribute("teachersList", userService.findAllTeachers());

        router.setPagePath(ConfigurationManager.getProperty("path.page.showCourses"));

        return router;
    }
}
