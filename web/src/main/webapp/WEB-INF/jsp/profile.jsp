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
<%--<c:set var="activeSubMenuProfileTab" value="viewProfileInfo" scope="page"/>--%>

<fmt:setBundle basename="jspResources" var="jspMessages"/>

<html>
<head>
    <title>
        <fmt:message bundle="${jspMessages}"
                     key="profileSubMenu.patientInfo"/>
        <c:out value=" | "/>
        <fmt:message bundle="${jspMessages}" key="profile.htmlTitle"/>
        <c:out value=" | "/>
        <fmt:message bundle="${jspMessages}" key="all.htmlTitle"/>
    </title>
    <jsp:include page="structural_element/metahead.jsp"/>
</head>
<body>

<div class="main-content">
    <%@ include file="structural_element/header.jsp" %>

    <div class="row mt-2 mr-2 ml-2">
        <%@ include file="structural_element/profileSubMenu.jsp" %>
        <div class="bg-light d-inline-block col">

            <table class="table">
                <tbody>
                <%--1--%>
                <tr class="row">
                    <td class="col-3 d-flex align-items-center">
                        <fmt:message bundle="${jspMessages}"
                                     key="profile.photo"/>
                    </td>
                    <td class="col d-flex align-items-center">
                        <img src="${pageContext.request.contextPath}/img/user.png"
                             class="rounded " style="height: 200px; width:auto"
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
                        Value
                    </td>
                </tr>
                <%--3--%>
                <tr class="row">
                    <td class="col-3 d-flex align-items-center">
                        <fmt:message bundle="${jspMessages}"
                                     key="register.middleNameInputLabel"/>
                    </td>
                    <td class="col d-flex align-items-center">
                        Value
                    </td>
                </tr>
                <%--4--%>
                <tr class="row">
                    <td class="col-3 d-flex align-items-center">
                        <fmt:message bundle="${jspMessages}"
                                     key="register.lastNameInputLabel"/>
                    </td>
                    <td class="col d-flex align-items-center">
                        Value
                    </td>
                </tr>
                <%--5--%>
                <tr class="row">
                    <td class="col-3 d-flex align-items-center">
                        <fmt:message bundle="${jspMessages}"
                                     key="register.birthdayInputLabel"/>
                    </td>
                    <td class="col d-flex align-items-center">
                        Value
                    </td>
                </tr>
                <%--6--%>
                <tr class="row">
                    <td class="col-3 d-flex align-items-center">
                        <fmt:message bundle="${jspMessages}"
                                     key="register.GenderInputLabel"/>
                    </td>
                    <td class="col d-flex align-items-center">
                        Value
                    </td>
                </tr>
                <%--7--%>
                <tr class="row">
                    <td class="col-3 d-flex align-items-center">
                        <fmt:message bundle="${jspMessages}"
                                     key="register.emailInputLabel"/>
                    </td>
                    <td class="col d-flex align-items-center">
                        Value
                    </td>
                </tr>
                <%--8--%>
                <tr class="row">
                    <td class="col-3 d-flex align-items-center">
                        <fmt:message bundle="${jspMessages}"
                                     key="register.phoneNumberInputLabel"/>
                    </td>
                    <td class="col d-flex align-items-center">
                        Value
                    </td>
                </tr>
                <%--9--%>
                <tr class="row">
                    <td class="col-3 d-flex align-items-center">
                        <fmt:message bundle="${jspMessages}"
                                     key="profile.maritalStatus"/>
                    </td>
                    <td class="col d-flex align-items-center">
                        Value
                    </td>
                </tr>
                <%--10--%>
                <tr class="row">
                    <td class="col-3 d-flex align-items-center">
                        <fmt:message bundle="${jspMessages}"
                                     key="profile.identityDocument"/>
                    </td>
                    <td class="col d-flex align-items-center">
                        Value
                    </td>
                </tr>
                <%--11--%>
                <tr class="row">
                    <td class="col-3 d-flex align-items-center">
                        <fmt:message bundle="${jspMessages}"
                                     key="profile.homeAddress"/>
                    </td>
                    <td class="col d-flex align-items-center">
                        Value
                    </td>
                </tr>
                <%--12--%>
                <tr class="row">
                    <td class="col-3 d-flex align-items-center">
                        <fmt:message bundle="${jspMessages}"
                                     key="profile.inCaseOfEmergencyContactPerson"/>
                    </td>
                    <td class="col d-flex align-items-center">
                        Value
                    </td>
                </tr>
                <%--13--%>
                <tr class="row">
                    <td class="col-3 d-flex align-items-center">
                        <fmt:message bundle="${jspMessages}"
                                     key="profile.inCaseOfEmergencyPhoneOfContactPerson"/>
                    </td>
                    <td class="col d-flex align-items-center">
                        Value
                    </td>
                </tr>
                <%--14--%>
                <tr class="row">
                    <td class="col-3 d-flex align-items-center">
                        <fmt:message bundle="${jspMessages}"
                                     key="profile.bloodType"/>
                    </td>
                    <td class="col d-flex align-items-center">
                        Value
                    </td>
                </tr>
                <%--15--%>
                <tr class="row">
                    <td class="col-3 d-flex align-items-center">
                        <fmt:message bundle="${jspMessages}"
                                     key="profile.bloodRh"/>
                    </td>
                    <td class="col d-flex align-items-center">
                        Value
                    </td>
                </tr>
                <%--16--%>
                <tr class="row">
                    <td class="col-3 d-flex align-items-center">
                        <fmt:message bundle="${jspMessages}"
                                     key="profile.disabilityDegree"/>
                    </td>
                    <td class="col d-flex align-items-center">
                        Value
                    </td>
                </tr>
                <%--17--%>
                <tr class="row">
                    <td class="col-3 d-flex align-items-center">
                        <fmt:message bundle="${jspMessages}"
                                     key="profile.transportationStatus"/>
                    </td>
                    <td class="col d-flex align-items-center">
                        Value
                    </td>
                </tr>
                <%--18--%>
                <tr class="row">
                    <td class="col-3 d-flex align-items-center">
                        <fmt:message bundle="${jspMessages}"
                                     key="profile.allergicReactionsPresence"/>
                    </td>
                    <td class="col d-flex align-items-center">
                        Value
                    </td>
                </tr>
                <%--19--%>
                <tr class="row">
                    <td class="col-3 d-flex align-items-center">
                        <fmt:message bundle="${jspMessages}"
                                     key="profile.extremelyHazardousDiseasesPresence"/>
                    </td>
                    <td class="col d-flex align-items-center">
                        Value
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

<jsp:include page="structural_element/footer.jsp"/>

</body>
</html>
