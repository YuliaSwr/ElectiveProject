package com.sida.electivefinalproject.command.admin.student;

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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class EditStudentCommand implements Command {
    private static final Logger logger = LoggerManager.getLogger(EditStudentCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("Called execute() in " + EditStudentCommand.class);

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
                UserType.STUDENT,
                AccountStatus.ACTIVE,
                request.getParameter("photo")
        );
        userService.updateUser(user);

        router.setType(RouteType.REDIRECT);
        router.setPagePath(ConfigurationManager.getProperty("path.page.redirect.showStudents"));

        return router;
    }

    public String getPhoto(HttpServletRequest request, int id) {
        try {
            String name = "";
            if (request.getPart("photo") == null || request.getParameter("photo") == null) {
                System.out.println("NULLL");
            }
            Part filePart = request.getPart("photo");

            InputStream is = filePart.getInputStream();
            String fileName = id + "_photo";
            String address = request.getServletContext().getRealPath("/images");
            Files.copy(is,
                    Paths.get(address + "/" + fileName),
                    StandardCopyOption.REPLACE_EXISTING);
            System.out.println("/images/" + fileName);
            name = "/images/" + fileName;
            return name;
        } catch (IOException | ServletException e) {
            return request.getParameter("def_photo");
        }
    }
}
