<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Bootstrap CSS CDN -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<!-- Optional Bootstrap JS Bundle -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<style>
/* Reset */
body, html {
    margin: 0;
    padding: 0;
    font-family: Arial, sans-serif;
    background-color: #f8f9fa;
    width: 100%;
    height: 100%;
}

/* 배너 스타일 */
.banner img {
    width: 100%;
    height: auto;
    display: block;
}
.search-bar {
    display: flex;
    justify-content: center;
    align-items: center;
    margin: 20px 0;
}

.search-bar form {
    display: flex;
    width: 40%; /* 화면 너비의 50% */
    max-width: 800px; /* 최대 너비 제한 */
}

.search-bar input[type="text"] {
    width: 100%;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px 0 0 5px;
    outline: none;
    font-size: 16px;
}

.search-bar button {
    padding: 10px;
    border: none;
    background-color: #ff6700;
    color: white;
    cursor: pointer;
    border-radius: 0 5px 5px 0;
    font-size: 16px;
}

.search-bar button:hover {
    background-color: #e05e00;
}
</style>
<body>
	<%@ include file="navbar.jsp" %>
    <!-- 배너 -->
    <div class="banner">
        <img alt="배너" src="images/banner2.png">
    </div>
	<!-- 책검색 제목 -->
    <br>
    <img src="images/searchbook.png" alt="아이콘" width="150px" height="auto" >
	
    <!-- 검색창 -->
    <div class="search-bar">
        <form action="searchBooks" method="get">
            <input type="text" name="query" placeholder="책 검색하기">
            <button type="submit"><i class="fa fa-search"></i></button>
        </form>
    </div>

    <!-- 책 목록 -->
    <div class="book-list">
        <%-- 책 목록 반복 출력 --%>
        <c:forEach var="book" items="${bookList}">
            <div class="book-item">
                <img src="images/<c:out value='${book.image}'/>" alt="<c:out value='${book.title}'/>">
                <div class="book-info">
                    <h2><c:out value="${book.title}"/></h2>
                    <p><c:out value="${book.author}"/> · <c:out value="${book.publisher}"/> · <c:out value="${book.year}"/>년 출판</p>
                    <p class="description"><c:out value="${book.description}"/></p>
                </div>
            </div>
        </c:forEach>
    </div>
</body>
</html>