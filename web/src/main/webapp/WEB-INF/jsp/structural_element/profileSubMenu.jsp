<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="jspResources" var="jspMessages"/>

<div class="list-group d-inline-block col-3">
    <button type="button"
            class="list-group-item list-group-item-action active">
        <fmt:message bundle="${jspMessages}" key="profileSubMenu.patientInfo"/>
    </button>
    <button type="button"
            class="list-group-item list-group-item-action">
        <fmt:message bundle="${jspMessages}"
                     key="profileSubMenu.patientIntoToBeChanged"/>
    </button>
    <button type="button"
            class="list-group-item list-group-item-action">
        <fmt:message bundle="${jspMessages}"
                     key="profileSubMenu.passwordToBeChanged"/>
    </button>
    <button type="button"
            class="list-group-item list-group-item-action">
        <fmt:message bundle="${jspMessages}"
                     key="profileSubMenu.emailToBeChanged"/>
    </button>
    <button type="button"
            class="list-group-item list-group-item-action mb-4">
        <fmt:message bundle="${jspMessages}"
                     key="profileSubMenu.photoToBeChanged"/>
    </button>

    <button type="button"
            class="list-group-item list-group-item-action border-top">
        <fmt:message bundle="${jspMessages}" key="profileSubMenu.staffInfo"/>
    </button>
    <button type="button"
            class="list-group-item list-group-item-action">
        <fmt:message bundle="${jspMessages}"
                     key="profileSubMenu.staffInfoToBeChanged"/>
    </button>
    <button type="button"
            class="list-group-item list-group-item-action">
        <fmt:message bundle="${jspMessages}" key="profileSubMenu.staffHistory"/>
    </button>
    <button type="button"
            class="list-group-item list-group-item-action">
        <fmt:message bundle="${jspMessages}"
                     key="profileSubMenu.staffPhotoToBeChanged"/>
    </button>
</div>