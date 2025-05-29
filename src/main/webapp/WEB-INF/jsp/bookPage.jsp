<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="header.jsp"%>

<html>
<head>
    <title>Book Page</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bookPage.css">
</head>
<body>
<div class="book-container">
    <div class="book-card">
        <h1 class="book-title">${requestScope.book.title}</h1>
        <p class="book-author"><strong><fmt:message key="page.book.author"/>:</strong> ${requestScope.book.author}</p>
        <p class="book-description">${requestScope.book.description}</p>
    </div>

    <div class="content-section">
        <div class="download-section">
            <h2 class="download-title"><fmt:message key="page.download.book"/></h2>
            <div class="download-links">
                <c:if test="${not empty requestScope.book.downloadFb2}">
                    <a href="${requestScope.book.downloadFb2}" class="btn-download">FB2</a>
                </c:if>
                <c:if test="${not empty requestScope.book.downloadEpub}">
                    <a href="${requestScope.book.downloadEpub}" class="btn-download">EPUB</a>
                </c:if>
                <c:if test="${not empty requestScope.book.downloadPdf}">
                    <a href="${requestScope.book.downloadPdf}" class="btn-download">PDF</a>
                </c:if>
                <c:if test="${not empty requestScope.book.downloadDocx}">
                    <a href="${requestScope.book.downloadDocx}" class="btn-download">Word</a>
                </c:if>
                <c:if test="${not empty requestScope.book.downloadMobi}">
                    <a href="${requestScope.book.downloadMobi}" class="btn-download">MOBI</a>
                </c:if>
            </div>
        </div>

        <div class="read-section">
            <h2 class="read-title">Read online</h2>
            <c:if test="${not empty book.read}">
                <a href="${book.read}" class="btn-read"><fmt:message key="page.read.book"/></a>
            </c:if>
        </div>
    </div>

    <a href="${pageContext.request.contextPath}/catalog" class="btn-back"><fmt:message key="page.back.button"/></a>
</div>
</body>
</html>
