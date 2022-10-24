<%@ tag pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="command" required="true" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="id" required="true" rtexprvalue="true" type="java.lang.String" %>

<div class="popup-outer delete-popup">
    <div class="popup-box">
        <div class="popup-content">
            <div class="popup-title">
                 Delete record with id = ${id}?
            </div>
            <form action="controller" method="post">
                <input type="text" name="command" value="${command}" hidden>
                <input type="text" name="id" value="${id}" hidden>
                <div class="popup-form">
                    <div class="submit-button">
                        <input type="submit" value="Submit" onsubmit="closePopup()">
                    </div>
                    <div class="cancel-button">
                        <input type="button" value="Cancel" onclick="closePopup()">
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
