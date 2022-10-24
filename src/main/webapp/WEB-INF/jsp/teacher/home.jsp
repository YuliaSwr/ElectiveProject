<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ftg" %>

<ftg:header-home role="${sessionScope.user_role}"/>

<ftg:menu user_role="${sessionScope.user_role}"/>

<div class="dashboard">
    <div class="main-content">
        <div class="top">
            <i class="uil uil-bars sidebar-toggle"></i>
            <div class="title">
                Home
            </div>
            <div class="search-box">
                <i class="uil uil-search"></i>
                <input type="text" placeholder="Search">
            </div>

            <div></div>
        </div>
        <div class="poster">
            <div class="banner">
                <img src="${pageContext.request.contextPath}/images/poster.png" height="200px">
                <h2>Welcome to <span>eCoursie</span></h2>
            </div>
        </div>
    </div>

    <div class="profile">
        <div class="profile-info">
            <div class="profile-image">
                <img src="${sessionScope.user_photo}" alt="">
            </div>
            <div class="name-status">
                <div class="name">
                    ${sessionScope.user_name}
                </div>
                <div class="email">
                    ${sessionScope.user_email}
                </div>
                <div class="status">
                    ${sessionScope.user_role}
                </div>
            </div>
        </div>
        <div class="courses-stat">
            <div class="cours-item all-courses t">
                <div class="title">
                    All My Courses
                </div>
                <div class="number">
                    <c:out value="${allCourses}"/>
                </div>
            </div>
        </div>
        <div class="profile-menu">
            <ul class="menu-list">
                <li><a href="#">Change profile photo</a></li>
                <li><a href="#">Change password</a></li>
                <li><a href="#" class="delete">Delete account</a></li>
            </ul>
        </div>
    </div>
</div>

<ftg:footer/>
