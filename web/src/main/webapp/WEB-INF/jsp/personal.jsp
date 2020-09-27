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

<h1 style="padding-left: 25px">
    <fmt:message bundle="${jspMessages}" key="personal.heading"/>
</h1>
<h6 style="padding-left: 25px">
    <fmt:message bundle="${jspMessages}" key="personal.message"/>
</h6>

<jsp:include page="footer.jsp"/>

</body>
</html>
