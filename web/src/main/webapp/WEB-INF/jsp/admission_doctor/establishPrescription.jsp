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
                     key="visitDetail.establishPresription"/>
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
                                     key="establishPrescription.heading"/>
                    </h1>
                    <c:choose>
                        <c:when test="${requestScope.addResult ne null}">
                            <c:if test="${requestScope.addResult eq 'success'}">
                                <div class="alert alert-success" role="alert">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="establishPrescription.addResultSuccess"/>
                                </div>
                            </c:if>
                            <c:if test="${requestScope.addResult eq 'techError'}">
                                <div class="alert alert-danger" role="alert">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="establishPrescription.addResultTechError"/>
                                </div>
                            </c:if>
                            <c:if test="${requestScope.addResult eq 'validationError'}">
                                <div class="alert alert-danger" role="alert">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="establishPrescription.addResultValidationError"/>
                                </div>
                            </c:if>
                        </c:when>
                    </c:choose>
                    <c:choose>
                        <c:when test="${true}">
                            <form action="${pageContext.request.contextPath}/profile"
                                  method="post">
                                <input type="hidden" name="command"
                                       value="establish-prescription">
                                <input type="hidden" name="hiddenVisitId"
                                       value="${requestScope.hiddenVisitId}">
                                <div class="form-group form-inline row mb-3">
                                </div>
                                <label for="prescriptionDescription">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="establishPrescription.prescriptionLabel"/>
                                </label>
                                <textarea type="text"
                                          id="prescriptionDescription"
                                          name="prescriptionDescriptionInput"
                                          rows="10"
                                          cols="100"
                                          required
                                          pattern=".{1,255}"
                                          placeholder="<fmt:message bundle="${jspMessages}"
                                       key="establishPrescription.medicinePlaceholder"/>"></textarea>
                                <div>
                                    <button type="submit"
                                            class="btn align-self-center btn-primary ">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="establishPrescription.button"/>
                                    </button>
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
