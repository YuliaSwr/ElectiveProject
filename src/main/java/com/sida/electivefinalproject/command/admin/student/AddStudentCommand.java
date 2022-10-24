package com.sida.electivefinalproject.command.admin.student;

import com.sida.electivefinalproject.command.Command;
import com.sida.electivefinalproject.command.RouteType;
import com.sida.electivefinalproject.command.Router;
import com.sida.electivefinalproject.entity.*;;
import com.sida.electivefinalproject.service.UserService;
import com.sida.electivefinalproject.util.ConfigurationManager;
import com.sida.electivefinalproject.util.LoggerManager;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddStudentCommand implements Command {
    private static final Logger logger = LoggerManager.getLogger(AddStudentCommand.class);
    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("Called execute() in " + AddStudentCommand.class);

        Router router = new Router();

        UserService userService = (UserService) request.getServletContext().getAttribute("userService");
        User user = new User (
                request.getParameter("firstName"),
                request.getParameter("lastName"),
                request.getParameter("gender"),
                request.getParameter("email"),
                request.getParameter("password"),
                UserType.STUDENT,
                AccountStatus.ACTIVE,
                "/images/default_photo.png"
        );
        userService.addUser(user);

        router.setType(RouteType.REDIRECT);
        router.setPagePath(ConfigurationManager.getProperty("path.page.redirect.showStudents"));

        return router;
    }
}


