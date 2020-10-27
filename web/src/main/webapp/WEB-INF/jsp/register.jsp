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
<fmt:setBundle basename="registrationRegExp" var="regEx"/>

<html>
<head>
    <title>
        <fmt:message bundle="${jspMessages}" key="register.htmlTitle"/>
        <c:out value=" | "/>
        <fmt:message bundle="${jspMessages}" key="all.htmlTitle"/>
    </title>
    <%@include file="structural_element/metahead.jsp" %>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/jquery-3.5.1.js">
    </script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/jquery.maskedinput.js">
    </script>
    <script>
        jQuery(document).ready(function () {
            $("#phoneNumberCountryCodeInput").mask("+9?99");
            $("#phoneNumberInnerCodeInput").mask("9?99");
            $("#phoneNumberInnerNumberInput").mask("999-99-99");
        });
    </script>
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
                   name="login" required
                   placeholder="<fmt:message bundle="${jspMessages}"
                   key="register.loginInput.placeholder"/>"
                   pattern="<fmt:message bundle="${regEx}" key="login"/>">
            <div id="loginIndent" class="col-4"></div>
            <small id="loginDescription" class="form-text text-muted col-5">
                <fmt:message bundle="${jspMessages}"
                             key="register.loginDescription"/>
            </small>
        </div>
        <div class="form-group form-inline col-9">
            <label for="passwordInput" class="col-4 custom-form-label">
                <fmt:message bundle="${jspMessages}"
                             key="register.passwordInputLabel"/>
            </label>
            <input type="password" class="form-control col-5"
                   id="passwordInput" name="password" required
                   placeholder="<fmt:message bundle="${jspMessages}"
                   key="register.passwordInput.placeholder"/>"
                   pattern="<fmt:message bundle="${regEx}" key="password"/>">
            <div id="passwordIndent" class="col-4"></div>
            <small id="passwordDescription" class="form-text text-muted col-5">
                <fmt:message bundle="${jspMessages}"
                             key="register.passwordDescription"/>
            </small>
        </div>
        <div class="form-group form-inline col-9">
            <label for="emailInput" class="col-4 custom-form-label">
                <fmt:message bundle="${jspMessages}"
                             key="register.emailInputLabel"/>
            </label>
            <input type="email" class="form-control col-5" id="emailInput"
                   name="email" required placeholder="xxxxx@xxxxx.xxx"
                   pattern="<fmt:message bundle="${regEx}" key="email"/>">
            <div id="emailIndent" class="col-4"></div>
            <small id="emailDescription" class="form-text text-muted col-5">
                <fmt:message bundle="${jspMessages}"
                             key="register.emailDescription"/>
            </small>
        </div>
        <div class="form-group form-inline col-9">
            <label for="phoneNumberCountryCodeInput"
                   for="phoneNumberInnerCodeInput"
                   for="phoneNumberInnerNumberInput"
                   class="col-4 custom-form-label">
                <fmt:message bundle="${jspMessages}"
                             key="register.phoneNumberInputLabel"/>
            </label>
            <div class="row col-5 flex-end">
                <input type="text" class="form-control col col-2"
                       id="phoneNumberCountryCodeInput"
                       name="phoneNumberCountryCode" required
                       placeholder="+XXX">
                <input type="text" class="form-control col col-2"
                       id="phoneNumberInnerCodeInput"
                       name="phoneNumberInnerCode" required
                       placeholder="(XX)">
                <input type="text" class="form-control col col-8"
                       id="phoneNumberInnerNumberInput"
                       name="phoneNumberInnerNumber" required
                       placeholder="XXX-XX-XX">
            </div>
        </div>
        <div class="form-group form-inline col-9">
            <label for="firstNameInput" class="col-4 custom-form-label">
                <fmt:message bundle="${jspMessages}"
                             key="register.firstNameInputLabel"/>
            </label>
            <input type="text" class="form-control col-5"
                   id="firstNameInput" name="firstName" required
                   placeholder="<fmt:message bundle="${jspMessages}"
                   key="register.firstNameInput.placeholder"/>"
                   pattern="<fmt:message bundle="${regEx}" key="anyName"/>">
            <div id="firstNameIndent" class="col-4"></div>
            <small id="firstNameDescription" class="form-text text-muted col-5">
                <fmt:message bundle="${jspMessages}"
                             key="register.anyNameDescription"/>
            </small>
        </div>
        <div class="form-group form-inline col-9">
            <label for="middleNameInput" class="col-4 custom-form-label">
                <fmt:message bundle="${jspMessages}"
                             key="register.middleNameInputLabel"/>
            </label>
            <input type="text" class="form-control col-5"
                   id="middleNameInput" name="middleName"
                   placeholder="<fmt:message bundle="${jspMessages}"
                   key="register.middleNameInput.placeholder"/>"
                   pattern="<fmt:message bundle="${regEx}" key="anyName"/>">
            <div id="middleNameIndent" class="col-4"></div>
            <small id="middleNameDescription"
                   class="form-text text-muted col-5">
                <fmt:message bundle="${jspMessages}"
                             key="register.anyNameDescription"/>
            </small>
        </div>
        <div class="form-group form-inline col-9">
            <label for="lastNameInput" class="col-4 custom-form-label">
                <fmt:message bundle="${jspMessages}"
                             key="register.lastNameInputLabel"/>
            </label>
            <input type="text" class="form-control col-5" id="lastNameInput"
                   name="lastName" required
                   placeholder="<fmt:message bundle="${jspMessages}"
                   key="register.lastNameInput.placeholder"/>"
                   pattern="<fmt:message bundle="${regEx}" key="anyName"/>">
            <div id="lastNameIndent" class="col-4"></div>
            <small id="lastNameDescription" class="form-text text-muted col-5">
                <fmt:message bundle="${jspMessages}"
                             key="register.anyNameDescription"/>
            </small>
        </div>
        <div class="form-group form-inline col-9">
            <label for="birthdayInput" class="col-4 custom-form-label">
                <fmt:message bundle="${jspMessages}"
                             key="register.birthdayInputLabel"/>
            </label>
            <input type="date" class="form-control col-5" id="birthdayInput"
                   name="birthday" required
                   placeholder="<fmt:message bundle="${jspMessages}"
                   key="register.birthdayInput.placeholder"/>"
                   alt="Введите Вашу дату рождения">
        </div>
        <div class="form-group form-inline col-9">
            <label class="col-4 custom-form-label">
                <fmt:message bundle="${jspMessages}"
                             key="register.GenderInputLabel"/>
            </label>
            <div class="form-control col-5 form-check form-check-inline">
                <input type="radio" class="form-check-input"
                       id="maleRadioInput"
                       name="gender" value="male" checked>
                <label for="maleRadioInput" class="form-check-label"
                       style="margin-right: 10px">
                    <fmt:message bundle="${jspMessages}"
                                 key="register.gender.male"/>
                </label>
                <input type="radio" class="form-check-input"
                       id="femaleRadioInput"
                       name="gender" value="female">
                <label for="femaleRadioInput" class="form-check-label"
                       style="margin-right: 10px">
                    <fmt:message bundle="${jspMessages}"
                                 key="register.gender.female"/>
                </label>
                <input type="radio" class="form-check-input"
                       id="otherRadioInput"
                       name="gender" value="other">
                <label for="otherRadioInput" class="form-check-label"
                       style="margin-right: 10px">
                    <fmt:message bundle="${jspMessages}"
                                 key="register.gender.other"/>
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
