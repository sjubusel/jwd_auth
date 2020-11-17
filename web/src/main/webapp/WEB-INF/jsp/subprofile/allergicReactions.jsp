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
<c:set var="activeSubMenuProfileTab" value="allergicReactions" scope="page"/>

<fmt:setBundle basename="jspResources" var="jspMessages"/>
<fmt:setBundle basename="registrationRegExp" var="regEx"/>

<html>
<head>
    <title>
        <fmt:message bundle="${jspMessages}"
                     key="profileSubMenu.allergicReactions"/>
        <c:out value=" | "/>
        <fmt:message bundle="${jspMessages}" key="profile.htmlTitle"/>
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
            let searchFoodReactions = null;
            $("#foodType").keyup(function () {
                if (searchFoodReactions !== null) {
                    searchFoodReactions.abort();
                }
                let text = $(this).val();
                if (text == null) {
                    $("#foodTypeResult").html("");
                } else {
                    $("#foodTypeResult").html("");
                    searchFoodReactions = $.ajax({
                        url: "ajax?command=fetch-food-allergy-in-allergic-reactions-jsp",
                        method: "post",
                        data: $("#foodType").serialize(),
                        datatype: "json",
                        success: function (data) {
                            if (data == null) {
                                $("#foodTypeResult").html("<div class=\"mb-3\"><small><em><fmt:message bundle="${jspMessages}"
                                           key="profileSubMenu.changePatientInfo.ajax.validation"/></em></small></div>");
                                return;
                            }
                            if (data.length === 0) {
                                $("#foodTypeResult").html("<div class=\"mb-3\"><small><em><fmt:message bundle="${jspMessages}"
                                           key="profileSubMenu.changePatientInfo.ajax.zeroResult"/></em></small></div>");
                                return;
                            }
                            let parent = document.getElementById("foodTypeResult");
                            for (let i = 0; i < data.length; i++) {
                                let childRow = document.createElement("div");
                                childRow.className += "row border list-group-item-action d-flex align-items-start";
                                parent.appendChild(childRow);

                                let childColId = document.createElement("div");
                                childColId.className += "col border d-none justify-content-center";
                                childRow.appendChild(childColId);

                                <%--suppress JSUnresolvedVariable --%>
                                childColId.innerHTML = data[i].foodTypeId;
                                childRow.setAttribute("onclick", "changeHiddenInput(\"hiddenFoodTypeId\", \"foodType\", this, this.parentElement);");

                                let childColName = document.createElement("div");
                                childColName.className += "col border d-flex justify-content-center";
                                childRow.appendChild(childColName);
                                childColName.innerHTML = data[i].foodTypeName;
                            }
                        }
                    });
                }
            });

            let searchMedicineReactions = null;
            $("#medicineType").keyup(function () {
                if (searchMedicineReactions !== null) {
                    searchMedicineReactions.abort();
                }
                let text = $(this).val();
                if (text == null) {
                    $("#medicineTypeResult").html("");
                } else {
                    $("#medicineTypeResult").html("");
                    searchMedicineReactions = $.ajax({
                        url: "ajax?command=fetch-medicine-allergy-in-allergic-reactions-jsp",
                        method: "post",
                        data: $("#medicineType").serialize(),
                        datatype: "json",
                        success: function (data) {
                            if (data == null) {
                                $("#medicineTypeResult").html("<div class=\"mb-3\"><small><em><fmt:message bundle="${jspMessages}"
                                           key="profileSubMenu.changePatientInfo.ajax.validation"/></em></small></div>");
                                return;
                            }
                            if (data.length === 0) {
                                $("#medicineTypeResult").html("<div class=\"mb-3\"><small><em><fmt:message bundle="${jspMessages}"
                                           key="profileSubMenu.changePatientInfo.ajax.zeroResult"/></em></small></div>");
                                return;
                            }
                            let parent = document.getElementById("medicineTypeResult");
                            for (let i = 0; i < data.length; i++) {
                                let childRow = document.createElement("div");
                                childRow.className += "row border list-group-item-action d-flex align-items-start";
                                parent.appendChild(childRow);

                                let childColId = document.createElement("div");
                                childColId.className += "col border d-none justify-content-center";
                                childRow.appendChild(childColId);

                                <%--suppress JSUnresolvedVariable --%>
                                childColId.innerHTML = data[i].medicineTypeId;
                                childRow.setAttribute("onclick", "changeHiddenInput(\"hiddenMedicineTypeId\", \"medicineType\", this, this.parentElement);");

                                let childColName = document.createElement("div");
                                childColName.className += "col border d-flex justify-content-center";
                                childRow.appendChild(childColName);
                                childColName.innerHTML = data[i].medicineName;
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

    <div class="row mt-2 mr-2 ml-2">
        <%@ include file="../structural_element/profileSubMenu.jsp" %>
        <%--CONTENTS--%>
        <div class="bg-light d-inline-block col">

            <c:choose>
                <c:when test="${requestScope.error ne null}">
                    <c:choose>
                        <%-- a tech error --%>
                        <c:when test="${requestScope.error eq 'tech'}">
                            <div class="alert alert-danger" role="alert">
                                <fmt:message bundle="${jspMessages}"
                                             key="profileSubMenu.allergicReactions.techError"/>
                            </div>
                        </c:when>
                        <%-- a validation error --%>
                        <c:when test="${requestScope.error eq 'val'}">
                            <div class="alert alert-danger" role="alert">
                                <fmt:message bundle="${jspMessages}"
                                             key="profileSubMenu.allergicReactions.validationError"/>
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
                <%--Here the jsp's contents begin--%>
                <c:otherwise>
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
                    <h1 class="text-left">
                        <fmt:message bundle="${jspMessages}"
                                     key="profileSubMenu.allergicReactionsFood.headingForm"/>
                    </h1>

                    <c:choose>
                        <c:when test="${requestScope.addFoodResult ne null}">
                            <c:choose>
                                <c:when test="${requestScope.addFoodResult eq 'success'}">
                                    <div class="alert alert-success" role="alert">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="profileSubMenu.allergicReactionsFood.success"/>
                                    </div>
                                </c:when>
                                <%-- a tech error --%>
                                <c:when test="${requestScope.addFoodResult eq 'techError'}">
                                    <div class="alert alert-danger" role="alert">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="profileSubMenu.allergicReactionsFood.techError"/>
                                    </div>
                                </c:when>
                                <%-- a validation error --%>
                                <c:when test="${requestScope.addFoodResult eq 'validationError'}">
                                    <div class="alert alert-danger" role="alert">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="profileSubMenu.allergicReactionsFood.validationErrorAfter"/>
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
                    </c:choose>

                    <form action="${pageContext.request.contextPath}/profile"
                          method="post">
                        <input type="hidden" name="command"
                               value="profile-allergic-reactions-food-add"/>
                        <div class="form-group form-inline row">
                            <label for="foodType"
                                   class="col-4 custom-form-label">
                                <fmt:message bundle="${jspMessages}"
                                             key="profileSubMenu.allergicReactionsFood.foodType"/>
                            </label>
                            <input type="hidden" id="hiddenFoodTypeId"
                                   name="hiddenFoodTypeIdInput" value=""/>
                            <input type="text"
                                   class="form-control col"
                                   id="foodType"
                                   name="foodTypeInput" required
                                   placeholder="<fmt:message bundle="${jspMessages}"
                                                                       key="profileSubMenu.allergicReactionsFood.foodType.recipientPlaceholder"/>"
                                   pattern="<fmt:message bundle="${regEx}" key="profileSubMenu.medicalHistoryPermission.recipientPattern"/>"
                            />
                        </div>
                        <div id="foodTypeResult" class="overflow-auto"
                             style="max-height: 100px">
                        </div>

                        <div class="form-group form-inline row">
                            <label for="detectionDate"
                                   class="col-4 custom-form-label">
                                <fmt:message bundle="${jspMessages}"
                                             key="profileSubMenu.allergicReactionsFood.detectionDate"/>
                            </label>
                            <input type="date"
                                   class="form-control col"
                                   id="detectionDate"
                                   name="detectionDateInput" required
                                   placeholder="<fmt:message bundle="${jspMessages}"
                                                                       key="profileSubMenu.allergicReactionsFood.foodType.detectionDatePlaceholder"/>"
                            />
                        </div>

                        <div class="form-group form-inline row">
                            <label for="allergicReactionFoodDescription"
                                   class="col-4 custom-form-label">
                                <fmt:message bundle="${jspMessages}"
                                             key="profileSubMenu.allergicReactionsFood.allergicReactionFoodDescription"/>
                            </label>
                            <input type="text"
                                   class="form-control col"
                                   id="allergicReactionFoodDescription"
                                   name="allergicReactionFoodDescriptionInput"
                                   placeholder="<fmt:message bundle="${jspMessages}"
                                                                       key="profileSubMenu.allergicReactionsFood.foodType.allergicReactionFoodDescriptionPlaceholder"/>"
                            />
                        </div>

                        <button type="submit"
                                class="btn align-self-center btn-primary">
                            <fmt:message bundle="${jspMessages}"
                                         key="profileSubMenu.medicalHistoryPermission.add"/>
                        </button>
                    </form>
                    <%--###########################################################################--%>
                    <h1 class="text-left">
                        <fmt:message bundle="${jspMessages}"
                                     key="profileSubMenu.allergicReactionsMedicine.heading"/>
                    </h1>

                    <c:choose>
                        <c:when test="${requestScope.addMedicineResult ne null}">
                            <c:choose>
                                <c:when test="${requestScope.addMedicineResult eq 'success'}">
                                    <div class="alert alert-success" role="alert">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="profileSubMenu.allergicReactionsMedicine.success"/>
                                    </div>
                                </c:when>
                                <%-- a tech error --%>
                                <c:when test="${requestScope.addMedicineResult eq 'techError'}">
                                    <div class="alert alert-danger" role="alert">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="profileSubMenu.allergicReactionsMedicine.techError"/>
                                    </div>
                                </c:when>
                                <%-- a validation error --%>
                                <c:when test="${requestScope.addMedicineResult eq 'validationError'}">
                                    <div class="alert alert-danger" role="alert">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="profileSubMenu.allergicReactionsMedicine.validationErrorAfter"/>
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
                    </c:choose>

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

                    <h1 class="text-left">
                        <fmt:message bundle="${jspMessages}"
                                     key="profileSubMenu.allergicReactionsMedicine.formHeading"/>
                    </h1>

                    <form action="${pageContext.request.contextPath}/profile"
                          method="post">
                        <input type="hidden" name="command"
                               value="profile-allergic-reactions-medicine-add"/>
                        <div class="form-group form-inline row">
                            <label for="medicineType"
                                   class="col-4 custom-form-label">
                                <fmt:message bundle="${jspMessages}"
                                             key="profileSubMenu.allergicMedicineReactions.medicineType"/>
                            </label>
                            <input type="hidden" id="hiddenMedicineTypeId"
                                   name="hiddenMedicineTypeIdInput" value=""/>
                            <input type="text"
                                   class="form-control col"
                                   id="medicineType"
                                   name="medicineTypeInput" required
                                   placeholder="<fmt:message bundle="${jspMessages}"
                                                                       key="profileSubMenu.allergicMedicineReactions.medicineTypePlaceholder"/>"
                                   pattern="<fmt:message bundle="${regEx}" key="profileSubMenu.changePatientInfo.latinHolderNamePattern"/>"
                            />
                        </div>
                        <div id="medicineTypeResult" class="overflow-auto"
                             style="max-height: 100px">
                        </div>

                        <div class="form-group form-inline row">
                            <label for="detectionDateMedicine"
                                   class="col-4 custom-form-label">
                                <fmt:message bundle="${jspMessages}"
                                             key="profileSubMenu.allergicMedicineReactions.detectionDate"/>
                            </label>
                            <input type="date"
                                   class="form-control col"
                                   id="detectionDateMedicine"
                                   name="detectionDateMedicineInput" required
                                   placeholder="<fmt:message bundle="${jspMessages}"
                                                                       key="profileSubMenu.allergicMedicineReactions.detectionDatePlaceholder"/>"
                            />
                        </div>

                        <div class="form-group form-inline row">
                            <label for="allergicReactionMedicineDescription"
                                   class="col-4 custom-form-label">
                                <fmt:message bundle="${jspMessages}"
                                             key="profileSubMenu.allergicMedicineReactions.allergicReactionMedicineDescription"/>
                            </label>
                            <input type="text"
                                   class="form-control col"
                                   id="allergicReactionMedicineDescription"
                                   name="allergicReactionMedicineDescriptionInput"
                                   placeholder="<fmt:message bundle="${jspMessages}"
                                                                       key="profileSubMenu.allergicMedicineReactions.allergicReactionMedicineDescriptionPlaceholder"/>"
                            />
                        </div>

                        <button type="submit"
                            class="btn align-self-center btn-primary">
                            <fmt:message bundle="${jspMessages}"
                                         key="profileSubMenu.medicalHistoryPermission.add"/>
                        </button>
                    </form>
                </c:otherwise>
            </c:choose>
        </div>
        <%--CONTENTS--%>
    </div>
</div>

<jsp:include page="../structural_element/footer.jsp"/>

</body>
</html>
