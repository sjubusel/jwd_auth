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

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>
        <fmt:message bundle="${jspMessages}" key="all.htmlTitle"/>
    </title>
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
</head>

<body>

<jsp:include page="header.jsp"/>

<c:choose>
    <c:when test="${requestScope.hospitalReport ne null}">
        <jsp:include page="mainContents.jsp"/>
    </c:when>
    <c:otherwise>
        <div class="alert alert-danger" role="alert">
            <c:out value="${requestScope.reportError}"/>
        </div>
    </c:otherwise>
</c:choose>

<jsp:include page="footer.jsp"/>

</body>
</html>
