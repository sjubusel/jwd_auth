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
        function changeHiddenInput(hiddenInputName, shownInputName, childRow, parent) {
            let hiddenInput = document.getElementById(hiddenInputName);
            let shownInput = document.getElementById(shownInputName);
            hiddenInput.value = childRow.firstChild.innerHTML;
            shownInput.value = childRow.lastChild.innerHTML;
            parent.innerHTML = "Выбрано " + shownInput.value;
            // alert("HiddenInput is " + hiddenInput.value);
        }

        jQuery(document).ready(function () {
            $("#phoneNumberCountryCodeInput").mask("+9?99");
            $("#phoneNumberInnerCodeInput").mask("9?99");
            $("#phoneNumberInnerNumberInput").mask("999-99-99");

            $("#emergencyPhoneNumberCountryCodeInput").mask("+9?99");
            $("#emergencyPhoneNumberInnerCodeInput").mask("9?99");
            $("#emergencyPhoneNumberInnerNumberInput").mask("999-99-99");

            let searchRequestCountry = null;
            $("#country").keyup(function () {
                if (searchRequestCountry != null) {
                    searchRequestCountry.abort();
                }
                let text = $(this).val();
                if (text === "") {
                    $("#countryResult").html("");
                } else {
                    $("#countryResult").html("");
                    searchRequestCountry = $.ajax({
                        url: "ajax?command=fetch-country-in-change-patient-info-jsp",
                        method: "post",
                        data: $("#country").serialize(),
                        dataType: "json",
                        success: function (data) {
                            if (data === null) {
                                $("#countryResult").html("<fmt:message bundle="${jspMessages}" key="profileSubMenu.changePatientInfo.ajax.validation"/>");
                                return;
                            }
                            if (data.length === 0) {
                                $("#countryResult").html("NOTHING");
                                return;
                            }
                            let parent = document.getElementById("countryResult");
                            for (let i = 0; i < data.length; i++) {
                                let childRow = document.createElement("div");
                                childRow.className += "row border list-group-item-action d-flex align-items-start";
                                parent.appendChild(childRow);
                                let childColId = document.createElement("div");
                                childColId.className += "col border d-none justify-content-center";
                                childRow.appendChild(childColId);
                                childColId.innerHTML = data[i].countryId;
                                childRow.setAttribute("onclick", "changeHiddenInput(\"hiddenCountry\", \"country\", this, this.parentElement);");

                                let childColName = document.createElement("div");
                                childColName.className += "col border d-flex justify-content-center";
                                childRow.appendChild(childColName);
                                childColName.innerHTML = data[i].countryName;
                            }
                        }
                    });
                }
            });

            let searchRequestRegion = null;
            $("#region").keyup(function () {
                if (searchRequestRegion != null) {
                    searchRequestRegion.abort();
                }
                let text = $(this).val();
                if (text === "") {
                    $("#regionResult").html("");
                } else {
                    document.getElementById("regionResult").innerHTML = "";
                    let hiddenCountry = document.getElementById("hiddenCountry");
                    if (hiddenCountry.value === "") {
                        // alert("YES");
                        $("#regionResult").html("Выберите сначала страну");
                        return;
                    }

                    searchRequestRegion = $.ajax({
                        url: "ajax?command=fetch-region-in-change-patient-info-jsp",
                        method: "post",
                        data: {
                            regionInput: text,
                            hiddenCountryId: hiddenCountry.value,
                        },
                        dataType: "json",
                        success: function (data) {
                            if (data === null) {
                                $("#regionResult").html("<fmt:message bundle="${jspMessages}" key="profileSubMenu.changePatientInfo.ajax.validation"/>");
                                return;
                            }
                            if (data.length === 0) {
                                $("#regionResult").html("NOTHING");
                                return;
                            }
                            let parent = document.getElementById("regionResult");
                            for (let i = 0; i < data.length; i++) {
                                let childRow = document.createElement("div");
                                childRow.className += "row border list-group-item-action d-flex align-items-start";
                                parent.appendChild(childRow);
                                let childColId = document.createElement("div");
                                childColId.className += "col border d-none justify-content-center";
                                childRow.appendChild(childColId);
                                childColId.innerHTML = data[i].regionId;
                                childRow.setAttribute("onclick", "changeHiddenInput(\"hiddenRegion\", \"region\", this, this.parentElement);");

                                let childColName = document.createElement("div");
                                childColName.className += "col border d-flex justify-content-center";
                                childRow.appendChild(childColName);
                                childColName.innerHTML = data[i].countryName;

                                let childColName2 = document.createElement("div");
                                childColName2.className += "col border d-flex justify-content-center";
                                childRow.appendChild(childColName2);
                                childColName2.innerHTML = data[i].regionName;
                            }
                        }
                    });
                }
            });

            let searchRequestArea = null;
            $("#area").keyup(function () {
                if (searchRequestArea != null) {
                    searchRequestArea.abort();
                }
                let text = $(this).val();
                if (text === "") {
                    $("#areaResult").html("");
                } else {
                    document.getElementById("areaResult").innerHTML = "";
                    let hiddenRegion = document.getElementById("hiddenRegion");
                    if (hiddenRegion.value === "") {
                        $("#areaResult").html("Выберите сначала регион");
                        return;
                    }

                    searchRequestArea = $.ajax({
                        url: "ajax?command=fetch-area-in-change-patient-info-jsp",
                        method: "post",
                        data: {
                            areaInput: text,
                            hiddenRegionId: hiddenRegion.value,
                        },
                        dataType: "json",
                        success: function (data) {
                            if (data === null) {
                                $("#areaResult").html("<fmt:message bundle="${jspMessages}" key="profileSubMenu.changePatientInfo.ajax.validation"/>");
                                return;
                            }
                            if (data.length === 0) {
                                $("#areaResult").html("NOTHING");
                                return;
                            }
                            let parent = document.getElementById("areaResult");
                            for (let i = 0; i < data.length; i++) {
                                let childRow = document.createElement("div");
                                childRow.className += "row border list-group-item-action d-flex align-items-start";
                                parent.appendChild(childRow);
                                let childColId = document.createElement("div");
                                childColId.className += "col border d-none justify-content-center";
                                childRow.appendChild(childColId);
                                childColId.innerHTML = data[i].areaId;
                                childRow.setAttribute("onclick", "changeHiddenInput(\"hiddenArea\", \"area\", this, this.parentElement);");

                                let childColName = document.createElement("div");
                                childColName.className += "col border d-flex justify-content-center";
                                childRow.appendChild(childColName);
                                childColName.innerHTML = data[i].regionName;

                                let childColName2 = document.createElement("div");
                                childColName2.className += "col border d-flex justify-content-center";
                                childRow.appendChild(childColName2);
                                childColName2.innerHTML = data[i].areaName;
                            }
                        }
                    });
                }
            });

            let searchRequestSettlement = null;
            $("#settlement").keyup(function () {
                if (searchRequestSettlement != null) {
                    searchRequestSettlement.abort();
                }
                let text = $(this).val();
                if (text === "") {
                    $("#settlementResult").html("");
                } else {
                    document.getElementById("settlementResult").innerHTML = "";
                    let hiddenArea = document.getElementById("hiddenArea");
                    if (hiddenArea.value === "") {
                        $("#settlementResult").html("Выберите сначала район");
                        return;
                    }

                    searchRequestSettlement = $.ajax({
                        url: "ajax?command=fetch-settlement-in-change-patient-info-jsp",
                        method: "post",
                        data: {
                            settlementInput: text,
                            hiddenAreaId: hiddenArea.value,
                        },
                        dataType: "json",
                        success: function (data) {
                            if (data === null) {
                                $("#settlementResult").html("<fmt:message bundle="${jspMessages}" key="profileSubMenu.changePatientInfo.ajax.validation"/>");
                                return;
                            }
                            if (data.length === 0) {
                                $("#settlementResult").html("NOTHING");
                                return;
                            }
                            let parent = document.getElementById("settlementResult");
                            for (let i = 0; i < data.length; i++) {
                                let childRow = document.createElement("div");
                                childRow.className += "row border list-group-item-action d-flex align-items-start";
                                parent.appendChild(childRow);
                                let childColId = document.createElement("div");
                                childColId.className += "col border d-none justify-content-center";
                                childRow.appendChild(childColId);
                                childColId.innerHTML = data[i].settlementId;
                                childRow.setAttribute("onclick", "changeHiddenInput(\"hiddenSettlement\", \"settlement\", this, this.parentElement);");

                                let childColName = document.createElement("div");
                                childColName.className += "col border d-flex justify-content-center";
                                childRow.appendChild(childColName);
                                childColName.innerHTML = data[i].areaName;

                                let childColName2 = document.createElement("div");
                                childColName2.className += "col border d-flex justify-content-center";
                                childRow.appendChild(childColName2);
                                childColName2.innerHTML = data[i].settlementName;
                            }
                        }
                    });
                }
            });

            let searchRequestRoad = null;
            $("#road").keyup(function () {
                if (searchRequestRoad != null) {
                    searchRequestRoad.abort();
                }
                let text = $(this).val();
                if (text === "") {
                    $("#roadResult").html("");
                } else {
                    document.getElementById("roadResult").innerHTML = "";
                    let hiddenSettlement = document.getElementById("hiddenSettlement");
                    if (hiddenSettlement.value === "") {
                        $("#roadResult").html("Выберите сначала населённый пункт");
                        return;
                    }

                    searchRequestRoad = $.ajax({
                        url: "ajax?command=fetch-road-in-change-patient-info-jsp",
                        method: "post",
                        data: {
                            roadInput: text,
                            hiddenSettlementId: hiddenSettlement.value,
                        },
                        dataType: "json",
                        success: function (data) {
                            if (data === null) {
                                $("#roadResult").html("<fmt:message bundle="${jspMessages}" key="profileSubMenu.changePatientInfo.ajax.validation"/>");
                                return;
                            }
                            if (data.length === 0) {
                                $("#roadResult").html("NOTHING");
                                return;
                            }
                            let parent = document.getElementById("roadResult");
                            for (let i = 0; i < data.length; i++) {
                                let childRow = document.createElement("div");
                                childRow.className += "row border list-group-item-action d-flex align-items-start";
                                parent.appendChild(childRow);
                                let childColId = document.createElement("div");
                                childColId.className += "col border d-none justify-content-center";
                                childRow.appendChild(childColId);
                                childColId.innerHTML = data[i].roadId;
                                childRow.setAttribute("onclick", "changeHiddenInput(\"hiddenRoad\", \"road\", this, this.parentElement);");

                                let childColName = document.createElement("div");
                                childColName.className += "col border d-flex justify-content-center";
                                childRow.appendChild(childColName);
                                childColName.innerHTML = data[i].settlementName;

                                let childColName2 = document.createElement("div");
                                childColName2.className += "col border d-flex justify-content-center";
                                childRow.appendChild(childColName2);
                                childColName2.innerHTML = data[i].roadName;
                            }
                        }
                    });
                }
            });

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
                                                 key="profileSubMenu.changePatientInfo.changeButton"/>
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
                                        changeBrnVar.innerText = "<fmt:message bundle="${jspMessages}" key="profileSubMenu.changePatientInfo.cancelChangeButton"/>";
                                        countryCode.required = true;
                                        innerCode.required = true;
                                        innerNumber.required = true;
                                        isNewPhone.value = "true";
                                    } else {
                                        phoneInputsVar.style.display = "none";
                                        changeBrnVar.innerText = "<fmt:message bundle="${jspMessages}" key="profileSubMenu.changePatientInfo.changeButton"/>";
                                        countryCode.required = false;
                                        innerCode.required = false;
                                        innerNumber.required = false;
                                        countryCode.value = "";
                                        innerCode.value = "";
                                        innerNumber.value = "";
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
                                <div id="changeAddressBtn"
                                     class="btn btn-secondary d-flex align-items-center"
                                     onclick="changeAddress()">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="profileSubMenu.changePatientInfo.changeButton"/>
                                </div>
                            </div>

                            <div id="addressInputs" style="display: none">
                                <h6>Новый адрес</h6>
                                <input type="hidden"
                                       id="isNewAddress"
                                       name="isNewAddress"
                                       value="false">
                                <div class="form-group form-inline row">
                                    <label for="zipCode"
                                           class="col-4 custom-form-label">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="profileSubMenu.changePatientInfo.address.zipCode"/>
                                    </label>
                                    <input type="text"
                                           class="form-control col-5"
                                           id="zipCode"
                                           name="zipCodeInput"
                                           placeholder="<fmt:message bundle="${jspMessages}"
                                       key="profileSubMenu.changePatientInfo.address.zipCodePlaceholder"/>"
                                           pattern="<fmt:message bundle="${jspMessages}" key="profileSubMenu.changePatientInfo.address.zipCodePattern"/>"
                                    />
                                </div>
                                <div class="form-group form-inline row">
                                    <label for="country"
                                           class="col-4 custom-form-label">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="profileSubMenu.changePatientInfo.address.country"/>
                                    </label>
                                    <input type="hidden" id="hiddenCountry"
                                           name="hiddenCountryInput" value=""/>
                                    <input type="text"
                                           class="form-control col-5"
                                           id="country"
                                           name="countryInput"
                                           placeholder="<fmt:message bundle="${jspMessages}"
                                       key="profileSubMenu.changePatientInfo.address.countryPlaceholder"/>"
                                           pattern="<fmt:message bundle="${jspMessages}" key="profileSubMenu.changePatientInfo.address.countryPattern"/>"
                                    />
                                </div>
                                <div id="countryResult" class="overflow-auto"
                                     style="max-height: 100px">
                                </div>
                                <div class="form-group form-inline row">
                                    <label for="region"
                                           class="col-4 custom-form-label">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="profileSubMenu.changePatientInfo.address.region"/>
                                    </label>
                                    <input type="hidden" id="hiddenRegion"
                                           name="hiddenRegionInput" value=""/>
                                    <input type="text"
                                           class="form-control col-5"
                                           id="region"
                                           name="regionInput"
                                           placeholder="<fmt:message bundle="${jspMessages}"
                                       key="profileSubMenu.changePatientInfo.address.regionPlaceholder"/>"
                                           pattern="<fmt:message bundle="${jspMessages}" key="profileSubMenu.changePatientInfo.address.regionPattern"/>"
                                    />
                                </div>
                                <div id="regionResult" class="overflow-auto"
                                     style="max-height: 100px">
                                </div>
                                <div class="form-group form-inline row">
                                    <label for="area"
                                           class="col-4 custom-form-label">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="profileSubMenu.changePatientInfo.address.area"/>
                                    </label>
                                    <input type="hidden" id="hiddenArea"
                                           name="hiddenAreaInput" value=""/>
                                    <input type="text"
                                           class="form-control col-5"
                                           id="area"
                                           name="areaInput"
                                           placeholder="<fmt:message bundle="${jspMessages}"
                                       key="profileSubMenu.changePatientInfo.address.areaPlaceholder"/>"
                                           pattern="<fmt:message bundle="${jspMessages}" key="profileSubMenu.changePatientInfo.address.areaPattern"/>"
                                    />
                                </div>
                                <div id="areaResult" class="overflow-auto"
                                     style="max-height: 100px">
                                </div>
                                <div class="form-group form-inline row">
                                    <label for="settlement"
                                           class="col-4 custom-form-label">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="profileSubMenu.changePatientInfo.address.settlement"/>
                                    </label>
                                    <input type="hidden" id="hiddenSettlement"
                                           name="hiddenSettlementInput"
                                           value=""/>
                                    <input type="text"
                                           class="form-control col-5"
                                           id="settlement"
                                           name="settlementInput"
                                           placeholder="<fmt:message bundle="${jspMessages}"
                                       key="profileSubMenu.changePatientInfo.address.settlementPlaceholder"/>"
                                           pattern="<fmt:message bundle="${jspMessages}" key="profileSubMenu.changePatientInfo.address.settlementPattern"/>"
                                    />
                                </div>
                                <div id="settlementResult" class="overflow-auto"
                                     style="max-height: 100px">
                                </div>
                                <div class="form-group form-inline row">
                                    <label for="road"
                                           class="col-4 custom-form-label">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="profileSubMenu.changePatientInfo.address.road"/>
                                    </label>
                                    <input type="hidden" id="hiddenRoad"
                                           name="hiddenRoadInput" value=""/>
                                    <input type="text"
                                           class="form-control col-5"
                                           id="road"
                                           name="roadInput"
                                           placeholder="<fmt:message bundle="${jspMessages}"
                                       key="profileSubMenu.changePatientInfo.address.roadPlaceholder"/>"
                                           pattern="<fmt:message bundle="${jspMessages}" key="profileSubMenu.changePatientInfo.address.roadPattern"/>"
                                    />
                                </div>
                                <div id="roadResult" class="overflow-auto"
                                     style="max-height: 100px">
                                </div>
                                <div class="form-group form-inline row">
                                    <label for="house"
                                           class="col-4 custom-form-label">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="profileSubMenu.changePatientInfo.address.house"/>
                                    </label>
                                    <input type="text"
                                           class="form-control col-5"
                                           id="house"
                                           name="houseInput"
                                           placeholder="<fmt:message bundle="${jspMessages}"
                                       key="profileSubMenu.changePatientInfo.address.housePlaceholder"/>"
                                           pattern="<fmt:message bundle="${jspMessages}" key="profileSubMenu.changePatientInfo.address.housePattern"/>"
                                    />
                                </div>
                                <div class="form-group form-inline row">
                                    <label for="building"
                                           class="col-4 custom-form-label">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="profileSubMenu.changePatientInfo.address.building"/>
                                    </label>
                                    <input type="text"
                                           class="form-control col-5"
                                           id="building"
                                           name="buildingInput"
                                           placeholder="<fmt:message bundle="${jspMessages}"
                                       key="profileSubMenu.changePatientInfo.address.buildingPlaceholder"/>"
                                           pattern="<fmt:message bundle="${jspMessages}" key="profileSubMenu.changePatientInfo.address.buildingPattern"/>"
                                    />
                                </div>
                                <div class="form-group form-inline row">
                                    <label for="room"
                                           class="col-4 custom-form-label">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="profileSubMenu.changePatientInfo.address.room"/>
                                    </label>
                                    <input type="text"
                                           class="form-control col-5"
                                           id="room"
                                           name="roomInput"
                                           placeholder="<fmt:message bundle="${jspMessages}"
                                       key="profileSubMenu.changePatientInfo.address.roomPlaceholder"/>"
                                           pattern="<fmt:message bundle="${jspMessages}" key="profileSubMenu.changePatientInfo.address.roomPattern"/>"
                                    />
                                </div>
                            </div>

                            <script>
                                function changeAddress() {
                                    let addressInputsVar = document.getElementById("addressInputs");
                                    let changeAddressBtnVar = document.getElementById("changeAddressBtn");
                                    let isNewAddress = document.getElementById("isNewAddress");

                                    let zipCode = document.getElementById("zipCode");
                                    let house = document.getElementById("house");
                                    let building = document.getElementById("building");
                                    let room = document.getElementById("room");

                                    let country = document.getElementById("country");
                                    let hiddenCountry = document.getElementById("hiddenCountry");
                                    let region = document.getElementById("region");
                                    let hiddenRegion = document.getElementById("hiddenRegion");
                                    let area = document.getElementById("area");
                                    let hiddenArea = document.getElementById("hiddenArea");
                                    let settlement = document.getElementById("settlement");
                                    let hiddenSettlement = document.getElementById("hiddenSettlement");
                                    let road = document.getElementById("road");
                                    let hiddenRoad = document.getElementById("hiddenRoad");
                                    if (addressInputsVar.style.display === "none") {
                                        addressInputsVar.style.display = "block";
                                        changeAddressBtnVar.innerText = "<fmt:message bundle="${jspMessages}" key="profileSubMenu.changePatientInfo.cancelChangeButton"/>";

                                        zipCode.required = true;
                                        house.required = true;

                                        country.required = true;
                                        hiddenCountry.required = true;
                                        region.required = true;
                                        hiddenRegion.required = true;
                                        area.required = true;
                                        hiddenArea.required = true;
                                        settlement.required = true;
                                        hiddenSettlement.required = true;
                                        road.required = true;
                                        hiddenRoad.required = true;

                                        isNewAddress.value = "true";
                                    } else {
                                        addressInputsVar.style.display = "none";
                                        changeAddressBtnVar.innerText = "<fmt:message bundle="${jspMessages}" key="profileSubMenu.changePatientInfo.changeButton"/>";

                                        zipCode.required = false;
                                        house.required = false;

                                        country.required = false;
                                        hiddenCountry.required = false;
                                        region.required = false;
                                        hiddenRegion.required = false;
                                        area.required = false;
                                        hiddenArea.required = false;
                                        settlement.required = false;
                                        hiddenSettlement.required = false;
                                        road.required = false;
                                        hiddenRoad.required = false;

                                        zipCode.value = "";
                                        house.value = "";
                                        building.value = "";
                                        room.value = "";

                                        country.value = "";
                                        hiddenCountry.value = "";
                                        region.value = "";
                                        hiddenRegion.value = "";
                                        area.value = "";
                                        hiddenArea.value = "";
                                        settlement.value = "";
                                        hiddenSettlement.value = "";
                                        road.value = "";
                                        hiddenRoad.value = "";

                                        isNewAddress.value = "false";

                                        document.getElementById("countryResult").innerHTML = "";
                                        document.getElementById("regionResult").innerHTML = "";
                                        document.getElementById("areaResult").innerHTML = "";
                                        document.getElementById("settlementResult").innerHTML = "";
                                        document.getElementById("roadResult").innerHTML = "";
                                    }
                                }
                            </script>
                            <hr>
                                <%--12--%>
                            <div class="form-group form-inline">
                                <label for="inCaseOfEmergencyContactPersonInfoLabel"
                                       class="col-4 custom-form-label pl-0 pr-0">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="profile.inCaseOfEmergencyContactPerson"/>
                                </label>
                                <input type="text"
                                       class="form-control col"
                                       id="inCaseOfEmergencyContactPersonInfoLabel"
                                       name="inCaseOfEmergencyContactPersonInfoInput"
                                        <c:choose>
                                            <c:when test="${requestScope.patientInfo.inCaseOfEmergencyContactPersonInfo ne null}">
                                                value="${requestScope.patientInfo.inCaseOfEmergencyContactPersonInfo}"
                                            </c:when>
                                            <c:otherwise>
                                                value=""
                                            </c:otherwise>
                                        </c:choose>
                                />
                            </div>
                            <hr>
                            <script>
                                const inCaseOfEmergencyContactPersonInit
                                    = (" " + document.getElementById("inCaseOfEmergencyContactPersonInfoLabel").value).slice(1);

                                // TODO use it on submit
                                function verifyOnSubmitIfChanged() {
                                    let currentValueOfContactPerson = document
                                        .getElementById("inCaseOfEmergencyContactPersonInfoLabel").value;
                                    if (inCaseOfEmergencyContactPersonInit.localeCompare(currentValueOfContactPerson) === 0) {
                                        // TODO maybe replace with currentValueOfContactPerson.value = null;
                                        currentValueOfContactPerson.value = "";
                                    }
                                }
                            </script>
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
                                <div id="emergencyPhoneChangeBtn"
                                     class="btn btn-secondary d-flex align-items-center"
                                     onclick="changeEmergencyPhone()">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="profileSubMenu.changePatientInfo.changeButton"/>
                                </div>
                            </div>

                            <div id="emergencyPhoneInputs"
                                 style="display: none">
                                <div class="form-group form-inline row">
                                    <label for="emergencyPhoneNumberCountryCodeInput"
                                           for="emergencyPhoneNumberInnerCodeInput"
                                           for="emergencyPhoneNumberInnerNumberInput"
                                           class="col-4 custom-form-label">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="profileSubMenu.changePatientInfo.newPhoneNumberInputLabel"/>
                                    </label>
                                    <div class="row col-5 flex-end">
                                        <input type="hidden"
                                               id="isNewEmergencyPhone"
                                               name="isNewEmergencyPhone"
                                               value="false">
                                        <input type="text"
                                               class="form-control col col-2"
                                               id="emergencyPhoneNumberCountryCodeInput"
                                               name="emergencyPhoneNumberCountryCode"
                                               placeholder="+XXX">
                                        <input type="text"
                                               class="form-control col col-2"
                                               id="emergencyPhoneNumberInnerCodeInput"
                                               name="emergencyPhoneNumberInnerCode"
                                               placeholder="(XX)">
                                        <input type="text"
                                               class="form-control col col-8"
                                               id="emergencyPhoneNumberInnerNumberInput"
                                               name="emergencyPhoneNumberInnerNumber"
                                               placeholder="XXX-XX-XX">
                                    </div>
                                </div>
                            </div>

                            <script>
                                function changeEmergencyPhone() {
                                    let phoneInputsVar = document.getElementById("emergencyPhoneInputs");
                                    let changeBrnVar = document.getElementById("emergencyPhoneChangeBtn");
                                    let isNewPhone = document.getElementById("isNewEmergencyPhone");
                                    let countryCode = document.getElementById("emergencyPhoneNumberCountryCodeInput");
                                    let innerCode = document.getElementById("emergencyPhoneNumberInnerCodeInput");
                                    let innerNumber = document.getElementById("emergencyPhoneNumberInnerNumberInput");
                                    if (phoneInputsVar.style.display === "none") {
                                        phoneInputsVar.style.display = "block";
                                        changeBrnVar.innerText = "<fmt:message bundle="${jspMessages}" key="profileSubMenu.changePatientInfo.cancelChangeButton"/>";
                                        countryCode.required = true;
                                        innerCode.required = true;
                                        innerNumber.required = true;
                                        isNewPhone.value = "true";
                                    } else {
                                        phoneInputsVar.style.display = "none";
                                        changeBrnVar.innerText = "<fmt:message bundle="${jspMessages}" key="profileSubMenu.changePatientInfo.changeButton"/>";
                                        countryCode.required = false;
                                        innerCode.required = false;
                                        innerNumber.required = false;
                                        countryCode.value = "";
                                        innerCode.value = "";
                                        innerNumber.value = "";
                                        isNewPhone.value = "false";
                                    }
                                }
                            </script>

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
                                    <div class="form-control col form-check form-check-inline">
                                        <input type="radio"
                                               class="form-check-input"
                                               id="transportationStatusWalking"
                                               name="transportationStatusInput"
                                               value="WALKING">
                                        <label for="transportationStatusWalking"
                                               class="form-check-label"
                                               style="margin-right: 10px">
                                            <fmt:message bundle="${jspMessages}"
                                                         key="profileSubMenu.changePatientInfo.transportationStatusWalking"/>
                                        </label>
                                        <input type="radio"
                                               class="form-check-input"
                                               id="transportationStatusWheelchair"
                                               name="transportationStatusInput"
                                               value="WHEELCHAIR">
                                        <label for="transportationStatusWheelchair"
                                               class="form-check-label"
                                               style="margin-right: 10px">
                                            <fmt:message bundle="${jspMessages}"
                                                         key="profileSubMenu.changePatientInfo.transportationStatusWheelchair"/>
                                        </label>
                                        <input type="radio"
                                               class="form-check-input"
                                               id="transportationStatusStretcher"
                                               name="transportationStatusInput"
                                               value="STRETCHER">
                                        <label for="transportationStatusStretcher"
                                               class="form-check-label"
                                               style="margin-right: 10px">
                                            <fmt:message bundle="${jspMessages}"
                                                         key="profileSubMenu.changePatientInfo.transportationStatusStretcher"/>
                                        </label>
                                    </div>

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
