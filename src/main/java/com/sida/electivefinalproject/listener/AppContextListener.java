package com.sida.electivefinalproject.listener;

import com.sida.electivefinalproject.service.CourseService;
import com.sida.electivefinalproject.service.EnrollmentService;
import com.sida.electivefinalproject.service.FinalGradeService;
import com.sida.electivefinalproject.service.UserService;
import com.sida.electivefinalproject.service.impl.CourseServiceImpl;
import com.sida.electivefinalproject.service.impl.EnrollmentServiceImpl;
import com.sida.electivefinalproject.service.impl.FinalGradeServiceImpl;
import com.sida.electivefinalproject.service.impl.UserServiceImpl;

import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener, ServletContextAttributeListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        CourseService courseService = new CourseServiceImpl();
        sce.getServletContext().setAttribute("courseService", courseService);

        UserService userService = new UserServiceImpl();
        sce.getServletContext().setAttribute("userService", userService);

        EnrollmentService enrollmentService = new EnrollmentServiceImpl();
        sce.getServletContext().setAttribute("enrollmentService", enrollmentService);

        FinalGradeService finalGradeService = new FinalGradeServiceImpl();
        sce.getServletContext().setAttribute("finalGradeService", finalGradeService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
