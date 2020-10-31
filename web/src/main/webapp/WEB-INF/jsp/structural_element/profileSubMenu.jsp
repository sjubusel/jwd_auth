<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="jspResources" var="jspMessages"/>

<div class="list-group d-inline-block col-3">
    <a type="button"
       href="${pageContext.request.contextPath}/profile?command=go-to-profile"
    <%--                <c:choose>--%>
    <%--                    <c:when test="${pageScope.activeSubMenuProfileTab == ''}"></c:when>--%>
    <%--                    <c:otherwise></c:otherwise>--%>
    <%--                </c:choose>--%>
            <c:choose>
                <c:when test="${pageScope.activeSubMenuProfileTab eq null}">
                    class="list-group-item list-group-item-action active"
                </c:when>
                <c:otherwise>
                    class="list-group-item list-group-item-action"
                </c:otherwise>
            </c:choose>
    >
        <fmt:message bundle="${jspMessages}" key="profileSubMenu.patientInfo"/>
    </a>
    <a type="button"
       href="${pageContext.request.contextPath}/profile?command=go-to-profile-change-patient-info"
            <c:choose>
                <c:when test="${pageScope.activeSubMenuProfileTab == 'changePatientInfo'}">
                    class="list-group-item list-group-item-action active"
                </c:when>
                <c:otherwise>
                    class="list-group-item list-group-item-action"
                </c:otherwise>
            </c:choose>
    >
        <fmt:message bundle="${jspMessages}"
                     key="profileSubMenu.changePatientInfo"/>
    </button>
    <button type="button"
            <c:choose>
                <c:when test="${pageScope.activeSubMenuProfileTab == 'changePassword'}">
                    class="list-group-item list-group-item-action active"
                </c:when>
                <c:otherwise>
                    class="list-group-item list-group-item-action"
                </c:otherwise>
            </c:choose>
    >
        <fmt:message bundle="${jspMessages}"
                     key="profileSubMenu.changePassword"/>
    </button>
    <button type="button"
            <c:choose>
                <c:when test="${pageScope.activeSubMenuProfileTab == 'changeEmail'}">
                    class="list-group-item list-group-item-action active"
                </c:when>
                <c:otherwise>
                    class="list-group-item list-group-item-action"
                </c:otherwise>
            </c:choose>
    >
        <fmt:message bundle="${jspMessages}"
                     key="profileSubMenu.changeEmail"/>
    </button>
    <button type="button"
            <c:choose>
                <c:when test="${pageScope.activeSubMenuProfileTab == 'changePhoto'}">
                    class="list-group-item list-group-item-action active"
                </c:when>
                <c:otherwise>
                    class="list-group-item list-group-item-action"
                </c:otherwise>
            </c:choose>
    >
        <fmt:message bundle="${jspMessages}"
                     key="profileSubMenu.changePhoto"/>
    </button>
    <button type="button"
            <c:choose>
                <c:when test="${pageScope.activeSubMenuProfileTab == 'allergicReactions'}">
                    class="list-group-item list-group-item-action active"
                </c:when>
                <c:otherwise>
                    class="list-group-item list-group-item-action"
                </c:otherwise>
            </c:choose>
    >
        <fmt:message bundle="${jspMessages}"
                     key="profileSubMenu.allergicReactions"/>
    </button>
    <button type="button"
            <c:choose>
                <c:when test="${pageScope.activeSubMenuProfileTab == 'extremelyHazardousDiseases'}">
                    class="list-group-item list-group-item-action mb-4 active"
                </c:when>
                <c:otherwise>
                    class="list-group-item list-group-item-action mb-4"
                </c:otherwise>
            </c:choose>
    >
        <fmt:message bundle="${jspMessages}"
                     key="profileSubMenu.extremelyHazardousDiseases"/>
    </button>


    <button type="button"
            <c:choose>
                <c:when test="${pageScope.activeSubMenuProfileTab == 'staffInfo'}">
                    class="list-group-item list-group-item-action border-top active"
                </c:when>
                <c:otherwise>
                    class="list-group-item list-group-item-action border-top"
                </c:otherwise>
            </c:choose>
    >
        <fmt:message bundle="${jspMessages}" key="profileSubMenu.staffInfo"/>
    </button>
    <button type="button"
            <c:choose>
                <c:when test="${pageScope.activeSubMenuProfileTab == 'changeStaffInfo'}">
                    class="list-group-item list-group-item-action active"
                </c:when>
                <c:otherwise>
                    class="list-group-item list-group-item-action"
                </c:otherwise>
            </c:choose>
    >
        <fmt:message bundle="${jspMessages}"
                     key="profileSubMenu.changeStaffInfo"/>
    </button>
    <button type="button"
            <c:choose>
                <c:when test="${pageScope.activeSubMenuProfileTab == 'staffHistory'}">
                    class="list-group-item list-group-item-action active"
                </c:when>
                <c:otherwise>
                    class="list-group-item list-group-item-action"
                </c:otherwise>
            </c:choose>
    >
        <fmt:message bundle="${jspMessages}" key="profileSubMenu.staffHistory"/>
    </button>
    <button type="button"
            <c:choose>
                <c:when test="${pageScope.activeSubMenuProfileTab == 'changeStaffPhoto'}">
                    class="list-group-item list-group-item-action active"
                </c:when>
                <c:otherwise>
                    class="list-group-item list-group-item-action"
                </c:otherwise>
            </c:choose>
    >
        <fmt:message bundle="${jspMessages}"
                     key="profileSubMenu.changeStaffPhoto"/>
    </button>
</div>