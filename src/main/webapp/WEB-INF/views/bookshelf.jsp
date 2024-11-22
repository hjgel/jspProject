<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>내 서재</title>
    <link rel="stylesheet" type="text/css" href="css/bookshelf.css">
</head>
<body>
	<%@ include file="navbar.jsp" %>

    <!-- 내 서재 제목 -->
    <!-- 헤더 배너 -->
    <div class="banner">
        <img alt="배너" src="images/banner2.png">
    </div>
    
    <br>
    <img src="images/내서재.png" alt="icon" class="panel-icon" width="150px" height="auto">
    
    <br><br>
    <img alt="" src="images/libbook.png" width="90%" height="auto"> 
    
    
    <!-- 책 카드 목록 -->
    <div class="book-grid">
        책 목록 반복 출력
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