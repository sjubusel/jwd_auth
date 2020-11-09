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
<c:set var="activeSubMenuProfileTab" value="medicalHistoryPermission"
       scope="page"/>

<fmt:setBundle basename="jspResources" var="jspMessages"/>

<html>
<head>
    <title>
        <fmt:message bundle="${jspMessages}"
                     key="profileSubMenu.medicalHistoryPermission"/>
        <c:out value=" | "/>
        <fmt:message bundle="${jspMessages}" key="profile.htmlTitle"/>
        <c:out value=" | "/>
        <fmt:message bundle="${jspMessages}" key="all.htmlTitle"/>
    </title>
    <jsp:include page="../structural_element/metahead.jsp"/>
</head>
<body>

<div class="main-content">
    <%@ include file="../structural_element/header.jsp" %>

    <div class="row mt-2 mr-2 ml-2">
        <%@ include file="../structural_element/profileSubMenu.jsp" %>

        <div class="bg-light d-inline-block col">

            <h1 class="text-left">
                <fmt:message bundle="${jspMessages}"
                             key="profileSubMenu.medicalHistoryPermission.heading"/>
            </h1>

            <div class="text-muted mb-3">
                <fmt:message bundle="${jspMessages}"
                             key="profileSubMenu.medicalHistoryPermission.disclaimer"/>
            </div>

            <%-- whether there is an error. if it is so, then print an error-message and quit, otherwise present contents of this jsp--%>
            <c:choose>
                <c:when test="${requestScope.error ne null}">
                    <c:choose>
                        <%-- a tech error --%>
                        <c:when test="tech">
                            <div class="alert alert-danger" role="alert">
                                <fmt:message bundle="${jspMessages}"
                                             key="profileSubMenu.medicalHistoryPermission.techError"/>
                            </div>
                        </c:when>
                        <%-- a validation error --%>
                        <c:when test="val">
                            <div class="alert alert-danger" role="alert">
                                <fmt:message bundle="${jspMessages}"
                                             key="profileSubMenu.medicalHistoryPermission.validationError"/>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="alert alert-danger" role="alert">
                                <fmt:message bundle="${jspMessages}"
                                             key="main.unknownCondition"/>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </c:when>
                <%--Here the jsp's contents begin--%>
                <c:otherwise>
                    <c:choose>
                        <c:when test="${requestScope.medicalHistoryPermissions ne null}">
                            STUB
                        </c:when>
                        <c:otherwise>
                            <fmt:message bundle="${jspMessages}"
                                         key="profileSubMenu.medicalHistoryPermission.noRecordsMessage"/>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>

            <%--            <fmt:message bundle="${jspMessages}"--%>
            <%--                         key="profileSubMenu.medicalHistoryPermission.tableHeadDescription"/>--%>
            <%--            <fmt:message bundle="${jspMessages}"--%>
            <%--                         key="profileSubMenu.medicalHistoryPermission.tableHeadVacantQuantity"/>--%>
            <%--            <fmt:message bundle="${jspMessages}"--%>
            <%--                         key="profileSubMenu.medicalHistoryPermission.tableHeadTotalQuantity"/>--%>

            <%--                <c:forEach var="hospitalReportPart"--%>
            <%--                           items="${requestScope.hospitalReport.contents}">--%>
            <%--                    <tr>--%>
            <%--                        <td><c:out value="${hospitalReportPart.deptName}"/></td>--%>
            <%--                        <td><c:out value="${hospitalReportPart.vacantPlacesNumber}"/></td>--%>
            <%--                        <td><c:out value="${hospitalReportPart.totalPlacesNumber}"/></td>--%>
            <%--                    </tr>--%>
            <%--                </c:forEach>--%>

            <form action="${pageContext.request.contextPath}/profile"
                  method="post">

                <input type="hidden" name="command"
                       value="profile-medical-history-permission"/>

                <button type="submit"
                        class="btn align-self-center btn-primary">
                    <fmt:message bundle="${jspMessages}"
                                 key="profileSubMenu.medicalHistoryPermission.add"/>
                </button>
            </form>


        </div>
    </div>
</div>

<jsp:include page="../structural_element/footer.jsp"/>

</body>
</html>
