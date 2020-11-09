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
                            <%--header--%>
                            <div class="row d-flex">
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="profileSubMenu.medicalHistoryPermission.recipient"/>
                                </div>
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="profileSubMenu.medicalHistoryPermission.permissionDateTime"/>
                                </div>
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="profileSubMenu.medicalHistoryPermission.cancellationDateTime"/>
                                </div>
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="profileSubMenu.medicalHistoryPermission.deleteButton"/>
                                </div>
                            </div>
                            <%--contents--%>
                            <c:forEach var="medPermission"
                                       items="${requestScope.medicalHistoryPermissions}">
                                <div class="row d-flex">
                                    <form action="${pageContext.request.contextPath}/profile"
                                          method="post">
                                        <input type="hidden" name="command"
                                               value="profile-medical-history-permission-delete"/>
                                        <input type="hidden"
                                               name="permissionIdInput"
                                               value="${medPermission.recipientId}"/>
                                            <%--1st column--%>
                                        <div class="col">
                                            <c:out value="${medPermission.recipientInfo}"/>
                                        </div>
                                            <%--2nd column--%>
                                        <div class="col">
                                            <c:out value="${medPermission.permissionDateTime}"/>
                                        </div>
                                            <%--3rd column--%>
                                        <div class="col"
                                                <c:if test="${medPermission.cancellationDescription ne null}">
                                                    title="${medPermission.cancellationDescription}"
                                                </c:if>
                                        >
                                            <c:if test="${medPermission.cancellationDateTime ne null}">
                                                <c:out value="${medPermission.cancellationDateTime}"/>
                                            </c:if>
                                        </div>
                                            <%-- 4th column (button) --%>
                                        <div class="col">
                                            <button type="submit"
                                                    class="btn align-self-center btn-primary"
                                                    <c:if test="${medPermission.cancellationDateTime ne null}">
                                                        disabled
                                                    </c:if>
                                            >
                                                <fmt:message
                                                        bundle="${jspMessages}"
                                                        key="profileSubMenu.medicalHistoryPermission.delete"/>
                                            </button>
                                        </div>
                                    </form>

                                </div>
                            </c:forEach>
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
