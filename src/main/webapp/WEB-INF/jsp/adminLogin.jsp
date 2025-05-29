<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="header.jsp"%>

<html>
<head>
    <title>Admin Log In</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/adminLogin.css">
</head>
<body>
<div class="login-container">
    <div class="login-box">
        <h1><fmt:message key="page.admin.welcome"/></h1>
        <h2><fmt:message key="page.login.request"/></h2>
        <form action="${pageContext.request.contextPath}/adminLogin" method="post">
            <div class="form-group">
                <label for="email"><fmt:message key="page.email"/></label>
                <input type="email" id="email" name="email" value="${param.email}" required>
            </div>
            <div class="form-group">
                <label for="password"><fmt:message key="page.password"/></label>
                <input type="password" id="password" name="password" required>
            </div>
            <button type="submit" class="btn-submit"><fmt:message key="page.login.button"/></button>
            <c:if test="${requestScope.errors}" var="error">
                <div class="error-message">
                        ${error.message}
                </div>
            </c:if>
        </form>
    </div>
</div>
</body>
</html>
