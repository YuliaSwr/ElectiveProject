<%@ tag pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="entity" required="true" rtexprvalue="true" type="java.lang.Object" %>
<%@ attribute name="delid" required="true" rtexprvalue="true" type="java.lang.String" %>

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

    <title>Students</title>

    <style>
        <jsp:directive.include file="/ADMIN/css/menu.css" />
        <jsp:directive.include file="/ADMIN/css/dashboard.css" />
        <jsp:directive.include file="/ADMIN/css/popup.css" />
    </style>
</head>

<c:if test="${entity eq null}">
<body>
</c:if>

<c:if test="${entity ne null}">
<body class="show">
</c:if>

<c:if test="${delid ne null}">
<body class="show-del">
</c:if>

