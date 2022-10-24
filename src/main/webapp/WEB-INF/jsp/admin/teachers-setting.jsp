<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ftg" %>

<%-- HEADER --%>
<ftg:header entity="${teacher}" delid="${delid}"/>

<%-- MENU --%>
<ftg:menu user_role="${sessionScope.user_role}"/>

<%-- DASHBOARD --%>
<section class="dashboard">

    <%-- TOP HEADER --%>
    <div class="top">
        <i class="uil uil-bars sidebar-toggle"></i>
        <p class="title">Teachers</p>
        <img src="${user.photo}" alt="">
    </div>

    <%-- DASHBOARD CONTENT --%>
    <div class="dash-content">
        <div class="activity">
            <%-- SECTION TITLE --%>
            <div class="title">
                <span class="material-symbols-outlined">database</span>
                <p class="text">All Data</p>
            </div>

            <%-- TOOLBAR --%>
            <div class="toolbar">
                <div class="search-box">
                    <i class="uil uil-search"></i>
                    <input type="text" placeholder="Search here...">
                </div>

                <div class="add-box">
                    <a href="#" onclick="showPopup()">Add new</a>
                </div>
            </div>

            <%-- TABLE --%>
            <div class="activity-data">
                <table>
                    <tr>
                        <th>id</th>
                        <th>Photo</th>
                        <th>First name</th>
                        <th>Last name</th>
                        <th>Gender</th>
                        <th>Email</th>
                        <th>Account status</th>
                        <th class="btns">Edit</th>
                        <th class="btns">Delete</th>
                    </tr>
                    <c:forEach var="teacher" items="${teachersList}">
                        <tr>
                            <td><c:out value="${teacher.id}"/></td>
                            <td><img src="${teacher.photo}" alt=""></td>
                            <td><c:out value="${teacher.firstName}"/>
                            <td><c:out value="${teacher.lastName}"/></td>
                            <td><c:out value="${teacher.gender}"/></td>
                            <td><c:out value="${teacher.email}"/></td>
                            <c:if test="${teacher.accountStatus.toString() ne 'BLOCKED'}">
                                <td><c:out value="${teacher.accountStatus.toString()}"/></td>
                            </c:if>
                            <c:if test="${teacher.accountStatus.toString() eq 'BLOCKED'}">
                                <td><a href="controller?command=unblock_user&id=${teacher.id}"
                                       class="unblock-btn"><span>BLOCKED</span></a>
                                </td>
                            </c:if>
                            <td>
                                <a href="controller?command=show_teachers&id=${teacher.id}" class="edit-btn"><span
                                        class="material-symbols-outlined">edit_square</span></a>
                            </td>
                            <td><a href="controller?command=show_teachers&del_id=${teacher.id}" class="delete-btn"><span
                                    class="material-symbols-outlined">disabled_by_default</span></a></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</section>

<%-- POPUP TO ADD OR EDIT TEACHER --%>
<ftg:teacher-popup teacher="${teacher}"/>

<%-- POPUP TO DELETE TEACHER --%>
<c:if test="${delid ne null}">
    <ftg:delete-popup command="delete_teacher" id="${delid}" />
</c:if>

<%-- FOOTER --%>
<ftg:footer />
