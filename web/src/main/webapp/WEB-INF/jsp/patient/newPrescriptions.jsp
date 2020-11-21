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
<c:set var="activeSubMenuTab" value="patientNewNonMedicinePrescriptions"
       scope="page"/>

<fmt:setBundle basename="jspResources" var="jspMessages"/>

<html>
<head>
    <title>
        <fmt:message bundle="${jspMessages}"
                     key="patientSubMenu.newNonMedicinePrescriptions"/>
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
        <%@ include file="patientSubMenu.jsp" %>
        <div class="bg-light d-inline-block col">

            <c:choose>
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
                    <c:if test="${requestScope.disagreeResult ne null}">
                        <c:choose>
                            <c:when test="${requestScope.disagreeResult eq 'success'}">
                                <div class="alert alert-success"
                                     role="alert">
                                    <fmt:message
                                            bundle="${jspMessages}"
                                            key="patient.newNonMedicinePrescriptions.disagreeResultSuccess"/>
                                </div>
                            </c:when>
                            <c:when test="${requestScope.disagreeResult eq 'techError'}">
                                <div class="alert alert-danger"
                                     role="alert">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="patient.newNonMedicinePrescriptions.disagreeResultTechError"/>
                                </div>
                            </c:when>
                            <c:when test="${requestScope.disagreeResult eq 'validationError'}">
                                <fmt:message bundle="${jspMessages}"
                                             key="patient.newNonMedicinePrescriptions.disagreeResultValidationError"/>
                            </c:when>
                            <c:otherwise>
                                <div class="alert alert-danger"
                                     role="alert">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="main.unknownCondition"/>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                    <c:choose>
                        <c:when test="${requestScope.prescriptions ne null}">
                            <div class="row d-flex mb-1 border">
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="visitDetail.diagnoses.departmentType"/>
                                </div>
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="visitDetail.prescriptionDateTime"/>
                                </div>
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="visitDetail.prescriptionDescription"/>
                                </div>
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="visitDetail.prescribingDoctorInfo"/>
                                </div>
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="visitDetail.controllingStaff"/>
                                </div>
                                <div class="col">
                                    <label for="disagreementDescription">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="patient.newMedPrescriptions.disagreement"/>
                                    </label>
                                </div>
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="newMedicinePrescriptions.buttons"/>
                                </div>
                            </div>
                            <c:forEach var="prescription"
                                       items="${requestScope.prescriptions}">
                                <form action="${pageContext.request.contextPath}/profile"
                                      method="post">
                                    <input type="hidden" name="command"
                                           value="patient-disagree-with-non-medicine-prescription"/>
                                    <input type="hidden"
                                           name="hiddenPrescriptionIdInput"
                                           value="<c:out value="${prescription.prescriptionId}"/>"/>
                                    <div class="row d-flex mb-1 border align-items-center">
                                        <div class="col">
                                            <c:out value="${prescription.departmentOrigin.description}"/>
                                        </div>
                                        <div class="col">
                                            <c:out value="${prescription.prescriptionDateTime}"/>
                                        </div>
                                        <div class="col">
                                            <c:out value="${prescription.prescriptionDescription}"/>
                                        </div>
                                        <div class="col">
                                            <c:out value="${prescription.prescribingStaffInfo}"/>
                                        </div>
                                        <div class="col d-block">
                                            <c:choose>
                                                <c:when test="${prescription.responsibleStaffId > 0}">
                                                    <div>
                                                        <fmt:message
                                                                bundle="${jspMessages}"
                                                                key="visitDetail.responsibleStaff"/>
                                                        <c:out value=": "/>
                                                        <c:out value="${prescription.responsibleStaffInfo}"/>
                                                    </div>
                                                </c:when>
                                            </c:choose>
                                        </div>
                                        <div class="col">
                                            <input type="text"
                                                   id="disagreementDescription"
                                                   name="disagreementDescriptionInput"
                                                   maxlength="255"
                                                   required>
                                        </div>
                                        <div class="col">
                                            <button type="submit"
                                                    class="btn align-self-center btn-primary">
                                                <fmt:message
                                                        bundle="${jspMessages}"
                                                        key="patient.newMedPrescriptions.disagree"/>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <fmt:message bundle="${jspMessages}"
                                         key="newNonMedicinePrescriptions.noNewRecords"/>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
</body>
</html>
