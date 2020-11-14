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
<c:set var="activeSubMenuStaffTab" value="visitsOnControl" scope="page"/>

<fmt:setBundle basename="jspResources" var="jspMessages"/>
<fmt:setBundle basename="registrationRegExp" var="regEx"/>

<html>
<head>
    <title>
        <fmt:message bundle="${jspMessages}"
                     key="visitDetail.establishDiagnosis"/>
        <c:out value=" | "/>
        <fmt:message bundle="${jspMessages}"
                     key="admissionDoctorSubMenu.visitsOnControl"/>
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
                    <h1>
                        <fmt:message bundle="${jspMessages}"
                                     key="establishDiagnosis.heading"/>
                    </h1>
                    <c:choose>
                        <c:when test="${true}">
                            <form action="${pageContext.request.contextPath}/profile"
                                  method="post">
                                <input type="hidden" name="command"
                                       value="establish-diagnosis">
                                <input type="hidden" name="hiddenVisitId"
                                       value="${}">
                                <div class="form-group form-inline row">
                                    <label for="disease">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="establishDiagnosis.diseaseLabel"/>
                                    </label>
                                    <input type="text" id="disease"
                                           name="diseaseInput" required
                                           placeholder="<fmt:message bundle="${jspMessages}"
                                       key="establishDiagnosis.diseasePlaceholder"/>"/>
                                </div>
                                    <%--AJAX RESULT--%>
                                <div id="diseaseResult"
                                     class="overflow-auto"
                                     style="max-height: 100px">
                                </div>
                                <div class="form-group form-inline row">
                                    <label for="diagnosisDescription">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="establishDiagnosis.diseaseDescriptionLabel"/>
                                    </label>
                                    <textarea type="text" required rows="10"
                                              cols="60"
                                              id="diagnosisDescription"
                                              name="diagnosisDescriptionInput"
                                              placeholder="<fmt:message bundle="${jspMessages}" key="establishDiagnosis.diseaseDescriptionPlaceholder"/>"></textarea>
                                </div>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <c:out value=""/>
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
