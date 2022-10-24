<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ftg" %>

<%-- HEADER --%>
<ftg:header entity="${course}" delid="${delid}"/>

<%-- MENU --%>
<ftg:menu user_role="${sessionScope.user_role}"/>

<%-- DASHBOARD --%>
<section class="dashboard">

    <%-- TOP HEADER --%>
    <div class="top">
        <i class="uil uil-bars sidebar-toggle"></i>
        <p class="title">Courses</p>
        <img src="${user.photo}" alt="">
    </div>

    <%-- DASHBOARD CONTENT --%>
    <div class="dash-content">
        <div class="overview">

            <%-- SECTION TITLE --%>
            <div class="title">
                <span class="material-symbols-outlined">leaderboard</span>
                <p class="text">Statistic</p>
            </div>

            <%-- INFORMATIONAL BOXES --%>
            <div class="boxes">
                <div class="box box1">
                    <span class="material-symbols-outlined">backup_table</span>
                    <span class="text">All courses</span>
                    <span class="number"><c:out value="${numALL}"/></span>
                </div>
                <div class="box box2-item">
                    <span class="material-symbols-outlined">terminal</span>
                    <span class="text">Technology</span>
                    <span class="number"><c:out value="${numIT}"/></span>
                </div>
                <div class="box box2-item">
                    <span class="material-symbols-outlined">biotech</span>
                    <span class="text">Science</span>
                    <span class="number"><c:out value="${numSCIENCE}"/></span>
                </div>
                <div class="box box2-item">
                    <span class="material-symbols-outlined">palette</span>
                    <span class="text">Art</span>
                    <span class="number"><c:out value="${numART}"/></span>
                </div>
                <div class="box box2-item">
                    <span class="material-symbols-outlined">language</span>
                    <span class="text">Economy</span>
                    <span class="number"><c:out value="${numECONOMY}"/></span>
                </div>
                <div class="box box2-item">
                    <span class="material-symbols-outlined">insert_chart</span>
                    <span class="text">Humanities</span>
                    <span class="number"><c:out value="${numHUMANITIES}"/></span>
                </div>
            </div>
        </div>

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
                        <th>Name</th>
                        <th>Description</th>
                        <th>Sphere</th>
                        <th>Duration</th>
                        <th>Teacher</th>
                        <th class="btns">Edit</th>
                        <th class="btns">Delete</th>
                    </tr>
                    <c:forEach var="course" items="${coursesList}">
                        <tr>
                            <td><c:out value="${course.id}"/></td>
                            <td><c:out value="${course.title}"/></td>
                            <td><c:out value="${course.description}"/></td>
                            <td><c:out value="${course.specialization}"/></td>
                            <td><c:out value="${course.durationInWeeks}"/></td>
                            <td><c:out value="${course.teacher}"/></td>
                            <td>
                                <a href="controller?command=show_courses&id=${course.id}" class="edit-btn"><span
                                        class="material-symbols-outlined">edit_square</span></a>
                            </td>
                            <td><a href="controller?command=show_courses&del_id=${course.id}" class="delete-btn"><span
                                class="material-symbols-outlined">disabled_by_default</span></a></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</section>

<%-- POPUP TO ADD OR EDIT COURSE --%>
<ftg:course-popup course="${course}"  teachersList="${teachersList}" teacher="${teacher}"/>

<%-- POPUP TO DELETE TEACHER --%>
<c:if test="${delid ne null}">
<ftg:delete-popup command="delete_course" id="${delid}" />
</c:if>

<%-- FOOTER --%>
<ftg:footer />
