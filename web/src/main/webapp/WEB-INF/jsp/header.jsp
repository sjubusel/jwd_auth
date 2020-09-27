<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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

<div class="alert alert-info" role="alert">
    <fmt:message bundle="${jspMessages}" key="header.alertInfo"/>
</div>

<div class="nav container-fluid nav-pills bg-light">
    <div class="nav-item">
        <a
                class="nav-link active"
                href="${pageContext.request.contextPath}/main">
            <fmt:message bundle="${jspMessages}" key="header.menu.main"/>
        </a>
    </div>
    <div class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/main">
            <fmt:message bundle="${jspMessages}" key="header.menu.patients"/>
        </a>
    </div>
    <div class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/main">
            <fmt:message bundle="${jspMessages}" key="header.menu.medStaff"/>
        </a>
    </div>
    <div class="nav-item flex-grow-1">
        <a class="nav-link" href="${pageContext.request.contextPath}/main">
            <fmt:message bundle="${jspMessages}" key="header.menu.contacts"/>
        </a>
    </div>

    <c:choose>
        <c:when test="${sessionScope.authUser eq null}">
            <div class="nav-item">
                <a class="nav-link"
                   href="${pageContext.request.contextPath}/login">
                    <fmt:message bundle="${jspMessages}"
                                 key="header.menu.singIn"/>
                </a>
            </div>
            <div class="nav-item">
                <a class="nav-link"
                   href="${pageContext.request.contextPath}/register">
                    <fmt:message bundle="${jspMessages}"
                                 key="header.menu.signUp"/>
                </a>
            </div>
        </c:when>
        <c:otherwise>
            <div class="nav-item">
                <a class="nav-link"
                   href="${pageContext.request.contextPath}/personal">
                    <fmt:message bundle="${jspMessages}"
                                 key="header.menu.helloMessage"/>
                    <c:out value=", "/>
                    <c:out value="${sessionScope.authUser.login}"/>
                </a>
            </div>
            <div class="nav-item">
                <a class="nav-link"
                   href="${pageContext.request.contextPath}/main?command=logout">
                    <fmt:message bundle="${jspMessages}"
                                 key="header.menu.logOut"/>
                </a>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<br>
