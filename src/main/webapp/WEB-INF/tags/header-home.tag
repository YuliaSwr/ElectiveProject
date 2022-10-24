<%@ tag pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="role" required="true" rtexprvalue="true" type="java.lang.String" %>

<html lang="en">
<head>
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

    <title>Home</title>

    <style>
        <jsp:directive.include file="/WEB-INF/css/menu.css" />
    </style>

    <c:if test="${role eq 'ADMIN'}">
        <style>
            <jsp:directive.include file="/ADMIN/css/home.css" />
            <jsp:directive.include file="/ADMIN/css/popup.css" />
        </style>
    </c:if>
    <c:if test="${role eq 'STUDENT'}">
        <style>
            <jsp:directive.include file="/STUDENT/css/home.css" />
        </style>
    </c:if>
    <c:if test="${role eq 'TEACHER'}">
        <style>
            <jsp:directive.include file="/TEACHER/css/home.css" />
        </style>
    </c:if>
</head>
<body>
