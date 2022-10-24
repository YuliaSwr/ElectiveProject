package com.sida.electivefinalproject.command.teacher;

import com.sida.electivefinalproject.command.Command;
import com.sida.electivefinalproject.command.RouteType;
import com.sida.electivefinalproject.command.Router;
import com.sida.electivefinalproject.entity.FinalGrade;
import com.sida.electivefinalproject.service.FinalGradeService;
import com.sida.electivefinalproject.util.ConfigurationManager;
import com.sida.electivefinalproject.util.LoggerManager;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class SetGradeCommand implements Command {
    private static final Logger logger = LoggerManager.getLogger(SetGradeCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("Called execute() in " + SetGradeCommand.class);

        Router router = new Router();

        int courseId = Integer.parseInt(request.getParameter("course_id"));
        int studentId = Integer.parseInt(request.getParameter("student_id"));
        int grade = Integer.parseInt(request.getParameter("grade"));

        FinalGrade finalGrade = new FinalGrade(courseId, studentId, grade);

        FinalGradeService finalGradeService = (FinalGradeService) request.getServletContext().getAttribute("finalGradeService");
        finalGradeService.addFinalGrade(finalGrade);

        router.setType(RouteType.REDIRECT);
        router.setPagePath(ConfigurationManager.getProperty("path.page.teacher.redirect.journalPage") + courseId);

        return router;
    }
}
