<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="jspResources" var="jspMessages"/>

<div class="list-group d-inline-block col-3">
    <a type="button"
       href="${pageContext.request.contextPath}/profile?command=go-to-staff-new-visits"
            <c:choose>
                <c:when test="${pageScope.activeSubMenuStaffTab == 'newVisits'}">
                    class="list-group-item list-group-item-action active"
                </c:when>
                <c:otherwise>
                    class="list-group-item list-group-item-action"
                </c:otherwise>
            </c:choose>
    >
        <fmt:message bundle="${jspMessages}"
                     key="admissionDoctorSubMenu.newVisits"/>
    </a>
    <a type="button"
       href="${pageContext.request.contextPath}/profile?command=go-to-staff-visits-on-control"
            <c:choose>
                <c:when test="${pageScope.activeSubMenuStaffTab == 'visitsOnControl'}">
                    class="list-group-item list-group-item-action active"
                </c:when>
                <c:otherwise>
                    class="list-group-item list-group-item-action"
                </c:otherwise>
            </c:choose>
    >
        <fmt:message bundle="${jspMessages}"
                     key="admissionDoctorSubMenu.visitsOnControl"/>
    </a>
    <a type="button"
       href="${pageContext.request.contextPath}/profile?command=go-to-staff-new-medicine-prescriptions"
            <c:choose>
                <c:when test="${pageScope.activeSubMenuStaffTab == 'newMedPrescriptions'}">
                    class="list-group-item list-group-item-action active"
                </c:when>
                <c:otherwise>
                    class="list-group-item list-group-item-action"
                </c:otherwise>
            </c:choose>
    >
        <fmt:message bundle="${jspMessages}"
                     key="admissionDoctorSubMenu.newMedPrescriptions"/>
    </a>
    <a type="button"
       href="${pageContext.request.contextPath}/profile?command=go-to-staff-new-non-medicine-prescriptions"
            <c:choose>
                <c:when test="${pageScope.activeSubMenuStaffTab == 'newPrescriptions'}">
                    class="list-group-item list-group-item-action active"
                </c:when>
                <c:otherwise>
                    class="list-group-item list-group-item-action"
                </c:otherwise>
            </c:choose>
    >
        <fmt:message bundle="${jspMessages}"
                     key="admissionDoctorSubMenu.newPrescriptions"/>
    </a>
    <a type="button"
       href="${pageContext.request.contextPath}/profile?command=go-to-staff-prescriptions-on-control"
            <c:choose>
                <c:when test="${pageScope.activeSubMenuStaffTab == 'prescriptionsOnControl'}">
                    class="list-group-item list-group-item-action active"
                </c:when>
                <c:otherwise>
                    class="list-group-item list-group-item-action"
                </c:otherwise>
            </c:choose>
    >
        <fmt:message bundle="${jspMessages}"
                     key="admissionDoctorSubMenu.prescriptionsOnControl"/>
    </a>
    <a type="button"
       href="${pageContext.request.contextPath}/profile?command=go-to-execute-prescription-by-its-id"
            <c:choose>
                <c:when test="${pageScope.activeSubMenuStaffTab == 'executePrescriptionByItsId'}">
                    class="list-group-item list-group-item-action active"
                </c:when>
                <c:otherwise>
                    class="list-group-item list-group-item-action"
                </c:otherwise>
            </c:choose>
    >
        <fmt:message bundle="${jspMessages}"
                     key="admissionDoctorSubMenu.executePrescriptionByItsId"/>
    </a>
    <a type="button"
       href="${pageContext.request.contextPath}/profile?command=go-to-refusals-of-hospitalization"
            <c:choose>
                <c:when test="${pageScope.activeSubMenuStaffTab == 'refuseToHospitalize'}">
                    class="list-group-item list-group-item-action active"
                </c:when>
                <c:otherwise>
                    class="list-group-item list-group-item-action"
                </c:otherwise>
            </c:choose>
    >
        <fmt:message bundle="${jspMessages}"
                     key="staff.refuseToHospitalize"/>
    </a>
    <a type="button"
       href="${pageContext.request.contextPath}/profile?command=go-to-all-refusal-references"
            <c:choose>
                <c:when test="${pageScope.activeSubMenuStaffTab == 'allRefusalReferences'}">
                    class="list-group-item list-group-item-action active"
                </c:when>
                <c:otherwise>
                    class="list-group-item list-group-item-action"
                </c:otherwise>
            </c:choose>
    >
        <fmt:message bundle="${jspMessages}"
                     key="staff.allRefusalReferences"/>
    </a>
</div>
