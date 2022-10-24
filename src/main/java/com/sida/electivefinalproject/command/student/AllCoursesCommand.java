package com.sida.electivefinalproject.command.student;

import com.sida.electivefinalproject.command.Command;
import com.sida.electivefinalproject.command.Router;
import com.sida.electivefinalproject.entity.*;
import com.sida.electivefinalproject.service.CourseService;
import com.sida.electivefinalproject.service.EnrollmentService;
import com.sida.electivefinalproject.service.UserService;
import com.sida.electivefinalproject.util.ConfigurationManager;
import com.sida.electivefinalproject.util.LoggerManager;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class AllCoursesCommand implements Command {
    private static final Logger logger = LoggerManager.getLogger(AllCoursesCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("Called execute() in " + AllCoursesCommand.class);

        Router router = new Router();

        EnrollmentService enrollmentService = (EnrollmentService) request.getServletContext().getAttribute("enrollmentService");
        UserService userService = (UserService) request.getServletContext().getAttribute("userService");
        CourseService courseService = (CourseService) request.getServletContext().getAttribute("courseService");

        List<Course> courses = courseService.findAll();
        List<CourseContainer> courseContainers = new ArrayList<>();

        for (Course course: courses) {
            long studentsRegistered = enrollmentService.getAllStudentsIdsByCourse(course.getId()).size();
            User teacher = userService.findById(course.getTeacher());
            String teacherName = teacher.getFirstName() + " " + teacher.getLastName();
            courseContainers.add(new CourseContainer(course, studentsRegistered, teacherName));
        }

        request.setAttribute("courseContainers", courseContainers);

        router.setPagePath(ConfigurationManager.getProperty("path.page.student.allCoursesPage"));
        return router;
    }
}
