package com.sida.electivefinalproject.command;

import com.sida.electivefinalproject.util.LoggerManager;
import com.sida.electivefinalproject.util.MessageManager;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.Marker;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * Define command.
 *
 * @return the Optional<Command>
 */
public class CommandFactory {
    private final static Logger logger = LoggerManager.getLogger(CommandFactory.class);

    public Optional<Command> defineCommand(HttpServletRequest request) {
        logger.debug("Called defineCommand()");

        Optional<Command> current = Optional.empty();
        String action = request.getParameter(ConstantName.PARAM_NAME_COMMAND);
        if (action == null || action.isEmpty()) {
            return current;
        }
        try {
            CommandType currentType = CommandType.valueOf(action.toUpperCase());
            current = Optional.of(currentType.getCommand());
        } catch (IllegalArgumentException e) {
            request.setAttribute(ConstantName.ATTRIBUTE_WRONG_ACTION,
                    MessageManager.getProperty(ConstantName.MESSAGE_WRONG_ACTION));
            logger.error("Can't define command", e);
        }
        return current;
    }
}
