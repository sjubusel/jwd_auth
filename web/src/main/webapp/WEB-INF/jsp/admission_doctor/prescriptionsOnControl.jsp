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
<c:set var="activeSubMenuStaffTab" value="prescriptionsOnControl" scope="page"/>

<fmt:setBundle basename="jspResources" var="jspMessages"/>

<html>
<head>
    <title>
        <fmt:message bundle="${jspMessages}"
                     key="admissionDoctorSubMenu.prescriptionsOnControl"/>
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
            <%--CONTENTS START--%>

            <c:choose>
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
                    <c:choose>
                        <c:when test="${requestScope.prescriptions ne null}">
                            <%--header--%>
                            <div class="row d-flex mb-1 border">
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="newNonMedicinePrescriptions.prescriptioDateTime"/>
                                </div>
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="newNonMedicinePrescriptions.prescriptionId"/>
                                </div>
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="newNonMedicinePrescriptions.patientInfo"/>
                                </div>
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="newNonMedicinePrescriptions.prescriptionDescription"/>
                                </div>
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="newNonMedicinePrescriptions.prescribingStaff"/>
                                </div>
                            </div>
                            <%--contents--%>
                            <c:forEach var="prescription"
                                       items="${requestScope.prescriptions}">
                                    <div class="row d-flex mb-1 border align-items-center">
                                        <div class="col">
                                            <c:out value="${prescription.prescriptionDateTime}"/>
                                        </div>
                                        <div class="col">
                                            <c:out value="${prescription.prescriptionId}"/>
                                        </div>
                                        <div class="col">
                                            <c:out value="${prescription.patientInfo}"/>
                                        </div>
                                        <div class="col">
                                            <c:out value="${prescription.prescriptionDescription}"/>
                                        </div>
                                        <div class="col">
                                            <c:out value="${prescription.prescribingStaffInfo}"/>
                                        </div>
                                    </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <fmt:message bundle="${jspMessages}"
                                         key="prescriptionsOnControl.noControlledNonPrescriptions"/>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>


            <%--CONTENTS END--%>
        </div>
    </div>
</div>

<jsp:include page="../structural_element/footer.jsp"/>
</body>
</html>
