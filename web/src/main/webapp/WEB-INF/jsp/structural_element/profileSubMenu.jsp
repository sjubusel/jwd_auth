<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="jspResources" var="jspMessages"/>

<div class="list-group d-inline-block col-3">
    <button type="button"
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
    </button>
    <button type="button"
            <c:choose>
                <c:when test="${pageScope.activeSubMenuProfileTab == ''}">
                    class="list-group-item list-group-item-action active"
                </c:when>
                <c:otherwise>
                    class="list-group-item list-group-item-action"
                </c:otherwise>
            </c:choose>
    >
        <fmt:message bundle="${jspMessages}"
                     key="profileSubMenu.patientInfoToBeChanged"/>
    </button>
    <button type="button"
            <c:choose>
                <c:when test="${pageScope.activeSubMenuProfileTab == ''}">
                    class="list-group-item list-group-item-action active"
                </c:when>
                <c:otherwise>
                    class="list-group-item list-group-item-action"
                </c:otherwise>
            </c:choose>
    >
        <fmt:message bundle="${jspMessages}"
                     key="profileSubMenu.passwordToBeChanged"/>
    </button>
    <button type="button"
            <c:choose>
                <c:when test="${pageScope.activeSubMenuProfileTab == ''}">
                    class="list-group-item list-group-item-action active"
                </c:when>
                <c:otherwise>
                    class="list-group-item list-group-item-action"
                </c:otherwise>
            </c:choose>
    >
        <fmt:message bundle="${jspMessages}"
                     key="profileSubMenu.emailToBeChanged"/>
    </button>
    <button type="button"
            <c:choose>
                <c:when test="${pageScope.activeSubMenuProfileTab == ''}">
                    class="list-group-item list-group-item-action active"
                </c:when>
                <c:otherwise>
                    class="list-group-item list-group-item-action"
                </c:otherwise>
            </c:choose>
    >
        <fmt:message bundle="${jspMessages}"
                     key="profileSubMenu.photoToBeChanged"/>
    </button>
    <button type="button"
            <c:choose>
                <c:when test="${pageScope.activeSubMenuProfileTab == ''}">
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
                <c:when test="${pageScope.activeSubMenuProfileTab == ''}">
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
                <c:when test="${pageScope.activeSubMenuProfileTab == ''}">
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
                <c:when test="${pageScope.activeSubMenuProfileTab == ''}">
                    class="list-group-item list-group-item-action active"
                </c:when>
                <c:otherwise>
                    class="list-group-item list-group-item-action"
                </c:otherwise>
            </c:choose>
    >
        <fmt:message bundle="${jspMessages}"
                     key="profileSubMenu.staffInfoToBeChanged"/>
    </button>
    <button type="button"
            <c:choose>
                <c:when test="${pageScope.activeSubMenuProfileTab == ''}">
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
                <c:when test="${pageScope.activeSubMenuProfileTab == ''}">
                    class="list-group-item list-group-item-action active"
                </c:when>
                <c:otherwise>
                    class="list-group-item list-group-item-action"
                </c:otherwise>
            </c:choose>
    >
        <fmt:message bundle="${jspMessages}"
                     key="profileSubMenu.staffPhotoToBeChanged"/>
    </button>
</div>