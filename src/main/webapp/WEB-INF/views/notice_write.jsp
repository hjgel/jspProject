<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공지사항 작성</title>
    <link rel="stylesheet" type="text/css" href="css/notice_write.css">
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
    
    <br>
    <img src="images/notice.png" alt="아이콘" width="150px" height="auto" >
    
    <% 
        	String message = (String) request.getAttribute("message");
		    String error = (String) request.getAttribute("error");
		    if (error != null) { 
		%>
		    <script>
		        alert("<%= error %>"); // 경고창에 에러 메시지 표시
		        window.location.href = "notice_write.do";
		    </script>
		<% } if (message != null) {   %>
			<script>
		        alert("책이 등록되었습니다!"); // 경고창에 에러 메시지 표시
		        window.location.href = "notice.do";
		    </script>
		<% } %>
   

    <!-- 공지사항 작성 폼 -->
    <div class="write-container">
        <h2>공지사항 작성</h2>
        <form action="notice_action.do" method="post">
            <div>
                <label for="title">제목</label>
                <input type="text" id="title" name="title" placeholder="제목을 입력하세요" required>
            </div>
            <div>
                <label for="content">내용</label>
                <textarea id="content" name="content" rows="10" placeholder="내용을 입력하세요" required></textarea>
            </div>
            <button type="submit">등록하기</button>
        </form>
    </div>
</body>
</html>