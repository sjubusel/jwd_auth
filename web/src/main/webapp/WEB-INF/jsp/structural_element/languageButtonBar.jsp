<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:choose>
    <c:when test="${sessionScope.language eq null}">
        <fmt:setLocale value="ru_RU"/>
        <c:set var="language" value="ru_RU" scope="session"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="${sessionScope.language}"/>
    </c:otherwise>
</c:choose>

<fmt:setBundle basename="jspResources" var="jspMessages"/>

<div class="lang-btn-group">
    <form id="form" action="${pageContext.request.contextPath}/main">
        <input type="hidden" name="command" value="change-language"/>
        <button
                <c:choose>
                    <c:when test="${sessionScope.language == 'ru_RU'}">
                        class="lang-btn active"
                    </c:when>
                    <c:otherwise>
                        class="lang-btn"
                    </c:otherwise>
                </c:choose>
                type="submit" form="form"
                formmethod="get"
                formaction="${pageContext.request.contextPath}/main"
                name="language"
                value="russian">
            рус
        </button>
        <button
                <c:choose>
                    <c:when test="${sessionScope.language == 'en_US'}">
                        class="lang-btn active"
                    </c:when>
                    <c:otherwise>
                        class="lang-btn"
                    </c:otherwise>
                </c:choose>
                type="submit" form="form"
                formmethod="get"
                formaction="${pageContext.request.contextPath}/main"
                name="language"
                value="english">
            eng
        </button>
    </form>
</div>
