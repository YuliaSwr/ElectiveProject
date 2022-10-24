package com.sida.electivefinalproject.controller;

import com.sida.electivefinalproject.command.*;
import com.sida.electivefinalproject.util.ConfigurationManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
@WebServlet(name = "controller", value = "/controller")
public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CommandFactory commandFactory = new CommandFactory();
        Optional<Command> commandOptional = commandFactory.defineCommand(request);

        if (commandOptional.isPresent()) {
            Command command = commandOptional.get();
            Router router = command.execute(request);
            if (router.getPagePath() != null) {
                if (router.getType().equals(RouteType.FORWARD)) {
                    RequestDispatcher dispatcher = request.getRequestDispatcher(router.getPagePath());
                    dispatcher.forward(request, response);
                } else {
                    response.sendRedirect(request.getContextPath() + router.getPagePath());
                }
            } else {
                response.sendRedirect(request.getContextPath() +
                        ConfigurationManager.getProperty(ConstantName.JSP_ERROR));
            }
        }
    }
}

