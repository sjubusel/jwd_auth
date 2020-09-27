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

<fmt:setBundle basename="jspResourses" var="jspMessages"/>

<br>

<div class="container-fluid justify-content-center dropdown-menu d-flex bg-light">
    <div class="p-2 justify-content-center flex-fill bd-highlight">
        © УО "Инновационная больница
        "Шабаны" г.Минска, 2020
    </div>
    <div class="p-2 flex-fill bd-highlight">Телефон для записи: +375 (29)
        600-00-01
    </div>
</div>