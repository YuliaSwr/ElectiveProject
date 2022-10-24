package com.sida.electivefinalproject.command.admin.teacher;

import com.sida.electivefinalproject.command.Command;
import com.sida.electivefinalproject.command.Router;
import com.sida.electivefinalproject.entity.User;
import com.sida.electivefinalproject.service.UserService;
import com.sida.electivefinalproject.util.ConfigurationManager;
import com.sida.electivefinalproject.util.LoggerManager;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowTeachersCommand implements Command {
    private static final Logger logger = LoggerManager.getLogger(ShowTeachersCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("Called execute() in " + ShowTeachersCommand.class);

        Router router = new Router();
        UserService userService = (UserService) request.getServletContext().getAttribute("userService");

        List<User> teachers = userService.findAllTeachers();
        request.setAttribute("teachersList", teachers);

        if (request.getParameter("id") != null) {
            User user = userService.findById(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("teacher", user);
        }

        if (request.getParameter("del_id") != null) {
            request.setAttribute("delid", Integer.parseInt(request.getParameter("del_id")));
        }

        router.setPagePath(ConfigurationManager.getProperty("path.page.showTeachers"));
        return router;
    }
}
