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
<c:set var="activeSubMenuProfileTab" value="changePatientInfo" scope="page"/>

<fmt:setBundle basename="jspResources" var="jspMessages"/>

<html>
<head>
    <title>
        <fmt:message bundle="${jspMessages}"
                     key="profileSubMenu.changePatientInfo"/>
        <c:out value=" | "/>
        <fmt:message bundle="${jspMessages}" key="profile.htmlTitle"/>
        <c:out value=" | "/>
        <fmt:message bundle="${jspMessages}" key="all.htmlTitle"/>
    </title>
    <jsp:include page="../structural_element/metahead.jsp"/>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/jquery-3.5.1.js">
    </script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/jquery.maskedinput.js">
    </script>
    <script>
        jQuery(document).ready(function () {
            $("#phoneNumberCountryCodeInput").mask("+9?99");
            $("#phoneNumberInnerCodeInput").mask("9?99");
            $("#phoneNumberInnerNumberInput").mask("999-99-99");
        });
    </script>
</head>
<body>

<div class="main-content">
    <%@ include file="../structural_element/header.jsp" %>
    <c:choose>
        <c:when test="${requestScope.error ne null}">
            <c:if test="${requestScope.error eq 'tech'}">
                <fmt:message bundle="${jspMessages}" key="profile.techError"/>
            </c:if>
            <c:if test="${requestScope.error eq 'val'}">
                <fmt:message bundle="${jspMessages}" key="profile.valError"/>
            </c:if>
        </c:when>
        <c:otherwise>
            <div class="row mt-2 mr-2 ml-2">
                <%@ include file="../structural_element/profileSubMenu.jsp" %>
                <div class="bg-light d-inline-block col">
                    <h1 class="text-left">
                        <fmt:message bundle="${jspMessages}"
                                     key="profileSubMenu.changePatientInfo.headingChangeAvatar"/>
                    </h1>
                        <%--Photo-change            --%>
                    <form action="${pageContext.request.contextPath}/profile"
                          method="post"
                          enctype="multipart/form-data">
                            <%-- TODO add i18n everywhere--%>
                        <c:if test="${requestScope.photoUpload ne null}">
                            <c:choose>
                                <c:when test="${requestScope.photoUpload eq 'success'}">
                                    <div class="alert alert-success"
                                         role="alert">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="profileSubMenu.changePatientInfo.successfulPhotoUpload"/>
                                    </div>
                                </c:when>
                                <c:when test="${requestScope.photoUpload eq 'techError'}">
                                    <div class="alert alert-danger"
                                         role="alert">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="profileSubMenu.changePatientInfo.techError"/>
                                    </div>
                                </c:when>
                                <c:when test="${requestScope.photoUpload eq 'incorrectFileName'}">
                                    <div class="alert alert-danger"
                                         role="alert">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="profileSubMenu.changePatientInfo.incorrectFileName"/>
                                    </div>
                                </c:when>
                                <c:when test="${requestScope.photoUpload eq 'validationError'}">
                                    <div class="alert alert-danger"
                                         role="alert">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="profileSubMenu.changePatientInfo.validationError"/>
                                    </div>
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
                        <input type="hidden" name="command"
                               value="profile-change-patient-photo"/>
                        <div class="form-group d-flex justify-content-center">
                                <%--                    <label for="photo"--%>
                                <%--                           class="align-self-center col-4 custom-form-label">--%>
                                <%--                        <fmt:message bundle="${jspMessages}"--%>
                                <%--                                     key="profile.photo"/>--%>
                                <%--                    </label>--%>
                            <img
                            <c:choose>
                            <c:when test="${requestScope.patientInfo.photoPath ne null}">
                                    src="http://${pageContext.request.serverName}:${pageContext.request.serverPort}/upload/user_photo/${requestScope.patientInfo.photoPath}"
                            </c:when>
                            <c:otherwise>
                                    src="${pageContext.request.contextPath}/img/user.png"
                            </c:otherwise>
                            </c:choose>
                                    class="rounded align-self-center"
                                    style="height: 200px; width:auto"
                                    alt="<fmt:message bundle="${jspMessages}"
                                        key="profile.photo"/>">

                            <input type="file"
                                   class="form-control-file align-self-center col-5"
                                   id="photo" name="photoUploadInput"
                                   accept="image/*"
                                   required>

                            <button type="submit"
                                    class="btn align-self-center btn-primary">
                                <fmt:message bundle="${jspMessages}"
                                             key="profileSubMenu.changePatientInfo.changePhoto"/>
                            </button>
                        </div>
                    </form>

                    <br>

                    <h1 class="text-left">
                        <fmt:message bundle="${jspMessages}"
                                     key="profileSubMenu.changePatientInfo.headingChangeAvailablePatientData"/>
                    </h1>
                    <form action="${pageContext.request.contextPath}/profile"
                          method="post">
                        <input type="hidden" name="command"
                               value="profile-change-patient-info"/>

                        <div class="container-fluid pl-0 pr-0">
                                <%--2--%>
                            <div class="row mb-3">
                                <div class="col-4 d-flex align-items-center">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="register.firstNameInputLabel"/>
                                </div>
                                <div class="col d-flex align-items-center">
                                        <%--<c:out value="${requestScope.patientInfo.}"/>--%>
                                    <c:choose>
                                        <c:when test="${requestScope.patientInfo.firstName ne null}">
                                            <c:out value="${requestScope.patientInfo.firstName}"/>
                                        </c:when>
                                        <c:otherwise>
                                            <c:out value=""/>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <hr>
                                <%--                            <div class="form-group form-inline">--%>
                                <%--                                <label for="firstNameInputLabel"--%>
                                <%--                                       class="col-4 custom-form-label pl-0 pr-0">--%>
                                <%--                                    <fmt:message bundle="${jspMessages}"--%>
                                <%--                                                 key="register.firstNameInputLabel"/>--%>
                                <%--                                </label>--%>
                                <%--                                <input type="text" class="form-control col"--%>
                                <%--                                       id="firstNameInputLabel"--%>
                                <%--                                       name="firstNameInput"--%>
                                <%--                                <c:choose>--%>
                                <%--                                <c:when test="${requestScope.patientInfo.firstName ne null}">--%>
                                <%--                                       value="${requestScope.patientInfo.firstName}"--%>
                                <%--                                </c:when>--%>
                                <%--                                <c:otherwise>--%>
                                <%--                                       value=""--%>
                                <%--                                </c:otherwise>--%>
                                <%--                                </c:choose>--%>
                                <%--                                       disabled>--%>
                                <%--                            </div>--%>
                                <%--                            <hr>--%>
                                <%--3--%>
                            <div class="row mb-3">
                                <div class="col-4 d-flex align-items-center">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="register.middleNameInputLabel"/>
                                </div>
                                <div class="col d-flex align-items-center">
                                    <c:choose>
                                        <c:when test="${requestScope.patientInfo.middleName ne null}">
                                            <c:out value="${requestScope.patientInfo.middleName}"/>
                                        </c:when>
                                        <c:otherwise>
                                            <c:out value=""/>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <hr>
                                <%--4--%>
                            <div class="row mb-3">
                                <div class="col-4 d-flex align-items-center">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="register.lastNameInputLabel"/>
                                </div>
                                <div class="col d-flex align-items-center">
                                    <c:choose>
                                        <c:when test="${requestScope.patientInfo.lastName ne null}">
                                            <c:out value="${requestScope.patientInfo.lastName}"/>
                                        </c:when>
                                        <c:otherwise>
                                            <c:out value=""/>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <hr>
                                <%--5--%>
                            <div class="row mb-3">
                                <div class="col-4 d-flex align-items-center">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="register.birthdayInputLabel"/>
                                </div>
                                <div class="col d-flex align-items-center">
                                        <%--  TODO make personal tag                              --%>
                                    <c:choose>
                                        <c:when test="${requestScope.patientInfo.birthday ne null}">
                                            <c:out value="${requestScope.patientInfo.birthday}"/>
                                        </c:when>
                                        <c:otherwise>
                                            <c:out value=""/>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <hr>
                                <%--6--%>
                            <div class="row mb-3">
                                <div class="col-4 d-flex align-items-center">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="register.GenderInputLabel"/>
                                </div>
                                <div class="col d-flex align-items-center">
                                    <c:choose>
                                        <c:when test="${requestScope.patientInfo.gender ne null}">
                                            <c:out value="${requestScope.patientInfo.gender.genderValue}"/>
                                        </c:when>
                                        <c:otherwise>
                                            <c:out value=""/>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <hr>
                                <%--7--%>
                            <div class="row mb-3">
                                <div class="col-4 d-flex align-items-center">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="register.emailInputLabel"/>
                                </div>
                                <div class="col d-flex align-items-center">
                                        <%--TODO hide with ***--%>
                                    <c:choose>
                                        <c:when test="${requestScope.patientInfo.email ne null}">
                                            <c:out value="${requestScope.patientInfo.email}"/>
                                        </c:when>
                                        <c:otherwise>
                                            <c:out value=""/>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div>
                                    <a href="${pageContext.request.contextPath}/profile?command=go-to-profile-email-change">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="profileSubMenu.changePatientInfo.changeEmailLink"/>
                                    </a>
                                </div>
                            </div>
                            <hr>
                                <%--8--%>
                            <div class="row mb-3">
                                <div class="col-4 d-flex align-items-center">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="register.phoneNumberInputLabel"/>
                                </div>
                                <div class="col d-flex align-items-center">
                                    <c:choose>
                                        <c:when test="${requestScope.patientInfo.phoneNumber ne null}">
                                            <c:out value="${requestScope.patientInfo.phoneNumber}"/>
                                        </c:when>
                                        <c:otherwise>
                                            <c:out value=""/>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div id="changeBtn"
                                     class="btn btn-secondary"
                                     onclick="changePhone()">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="profileSubMenu.changePatientInfo.changePhoneNumber"/>
                                </div>
                            </div>

                            <div id="phoneInputs" style="display: none">
                                <div class="form-group form-inline row">
                                    <label for="phoneNumberCountryCodeInput"
                                           for="phoneNumberInnerCodeInput"
                                           for="phoneNumberInnerNumberInput"
                                           class="col-4 custom-form-label">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="profileSubMenu.changePatientInfo.newPhoneNumberInputLabel"/>
                                    </label>
                                    <div class="row col-5 flex-end">
                                        <input type="hidden" id="isNewPhone"
                                               name="isNewPhone" value="false">
                                        <input type="text"
                                               class="form-control col col-2"
                                               id="phoneNumberCountryCodeInput"
                                               name="phoneNumberCountryCode"
                                               placeholder="+XXX">
                                        <input type="text"
                                               class="form-control col col-2"
                                               id="phoneNumberInnerCodeInput"
                                               name="phoneNumberInnerCode"
                                               placeholder="(XX)">
                                        <input type="text"
                                               class="form-control col col-8"
                                               id="phoneNumberInnerNumberInput"
                                               name="phoneNumberInnerNumber"
                                               placeholder="XXX-XX-XX">
                                    </div>
                                </div>
                            </div>

                            <script>
                                function changePhone() {
                                    let phoneInputsVar = document.getElementById("phoneInputs");
                                    let changeBrnVar = document.getElementById("changeBtn");
                                    let isNewPhone = document.getElementById("isNewPhone");
                                    let countryCode = document.getElementById("phoneNumberCountryCodeInput");
                                    let innerCode = document.getElementById("phoneNumberInnerCodeInput");
                                    let innerNumber = document.getElementById("phoneNumberInnerNumberInput");
                                    if (phoneInputsVar.style.display === "none") {
                                        phoneInputsVar.style.display = "block";
                                        changeBrnVar.innerText = "<fmt:message bundle="${jspMessages}" key="profileSubMenu.changePatientInfo.cancelChangePhoneNumber"/>";
                                        countryCode.required = true;
                                        innerCode.required = true;
                                        innerNumber.required = true;
                                        isNewPhone.value = "true";
                                    } else {
                                        phoneInputsVar.style.display = "none";
                                        changeBrnVar.innerText = "<fmt:message bundle="${jspMessages}" key="profileSubMenu.changePatientInfo.changePhoneNumber"/>";
                                        countryCode.required = false;
                                        innerCode.required = false;
                                        innerNumber.required = false;
                                        isNewPhone.value = "false";
                                    }
                                }
                            </script>

                            <hr>
                                <%--9--%>
                            <div class="row mb-3">
                                <div class="col-4 d-flex align-items-center">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="profile.maritalStatus"/>
                                </div>
                                <div class="col d-flex align-items-center">
                                    <div class="form-control col form-check form-check-inline">
                                        <input type="radio"
                                               class="form-check-input"
                                               id="unmarried"
                                               name="maritalStatusInput"
                                               value="unmarried">
                                        <label for="unmarried"
                                               class="form-check-label"
                                               style="margin-right: 10px">
                                            <fmt:message bundle="${jspMessages}"
                                                         key="profileSubMenu.changePatientInfo.unmarried"/>
                                        </label>
                                        <input type="radio"
                                               class="form-check-input"
                                               id="married"
                                               name="maritalStatusInput"
                                               value="married">
                                        <label for="married"
                                               class="form-check-label"
                                               style="margin-right: 10px">
                                            <fmt:message bundle="${jspMessages}"
                                                         key="profileSubMenu.changePatientInfo.married"/>
                                        </label>
                                        <input type="radio"
                                               class="form-check-input"
                                               id="divorced"
                                               name="maritalStatusInput"
                                               value="divorced">
                                        <label for="divorced"
                                               class="form-check-label"
                                               style="margin-right: 10px">
                                            <fmt:message bundle="${jspMessages}"
                                                         key="profileSubMenu.changePatientInfo.divorced"/>
                                        </label>
                                        <input type="radio"
                                               class="form-check-input"
                                               id="widower"
                                               name="maritalStatusInput"
                                               value="widower">
                                        <label for="widower"
                                               class="form-check-label"
                                               style="margin-right: 10px">
                                            <fmt:message bundle="${jspMessages}"
                                                         key="profileSubMenu.changePatientInfo.widower"/>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <hr>
                                <%--10--%>
                            <div class="row mb-3">
                                <div class="col-4 d-flex align-items-center">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="profile.identityDocument"/>
                                </div>
                                <div class="col d-flex align-items-center">
                                    <c:choose>
                                        <c:when test="${requestScope.patientInfo.identityDocument ne null}">
                                            <%-- TODO make your own tag in order to view identity document --%>
                                            <c:out value="${requestScope.patientInfo.identityDocument.toString()}"/>
                                        </c:when>
                                        <c:otherwise>
                                            <c:out value=""/>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <hr>
                                <%--11--%>
                            <div class="row mb-3">
                                <div class="col-4 d-flex align-items-center">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="profile.homeAddress"/>
                                </div>
                                <div class="col d-flex align-items-center">
                                    <c:choose>
                                        <c:when test="${requestScope.patientInfo.homeAddress ne null}">
                                            <%-- TODO make your own tag in order to view identity document --%>
                                            <c:out value="${requestScope.patientInfo.homeAddress.toString()}"/>
                                        </c:when>
                                        <c:otherwise>
                                            <c:out value=""/>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <hr>
                                <%--12--%>
                            <div class="row mb-3">
                                <div class="col-4 d-flex align-items-center">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="profile.inCaseOfEmergencyContactPerson"/>
                                </div>
                                <div class="col d-flex align-items-center">
                                    <c:choose>
                                        <c:when test="${requestScope.patientInfo.inCaseOfEmergencyContactPersonInfo ne null}">
                                            <c:out value="${requestScope.patientInfo.inCaseOfEmergencyContactPersonInfo}"/>
                                        </c:when>
                                        <c:otherwise>
                                            <c:out value=""/>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <hr>
                                <%--13--%>
                            <div class="row mb-3">
                                <div class="col-4 d-flex align-items-center">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="profile.inCaseOfEmergencyPhoneOfContactPerson"/>
                                </div>
                                <div class="col d-flex align-items-center">
                                    <c:choose>
                                        <c:when test="${requestScope.patientInfo.inCaseOfEmergencyContactPersonPhone ne null}">
                                            <c:out value="${requestScope.patientInfo.inCaseOfEmergencyContactPersonPhone}"/>
                                        </c:when>
                                        <c:otherwise>
                                            <c:out value=""/>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <hr>
                                <%--14--%>
                            <div class="row mb-3">
                                <div class="col-4 d-flex align-items-center">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="profile.bloodType"/>
                                </div>
                                <div class="col d-flex align-items-center">
                                    <div class="form-control col form-check form-check-inline">
                                        <input type="radio"
                                               class="form-check-input"
                                               id="blood1"
                                               name="bloodTypeInput"
                                               value="I">
                                        <label for="blood1"
                                               class="form-check-label"
                                               style="margin-right: 10px">
                                            <fmt:message bundle="${jspMessages}"
                                                         key="profileSubMenu.changePatientInfo.blood1"/>
                                        </label>
                                        <input type="radio"
                                               class="form-check-input"
                                               id="blood2"
                                               name="bloodTypeInput"
                                               value="II">
                                        <label for="blood2"
                                               class="form-check-label"
                                               style="margin-right: 10px">
                                            <fmt:message bundle="${jspMessages}"
                                                         key="profileSubMenu.changePatientInfo.blood2"/>
                                        </label>
                                        <input type="radio"
                                               class="form-check-input"
                                               id="blood3"
                                               name="bloodTypeInput"
                                               value="III">
                                        <label for="blood3"
                                               class="form-check-label"
                                               style="margin-right: 10px">
                                            <fmt:message bundle="${jspMessages}"
                                                         key="profileSubMenu.changePatientInfo.blood3"/>
                                        </label>
                                        <input type="radio"
                                               class="form-check-input"
                                               id="blood4"
                                               name="bloodTypeInput"
                                               value="IV">
                                        <label for="blood4"
                                               class="form-check-label"
                                               style="margin-right: 10px">
                                            <fmt:message bundle="${jspMessages}"
                                                         key="profileSubMenu.changePatientInfo.blood4"/>
                                        </label>
                                        <input type="radio"
                                               class="form-check-input"
                                               id="bloodOther"
                                               name="bloodTypeInput"
                                               value="unknown">
                                        <label for="bloodOther"
                                               class="form-check-label"
                                               style="margin-right: 10px">
                                            <fmt:message bundle="${jspMessages}"
                                                         key="profileSubMenu.changePatientInfo.bloodOther"/>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <hr>
                                <%--15--%>
                            <div class="row mb-3">
                                <div class="col-4 d-flex align-items-center">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="profile.bloodRh"/>
                                </div>
                                <div class="col d-flex align-items-center">
                                    <div class="form-control col form-check form-check-inline">
                                        <input type="radio"
                                               class="form-check-input"
                                               id="rhBloodGroupPlus"
                                               name="rhBloodGroupInput"
                                               value="+">
                                        <label for="rhBloodGroupPlus"
                                               class="form-check-label"
                                               style="margin-right: 10px">
                                            <fmt:message bundle="${jspMessages}"
                                                         key="profileSubMenu.changePatientInfo.rhBloodGroupPositive"/>
                                        </label>
                                        <input type="radio"
                                               class="form-check-input"
                                               id="rhBloodGroupMinus"
                                               name="rhBloodGroupInput"
                                               value="-">
                                        <label for="rhBloodGroupMinus"
                                               class="form-check-label"
                                               style="margin-right: 10px">
                                            <fmt:message bundle="${jspMessages}"
                                                         key="profileSubMenu.changePatientInfo.rhBloodGroupNegative"/>
                                        </label>
                                        <input type="radio"
                                               class="form-check-input"
                                               id="rhBloodGroupUnknown"
                                               name="rhBloodGroupInput"
                                               value="unknown">
                                        <label for="rhBloodGroupUnknown"
                                               class="form-check-label"
                                               style="margin-right: 10px">
                                            <fmt:message bundle="${jspMessages}"
                                                         key="profileSubMenu.changePatientInfo.bloodOther"/>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <hr>
                                <%--16--%>
                            <div class="row mb-3">
                                <div class="col-4 d-flex align-items-center">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="profile.disabilityDegree"/>
                                </div>
                                <div class="col d-flex align-items-center">
                                    <div class="form-control col form-check form-check-inline">
                                        <input type="radio"
                                               class="form-check-input"
                                               id="disabilityDegree1"
                                               name="disabilityDegreeInput"
                                               value="1">
                                        <label for="disabilityDegree1"
                                               class="form-check-label"
                                               style="margin-right: 10px">
                                            <fmt:message bundle="${jspMessages}"
                                                         key="profileSubMenu.changePatientInfo.disabilityDegree1"/>
                                        </label>
                                        <input type="radio"
                                               class="form-check-input"
                                               id="disabilityDegree2"
                                               name="disabilityDegreeInput"
                                               value="2">
                                        <label for="disabilityDegree2"
                                               class="form-check-label"
                                               style="margin-right: 10px">
                                            <fmt:message bundle="${jspMessages}"
                                                         key="profileSubMenu.changePatientInfo.disabilityDegree2"/>
                                        </label>
                                        <input type="radio"
                                               class="form-check-input"
                                               id="disabilityDegree3"
                                               name="disabilityDegreeInput"
                                               value="3">
                                        <label for="disabilityDegree3"
                                               class="form-check-label"
                                               style="margin-right: 10px">
                                            <fmt:message bundle="${jspMessages}"
                                                         key="profileSubMenu.changePatientInfo.disabilityDegree3"/>
                                        </label>
                                        <input type="radio"
                                               class="form-check-input"
                                               id="disabilityDegree0"
                                               name="disabilityDegreeInput"
                                               value="0">
                                        <label for="disabilityDegree0"
                                               class="form-check-label"
                                               style="margin-right: 10px">
                                            <fmt:message bundle="${jspMessages}"
                                                         key="profileSubMenu.changePatientInfo.disabilityDegree0"/>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <hr>
                                <%--17--%>
                            <div class="row mb-3">
                                <div class="col-4 d-flex align-items-center">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="profile.transportationStatus"/>
                                </div>
                                <div class="col d-flex align-items-center">
                                    <c:choose>
                                        <c:when test="${requestScope.patientInfo.transportationStatus ne null}">
                                            <c:out value="${requestScope.patientInfo.transportationStatus.description}"/>
                                        </c:when>
                                        <c:otherwise>
                                            <c:out value=""/>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <hr>
                                <%--18--%>
                            <div class="row mb-3">
                                <div class="col-4 d-flex align-items-center">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="profile.allergicReactionsPresence"/>
                                </div>
                                <div class="col d-flex align-items-center">
                                    <c:choose>
                                        <c:when test="${requestScope.patientInfo.hasAllergicReactions == false}">
                                            <fmt:message bundle="${jspMessages}"
                                                         key="profile.absence"/>
                                        </c:when>
                                        <c:otherwise>
                                            <fmt:message bundle="${jspMessages}"
                                                         key="profile.presence"/>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div>
                                    <a href="${pageContext.request.contextPath}/profile?command=go-to-profile-allergic-reactions">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="profileSubMenu.changePatientInfo.universalChangeLink"/>
                                    </a>
                                </div>
                            </div>
                            <hr>
                                <%--19--%>
                            <div class="row mb-3">
                                <div class="col-4 d-flex align-items-center">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="profile.extremelyHazardousDiseasesPresence"/>
                                </div>
                                <div class="col d-flex align-items-center">
                                    <c:choose>
                                        <c:when test="${requestScope.patientInfo.hasExtremelyHazardousDiseases == false}">
                                            <fmt:message bundle="${jspMessages}"
                                                         key="profile.absence"/>
                                        </c:when>
                                        <c:otherwise>
                                            <fmt:message bundle="${jspMessages}"
                                                         key="profile.presence"/>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div>
                                    <a href="${pageContext.request.contextPath}/profile?command=go-to-profile-extremely-hazardous-diseases">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="profileSubMenu.changePatientInfo.universalChangeLink"/>
                                    </a>
                                </div>
                            </div>

                        </div>

                            <%--                <div class="form-group form-inline">--%>
                            <%--                    <label for="firstNameInputLabel"--%>
                            <%--                           class="col-4 custom-form-label">--%>
                            <%--                        <fmt:message bundle="${jspMessages}"--%>
                            <%--                                     key="register.firstNameInputLabel"/>--%>
                            <%--                    </label>--%>
                            <%--                    <input type="text" class="form-control col"--%>
                            <%--                           id="firstNameInputLabel"--%>
                            <%--                           name="firstNameInput"--%>
                            <%--                    <c:choose>--%>
                            <%--                    <c:when test="${requestScope.patientInfo.firstName ne null}">--%>
                            <%--                           value="${requestScope.patientInfo.firstName}"--%>
                            <%--                    </c:when>--%>
                            <%--                    <c:otherwise>--%>
                            <%--                           value=""--%>
                            <%--                    </c:otherwise>--%>
                            <%--                    </c:choose>--%>
                            <%--                           disabled>--%>
                            <%--                </div>--%>
                            <%--                <div class="form-group form-inline">--%>
                            <%--                    <label for="" class="col-4 custom-form-label">--%>

                            <%--                    </label>--%>
                            <%--                    <input type="text" class="form-control col"--%>
                            <%--                           id=""--%>
                            <%--                           name=""--%>
                            <%--                    <c:choose>--%>
                            <%--                    <c:when test="${requestScope.patientInfo. ne null}">--%>
                            <%--                           value="${requestScope.patientInfo.}"--%>
                            <%--                    </c:when>--%>
                            <%--                    <c:otherwise>--%>
                            <%--                           value=""--%>
                            <%--                    </c:otherwise>--%>
                            <%--                    </c:choose>--%>
                            <%--                           disabled>--%>
                            <%--                </div>--%>
                    </form>

                        <%--            <fmt:message bundle="${jspMessages}"--%>
                        <%--                         key="profile.photo"/>--%>
                        <%--                    <fmt:message bundle="${jspMessages}"--%>
                        <%--                                 key="register.firstNameInputLabel"/>--%>
                        <%--                    <fmt:message bundle="${jspMessages}"--%>
                        <%--                                 key="register.middleNameInputLabel"/>--%>
                        <%--                    <fmt:message bundle="${jspMessages}"--%>
                        <%--                                 key="register.lastNameInputLabel"/>--%>
                        <%--                    <fmt:message bundle="${jspMessages}"--%>
                        <%--                                 key="register.birthdayInputLabel"/>--%>
                        <%--                    <fmt:message bundle="${jspMessages}"--%>
                        <%--                                 key="register.GenderInputLabel"/>--%>
                        <%--                    <fmt:message bundle="${jspMessages}"--%>
                        <%--                                 key="register.emailInputLabel"/>--%>
                        <%--                    <fmt:message bundle="${jspMessages}"--%>
                        <%--                                 key="register.phoneNumberInputLabel"/>--%>
                        <%--                    <fmt:message bundle="${jspMessages}"--%>
                        <%--                                 key="profile.maritalStatus"/>--%>
                        <%--                    <fmt:message bundle="${jspMessages}"--%>
                        <%--                                 key="profile.identityDocument"/>--%>
                        <%--                    <fmt:message bundle="${jspMessages}"--%>
                        <%--                                 key="profile.homeAddress"/>--%>
                        <%--                    <fmt:message bundle="${jspMessages}"--%>
                        <%--                                 key="profile.inCaseOfEmergencyContactPerson"/>--%>
                        <%--                    <fmt:message bundle="${jspMessages}"--%>
                        <%--                                 key="profile.inCaseOfEmergencyPhoneOfContactPerson"/>--%>
                        <%--                    <fmt:message bundle="${jspMessages}"--%>
                        <%--                                 key="profile.bloodType"/>--%>
                        <%--                    <fmt:message bundle="${jspMessages}"--%>
                        <%--                                 key="profile.bloodRh"/>--%>
                        <%--                    <fmt:message bundle="${jspMessages}"--%>
                        <%--                                 key="profile.disabilityDegree"/>--%>
                        <%--                    <fmt:message bundle="${jspMessages}"--%>
                        <%--                                 key="profile.transportationStatus"/>--%>
                        <%--                    <fmt:message bundle="${jspMessages}"--%>
                        <%--                                 key="profile.allergicReactionsPresence"/>--%>
                        <%--                    <fmt:message bundle="${jspMessages}"--%>
                        <%--                                 key="profile.extremelyHazardousDiseasesPresence"/>--%>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<jsp:include page="../structural_element/footer.jsp"/>

</body>
</html>
