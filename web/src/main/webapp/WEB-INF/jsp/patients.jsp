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

<c:set var="activeMenuTab" value="patients" scope="page"/>

<fmt:setBundle basename="jspResources" var="jspMessages"/>

<html>
<head>
    <title>
        <fmt:message bundle="${jspMessages}" key="patients.htmlTitle"/>
        <c:out value=" | "/>
        <fmt:message bundle="${jspMessages}" key="all.htmlTitle"/>
    </title>
    <jsp:include page="structural_element/metahead.jsp"/>
</head>
<body>

<div class="main-content">
    <%@ include file="structural_element/header.jsp" %>

    <div class="row mt-2 mr-2 ml-2">
        <%@ include file="patient/patientSubMenu.jsp" %>
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
                    <fmt:message bundle="${jspMessages}"
                                 key="patients.welcome"/>
                    <br>
                    <c:out value="${sessionScope.authUser.lastName}"/>
                    <c:out value=" "/>
                    <c:out value="${sessionScope.authUser.firstName}"/>
                    <br>
                    <fmt:message bundle="${jspMessages}"
                                 key="patients.announceRole"/>
                    <c:out value=": "/>
                    <fmt:message bundle="${jspMessages}"
                                 key="patients.rolePatient"/>
                    <br>
                    <br>
                    <c:if test="${requestScope.visitInfo ne null}">
                        <c:choose>
                            <c:when test="${requestScope.visitInfo.visitId > 0 }">
                                <h1>
                                    <fmt:message bundle="${jspMessages}"
                                                 key="patient.currentVisitInfo"/>
                                </h1>
                                <table class="table">
                                    <tbody>
                                    <tr class="row">
                                    <tr class="row">
                                        <td class="col-5 d-flex align-items-center">
                                            <fmt:message bundle="${jspMessages}"
                                                         key="visitDetail.visitId"/>
                                        </td>
                                        <td class="col d-flex align-items-center">
                                            <c:choose>
                                                <c:when test="${requestScope.visitInfo.visitId ne null}">
                                                    <c:out value="${requestScope.visitInfo.visitId}"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:out value=""/>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                    <tr class="row">
                                        <td class="col-5 d-flex align-items-center">
                                            <fmt:message bundle="${jspMessages}"
                                                         key="visitDetail.visitDateTime"/>
                                        </td>
                                        <td class="col d-flex align-items-center">
                                            <c:choose>
                                                <c:when test="${requestScope.visitInfo.visitDateTime ne null}">
                                                    <c:out value="${requestScope.visitInfo.visitDateTime}"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:out value=""/>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                    <tr class="row">
                                        <td class="col-5 d-flex align-items-center">
                                            <fmt:message bundle="${jspMessages}"
                                                         key="visitDetail.patientId"/>
                                        </td>
                                        <td class="col d-flex align-items-center">
                                            <c:choose>
                                                <c:when test="${requestScope.visitInfo.patientId ne null}">
                                                    <c:out value="${requestScope.visitInfo.patientId}"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:out value=""/>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>

                                    <tr class="row">
                                        <td class="col-5 d-flex align-items-center">
                                            <fmt:message bundle="${jspMessages}"
                                                         key="visitDetail.visitReason"/>
                                        </td>
                                        <td class="col d-flex align-items-center">
                                            <c:choose>
                                                <c:when test="${requestScope.visitInfo.visitReason ne null}">
                                                    <c:out value="${requestScope.visitInfo.visitReason.description}"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:out value=""/>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>

                                    <tr class="row">
                                        <td class="col-5 d-flex align-items-center">
                                            <fmt:message bundle="${jspMessages}"
                                                         key="visitDetail.patientVisitDescriptionInfo"/>
                                        </td>
                                        <td class="col d-flex align-items-center">
                                            <c:choose>
                                                <c:when test="${requestScope.visitInfo.patientVisitDescriptionInfo ne null}">
                                                    <c:out value="${requestScope.visitInfo.patientVisitDescriptionInfo}"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:out value=""/>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>

                                    <tr class="row">
                                        <td class="col-5 d-flex align-items-center">
                                            <fmt:message bundle="${jspMessages}"
                                                         key="visitDetail.responsibleDoctorId"/>
                                        </td>
                                        <td class="col d-flex align-items-center">
                                            <c:choose>
                                                <c:when test="${requestScope.visitInfo.responsibleDoctorId > 0}">
                                                    <c:out value="${requestScope.visitInfo.responsibleDoctorId}"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:out value=""/>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>

                                    <tr class="row">
                                        <td class="col-5 d-flex align-items-center">
                                            <fmt:message bundle="${jspMessages}"
                                                         key="visitDetail.responsibleDoctorInfo"/>
                                        </td>
                                        <td class="col d-flex align-items-center">
                                            <c:choose>
                                                <c:when test="${requestScope.visitInfo.responsibleDoctorId > 0}">
                                                    <c:out value="${requestScope.visitInfo.responsibleDoctorInfo}"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:out value=""/>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>

                                    <tr class="row">
                                        <td class="col-5 d-flex align-items-center">
                                            <fmt:message bundle="${jspMessages}"
                                                         key="visitDetail.transportationStatus"/>
                                        </td>
                                        <td class="col d-flex align-items-center">
                                            <c:choose>
                                                <c:when test="${requestScope.visitInfo.transportationStatus ne null}">
                                                    <c:out value="${requestScope.visitInfo.transportationStatus.description}"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:out value=""/>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>

                                    <c:choose>
                                        <c:when test="${requestScope.visitInfo.responsibleNonDoctorStaffId > 0}">
                                            <tr class="row">
                                                <td class="col-5 d-flex align-items-center">
                                                    <fmt:message
                                                            bundle="${jspMessages}"
                                                            key="visitDetail.responsibleNonDoctorStaffId"/>
                                                </td>
                                                <td class="col d-flex align-items-center">
                                                    <c:choose>
                                                        <c:when test="${requestScope.visitInfo.responsibleNonDoctorStaffId ne null}">
                                                            <c:out value="${requestScope.visitInfo.responsibleNonDoctorStaffId}"/>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:out value=""/>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                            </tr>

                                            <tr class="row">
                                                <td class="col-5 d-flex align-items-center">
                                                    <fmt:message
                                                            bundle="${jspMessages}"
                                                            key="visitDetail.responsibleNonDoctorStaffInfo"/>
                                                </td>
                                                <td class="col d-flex align-items-center">
                                                    <c:choose>
                                                        <c:when test="${requestScope.visitInfo.responsibleNonDoctorStaffInfo ne null}">
                                                            <c:out value="${requestScope.visitInfo.responsibleNonDoctorStaffInfo}"/>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:out value=""/>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                            </tr>
                                        </c:when>
                                        <c:otherwise> </c:otherwise>
                                    </c:choose>

                                    <tr class="row">
                                        <td class="col-5 d-flex align-items-center">
                                            <fmt:message
                                                    bundle="${jspMessages}"
                                                    key="visitDetail.patientComplaints"/>
                                        </td>
                                        <td class="col d-flex align-items-center">
                                            <c:choose>
                                                <c:when test="${requestScope.visitInfo.patientComplaints ne null}">
                                                    <c:out value="${requestScope.visitInfo.patientComplaints}"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:out value=""/>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                                <br>
                                <br>
                                <h3>
                                    <fmt:message bundle="${jspMessages}"
                                                 key="patients.diagnosisWhileVisit"/>
                                </h3>
                                <c:choose>
                                    <c:when test="${requestScope.diagnoses.size() > 0}">
                                        <div class="row d-flex mb-1 border">
                                            <div class="col">
                                                <fmt:message bundle="${jspMessages}"
                                                             key="visitDetail.diagnoses.diagnosisDateTime"/>
                                            </div>
                                            <div class="col">
                                                <fmt:message bundle="${jspMessages}"
                                                             key="visitDetail.diagnoses.diseaseInfo"/>
                                            </div>
                                            <div class="col">
                                                <fmt:message bundle="${jspMessages}"
                                                             key="visitDetail.diagnoses.diseaseDescription"/>
                                            </div>
                                            <div class="col">
                                                <fmt:message bundle="${jspMessages}"
                                                             key="visitDetail.diagnoses.doctorInfo"/>
                                            </div>
                                            <div class="col">
                                                <fmt:message bundle="${jspMessages}"
                                                             key="visitDetail.diagnoses.cancellationInfo"/>
                                            </div>
                                        </div>

                                        <c:forEach var="diagnosis"
                                                   items="${requestScope.diagnoses}">
                                            <div class="row d-flex mb-1 border align-items-center">
                                                <div class="col">
                                                    <c:out value="${diagnosis.diagnosisDateTime}"/>
                                                </div>
                                                <div class="col">
                                                    <c:out value="${diagnosis.diseaseInfo}"/>
                                                </div>
                                                <div class="col">
                                                    <c:out value="${diagnosis.diagnosisDescription}"/>
                                                </div>
                                                <div class="col">
                                                    <c:out value="${diagnosis.diagnoseDoctorInfo}"/>
                                                </div>
                                                <div class="col d-block">
                                                    <c:choose>
                                                        <c:when test="${diagnosis.cancellationDateTime ne null}">
                                                            <div>
                                                                <fmt:message
                                                                        bundle="${jspMessages}"
                                                                        key="visitDetail.cancellationDateTime"/>
                                                                <c:out value=": "/>
                                                                <c:out value="${diagnosis.cancellationDateTime}"/>
                                                            </div>
                                                            <div>
                                                                <fmt:message
                                                                        bundle="${jspMessages}"
                                                                        key="visitDetail.cancellationStaff"/>
                                                                <c:out value=": "/>
                                                                <c:out value="${diagnosis.cancellationDoctorInfo}"/>
                                                            </div>
                                                            <div>
                                                                <fmt:message
                                                                        bundle="${jspMessages}"
                                                                        key="visitDetail.cancellationReason"/>
                                                                <c:out value=": "/>
                                                                <c:out value="${diagnosis.cancellationDiagnosisDescription}"/>
                                                            </div>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <fmt:message
                                                                    bundle="${jspMessages}"
                                                                    key="visitDetail.actual"/>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <fmt:message bundle="${jspMessages}"
                                                     key="refusalReferenceInDetail.noDiagnosesRecords"/>
                                    </c:otherwise>
                                </c:choose>
                                <br>
                                <br>
                                <h3>
                                    <fmt:message bundle="${jspMessages}"
                                                 key="patients.medicinePrescriptionsWhileVisit"/>
                                </h3>
                                <c:choose>
                                    <c:when test="${requestScope.medicinePrescriptions ne null}">
                                        <div class="row d-flex mb-1 border">
                                            <div class="col">
                                                <fmt:message bundle="${jspMessages}"
                                                             key="visitDetail.prescriptionDateTime"/>
                                            </div>
                                            <div class="col">
                                                <fmt:message bundle="${jspMessages}"
                                                             key="visitDetail.prescribingDoctorInfo"/>
                                            </div>
                                            <div class="col">
                                                <fmt:message bundle="${jspMessages}"
                                                             key="visitDetail.medicineInfo"/>
                                            </div>
                                            <div class="col">
                                                <fmt:message bundle="${jspMessages}"
                                                             key="visitDetail.dosageInfo"/>
                                            </div>
                                            <div class="col">
                                                <fmt:message bundle="${jspMessages}"
                                                             key="visitDetail.prescriptionResult"/>
                                            </div>
                                        </div>
                                        <c:forEach var="medPrescription"
                                                   items="${requestScope.medicinePrescriptions}">
                                            <div class="row d-flex mb-1 border align-items-center">
                                                <div class="col">
                                                    <c:out value="${medPrescription.prescriptionDateTime}"/>
                                                </div>
                                                <div class="col">
                                                    <c:out value="${medPrescription.prescribingStaffInfo}"/>
                                                </div>
                                                <div class="col">
                                                    <c:out value="${medPrescription.medicineInfo}"/>
                                                </div>
                                                <div class="col">
                                                    <c:out value="${medPrescription.dosageQuantity}"/>
                                                    <c:out value=" "/>
                                                    <c:out value="${medPrescription.dosageMeasureUnit.shortName}"/>
                                                </div>
                                                <div class="col d-block">
                                                    <c:choose>
                                                        <c:when test="${medPrescription.executionDateTime ne null
                                            || medPrescription.patientDisagreementDateTime ne null}">

                                                        </c:when>
                                                        <c:otherwise>
                                                            <form action="${pageContext.request.contextPath}/profile"
                                                                  method="post">
                                                                <input type="hidden"
                                                                       name="command"
                                                                       value="cancel-medicine-prescription">
                                                                <input type="hidden"
                                                                       name="hiddenMedPrescriptionIdInput"
                                                                       value="${medPrescription.prescriptionId}">
                                                                <input type="hidden"
                                                                       name="hiddenVisitId"
                                                                       value="${requestScope.visitInfo.visitId}">
                                                                <button type="submit"
                                                                        class="btn align-self-center btn-primary ">
                                                                    <fmt:message
                                                                            bundle="${jspMessages}"
                                                                            key="visitDetail.cancelBtn"/>
                                                                </button>
                                                            </form>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <c:choose>
                                                        <c:when test="${medPrescription.executorStaffId > 0}">
                                                            <div>
                                                                <fmt:message
                                                                        bundle="${jspMessages}"
                                                                        key="visitDetail.executor"/>
                                                                <c:out value=": "/>
                                                                <c:out value="${medPrescription.executorStaffInfo}"/>
                                                            </div>
                                                            <div>
                                                                <fmt:message
                                                                        bundle="${jspMessages}"
                                                                        key="visitDetail.executionDateTime"/>
                                                                <c:out value=": "/>
                                                                <c:out value="${medPrescription.executionDateTime}"/>
                                                            </div>
                                                            <div>
                                                                <fmt:message
                                                                        bundle="${jspMessages}"
                                                                        key="visitDetail.executionResult"/>
                                                                <c:out value=": "/>
                                                                <c:out value="${medPrescription.executionDescription}"/>
                                                            </div>
                                                        </c:when>
                                                    </c:choose>
                                                    <c:choose>
                                                        <c:when test="${medPrescription.doesPatientAgree eq false}">
                                                            <div>
                                                                <fmt:message
                                                                        bundle="${jspMessages}"
                                                                        key="visitDetail.patientDisagrees"/>
                                                            </div>
                                                            <div>
                                                                <fmt:message
                                                                        bundle="${jspMessages}"
                                                                        key="visitDetail.disagreementDateTime"/>
                                                                <c:out value=": "/>
                                                                <c:out value="${medPrescription.patientDisagreementDateTime}"/>
                                                            </div>
                                                            <div>
                                                                <fmt:message
                                                                        bundle="${jspMessages}"
                                                                        key="visitDetail.disagreementDiscription"/>
                                                                <c:out value=": "/>
                                                                <c:out value="${medPrescription.patientDisagreementDescription}"/>
                                                            </div>
                                                        </c:when>
                                                    </c:choose>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <fmt:message bundle="${jspMessages}"
                                                     key="visitDetail.absenceOfSomething"/>
                                    </c:otherwise>
                                </c:choose>
                                <br>
                                <br>
                                <h3>
                                    <fmt:message bundle="${jspMessages}"
                                                 key="patients.prescriptionsWhileVisit"/>
                                </h3>
                                <c:choose>
                                    <c:when test="${requestScope.prescriptions ne null}">
                                        <%--header--%>
                                        <div class="row d-flex mb-1 border">
                                            <div class="col">
                                                <fmt:message bundle="${jspMessages}"
                                                             key="visitDetail.prescriptionDateTime"/>
                                            </div>
                                            <div class="col">
                                                <fmt:message bundle="${jspMessages}"
                                                             key="visitDetail.prescribingDoctorInfo"/>
                                            </div>
                                            <div class="col">
                                                <fmt:message bundle="${jspMessages}"
                                                             key="visitDetail.prescriptionDescription"/>
                                            </div>
                                            <div class="col">
                                                <fmt:message bundle="${jspMessages}"
                                                             key="visitDetail.controllingStaff"/>
                                            </div>
                                            <div class="col">
                                                <fmt:message bundle="${jspMessages}"
                                                             key="visitDetail.prescriptionResult"/>
                                            </div>
                                        </div>
                                        <c:forEach var="prescription"
                                                   items="${requestScope.prescriptions}">
                                            <div class="row d-flex mb-1 border align-items-center">
                                                <div class="col">
                                                    <c:out value="${prescription.prescriptionDateTime}"/>
                                                </div>
                                                <div class="col">
                                                    <c:out value="${prescription.prescribingStaffInfo}"/>
                                                </div>
                                                <div class="col">
                                                    <c:out value="${prescription.prescriptionDescription}"/>
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
                                                <div class="col d-block">
                                                    <c:choose>
                                                        <c:when test="${prescription.executionDateTime ne null || prescription.patientDisagreementDateTime ne null}">
                                                            <c:choose>
                                                                <c:when test="${prescription.executorStaffId > 0}">
                                                                    <div>
                                                                        <fmt:message
                                                                                bundle="${jspMessages}"
                                                                                key="visitDetail.executor"/>
                                                                        <c:out value=": "/>
                                                                        <c:out value="${prescription.executorStaffInfo}"/>
                                                                    </div>
                                                                    <div>
                                                                        <fmt:message
                                                                                bundle="${jspMessages}"
                                                                                key="visitDetail.executionDateTime"/>
                                                                        <c:out value=": "/>
                                                                        <c:out value="${prescription.executionDateTime}"/>
                                                                    </div>
                                                                    <div>
                                                                        <fmt:message
                                                                                bundle="${jspMessages}"
                                                                                key="visitDetail.executionResult"/>
                                                                        <c:out value=": "/>
                                                                        <c:out value="${prescription.executionDescription}"/>
                                                                    </div>
                                                                </c:when>
                                                            </c:choose>
                                                            <c:choose>
                                                                <c:when test="${prescription.doesPatientAgree eq false}">
                                                                    <div>
                                                                        <fmt:message
                                                                                bundle="${jspMessages}"
                                                                                key="visitDetail.patientDisagrees"/>
                                                                    </div>
                                                                    <div>
                                                                        <fmt:message
                                                                                bundle="${jspMessages}"
                                                                                key="visitDetail.disagreementDateTime"/>
                                                                        <c:out value=": "/>
                                                                        <c:out value="${prescription.patientDisagreementDateTime}"/>
                                                                    </div>
                                                                    <div>
                                                                        <fmt:message
                                                                                bundle="${jspMessages}"
                                                                                key="visitDetail.disagreementDiscription"/>
                                                                        <c:out value=": "/>
                                                                        <c:out value="${prescription.patientDisagreementDescription}"/>
                                                                    </div>
                                                                </c:when>
                                                            </c:choose>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <form action="${pageContext.request.contextPath}/profile"
                                                                  method="post">
                                                                <input type="hidden"
                                                                       name="command"
                                                                       value="cancel-non-medicine-prescription">
                                                                <input type="hidden"
                                                                       name="hiddenPrescriptionIdInput"
                                                                       value="${prescription.prescriptionId}">
                                                                <input type="hidden"
                                                                       name="hiddenVisitId"
                                                                       value="${requestScope.visitInfo.visitId}">
                                                                <button type="submit"
                                                                        class="btn align-self-center btn-primary ">
                                                                    <fmt:message
                                                                            bundle="${jspMessages}"
                                                                            key="visitDetail.cancelBtn"/>
                                                                </button>
                                                            </form>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <fmt:message bundle="${jspMessages}"
                                                     key="visitDetail.absenceOfSomething"/>
                                    </c:otherwise>
                                </c:choose>
                            </c:when>
                            <c:otherwise>
                                <fmt:message bundle="${jspMessages}"
                                             key="patient.noCurrentVisit"/>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>

<jsp:include page="structural_element/footer.jsp"/>

</body>
</html>
