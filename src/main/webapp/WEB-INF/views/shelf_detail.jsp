<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>책 상세보기</title>
    <link rel="stylesheet" type="text/css" href="css/shelf_detail.css">
</head>

<script>
    // 에러 메시지가 있을 경우 경고창 표시 후 리디렉션
    function showErrorAndRedirect(errorMsg, redirectUrl) {
        if (errorMsg) {
            alert(errorMsg);
            setTimeout(() => {
                window.location.href = redirectUrl;
            }, 1000); // 1초 후 리디렉션
        }
    }
</script>
<body>
    <%@ include file="navbar.jsp" %>

    <!-- 배너 -->
    <div class="banner">
        <img alt="배너" src="images/banner2.png">
    </div>
    
    <!-- 책검색 제목 -->
    <br>
    <img src="images/reading.png" alt="아이콘" width="150px" height="auto" >
    

	
    <br><br>

    <div class="book-detail">
        <!-- 책 이미지 및 기본 정보 -->
            <img src="<c:out value='${book.title_url}'/>" alt="<c:out value='${book.title}'/>" class="book-image">
            <div class="book-info">
                <h1><c:out value="${book.title}"/></h1>
                <p><strong>저자:</strong> <c:out value="${book.author}"/></p>
                <p><strong>페이지 수:</strong> <c:out value="${book.pages}"/> 페이지</p>
            </div>
        </div>
        
        
        
        <%
		    String message = (String) request.getAttribute("message");
		    String error = (String) request.getAttribute("error");
			
		%>
		
		<% if (message != null) { %>
		    <script>
		        alert("<%= message %>"); // 경고창에 에러 메시지 표시
		        
		    </script>
		    <% String book_info_id = (String) request.getAttribute("book_info_id");%>
		    <script>
		        window.location.href = "shelf_detail.do?book_info_id=<%= book_info_id %>";
		    </script>
		<% } %>
		
		<% if (error != null) { %>
		    <script>
		        alert("<%= error %>"); // 경고창에 에러 메시지 표시
	        </script>
		    <% String book_info_id = (String) request.getAttribute("book_info_id");%>
		    <script>
		        window.location.href = "bookshelf.do?book_info_id=<%= book_info_id %>";
		    </script>
		<% } %>
        

        <!-- 독후감 작성 및 목록 -->
        <div class="diary-section">
            <h2>독후감</h2>
            
            <!-- 독후감 목록 -->
            <c:forEach var="diary" items="${diaryList}">
                <div class="diary-item">
                    <p>${diary.content}</p>
                    <small>작성일: ${diary.createdAt}</small>
                </div>
                <hr>
            </c:forEach>

            <!-- 독후감 작성 -->
            <form action="addDiary.do" method="post" accept-charset="UTF-8">
                <textarea name="content" rows="5" placeholder="독후감을 작성하세요" style="width: 98%; height: 400px;" required>
읽은 페이지수: 

제목:
 
줄거리: 

느낀점: </textarea>
                <input type="hidden" name="book_info_id" value="${book_info_id}">
                <button type="submit">독후감 추가</button>
            </form>
        </div>
        <br><br>
  
</body>
</html>