<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
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

<fmt:setBundle basename="jspResourses" var="jspMessages"/>

<div class="text-body" style="padding-left: 25px; padding-right: 25px">
    <fmt:message bundle="${jspMessages}" key="mainContents.mainMessage"/>
</div>

<br>

<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">
            <fmt:message bundle="${jspMessages}"
                         key="mainContents.tableHeadDescription"/>
        </th>
        <th scope="col">
            <fmt:message bundle="${jspMessages}"
                         key="mainContents.tableHeadVacantQuantity"/>
        </th>
        <th scope="col">
            <fmt:message bundle="${jspMessages}"
                         key="mainContents.tableHeadTotalQuantity"/>
        </th>
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