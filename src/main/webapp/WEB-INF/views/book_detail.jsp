<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book Detail</title>
<style>
	
	
    /* 책 상세정보 레이아웃 스타일 */
    .book-detail {
        display: flex; /* Flexbox 사용 */
        align-items: flex-start; /* 세로 정렬: 상단 기준 */
        justify-content: flex-start; /* 가로 정렬: 왼쪽 정렬 */
        gap: 20px; /* 이미지와 텍스트 사이 간격 */
        padding: 40px; /* 내부 여백 */
        border: 1px solid #ddd; /* 테두리 */
        border-radius: 10px; /* 둥근 테두리 */
        box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1); /* 그림자 효과 */
        background-color: #ffffff; /* 카드 배경색 */
        max-width: 1000px; /* 컨테이너 최대 너비 */
        width: 120%; /* 화면 너비의 80% */
        margin: auto; /* 컨테이너를 중앙으로 */
        min-height: 400px; /* 최소 높이 설정 */
    }

    .book-detail img {
        width: 300px; /* 이미지 너비 */
        height: auto; /* 비율 유지 */
        border-radius: 5px; /* 이미지 모서리 둥글게 */
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* 이미지 그림자 효과 */
    }

    .book-info {
        display: flex;
        flex-direction: column; /* 텍스트를 세로로 정렬 */
    }

    .book-info h1 {
        margin: 0;
        font-size: 24px; /* 제목 크기 */
        color: #333;
    }

    .book-info p {
        margin: 5px 0;
        color: #555; /* 텍스트 색상 */
        ont-size: 22px; /* 글자 크기 */
    	line-height: 1.5; /* 줄 간격 */

    }

    .book-info strong {
        font-weight: bold;
        color: #333;
    }
    .book-introduction{
    	border: 1px solid #ddd; /* 테두리 */
    	height: 200px; /* 비율 유지 */
    	padding: 10px; /* 테두리 안쪽 여백 */
    	box-sizing: border-box; /* 패딩이 height에 포함되도록 설정 */
    }
</style>
</head>
<body>
    <%@ include file="navbar.jsp" %>
    <!-- 배너 -->
    <div class="banner">
        <img alt="배너" src="images/banner2.png">
    </div>
	<!-- 책검색 제목 -->
    <br>
    <img src="images/detail.png" alt="아이콘" width="150px" height="auto" >
	
    <br><br>
    <div class="book-detail">
        <!-- 책 이미지 -->
        <img src="<c:out value='${book.titleUrl}'/>" alt="<c:out value='${book.title}'/>">
        
        <!-- 책 정보 -->
        <div class="book-info">
            <h1><c:out value="${book.title}"/></h1><br>
            <p><strong>저자:</strong> <c:out value="${book.author}"/></p>
            <p><strong>출판사:</strong> <c:out value="${book.publisher}"/></p>
            <p><strong>출판일:</strong> <c:out value="${book.publishDate}"/></p>
            <p><strong>주제:</strong> <c:out value="${book.subject}"/></p>
            <br><br>
            <p><strong>소개</strong></p>
            <div class="book-introduction"><p><c:out value="${book.introduction}"/></p></div>
            
        </div>
    </div>
</body>
</html>