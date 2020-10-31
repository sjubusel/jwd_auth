<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="jspResources" var="jspMessages"/>

<div class="list-group d-inline-block col-3">
    <button type="button"
            class="list-group-item list-group-item-action active">
        <%--        profileSubMenu.patientInfo--%>
        <c:out value="Информация о пользователе"/>
    </button>
    <button type="button"
            class="list-group-item list-group-item-action">
        <%--        profileSubMenu.patientIntoToBeChanged--%>
        <c:out value="Изменение информации о пользователе"/>
    </button>
    <button type="button"
            class="list-group-item list-group-item-action">
        <%--        profileSubMenu.passwordToBeChanged--%>
        <c:out value="Изменение пароля"/>
    </button>
    <button type="button"
            class="list-group-item list-group-item-action">
        <%--        profileSubMenu.emailToBeChanged--%>
        <c:out value="Изменение электронной почты"/>
    </button>
    <button type="button"
            class="list-group-item list-group-item-action mb-4">
        <%--        profileSubMenu.photoToBeChanged--%>
        <c:out value="Изменение фотографии пользователя"/>
    </button>

    <button type="button"
            class="list-group-item list-group-item-action border-top">
        <%--        profileSubMenu.staffInfo--%>
        <c:out value="Информация о медицинком работнике"/>
    </button>
    <button type="button"
            class="list-group-item list-group-item-action">
        <%--        profileSubMenu.staffInfoToBeChanged--%>
        <c:out value="Изменение информации о медицинком работнике"/>
    </button>
    <button type="button"
            class="list-group-item list-group-item-action">
        <%--        profileSubMenu.staffHistory--%>
        <c:out value="История информации о медицинком работнике"/>
    </button>
    <button type="button"
            class="list-group-item list-group-item-action">
        <%--        profileSubMenu.staffPhotoToBeChanged--%>
        <c:out value="Изменение фотографии медицинкого работника"/>
    </button>
</div>