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
    <img src="${sessionScope.user_photo}" alt="">
  </div>

  <%-- DASHBOARD CONTENT --%>
  <div class="dash-content">
    <div class="activity">
      <%-- TABLE --%>
      <div class="activity-data">
        <table>
          <tr>
            <th>Course</th>
            <th>Teacher</th>
            <th>Specialization</th>
            <th>Grade</th>
          </tr>
          <c:forEach var="container" items="${gradeContainers}">
            <tr>
              <td><c:out value="${container.course.title}"/></td>
              <td><c:out value="${container.teacherName}"/></td>
              <td><c:out value="${container.course.specialization.toString().toLowerCase()}"/></td>
              <td><c:out value="${container.finalGrade.grade}"/></td>
            </tr>
          </c:forEach>
        </table>
      </div>
    </div>
  </div>
</section>

<%-- FOOTER --%>
<ftg:footer/>
