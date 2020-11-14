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
                     key="visitDetail.establishMedPrescription"/>
        <c:out value=" | "/>
        <fmt:message bundle="${jspMessages}"
                     key="admissionDoctorSubMenu.visitsOnControl"/>
        <c:out value=" | "/>
        <fmt:message bundle="${jspMessages}" key="staff.htmlTitle"/>
        <c:out value=" | "/>
        <fmt:message bundle="${jspMessages}" key="all.htmlTitle"/>
    </title>
    <jsp:include page="../structural_element/metahead.jsp"/>
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
                                     key="establishMedicinePrescription.heading"/>
                    </h1>
                    <c:choose>
                        <c:when test="${true}">
                            <form action="${pageContext.request.contextPath}/profile"
                                  method="post">
                                <input type="hidden" name="command"
                                       value="establish-medicine-prescription">
                                <input type="hidden" name="hiddenVisitId"
                                       value="${requestScope.hiddenVisitId}">
                                <div class="form-group form-inline row">
                                    <input type="hidden" id="hiddenMedicineId"
                                           name="hiddenMedicineIdInput"
                                           required/>
                                    <label class="col col-5" for="medicine">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="establishMedicinePrescription.medicineLabel"/>
                                    </label>
                                    <input type="text" id="medicine"
                                           name="medicineInput" required
                                           placeholder="<fmt:message bundle="${jspMessages}"
                                       key="establishMedicinePrescription.medicinePlaceholder"/>"/>
                                </div>
                                <div class="form-group form-inline row">
                                    <label for="targetApplicationDateTime">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="establishMedicinePrescription.targetApplicationDateTime"/>
                                    </label>
                                    <input type="datetime-local"
                                           id="targetApplicationDateTime"
                                           name="targetApplicationDateTimeInput"
                                           required/>
                                </div>
                                <div class="form-group form-inline row">
                                    <label for="dosage">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="establishMedicinePrescription.dosage"/>
                                    </label>
                                    <input type="text" id="dosage"
                                           name="dosageInput"
                                           required
                                           placeholder="<fmt:message bundle="${jspMessages}"
                                                     key="establishMedicinePrescription.dosagePlaceholder"/>"
                                           pattern="([0-9]+)|([0-9]+\.[0-9]+)">
                                </div>
                                <div class="col d-flex align-items-center pl-0 pr-0">
                                    <div class="form-control col form-check form-check-inline">
                                        <input type="radio"
                                               class="form-check-input"
                                               id="medicineMeasureUnitTablet"
                                               name="medicineMeasureUnitInput"
                                               checked
                                               value="TABLET">
                                        <label for="medicineMeasureUnitTablet"
                                               class="form-check-label"
                                               style="margin-right: 10px">
                                            <fmt:message bundle="${jspMessages}"
                                                         key="establishMedicinePrescription.medicineMeasureUnitTablet"/>
                                        </label>

                                        <input type="radio"
                                               class="form-check-input"
                                               id="medicineMeasureUnitAmpoule"
                                               name="medicineMeasureUnitInput"
                                               value="AMPOULE">
                                        <label for="medicineMeasureUnitAmpoule"
                                               class="form-check-label"
                                               style="margin-right: 10px">
                                            <fmt:message bundle="${jspMessages}"
                                                         key="establishMedicinePrescription.medicineMeasureUnitAmpoule"/>
                                        </label>

                                        <input type="radio"
                                               class="form-check-input"
                                               id="medicineMeasureVial"
                                               name="medicineMeasureUnitInput"
                                               value="VIAL">
                                        <label for="medicineMeasureVial"
                                               class="form-check-label"
                                               style="margin-right: 10px">
                                            <fmt:message bundle="${jspMessages}"
                                                         key="establishMedicinePrescription.medicineMeasureVial"/>
                                        </label>

                                        <input type="radio"
                                               class="form-check-input"
                                               id="medicineMeasureUnitSyrette"
                                               name="medicineMeasureUnitInput"
                                               value="SYRETTE">
                                        <label for="medicineMeasureUnitSyrette"
                                               class="form-check-label"
                                               style="margin-right: 10px">
                                            <fmt:message bundle="${jspMessages}"
                                                         key="establishMedicinePrescription.medicineMeasureUnitSyrette"/>
                                        </label>
                                    </div>
                                </div>
                                <div>
                                    <button type="submit"
                                            class="btn align-self-center btn-primary ">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="establishMedicinePrescription.button"/>
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
