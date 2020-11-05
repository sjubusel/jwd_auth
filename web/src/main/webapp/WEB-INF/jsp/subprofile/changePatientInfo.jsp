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
                            </div>
                            <hr>
                                <%--9--%>
                            <div class="row mb-3">
                                <div class="col-4 d-flex align-items-center">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="profile.maritalStatus"/>
                                </div>
                                <div class="col d-flex align-items-center">
                                    <c:choose>
                                        <c:when test="${requestScope.patientInfo.maritalStatus ne null}">
                                            <c:out value="${requestScope.patientInfo.maritalStatus.description}"/>
                                        </c:when>
                                        <c:otherwise>
                                            <c:out value=""/>
                                        </c:otherwise>
                                    </c:choose>
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
                                    <c:choose>
                                        <c:when test="${requestScope.patientInfo.bloodType ne null}">
                                            <c:out value="${requestScope.patientInfo.bloodType.value}"/>
                                        </c:when>
                                        <c:otherwise>
                                            <c:out value=""/>
                                        </c:otherwise>
                                    </c:choose>
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
                                    <c:choose>
                                        <c:when test="${requestScope.patientInfo.rhBloodGroup ne null}">
                                            <c:out value="${requestScope.patientInfo.rhBloodGroup.description}"/>
                                        </c:when>
                                        <c:otherwise>
                                            <c:out value=""/>
                                        </c:otherwise>
                                    </c:choose>
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
                                    <c:choose>
                                        <c:when test="${requestScope.patientInfo.disabilityDegree ne null}">
                                            <c:out value="${requestScope.patientInfo.disabilityDegree.description}"/>
                                        </c:when>
                                        <c:otherwise>
                                            <c:out value=""/>
                                        </c:otherwise>
                                    </c:choose>
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
