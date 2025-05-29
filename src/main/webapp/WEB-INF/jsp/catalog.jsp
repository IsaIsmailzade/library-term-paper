<%--
  Created by IntelliJ IDEA.
  User: Isa
  Date: 19.12.2024
  Time: 11:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="header.jsp"%>

<html>
<head>
    <title>Book's Catalog</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/catalog.css">
</head>
<body>
<div class="catalog-container">
    <h1 class="catalog-title"><fmt:message key="page.header.text"/></h1>
    <div class="books-list">
        <c:forEach items="${requestScope.books}" var="book">
            <div class="book-item">
                <a href="${pageContext.request.contextPath}/bookPage?bookId=${book.id}" class="book-link">
                    <h2 class="book-title">${book.title}</h2>
                    <p class="book-author">${book.author}</p>
                </a>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
