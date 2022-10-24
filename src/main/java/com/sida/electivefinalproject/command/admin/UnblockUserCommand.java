package com.sida.electivefinalproject.command.admin;

import com.sida.electivefinalproject.command.Command;
import com.sida.electivefinalproject.command.RouteType;
import com.sida.electivefinalproject.command.Router;
import com.sida.electivefinalproject.command.admin.student.DeleteStudentCommand;
import com.sida.electivefinalproject.command.admin.student.EditStudentCommand;
import com.sida.electivefinalproject.entity.AccountStatus;
import com.sida.electivefinalproject.entity.User;
import com.sida.electivefinalproject.entity.UserType;
import com.sida.electivefinalproject.service.UserService;
import com.sida.electivefinalproject.util.ConfigurationManager;
import com.sida.electivefinalproject.util.LoggerManager;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class UnblockUserCommand implements Command {
    private static final Logger logger = LoggerManager.getLogger(UnblockUserCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("Called execute() in " + UnblockUserCommand.class);

        Router router = new Router();

        UserService userService = (UserService) request.getServletContext().getAttribute("userService");

        int id = Integer.parseInt(request.getParameter("id"));
        userService.unblockUser(id);

        router.setType(RouteType.REDIRECT);
        router.setPagePath(ConfigurationManager.getProperty("path.page.redirect.showStudents"));

        return router;
    }
}
