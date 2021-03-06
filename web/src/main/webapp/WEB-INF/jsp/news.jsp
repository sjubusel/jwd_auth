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

<c:set var="activeMenuTab" value="news" scope="page"/>

<fmt:setBundle basename="jspResources" var="jspMessages"/>

<html>
<head>
    <title>
        <fmt:message bundle="${jspMessages}" key="news.htmlTitle"/>
        <c:out value=" | "/>
        <fmt:message bundle="${jspMessages}" key="all.htmlTitle"/>
    </title>
    <jsp:include page="structural_element/metahead.jsp"/>
</head>
<body>

<div class="main-content">
    <%@ include file="structural_element/header.jsp" %>

    <h2 style="padding-left: 25px">
        <fmt:message bundle="${jspMessages}" key="news.heading"/>
    </h2>

    <div class="card text-center">
        <div class="card-header">
            <fmt:message bundle="${jspMessages}" key="news.firstArticle"/>
        </div>
        <div class="card-body">
            <h5 class="card-title"><fmt:message bundle="${jspMessages}"
                                                key="news.firstArticleTitle"/></h5>
            <p class="card-text">
                <fmt:message bundle="${jspMessages}"
                             key="news.firstArticleText"/>
            </p>
            <a href="${pageContext.request.contextPath}/main"
               class="btn btn-primary"><fmt:message
                    bundle="${jspMessages}"
                    key="news.firstArticleLink"/>
            </a>
        </div>
        <div class="card-footer text-muted">
            15.11.2020
        </div>
    </div>

</div>

<jsp:include page="structural_element/footer.jsp"/>

</body>
</html>
