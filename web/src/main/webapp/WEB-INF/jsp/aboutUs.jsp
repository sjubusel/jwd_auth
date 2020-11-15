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

<c:set var="activeMenuTab" value="aboutUs" scope="page"/>

<html>
<head>
    <title>
        <fmt:message bundle="${jspMessages}" key="aboutUs.htmlTitle"/>
        <c:out value=" | "/>
        <fmt:message bundle="${jspMessages}" key="all.htmlTitle"/>
    </title>
    <jsp:include page="structural_element/metahead.jsp"/>
</head>
<body>

<div class="main-content">
    <%@ include file="structural_element/header.jsp" %>

    <h2 style="padding-left: 25px">
        <fmt:message bundle="${jspMessages}" key="aboutUs.htmlTitle"/>
    </h2>
    <div class="ml-2 mr-2 mb-2 mt-2">
        <fmt:message bundle="${jspMessages}" key="aboutUs.contents"/>
    </div>

    <div class="text-center">
        <img class="rounded mx-auto d-block" height="500"
             src="http://${pageContext.request.serverName}:${pageContext.request.serverPort}/upload/assets/3.jpg"
             alt="main photo">
    </div>
</div>

<jsp:include page="structural_element/footer.jsp"/>

</body>
</html>
