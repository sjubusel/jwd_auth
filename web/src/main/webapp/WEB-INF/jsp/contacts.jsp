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

<c:set var="activeMenuTab" value="contacts" scope="page"/>

<fmt:setBundle basename="jspResources" var="jspMessages"/>

<html>
<head>
    <title>
        <fmt:message bundle="${jspMessages}" key="contacts.htmlTitle"/>
        <c:out value=" | "/>
        <fmt:message bundle="${jspMessages}" key="all.htmlTitle"/>
    </title>
    <jsp:include page="structural_element/metahead.jsp"/>
</head>
<body>

<div class="main-content">
    <%@ include file="structural_element/header.jsp" %>

    <h2 class="ml-2 text-center" style="padding-left: 25px">
        <fmt:message bundle="${jspMessages}" key="contacts.heading"/>
    </h2>
    <div class="ml-2 text-center">
        <fmt:message bundle="${jspMessages}" key="contacts.contacts"/>
    </div>
    <div class="ml-2 text-center">
        +375 (17) 212 76 21
    </div>
    <div class="ml-2 text-center">
        <fmt:message bundle="${jspMessages}" key="contacts.hotLine"/>
    </div>
    <div class="ml-2 text-center" >
        +375 (17) 287 00 01
    </div>
    <div class="ml-2 text-center">Email: <a href="mailto:info@inno-hosp.by">info@inno-hosp.by</a>
    </div>
    <div class="ml-2 text-center">
        <fmt:message bundle="${jspMessages}" key="contacts.address"/>
        <c:out value=": "/>
        <fmt:message bundle="${jspMessages}" key="contacts.addressValue"/>
    </div>

    <div class="ml-2 d-flex justify-content-center">
        <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d18814.59546233252!2d27.714514657882944!3d53.881549126041975!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x46dbcdac207b3c3f%3A0x29f9585376ca20bf!2z0KHRhtGW0LrQu9C10LLQsCwgdnVsaWNhIE1hc2tvxa1za2FqYQ!5e0!3m2!1sen!2sby!4v1605400605994!5m2!1sen!2sby"
                width="600" height="450" frameborder="0" style="border:0;"
                allowfullscreen="" aria-hidden="false" tabindex="0"></iframe>
    </div>
</div>

<jsp:include page="structural_element/footer.jsp"/>
</body>
</html>
