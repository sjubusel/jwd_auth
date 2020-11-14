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

<html>
<head>
    <title>
        <fmt:message bundle="${jspMessages}"
                     key="admissionDoctorSubMenu.visitDetail"/>
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
                <%--CONTENT ITSELF--%>
                <c:otherwise>
                    <h1>
                        <fmt:message bundle="${jspMessages}"
                                     key="visitDetail.headingVisitInfo"/>
                    </h1>
                    <c:choose>
                        <c:when test="${requestScope.visitInfo ne null}">
                            <table class="table">
                                <tbody>
                                <tr class="row">
                                    <td class="col-5 d-flex align-items-center">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="visitDetail.isPrescriptionsComplete"/>
                                    </td>
                                    <td class="col d-flex align-items-center">
                                        <c:choose>
                                            <c:when test="${(requestScope.visitInfo.isPrescriptionsComplete eq true)}">
                                                <img src="${pageContext.request.contextPath}/img/plus.png"
                                                     alt="plus">
                                            </c:when>
                                            <c:otherwise>
                                                <img src="${pageContext.request.contextPath}/img/minus.png"
                                                     alt="minus">
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                                <tr class="row">
                                    <td class="col-5 d-flex align-items-center">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="visitDetail.visitId"/>
                                    </td>
                                    <td class="col d-flex align-items-center">
                                        <c:choose>
                                            <c:when test="${requestScope.visitInfo.visitId ne null}">
                                                <c:out value="${requestScope.visitInfo.visitId}"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value=""/>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>

                                <tr class="row">
                                    <td class="col-5 d-flex align-items-center">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="visitDetail.visitDateTime"/>
                                    </td>
                                    <td class="col d-flex align-items-center">
                                        <c:choose>
                                            <c:when test="${requestScope.visitInfo.visitDateTime ne null}">
                                                <c:out value="${requestScope.visitInfo.visitDateTime}"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value=""/>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>

                                <tr class="row">
                                    <td class="col-5 d-flex align-items-center">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="visitDetail.patientId"/>
                                    </td>
                                    <td class="col d-flex align-items-center">
                                        <c:choose>
                                            <c:when test="${requestScope.visitInfo.patientId ne null}">
                                                <c:out value="${requestScope.visitInfo.patientId}"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value=""/>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>

                                <tr class="row">
                                    <td class="col-5 d-flex align-items-center">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="visitDetail.patientShortInfo"/>
                                    </td>
                                    <td class="col d-flex align-items-center">
                                        <c:choose>
                                            <c:when test="${requestScope.visitInfo.patientShortInfo ne null}">
                                                <c:out value="${requestScope.visitInfo.patientShortInfo}"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value=""/>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>

                                <tr class="row">
                                    <td class="col-5 d-flex align-items-center">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="visitDetail.visitReason"/>
                                    </td>
                                    <td class="col d-flex align-items-center">
                                        <c:choose>
                                            <c:when test="${requestScope.visitInfo.visitReason ne null}">
                                                <c:out value="${requestScope.visitInfo.visitReason.description}"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value=""/>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>

                                <tr class="row">
                                    <td class="col-5 d-flex align-items-center">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="visitDetail.patientVisitDescriptionInfo"/>
                                    </td>
                                    <td class="col d-flex align-items-center">
                                        <c:choose>
                                            <c:when test="${requestScope.visitInfo.patientVisitDescriptionInfo ne null}">
                                                <c:out value="${requestScope.visitInfo.patientVisitDescriptionInfo}"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value=""/>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>

                                <tr class="row">
                                    <td class="col-5 d-flex align-items-center">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="visitDetail.responsibleDoctorId"/>
                                    </td>
                                    <td class="col d-flex align-items-center">
                                        <c:choose>
                                            <c:when test="${requestScope.visitInfo.responsibleDoctorId ne null}">
                                                <c:out value="${requestScope.visitInfo.responsibleDoctorId}"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value=""/>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>

                                <tr class="row">
                                    <td class="col-5 d-flex align-items-center">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="visitDetail.responsibleDoctorInfo"/>
                                    </td>
                                    <td class="col d-flex align-items-center">
                                        <c:choose>
                                            <c:when test="${requestScope.visitInfo.responsibleDoctorInfo ne null}">
                                                <c:out value="${requestScope.visitInfo.responsibleDoctorInfo}"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value=""/>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>

                                <tr class="row">
                                    <td class="col-5 d-flex align-items-center">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="visitDetail.transportationStatus"/>
                                    </td>
                                    <td class="col d-flex align-items-center">
                                        <c:choose>
                                            <c:when test="${requestScope.visitInfo.transportationStatus ne null}">
                                                <c:out value="${requestScope.visitInfo.transportationStatus.description}"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value=""/>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>

                                <c:choose>
                                    <c:when test="${requestScope.visitInfo.responsibleNonDoctorStaffId > 0}">
                                        <tr class="row">
                                            <td class="col-5 d-flex align-items-center">
                                                <fmt:message
                                                        bundle="${jspMessages}"
                                                        key="visitDetail.responsibleNonDoctorStaffId"/>
                                            </td>
                                            <td class="col d-flex align-items-center">
                                                <c:choose>
                                                    <c:when test="${requestScope.visitInfo.responsibleNonDoctorStaffId ne null}">
                                                        <c:out value="${requestScope.visitInfo.responsibleNonDoctorStaffId}"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:out value=""/>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                        </tr>

                                        <tr class="row">
                                            <td class="col-5 d-flex align-items-center">
                                                <fmt:message
                                                        bundle="${jspMessages}"
                                                        key="visitDetail.responsibleNonDoctorStaffInfo"/>
                                            </td>
                                            <td class="col d-flex align-items-center">
                                                <c:choose>
                                                    <c:when test="${requestScope.visitInfo.responsibleNonDoctorStaffInfo ne null}">
                                                        <c:out value="${requestScope.visitInfo.responsibleNonDoctorStaffInfo}"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:out value=""/>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                        </tr>
                                    </c:when>
                                    <c:otherwise> </c:otherwise>
                                </c:choose>

                                <tr class="row">
                                    <td class="col-5 d-flex align-items-center">
                                        <label for="complaints">
                                            <fmt:message bundle="${jspMessages}"
                                                         key="visitDetail.patientComplaints"/>
                                        </label>
                                    </td>
                                    <td class="col d-flex align-items-center">
                                            <%--TODO make a command--%>
                                        <form action="${pageContext.request.contextPath}/profile?command=change-complaints-of=patient"
                                              method="post" class="d-block">
                                            <textarea type="text"
                                                      name="complaintsInput"
                                                      id="complaints"
                                                      rows="10" cols="60"
                                                      placeholder="<fmt:message bundle="${jspMessages}" key="visitDetail.patientComplaintsPlaceholder"/>"><c:choose><c:when
                                                    test="${requestScope.visitInfo.patientComplaints ne null}"><c:out
                                                    value="${requestScope.visitInfo.patientComplaints}"/></c:when><c:otherwise><c:out
                                                    value=""/></c:otherwise></c:choose></textarea>
                                            <br/>
                                            <button type="submit"
                                                    class="btn align-self-center btn-primary">
                                                <fmt:message
                                                        bundle="${jspMessages}"
                                                        key="visitDetail.complaintsButton"/>
                                            </button>
                                        </form>
                                    </td>
                                </tr>

                                <tr class="row">
                                    <td class="col-5 d-flex align-items-center">
                                        <fmt:message bundle="${jspMessages}"
                                                     key="visitDetail.visitResult"/>
                                    </td>
                                    <td class="col d-flex align-items-center">
                                        <c:choose>
                                            <c:when test="${requestScope.visitInfo.visitResult ne null}">
                                                <c:out value="${requestScope.visitInfo.visitResult.description}"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value=""/>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>

                                <c:choose>
                                    <c:when test="${requestScope.visitInfo.hospitalizationDepartmentId > 0}">
                                        <tr class="row">
                                            <td class="col-5 d-flex align-items-center">
                                                <fmt:message
                                                        bundle="${jspMessages}"
                                                        key="visitDetail.hospitalizationDepartmentId"/>
                                            </td>
                                            <td class="col d-flex align-items-center">
                                                <c:choose>
                                                    <c:when test="${requestScope.visitInfo.hospitalizationDepartmentId ne null}">
                                                        <c:out value="${requestScope.visitInfo.hospitalizationDepartmentId}"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:out value=""/>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                        </tr>
                                        <tr class="row">
                                            <td class="col-5 d-flex align-items-center">
                                                <fmt:message
                                                        bundle="${jspMessages}"
                                                        key="visitDetail.hospitalizationDepartmentInfo"/>
                                            </td>
                                            <td class="col d-flex align-items-center">
                                                <c:choose>
                                                    <c:when test="${requestScope.visitInfo.hospitalizationDepartmentInfo ne null}">
                                                        <c:out value="${requestScope.visitInfo.hospitalizationDepartmentInfo}"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:out value=""/>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                        </tr>
                                    </c:when>
                                    <c:otherwise> </c:otherwise>
                                </c:choose>
                                </tbody>
                            </table>
                        </c:when>
                        <c:otherwise>
                            <fmt:message bundle="${jspMessages}"
                                         key="visitDetail.absenceOfSomething"/>
                        </c:otherwise>
                    </c:choose>
                    <
                </c:otherwise>
            </c:choose>


            <%--CONTENTS END--%>
        </div>
    </div>
</div>

<jsp:include page="../structural_element/footer.jsp"/>
</body>
</html>
