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
<c:set var="activeSubMenuStaffTab" value="newMedPrescriptions" scope="page"/>

<fmt:setBundle basename="jspResources" var="jspMessages"/>

<html>
<head>
    <title>
        <fmt:message bundle="${jspMessages}"
                     key="admissionDoctorSubMenu.newMedPrescriptions"/>
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
<%--                    <c:if test="${requestScope.acceptResult ne null}">--%>
<%--                        <c:choose>--%>
<%--                            <c:when test="${requestScope.acceptResult eq 'success'}">--%>
<%--                                <div class="alert alert-success"--%>
<%--                                     role="alert">--%>
<%--                                    &lt;%&ndash;TODO create a command &ndash;%&gt;--%>
<%--                                    <a href="${pageContext.request.contextPath}/profile?command=go-to-doctor-view-controlled-visit&hiddenVisitId=${requestScope.hiddenVisitId}">--%>
<%--                                        <fmt:message--%>
<%--                                                bundle="${jspMessages}"--%>
<%--                                                key="newMedPrescriptions.successAcceptance"/>--%>
<%--                                    </a>--%>
<%--                                </div>--%>
<%--                            </c:when>--%>
<%--                            <c:when test="${requestScope.acceptResult eq 'techError'}">--%>
<%--                                <div class="alert alert-danger"--%>
<%--                                     role="alert">--%>
<%--                                    <fmt:message bundle="${jspMessages}"--%>
<%--                                                 key="newMedPrescriptions.techErrorAccept"/>--%>
<%--                                </div>--%>
<%--                            </c:when>--%>
<%--                            <c:when test="${requestScope.acceptResult eq 'validationError'}">--%>
<%--                                <fmt:message bundle="${jspMessages}"--%>
<%--                                             key="newMedPrescriptions.validationErrorAccept"/>--%>
<%--                            </c:when>--%>
<%--                            <c:otherwise>--%>
<%--                                <div class="alert alert-danger"--%>
<%--                                     role="alert">--%>
<%--                                    <fmt:message bundle="${jspMessages}"--%>
<%--                                                 key="main.unknownCondition"/>--%>
<%--                                </div>--%>
<%--                            </c:otherwise>--%>
<%--                        </c:choose>--%>
<%--                    </c:if>--%>
                    <c:choose>
                        <c:when test="${requestScope. ne null}">
                            <%--header--%>
                            <div class="row d-flex mb-1 border">
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="newMedPrescriptions.prescriptionDateTime"/>
                                </div>
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="newMedPrescriptions.patientInfo"/>
                                </div>
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="newMedPrescriptions.medicineInfo"/>
                                </div>
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="newMedPrescriptions.dosageInfo"/>
                                </div>
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="newMedicinePrescriptions.buttons"/>
                                </div>
                            </div>
                            <%--contents--%>
                            <c:forEach var="medPrescription"
                                       items="${requestScope.medicinePrescriptions}">
                                <form action="${pageContext.request.contextPath}/profile"
                                      method="post">
                                    <input type="hidden" name="command"
                                           <%--TODO write command--%>
                                           value="staff-accept-medicine-prescription"/>

                                    <div class="row d-flex mb-1 border align-items-center">
                                        <input type="hidden"
                                               name="hiddenVisitId"
                                               value="<c:out value="${visits.visitId}"/>"/>
                                            <%--1st column--%>
                                        <div class="col">
                                            <c:out value="${visits.visitDateTime}"/>
                                        </div>
                                        <div class="col">
                                            <c:out value="${visits.patientShortInfo}"/>
                                        </div>
                                        <div class="col">
                                            <c:out value="${visits.patientVisitDescriptionInfo}"/>
                                        </div>
                                        <div class="col">
                                            <button type="submit"
                                                    class="btn align-self-center btn-primary">
                                                <fmt:message
                                                        bundle="${jspMessages}"
                                                        key="newMedicinePrescriptions.execute"/>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <fmt:message bundle="${jspMessages}"
                                         key="newMedicinePrescriptions.noNewRecords"/>
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
