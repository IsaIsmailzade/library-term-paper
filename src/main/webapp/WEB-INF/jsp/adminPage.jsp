<%-- Created by IntelliJ IDEA. User: Isa Date: 20.01.2025 --%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="header.jsp"%>

<html>
<head>
    <title>Admin Page</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/adminPage.css">
</head>
<body>
<div class="admin-container">
    <h1 class="admin-title"><fmt:message key="page.admin.book.information"/></h1>
    <div class="admin-box">
        <div class="admin-actions">
            <div class="adding-section">
                <form action="${pageContext.request.contextPath}/adminPage" method="post">
                    <h2 class="section-title"><fmt:message key="page.admin.adding" /></h2>
                    <input type="hidden" name="action" value="add">
                    <div class="form-group">
                        <label for="title"><fmt:message key="page.admin.input.title" /></label>
                        <input type="text" id="title" name="title">
                    </div>
                    <div class="form-group">
                        <label for="author"><fmt:message key="page.admin.input.author" /></label>
                        <input type="text" id="author" name="author">
                    </div>
                    <div class="form-group">
                        <label for="description"><fmt:message key="page.admin.input.description" /></label>
                        <textarea id="description" name="description"></textarea>
                    </div>

                    <div class="download-links">
                        <div class="links-left">
                            <h2><fmt:message key="page.admin.download.links"/></h2>
                            <div class="form-group">
                                <label for="downloadFb2">FB2</label>
                                <input type="text" id="downloadFb2" name="downloadFb2">
                            </div>
                            <div class="form-group">
                                <label for="downloadEpub">EPUB</label>
                                <input type="text" id="downloadEpub" name="downloadEpub">
                            </div>
                            <div class="form-group">
                                <label for="downloadPdf">PDF</label>
                                <input type="text" id="downloadPdf" name="downloadPdf">
                            </div>
                            <div class="form-group">
                                <label for="downloadDocx">Word</label>
                                <input type="text" id="downloadDocx" name="downloadDocx">
                            </div>
                            <div class="form-group">
                                <label for="downloadMobi">MOBI</label>
                                <input type="text" id="downloadMobi" name="downloadMobi">
                            </div>
                        </div>
                    </div>

                    <h2 class="links-left"><fmt:message key="page.admin.read.link"/></h2>
                    <div class="form-group">
                        <label for="read">Link</label>
                        <input type="text" id="read" name="read">
                    </div>

                    <button type="submit" class="btn-submit"><fmt:message key="page.admin.add.button"/></button>
                    <c:if test="${not empty requestScope.add}">
                        <div class="add-message">
                                ${requestScope.add}
                        </div>
                    </c:if>
                </form>
            </div>

            <div class="deleting-section">
                <form action="${pageContext.request.contextPath}/adminPage" method="post">
                    <h2 class="section-title"><fmt:message key="page.admin.deleting"/></h2>
                    <input type="hidden" name="action" value="delete">
                    <div class="form-group">
                        <label for="id"><fmt:message key="page.admin.input.id"/></label>
                        <input type="text" id="id" name="id">
                    </div>
                    <button type="submit" class="btn-submit"><fmt:message key="page.admin.delete.button"/></button>
                    <c:if test="${not empty requestScope.delete}">
                        <div class="delete-message">
                                ${requestScope.delete}
                        </div>
                    </c:if>
                </form>
            </div>
        </div>
    </div>

    <c:if test="${not empty requestScope.errors}">
        <div class="error-message">
            <c:forEach var="error" items="${requestScope.errors}">
                <span>${error.message}</span><br>
            </c:forEach>
        </div>
    </c:if>
</div>
</body>
</html>
