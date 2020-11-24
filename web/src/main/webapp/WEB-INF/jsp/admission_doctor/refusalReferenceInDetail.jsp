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
                    <%--prescriptions--%>
                    <br>
                    <h3>
                        <fmt:message bundle="${jspMessages}"
                                     key="refusalReferenceInDetail.performedAid"/>
                    </h3>
                    <%--medicine prescriptions--%>
                    <br>
                    <h3>
                        <fmt:message bundle="${jspMessages}"
                                     key="refusalReferenceInDetail.conclusion"/>
                    </h3>
                    <%--medicine prescriptions--%>
                    <br>
                    <h3>
                        <fmt:message bundle="${jspMessages}"
                                     key="refusalReferenceInDetail.recommendation"/>
                    </h3>
                    <h6>
                        <fmt:message bundle="${jspMessages}"
                                     key="refusalReferenceInDetail.recommendedMedicines"/>
                    </h6>
                    <%--refusal medicine prescriptions--%>
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
