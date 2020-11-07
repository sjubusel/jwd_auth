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

<fmt:setBundle basename="jspResources" var="jspMessages"/>
<fmt:setBundle basename="registrationRegExp" var="regEx"/>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/bootstrap.css">
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/jquery-3.5.1.js">
    </script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/jquery.maskedinput.js">
    </script>
    <script>
        // jQuery(document).ready(function () {
        //     $("#phoneNumberCountryCodeInput").mask("+9?99");
        //     $("#phoneNumberInnerCodeInput").mask("9?99");
        //     $("#phoneNumberInnerNumberInput").mask("999-99-99");
        // });
    </script>
</head>
<body>

<div class="form-group form-inline row">
    <label for="zipCode"
           class="col-4 custom-form-label">
        <fmt:message bundle="${jspMessages}"
                     key="profileSubMenu.changePatientInfo.address.zipCode"/>
    </label>
    <input type="text" class="form-control col-5"
           id="zipCode"
           name="zipCodeInput" required
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
    <input type="hidden" id="hiddenCountry" name="hiddenCountryInput" value=""/>
    <input type="text" class="form-control col-5"
           id="country"
           name="countryInput" required
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
    <input type="hidden" id="hiddenRegion" name="hiddenRegionInput" value=""/>
    <input type="text" class="form-control col-5"
           id="region"
           name="regionInput" required
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
    <input type="hidden" id="hiddenArea" name="hiddenAreaInput" value=""/>
    <input type="text" class="form-control col-5"
           id="area"
           name="areaInput" required
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
    <input type="hidden" id="hiddenSettlement" name="hiddenSettlementInput"
           value=""/>
    <input type="text" class="form-control col-5"
           id="settlement"
           name="settlementInput" required
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
    <input type="hidden" id="hiddenRoad" name="hiddenRoadInput" value=""/>
    <input type="text" class="form-control col-5"
           id="road"
           name="roadInput" required
           placeholder="<fmt:message bundle="${jspMessages}"
                                       key="profileSubMenu.changePatientInfo.address.roadPlaceholder"/>"
           pattern="<fmt:message bundle="${jspMessages}" key="profileSubMenu.changePatientInfo.address.roadPattern"/>"
    />
</div>

<div class="form-group form-inline row">
    <label for="house"
           class="col-4 custom-form-label">
        <fmt:message bundle="${jspMessages}"
                     key="profileSubMenu.changePatientInfo.address.house"/>
    </label>
    <input type="text" class="form-control col-5"
           id="house"
           name="houseInput" required
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
    <input type="text" class="form-control col-5"
           id="building"
           name="buildingInput" required
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
    <input type="text" class="form-control col-5"
           id="room"
           name="roomInput" required
           placeholder="<fmt:message bundle="${jspMessages}"
                                       key="profileSubMenu.changePatientInfo.address.roomPlaceholder"/>"
           pattern="<fmt:message bundle="${jspMessages}" key="profileSubMenu.changePatientInfo.address.roomPattern"/>"
    />
</div>

<script>
    // $(document).ready(function () {
    //     let searchRequestCountryTemp = null;
    //     $("#countryTemp").keyup(function () {
    //         if (searchRequestCountryTemp != null) {
    //             searchRequestCountryTemp.abort();
    //         }
    //         let text = $(this).val();
    //         if (text === "") {
    //             $("#result").html("NOTHING");
    //         } else {
    //             $("#result").html("");
    //             searchRequestCountryTemp = $.ajax({
    //                 url: "ajax?command=tempFetch",
    //                 method: "post",
    //                 data: $("#countryTemp").serialize(),
    //                 dataType: "json",
    //                 success: function (data) {
    //                     let message = "";
    //                     for (let i = 0; i < data.length; i++) {
    //                         message = message.concat(data[i]);
    //                         message = message.concat("<br>")
    //                     }
    //                     $("#result").html(message);
    //                     // $("#result").html(data.firstCountry);
    //                 }
    //             });
    //         }
    //     });
    // });

    function changeHiddenInput(hiddenInputName, shownInputName, childRow, parent) {
        let hiddenInput = document.getElementById(hiddenInputName);
        let shownInput = document.getElementById(shownInputName);
        hiddenInput.value = childRow.firstChild.innerHTML;
        shownInput.value = childRow.lastChild.innerHTML;
        parent.innerHTML = "Выбрано " + shownInput.value;
        // alert("HiddenInput is " + hiddenInput.value);
    }

    $(document).ready(function () {
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
                            $("#countryResult").html("VALID");
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
                $("#regionResult").html("");
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
                            $("#regionResult").html("VALID");
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
                $("#areaResult").html("");
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
                            $("#areaResult").html("VALID");
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
                $("#settlementResult").html("");
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
                            $("#settlementResult").html("VALID");
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

    });
