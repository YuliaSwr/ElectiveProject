package com.sida.electivefinalproject.command.admin.course;

import com.sida.electivefinalproject.command.Command;
import com.sida.electivefinalproject.command.Router;
import com.sida.electivefinalproject.util.LoggerManager;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class FindCourseCommand implements Command {
    private static final Logger logger = LoggerManager.getLogger(FindCourseCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("Called execute() in " + FindCourseCommand.class);
        return null;
    }
}
