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

<fmt:setBundle basename="jspResources" var="jspMessages"/>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>
        <fmt:message bundle="${jspMessages}" key="all.htmlTitle"/>
    </title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>
<body>

<jsp:include page="header.jsp"/>

<h2 style="padding-left: 25px">
    <fmt:message bundle="${jspMessages}" key="login.heading"/>
</h2>

<form action="${pageContext.request.contextPath}/login" method="post"
      style="padding-left: 25px">

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
        <c:out value="${requestScope.error}"/>
    </div>
</c:if>

<jsp:include page="footer.jsp"/>

</body>
</html>
