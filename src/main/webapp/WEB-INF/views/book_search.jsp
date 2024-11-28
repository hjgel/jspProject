<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    justify-content: center; /* 가로 중앙 정렬 */
    align-items: center; /* 세로 중앙 정렬 */
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

<style>
/* 책 이미지 크기 조정 */
.book-item img {
 	width: 100px !important; /* 너비를 50px로 설정 */
    height: auto !important; /* 비율 유지 */
    border-radius: 10px; /* 모서리를 부드럽게 (옵션) */
    margin-right: 15px; /* 이미지와 텍스트 간격 */
}
* book-list를 중앙 정렬 */
.book-list {
    display: flex; /* Flexbox 사용 */
    flex-direction: column; /* 세로 정렬 */
    align-items: center; /* 가로 중앙 정렬 */
    width: 100%; /* 전체 너비 설정 */
    max-width: 1200px; /* 최대 너비 제한 */
    padding: 20px; /* 리스트 내부 여백 */
    background-color: #ffffff; /* 배경색 */

    */
}

/* 개별 book-item 스타일 */
.book-item {
    display: flex;
    align-items: center;
    margin-bottom: 20px; /* 항목 간 여백 */
    width: 100%; /* 리스트 항목의 너비 (옵션: 화면 너비의 80%) */
    max-width: 1000px; /* 최대 너비 제한 */
    padding: 10px;
    background-color: #fff; /* 항목 배경색 */
    margin: 0 auto;
}

/* 책 소개 텍스트 생략 처리 */
.book-introduction {
    display: -webkit-box; /* 플렉스 기반 레이아웃 */
    -webkit-line-clamp: 2; /* 최대 2줄까지 표시 */
    -webkit-box-orient: vertical; /* 박스 방향을 수직으로 설정 */
    overflow: hidden; /* 넘치는 텍스트를 숨김 */
    text-overflow: ellipsis; /* 생략부호 (...) 추가 */
    line-height: 1.6; /* 줄 간격 */
    max-height: calc(1.6em * 5); /* 줄 간격에 맞게 최대 높이 설정 */
    color: #666;
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
        <form action="searchBook.do" method="get">
            <input type="text" name="query" placeholder="책 검색하기">
            <button type="submit"><i class="fa fa-search"></i></button>
        </form>
    </div>

    <div class="book-list">
	    <c:forEach var="book" items="${bookList}">
	    	<a href="book_detail.do?bookId=<c:out value='${book.bookId}'/>" style="text-decoration: none; color: inherit;">
		        <div class="book-item" style="display: flex; align-items: center; margin-bottom: 15px;">
		        	
			            <img src="<c:out value='${book.titleUrl}'/>" alt="<c:out value='${book.title}'/>" 
			                 style="width: 150px; height: auto; margin-right: 15px;">
			            <div class="book-info">
			                <h2 style="margin: 0;"><c:out value="${book.title}"/></h2>
			                <p style="margin: 5px 0; color: #555;">
			                    <c:out value="${book.author}"/> · 
			                    <c:out value="${book.publisher}"/> · 
			                    <c:out value="${book.publishDate}"/>년 출판
			                </p>
			                <br><br>
			                <p class="book-introduction"><c:out value="${book.introduction }"/></p>
			            </div>
		        </div>
	        </a>
	    </c:forEach>
	
	</div>	
</body>
</html>