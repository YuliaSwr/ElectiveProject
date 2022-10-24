package com.sida.electivefinalproject.command.login;

import com.sida.electivefinalproject.command.Command;
import com.sida.electivefinalproject.command.RouteType;
import com.sida.electivefinalproject.command.Router;
import com.sida.electivefinalproject.entity.User;
import com.sida.electivefinalproject.entity.UserType;
import com.sida.electivefinalproject.service.UserService;
import com.sida.electivefinalproject.util.ConfigurationManager;
import com.sida.electivefinalproject.util.LoggerManager;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {
    private static final Logger logger = LoggerManager.getLogger(LoginCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("Called execute() in " + LoginCommand.class);

        Router router = new Router();

        UserService userService = (UserService) request.getServletContext().getAttribute("userService");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (email != null && password != null) {
            User user = userService.findByEmail(email);
            System.out.println("USER = " + user.getEmail());
            if (user.getPassword().equals(password)) {
                HttpSession session = request.getSession(true);
                session.setAttribute("user_id", user.getId());
                session.setAttribute("user_name", user.getFirstName() + " " + user.getLastName());
                session.setAttribute("user_email", user.getEmail());
                session.setAttribute("user_photo", user.getPhoto());
                session.setAttribute("user_role", user.getUserType().toString());
                setHomePath(user, router);
                System.out.println("!!! = " + router.getPagePath());
                return router;
            }
        }

        router.setPagePath(ConfigurationManager.getProperty("path.page.login"));

        return router;
    }

    private void setHomePath(User user, Router router) {
        if (user.getUserType().equals(UserType.STUDENT)) {
            router.setType(RouteType.REDIRECT);
            router.setPagePath("/controller?command=student_home");
        } else if (user.getUserType().equals(UserType.ADMIN)) {
            router.setType(RouteType.REDIRECT);
            router.setPagePath("/controller?command=admin_home");
        } else if  (user.getUserType().equals(UserType.TEACHER)){
            router.setType(RouteType.REDIRECT);
            router.setPagePath("/controller?command=teacher_home");
        }
    }
}
