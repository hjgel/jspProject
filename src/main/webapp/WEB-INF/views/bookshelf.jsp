<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>내 서재</title>
    <link rel="stylesheet" type="text/css" href="css/book_shelf.css">
</head>
<body>
	<%@ include file="navbar.jsp" %>

    <!-- 내 서재 제목 -->
    <!-- 헤더 배너 -->
    <div class="banner">
        <img alt="배너" src="images/banner2.png">
    </div>
    
    <br>
    <img src="images/bookshelf.png" alt="icon" class="panel-icon" width="150px" height="auto">
    
    <br><br>
    <!-- <img alt="" src="images/libbook.png" width="90%" height="auto"> --> 
    
    
    <!-- 책 카드 목록 -->
    <div class="book-grid">
    <c:forEach var="book" items="${bookList}">
    	<a href="shelf_detail.do?book_info_id=${book.book_info_id}" class="book-card-link">
	        <div class="book-card">
	            <!-- 책 이미지 -->
	            <img src="<c:out value='${book.titleUrl}'/>" alt="<c:out value='${book.title}'/>">
	            <!-- 책 제목 -->
	            <h3><c:out value="${book.title}"/></h3>
	            <!-- 저자 -->
	            <p><c:out value="${book.author}"/></p>
	            <!-- 페이지 수 -->
	            <p class="progress-text">
	                <strong><c:out value="${book.pages}"/></strong> 페이지
	            </p>
	        </div>
        </a>
    </c:forEach>
    

</div>
</body>
</html>