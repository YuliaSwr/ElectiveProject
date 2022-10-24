package com.sida.electivefinalproject.command.teacher;

import com.sida.electivefinalproject.command.Command;
import com.sida.electivefinalproject.command.Router;
import com.sida.electivefinalproject.entity.*;
import com.sida.electivefinalproject.service.CourseService;
import com.sida.electivefinalproject.service.EnrollmentService;
import com.sida.electivefinalproject.service.FinalGradeService;
import com.sida.electivefinalproject.service.UserService;
import com.sida.electivefinalproject.util.ConfigurationManager;
import com.sida.electivefinalproject.util.LoggerManager;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class JournalPageCommand implements Command {
    private static final Logger logger = LoggerManager.getLogger(JournalPageCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("Called execute() in " + JournalPageCommand.class);

        Router router = new Router();
        EnrollmentService enrollmentService = (EnrollmentService) request.getServletContext().getAttribute("enrollmentService");
        CourseService courseService = (CourseService) request.getServletContext().getAttribute("courseService");
        UserService userService = (UserService) request.getServletContext().getAttribute("userService");
        FinalGradeService finalGradeService = (FinalGradeService) request.getServletContext().getAttribute("finalGradeService");

        int courseId = Integer.parseInt(request.getParameter("id"));
        Course course = courseService.findById(courseId);
        request.setAttribute("course", course);

        List<StudentContainer> studentContainers = new ArrayList<>();
        List<Integer> studentIds = enrollmentService.getAllStudentsIdsByCourse(courseId);
        for (Integer studentId : studentIds) {
            User student = userService.findById(studentId);
            Enrollment enrollment = enrollmentService.findByStudentAndCourse(studentId, courseId);
            FinalGrade finalGrade = finalGradeService.findByStudentIdAndCourseId(studentId, courseId);
            studentContainers.add(new StudentContainer(student, enrollment, finalGrade));
        }

        if (request.getParameter("student_id") != null) {
            request.setAttribute("studentId", Integer.parseInt(request.getParameter("student_id")));
        }

        request.setAttribute("studentContainers", studentContainers);

        router.setPagePath(ConfigurationManager.getProperty("path.page.teacher.journalPage"));

        return router;
    }
}
