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

<c:set var="activeMenuTab" value="patients" scope="page"/>
<c:set var="activeSubMenuTab" value="refusalReferencesForPatient" scope="page"/>

<fmt:setBundle basename="jspResources" var="jspMessages"/>
<fmt:setBundle basename="registrationRegExp" var="regEx"/>

<html>
<head>
    <title>
        <fmt:message bundle="${jspMessages}"
                     key="patientSubMenu.refusalReferencesForPatient"/>
        <c:out value=" | "/>
        <fmt:message bundle="${jspMessages}" key="patients.htmlTitle"/>
        <c:out value=" | "/>
        <fmt:message bundle="${jspMessages}" key="all.htmlTitle"/>
    </title>
    <jsp:include page="../structural_element/metahead.jsp"/>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/jquery-3.5.1.js"></script>
    <script>
        let currentPagePath = "${pageContext.request.contextPath}/profile?command=go-to-refusal-references-for-patient&page=";
        let pageNumber = parseInt(${requestScope.page});
        window.pagesAmount = parseInt(fetchPageQuantity().responseText);

        function fetchPageQuantity() {
            return $.ajax({
                url: "ajax?command=fetch-page-quantity-in-refusal-references-for-patient-jsp",
                method: "post",
                async: false,
                data: {},
                datatype: "json",
                success: function (data) {
                    return parseInt(data);
                }
            });
        }

        function createPageLink(isLiActive, isLiDisabled, page, aInnerHtml) {
            let liElement = document.createElement("li");
            liElement.className = "page-item";
            if (isLiActive === true) {
                liElement.className += " active"
            }
            if (isLiDisabled === true) {
                liElement.className += "  disabled"
            }

            let aElement = document.createElement("a");
            aElement.className = "page-link";
            aElement.href = currentPagePath + page;
            aElement.innerHTML = aInnerHtml;
            liElement.appendChild(aElement);

            return liElement;
        }

        $(document).ready(function () {
                let paginationContainer = document.getElementById("pagination");
                if (pagesAmount === 0) {
                    return;
                }

                if (pageNumber <= pagesAmount) {
                    let minIndex;
                    let maxIndex;

                    if ((pageNumber - 5 >= 0) && pageNumber + 5 <= window.pagesAmount) {
                        minIndex = pageNumber - 5 + 1;
                        maxIndex = pageNumber + 5;
                    } else if (pageNumber - 5 >= 0) {
                        maxIndex = window.pagesAmount;
                        if (maxIndex - 10 >= 0) {
                            minIndex = maxIndex - 10 + 1;
                        } else {
                            minIndex = 1;
                        }
                    } else if (pageNumber + 5 <= 10) {
                        minIndex = 1;
                        if (minIndex + 10 - 1 <= window.pagesAmount) {
                            maxIndex = minIndex + 10 - 1;
                        } else {
                            maxIndex = window.pagesAmount;
                        }
                    } else {
                        minIndex = 1;
                        maxIndex = window.pagesAmount;
                    }

                    for (let i = minIndex; i < pageNumber; i++) {
                        paginationContainer.appendChild(createPageLink(false, false, i, i));
                    }
                    let currentPageLink = createPageLink(true, false, pageNumber, pageNumber);
                    paginationContainer.appendChild(currentPageLink);
                    for (let i = pageNumber + 1; i <= maxIndex; i++) {
                        paginationContainer.appendChild(createPageLink(false, false, i, i));
                    }
                } else {
                    let liElement = createPageLink(true, false, 1, 1);
                    paginationContainer.appendChild(liElement);
                }

                let isPreviousPageDisabled = false;
                if ((pageNumber - 1) < 1) {
                    isPreviousPageDisabled = true;
                }
                paginationContainer.prepend(createPageLink(false, isPreviousPageDisabled, pageNumber - 1, "Previous"));

                let isNextPageDisabled = false;
                if ((pageNumber + 1) > pagesAmount) {
                    isNextPageDisabled = true;
                }
                paginationContainer.appendChild(createPageLink(false, isNextPageDisabled, pageNumber + 1, "Next"));

                paginationContainer.prepend(createPageLink(false, false, 1, "First"));
                paginationContainer.appendChild(createPageLink(false, false, pagesAmount, "Last"))
            }
        );
    </script>
</head>
<body>

<div class="main-content">

    <%@ include file="../structural_element/header.jsp" %>
    <div class="row mt-2 mr-2 ml-2">
        <%@ include file="patientSubMenu.jsp" %>

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
                                     key="allRefusalReferences.heading"/>
                    </h1>
                    <c:choose>
                        <c:when test="${requestScope.refusalReferences ne null}">
                            <div class="row d-flex mb-1 border">
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="allRefusalReferences.numberHead"/>
                                </div>
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="allRefusalReferences.departureDateTime"/>
                                </div>
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="allRefusalReferences.visitDateTime"/>
                                </div>
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="allRefusalReferences.patientInfo"/>
                                </div>
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="allRefusalReferences.visitDescription"/>
                                </div>
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="allRefusalReferences.actions"/>
                                </div>
                            </div>
                            <c:forEach var="reference"
                                       items="${requestScope.refusalReferences}">
                                <form action="${pageContext.request.contextPath}/profile"
                                      method="get">
                                    <input type="hidden" name="command"
                                           value="go-to-patient-refusal-reference-in-detail">
                                    <input type="hidden"
                                           name="hiddenReferenceIdInput"
                                           value="${reference.refusalReferenceId}">
                                    <div class="row d-flex mb-1 border align-items-center">
                                        <div class="col">
                                            <c:out value="${reference.refusalReferenceId}"/>
                                        </div>
                                        <div class="col">
                                            <c:out value="${reference.referenceDatetime}"/>
                                        </div>
                                        <div class="col">
                                            <c:out value="${reference.visitInfo.visitDateTime}"/>
                                        </div>
                                        <div class="col">
                                            <c:out value="${reference.visitInfo.patientShortInfo}"/>
                                        </div>
                                        <div class="col">
                                            <c:out value="${reference.visitInfo.patientVisitDescriptionInfo}"/>
                                        </div>
                                        <div class="col">
                                            <button type="submit"
                                                    class="btn align-self-center btn-primary">
                                                <fmt:message
                                                        bundle="${jspMessages}"
                                                        key="allRefusalReferences.details"/>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </c:forEach>
                            <nav aria-label="Page navigation example">
                                <ul id="pagination"
                                    class="pagination justify-content-center">
                                </ul>
                            </nav>
                        </c:when>
                        <c:otherwise>
                            <fmt:message bundle="${jspMessages}"
                                         key="refuseToHospitalize.noRecords"/>
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
