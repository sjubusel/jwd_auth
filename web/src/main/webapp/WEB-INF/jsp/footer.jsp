<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:choose>
    <c:when test="${sessionScope.language eq null}">
        <fmt:setLocale value="ru_RU"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="${sessionScope.language}"/>
    </c:otherwise>
</c:choose>

<fmt:setBundle basename="jspResources" var="jspMessages"/>

<br>

<div class="container-fluid justify-content-center dropdown-menu d-flex bg-light">
    <div class="p-2 justify-content-center flex-fill bd-highlight">
        <fmt:message bundle="${jspMessages}" key="footer.copyrightRecord"/>
    </div>
    <div class="p-2 flex-fill bd-highlight">
        <fmt:message bundle="${jspMessages}" key="footer.contactNumber"/>
    </div>
</div>