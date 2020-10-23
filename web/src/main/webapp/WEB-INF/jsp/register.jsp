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

<c:set var="activeMenuTab" value="signUp" scope="page"/>

<fmt:setBundle basename="jspResources" var="jspMessages"/>

<html>
<head>
    <title>
        <fmt:message bundle="${jspMessages}" key="register.htmlTitle"/>
        <c:out value=" | "/>
        <fmt:message bundle="${jspMessages}" key="all.htmlTitle"/>
    </title>
    <jsp:include page="structural_element/metahead.jsp"/>
</head>
<body>

<div class="main-content">
    <%@ include file="structural_element/header.jsp" %>

    <h2 style="padding-left: 25px">
        <fmt:message bundle="${jspMessages}" key="register.heading"/>
    </h2>

    <form action="${pageContext.request.contextPath}/main" method="post"
          style="padding-left: 25px">
        <input type="hidden" name="command" value="register"/>
        <div class="form-group">
            <label for="loginInput">
                <fmt:message bundle="${jspMessages}"
                             key="register.loginInputLabel"/>
            </label>
            <input type="text" class="form-control" id="loginInput" name="login"
                   placeholder="">
        </div>
        <div class="form-group">
            <label for="passwordInput">
                <fmt:message bundle="${jspMessages}"
                             key="register.passwordInputLabel"/>
            </label>
            <input type="password" class="form-control" id="passwordInput"
                   name="password">
        </div>
        <div class="form-group">
            <label for="emailInput">
                <c:out value="Адрес электронной почты"/>
            </label>
            <input type="email" class="form-control" id="emailInput"
                   name="email">
        </div>
        <div class="form-group">
            <label for="firstNameInput">
                <c:out value="Имя"/>
            </label>
            <input type="text" class="form-control" id="firstNameInput"
                   name="firstName">
        </div>
        <div class="form-group">
            <label for="middleNameInput">
                <c:out value="Отчество"/>
            </label>
            <input type="text" class="form-control" id="middleNameInput"
                   name="middleName">
        </div>
        <div class="form-group">
            <label for="lastNameInput">
                <c:out value="Фамилия"/>
            </label>
            <input type="text" class="form-control" id="lastNameInput"
                   name="lastName">
        </div>
        <div class="form-group">
            <label for="birthdayInput">
                <c:out value="День рождения"/>
            </label>
            <input type="date" class="form-control" id="birthdayInput"
                   name="birthday">
        </div>
        <button type="submit" class="btn btn-primary">
            <fmt:message bundle="${jspMessages}" key="register.submitButton"/>
        </button>

    </form>

    <br>

    <c:if test="${requestScope.error ne null}">
        <div class="alert alert-danger" role="alert">
            <c:choose>
                <c:when test="${requestScope.error eq 'auth-data'}">
                    <fmt:message bundle="${jspMessages}"
                                 key="register.authDataError"/>
                </c:when>
                <c:when test="${requestScope.error eq 'login'}">
                    <fmt:message bundle="${jspMessages}"
                                 key="register.loginError"/>
                </c:when>
                <c:when test="${requestScope.error eq 'pass'}">
                    <fmt:message bundle="${jspMessages}"
                                 key="register.passwordError"/>
                </c:when>
                <c:when test="${requestScope.error eq 'duplicate'}">
                    <fmt:message bundle="${jspMessages}"
                                 key="register.duplicateError"/>
                </c:when>
                <c:otherwise>
                    <fmt:message bundle="${jspMessages}"
                                 key="register.techError"/>
                </c:otherwise>
            </c:choose>
        </div>
    </c:if>
</div>

<jsp:include page="structural_element/footer.jsp"/>

</body>
</html>
