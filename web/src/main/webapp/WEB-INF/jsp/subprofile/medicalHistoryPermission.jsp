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
            <%--AFTER DELETING OF PERMISSION  --%>
            <c:choose>
                <c:when test="${requestScope.deleteResult ne null}">
                    <c:choose>
                        <c:when test="${requestScope.deleteResult eq 'success'}">
                            <div class="alert alert-success" role="alert">
                                <fmt:message bundle="${jspMessages}"
                                             key="profileSubMenu.medicalHistoryPermission.successAfterDeletingOfPermission"/>
                            </div>
                        </c:when>
                        <%-- a tech error --%>
                        <c:when test="${requestScope.deleteResult eq 'techError'}">
                            <div class="alert alert-danger" role="alert">
                                <fmt:message bundle="${jspMessages}"
                                             key="profileSubMenu.medicalHistoryPermission.techErrorAfterDeletingOfPermission"/>
                            </div>
                        </c:when>
                        <%-- a validation error --%>
                        <c:when test="${requestScope.deleteResult eq 'validationError'}">
                            <div class="alert alert-danger" role="alert">
                                <fmt:message bundle="${jspMessages}"
                                             key="profileSubMenu.medicalHistoryPermission.validationErrorAfterDeletingOfPermission"/>
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
            </c:choose>

            <%-- SIMPLE GET REQUEST: whether there is an error. if it is so, then print an error-message and quit, otherwise present contents of this jsp--%>
            <c:choose>
                <c:when test="${requestScope.error ne null}">
                    <c:choose>
                        <%-- a tech error --%>
                        <c:when test="${requestScope.error eq 'tech'}">
                            <div class="alert alert-danger" role="alert">
                                <fmt:message bundle="${jspMessages}"
                                             key="profileSubMenu.medicalHistoryPermission.techError"/>
                            </div>
                        </c:when>
                        <%-- a validation error --%>
                        <c:when test="${requestScope.error eq 'val'}">
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
                            <div class="row d-flex mb-1 border">
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
                                <form action="${pageContext.request.contextPath}/profile"
                                      method="post">
                                    <div class="row d-flex mb-1 border align-items-center">

                                        <input type="hidden" name="command"
                                               value="profile-medical-history-permission-delete"/>
                                        <input type="hidden"
                                               name="permissionIdInput"
                                               value="${medPermission.permissionId}"/>
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

                                    </div>
                                </form>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <fmt:message bundle="${jspMessages}"
                                         key="profileSubMenu.medicalHistoryPermission.noRecordsMessage"/>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>

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
