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
<c:set var="activeSubMenuProfileTab" value="extremelyHazardousDiseases"
       scope="page"/>

<fmt:setBundle basename="jspResources" var="jspMessages"/>
<fmt:setBundle basename="registrationRegExp" var="regEx"/>

<html>
<head>
    <title>
        <fmt:message bundle="${jspMessages}"
                     key="profileSubMenu.extremelyHazardousDiseases"/>
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

        let searchExtremeCases = null;
        jQuery(document).ready(function () {
            let searchExtremeCases = null;
            $("#disease").keyup(function () {
                if (searchExtremeCases !== null) {
                    searchExtremeCases.abort();
                }
                let text = $(this).val();
                if (text == null) {
                    $("#diseaseResult").html("");
                } else {
                    $("#diseaseResult").html("");
                    searchExtremeCases = $.ajax({
                        url: "ajax?command=fetch-extremely-hazardous-diseases-in-extremely-hazardous-diseases-jsp",
                        method: "post",
                        data: $("#disease").serialize(),
                        datatype: "json",
                        success: function (data) {
                            if (data == null) {
                                $("#diseaseResult").html("<div class=\"mb-3\"><small><em><fmt:message bundle="${jspMessages}"
                                           key="profileSubMenu.changePatientInfo.ajax.validation"/></em></small></div>");
                                return;
                            }
                            if (data.length === 0) {
                                $("#diseaseResult").html("<div class=\"mb-3\"><small><em><fmt:message bundle="${jspMessages}"
                                           key="profileSubMenu.changePatientInfo.ajax.zeroResult"/></em></small></div>");
                                return;
                            }
                            let parent = document.getElementById("diseaseResult");
                            for (let i = 0; i < data.length; i++) {
                                let childRow = document.createElement("div");
                                childRow.className += "row border list-group-item-action d-flex align-items-start";
                                parent.appendChild(childRow);

                                let childColId = document.createElement("div");
                                childColId.className += "col border d-none justify-content-center";
                                childRow.appendChild(childColId);

                                <%--suppress JSUnresolvedVariable --%>
                                childColId.innerHTML = data[i].diseaseId;
                                childRow.setAttribute("onclick", "changeHiddenInput(\"hiddenDiseaseId\", \"disease\", this, this.parentElement);");

                                let childColName = document.createElement("div");
                                childColName.className += "col border d-flex justify-content-center";
                                childRow.appendChild(childColName);
                                childColName.innerHTML = data[i].diseaseName;
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
        <div class="bg-light d-inline-block col">

            <c:choose>
                <c:when test="${requestScope.error ne null}">
                    <c:choose>
                        <%-- a tech error --%>
                        <c:when test="${requestScope.error eq 'tech'}">
                            <div class="alert alert-danger" role="alert">
                                <fmt:message bundle="${jspMessages}"
                                             key="profileSubMenu.extremelyHazardousDiseases.techError"/>
                            </div>
                        </c:when>
                        <%-- a validation error --%>
                        <c:when test="${requestScope.error eq 'val'}">
                            <div class="alert alert-danger" role="alert">
                                <fmt:message bundle="${jspMessages}"
                                             key="profileSubMenu.extremelyHazardousDiseases.validationError"/>
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

                    <h1 class="text-left">
                        <fmt:message bundle="${jspMessages}"
                                     key="profileSubMenu.extremelyHazardousDiseases.heading"/>
                    </h1>

                    <c:choose>
                        <c:when test="${requestScope.extremelyHazardousDiseases ne null}">

                            <%--header--%>
                            <div class="row d-flex mb-1 border">
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="profileSubMenu.extremelyHazardousDiseases.columnName"/>
                                </div>
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="profileSubMenu.extremelyHazardousDiseases.columnDetectionDate"/>
                                </div>
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="profileSubMenu.extremelyHazardousDiseases.caseDescription"/>
                                </div>
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="profileSubMenu.extremelyHazardousDiseases.recoveryDate"/>
                                </div>
                            </div>
                            <%--contents--%>
                            <c:forEach var="extremelyHazardousDiseases"
                                       items="${requestScope.extremelyHazardousDiseases}">
                                <div class="row d-flex mb-1 border align-items-center">
                                        <%--1st column--%>
                                    <div class="col">
                                        <c:out value="${extremelyHazardousDiseases.diseaseDescription}"/>
                                    </div>
                                    <div class="col">
                                        <c:out value="${extremelyHazardousDiseases.detectionDate}"/>
                                    </div>
                                    <div class="col">
                                        <c:if test="${extremelyHazardousDiseases.caseDescription ne null}">
                                            <c:out value="${extremelyHazardousDiseases.caseDescription}"/>
                                        </c:if>
                                    </div>
                                    <div class="col">
                                        <c:if test="${extremelyHazardousDiseases.recoveryDate ne null}">
                                            <c:out value="${extremelyHazardousDiseases.recoveryDate}"/>
                                        </c:if>
                                    </div>
                                </div>
                            </c:forEach>

                        </c:when>
                        <c:otherwise>
                            <fmt:message bundle="${jspMessages}"
                                         key="profileSubMenu.extremelyHazardousDiseases.noRecords"/>
                        </c:otherwise>
                    </c:choose>

                    <h1 class="text-left">
                        <fmt:message bundle="${jspMessages}"
                                     key="profileSubMenu.extremelyHazardousDiseases.headingForm"/>
                    </h1>

                    <c:choose>
                        <c:when test="${requestScope.addResult ne null}">
                            <c:choose>
                                <c:when test="${requestScope.addResult eq 'success'}">
                                    <div class="alert alert-success"
                                         role="alert">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="profileSubMenu.extremelyHazardousDiseases.success"/>
                                    </div>
                                </c:when>
                                <%-- a tech error --%>
                                <c:when test="${requestScope.addResult eq 'techError'}">
                                    <div class="alert alert-danger"
                                         role="alert">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="profileSubMenu.extremelyHazardousDiseases.techError"/>
                                    </div>
                                </c:when>
                                <%-- a validation error --%>
                                <c:when test="${requestScope.addResult eq 'validationError'}">
                                    <div class="alert alert-danger"
                                         role="alert">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="profileSubMenu.extremelyHazardousDiseases.validationErrorAfter"/>
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
                        </c:when>
                    </c:choose>

                    <form action="${pageContext.request.contextPath}/profile"
                          method="post">
                        <input type="hidden" name="command"
                            <%-- TODO command add --%>
                               value="profile-extremely-hazardous-diseases-add"/>
                        <div class="form-group form-inline row">
                            <label for="disease"
                                   class="col-4 custom-form-label">
                                <fmt:message bundle="${jspMessages}"
                                             key="profileSubMenu.extremelyHazardousDiseases.diseaseLabel"/>
                            </label>
                            <input type="hidden" id="hiddenDiseaseId"
                                   name="hiddenDiseaseIdInput" value=""/>
                            <input type="text"
                                   class="form-control col"
                                   id="disease"
                                   name="diseaseInput" required
                                   placeholder="<fmt:message bundle="${jspMessages}"
                                                                       key="profileSubMenu.extremelyHazardousDiseases.diseasePlaceholder"/>"
                                   pattern="<fmt:message bundle="${regEx}" key="profileSubMenu.extremelyHazardousDiseases.disease"/>"
                            />
                        </div>
                        <div id="diseaseResult" class="overflow-auto"
                             style="max-height: 100px">
                        </div>

                        <div class="form-group form-inline row">
                            <label for="detectionDate"
                                   class="col-4 custom-form-label">
                                <fmt:message bundle="${jspMessages}"
                                             key="profileSubMenu.extremelyHazardousDiseases.detectionDate"/>
                            </label>
                            <input type="date"
                                   class="form-control col"
                                   id="detectionDate"
                                   name="detectionDateInput" required
                                   placeholder="<fmt:message bundle="${jspMessages}"
                                                                       key="profileSubMenu.extremelyHazardousDiseases.detectionDatePlaceholder"/>"
                            />
                        </div>

                        <div class="form-group form-inline row">
                            <label for="caseDescription"
                                   class="col-4 custom-form-label">
                                <fmt:message bundle="${jspMessages}"
                                             key="profileSubMenu.extremelyHazardousDiseases.caseDescription"/>
                            </label>
                            <input type="text"
                                   class="form-control col"
                                   id="caseDescription"
                                   name="caseDescriptionInput"
                                   placeholder="<fmt:message bundle="${jspMessages}"
                                                                       key="profileSubMenu.extremelyHazardousDiseases.caseDescriptionPlaceholder"/>"
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
    </div>
</div>

<jsp:include page="../structural_element/footer.jsp"/>

</body>
</html>
