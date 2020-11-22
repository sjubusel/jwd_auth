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
<c:set var="activeSubMenuStaffTab" value="refuseToHospitalize" scope="page"/>

<fmt:setBundle basename="jspResources" var="jspMessages"/>
<fmt:setBundle basename="registrationRegExp" var="regEx"/>

<html>
<head>
    <title>
        <fmt:message bundle="${jspMessages}"
                     key="staff.formRefuseToHospitalize"/>
        <c:out value=" | "/>
        <fmt:message bundle="${jspMessages}"
                     key="staff.refuseToHospitalize"/>
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
            let searchMedicine = null;
            $("#medicine").keyup(function () {
                if (searchMedicine !== null) {
                    searchMedicine.abort();
                }
                let text = $(this).val();
                if (text == null) {
                    $("#medicineResult").html("");
                } else {
                    $("#medicineResult").html("");
                    searchMedicine = $.ajax({
                        url: "ajax?command=fetch-medicines-in-establish-medicine_prescription-jsp",
                        method: "post",
                        data: $("#medicine").serialize(),
                        datatype: "json",
                        success: function (data) {
                            if (data == null) {
                                $("#medicineResult").html("<div class=\"mb-3\"><small><em><fmt:message bundle="${jspMessages}"
                                           key="profileSubMenu.changePatientInfo.ajax.validation"/></em></small></div>");
                                return;
                            }
                            if (data.length === 0) {
                                $("#medicineResult").html("<div class=\"mb-3\"><small><em><fmt:message bundle="${jspMessages}"
                                           key="profileSubMenu.changePatientInfo.ajax.zeroResult"/></em></small></div>");
                                return;
                            }
                            let parent = document.getElementById("medicineResult");
                            for (let i = 0; i < data.length; i++) {
                                let childRow = document.createElement("div");
                                childRow.className += "row border list-group-item-action d-flex align-items-start";
                                parent.appendChild(childRow);

                                let childColId = document.createElement("div");
                                childColId.className += "col border d-none justify-content-center";
                                childRow.appendChild(childColId);

                                <%--suppress JSUnresolvedVariable --%>
                                childColId.innerHTML = data[i].medicineId;
                                childRow.setAttribute("onclick", "changeHiddenInput(\"hiddenMedicineId\", \"medicine\", this, this.parentElement);");

                                let childColName = document.createElement("div");
                                childColName.className += "col border d-flex justify-content-center";
                                childRow.appendChild(childColName);
                                childColName.innerHTML = data[i].medicineInfo;
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
                    <%--                    <c:if test="${requestScope.refusalResult ne null}">--%>
                    <%--                        <c:choose>--%>
                    <%--                            <c:when test="${requestScope.refusalResult eq 'techError'}">--%>
                    <%--                                <div class="alert alert-danger" role="alert">--%>
                    <%--                                    <fmt:message bundle="${jspMessages}"--%>
                    <%--                                                 key="makeVisitDecision.refusalResultTechError"/>--%>
                    <%--                                </div>--%>
                    <%--                            </c:when>--%>
                    <%--                            <c:when test="${requestScope.refusalResult eq 'validationError'}">--%>
                    <%--                                <div class="alert alert-danger" role="alert">--%>
                    <%--                                    <fmt:message bundle="${jspMessages}"--%>
                    <%--                                                 key="makeVisitDecision.refusalResultValError"/>--%>
                    <%--                                </div>--%>
                    <%--                            </c:when>--%>
                    <%--                        </c:choose>--%>
                    <%--                    </c:if>--%>
                    <h1>
                        <fmt:message bundle="${jspMessages}"
                                     key="refuseToHospitalize.heading"/>
                    </h1>
                    <br>
                    <h5>
                        <fmt:message bundle="${jspMessages}"
                                     key="refuseToHospitalize.recommendationHeading"/>
                    </h5>
                    <c:choose>
                        <c:when test="${requestScope.cancelResult ne null}">
                            <c:if test="${requestScope.cancelResult eq 'success'}">
                                <div class="alert alert-success">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="refuseToHospitalize.successCancelResult"/>
                                </div>
                            </c:if>
                            <c:if test="${requestScope.cancelResult eq 'techError'}">
                                <div class="alert alert-danger">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="refuseToHospitalize.techErrorCancelResult"/>
                                </div>
                            </c:if>
                            <c:if test="${requestScope.cancelResult eq 'validationError'}">
                                <div class="alert alert-danger">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="refuseToHospitalize.validationErrorCancelResult"/>
                                </div>
                            </c:if>
                        </c:when>
                    </c:choose>
                    <c:choose>
                        <c:when test="${requestScope.medRecoms ne null}">
                            <div class="row d-flex mb-1 border">
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="refuseToHospitalize.recommendationDateTime"/>
                                </div>
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="refuseToHospitalize.medicineInfo"/>
                                </div>
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="refuseToHospitalize.intakeInstructions"/>
                                </div>
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="refuseToHospitalize.actions"/>
                                </div>
                            </div>
                            <c:forEach var="recommendation"
                                       items="${requestScope.medRecoms}">
                                <form action="${pageContext.request.contextPath}/profile"
                                      method="post">
                                    <input type="hidden" name="command"
                                           value="cancel-refusal-medicine-recommendation">
                                    <input type="hidden"
                                           name="hiddenRecommendationIdInput"
                                           value="${recommendation.recommendationId}">
                                    <input type="hidden" name="hiddenVisitId"
                                           value="${requestScope.hiddenVisitId}">
                                    <div class="row d-flex mb-1 border align-items-center">
                                        <div class="col">
                                            <c:out value="${recommendation.recommendationDateTime}"/>
                                        </div>
                                        <div class="col">
                                            <c:out value="${recommendation.medicineInfo}"/>
                                        </div>
                                        <div class="col">
                                            <c:out value="${recommendation.intakeInstructions}"/>
                                        </div>
                                        <div class="col">
                                            <button type="submit"
                                                    class="btn align-self-center btn-primary">
                                                <fmt:message
                                                        bundle="${jspMessages}"
                                                        key="refuseToHospitalize.cancel"/>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <fmt:message bundle="${jspMessages}"
                                         key="refuseToHospitalize.noRecords"/>
                        </c:otherwise>
                    </c:choose>
                    <br>
                    <br>
                    <h5>
                        <fmt:message bundle="${jspMessages}"
                                     key="refuseToHospitalize.newRecommendationForm"/>
                    </h5>
                    <c:choose>
                        <c:when test="${requestScope.addResult ne null}">
                            <c:if test="${requestScope.addResult eq 'success'}">
                                <div class="alert alert-success">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="refuseToHospitalize.successAddResult"/>
                                </div>
                            </c:if>
                            <c:if test="${requestScope.addResult eq 'techError'}">
                                <div class="alert alert-danger">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="refuseToHospitalize.techErrorAddResult"/>
                                </div>
                            </c:if>
                            <c:if test="${requestScope.addResult eq 'validationError'}">
                                <div class="alert alert-danger">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="refuseToHospitalize.validationErrorAddResult"/>
                                </div>
                            </c:if>
                        </c:when>
                    </c:choose>
                    <form action="${pageContext.request.contextPath}/profile"
                          method="post">
                        <input type="hidden" name="command"
                               value="add-refusal-medicine-recommendation">
                        <input type="hidden" name="hiddenVisitId"
                               value="${requestScope.hiddenVisitId}">
                        <input type="hidden" id="hiddenMedicineId"
                               name="hiddenMedicineIdInput"
                               required/>
                        <div class="form-group form-inline row mb-3">
                            <label class="col col-5 text-left" for="medicine">
                                <fmt:message bundle="${jspMessages}"
                                             key="establishMedicinePrescription.medicineLabel"/>
                            </label>
                            <input type="text" id="medicine" class="col"
                                   name="medicineInput" required
                                   placeholder="<fmt:message bundle="${jspMessages}"
                                       key="establishMedicinePrescription.medicinePlaceholder"/>"/>
                        </div>
                        <div id="medicineResult"
                             class="overflow-auto"
                             style="max-height: 100px">
                        </div>
                        <div class="form-group form-inline row mb-3">
                            <label class="col col-5 text-left"
                                   for="intakeInstructions">
                                <fmt:message bundle="${jspMessages}"
                                             key="refuseToHospitalize.intakeInstructions"/>
                            </label>
                            <input type="text" id="intakeInstructions"
                                   name="intakeInstructionsInput" required
                                   maxlength="255" class="col"
                                   placeholder="<fmt:message bundle="${jspMessages}"
                                       key="refuseToHospitalize.intakeInstructionsPlaceholder"/>"/>
                        </div>
                        <div>
                            <button type="submit"
                                    class="btn align-self-center btn-primary ">
                                <fmt:message bundle="${jspMessages}"
                                             key="refuseToHospitalize.button"/>
                            </button>
                        </div>
                    </form>

                </c:otherwise>
            </c:choose>
        </div>

    </div>
</div>

<jsp:include page="../structural_element/footer.jsp"/>
</body>
</html>
