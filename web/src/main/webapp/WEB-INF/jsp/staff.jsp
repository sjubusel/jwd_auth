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

<c:set var="activeMenuTab" value="staff" scope="page"/>

<fmt:setBundle basename="jspResources" var="jspMessages"/>

<html>
<head>
    <title>
        <fmt:message bundle="${jspMessages}" key="staff.htmlTitle"/>
        <c:out value=" | "/>
        <fmt:message bundle="${jspMessages}" key="all.htmlTitle"/>
    </title>
    <jsp:include page="structural_element/metahead.jsp"/>
</head>
<body>

<div class="main-content">
    <%@ include file="structural_element/header.jsp" %>
    <div class="row mt-2 mr-2 ml-2">
        <%--<div class="list-group d-inline-block col-3">

        </div>
        <div class="bg-light d-inline-block col">

        </div>--%>
        <c:choose>
            <c:when test="${sessionScope.authUser ne null}">
                <c:choose>
                    <c:when test="${sessionScope.authUser.role.roleId eq 0}">

                    </c:when>
                    <c:when test="${sessionScope.authUser.role.roleId == 1}">
                        <fmt:message bundle="${jspMessages}"
                                     key="staff.accessDeniedForPatient"/>
                    </c:when>
                    <c:when test="${sessionScope.authUser.role.roleId == 2}">
                        <%@ include file="registrar/registrarSubMenu.jsp" %>
                    </c:when>
                    <c:when test="${sessionScope.authUser.role.roleId == 3}">

                    </c:when>
                    <c:when test="${sessionScope.authUser.role.roleId == 4}">

                    </c:when>
                    <c:when test="${sessionScope.authUser.role.roleId == 5}">

                    </c:when>
                    <c:when test="${sessionScope.authUser.role.roleId == 7}">

                    </c:when>
                    <c:when test="${sessionScope.authUser.role.roleId == 8}">

                    </c:when>
                    <c:when test="${sessionScope.authUser.role.roleId == 9}">

                    </c:when>
                    <c:when test="${sessionScope.authUser.role.roleId == 10}">

                    </c:when>
                    <c:when test="${sessionScope.authUser.role.roleId == 11}">

                    </c:when>
                    <c:when test="${sessionScope.authUser.role.roleId == 12}">

                    </c:when>
                </c:choose>
                <c:if test="${sessionScope.authUser.role.roleId > 1}">
                    <div class="bg-light d-inline-block col">
                        <fmt:message bundle="${jspMessages}"
                                     key="staff.welcome"/>
                        <br>
                        <c:out value="${sessionScope.authUser.lastName}"/>
                        <c:out value=" "/>
                        <c:out value="${sessionScope.authUser.firstName}"/>
                        <br>
                        <fmt:message bundle="${jspMessages}"
                                     key="staff.announceRole"/>
                        <c:out value=": "/>
                        <c:out value="${sessionScope.authUser.role.roleDescription}"/>
                    </div>
                </c:if>
            </c:when>
            <c:otherwise>
                <fmt:message bundle="${jspMessages}"
                             key="staff.accessDeniedForVisitors"/>
            </c:otherwise>
        </c:choose>
    </div>
</div>

<jsp:include page="structural_element/footer.jsp"/>

</body>
</html>
