package com.sida.electivefinalproject.command.login;

import com.sida.electivefinalproject.command.Command;
import com.sida.electivefinalproject.command.Router;
import com.sida.electivefinalproject.entity.User;
import com.sida.electivefinalproject.service.UserService;
import com.sida.electivefinalproject.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        HttpSession session = request.getSession(true);
        session.setAttribute("user_id", null);
        session.setAttribute("user_name", null);
        session.setAttribute("user_email", null);
        session.setAttribute("user_photo", null);
        session.setAttribute("user_role", null);

        router.setPagePath(ConfigurationManager.getProperty("path.page.login"));
        return router;
    }
}
