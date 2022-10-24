package com.sida.electivefinalproject.command.teacher;

import com.sida.electivefinalproject.command.Command;
import com.sida.electivefinalproject.command.Router;
import com.sida.electivefinalproject.command.student.StudentMyCoursesCommand;
import com.sida.electivefinalproject.entity.Course;
import com.sida.electivefinalproject.entity.CourseContainer;
import com.sida.electivefinalproject.entity.Enrollment;
import com.sida.electivefinalproject.entity.ProgressStatus;
import com.sida.electivefinalproject.service.CourseService;
import com.sida.electivefinalproject.service.EnrollmentService;
import com.sida.electivefinalproject.util.ConfigurationManager;
import com.sida.electivefinalproject.util.LoggerManager;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TeacherMyCoursesCommand implements Command {
    private static final Logger logger = LoggerManager.getLogger(TeacherMyCoursesCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("Called execute() in " + TeacherMyCoursesCommand.class);

        Router router = new Router();
        int teacherId = (int) request.getSession(true).getAttribute("user_id");

        CourseService courseService = (CourseService) request.getServletContext().getAttribute("courseService");
        List<Course> courses = courseService.findAllByTeacherId(teacherId);

        request.setAttribute("coursesList", courses);

        router.setPagePath(ConfigurationManager.getProperty("path.page.teacher.myCourses"));
        return router;
    }
}
