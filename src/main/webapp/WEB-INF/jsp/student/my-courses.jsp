<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ftg" %>

<%--<ftg:header entity="${null}" delid="${null}"/>--%>

<html lang="uk">
<head>
    <meta charset="UTF-8">
<!-- FONTS -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&family=Raleway:wght@300;400;500;600&display=swap"
          rel="stylesheet">

    <!-- ICONS -->
    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,1,0"/>
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0"/>

    <title>My courses</title>

    <style>
        <jsp:directive.include file="/WEB-INF/css/menu.css" />
        <jsp:directive.include file="/WEB-INF/css/dashboard.css" />
        <jsp:directive.include file="/WEB-INF/css/all-courses.css" />
    </style>
</head>
<body>
<%-- MENU --%>
<ftg:menu user_role="${sessionScope.user_role}"/>

<%-- DASHBOARD --%>
<section class="dashboard">
    <%-- TOP HEADER --%>
    <div class="top">
        <i class="uil uil-bars sidebar-toggle"></i>
        <p class="title">My Courses</p>
        <img src="${sessionScope.user_photo}" alt="">
    </div>

    <%-- DASHBOARD CONTENT --%>
    <div class="dash-content">
        <div class="activity">
            <div class="content">
                <div class="tabs">
                    <div class="tab">
                        <a href="controller?command=my_courses_st">All</a>
                    </div>
                    <div class="tab">
                        <a href="controller?command=my_courses_st&type=enrolled">Enrolled</a>
                    </div>
                    <div class="tab">
                        <a href="controller?command=my_courses_st&type=started">Started</a>
                    </div>
                    <div class="tab">
                        <a href="controller?command=my_courses_st&type=completed">Completed</a>
                    </div>
                </div>
                <div class="list">
                    <c:forEach var="container" items="${courseContainers}">
                        <div class="course-box">
                            <ftg:course-photo course="${container.course}"/>
                            <div class="course-info">
                                <div class="title">
                                    <c:out value="${container.course.title}"/>
                                </div>
                                <div class="description">
                                    <c:out value="${container.course.description}"/>
                                </div>
                                <div class="sphere">
                                    <c:out value="${container.course.specialization.toString().toLowerCase()}"/>
                                </div>
                                <div class="teacher">
                                    <b>Teacher:</b> <c:out value="${container.teacherName}"/>
                                </div>
                            </div>
                            <div class="course-num">
                                <div class="duration">
                                    <c:out value="${container.course.durationInWeeks}"/> weeks
                                </div>
                                <c:if test="${container.enrollment.startDate ne null}">
                                    <div class="date">
                                        <span>Start date: </span><c:out value="${container.enrollment.startDate}"/>
                                    </div>
                                </c:if>
                                <c:if test="${container.enrollment.endDate ne null}">
                                    <div class="date">
                                        <span>End date: </span><c:out value="${container.enrollment.endDate}"/>
                                    </div>
                                </c:if>
                                <c:if test="${container.enrollment.startDate eq null}">
                                    <div class="button start-button">
                                        <a href="controller?command=start_course&id=${container.course.id}">Start</a>
                                    </div>
                                </c:if>
                                <div class="button leave-button">
                                    <a href="controller?command=leave_course&id=${container.course.id}">Leave</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</section>

<%-- FOOTER --%>
<ftg:footer/>
