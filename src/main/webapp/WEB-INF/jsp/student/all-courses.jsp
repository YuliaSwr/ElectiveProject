<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ftg" %>

<%--<ftg:header entity="${null}" delid="${null}"/>--%>

<html lang="uk">
<head>
    <meta charset="UTF-8">
    <%--    <!-- CSS -->--%>
    <%--    <link rel="stylesheet" href="../../ADMIN/css/menu.css">--%>
    <%--    <link rel="stylesheet" href="../../ADMIN/css/dashboard.css">--%>
    <%--    <link rel="stylesheet" href="../../ADMIN/css/popup.css">--%>

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

    <title>All courses</title>

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
        <p class="title">All Courses</p>
        <img src="${sessionScope.user_photo}" alt="">
    </div>

    <%-- DASHBOARD CONTENT --%>
    <div class="dash-content">
        <div class="activity">
            <%-- TOOLBAR --%>
            <div class="toolbar">
                <div class="search-box">
                    <i class="uil uil-search"></i>
                    <input type="text" placeholder="Search here...">
                </div>
                <div class="filter">
                    <div class="title">Filter by:</div>
                    <div class="items">
                        <select>
                            <option value="" hidden>Specialization</option>
                            <option value="">ART</option>
                            <option value="">ART</option>
                            <option value="">ART</option>
                        </select>
                    </div>
                </div>
                <div class="sort">
                    <div class="title">Sort by:</div>
                    <div class="items">
                        <a href="#">Name</a>
                        <a href="#">Duration</a>
                        <a href="#">Students number</a>
                    </div>
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
                            <div class="button register">
                                <a href="controller?command=enroll_course&id=${container.course.id}">Register</a>
                            </div>
                            <div class="students-number">
                                <span>Registered students:</span> <c:out value="${container.studentsRegistered}"/>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</section>

<%-- FOOTER --%>
<ftg:footer/>
