<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="header.jsp"%>

<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/registration.css">
</head>
<body>
<div class="registration-container">
    <div class="registration-box">
        <h1><fmt:message key="page.library.text"/></h1>
        <h2><fmt:message key="page.register.request"/></h2>
        <form action="${pageContext.request.contextPath}/registration" method="post">
            <div class="form-group">
                <label for="name"><fmt:message key="page.name"/></label>
                <input type="text" id="name" name="name" value="${requestScope.name}" required>
            </div>
            <div class="form-group">
                <label for="surname"><fmt:message key="page.surname"/></label>
                <input type="text" id="surname" name="surname" value="${requestScope.surname}" required>
            </div>
            <div class="form-group">
                <label for="email"><fmt:message key="page.email"/></label>
                <input type="email" id="email" name="email" value="${requestScope.email}" required>
            </div>
            <div class="form-group">
                <label for="password"><fmt:message key="page.password"/></label>
                <input type="password" id="password" name="password" value="${requestScope.password}" required>
            </div>
            <div class="form-group">
                <label for="phone"><fmt:message key="page.phone"/></label>
                <input type="tel" id="phone" name="phone" value="${requestScope.phone}" required>
            </div>
            <button type="submit" class="btn-submit"><fmt:message key="page.registration.button"/></button>
            <a href="${pageContext.request.contextPath}/login" class="btn-link">
                <fmt:message key="page.login.button"/>
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
