package com.sida.electivefinalproject.command.admin;

import com.sida.electivefinalproject.command.Command;
import com.sida.electivefinalproject.command.Router;
import com.sida.electivefinalproject.entity.User;
import com.sida.electivefinalproject.service.UserService;
import com.sida.electivefinalproject.util.ConfigurationManager;
import com.sida.electivefinalproject.util.LoggerManager;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AdminHomePageCommand implements Command {
    private static final Logger logger = LoggerManager.getLogger(AdminHomePageCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("Called execute() in " + AdminHomePageCommand.class);

        Router router = new Router();
        router.setPagePath(ConfigurationManager.getProperty("path.page.admin.homePage"));

        return router;
    }
}
