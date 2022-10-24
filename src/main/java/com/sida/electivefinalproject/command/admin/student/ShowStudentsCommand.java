package com.sida.electivefinalproject.command.admin.student;

import com.sida.electivefinalproject.command.Command;
import com.sida.electivefinalproject.command.Router;
import com.sida.electivefinalproject.entity.User;
import com.sida.electivefinalproject.service.UserService;
import com.sida.electivefinalproject.util.ConfigurationManager;
import com.sida.electivefinalproject.util.LoggerManager;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowStudentsCommand implements Command {
    private static final Logger logger = LoggerManager.getLogger(ShowStudentsCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("Called execute() in " + ShowStudentsCommand.class);

        Router router = new Router();
        UserService userService = (UserService) request.getServletContext().getAttribute("userService");

        List<User> students = userService.findAllStudents();
        request.setAttribute("studentsList", students);

        if (request.getParameter("id") != null) {
            User user = userService.findById(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("student", user);
        }

        if (request.getParameter("del_id") != null) {
            request.setAttribute("delid", Integer.parseInt(request.getParameter("del_id")));
        }

        router.setPagePath(ConfigurationManager.getProperty("path.page.showStudents"));
        return router;
    }
}
