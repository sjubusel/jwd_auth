<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Учреждение здравоохранения "Инновационная больница "Шабаны"
        г.Минска"</title>
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
