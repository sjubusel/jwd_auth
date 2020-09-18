<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>

<div class="alert alert-info" role="alert">
    Сайт находится в стадии разработки. Приносим свои извинения.
</div>

<div class="nav container-fluid nav-pills bg-light">
    <div class="nav-item">
        <a class="nav-link active"
           href="${pageContext.request.contextPath}/main">Главная</a>
    </div>
    <div class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/main">Для
            пациентов</a>
    </div>
    <div class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/main">Для
            персонала</a>
    </div>
    <div class="nav-item flex-grow-1">
        <a class="nav-link" href="${pageContext.request.contextPath}/main">Контакты</a>
    </div>

    <c:choose>
        <c:when test="${sessionScope.authUser eq null}">
            <div class="nav-item">
                <a class="nav-link"
                   href="${pageContext.request.contextPath}/login">
                    Войти</a>
            </div>
            <div class="nav-item">
                <a class="nav-link"
                   href="${pageContext.request.contextPath}/register">
                    Регистрация</a>
            </div>
        </c:when>
        <c:otherwise>
            <div class="nav-item">
                <a class="nav-link"
                   href="${pageContext.request.contextPath}/personal">
                    Пользователь, <c:out
                        value="${sessionScope.authUser.login}"/>
                </a>
            </div>
            <div class="nav-item">
                <a class="nav-link"
                   href="${pageContext.request.contextPath}/main?command=logout">
                    выйти</a>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<br>
