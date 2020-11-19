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
<c:set var="activeSubMenuStaffTab" value="newMedPrescriptions" scope="page"/>

<fmt:setBundle basename="jspResources" var="jspMessages"/>

<html>
<head>
    <title>
        <fmt:message bundle="${jspMessages}"
                     key="medicinePrescriptionExecution.title"/>
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
            <%--CONTENTS START--%>
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
                    <h1>
                        <fmt:message bundle="${jspMessages}"
                                     key="medicinePrescriptionExecution.infoHeading"/>
                    </h1>
                    <c:choose>
                        <c:when test="${requestScope.medicinePrescription ne null}">
                            <jsp:useBean id="medicinePrescription"
                                         class="by.epamtc.jwd.auth.model.med_info.MedicinePrescription"
                                         scope="request"/>
                            <table class="table">
                                <tbody>
                                <tr class="row">
                                    <td class="col-3 d-flex align-items-center">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="medicinePrescriptionExecution.prescriptionId"/>
                                    </td>
                                    <td class="col d-flex align-items-center">
                                        <c:choose>
                                            <c:when test="${medicinePrescription.prescriptionId > 0}">
                                                <c:out value="${medicinePrescription.prescriptionId}"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value=""/>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                                <tr class="row">
                                    <td class="col-3 d-flex align-items-center">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="medicinePrescriptionExecution.originDocumentId"/>
                                    </td>
                                    <td class="col d-flex align-items-center">
                                        <c:choose>
                                            <c:when test="${medicinePrescription.originDocumentId > 0}">
                                                <c:out value="${medicinePrescription.originDocumentId}"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value=""/>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                                <tr class="row">
                                    <td class="col-3 d-flex align-items-center">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="medicinePrescriptionExecution.patientInfo"/>
                                    </td>
                                    <td class="col d-flex align-items-center">
                                        <c:choose>
                                            <c:when test="${medicinePrescription.patientInfo ne null}">
                                                <c:out value="${medicinePrescription.patientInfo}"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value=""/>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                                <tr class="row">
                                    <td class="col-3 d-flex align-items-center">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="medicinePrescriptionExecution.medicineInfo"/>
                                    </td>
                                    <td class="col d-flex align-items-center">
                                        <c:choose>
                                            <c:when test="${medicinePrescription.medicineInfo ne null}">
                                                <c:out value="${medicinePrescription.medicineInfo}"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value=""/>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                                <tr class="row">
                                    <td class="col-3 d-flex align-items-center">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="medicinePrescriptionExecution.dosage"/>
                                    </td>
                                    <td class="col d-flex align-items-center">
                                        <c:choose>
                                            <c:when test="${medicinePrescription.dosageQuantity > 0
                                            && medicinePrescription.dosageMeasureUnit ne null}">
                                                <c:out value="${medicinePrescription.dosageQuantity}"/>
                                                <c:out value=" "/>
                                                <c:out value="${medicinePrescription.dosageMeasureUnit.shortName}"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value=""/>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                                <tr class="row">
                                    <td class="col-3 d-flex align-items-center">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="medicinePrescriptionExecution.prescriptionDateTime"/>
                                    </td>
                                    <td class="col d-flex align-items-center">
                                        <c:choose>
                                            <c:when test="${medicinePrescription.prescriptionDateTime ne null}">
                                                <c:out value="${medicinePrescription.prescriptionDateTime}"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value=""/>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                                <tr class="row">
                                    <td class="col-3 d-flex align-items-center">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="medicinePrescriptionExecution.targetApplicationDateTime"/>
                                    </td>
                                    <td class="col d-flex align-items-center">
                                        <c:choose>
                                            <c:when test="${medicinePrescription.targetApplicationDateTime ne null}">
                                                <c:out value="${medicinePrescription.targetApplicationDateTime}"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value=""/>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                                <tr class="row">
                                    <td class="col-3 d-flex align-items-center">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="medicinePrescriptionExecution.prescribingStaffInfo"/>
                                    </td>
                                    <td class="col d-flex align-items-center">
                                        <c:choose>
                                            <c:when test="${medicinePrescription.prescribingStaffInfo ne null}">
                                                <c:out value="${medicinePrescription.prescribingStaffInfo}"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value=""/>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                            <h1>
                                <fmt:message bundle="${jspMessages}"
                                             key="medicinePrescriptionExecution.executionResultHeading"/>
                            </h1>
                            <form action="${pageContext.request.contextPath}/profile"
                                  method="post">
                                <div class="row">
                                    <input type="hidden"
                                           name="command"
                                           value="staff-execute-medicine-prescription">
                                    <input type="hidden"
                                           name="hiddenPrescriptionIdInput"
                                           value="${medicinePrescription.prescriptionId}">
                                    <label>
                                        <fmt:message bundle="${jspMessages}"
                                                     key="medicinePrescriptionExecution.executionResult"/>
                                        <textarea type="text"
                                                  name="executionResultInput"
                                                  rows="10"
                                                  cols="100"
                                                  placeholder="<fmt:message bundle="${jspMessages}"
                                         key="medicinePrescriptionExecution.executionResultPlaceholder"/>"></textarea>
                                    </label>
                                </div>
                                <div class="row">
                                    <button type="submit"
                                            class="btn align-self-center btn-primary">
                                        <fmt:message
                                                bundle="${jspMessages}"
                                                key="medicinePrescriptionExecution.executeButton"/>
                                    </button>
                                </div>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <fmt:message bundle="${jspMessages}"
                                         key="medicinePrescriptionExecution.prescriptionIsalreadyExecuted"/>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>
            <%--CONTENTS END--%>
        </div>
    </div>
</div>

<jsp:include page="../structural_element/footer.jsp"/>
</body>
</html>
