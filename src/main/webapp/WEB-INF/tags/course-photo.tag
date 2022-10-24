<%@ tag pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="course" required="true" rtexprvalue="true" type="com.sida.electivefinalproject.entity.Course" %>

<div class="course-photo">
    <c:if test="${course.specialization eq 'TECHNOLOGY'}">
        <img src="${pageContext.request.contextPath}/images/technology.png" alt="">
    </c:if>
    <c:if test="${course.specialization eq 'HUMANITIES'}">
        <img src="${pageContext.request.contextPath}/images/humanities.png" alt="">
    </c:if>
    <c:if test="${course.specialization eq 'SCIENCE'}">
        <img src="${pageContext.request.contextPath}/images/science.png" alt="">
    </c:if>
    <c:if test="${course.specialization eq 'ECONOMY'}">
        <img src="${pageContext.request.contextPath}/images/ecomony.png" alt="">
    </c:if>
    <c:if test="${course.specialization eq 'ART'}">
        <img src="${pageContext.request.contextPath}/images/art.png" alt="">
    </c:if>
</div>
