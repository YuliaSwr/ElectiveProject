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

            <img src="../img/logo.png">
        </div>
        <div class="poster">
            <div class="banner">
                <img src="../img/poster.png" height="200px">
                <h2>Welcome to <span>eCoursie</span></h2>
            </div>
        </div>
        <div class="courses">
            <div class="title">
                My courses in progress
            </div>
            <div class="items">
                <div class="item">
                    <img src="../img/poster.png">
                    Operation System
                </div>
                <div class="item">
                    <img src="../img/poster.png">
                    Operation System
                </div>
                <div class="item">
                    <img src="../img/poster.png">
                    Operation System
                </div>
                <div class="item arrow">
                    <a href="#">see more -> </a>
                </div>
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
            <div class="cours-item all-courses">
                <div class="title">
                    All Courses
                </div>
                <div class="number">
                    20
                </div>
            </div>
            <div class="cours-item passed-courses">
                <div class="title">
                    Passed Courses
                </div>
                <div class="number">
                    11
                </div>
            </div>
        </div>
        <div class="profile-menu">
            <ul class="menu-list">
                <li><a href="#">Change profile info</a></li>
                <li><a href="#">Change password</a></li>
                <li><a href="#" class="delete">Delete account</a></li>
            </ul>
        </div>
    </div>
</div>

<ftg:footer/>
