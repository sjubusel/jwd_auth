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
<c:set var="activeSubMenuStaffTab" value="refuseToHospitalize" scope="page"/>

<fmt:setBundle basename="jspResources" var="jspMessages"/>
<fmt:setBundle basename="registrationRegExp" var="regEx"/>

<html>
<head>

    <title>
        <fmt:message bundle="${jspMessages}"
                     key="staff.refuseToHospitalize"/>
        <c:out value=" | "/>
        <fmt:message bundle="${jspMessages}" key="staff.htmlTitle"/>
        <c:out value=" | "/>
        <fmt:message bundle="${jspMessages}" key="all.htmlTitle"/>
    </title>
    <jsp:include page="../structural_element/metahead.jsp"/>
</head>
<body>

<div class="main-content">

    <%@ include file="../structural_element/header.jsp" %>
    <div class="row mt-2 mr-2 ml-2">
        <%@ include file="admissionDoctorSubMenu.jsp" %>

        <div class="bg-light d-inline-block col">
            <%--CONTENTS--%>
            <c:choose>
                <%--ERROR--%>
                <c:when test="${requestScope.error ne null}">
                    <c:choose>
                        <%-- a tech error --%>
                        <c:when test="${requestScope.error eq 'tech'}">
                            <div class="alert alert-danger" role="alert">
                                <fmt:message bundle="${jspMessages}"
                                             key="ordinary.techError"/>
                            </div>
                        </c:when>
                        <%-- a validation error --%>
                        <c:when test="${requestScope.error eq 'val'}">
                            <div class="alert alert-danger" role="alert">
                                <fmt:message bundle="${jspMessages}"
                                             key="ordinary.validationError"/>
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
                <c:otherwise>
                    <%--                    <c:if test="${requestScope.refusalResult ne null}">--%>
                    <%--                        <c:choose>--%>
                    <%--                            <c:when test="${requestScope.refusalResult eq 'techError'}">--%>
                    <%--                                <div class="alert alert-danger" role="alert">--%>
                    <%--                                    <fmt:message bundle="${jspMessages}"--%>
                    <%--                                                 key="makeVisitDecision.refusalResultTechError"/>--%>
                    <%--                                </div>--%>
                    <%--                            </c:when>--%>
                    <%--                            <c:when test="${requestScope.refusalResult eq 'validationError'}">--%>
                    <%--                                <div class="alert alert-danger" role="alert">--%>
                    <%--                                    <fmt:message bundle="${jspMessages}"--%>
                    <%--                                                 key="makeVisitDecision.refusalResultValError"/>--%>
                    <%--                                </div>--%>
                    <%--                            </c:when>--%>
                    <%--                        </c:choose>--%>
                    <%--                    </c:if>--%>
                    <h1>
                        <fmt:message bundle="${jspMessages}"
                                     key="refusalsOnControl.heading"/>
                    </h1>
                    <c:choose>
                        <c:when test="${requestScope.refusals ne null}">
                            <div class="row d-flex mb-1 border">
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="refusalsOnControl.visitDateTime"/>
                                </div>
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="refusalsOnControl.patientShortInfo"/>
                                </div>
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="refusalsOnControl.patientVisitDescriptionInfo"/>
                                </div>
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="refusalsOnControl.actions"/>
                                </div>
                            </div>
                            <c:forEach var="refusal"
                                       items="${requestScope.refusals}">
                                <form action="${pageContext.request.contextPath}/profile"
                                      method="post">
                                    <input type="hidden" name="command"
                                           value="go-to-refuse-to-hospitalize">
                                    <input type="hidden"
                                           name="hiddenVisitId"
                                           value="${refusal.visitId}">
                                    <div class="row d-flex mb-1 border align-items-center">
                                        <div class="col">
                                            <c:out value="${refusal.visitDateTime}"/>
                                        </div>
                                        <div class="col">
                                            <c:out value="${refusal.patientShortInfo}"/>
                                        </div>
                                        <div class="col">
                                            <c:out value="${refusal.patientVisitDescriptionInfo}"/>
                                        </div>
                                        <div class="col">
                                            <button type="submit"
                                                    class="btn align-self-center btn-primary">
                                                <fmt:message
                                                        bundle="${jspMessages}"
                                                        key="refusalsOnControl.view"/>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <fmt:message bundle="${jspMessages}"
                                         key="refusalsOnControl.noRecords"/>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>
        </div>

    </div>
</div>

<jsp:include page="../structural_element/footer.jsp"/>
</body>
</html>
