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

<c:set var="activeMenuTab" value="profile" scope="page"/>

<fmt:setBundle basename="jspResources" var="jspMessages"/>

<html>
<head>
    <title>
        <fmt:message bundle="${jspMessages}" key="profile.htmlTitle"/>
        <c:out value=" | "/>
        <fmt:message bundle="${jspMessages}" key="all.htmlTitle"/>
    </title>
    <jsp:include page="structural_element/metahead.jsp"/>
</head>
<body>

<jsp:include page="structural_element/header.jsp"/>

<h1 style="padding-left: 25px">
    <fmt:message bundle="${jspMessages}" key="profile.heading"/>
</h1>
<h6 style="padding-left: 25px">
    <fmt:message bundle="${jspMessages}" key="profile.message"/>
</h6>

<jsp:include page="structural_element/footer.jsp"/>

</body>
</html>
