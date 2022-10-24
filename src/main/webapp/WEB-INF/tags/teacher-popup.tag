<%@ tag pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="teacher" required="true" rtexprvalue="true" type="com.sida.electivefinalproject.entity.User" %>

<div class="popup-outer">
    <div class="popup-box">
        <a href="controller?command=show_teachers"><span class="material-symbols-outlined close-button"
                                                         onclick="closePopup()">close</span></a>
        <div class="popup-content">
            <div class="popup-title">
                <c:if test="${teacher eq null}">
                    Add new student
                </c:if>
                <c:if test="${teacher ne null}">
                    Edit student
                </c:if>
            </div>
            <form action="controller" method="post" enctype="multipart/form-data">
                <c:if test="${teacher eq null}">
                    <input type="text" name="command" value="add_teacher" hidden>
                </c:if>
                <c:if test="${teacher ne null}">
                    <input type="text" name="command" value="edit_teacher" hidden>
                    <input type="text" name="photo" value="${teacher.photo}" hidden/>
                </c:if>
                <div class="popup-form">
                    <c:if test="${teacher ne null}">
                        <div class="input-block">
                            <label>Id</label>
                            <input type="text" name="id" class="form-input read-only" value="${teacher.id}" readonly>
                        </div>
                    </c:if>
                    <div class="input-block">
                        <label>First name</label>
                        <input type="text" name="firstName" value="${teacher.firstName}" required>
                    </div>
                    <div class="input-block">
                        <label>Last name</label>
                        <input type="text" name="lastName" value="${teacher.lastName}" required>
                    </div>
                    <div class="input-block">
                        <label>Gender</label>
                        <select name="gender" required>
                            <c:if test="${teacher ne null}">
                                <option value="${teacher.gender}" hidden>${teacher.gender}</option>
                            </c:if>
                            <c:if test="${teacher eq null}">
                                <option value="" hidden>Select gender</option>
                            </c:if>
                            <option value="F">FEMALE</option>
                            <option value="M">MALE</option>
                        </select>
                    </div>
                    <div class="input-block">
                        <label>Email</label>
                        <input value="${teacher.email}" type="email" name="email" required>
                    </div>
                    <c:if test="${teacher ne null}">
                        <input value="${teacher.password}" type="text" name="password" hidden>
                    </c:if>
                    <c:if test="${teacher eq null}">
                        <div class="input-block">
                            <label>Password</label>
                            <input type="text" name="password" value="${teacher.lastName}" required>
                        </div>
                    </c:if>
                    <c:if test="${teacher ne null}">
                        <input value="${teacher.accountStatus}" type="text" name="accountStatus" hidden>
                    </c:if>
                    <c:if test="${teacher eq null}">
                        <input value="ACTIVE" type="text" name="accountStatus" hidden>
                    </c:if>
                </div>
                <div class="submit-button">
                    <input type="submit" value="Submit" onsubmit="closePopup()">
                </div>
            </form>
        </div>
    </div>
</div>
