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
<c:set var="activeSubMenuStaffTab" value="executePrescriptionByItsId"
       scope="page"/>

<fmt:setBundle basename="jspResources" var="jspMessages"/>
<fmt:setBundle basename="registrationRegExp" var="regEx"/>

<html>
<head>
    <title>
        <fmt:message bundle="${jspMessages}"
                     key="admissionDoctorSubMenu.executePrescriptionByItsId"/>
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
                    <c:if test="${requestScope.prescription ne null}">
                        <h1>
                            <fmt:message bundle="${jspMessages}"
                                         key="executePrescriptionByItsId.detailedHeading"/>
                        </h1>
                        <c:choose>
                            <c:when test="${(requestScope.prescription.executionDateTime eq null)
                             && (requestScope.prescription.patientDisagreementDateTime eq null)}">
                                <table class="table">
                                    <tbody>
                                    <tr class="row">
                                        <td class="col-3 d-flex align-items-center">
                                            <fmt:message bundle="${jspMessages}"
                                                         key="executePrescriptionByItsId.prescriptionId"/>
                                        </td>
                                        <td class="col d-flex align-items-center">
                                            <c:choose>
                                                <c:when test="${requestScope.prescription.prescriptionId > 0}">
                                                    <c:out value="${requestScope.prescription.prescriptionId}"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:out value=""/>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                    <tr class="row">
                                        <td class="col-3 d-flex align-items-center">
                                            <fmt:message bundle="${jspMessages}"
                                                         key="executePrescriptionByItsId.prescriptionDateTime"/>
                                        </td>
                                        <td class="col d-flex align-items-center">
                                            <c:choose>
                                                <c:when test="${requestScope.prescription.prescriptionDateTime ne null}">
                                                    <c:out value="${requestScope.prescription.prescriptionDateTime}"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:out value=""/>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                    <tr class="row">
                                        <td class="col-3 d-flex align-items-center">
                                            <fmt:message bundle="${jspMessages}"
                                                         key="executePrescriptionByItsId.patientInfo"/>
                                        </td>
                                        <td class="col d-flex align-items-center">
                                            <c:choose>
                                                <c:when test="${requestScope.prescription.patientInfo ne null}">
                                                    <c:out value="${requestScope.prescription.patientInfo}"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:out value=""/>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                    <tr class="row">
                                        <td class="col-3 d-flex align-items-center">
                                            <fmt:message bundle="${jspMessages}"
                                                         key="executePrescriptionByItsId.prescriptionDescription"/>
                                        </td>
                                        <td class="col d-flex align-items-center">
                                            <c:choose>
                                                <c:when test="${requestScope.prescription.prescriptionDescription ne null}">
                                                    <c:out value="${requestScope.prescription.prescriptionDescription}"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:out value=""/>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                    <tr class="row">
                                        <td class="col-3 d-flex align-items-center">
                                            <fmt:message bundle="${jspMessages}"
                                                         key="executePrescriptionByItsId.prescribingStaffInfo"/>
                                        </td>
                                        <td class="col d-flex align-items-center">
                                            <c:choose>
                                                <c:when test="${requestScope.prescription.prescribingStaffInfo ne null}">
                                                    <c:out value="${requestScope.prescription.prescribingStaffInfo}"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:out value=""/>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                                <h1>
                                    <fmt:message bundle="${jspMessages}"
                                                 key="executePrescriptionByItsId.executionResultHeading"/>
                                </h1>
                                <form action="${pageContext.request.contextPath}/profile"
                                      method="post">
                                    <div class="row">
                                        <input type="hidden"
                                               name="command"
                                               value="staff-execute-non-medicine-prescription">
                                        <input type="hidden"
                                               name="hiddenPrescriptionIdInput"
                                               value="${requestScope.prescription.prescriptionId}">
                                        <label class="d-flex justify-content-center">
                                            <fmt:message bundle="${jspMessages}"
                                                         key="executePrescriptionByItsId.executionResult"/>
                                            <textarea type="text"
                                                      name="executionResultInput"
                                                      rows="10"
                                                      cols="100"
                                                      required
                                                      placeholder="<fmt:message bundle="${jspMessages}"
                                         key="executePrescriptionByItsId.executionResultPlaceholder"/>"></textarea>
                                        </label>
                                    </div>
                                    <div class="row">
                                        <button type="submit"
                                                class="btn align-self-center btn-primary">
                                            <fmt:message
                                                    bundle="${jspMessages}"
                                                    key="executePrescriptionByItsId.executeButton"/>
                                        </button>
                                    </div>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <fmt:message bundle="${jspMessages}"
                                             key="executePrescriptionByItsId.alreadyExecuted"/>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                    <br>
                    <c:if test="${requestScope.executionResult ne null}">
                        <c:choose>
                            <c:when test="${requestScope.executionResult eq 'success'}">
                                <div class="alert alert-success" role="alert">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="executePrescriptionByItsId.executionResultSuccess"/>
                                </div>
                            </c:when>
                            <c:when test="${requestScope.executionResult eq 'techError'}">
                                <div class="alert alert-danger" role="alert">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="executePrescriptionByItsId.executionResultTechError"/>
                                </div>
                            </c:when>
                            <c:when test="${requestScope.executionResult eq 'validationError'}">
                                <div class="alert alert-danger" role="alert">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="executePrescriptionByItsId.executionResultValidationError"/>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <fmt:message bundle="${jspMessages}"
                                             key="main.unknownCondition"/>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                    <h1>
                        <fmt:message bundle="${jspMessages}"
                                     key="executePrescriptionByItsId.heading"/>
                    </h1>
                    <form action="${pageContext.request.contextPath}/profile"
                          method="post">
                        <div class="form-group form-inline row ml-1 mr-1">
                            <input type="hidden" name="command"
                                   value="go-to-detail-execute-prescription-by-its-id">
                            <label for="prescriptionId">
                                <fmt:message bundle="${jspMessages}"
                                             key="executePrescriptionByItsId.label"/>
                            </label>
                            <input type="text" name="prescriptionIdInput"
                                   required
                                   id="prescriptionId" class="col"
                                   placeholder="<fmt:message bundle="${jspMessages}"
                           key="executePrescriptionByItsId.placeholder"/>"
                                   pattern="[0-9]+">
                        </div>
                        <button type="submit"
                                class="btn align-self-center btn-primary">
                            <fmt:message
                                    bundle="${jspMessages}"
                                    key="executePrescriptionByItsId.button"/>
                        </button>
                    </form>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
</body>
</html>
