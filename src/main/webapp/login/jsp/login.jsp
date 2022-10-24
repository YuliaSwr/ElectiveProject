<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ftg" %>

<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Login</title>

    <!-- CSS -->
<%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/ADMIN/css/login.css"/>--%>
<%--    <link rel="stylesheet" href="css/login.css"/>--%>

    <style>
        <jsp:directive.include file="/login/css/login.css"/>
    </style>

    <!-- FONTS -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&family=Raleway:wght@300;400;500;600&display=swap"
          rel="stylesheet">

    <!-- ICONS -->
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0"/>
</head>
<body>
<div class="container">
    <div class="forms-container">
        <div class="mode">
            <span class="material-symbols-outlined">nightlight</span>
            <div class="mode-toggle">
                <span class="switch"></span>
            </div>
        </div>
        <div class="signin-signup">

            <%--LOGIN --%>
            <form action="controller" class="sign-in-form" method="post">
                <input type="text" name="command" value="login" hidden>
                <h2 class="title">Sign in</h2>
                <div class="input-field">
                    <i class="fas fa-user"></i>
                    <input type="text" name="email" placeholder="Email"/>
                </div>
                <div class="input-field">
                    <i class="fas fa-lock"></i>
                    <input type="password" name="password" placeholder="Password"/>
                </div>
                <input type="submit" value="Login" class="btn solid"/>
            </form>

            <%-- REGISTER --%>
            <form action="controller" class="sign-up-form">
                <input type="text" name="command" value="register" hidden>
                <h2 class="title">Sign up</h2>
                <div class="input-field">
                    <i class="fas fa-user"></i>
                    <input type="text" placeholder="First name"/>
                </div>
                <div class="input-field">
                    <i class="fas fa-user"></i>
                    <input type="text" placeholder="Last name"/>
                </div>
                <div class="input-field">
                    <i class="fas fa-user"></i>
                    <select name="gender">
                        <option value="" hidden>Select gender</option>
                        <option value="F">Female</option>
                        <option value="M">Male</option>
                    </select>
                </div>
                <div class="input-field">
                    <i class="fas fa-envelope"></i>
                    <input type="email" placeholder="Email"/>
                </div>
                <div class="input-field">
                    <i class="fas fa-lock"></i>
                    <input type="password" placeholder="Password"/>
                </div>
                <input type="submit" class="btn" value="Sign up"/>
            </form>
        </div>
    </div>

    <div class="panels-container">
        <div class="panel left-panel">
            <div class="content">
                <p class="title">eCoursie</p>
                <h3>New Student?</h3>
                <p>
                    Register with your email to become a part of our educational platform
                </p>
                <button class="btn transparent" id="sign-up-btn">
                    Sign up!
                </button>
            </div>
            <img src="../img/poster.png" class="image" alt=""/>
            <img src="<c:url value="/images/poster.png"/>" class="image" alt=""/>
        </div>
        <div class="panel right-panel">
            <div class="content">
                <p class="title">eCoursie</p>
                <h3>One of us?</h3>
                <p>
                    Sign in with your email if you are already registered
                </p>
                <button class="btn transparent" id="sign-in-btn">
                    Sign in
                </button>
            </div>
            <img src="<c:url value="/images/student.svg"/>" class="image" alt=""/>
        </div>
    </div>
</div>

<!-- JS -->
<script src="https://kit.fontawesome.com/64d58efce2.js"
        crossorigin="anonymous"></script>
<%--<script src="${pageContext.request.contextPath}/login/js/app.js"></script>--%>
<%--<script src="${pageContext.request.contextPath}/login/js/toggle.js"></script>--%>

<script>
    <jsp:directive.include file="/login/js/app.js"/>
    <jsp:directive.include file="/login/js/toggle.js"/>
</script>
</body>
</html>
