<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>독정이</title>
    <link rel="stylesheet" type="text/css" href="css/main.css">
</head>
</head>
<body>
    <%@ include file="navbar.jsp" %>
    
    <!-- 헤더 배너 -->
    <div class="banner">
        <img alt="배너" src="images/banner.png">
    </div>
    <br><br>
    <div class="container">
    <h2 class="panel-title">
    	<img src="images/now.png" alt="icon" class="panel-icon" width="170px" height="auto">
    </h2>
    <!-- 좌측: 내 독서 현황 -->
    <div class="left-panel">
        
        <div class="panel-content">
            <p class="name"><strong>👤 이름:</strong> <%= username %></p> <br>
            <p><strong>   &nbsp 읽은 책 수:</strong> ${bookCount}권</p>
            <p><strong>   &nbsp 독서일기 작성 수:</strong> ${diaryCount}건</p>
        </div>
    </div>

    <!-- 우측: 내가 읽은 책 -->
    <div class="right-panel">
        <h2>내가 읽은 책</h2>
        <div class="book-grid">
		    <c:forEach var="book" items="${bookList}">
		        <a href="shelf_detail.do?book_info_id=${book.book_info_id}" class="book-card-link"><div class="book" style="background-image: url('<c:out value="${book.titleUrl}"/>');"></div></a>
		    </c:forEach>
		</div>
    </div>
</div>
    
</body>
</html>