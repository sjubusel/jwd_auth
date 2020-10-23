<%--<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>--%>
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

<fmt:setBundle basename="jspResources" var="jspMessages"/>

<div class="alert alert-info" role="alert">
    <fmt:message bundle="${jspMessages}" key="header.alertInfo"/>
</div>

<div id="menu">
    <div>
        <a href="${pageContext.request.contextPath}/">
            <img id="logotype" src="img/logo.png" alt="logotype"/>
        </a>
    </div>
    <div class="nav container-fluid nav-pills bg-light left-menu-part">
        <div class="nav-item">
            <a <c:choose>
                <c:when test="${pageScope.activeMenuTab eq null}">
                    class="nav-link active"
                </c:when>
                <c:otherwise>
                    class="nav-link"
                </c:otherwise>
            </c:choose>
                    href="${pageContext.request.contextPath}/main">
                <fmt:message bundle="${jspMessages}" key="header.menu.main"/>
            </a>
        </div>

        <c:if test="${sessionScope.authUser ne null}">
            <div class="nav-item">
                <a <c:choose>
                    <c:when test="${pageScope.activeMenuTab == 'patients'}">
                        class="nav-link active"
                    </c:when>
                    <c:otherwise>
                        class="nav-link"
                    </c:otherwise>
                </c:choose>
                        href="${pageContext.request.contextPath}/main?command=go-to-patients">
                    <fmt:message bundle="${jspMessages}"
                                 key="header.menu.patients"/>
                </a>
            </div>
        </c:if>

        <c:if test="${sessionScope.authUser ne null}">
            <div class="nav-item">
                <a <c:choose>
                    <c:when test="${pageScope.activeMenuTab == 'staff'}">
                        class="nav-link active"
                    </c:when>
                    <c:otherwise>
                        class="nav-link"
                    </c:otherwise>
                </c:choose>
                        href="${pageContext.request.contextPath}/main?command=go-to-staff">
                    <fmt:message bundle="${jspMessages}"
                                 key="header.menu.medStaff"/>
                </a>
            </div>
        </c:if>

        <div class="nav-item">
            <a <c:choose>
                <c:when test="${pageScope.activeMenuTab == 'news'}">
                    class="nav-link active"
                </c:when>
                <c:otherwise>
                    class="nav-link"
                </c:otherwise>
            </c:choose>
                    href="${pageContext.request.contextPath}/main?command=go-to-news">
                <fmt:message bundle="${jspMessages}"
                             key="header.menu.news"/>
            </a>
        </div>
        <div class="nav-item">
            <a <c:choose>
                <c:when test="${pageScope.activeMenuTab == 'aboutUs'}">
                    class="nav-link active"
                </c:when>
                <c:otherwise>
                    class="nav-link"
                </c:otherwise>
            </c:choose>
                    href="${pageContext.request.contextPath}/main?command=go-to-about-us">
                <fmt:message bundle="${jspMessages}"
                             key="header.menu.aboutUs"/>
            </a>
        </div>
        <div class="nav-item">
            <a <c:choose>
                <c:when test="${pageScope.activeMenuTab == 'contacts'}">
                    class="nav-link active"
                </c:when>
                <c:otherwise>
                    class="nav-link"
                </c:otherwise>
            </c:choose>
                    href="${pageContext.request.contextPath}/main?command=go-to-contacts">
                <fmt:message bundle="${jspMessages}"
                             key="header.menu.contacts"/>
            </a>
        </div>
        <div class="nav-item flex-grow-1">
            <%--it is a stub-div in order to run away from bootstrap percularities --%>
        </div>

        <%@ include
                file="/WEB-INF/jsp/structural_element/languageButtonBar.jsp" %>

        <c:choose>
            <c:when test="${sessionScope.authUser eq null}">
                <div class="nav-item">
                    <a <c:choose>
                        <c:when test="${pageScope.activeMenuTab == 'signIn'}">
                            class="nav-link active"
                        </c:when>
                        <c:otherwise>
                            class="nav-link"
                        </c:otherwise>
                    </c:choose>
                            href="${pageContext.request.contextPath}/main?command=go-to-login">
                        <fmt:message bundle="${jspMessages}"
                                     key="header.menu.singIn"/>
                    </a>
                </div>
                <div class="nav-item">
                    <a <c:choose>
                        <c:when test="${pageScope.activeMenuTab == 'signUp'}">
                            class="nav-link active"
                        </c:when>
                        <c:otherwise>
                            class="nav-link"
                        </c:otherwise>
                    </c:choose>
                            href="${pageContext.request.contextPath}/main?command=go-to-register">
                        <fmt:message bundle="${jspMessages}"
                                     key="header.menu.signUp"/>
                    </a>
                </div>
            </c:when>
            <c:otherwise>
                <div class="nav-item">
                    <a <c:choose>
                        <c:when test="${pageScope.activeMenuTab == 'profile'}">
                            class="nav-link active"
                        </c:when>
                        <c:otherwise>
                            class="nav-link"
                        </c:otherwise>
                    </c:choose>
                            href="${pageContext.request.contextPath}/profile?command=go-to-profile">
                        <fmt:message bundle="${jspMessages}"
                                     key="header.menu.helloMessage"/>
                        <c:out value=", "/>
                        <c:out value="${sessionScope.authUser.login}"/>
                    </a>
                </div>
                <div class="nav-item">
                    <a class="nav-link"
                       href="${pageContext.request.contextPath}/profile?command=logout">
                        <fmt:message bundle="${jspMessages}"
                                     key="header.menu.logOut"/>
                    </a>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>

<br>
