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

<jsp:include page="structural_element/header.jsp"/>

<c:choose>
    <c:when test="${requestScope.hospitalReport ne null}">
        <jsp:include page="mainContents.jsp"/>
    </c:when>
    <c:when test="${requestScope.reportError ne null}">
        <div class="alert alert-danger" role="alert">
            <fmt:message bundle="${jspMessages}" key="main.reportError"/>
        </div>
    </c:when>
    <c:when test="${requestScope.invalidCommand ne null}">
        <div class="alert alert-danger" role="alert">
            <fmt:message bundle="${jspMessages}" key="main.invalidCommand"/>
        </div>
    </c:when>
    <c:otherwise>
        <div class="alert alert-danger" role="alert">
            <fmt:message bundle="${jspMessages}" key="main.unknownCondition"/>
        </div>
    </c:otherwise>
</c:choose>

<jsp:include page="structural_element/footer.jsp"/>

</body>
</html>
