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
<c:set var="activeSubMenuStaffTab" value="visitsOnControl" scope="page"/>

<fmt:setBundle basename="jspResources" var="jspMessages"/>

<html>
<head>
    <title>
        <fmt:message bundle="${jspMessages}"
                     key="admissionDoctorSubMenu.visitDetail"/>
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
                <%--CONTENT ITSELF--%>
                <c:otherwise>
                    <h1>
                        <fmt:message bundle="${jspMessages}"
                                     key="visitDetail.headingVisitInfo"/>
                    </h1>
                    <c:choose>
                        <c:when test="${requestScope.changeResult ne null}">
                            <c:if test="${requestScope.changeResult eq 'success'}">
                                <div class="alert alert-success" role="alert">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="visitDetail.changeResultSuccess"/>
                                </div>
                            </c:if>
                            <c:if test="${requestScope.changeResult eq 'techError'}">
                                <div class="alert alert-danger" role="alert">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="visitDetail.changeResultTechError"/>
                                </div>
                            </c:if>
                            <c:if test="${requestScope.changeResult eq 'validationError'}">
                                <div class="alert alert-danger" role="alert">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="visitDetail.changeResultValidationError"/>
                                </div>
                            </c:if>
                        </c:when>
                    </c:choose>
                    <c:choose>
                        <c:when test="${requestScope.visitInfo ne null}">
                            <table class="table">
                                <tbody>
                                <tr class="row">
                                    <td class="col-5 d-flex align-items-center">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="visitDetail.isPrescriptionsComplete"/>
                                    </td>
                                    <td class="col d-flex align-items-center">
                                        <c:choose>
                                            <c:when test="${(requestScope.visitInfo.isPrescriptionsComplete eq true)}">
                                                <img src="${pageContext.request.contextPath}/img/plus.png"
                                                     alt="plus">
                                            </c:when>
                                            <c:otherwise>
                                                <img src="${pageContext.request.contextPath}/img/minus.png"
                                                     alt="minus">
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
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
                                                     key="visitDetail.patientShortInfo"/>
                                    </td>
                                    <td class="col d-flex align-items-center">
                                        <c:choose>
                                            <c:when test="${requestScope.visitInfo.patientShortInfo ne null}">
                                                <c:out value="${requestScope.visitInfo.patientShortInfo}"/>
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
                                            <c:when test="${requestScope.visitInfo.responsibleDoctorId ne null}">
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
                                            <c:when test="${requestScope.visitInfo.responsibleDoctorInfo ne null}">
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
                                        <label for="complaints">
                                            <fmt:message bundle="${jspMessages}"
                                                         key="visitDetail.patientComplaints"/>
                                        </label>
                                    </td>
                                    <td class="col d-flex align-items-center">
                                        <form action="${pageContext.request.contextPath}/profile?command=change-complaints-of-patient"
                                              method="post" class="d-block">
                                            <textarea type="text"
                                                      name="complaintsInput"
                                                      id="complaints"
                                                      rows="10" cols="60"
                                                      placeholder="<fmt:message bundle="${jspMessages}" key="visitDetail.patientComplaintsPlaceholder"/>"><c:choose><c:when
                                                    test="${requestScope.visitInfo.patientComplaints ne null}"><c:out
                                                    value="${requestScope.visitInfo.patientComplaints}"/></c:when><c:otherwise><c:out
                                                    value=""/></c:otherwise></c:choose></textarea>
                                            <br/>
                                            <input type="hidden"
                                                   name="visitNumberInput"
                                                   value="${requestScope.visitInfo.visitId}">
                                            <button type="submit"
                                                    class="btn align-self-center btn-primary">
                                                <fmt:message
                                                        bundle="${jspMessages}"
                                                        key="visitDetail.complaintsButton"/>
                                            </button>
                                        </form>
                                    </td>
                                </tr>

                                <tr class="row">
                                    <td class="col-5 d-flex align-items-center">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="visitDetail.visitResult"/>
                                    </td>
                                    <td class="col d-flex align-items-center">
                                        <c:choose>
                                            <c:when test="${requestScope.visitInfo.visitResult ne null}">
                                                <c:out value="${requestScope.visitInfo.visitResult.description}"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value=""/>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>

                                <c:choose>
                                    <c:when test="${requestScope.visitInfo.hospitalizationDepartmentId > 0}">
                                        <tr class="row">
                                            <td class="col-5 d-flex align-items-center">
                                                <fmt:message
                                                        bundle="${jspMessages}"
                                                        key="visitDetail.hospitalizationDepartmentId"/>
                                            </td>
                                            <td class="col d-flex align-items-center">
                                                <c:choose>
                                                    <c:when test="${requestScope.visitInfo.hospitalizationDepartmentId ne null}">
                                                        <c:out value="${requestScope.visitInfo.hospitalizationDepartmentId}"/>
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
                                                        key="visitDetail.hospitalizationDepartmentInfo"/>
                                            </td>
                                            <td class="col d-flex align-items-center">
                                                <c:choose>
                                                    <c:when test="${requestScope.visitInfo.hospitalizationDepartmentInfo ne null}">
                                                        <c:out value="${requestScope.visitInfo.hospitalizationDepartmentInfo}"/>
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
                                </tbody>
                            </table>
                        </c:when>
                        <c:otherwise>
                            <fmt:message bundle="${jspMessages}"
                                         key="visitDetail.absenceOfSomething"/>
                        </c:otherwise>
                    </c:choose>
                    <br>
                    <br>
                    <h1>
                        <fmt:message bundle="${jspMessages}"
                                     key="visitDetail.headingPersonalInformation"/>
                    </h1>
                    <c:choose>
                        <c:when test="${requestScope.patientInfo ne null}">
                            <table class="table">
                                <tbody>
                                    <%--1--%>
                                <tr class="row">
                                    <td class="col-3 d-flex align-items-center">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="profile.photo"/>
                                    </td>
                                    <td class="col d-flex align-items-center">
                                        <img
                                        <c:choose>
                                        <c:when test="${requestScope.patientInfo.photoPath ne null}">
                                                src="http://${pageContext.request.serverName}:${pageContext.request.serverPort}/upload/user_photo/${requestScope.patientInfo.photoPath}"
                                        </c:when>
                                        <c:otherwise>
                                                src="${pageContext.request.contextPath}/img/user.png"
                                        </c:otherwise>
                                        </c:choose>
                                                class="rounded "
                                                style="height: 200px; width:auto"
                                                alt="<fmt:message bundle="${jspMessages}"
                                                  key="profile.photo"/>">
                                    </td>
                                </tr>
                                    <%--2--%>
                                <tr class="row">
                                    <td class="col-3 d-flex align-items-center">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="register.firstNameInputLabel"/>
                                    </td>
                                    <td class="col d-flex align-items-center">
                                            <%--<c:out value="${requestScope.patientInfo.}"/>--%>
                                        <c:choose>
                                            <c:when test="${requestScope.patientInfo.firstName ne null}">
                                                <c:out value="${requestScope.patientInfo.firstName}"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value=""/>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                                    <%--3--%>
                                <tr class="row">
                                    <td class="col-3 d-flex align-items-center">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="register.middleNameInputLabel"/>
                                    </td>
                                    <td class="col d-flex align-items-center">
                                        <c:choose>
                                            <c:when test="${requestScope.patientInfo.middleName ne null}">
                                                <c:out value="${requestScope.patientInfo.middleName}"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value=""/>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                                    <%--4--%>
                                <tr class="row">
                                    <td class="col-3 d-flex align-items-center">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="register.lastNameInputLabel"/>
                                    </td>
                                    <td class="col d-flex align-items-center">
                                        <c:choose>
                                            <c:when test="${requestScope.patientInfo.lastName ne null}">
                                                <c:out value="${requestScope.patientInfo.lastName}"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value=""/>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                                    <%--5--%>
                                <tr class="row">
                                    <td class="col-3 d-flex align-items-center">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="register.birthdayInputLabel"/>
                                    </td>
                                    <td class="col d-flex align-items-center">
                                            <%--  TODO make personal tag                              --%>
                                        <c:choose>
                                            <c:when test="${requestScope.patientInfo.birthday ne null}">
                                                <c:out value="${requestScope.patientInfo.birthday}"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value=""/>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                                    <%--6--%>
                                <tr class="row">
                                    <td class="col-3 d-flex align-items-center">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="register.GenderInputLabel"/>
                                    </td>
                                    <td class="col d-flex align-items-center">
                                        <c:choose>
                                            <c:when test="${requestScope.patientInfo.gender ne null}">
                                                <c:out value="${requestScope.patientInfo.gender.genderValue}"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value=""/>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                                    <%--7--%>
                                <tr class="row">
                                    <td class="col-3 d-flex align-items-center">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="register.emailInputLabel"/>
                                    </td>
                                    <td class="col d-flex align-items-center">
                                        <c:choose>
                                            <c:when test="${requestScope.patientInfo.email ne null}">
                                                <my:masker
                                                        email="${requestScope.patientInfo.email}"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value=""/>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                                    <%--8--%>
                                <tr class="row">
                                    <td class="col-3 d-flex align-items-center">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="register.phoneNumberInputLabel"/>
                                    </td>
                                    <td class="col d-flex align-items-center">
                                        <c:choose>
                                            <c:when test="${requestScope.patientInfo.phoneNumber ne null}">
                                                <c:out value="${requestScope.patientInfo.phoneNumber}"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value=""/>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                                    <%--9--%>
                                <tr class="row">
                                    <td class="col-3 d-flex align-items-center">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="profile.maritalStatus"/>
                                    </td>
                                    <td class="col d-flex align-items-center">
                                        <c:choose>
                                            <c:when test="${requestScope.patientInfo.maritalStatus ne null}">
                                                <c:out value="${requestScope.patientInfo.maritalStatus.description}"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value=""/>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                                    <%--10--%>
                                <tr class="row">
                                    <td class="col-3 d-flex align-items-center">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="profile.identityDocument"/>
                                    </td>
                                    <td class="col d-flex align-items-center">
                                        <c:choose>
                                            <c:when test="${requestScope.patientInfo.identityDocument ne null}">
                                                <my:printIdentityDocument
                                                        idDoc="${requestScope.patientInfo.identityDocument}"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value=""/>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                                    <%--11--%>
                                <tr class="row">
                                    <td class="col-3 d-flex align-items-center">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="profile.homeAddress"/>
                                    </td>
                                    <td class="col d-flex align-items-center">
                                        <c:choose>
                                            <c:when test="${requestScope.patientInfo.homeAddress ne null}">
                                                <my:printAddress
                                                        address="${requestScope.patientInfo.homeAddress}"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value=""/>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                                    <%--12--%>
                                <tr class="row">
                                    <td class="col-3 d-flex align-items-center">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="profile.inCaseOfEmergencyContactPerson"/>
                                    </td>
                                    <td class="col d-flex align-items-center">
                                        <c:choose>
                                            <c:when test="${requestScope.patientInfo.inCaseOfEmergencyContactPersonInfo ne null}">
                                                <c:out value="${requestScope.patientInfo.inCaseOfEmergencyContactPersonInfo}"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value=""/>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                                    <%--13--%>
                                <tr class="row">
                                    <td class="col-3 d-flex align-items-center">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="profile.inCaseOfEmergencyPhoneOfContactPerson"/>
                                    </td>
                                    <td class="col d-flex align-items-center">
                                        <c:choose>
                                            <c:when test="${requestScope.patientInfo.inCaseOfEmergencyContactPersonPhone ne null}">
                                                <c:out value="${requestScope.patientInfo.inCaseOfEmergencyContactPersonPhone}"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value=""/>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                                    <%--14--%>
                                <tr class="row">
                                    <td class="col-3 d-flex align-items-center">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="profile.bloodType"/>
                                    </td>
                                    <td class="col d-flex align-items-center">
                                        <c:choose>
                                            <c:when test="${requestScope.patientInfo.bloodType ne null}">
                                                <c:out value="${requestScope.patientInfo.bloodType.value}"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value=""/>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                                    <%--15--%>
                                <tr class="row">
                                    <td class="col-3 d-flex align-items-center">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="profile.bloodRh"/>
                                    </td>
                                    <td class="col d-flex align-items-center">
                                        <c:choose>
                                            <c:when test="${requestScope.patientInfo.rhBloodGroup ne null}">
                                                <c:out value="${requestScope.patientInfo.rhBloodGroup.description}"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value=""/>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                                    <%--16--%>
                                <tr class="row">
                                    <td class="col-3 d-flex align-items-center">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="profile.disabilityDegree"/>
                                    </td>
                                    <td class="col d-flex align-items-center">
                                        <c:choose>
                                            <c:when test="${requestScope.patientInfo.disabilityDegree ne null}">
                                                <c:out value="${requestScope.patientInfo.disabilityDegree.description}"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value=""/>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                                    <%--17--%>
                                <tr class="row">
                                    <td class="col-3 d-flex align-items-center">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="profile.transportationStatus"/>
                                    </td>
                                    <td class="col d-flex align-items-center">
                                        <c:choose>
                                            <c:when test="${requestScope.patientInfo.transportationStatus ne null}">
                                                <c:out value="${requestScope.patientInfo.transportationStatus.description}"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value=""/>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                                    <%--18--%>
                                <tr class="row">
                                    <td class="col-3 d-flex align-items-center">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="profile.allergicReactionsPresence"/>
                                    </td>
                                    <td class="col d-flex align-items-center">
                                        <c:choose>
                                            <c:when test="${requestScope.patientInfo.hasAllergicReactions == false}">
                                                <fmt:message
                                                        bundle="${jspMessages}"
                                                        key="profile.absence"/>
                                            </c:when>
                                            <c:otherwise>
                                                <fmt:message
                                                        bundle="${jspMessages}"
                                                        key="profile.presence"/>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                                    <%--19--%>
                                <tr class="row">
                                    <td class="col-3 d-flex align-items-center">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="profile.extremelyHazardousDiseasesPresence"/>
                                    </td>
                                    <td class="col d-flex align-items-center">
                                        <c:choose>
                                            <c:when test="${requestScope.patientInfo.hasExtremelyHazardousDiseases == false}">
                                                <fmt:message
                                                        bundle="${jspMessages}"
                                                        key="profile.absence"/>
                                            </c:when>
                                            <c:otherwise>
                                                <fmt:message
                                                        bundle="${jspMessages}"
                                                        key="profile.presence"/>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </c:when>
                        <c:otherwise>
                            <fmt:message bundle="${jspMessages}"
                                         key="visitDetail.absenceOfSomething"/>
                        </c:otherwise>
                    </c:choose>
                    <br>
                    <br>
                    <h1 class="text-left">
                        <fmt:message bundle="${jspMessages}"
                                     key="profileSubMenu.allergicReactionsFood.heading"/>
                    </h1>
                    <c:choose>
                        <c:when test="${requestScope.allergicFoodReactions ne null}">
                            <%--header--%>
                            <div class="row d-flex mb-1 border">
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="profileSubMenu.allergicReactionsFood.foodType"/>
                                </div>
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="profileSubMenu.allergicReactionsFood.detectionData"/>
                                </div>
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="profileSubMenu.allergicReactionsFood.description"/>
                                </div>
                            </div>
                            <%--contents--%>
                            <c:forEach var="allergicFoodReactions"
                                       items="${requestScope.allergicFoodReactions}">
                                <div class="row d-flex mb-1 border align-items-center">
                                        <%--1st column--%>
                                    <div class="col">
                                        <c:out value="${allergicFoodReactions.foodTypeInfo}"/>
                                    </div>
                                    <div class="col">
                                        <c:out value="${allergicFoodReactions.detectionDate}"/>
                                    </div>
                                    <div class="col">
                                        <c:if test="${allergicFoodReactions.allergicDescription ne null}">
                                            <c:out value="${allergicFoodReactions.allergicDescription}"/>
                                        </c:if>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <fmt:message bundle="${jspMessages}"
                                         key="profileSubMenu.allergicReactionsFood.noRecordsMessage"/>
                        </c:otherwise>
                    </c:choose>
                    <br>
                    <br>
                    <h1 class="text-left">
                        <fmt:message bundle="${jspMessages}"
                                     key="profileSubMenu.allergicReactionsMedicine.heading"/>
                    </h1>
                    <c:choose>
                        <c:when test="${requestScope.allergicMedicineReactions ne null}">
                            <%--header--%>
                            <div class="row d-flex mb-1 border">
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="profileSubMenu.allergicMedicineReactions.medicineName"/>
                                </div>
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="profileSubMenu.allergicMedicineReactions.detectionData"/>
                                </div>
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="profileSubMenu.allergicMedicineReactions.description"/>
                                </div>
                            </div>
                            <%--contents--%>
                            <c:forEach var="allergicMedicineReactions"
                                       items="${requestScope.allergicMedicineReactions}">
                                <div class="row d-flex mb-1 border align-items-center">
                                        <%--1st column--%>
                                    <div class="col">
                                        <c:out value="${allergicMedicineReactions.medicineDescription}"/>
                                    </div>
                                    <div class="col">
                                        <c:out value="${allergicMedicineReactions.detectionDate}"/>
                                    </div>
                                    <div class="col">
                                        <c:if test="${allergicMedicineReactions.allergicReaction ne null}">
                                            <c:out value="${allergicMedicineReactions.allergicReaction}"/>
                                        </c:if>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <fmt:message bundle="${jspMessages}"
                                         key="profileSubMenu.allergicMedicineReactions.noRecordsMessage"/>
                        </c:otherwise>
                    </c:choose>
                    <br>
                    <br>
                    <h1 class="text-left">
                        <fmt:message bundle="${jspMessages}"
                                     key="profileSubMenu.extremelyHazardousDiseases.heading"/>
                    </h1>
                    <c:choose>
                        <c:when test="${requestScope.extremelyHazardousDiseases ne null}">
                            <%--header--%>
                            <div class="row d-flex mb-1 border">
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="profileSubMenu.extremelyHazardousDiseases.columnName"/>
                                </div>
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="profileSubMenu.extremelyHazardousDiseases.columnDetectionDate"/>
                                </div>
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="profileSubMenu.extremelyHazardousDiseases.caseDescription"/>
                                </div>
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="profileSubMenu.extremelyHazardousDiseases.recoveryDate"/>
                                </div>
                            </div>
                            <%--contents--%>
                            <c:forEach var="extremelyHazardousDiseases"
                                       items="${requestScope.extremelyHazardousDiseases}">
                                <div class="row d-flex mb-1 border align-items-center">
                                        <%--1st column--%>
                                    <div class="col">
                                        <c:out value="${extremelyHazardousDiseases.diseaseDescription}"/>
                                    </div>
                                    <div class="col">
                                        <c:out value="${extremelyHazardousDiseases.detectionDate}"/>
                                    </div>
                                    <div class="col">
                                        <c:if test="${extremelyHazardousDiseases.caseDescription ne null}">
                                            <c:out value="${extremelyHazardousDiseases.caseDescription}"/>
                                        </c:if>
                                    </div>
                                    <div class="col">
                                        <c:if test="${extremelyHazardousDiseases.recoveryDate ne null}">
                                            <c:out value="${extremelyHazardousDiseases.recoveryDate}"/>
                                        </c:if>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <fmt:message bundle="${jspMessages}"
                                         key="profileSubMenu.extremelyHazardousDiseases.noRecords"/>
                        </c:otherwise>
                    </c:choose>
                    <br>
                    <br>
                    <h1>
                        <fmt:message bundle="${jspMessages}"
                                     key="visitDetail.headingDiagnoses"/>
                    </h1>
                    <c:choose>
                        <c:when test="${requestScope.allDiagnoses ne null}">
                            <div class="row d-flex mb-1 border">
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="visitDetail.diagnoses.departmentType"/>
                                </div>
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
                            <div class="overflow-auto"
                                 style="max-height: 250px">
                                <c:forEach var="diagnosis"
                                           items="${requestScope.allDiagnoses}">
                                    <div class="row d-flex mb-1 border align-items-center">
                                        <div class="col">
                                            <c:out value="${diagnosis.departmentOrigin.description}"/>
                                        </div>
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
                            </div>
                        </c:when>
                        <c:otherwise>
                            <fmt:message bundle="${jspMessages}"
                                         key="visitDetail.diagnoses.noRecords"/>
                        </c:otherwise>
                    </c:choose>
                    <br>
                    <br>
                    <h1>
                        <fmt:message bundle="${jspMessages}"
                                     key="visitDetail.currentPrescriptions"/>
                    </h1>
                    <c:choose>
                        <c:when test="${requestScope.prescriptions ne null}">
                            <%--header--%>
                            <c:choose>
                                <c:when test="${requestScope.cancelPrescriptionResult ne null}">
                                    <c:if test="${requestScope.cancelPrescriptionResult eq 'success'}">
                                        <div class="alert alert-success" role="alert">
                                            <fmt:message bundle="${jspMessages}"
                                                         key="visitDetail.cancelPrescriptionResultSuccess"/>
                                        </div>
                                    </c:if>
                                    <c:if test="${requestScope.cancelPrescriptionResult eq 'techError'}">
                                        <div class="alert alert-danger" role="alert">
                                            <fmt:message bundle="${jspMessages}"
                                                         key="visitDetail.cancelPrescriptionResultTechError"/>
                                        </div>
                                    </c:if>
                                    <c:if test="${requestScope.cancelPrescriptionResult eq 'validationError'}">
                                        <div class="alert alert-danger" role="alert">
                                            <fmt:message bundle="${jspMessages}"
                                                         key="visitDetail.cancelPrescriptionResultValidationError"/>
                                        </div>
                                    </c:if>
                                </c:when>
                            </c:choose>
                            <a id="prescriptionsTable"></a>
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
                                       items="${requestScope.prescriptions}">
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
                                            <c:otherwise>
                                                <form action="${pageContext.request.contextPath}/profile"
                                                      method="post">
                                                    <input type="hidden"
                                                           name="command"
                                                        <%-- TODO cancel prescription --%>
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
                    <br>
                    <br>
                    <h1>
                        <fmt:message bundle="${jspMessages}"
                                     key="visitDetail.headingMedicinePrescriptions"/>
                    </h1>
                    <c:choose>
                        <c:when test="${requestScope.medicinePrescriptions ne null}">
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
                                       items="${requestScope.medicinePrescriptions}">
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
                                         key="visitDetail.absenceOfSomething"/>
                        </c:otherwise>
                    </c:choose>
                    <br>
                    <br>
                    <h1>
                        <fmt:message bundle="${jspMessages}"
                                     key="visitDetail.admittingDoctorMenu"/>
                    </h1>
                    <div class="list-group">
                        <a href="${pageContext.request.contextPath}/profile?command=go-to-establish-diagnosis&hiddenVisitId=${requestScope.visitInfo.visitId}"
                           target="_blank"
                           class="list-group-item list-group-item-action">
                            <fmt:message bundle="${jspMessages}"
                                         key="visitDetail.establishDiagnosis"/>
                        </a>
                        <a href="${pageContext.request.contextPath}/profile?command=go-to-establish-med-prescription&hiddenVisitId=${requestScope.visitInfo.visitId}"
                           target="_blank"
                           class="list-group-item list-group-item-action">
                            <fmt:message bundle="${jspMessages}"
                                         key="visitDetail.establishMedPrescription"/>
                        </a>
                        <a href="${pageContext.request.contextPath}/profile?command=go-to-establish-prescription&hiddenVisitId=${requestScope.visitInfo.visitId}"
                           target="_blank"
                           class="list-group-item list-group-item-action">
                            <fmt:message bundle="${jspMessages}"
                                         key="visitDetail.establishPresription"/>
                        </a>
                        <a href="${pageContext.request.contextPath}/profile?command=go-to-make-visit-decision&hiddenVisitId=${requestScope.visitInfo.visitId}"
                           class="list-group-item list-group-item-action">
                            <fmt:message bundle="${jspMessages}"
                                         key="visitDetail.makeDecision"/>
                        </a>
                    </div>
                </c:otherwise>
            </c:choose>


            <%--CONTENTS END--%>
        </div>
    </div>
</div>

<jsp:include page="../structural_element/footer.jsp"/>
</body>
</html>
