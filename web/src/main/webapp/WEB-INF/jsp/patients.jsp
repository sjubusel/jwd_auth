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

<c:set var="activeMenuTab" value="patients" scope="page"/>

<fmt:setBundle basename="jspResources" var="jspMessages"/>

<html>
<head>
    <title>
        <fmt:message bundle="${jspMessages}" key="patients.htmlTitle"/>
        <c:out value=" | "/>
        <fmt:message bundle="${jspMessages}" key="all.htmlTitle"/>
    </title>
    <jsp:include page="structural_element/metahead.jsp"/>
</head>
<body>

<div class="main-content">
    <%@ include file="structural_element/header.jsp" %>

    <div class="row mt-2 mr-2 ml-2">
        <%@ include file="patient/patientSubMenu.jsp" %>
        <div class="bg-light d-inline-block col">
            <fmt:message bundle="${jspMessages}"
                         key="patients.welcome"/>
            <br>
            <c:out value="${sessionScope.authUser.lastName}"/>
            <c:out value=" "/>
            <c:out value="${sessionScope.authUser.firstName}"/>
            <br>
            <fmt:message bundle="${jspMessages}"
                         key="patients.announceRole"/>
            <c:out value=": "/>
            <fmt:message bundle="${jspMessages}"
                         key="patients.rolePatient"/>
            <br>
            <br>

        </div>
    </div>
</div>

<jsp:include page="structural_element/footer.jsp"/>

</body>
</html>
