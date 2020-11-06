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
    // function myFunc() {
    //     $("#countryTemp").keyup(function () {
    //         var text = $(this).val();
    //         if (text === "") {
    //
    //         } else {
    //             $("#result").html("");
    //             $.ajax({
    //                 url: "ajax?command=tempFetch",
    //                 method: "post",
    //                 data: {countryInput: text},
    //                 dataType: "json",
    //                 success: function (data) {
    //                     $("#result").html(data.firstCountry);
    //                 }
    //             });
    //         }
    //     });
    // };

    // $(function () {
    $(document).ready(function () {
        $("#countryTemp").keyup(function () {
            var text = $(this).val();
            if (text === "") {

            } else {
                $("#result").html("");
                $.ajax({
                    url: "ajax?command=tempFetch",
                    method: "post",
                    data: {countryInput: text},
                    dataType: "json",
                    success: function (data) {
                        $("#result").html(data.firstCountry);
                    }
                });
            }
        });
    });
    // });
</script>

<div>
    <label for="countryTemp">SEARCH</label><input type="text" id="countryTemp"
                                                  name="countryTemp"
                                                  placeholder="Insert country"/>
</div>

<div id="result">#####################################################</div>

</body>
</html>
