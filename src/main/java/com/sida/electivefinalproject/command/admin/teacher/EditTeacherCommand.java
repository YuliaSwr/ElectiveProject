package com.sida.electivefinalproject.command.admin.teacher;

import com.sida.electivefinalproject.command.Command;
import com.sida.electivefinalproject.command.RouteType;
import com.sida.electivefinalproject.command.Router;
import com.sida.electivefinalproject.entity.AccountStatus;
import com.sida.electivefinalproject.entity.User;
import com.sida.electivefinalproject.entity.UserType;
import com.sida.electivefinalproject.service.UserService;
import com.sida.electivefinalproject.util.ConfigurationManager;
import com.sida.electivefinalproject.util.LoggerManager;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class EditTeacherCommand implements Command {
    private static final Logger logger = LoggerManager.getLogger(EditTeacherCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("Called execute() in " + EditTeacherCommand.class);

        Router router = new Router();
        UserService userService = (UserService) request.getServletContext().getAttribute("userService");

        int id = Integer.parseInt(request.getParameter("id"));
        User user = new User(
                id,
                request.getParameter("firstName"),
                request.getParameter("lastName"),
                request.getParameter("gender"),
                request.getParameter("email"),
                request.getParameter("password"),
                UserType.TEACHER,
                AccountStatus.ACTIVE,
                request.getParameter("photo")
        );
        userService.updateUser(user);

        router.setType(RouteType.REDIRECT);
        router.setPagePath(ConfigurationManager.getProperty("path.page.redirect.showTeachers"));

        return router;
    }
}
