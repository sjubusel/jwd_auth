<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="my" uri="/WEB-INF/tld/customTaglibPassport.tld" %>

<c:choose>
    <c:when test="${sessionScope.language eq null}">
        <fmt:setLocale value="ru_RU"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="${sessionScope.language}"/>
    </c:otherwise>
</c:choose>

<c:set var="activeMenuTab" value="staff" scope="page"/>
<c:set var="activeSubMenuStaffTab" value="allRefusalReferences" scope="page"/>

<fmt:setBundle basename="jspResources" var="jspMessages"/>
<fmt:setBundle basename="registrationRegExp" var="regEx"/>

<html>
<head>
    <title>
        <fmt:message bundle="${jspMessages}"
                     key="staff.refusalReferenceInDetail"/>
        <c:out value=" | "/>
        <fmt:message bundle="${jspMessages}"
                     key="staff.allRefusalReferences"/>
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
                                     key="refusalReferenceInDetail.heading"/>
                        <c:out value=" № "/>
                        <c:out value="${requestScope.refusalReference.refusalReferenceId}"/>
                    </h1>
                    <div class="row">
                        <div class="col">
                            <fmt:message bundle="${jspMessages}"
                                         key="refusalReferenceInDetail.recepient"/>
                        </div>
                        <div class="col">
                            <c:out value="${requestScope.refusalReference.patientInfo.firstName}"/>
                            <c:out value=" "/>
                            <c:if test="${requestScope.refusalReference.patientInfo ne null}">
                                <c:out value="${requestScope.refusalReference.patientInfo.middleName}"/>
                                <c:out value=" "/>
                            </c:if>
                            <c:out value="${requestScope.refusalReference.patientInfo.lastName}"/>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col">
                            <fmt:message bundle="${jspMessages}"
                                         key="refusalReferenceInDetail.birthday"/>
                        </div>
                        <div class="col">
                            <c:out value="${requestScope.refusalReference.patientInfo.birthday}"/>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col">
                            <fmt:message bundle="${jspMessages}"
                                         key="refusalReferenceInDetail.gender"/>
                        </div>
                        <div class="col">
                            <c:out value="${requestScope.refusalReference.patientInfo.gender.genderValue}"/>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col">
                            <fmt:message bundle="${jspMessages}"
                                         key="refusalReferenceInDetail.homeAddress"/>
                        </div>
                        <div class="col">
                            <my:printAddress
                                    address="${requestScope.refusalReference.patientInfo.homeAddress}"/>
                        </div>
                    </div>
                    <br>
                    <h3>
                        <fmt:message bundle="${jspMessages}"
                                     key="refusalReferenceInDetail.additionalMedicalInformation"/>
                    </h3>
                    <div class="row">
                        <div class="col">
                            <fmt:message bundle="${jspMessages}"
                                         key="refusalReferenceInDetail.visitDateTime"/>
                        </div>
                        <div class="col">
                            <c:out value="${requestScope.refusalReference.visitInfo.visitDateTime}"/>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col">
                            <fmt:message bundle="${jspMessages}"
                                         key="refusalReferenceInDetail.referenceDatetime"/>
                        </div>
                        <div class="col">
                            <c:out value="${requestScope.refusalReference.referenceDatetime}"/>
                        </div>
                    </div>
                    <br>
                    <h3>
                        <fmt:message bundle="${jspMessages}"
                                     key="refusalReferenceInDetail.medicalExamination"/>
                    </h3>
                    <c:choose>
                        <c:when test="${requestScope.refusalReference.prescriptions.size() > 0}">
                            <%--header--%>
                            <div class="row d-flex mb-1 border">
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="visitDetail.isThisPrescriptionComplete"/>
                                </div>
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
                                       items="${requestScope.refusalReference.prescriptions}">
                                <div class="row d-flex mb-1 border align-items-center">
                                    <div class="col">
                                        <c:choose>
                                            <c:when test="${prescription.isPrescriptionComplete eq true}">
                                                <img src="${pageContext.request.contextPath}/img/plus.png"
                                                     alt="plus">
                                            </c:when>
                                            <c:otherwise>
                                                <img src="${pageContext.request.contextPath}/img/minus.png"
                                                     alt="minus">
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
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
                                        </c:choose>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <fmt:message bundle="${jspMessages}"
                                         key="refusalReferenceInDetail.noMedicalExamination"/>
                        </c:otherwise>
                    </c:choose>
                    <br>
                    <h3>
                        <fmt:message bundle="${jspMessages}"
                                     key="refusalReferenceInDetail.performedAid"/>
                    </h3>
                    <c:choose>
                        <c:when test="${requestScope.refusalReference.medPrescriptions.size() > 0}">
                            <div class="row d-flex mb-1 border">
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="visitDetail.isThisPrescriptionComplete"/>
                                </div>
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
                                       items="${requestScope.refusalReference.medPrescriptions}">
                                <div class="row d-flex mb-1 border align-items-center">
                                    <div class="col">
                                        <c:choose>
                                            <c:when test="${medPrescription.isPrescriptionComplete eq true}">
                                                <img src="${pageContext.request.contextPath}/img/plus.png"
                                                     alt="plus">
                                            </c:when>
                                            <c:otherwise>
                                                <img src="${pageContext.request.contextPath}/img/minus.png"
                                                     alt="minus">
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
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
                                        <div>
                                            <fmt:message bundle="${jspMessages}"
                                                         key="visitDetail.targetApplicationDateTime"/>
                                            <c:out value=": "/>
                                            <c:out value="${medPrescription.targetApplicationDateTime}"/>
                                        </div>
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
                                         key="refusalReferenceInDetail.noAppliedAid"/>
                        </c:otherwise>
                    </c:choose>
                    <br>
                    <h3>
                        <fmt:message bundle="${jspMessages}"
                                     key="refusalReferenceInDetail.conclusion"/>
                    </h3>
                    <c:choose>
                        <c:when test="${requestScope.refusalReference.diagnoses.size() > 0}">
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
                                       items="${requestScope.refusalReference.diagnoses}">
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
                    <h3>
                        <fmt:message bundle="${jspMessages}"
                                     key="refusalReferenceInDetail.recommendation"/>
                    </h3>
                    <h6>
                        <fmt:message bundle="${jspMessages}"
                                     key="refusalReferenceInDetail.recommendedMedicines"/>
                    </h6>
                    <%-- TODO refusal medicine prescriptions--%>
                    <div class="row">
                        <div class="col">
                            <h6>
                                <fmt:message bundle="${jspMessages}"
                                             key="refusalReferenceInDetail.otherRecommendations"/>
                            </h6>
                        </div>
                        <div class="col">
                            <c:out value="${requestScope.refusalReference.refusalRecommendations}"/>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>

    </div>
</div>

<jsp:include page="../structural_element/footer.jsp"/>
</body>
</html>
