<%@ tag pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="user_role" required="true" rtexprvalue="true" type="java.lang.String" %>

<nav>
    <div class="logo-name">
        <div class="logo-image">
            <span class="material-symbols-outlined">school</span>
        </div>

        <span class="logo_name">eCoursie</span>
    </div>

    <div class="menu-items">
        <c:if test="${user_role eq 'ADMIN'}">
            <ul class="nav-links">
                <li><a href="controller?command=admin_home">
                    <span class="material-symbols-outlined">home</span>
                    <span class="link-name">Home</span>
                </a></li>
                <li><a href="controller?command=show_courses">
                    <span class="material-symbols-outlined">backup_table</span>
                    <span class="link-name">Courses</span>
                </a></li>
                <li><a href="controller?command=show_teachers">
                    <span class="material-symbols-outlined">badge</span>
                    <span class="link-name">Teachers</span>
                </a></li>
                <li><a href="controller?command=show_students">
                    <span class="material-symbols-outlined">group</span>
                    <span class="link-name">Students</span>
                </a></li>
            </ul>
        </c:if>
        <c:if test="${user_role eq 'STUDENT'}">
            <ul class="nav-links">
                <li><a href="controller?command=student_home">
                    <span class="material-symbols-outlined">home</span>
                    <span class="link-name">Home</span>
                </a></li>
                <li><a href="controller?command=my_courses_st">
                    <span class="material-symbols-outlined">backup_table</span>
                    <span class="link-name">My Courses</span>
                </a></li>
                <li><a href="controller?command=all_courses">
                    <span class="material-symbols-outlined">language</span>
                    <span class="link-name">All Courses</span>
                </a></li>
                <li><a href="controller?command=my_grades">
                    <span class="material-symbols-outlined">task_alt</span>
                    <span class="link-name">My Grades</span>
                </a></li>
            </ul>
        </c:if>
        <c:if test="${user_role eq 'TEACHER'}">
            <ul class="nav-links">
                <li><a href="controller?command=teacher_home">
                    <span class="material-symbols-outlined">home</span>
                    <span class="link-name">Home</span>
                </a></li>
                <li><a href="controller?command=my_courses_te">
                    <span class="material-symbols-outlined">backup_table</span>
                    <span class="link-name">My Courses</span>
                </a></li>
            </ul>
        </c:if>

        <ul class="logout-mode">
            <li><a href="controller?command=logout">
                <span class="material-symbols-outlined">logout</span>
                <span class="link-name">Logout</span>
            </a></li>

            <li class="mode">
                <a href="#">
                    <span class="material-symbols-outlined">nightlight</span>
                    <span class="link-name">Dark Mode</span>
                </a>

                <div class="mode-toggle">
                    <span class="switch"></span>
                </div>
            </li>
        </ul>
    </div>
</nav>
