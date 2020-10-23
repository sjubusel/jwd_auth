<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
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

<c:set var="activeMenuTab" value="signIn" scope="page"/>

<fmt:setBundle basename="jspResources" var="jspMessages"/>

<html>
<head>
    <title>
        <fmt:message bundle="${jspMessages}" key="login.htmlTitle"/>
        <c:out value=" | "/>
        <fmt:message bundle="${jspMessages}" key="all.htmlTitle"/>
    </title>
    <jsp:include page="structural_element/metahead.jsp"/>
</head>
<body>

<jsp:include page="structural_element/header.jsp"/>

<h2 style="padding-left: 25px">
    <fmt:message bundle="${jspMessages}" key="login.heading"/>
</h2>

<form action="${pageContext.request.contextPath}/main" method="post"
      style="padding-left: 25px">
    <input type="hidden" name="command" value="login"/>
    <div class="form-group">
        <label for="loginInput">
            <fmt:message bundle="${jspMessages}" key="login.loginInputLabel"/>
        </label>
        <input type="text" class="form-control" id="loginInput" name="login">
    </div>
    <div class="form-group">
        <label for="passwordInput">
            <fmt:message bundle="${jspMessages}"
                         key="login.passwordInputLabel"/>
        </label>
        <input type="password" class="form-control" id="passwordInput"
               name="password">
    </div>
    <button type="submit" class="btn btn-primary">
        <fmt:message bundle="${jspMessages}" key="login.submitButton"/>
    </button>

</form>

<br>

<c:if test="${requestScope.error ne null}">
    <div class="alert alert-danger" role="alert">
        <c:choose>
            <c:when test="${requestScope.error eq 'simple'}">
                <fmt:message bundle="${jspMessages}" key="login.simpleError"/>
            </c:when>
            <c:when test="${requestScope.error eq 'tech'}">
                <fmt:message bundle="${jspMessages}" key="login.techError"/>
            </c:when>
            <c:otherwise>
                <c:out value="${requestScope.error}"/>
            </c:otherwise>
        </c:choose>

    </div>
</c:if>

<jsp:include page="structural_element/footer.jsp"/>

</body>
</html>
