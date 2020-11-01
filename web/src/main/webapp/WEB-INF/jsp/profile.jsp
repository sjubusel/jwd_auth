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
<%--<c:set var="activeSubMenuProfileTab" value="viewProfileInfo" scope="page"/>--%>

<fmt:setBundle basename="jspResources" var="jspMessages"/>

<html>
<head>
    <title>
        <fmt:message bundle="${jspMessages}"
                     key="profileSubMenu.patientInfo"/>
        <c:out value=" | "/>
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
        <%@ include file="structural_element/profileSubMenu.jsp" %>
        <div class="bg-light d-inline-block col">
<%--            <div class="">--%>
<%--                SUCCESS-BLOCK <br> БЛОК УСПЕХА--%>
<%--            </div>--%>

            <table class="table">
                <tbody>
                <%--1--%>
                <tr class="row">
                    <td class="col-3 d-flex align-items-center">
                        Фотография
                    </td>
                    <td class="col d-flex align-items-center">
                        <img src="${pageContext.request.contextPath}/img/user.png"
                             class="rounded " style="height: 200px; width:auto"
                             alt="Фотография пользователя">
                    </td>
                </tr>
                <%--2--%>
                <tr class="row">
                    <td class="col-3 d-flex align-items-center">
                        Имя
                    </td>
                    <td class="col d-flex align-items-center">
                        Value
                    </td>
                </tr>
                <%--3--%>
                <tr class="row">
                    <td class="col-3 d-flex align-items-center">
                        Отчество
                    </td>
                    <td class="col d-flex align-items-center">
                        Value
                    </td>
                </tr>
                <%--4--%>
                <tr class="row">
                    <td class="col-3 d-flex align-items-center">
                        Фамилия
                    </td>
                    <td class="col d-flex align-items-center">
                        Value
                    </td>
                </tr>
                <%--5--%>
                <tr class="row">
                    <td class="col-3 d-flex align-items-center">
                        Дата рождения
                    </td>
                    <td class="col d-flex align-items-center">
                        Value
                    </td>
                </tr>
                <%--6--%>
                <tr class="row">
                    <td class="col-3 d-flex align-items-center">
                        Пол
                    </td>
                    <td class="col d-flex align-items-center">
                        Value
                    </td>
                </tr>
                <%--7--%>
                <tr class="row">
                    <td class="col-3 d-flex align-items-center">
                        Адрес электронной почты
                    </td>
                    <td class="col d-flex align-items-center">
                        Value
                    </td>
                </tr>
                <%--8--%>
                <tr class="row">
                    <td class="col-3 d-flex align-items-center">
                        Номер телефона
                    </td>
                    <td class="col d-flex align-items-center">
                        Value
                    </td>
                </tr>
                <%--9--%>
                <tr class="row">
                    <td class="col-3 d-flex align-items-center">
                        Семейный статус
                    </td>
                    <td class="col d-flex align-items-center">
                        Value
                    </td>
                </tr>
                <%--10--%>
                <tr class="row">
                    <td class="col-3 d-flex align-items-center">
                        Удостоверяющий личность документ
                    </td>
                    <td class="col d-flex align-items-center">
                        Value
                    </td>
                </tr>
                <%--11--%>
                <tr class="row">
                    <td class="col-3 d-flex align-items-center">
                        Домашний телефон
                    </td>
                    <td class="col d-flex align-items-center">
                        Value
                    </td>
                </tr>
                <%--12--%>
                <tr class="row">
                    <td class="col-3 d-flex align-items-center">
                        Контактное лицо в случае крайней необходимости
                    </td>
                    <td class="col d-flex align-items-center">
                        Value
                    </td>
                </tr>
                <%--13--%>
                <tr class="row">
                    <td class="col-3 d-flex align-items-center">
                        Номер телефона контактного лица в случае крайней
                        необходимости
                    </td>
                    <td class="col d-flex align-items-center">
                        Value
                    </td>
                </tr>
                <%--14--%>
                <tr class="row">
                    <td class="col-3 d-flex align-items-center">
                        Группа крови
                    </td>
                    <td class="col d-flex align-items-center">
                        Value
                    </td>
                </tr>
                <%--15--%>
                <tr class="row">
                    <td class="col-3 d-flex align-items-center">
                        Резус-фактор (Rh)
                    </td>
                    <td class="col d-flex align-items-center">
                        Value
                    </td>
                </tr>
                <%--16--%>
                <tr class="row">
                    <td class="col-3 d-flex align-items-center">
                        Группа инвалидности
                    </td>
                    <td class="col d-flex align-items-center">
                        Value
                    </td>
                </tr>
                <%--17--%>
                <tr class="row">
                    <td class="col-3 d-flex align-items-center">
                        Статус транспортировки
                    </td>
                    <td class="col d-flex align-items-center">
                        Value
                    </td>
                </tr>
                <%--18--%>
                <tr class="row">
                    <td class="col-3 d-flex align-items-center">
                        Наличие аллергических реакций
                    </td>
                    <td class="col d-flex align-items-center">
                        Value
                    </td>
                </tr>
                <%--19--%>
                <tr class="row">
                    <td class="col-3 d-flex align-items-center">
                        Наличие особо опасных болезней
                    </td>
                    <td class="col d-flex align-items-center">
                        Value
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

<jsp:include page="structural_element/footer.jsp"/>

</body>
</html>
