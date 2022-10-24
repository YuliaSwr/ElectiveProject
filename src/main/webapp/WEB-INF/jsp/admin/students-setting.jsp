<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ftg" %>

<%-- HEADER --%>
<ftg:header entity="${student}" delid="${delid}"/>

<%-- MENU --%>
<ftg:menu user_role="${sessionScope.user_role}"/>

<%-- DASHBOARD --%>
<section class="dashboard">

    <%-- TOP HEADER --%>
    <div class="top">
        <i class="uil uil-bars sidebar-toggle"></i>
        <p class="title">Students</p>
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
                    <c:forEach var="student" items="${studentsList}">
                        <tr>
                            <td><c:out value="${student.id}"/></td>
                            <td><img src="${student.photo}" alt=""></td>
                            <td><c:out value="${student.firstName}"/>
                            <td><c:out value="${student.lastName}"/></td>
                            <td><c:out value="${student.gender}"/></td>
                            <td><c:out value="${student.email}"/></td>
                            <c:if test="${student.accountStatus.toString() ne 'BLOCKED'}">
                                <td><c:out value="${student.accountStatus.toString()}"/></td>
                            </c:if>
                            <c:if test="${student.accountStatus.toString() eq 'BLOCKED'}">
                                <td><a href="controller?command=unblock_user&id=${student.id}" class="unblock-btn"><span>BLOCKED</span></a></td>
                            </c:if>
                            <td><a href="controller?command=show_students&id=${student.id}" class="edit-btn"><span class="material-symbols-outlined">edit_square</span></a></td>
                            <td><a href="controller?command=show_students&del_id=${student.id}" class="delete-btn"><span
                                    class="material-symbols-outlined">disabled_by_default</span></a></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</section>

<%-- POPUP TO ADD OR EDIT STUDENT --%>
<ftg:student-popup student="${student}"/>

<%-- POPUP TO DELETE STUDENT --%>
<c:if test="${delid ne null}">
    <ftg:delete-popup command="delete_student" id="${delid}" />
</c:if>

<%-- FOOTER --%>
<ftg:footer/>
