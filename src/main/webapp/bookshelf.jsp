<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>내 서재</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
    <!-- 배너 -->
    <div class="banner">
        <img src="images/banner.png" alt="배너">
    </div>

    <!-- 내 서재 제목 -->
    <div class="library-header">
        <h1><img src="images/icon.png" alt="아이콘"> 내 서재</h1>
    </div>

    <!-- 책 카드 목록 -->
    <div class="book-grid">
        <%-- 책 목록 반복 출력 --%>
        <c:forEach var="book" items="${bookList}">
            <div class="book-card">
                <img src="images/<c:out value='${book.image}'/>" alt="<c:out value='${book.title}'/>">
                <h3><c:out value="${book.title}"/></h3>
                <p><c:out value="${book.author}"/></p>
                <div class="progress-bar">
                    <div class="progress" style="width: <c:out value='${book.progressPercent}'/>%;"></div>
                </div>
                <p class="progress-text"><strong><c:out value="${book.currentPage}"/></strong> / <c:out value="${book.totalPage}"/></p>
            </div>
        </c:forEach>
    </div>
</body>
</html>