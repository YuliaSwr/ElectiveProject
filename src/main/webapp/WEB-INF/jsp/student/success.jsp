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
        <jsp:directive.include file="/WEB-INF/css/success.css" />
    </style>


</head>

<body>

<c:set var="myContext" value="${pageContext.request.contextPath}"/>

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
    <div class="dash-content success">
        <img src="${pageContext.request.contextPath}/images/Astronaut.png">
        <p>You are successfully enrolled to chosen course</p>
    </div>
</section>

<%-- FOOTER --%>
<ftg:footer/>
