<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="text-body" style="padding-left: 25px; padding-right: 25px">
    Уважаемые посетители, Вашему вниманию представляется информация о текущей
    заполняемости больницы. Напоминаем, что в нашей больнице бесплатно
    реализуется "Программа инновационного лечения".
</div>

<br>

<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">Наименование</th>
        <th scope="col">Свободно мест</th>
        <th scope="col">Всего мест</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="hospitalReportPart"
               items="${requestScope.hospitalReport.contents}">
        <tr>
            <td><c:out value="${hospitalReportPart.deptName}"/></td>
            <td><c:out value="${hospitalReportPart.vacantPlacesNumber}"/></td>
            <td><c:out value="${hospitalReportPart.totalPlacesNumber}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>