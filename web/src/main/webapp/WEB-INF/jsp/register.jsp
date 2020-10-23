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
          style="padding-left: 25px; padding-right: 25px">
        <input type="hidden" name="command" value="register"/>
        <div class="form-group form-inline col-9">
            <label for="loginInput" class="col-4 custom-form-label">
                <fmt:message bundle="${jspMessages}"
                             key="register.loginInputLabel"/>
            </label>
            <input type="text" class="form-control col-5" id="loginInput"
                   name="login"
                   placeholder="">
        </div>
        <div class="form-group form-inline col-9">
            <label for="passwordInput" class="col-4 custom-form-label">
                <fmt:message bundle="${jspMessages}"
                             key="register.passwordInputLabel"/>
            </label>
            <input type="password" class="form-control col-5" id="passwordInput"
                   name="password">
        </div>
        <div class="form-group form-inline col-9">
            <label for="emailInput" class="col-4 custom-form-label">
                <c:out value="Адрес электронной почты"/>
            </label>
            <input type="email" class="form-control col-5" id="emailInput"
                   name="email">
        </div>
        <div class="form-group form-inline col-9">
            <label for="firstNameInput" class="col-4 custom-form-label">
                <c:out value="Имя"/>
            </label>
            <input type="text" class="form-control col-5" id="firstNameInput"
                   name="firstName">
        </div>
        <div class="form-group form-inline col-9">
            <label for="middleNameInput" class="col-4 custom-form-label">
                <c:out value="Отчество"/>
            </label>
            <input type="text" class="form-control col-5" id="middleNameInput"
                   name="middleName">
        </div>
        <div class="form-group form-inline col-9">
            <label for="lastNameInput" class="col-4 custom-form-label">
                <c:out value="Фамилия"/>
            </label>
            <input type="text" class="form-control col-5" id="lastNameInput"
                   name="lastName">
        </div>
        <div class="form-group form-inline col-9">
            <label for="birthdayInput" class="col-4 custom-form-label">
                <c:out value="День рождения"/>
            </label>
            <input type="date" class="form-control col-5" id="birthdayInput"
                   name="birthday">
        </div>
        <div class="form-group form-inline col-9">
            <label class="col-4 custom-form-label">
                <c:out value="Пол"/>
            </label>
            <div class="form-control col-5 form-check form-check-inline justify-content-start">
                <input type="radio" class="form-check-input" id="maleRadioInput"
                       name="gender" value="male">
                <label for="maleRadioInput" class="form-check-label"
                       style="margin-right: 10px">
                    <c:out value="мужской"/>
                    <%--                <fmt:message bundle="${jspMessages}"--%>
                    <%--                             key="register.gender.male"/>--%>
                </label>
                <input type="radio" class="form-check-input"
                       id="femaleRadioInput"
                       name="gender" value="female">
                <label for="femaleRadioInput" class="form-check-label"
                       style="margin-right: 10px">
                    <c:out value="женский"/>
                    <%--                <fmt:message bundle="${jspMessages}"--%>
                    <%--                             key="register.gender.remale"/>--%>
                </label>
                <input type="radio" class="form-check-input"
                       id="otherRadioInput"
                       name="gender" value="other">
                <label for="otherRadioInput" class="form-check-label"
                       style="margin-right: 10px">
                    <c:out value="другой"/>
                    <%--                <fmt:message bundle="${jspMessages}"--%>
                    <%--                             key="register.gender.other"/>--%>
                </label>
            </div>
        </div>

        <br>

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
