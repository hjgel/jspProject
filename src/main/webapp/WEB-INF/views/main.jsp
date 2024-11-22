<%@ page contentType="text/html; charset=UTF-8" %>
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
            <p class="name"><strong>👤 이름:</strong> 이현준</p> <br>
            <p><strong>   &nbsp 읽은 책 수:</strong> 30권</p>
            <p><strong>   &nbsp 독서일기 작성 수:</strong> 80건</p>
        </div>
    </div>

    <!-- 우측: 내가 읽은 책 -->
    <div class="right-panel">
        <h2>내가 읽은 책</h2>
        <div class="book-grid">
            <div class="book" style="background-image: url('http://www.nl.go.kr/seoji/fu/ecip/dbfiles/CIP_FILES_TBL/6303968_3.jpg');"></div>
            <div class="book" style="background-image: url('images/book2.jpg');"></div>
            <div class="book" style="background-image: url('images/book3.jpg');"></div>
            <div class="book" style="background-image: url('images/book4.jpg');"></div>
            <div class="book" style="background-image: url('images/book5.jpg');"></div>
            <div class="book" style="background-image: url('images/book6.jpg');"></div>
            <div class="book" style="background-image: url('images/book1.jpg');"></div>
            <div class="book" style="background-image: url('images/book2.jpg');"></div>
            <div class="book" style="background-image: url('images/book3.jpg');"></div>
            <div class="book" style="background-image: url('images/book4.jpg');"></div>
            <div class="book" style="background-image: url('images/book5.jpg');"></div>
            <div class="book" style="background-image: url('images/book6.jpg');"></div>
        </div>
    </div>
</div>
    
</body>
</html>