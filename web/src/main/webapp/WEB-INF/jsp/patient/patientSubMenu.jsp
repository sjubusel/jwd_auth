<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="jspResources" var="jspMessages"/>

<div class="list-group d-inline-block col-3">
    <a type="button"
       href="${pageContext.request.contextPath}/profile?command=go-to-patient-new-medicine-prescriptions"
            <c:choose>
                <c:when test="${pageScope.activeSubMenuTab == 'patientNewMedicinePrescriptions'}">
                    class="list-group-item list-group-item-action active"
                </c:when>
                <c:otherwise>
                    class="list-group-item list-group-item-action"
                </c:otherwise>
            </c:choose>
    >
        <fmt:message bundle="${jspMessages}"
                     key="patientSubMenu.newMedicinePrescriptions"/>
    </a>
    <a type="button"
       href="${pageContext.request.contextPath}/profile?command=go-to-patient-new-prescriptions"
            <c:choose>
                <c:when test="${pageScope.activeSubMenuTab == 'patientNewNonMedicinePrescriptions'}">
                    class="list-group-item list-group-item-action active"
                </c:when>
                <c:otherwise>
                    class="list-group-item list-group-item-action"
                </c:otherwise>
            </c:choose>
    >
        <fmt:message bundle="${jspMessages}"
                     key="patientSubMenu.newNonMedicinePrescriptions"/>
    </a>
    <a type="button"
       href="${pageContext.request.contextPath}/profile?command=go-to-refusal-references-for-patient"
            <c:choose>
                <c:when test="${pageScope.activeSubMenuTab == 'refusalReferencesForPatient'}">
                    class="list-group-item list-group-item-action active"
                </c:when>
                <c:otherwise>
                    class="list-group-item list-group-item-action"
                </c:otherwise>
            </c:choose>
    >
        <fmt:message bundle="${jspMessages}"
                     key="patientSubMenu.refusalReferencesForPatient"/>
    </a>
</div>