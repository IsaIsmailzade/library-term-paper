<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header.jsp"%>

<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>
<div class="login-container">
    <div class="login-box">
        <h1><fmt:message key="page.library.text"/></h1>
        <h2><fmt:message key="page.login.request"/></h2>
        <form action="${pageContext.request.contextPath}/login" method="post">
            <div class="form-group">
                <label for="email"><fmt:message key="page.email"/></label>
                <input type="email" id="email" name="email" value="${param.email}" required>
            </div>
            <div class="form-group">
                <label for="password"><fmt:message key="page.password"/></label>
                <input type="password" id="password" name="password" required>
            </div>
            <button type="submit" class="btn-submit"><fmt:message key="page.login.button"/></button>
            <a href="${pageContext.request.contextPath}/registration" class="btn-link">
                <fmt:message key="page.registration.button"/>
            </a>
            <c:if test="${not empty requestScope.errors}">
                <div class="error-message">
                    <c:forEach var="error" items="${requestScope.errors}">
                        <span>${error.message}</span><br>
                    </c:forEach>
                </div>
            </c:if>
        </form>
    </div>
</div>
</body>
</html>
