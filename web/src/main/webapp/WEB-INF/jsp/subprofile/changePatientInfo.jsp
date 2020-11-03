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
<c:set var="activeSubMenuProfileTab" value="changePatientInfo"
       scope="page"/>

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

    <div class="row mt-2 mr-2 ml-2">
        <%@ include file="../structural_element/profileSubMenu.jsp" %>
        <div class="bg-light d-inline-block col">
            <h1 class="text-left">
                Замена аватора пользователя
            </h1>
            <%--Photo-change            --%>
            <form action="${pageContext.request.contextPath}/profile"
                  method="post"
                  enctype="multipart/form-data">
                <input type="hidden" name="command"
                       value="profile-change-patient-photo"/>
                <div class="form-group d-flex justify-content-center">
                    <label for="photo"
                           class="align-self-center col-4 custom-form-label">
                        <fmt:message bundle="${jspMessages}"
                                     key="profile.photo"/>
                    </label>

                    <img
                    <c:choose>
                    <c:when test="${requestScope.patientInfo.photoPath ne null}">
                            src="${pageContext.request.contextPath}${requestScope.patientInfo.photoPath}"
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
                           id="photo" name="photoUploadInput" dir="ltr">

                    <button type="submit"
                            class="btn align-self-center btn-primary">
                        change
                        <%--                        <fmt:message bundle="${jspMessages}"--%>
                        <%--                                     key="profileSubMenu.changePatientInfo.changePhoto"/>--%>
                    </button>
                </div>
            </form>

            <br>

            <h1 class="text-left">
                Редактирование доступных данных пользователя
            </h1>
            <form action="${pageContext.request.contextPath}/profile"
                  method="post">
                <input type="hidden" name="command"
                       value="profile-change-patient-info"/>

                <table class="table">
                    <tbody>
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

                    <div class="form-group form-inline">
                        <label for="firstNameInputLabel"
                               class="col-4 custom-form-label pl-0 pr-0">
                            <fmt:message bundle="${jspMessages}"
                                         key="register.firstNameInputLabel"/>
                        </label>
                        <input type="text" class="form-control col"
                               id="firstNameInputLabel"
                               name="firstNameInput"
                        <c:choose>
                        <c:when test="${requestScope.patientInfo.firstName ne null}">
                               value="${requestScope.patientInfo.firstName}"
                        </c:when>
                        <c:otherwise>
                               value=""
                        </c:otherwise>
                        </c:choose>
                               disabled>
                    </div>

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
                            <%--TODO hide with ***--%>
                            <c:choose>
                                <c:when test="${requestScope.patientInfo.email ne null}">
                                    <c:out value="${requestScope.patientInfo.email}"/>
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
                                    <%-- TODO make your own tag in order to view identity document --%>
                                    <c:out value="${requestScope.patientInfo.identityDocument.toString()}"/>
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
                                    <%-- TODO make your own tag in order to view identity document --%>
                                    <c:out value="${requestScope.patientInfo.homeAddress.toString()}"/>
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
                                    <fmt:message bundle="${jspMessages}"
                                                 key="profile.absence"/>
                                </c:when>
                                <c:otherwise>
                                    <fmt:message bundle="${jspMessages}"
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
                                    <fmt:message bundle="${jspMessages}"
                                                 key="profile.absence"/>
                                </c:when>
                                <c:otherwise>
                                    <fmt:message bundle="${jspMessages}"
                                                 key="profile.presence"/>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    </tbody>
                </table>

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
            <fmt:message bundle="${jspMessages}"
                         key="register.firstNameInputLabel"/>
            <fmt:message bundle="${jspMessages}"
                         key="register.middleNameInputLabel"/>
            <fmt:message bundle="${jspMessages}"
                         key="register.lastNameInputLabel"/>
            <fmt:message bundle="${jspMessages}"
                         key="register.birthdayInputLabel"/>
            <fmt:message bundle="${jspMessages}"
                         key="register.GenderInputLabel"/>
            <fmt:message bundle="${jspMessages}"
                         key="register.emailInputLabel"/>
            <fmt:message bundle="${jspMessages}"
                         key="register.phoneNumberInputLabel"/>
            <fmt:message bundle="${jspMessages}"
                         key="profile.maritalStatus"/>
            <fmt:message bundle="${jspMessages}"
                         key="profile.identityDocument"/>
            <fmt:message bundle="${jspMessages}"
                         key="profile.homeAddress"/>
            <fmt:message bundle="${jspMessages}"
                         key="profile.inCaseOfEmergencyContactPerson"/>
            <fmt:message bundle="${jspMessages}"
                         key="profile.inCaseOfEmergencyPhoneOfContactPerson"/>
            <fmt:message bundle="${jspMessages}"
                         key="profile.bloodType"/>
            <fmt:message bundle="${jspMessages}"
                         key="profile.bloodRh"/>
            <fmt:message bundle="${jspMessages}"
                         key="profile.disabilityDegree"/>
            <fmt:message bundle="${jspMessages}"
                         key="profile.transportationStatus"/>
            <fmt:message bundle="${jspMessages}"
                         key="profile.allergicReactionsPresence"/>
            <fmt:message bundle="${jspMessages}"
                         key="profile.extremelyHazardousDiseasesPresence"/>
        </div>
    </div>
</div>

<jsp:include page="../structural_element/footer.jsp"/>

</body>
</html>
