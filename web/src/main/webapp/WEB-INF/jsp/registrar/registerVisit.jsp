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

<c:set var="activeMenuTab" value="staff" scope="page"/>
<c:set var="activeSubMenuStaffTab" value="registerVisit"
       scope="page"/>

<fmt:setBundle basename="jspResources" var="jspMessages"/>
<fmt:setBundle basename="registrationRegExp" var="regEx"/>

<html>
<head>
    <title>
        <fmt:message bundle="${jspMessages}"
                     key="registrarSubMenu.registerVisit"/>
        <c:out value=" | "/>
        <fmt:message bundle="${jspMessages}" key="staff.htmlTitle"/>
        <c:out value=" | "/>
        <fmt:message bundle="${jspMessages}" key="all.htmlTitle"/>
    </title>
    <jsp:include page="../structural_element/metahead.jsp"/>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/jquery-3.5.1.js">
    </script>
    <script>
        function changeHiddenInput(hiddenInputName, shownInputName, childRow, parent) {
            let hiddenInput = document.getElementById(hiddenInputName);
            let shownInput = document.getElementById(shownInputName);
            hiddenInput.value = childRow.firstChild.innerHTML;
            shownInput.value = childRow.lastChild.innerHTML;
            parent.innerHTML = "<div class=\"mb-3\"><small><em>" + "<fmt:message bundle="${jspMessages}"
                                                 key="profileSubMenu.changePatientInfo.ajax.chosen"/>: "
                + shownInput.value + "</em></small></div>";
        }

        jQuery(document).ready(function () {
            let searchPatient = null;
            $("#person").keyup(function () {
                if (searchPatient !== null) {
                    searchPatient.abort();
                }
                let text = $(this).val();
                if (text == null) {
                    $("#personResult").html("");
                } else {
                    $("#personResult").html("");
                    searchPatient = $.ajax({
                        url: "ajax?command=fetch-persons-in-register-visit-jsp",
                        method: "post",
                        data: $("#person").serialize(),
                        datatype: "json",
                        success: function (data) {
                            if (data == null) {
                                $("#personResult").html("<div class=\"mb-3\"><small><em><fmt:message bundle="${jspMessages}"
                                           key="profileSubMenu.changePatientInfo.ajax.validation"/></em></small></div>");
                                return;
                            }
                            if (data.length === 0) {
                                $("#personResult").html("<div class=\"mb-3\"><small><em><fmt:message bundle="${jspMessages}"
                                           key="profileSubMenu.changePatientInfo.ajax.zeroResult"/></em></small></div>");
                                return;
                            }
                            let parent = document.getElementById("personResult");
                            for (let i = 0; i < data.length; i++) {
                                let childRow = document.createElement("div");
                                childRow.className += "row border list-group-item-action d-flex align-items-start";
                                parent.appendChild(childRow);

                                let childColId = document.createElement("div");
                                childColId.className += "col border d-none justify-content-center";
                                childRow.appendChild(childColId);

                                childColId.innerHTML = data[i].personId;
                                childRow.setAttribute("onclick", "changeHiddenInput(\"hiddenPersonId\", \"person\", this, this.parentElement);");

                                let childColName = document.createElement("div");
                                childColName.className += "col border d-flex justify-content-center";
                                childRow.appendChild(childColName);
                                childColName.innerHTML = data[i].personInfo;
                            }
                        }
                    });
                }
            });
        })
    </script>
</head>
<body>

<div class="main-content">
    <%@ include file="../structural_element/header.jsp" %>

    <div class="row mt-2 mr-2 ml-2">
        <%@ include file="registrarSubMenu.jsp" %>
        <div class="bg-light d-inline-block col">
            <%--CONTENTS START--%>
            <h1>
                <fmt:message bundle="${jspMessages}"
                             key="registerVisit.heading"/>
            </h1>

            <form action="${pageContext.request.contextPath}/profile"
                  method="post">
                <input type="hidden" name="command"
                <%-- TODO command add --%>
                       value="staff-register-visit"/>
                <div class="form-group form-inline row">
                    <label for="person"
                           class="col-4 custom-form-label">
                        <fmt:message bundle="${jspMessages}"
                                     key="registerVisit.personInitData"/>
                    </label>
                    <input type="hidden" id="hiddenPersonId"
                           name="hiddenPersonIdInput" value=""/>
                    <input type="text"
                           class="form-control col"
                           id="person"
                           name="personInput" required
                           placeholder="<fmt:message bundle="${jspMessages}"
                                                                       key="registerVisit.personPlaceholder"/>"
                           pattern="<fmt:message bundle="${regEx}" key="anyName"/>"
                    />
                </div>
                <div id="personResult" class="overflow-auto"
                     style="max-height: 100px">
                </div>
                <div class="row mb-3">
                    <div class="col-4 d-flex align-items-center">
                        <fmt:message bundle="${jspMessages}"
                                     key="registerVisit.visitReason"/>
                    </div>
                    <div class="col d-flex align-items-center pl-0 pr-0">
                        <div class="form-control col form-check form-check-inline">
                            <input type="radio"
                                   class="form-check-input"
                                   id="visitReasonEmergency"
                                   name="visitReasonEmergencyInput" checked
                                   value="EMERGENCY">
                            <label for="visitReasonEmergency"
                                   class="form-check-label"
                                   style="margin-right: 10px">
                                <fmt:message bundle="${jspMessages}"
                                             key="registerVisit.emergency"/>
                            </label>

                            <input type="radio"
                                   class="form-check-input"
                                   id="visitReasonIndependently"
                                   name="visitReasonIndependentlyInput" required
                                   value="INDEPENDENTLY">
                            <label for="visitReasonIndependently"
                                   class="form-check-label"
                                   style="margin-right: 10px">
                                <fmt:message bundle="${jspMessages}"
                                             key="registerVisit.independently"/>
                            </label>
                            <input type="radio"
                                   class="form-check-input"
                                   id="visitReasonPrescription"
                                   name="visitReasonPrescriptionInput"
                                   value="PRESCRIPTION">
                            <label for="visitReasonPrescription"
                                   class="form-check-label"
                                   style="margin-right: 10px">
                                <fmt:message bundle="${jspMessages}"
                                             key="registerVisit.prescription"/>
                            </label>
                        </div>

                    </div>
                </div>

                <div class="form-group form-inline row">
                    <label for="visitReasonDescription"
                           class="col-4 custom-form-label">
                        <fmt:message bundle="${jspMessages}"
                                     key="registerVisit.visitReasonDescription"/>
                    </label>
                    <textarea type="text"
                              class="form-control col"
                              id="visitReasonDescription"
                              name="visitReasonDescriptionInput" required
                              rows="10" cols="30"
                              placeholder="<fmt:message bundle="${jspMessages}"
                                                                       key="registerVisit.visitReasonDescriptionPlaceholder"/>"
                    ></textarea>
                </div>

                <div class="row mb-3">
                    <div class="col-4 d-flex align-items-center">
                        <fmt:message bundle="${jspMessages}"
                                     key="profile.transportationStatus"/>
                    </div>
                    <div class="col d-flex align-items-center pl-0 pr-0">
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
                                   name="transportationStatusInput" checked
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

                <div class="form-group form-inline row">
                    <label for="complaintsDescription"
                           class="col-4 custom-form-label">
                        <fmt:message bundle="${jspMessages}"
                                     key="registerVisit.complaintsDescription"/>
                    </label>
                    <textarea type="text"
                              class="form-control col"
                              id="complaintsDescription"
                              name="complaintsDescriptionInput" required
                              rows="10" cols="30"
                              placeholder="<fmt:message bundle="${jspMessages}"
                                                                       key="registerVisit.complaintsDescriptionPlaceholder"/>"
                    ></textarea>
                </div>

                <button type="submit"
                        class="btn align-self-center btn-primary">
                    <fmt:message bundle="${jspMessages}"
                                 key="profileSubMenu.medicalHistoryPermission.add"/>
                </button>
            </form>
            <%--CONTENTS END--%>
        </div>
    </div>
</div>

</body>
</html>
