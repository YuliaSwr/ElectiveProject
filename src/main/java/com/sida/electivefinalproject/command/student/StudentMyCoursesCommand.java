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
import java.util.stream.Collectors;

public class StudentMyCoursesCommand implements Command {
    private static final Logger logger = LoggerManager.getLogger(StudentMyCoursesCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("Called execute() in " + StudentMyCoursesCommand.class);

        Router router = new Router();

        int studentId = (int) request.getSession(true).getAttribute("user_id");

        EnrollmentService enrollmentService = (EnrollmentService) request.getServletContext().getAttribute("enrollmentService");
        CourseService courseService = (CourseService) request.getServletContext().getAttribute("courseService");
        UserService userService = (UserService) request.getServletContext().getAttribute("userService");

        List<Integer> coursesIds = enrollmentService.getAllCoursesIdsByUser(studentId);

        List<CourseContainer> courseContainers = new ArrayList<>();
        for (Integer courseId : coursesIds) {
            Course course = courseService.findById(courseId);
            Enrollment enrollment = enrollmentService.findByStudentAndCourse(studentId, courseId);
            long studentsRegistered = enrollmentService.getAllStudentsIdsByCourse(courseId).size();
            User teacher = userService.findById(course.getTeacher());
            String teacherName = teacher.getFirstName() + " " + teacher.getLastName();
            courseContainers.add(new CourseContainer(course, enrollment, studentsRegistered, teacherName));
        }

        if (request.getParameter("type") != null) {
            String type = request.getParameter("type");
            switch (type) {
                case "enrolled":
                    courseContainers = courseContainers.stream().filter(x -> x.getEnrollment().getProgressStatus().equals(ProgressStatus.UNSTARTED)).collect(Collectors.toList());
                    break;
                case "started":
                    courseContainers = courseContainers.stream().filter(x -> x.getEnrollment().getProgressStatus().equals(ProgressStatus.IN_PROGRESS)).collect(Collectors.toList());
                    break;
                case "completed":
                    courseContainers = courseContainers.stream().filter(x -> x.getEnrollment().getProgressStatus().equals(ProgressStatus.COMPLETED)).collect(Collectors.toList());
                    break;
                default:
                    break;
            }
        }

        request.setAttribute("courseContainers", courseContainers);

        router.setPagePath(ConfigurationManager.getProperty("path.page.student.myCourses"));
        return router;
    }
}
