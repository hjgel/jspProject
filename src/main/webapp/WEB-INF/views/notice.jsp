<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공지사항</title>
    <link rel="stylesheet" type="text/css" href="css/notice.css">
    <!-- Bootstrap CSS CDN -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <!-- Optional Bootstrap JS Bundle -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<%@ include file="navbar.jsp" %>
    <!-- 배너 -->
    <div class="banner">
        <img alt="배너" src="images/banner2.png">
    </div>
    <!-- 공지사항 제목 -->
    <br>
    <img src="images/notice.png" alt="아이콘" width="150px" height="auto" >
   

    <!-- 공지사항 테이블 -->
    <div class="notice-table-container">
        <table class="table">
            <thead>
                <tr class="text-center">
                    <th>번호</th>
			     	<th style="width:50%">제목</th>
			     	<th>글쓴이</th>
			    	<th>작성일시</th>
                </tr>
            </thead>
            <tbody>
                <%-- 공지사항 데이터 반복 출력 --%>
                <c:forEach var="notice" items="${noticeList}">
                    <tr>
                        <td><c:out value="${notice.id}"/></td>
                        <td><c:out value="${notice.title}"/></td>
                        <td><c:out value="${notice.author}"/></td>
                        <td><c:out value="${notice.date}"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>