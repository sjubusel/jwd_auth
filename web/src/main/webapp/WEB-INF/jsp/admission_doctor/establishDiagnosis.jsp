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
<c:set var="activeSubMenuStaffTab" value="visitsOnControl" scope="page"/>

<fmt:setBundle basename="jspResources" var="jspMessages"/>
<fmt:setBundle basename="registrationRegExp" var="regEx"/>

<html>
<head>
    <title>
        <fmt:message bundle="${jspMessages}"
                     key="visitDetail.establishDiagnosis"/>
        <c:out value=" | "/>
        <fmt:message bundle="${jspMessages}"
                     key="admissionDoctorSubMenu.visitsOnControl"/>
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
            let searchDisease = null;
            $("#disease").keyup(function () {
                if (searchDisease !== null) {
                    searchDisease.abort();
                }
                let text = $(this).val();
                if (text == null) {
                    $("#diseaseResult").html("");
                } else {
                    $("#diseaseResult").html("");
                    searchDisease = $.ajax({
                        url: "ajax?command=fetch-diseases-in-establish-diagnosis-jsp",
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
                    <h1>
                        <fmt:message bundle="${jspMessages}"
                                     key="establishDiagnosis.heading"/>
                    </h1>

                    <c:choose>
                        <c:when test="${requestScope.changeResult ne null}">
                            <c:if test="${requestScope.changeResult eq 'success'}">
                                <div class="alert alert-success" role="alert">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="establishDiagnosis.changeResultSuccess"/>
                                </div>
                            </c:if>
                            <c:if test="${requestScope.changeResult eq 'techError'}">
                                <div class="alert alert-danger" role="alert">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="establishDiagnosis.changeResultTechError"/>
                                </div>
                            </c:if>
                            <c:if test="${requestScope.changeResult eq 'validationError'}">
                                <div class="alert alert-danger" role="alert">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="establishDiagnosis.changeResultValidationError"/>
                                </div>
                            </c:if>
                        </c:when>
                    </c:choose>

                    <c:choose>
                        <c:when test="${true}">
                            <form action="${pageContext.request.contextPath}/profile"
                                  method="post">
                                <input type="hidden" name="command"
                                       value="establish-diagnosis">
                                <input type="hidden" name="hiddenVisitId"
                                       value="${requestScope.hiddenVisitId}">
                                <div class="form-group form-inline row">
                                    <label class="col col-5" for="disease">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="establishDiagnosis.diseaseLabel"/>
                                    </label>
                                    <input type="hidden" id="hiddenDiseaseId"
                                           name="hiddenDiseaseIdInput"
                                           required/>
                                    <input type="text" id="disease"
                                           name="diseaseInput" required
                                           placeholder="<fmt:message bundle="${jspMessages}"
                                       key="establishDiagnosis.diseasePlaceholder"/>"/>
                                </div>
                                    <%--AJAX RESULT--%>
                                <div id="diseaseResult"
                                     class="overflow-auto"
                                     style="max-height: 100px">
                                </div>
                                <div class="form-group form-inline row">
                                    <label class="col col-5"
                                           for="diagnosisDescription">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="establishDiagnosis.diseaseDescriptionLabel"/>
                                    </label>
                                    <textarea type="text" required rows="10"
                                              cols="60"
                                              id="diagnosisDescription"
                                              name="diagnosisDescriptionInput"
                                              placeholder="<fmt:message bundle="${jspMessages}" key="establishDiagnosis.diseaseDescriptionPlaceholder"/>"></textarea>
                                </div>
                                <div>
                                    <button type="submit"
                                            class="btn align-self-center btn-primary ">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="establishDiagnosis.button"/>
                                    </button>
                                </div>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <c:out value=""/>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>
        </div>

    </div>
</div>

<jsp:include page="../structural_element/footer.jsp"/>
</body>
</html>
