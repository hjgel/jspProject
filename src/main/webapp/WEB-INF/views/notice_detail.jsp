<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공지사항 상세</title>
    <link rel="stylesheet" type="text/css" href="css/notice_detail.css">
</head>
<body>
	<%@ include file="navbar.jsp" %>

    <!-- 배너 -->
    <div class="banner">
        <img alt="배너" src="images/banner2.png">
    </div>
    
    <br>
    <img src="images/notice.png" alt="아이콘" width="150px" height="auto" >
    
    <!-- 제목 -->
    <h1 class="notice-title">제목 : <c:out value="${notice.title}"/></h1>
    
    <!-- 내용 -->
    
    <div class="notice-content">
        <p>${notice.content}</p> <!-- HTML 태그 포함 시 escapeXml="false" -->
    </div>

    <!-- 작성일 -->
    <p class="notice-date">작성일: <c:out value="${notice.created_at}"/></p>
</body>
</html>