<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header.css">

<fmt:setLocale value="${requestScope.lang != null ? sessionScope.lang : (param.lang != null ? param.lang : 'en_US')}" />
<fmt:setBundle basename="translations" />

<div class="header-container">
    <c:if test="${not empty sessionScope.user or not empty sessionScope.admin}">
        <div class="logout-box">
            <form action="${pageContext.request.contextPath}/logout" method="post">
                <button type="submit" class="btn-logout"><fmt:message key="page.logout.button" /></button>
            </form>
        </div>
    </c:if>
    <div class="locale-box">
        <form id="ru" action="${pageContext.request.contextPath}/locale" method="post"></form>
        <form id="en" action="${pageContext.request.contextPath}/locale" method="post"></form>
        <button form="ru" type="submit" name="lang" value="ru_RU" class="btn-locale">RU</button>
        <button form="en" type="submit" name="lang" value="en_US" class="btn-locale">EN</button>
    </div>
</div>
