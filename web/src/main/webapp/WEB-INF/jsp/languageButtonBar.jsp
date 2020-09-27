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

<div style="float: right">
    <div>
        <fmt:message bundle="${jspMessages}"
                     key="languageButtonBar.currentLang"/>
    </div>
    <form id="form" action="${pageContext.request.contextPath}/main">
        <input type="hidden" name="command" value="change-language"/>
        <button class="btn-primary" type="submit" form="form" formmethod="get"
                name="language"
                value="russian">
            рус
        </button>
        <button type="submit" form="form" formmethod="get" name="language"
                value="english">
            eng
        </button>
    </form>
</div>