</script>

</body>
</html>

<%--<div class="row border border-success list-group-item-action d-flex align-items-start">--%>
<%--    <div class="col border border-secondary d-flex justify-content-center">--%>
<%--        НОМЕР--%>
<%--    </div>--%>
<%--    <div class="col border border-secondary d-flex justify-content-center">--%>
<%--        СОДЕРЖАНИЕ--%>
<%--    </div>--%>
<%--</div>--%>

<%--<div>--%>
<%--    <label for="countryTemp">SEARCH</label><input type="text" id="countryTemp"--%>
<%--                                                  name="countryTemp"--%>
<%--                                                  placeholder="Insert country"/>--%>
<%--</div>--%>

<%--<div id="result">#####################################################</div>--%>

<%--<div id="countryResult" class="overflow-auto"--%>
<%--     style="max-height: 100px">--%>
<%--    <div class="row border list-group-item-action d-flex align-items-start">--%>
<%--        <div class="col border d-flex justify-content-center">--%>
<%--            НОМЕР--%>
<%--        </div>--%>
<%--        <div class="col border d-flex justify-content-center">--%>
<%--            СОДЕРЖАНИЕ--%>
<%--        </div>--%>
<%--    </div>--%>

<%--    <div class="row border list-group-item-action d-flex align-items-start">--%>
<%--        <div class="col border d-flex justify-content-center">--%>
<%--            НОМЕР--%>
<%--        </div>--%>
<%--        <div class="col border d-flex justify-content-center">--%>
<%--            СОДЕРЖАНИЕ--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <div class="row border list-group-item-action d-flex align-items-start">--%>
<%--        <div class="col border d-flex justify-content-center">--%>
<%--            НОМЕР--%>
<%--        </div>--%>
<%--        <div class="col border d-flex justify-content-center">--%>
<%--            СОДЕРЖАНИЕ--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <div class="row border list-group-item-action d-flex align-items-start">--%>
<%--        <div class="col border d-flex justify-content-center">--%>
<%--            НОМЕР--%>
<%--        </div>--%>
<%--        <div class="col border d-flex justify-content-center">--%>
<%--            СОДЕРЖАНИЕ--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <div class="row border list-group-item-action d-flex align-items-start">--%>
<%--        <div class="col border d-flex justify-content-center">--%>
<%--            НОМЕР--%>
<%--        </div>--%>
<%--        <div class="col border d-flex justify-content-center">--%>
<%--            СОДЕРЖАНИЕ--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <div class="row border list-group-item-action d-flex align-items-start">--%>
<%--        <div class="col border d-flex justify-content-center">--%>
<%--            НОМЕР--%>
<%--        </div>--%>
<%--        <div class="col border d-flex justify-content-center">--%>
<%--            СОДЕРЖАНИЕ--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <div class="row border list-group-item-action d-flex align-items-start">--%>
<%--        <div class="col border d-flex justify-content-center">--%>
<%--            НОМЕР--%>
<%--        </div>--%>
<%--        <div class="col border d-flex justify-content-center">--%>
<%--            СОДЕРЖАНИЕ--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <div class="row border list-group-item-action d-flex align-items-start">--%>
<%--        <div class="col border d-flex justify-content-center">--%>
<%--            НОМЕР--%>
<%--        </div>--%>
<%--        <div class="col border d-flex justify-content-center">--%>
<%--            СОДЕРЖАНИЕ--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>