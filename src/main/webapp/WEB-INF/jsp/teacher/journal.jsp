<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ftg" %>

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
        <jsp:directive.include file="/WEB-INF/css/journal.css" />
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
        <p class="title">Course Journal</p>
        <img src="${pageContext.request.contextPath}/images/default_photo.png" alt="">
    </div>

    <%-- DASHBOARD CONTENT --%>
    <div class="dash-content">
        <div class="activity">
            <div class="course-box">
                <ftg:course-photo course="${course}"/>
                <div class="course-info">
                    <div class="title">
                        <c:out value="${course.title}"/>
                    </div>
                    <div class="description">
                        <c:out value="${course.description}"/>
                    </div>
                    <div class="sphere">
                        <c:out value="${course.specialization.toString().toLowerCase()}"/>
                    </div>
                    <div class="duration">
                        <span>Duration: </span><c:out value="${course.durationInWeeks}"/> weeks
                    </div>
                </div>
            </div>
            <%-- TABLE --%>
            <div class="activity-data">
                <table>
                    <tr>
                        <th>Photo</th>
                        <th>First name</th>
                        <th>Last name</th>
                        <th>Gender</th>
                        <th>Email</th>
                        <th>Status</th>
                        <th>Enrollment date</th>
                        <th>Start date</th>
                        <th>End date</th>
                        <th class="btns">Grade</th>
                    </tr>
                    <c:forEach var="container" items="${studentContainers}">
                        <tr>
                            <td><img src="<c:out value="${container.student.photo}"/>"></td>
                            <td><c:out value="${container.student.firstName}"/></td>
                            <td><c:out value="${container.student.lastName}"/></td>
                            <td><c:out value="${container.student.gender}"/></td>
                            <td><c:out value="${container.student.email}"/></td>
                            <td><c:out value="${container.enrollment.progressStatus}"/></td>
                            <td><c:out value="${container.enrollment.enrollmentDate.toString()}"/></td>
                            <td><c:out value="${container.enrollment.startDate}"/></td>
                            <td><c:out value="${container.enrollment.endDate}"/></td>
                            <td class="btns">
                                <c:if test="${studentId eq null && container.enrollment.progressStatus eq 'COMPLETED' && container.finalGrade.grade eq 0}">
                                    <a href="controller?command=journal_page&id=${course.id}&student_id=${container.student.id}">Set
                                        Grade</a>
                                </c:if>
                                <c:if test="${studentId ne null && container.student.id eq studentId}">
                                    <form action="controller?command=set_grade" method="post">
                                        <input type="number" name="course_id" value="${course.id}" hidden>
                                        <input type="number" name="student_id" value="${container.student.id}" hidden>
                                        <input type="number" name="grade" min="1" max="100">
                                        <input type="submit">
                                    </form>
                                </c:if>
                                <c:if test="${container.finalGrade.grade ne 0}">
                                    <c:out value="${container.finalGrade.grade}"/>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</section>

<%-- FOOTER --%>
<ftg:footer/>
