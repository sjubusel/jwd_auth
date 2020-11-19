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
</head>
<body>
<div class="main-content">
    <%@ include file="../structural_element/header.jsp" %>
    <div class="row mt-2 mr-2 ml-2">
        <%@ include file="admissionDoctorSubMenu.jsp" %>
        <div class="bg-light d-inline-block col">
            <h1>
                <fmt:message bundle="${jspMessages}"
                             key="executePrescriptionByItsId.heading"/>
            </h1>
            <form action="${pageContext.request.contextPath}/profile"
                  method="post">
                <div class="form-group form-inline row ml-1 mr-1">
                    <input type="hidden" name="command"
                           value="execute-prescription-by-its-id">
                    <label for="prescriptionId">
                        <fmt:message bundle="${jspMessages}"
                                     key="executePrescriptionByItsId.label"/>
                    </label>
                    <input type="text" name="prescriptionIdInput" required
                           autofocus id="prescriptionId" class="col"
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
        </div>
    </div>
</div>
</body>
</html>
