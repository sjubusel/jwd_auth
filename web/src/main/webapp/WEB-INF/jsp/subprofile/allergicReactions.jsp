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
<c:set var="activeSubMenuProfileTab" value="allergicReactions" scope="page"/>

<fmt:setBundle basename="jspResources" var="jspMessages"/>

<html>
<head>
    <title>
        <fmt:message bundle="${jspMessages}"
                     key="profileSubMenu.allergicReactions"/>
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
            <div class="">ALLERGIC REACTIONS</div>
        </div>

        <%--&lt;%&ndash;CONTENTS&ndash;%&gt;
        <div class="bg-light d-inline-block col">

            <c:choose>
                <c:when test="${requestScope.error ne null}">
                    <c:choose>
                        &lt;%&ndash; a tech error &ndash;%&gt;
                        <c:when test="${requestScope.error eq 'tech'}">
                            <div class="alert alert-danger" role="alert">
                                <fmt:message bundle="${jspMessages}"
                                             key="profileSubMenu.allergicReactions.techError"/>
                            </div>
                        </c:when>
                        &lt;%&ndash; a validation error &ndash;%&gt;
                        <c:when test="${requestScope.error eq 'val'}">
                            <div class="alert alert-danger" role="alert">
                                <fmt:message bundle="${jspMessages}"
                                             key="profileSubMenu.allergicReactions.validationError"/>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="alert alert-danger" role="alert">
                                <fmt:message bundle="${jspMessages}"
                                             key="main.unknownCondition"/>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </c:when>
                &lt;%&ndash;Here the jsp's contents begin&ndash;%&gt;
                <c:otherwise>
                    <h1 class="text-left">
                        <fmt:message bundle="${jspMessages}"
                                     key="profileSubMenu.allergicReactionsFood.heading"/>
                    </h1>

                    &lt;%&ndash;<div class="text-muted mb-3">
                        <fmt:message bundle="${jspMessages}"
                                     key="profileSubMenu.allergicReactionsFood.disclaimer"/>
                    </div>&ndash;%&gt;
                    <c:choose>
                        <c:when test="${requestScope.medicalHistoryPermissions ne null}">
                            &lt;%&ndash;header&ndash;%&gt;
                            <div class="row d-flex mb-1 border">
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="profileSubMenu.allergicReactionsFood.recipient"/>
                                </div>
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="profileSubMenu.allergicReactionsFood.permissionDateTime"/>
                                </div>
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="profileSubMenu.allergicReactionsFood.cancellationDateTime"/>
                                </div>
                                <div class="col">
                                    <fmt:message bundle="${jspMessages}"
                                                 key="profileSubMenu.allergicReactionsFood.deleteButton"/>
                                </div>
                            </div>
                            &lt;%&ndash;contents&ndash;%&gt;
                            <c:forEach var="medPermission"
                                       items="${requestScope.medicalHistoryPermissions}">
                                <form action="${pageContext.request.contextPath}/profile"
                                      method="post">
                                    <div class="row d-flex mb-1 border align-items-center">

                                        <input type="hidden" name="command"
                                               value="profile-medical-history-permission-delete"/>
                                        <input type="hidden"
                                               name="permissionIdInput"
                                               value="${medPermission.permissionId}"/>
                                            &lt;%&ndash;1st column&ndash;%&gt;
                                        <div class="col">
                                            <c:out value="${medPermission.recipientInfo}"/>
                                        </div>
                                            &lt;%&ndash;2nd column&ndash;%&gt;
                                        <div class="col">
                                            <c:out value="${medPermission.permissionDateTime}"/>
                                        </div>
                                            &lt;%&ndash;3rd column&ndash;%&gt;
                                        <div class="col"
                                                <c:if test="${medPermission.cancellationDescription ne null}">
                                                    title="${medPermission.cancellationDescription}"
                                                </c:if>
                                        >
                                            <c:if test="${medPermission.cancellationDateTime ne null}">
                                                <c:out value="${medPermission.cancellationDateTime}"/>
                                            </c:if>
                                        </div>
                                            &lt;%&ndash; 4th column (button) &ndash;%&gt;
                                        <div class="col">
                                            <button type="submit"
                                                    class="btn align-self-center btn-primary"
                                                    <c:if test="${medPermission.cancellationDateTime ne null}">
                                                        disabled
                                                    </c:if>
                                            >
                                                <fmt:message
                                                        bundle="${jspMessages}"
                                                        key="profileSubMenu.medicalHistoryPermission.delete"/>
                                            </button>
                                        </div>

                                    </div>
                                </form>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <fmt:message bundle="${jspMessages}"
                                         key="profileSubMenu.allergicReactionsFood.noRecordsMessage"/>
                        </c:otherwise>


                        &lt;%&ndash;!!!!!!!!!! FORM&ndash;%&gt;
                    </c:choose>
                    &lt;%&ndash;AND THE FOLLOWING&ndash;%&gt;
                </c:otherwise>
            </c:choose>

            &lt;%&ndash;            <br>&ndash;%&gt;
            &lt;%&ndash;            <br>&ndash;%&gt;
            &lt;%&ndash;            <h1 class="text-left">&ndash;%&gt;
            &lt;%&ndash;                <fmt:message bundle="${jspMessages}"&ndash;%&gt;
            &lt;%&ndash;                             key="profileSubMenu.allergicReactionsFood.formHeading"/>&ndash;%&gt;
            &lt;%&ndash;            </h1>&ndash;%&gt;

            &lt;%&ndash;            <form action="${pageContext.request.contextPath}/profile"&ndash;%&gt;
            &lt;%&ndash;                  method="post">&ndash;%&gt;

            &lt;%&ndash;                <input type="hidden" name="command"&ndash;%&gt;
            &lt;%&ndash;                       value="profile-medical-history-permission-add"/>&ndash;%&gt;

            &lt;%&ndash;                <div class="form-group form-inline row">&ndash;%&gt;
            &lt;%&ndash;                    <label for="recipient" class="col-4 custom-form-label">&ndash;%&gt;
            &lt;%&ndash;                        <fmt:message bundle="${jspMessages}"&ndash;%&gt;
            &lt;%&ndash;                                     key="profileSubMenu.medicalHistoryPermission.recipient"/>&ndash;%&gt;
            &lt;%&ndash;                    </label>&ndash;%&gt;
            &lt;%&ndash;                    <input type="hidden" id="hiddenRecipientId"&ndash;%&gt;
            &lt;%&ndash;                           name="hiddenRecipientIdInput" value=""/>&ndash;%&gt;
            &lt;%&ndash;                    <input type="text"&ndash;%&gt;
            &lt;%&ndash;                           class="form-control col"&ndash;%&gt;
            &lt;%&ndash;                           id="recipient"&ndash;%&gt;
            &lt;%&ndash;                           name="recipientInput"&ndash;%&gt;
            &lt;%&ndash;                           placeholder="<fmt:message bundle="${jspMessages}"&ndash;%&gt;
            &lt;%&ndash;                                       key="profileSubMenu.medicalHistoryPermission.recipientPlaceholder"/>"&ndash;%&gt;
            &lt;%&ndash;                           pattern="<fmt:message bundle="${regEx}" key="profileSubMenu.medicalHistoryPermission.recipientPattern"/>"&ndash;%&gt;
            &lt;%&ndash;                    />&ndash;%&gt;
            &lt;%&ndash;                </div>&ndash;%&gt;
            &lt;%&ndash;                <div id="recipientResult" class="overflow-auto"&ndash;%&gt;
            &lt;%&ndash;                     style="max-height: 100px">&ndash;%&gt;
            &lt;%&ndash;                </div>&ndash;%&gt;

            &lt;%&ndash;                <button type="submit"&ndash;%&gt;
            &lt;%&ndash;                        class="btn align-self-center btn-primary">&ndash;%&gt;
            &lt;%&ndash;                    <fmt:message bundle="${jspMessages}"&ndash;%&gt;
            &lt;%&ndash;                                 key="profileSubMenu.medicalHistoryPermission.add"/>&ndash;%&gt;
            &lt;%&ndash;                </button>&ndash;%&gt;
            &lt;%&ndash;            </form>&ndash;%&gt;

            &lt;%&ndash;########################################################################&ndash;%&gt;
            <h1 class="text-left">
                <fmt:message bundle="${jspMessages}"
                             key="profileSubMenu.allergicReactionsMedicine.heading"/>
            </h1>

            &lt;%&ndash;<div class="text-muted mb-3">
                <fmt:message bundle="${jspMessages}"
                             key="profileSubMenu.allergicReactionsMedicine.disclaimer"/>
            </div>&ndash;%&gt;


        </div>--%>
        <%--CONTENTS--%>
    </div>
</div>

<jsp:include page="../structural_element/footer.jsp"/>

</body>
</html>
