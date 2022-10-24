package com.sida.electivefinalproject.filter;

import com.sida.electivefinalproject.command.CommandType;
import com.sida.electivefinalproject.command.Router;
import com.sida.electivefinalproject.entity.UserType;
import com.sida.electivefinalproject.util.ConfigurationManager;
import com.sida.electivefinalproject.util.LoggerManager;
import org.slf4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Security filter for delimitation of user accessible command
 */
@WebFilter(urlPatterns = {"/controller"}, filterName = "SecurityFilter")
public class SecurityFilter implements Filter {
    private static final Logger logger = LoggerManager.getLogger(SecurityFilter.class);

    private final List<CommandType> adminCommands = new ArrayList<>();
    private final List<CommandType> studentCommands = new ArrayList<>();
    private final List<CommandType> teacherCommands = new ArrayList<>();
    private final List<CommandType> guestCommands = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("Entry to filter: " + SecurityFilter.class);
        initAdminCommands();
        initStudentCommands();
        initTeacherCommands();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        final HttpSession session = req.getSession();

        String command = req.getParameter("command");

        if (session.getAttribute("user_role") == null) {
            if (!command.equals("login")) {
                redirectToLoginPage(session, command, req, resp);
                return;
            }
        } else if (session.getAttribute("user_role").toString().equalsIgnoreCase(UserType.STUDENT.toString())) {
            if (!studentCommands.contains(CommandType.valueOf(command.toUpperCase()))) {
                System.out.println(session.getAttribute("user_role"));
                System.out.println(CommandType.valueOf(command.toUpperCase()));
                forwardToErrorPage(session, command, req, resp);
                return;
            }
        } else if (session.getAttribute("user_role").toString().equalsIgnoreCase(UserType.TEACHER.toString())) {
            if (!teacherCommands.contains(CommandType.valueOf(command.toUpperCase()))) {
                System.out.println(session.getAttribute("user_role"));
                System.out.println(CommandType.valueOf(command.toUpperCase()));
                forwardToErrorPage(session, command, req, resp);
                return;
            }
        } else if (session.getAttribute("user_role").toString().equalsIgnoreCase(UserType.ADMIN.toString())) {
            if (!adminCommands.contains(CommandType.valueOf(command.toUpperCase()))) {
                forwardToErrorPage(session, command, req, resp);
                return;
            }
        }else {
            filterChain.doFilter(req, resp);
            return;
        }
        filterChain.doFilter(req, resp);
    }

    private void forwardToErrorPage(HttpSession session, String command, HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        logger.warn("Have no enough permits for the command (" + session.getAttribute("user_role") + ") '" + command + '\'');
        req.setAttribute("errorMessage", "You have no enough permissions to visit this page");
        req.getRequestDispatcher(ConfigurationManager.getProperty("path.page.error")).forward(req, resp);
    }

    private void redirectToLoginPage(HttpSession session, String command, HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        logger.warn("Have no enough permits for the command (" + session.getAttribute("user_role") + ") '" + command + '\'');
        logger.info("Redirect to login page");
        Router router = new Router();
        router.setPagePath(ConfigurationManager.getProperty("path.page.login"));
        resp.sendRedirect("/controller?command=login");
    }

    @Override
    public void destroy() {
        logger.info("Exit from filter: " + SecurityFilter.class);
    }


    private void initTeacherCommands() {
        teacherCommands.add(CommandType.LOGIN);
        teacherCommands.add(CommandType.TEACHER_HOME);
        teacherCommands.add(CommandType.MY_COURSES_TE);
        teacherCommands.add(CommandType.JOURNAL_PAGE);
        teacherCommands.add(CommandType.SET_GRADE);
        teacherCommands.add(CommandType.LOGOUT);
    }

    private void initStudentCommands() {
        teacherCommands.add(CommandType.LOGIN);
        studentCommands.add(CommandType.STUDENT_HOME);
        studentCommands.add(CommandType.ALL_COURSES);
        studentCommands.add(CommandType.ENROLL_COURSE);
        studentCommands.add(CommandType.START_COURSE);
        studentCommands.add(CommandType.LEAVE_COURSE);
        studentCommands.add(CommandType.MY_GRADES);
        studentCommands.add(CommandType.MY_COURSES_ST);
        studentCommands.add(CommandType.LOGOUT);
    }

    private void initAdminCommands() {
        teacherCommands.add(CommandType.LOGIN);
        adminCommands.add(CommandType.ADMIN_HOME);
        adminCommands.add(CommandType.SHOW_COURSES);
        adminCommands.add(CommandType.ADD_COURSE);
        adminCommands.add(CommandType.EDIT_COURSE);
        adminCommands.add(CommandType.DELETE_COURSE);

        adminCommands.add(CommandType.SHOW_TEACHERS);
        adminCommands.add(CommandType.ADD_TEACHER);
        adminCommands.add(CommandType.EDIT_TEACHER);
        adminCommands.add(CommandType.DELETE_TEACHER);

        adminCommands.add(CommandType.SHOW_STUDENTS);
        adminCommands.add(CommandType.ADD_STUDENT);
        adminCommands.add(CommandType.EDIT_STUDENT);
        adminCommands.add(CommandType.DELETE_STUDENT);

        adminCommands.add(CommandType.UNBLOCK_USER);

        adminCommands.add(CommandType.LOGOUT);
    }
}
