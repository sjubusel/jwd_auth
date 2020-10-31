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

<c:set var="activeMenuTab" value="profile" scope="page"/>
<c:set var="activeSubMenuProfileTab" value="viewProfileInfo" scope="page"/>

<fmt:setBundle basename="jspResources" var="jspMessages"/>

<html>
<head>
    <title>
        <fmt:message bundle="${jspMessages}" key="profile.htmlTitle"/>
        <c:out value=" | "/>
        <fmt:message bundle="${jspMessages}" key="all.htmlTitle"/>
    </title>
    <jsp:include page="structural_element/metahead.jsp"/>
</head>
<body>

<div class="main-content">
    <%@ include file="structural_element/header.jsp" %>

    <div class="row mt-2 mr-2 ml-2">
        <div class="list-group d-inline-block col-3">
            <button type="button"
                    class="list-group-item list-group-item-action active">
                <c:out value="Информация о пользователе"/>
            </button>
            <button type="button"
                    class="list-group-item list-group-item-action">
                <c:out value="Изменение информации о пользователе"/>
            </button>
            <button type="button"
                    class="list-group-item list-group-item-action">
                <c:out value="Изменение пароля"/>
            </button>
            <button type="button"
                    class="list-group-item list-group-item-action">
                <c:out value="Изменение электронной почты"/>
            </button>
            <button type="button"
                    class="list-group-item list-group-item-action mb-4">
                <c:out value="Изменение фотографии пользователя"/>
            </button>

            <button type="button"
                    class="list-group-item list-group-item-action border-top">
                <c:out value="Информация о медицинком работнике"/>
            </button>
            <button type="button"
                    class="list-group-item list-group-item-action">
                <c:out value="История информации о медицинком работнике"/>
            </button>
            <button type="button"
                    class="list-group-item list-group-item-action">
                <c:out value="Изменение информации о медицинком работнике"/>
            </button>
            <button type="button"
                    class="list-group-item list-group-item-action">
                <c:out value="Изменение фотографии медицинкого работника"/>
            </button>
        </div>

        <div class="bg-light d-inline-block col">
            <div class="">SUCCESS-BLOCK</div>
        </div>
    </div>
</div>

<jsp:include page="structural_element/footer.jsp"/>

</body>
</html>
