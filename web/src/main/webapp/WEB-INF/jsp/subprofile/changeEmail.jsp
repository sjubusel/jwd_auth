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
<c:set var="activeSubMenuProfileTab" value="changeEmail" scope="page"/>

<fmt:setBundle basename="jspResources" var="jspMessages"/>
<fmt:setBundle basename="registrationRegExp" var="regEx"/>

<html>
<head>
    <title>
        <fmt:message bundle="${jspMessages}"
                     key="profileSubMenu.changeEmail"/>
        <c:out value=" | "/>
        <fmt:message bundle="${jspMessages}" key="profile.htmlTitle"/>
        <c:out value=" | "/>
        <fmt:message bundle="${jspMessages}" key="all.htmlTitle"/>
    </title>
    <jsp:include page="../structural_element/metahead.jsp"/>
</head>
<body>

<div class="main-content">
    <%@ include file="../structural_element/header.jsp" %>

    <div class="row mt-2 mr-2 ml-2">
        <%@ include file="../structural_element/profileSubMenu.jsp" %>
        <div class="bg-light d-inline-block col">
            <h1 class="text-left">
                <fmt:message bundle="${jspMessages}"
                             key="profileSubMenu.changeEmail.heading"/>
            </h1>

            <div class="text-muted">
                <fmt:message bundle="${jspMessages}"
                             key="profileSubMenu.changeEmail.warning"/>
            </div>

            <form action="${pageContext.request.contextPath}/main"
                  method="post">
                <input type="hidden" name="command" value="profile-email-change"/>

                <div class="form-group form-inline row">
                    <label for="emailInput" class="col-4 custom-form-label">
                        <fmt:message bundle="${jspMessages}"
                                     key="register.emailInputLabel"/>
                    </label>
                    <input type="email" class="form-control col" id="emailInput"
                           name="email" required placeholder="xxxxx@xxxxx.xxx"
                           pattern="<fmt:message bundle="${regEx}" key="email"/>">
                    <div id="emailIndent" class="col-4"></div>
                    <small id="emailDescription"
                           class="form-text text-muted col">
                        <fmt:message bundle="${jspMessages}"
                                     key="register.emailDescription"/>
                    </small>
                </div>

                <div class="form-group form-inline row">
                    <label for="passwordInput" class="col-4 custom-form-label">
                        <fmt:message bundle="${jspMessages}"
                                     key="register.passwordInputLabel"/>
                    </label>
                    <input type="password" class="form-control col"
                           id="passwordInput" name="password" required
                           placeholder="<fmt:message bundle="${jspMessages}"
                   key="register.passwordInput.placeholder"/>"
                           pattern="<fmt:message bundle="${regEx}" key="password"/>">
                </div>
            </form>

        </div>
    </div>
</div>

<jsp:include page="../structural_element/footer.jsp"/>

</body>
</html>
