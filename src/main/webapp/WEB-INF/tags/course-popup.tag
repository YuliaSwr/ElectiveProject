<%@ tag pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="course" required="true" rtexprvalue="true" type="com.sida.electivefinalproject.entity.Course" %>
<%@ attribute name="teacher" required="true" rtexprvalue="true" type="com.sida.electivefinalproject.entity.User" %>
<%@ attribute name="teachersList" required="true" rtexprvalue="true" type="java.util.List" %>


<div class="popup-outer">
    <div class="popup-box">
        <a href="controller?command=show_courses">
            <span class="material-symbols-outlined close-button" onclick="closePopup()">close</span>
        </a>
        <div class="popup-content">
            <div class="popup-title">
                <c:if test="${course eq null}">Add new course</c:if>
                <c:if test="${course ne null}">Edit course</c:if>
            </div>
            <form action="controller" method="post">
                <c:if test="${course eq null}"><input type="text" name="command" value="add_course" hidden></c:if>
                <c:if test="${course ne null}"><input type="text" name="command" value="edit_course" hidden></c:if>
                <div class="popup-form">
                    <c:if test="${course ne null}">
                        <div class="input-block">
                            <label>Id</label>
                            <input type="text" name="id" class="form-input read-only" value="${course.id}" readonly>
                        </div>
                    </c:if>
                    <div class="input-block">
                        <label>Title</label>
                        <input type="text" name="title" class="form-input" value="${course.title}" required>
                    </div>
                    <div class="input-block">
                        <label>Description</label>
                        <input type="text" name="description" class="form-input" value="${course.description}" required>
                    </div>
                    <div class="input-block">
                        <label>Specialization</label>
                        <select name="specialization" required>
                            <c:if test="${course ne null}">
                                <option value="${course.specialization}" hidden>${course.specialization}</option>
                            </c:if>
                            <c:if test="${course eq null}">
                                <option value="" hidden>Select specialization</option>
                            </c:if>
                            <option value="1">IT</option>
                            <option value="2">ART</option>
                            <option value="3">SCIENCE</option>
                            <option value="4">ECONOMY</option>
                            <option value="5">HUMANITIES</option>
                        </select>
                    </div>
                    <div class="input-block">
                        <label>Duration in weeks</label>
                        <input value="${course.durationInWeeks}" type="number" name="durationInWeeks" min="1" class="form-input" required>
                    </div>
                    <div class="input-block">
                        <label>Teacher</label>
                        <select name="teacherId" required>
                            <c:if test="${course ne null}">
                                <option value="${course.teacher}" hidden>${teacher.email}</option>
                            </c:if>
                            <c:if test="${course eq null}">
                                <option value="" hidden>Select teacher</option>
                            </c:if>
                            <c:forEach var="teacher" items="${teachersList}">
                                <option value="${teacher.id}">${teacher.email}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="submit-button">
                    <input type="submit" value="Submit" onsubmit="closePopup()">
                </div>
            </form>
        </div>
    </div>
</div>
