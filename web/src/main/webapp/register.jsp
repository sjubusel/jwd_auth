<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Учреждение здравоохранения "Инновационная больница "Шабаны"
        г.Минска"</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>


<jsp:include page="header.jsp"/>

<h2 style="padding-left: 25px">Форма регистрации в системе:</h2>

<form action="${pageContext.request.contextPath}/register" method="post"
      style="padding-left: 25px">

    <div class="form-group">
        <label for="loginInput">Логин, создаваемый в системе</label>
        <input type="text" class="form-control" id="loginInput" name="login">
    </div>
    <div class="form-group">
        <label for="passwordInput">Пароль</label>
        <input type="password" class="form-control" id="passwordInput"
               name="password">
    </div>
    <button type="submit" class="btn btn-primary">Зарегистрироваться</button>

</form>

<br>

<c:if test="${requestScope.error ne null}">
    <div class="alert alert-danger" role="alert">
        <c:out value="${requestScope.error}"/>
    </div>
</c:if>

<jsp:include page="footer.jsp"/>

</body>
</html>
