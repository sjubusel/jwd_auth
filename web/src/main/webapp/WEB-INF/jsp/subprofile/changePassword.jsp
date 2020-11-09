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
<c:set var="activeSubMenuProfileTab" value="changePassword"
       scope="page"/>

<fmt:setBundle basename="jspResources" var="jspMessages"/>
<fmt:setBundle basename="registrationRegExp" var="regEx"/>

<html>
<head>
    <title>
        <fmt:message bundle="${jspMessages}"
                     key="profileSubMenu.changePassword"/>
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
                             key="profileSubMenu.changePassword.heading"/>
            </h1>

            <div class="text-muted mb-3">
                <fmt:message bundle="${jspMessages}"
                             key="profileSubMenu.changePassword.warning"/>
            </div>

            <c:if test="${requestScope.changeResult ne null}">
                <c:choose>
                    <c:when test="${requestScope.changeResult eq 'success'}">
                        <div class="alert alert-success"
                             role="alert">
                            <fmt:message bundle="${jspMessages}"
                                         key="profileSubMenu.changePassword.changeResultSuccessful"/>
                        </div>
                    </c:when>
                    <c:when test="${requestScope.changeResult eq 'techError'}">
                        <div class="alert alert-danger"
                             role="alert">
                            <fmt:message bundle="${jspMessages}"
                                         key="profileSubMenu.changePassword.changeResultTechError"/>
                        </div>
                    </c:when>
                    <c:when test="${requestScope.changeResult eq 'validationError'}">
                        <div class="alert alert-danger"
                             role="alert">
                            <fmt:message bundle="${jspMessages}"
                                         key="profileSubMenu.changePassword.changeResultValidationError"/>
                        </div>
                    </c:when>
                    <c:when test="${requestScope.changeResult eq 'duplicateError'}">
                        <div class="alert alert-danger"
                             role="alert">
                            <fmt:message bundle="${jspMessages}"
                                         key="profileSubMenu.changePassword.changeResultDuplicateError"/>
                        </div>
                    </c:when>
                    <c:when test="${requestScope.changeResult eq 'illegalPassWordError'}">
                        <div class="alert alert-danger"
                             role="alert">
                            <fmt:message bundle="${jspMessages}"
                                         key="profileSubMenu.changeEMail.changeResultIllegalPassWordError"/>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="alert alert-danger"
                             role="alert">
                            <fmt:message bundle="${jspMessages}"
                                         key="main.unknownCondition"/>
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:if>

            <form action="${pageContext.request.contextPath}/main"
                  method="post">

                <input type="hidden" name="command"
                       value="profile-change-password"/>

                <div class="form-group form-inline row">
                    <label for="newPasswordInput"
                           class="col-4 custom-form-label">
                        <fmt:message bundle="${jspMessages}"
                                     key="register.passwordInputLabel"/>
                    </label>
                    <input type="password" class="form-control col"
                           id="newPasswordInput" name="newPassword" required
                           placeholder="<fmt:message bundle="${jspMessages}"
                   key="register.passwordInput.placeholder"/>"
                           pattern="<fmt:message bundle="${regEx}" key="password"/>">
                </div>

                <div class="row mb-3">
                    <div id="passwordIndent" class="col-4"></div>
                    <small id="passwordDescription"
                           class="form-text text-muted col">
                        <fmt:message bundle="${jspMessages}"
                                     key="register.passwordDescription"/>
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
                   key="profileSubMenu.changeEmail.passwordPlaceholder"/>"
                           pattern="<fmt:message bundle="${regEx}" key="password"/>">
                </div>

                <button type="submit"
                        class="btn align-self-center btn-primary">
                    <fmt:message bundle="${jspMessages}"
                                 key="profileSubMenu.changePatientInfo.changePhoto"/>
                </button>
            </form>

        </div>
    </div>
</div>

<jsp:include page="../structural_element/footer.jsp"/>

</body>
</html>
