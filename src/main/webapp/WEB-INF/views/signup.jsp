<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<!-- Optional Bootstrap JS Bundle -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<title>로그인</title>
<style>
/* Reset 기본 여백 제거 */
body, html {
    margin: 0;
    padding: 0;
    width: 100%;
    height: 100%;
    font-family: Arial, sans-serif;
    background-color: #f8f9fa;
    display: flex; /* Flexbox 사용 */
    flex-direction: column; /* 세로 정렬 */
    justify-content: center; /* 세로 중앙 정렬 */
    align-items: center; /* 가로 중앙 정렬 */
}

/* 배너 스타일 */
.banner {
    width: 100%;
    height: 20vh; /* 배너 높이를 화면 높이의 20%로 설정 */
    position: fixed; /* 최상단에 고정 */
    top: 0;
    left: 0;
    z-index: 1000; /* 다른 요소 위에 배치 */
    background-color: white; /* 배너 배경색 */
    box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1); /* 그림자 효과 */
}

.banner img {
    width: 100%;
    height: 100%;
    object-fit: cover; /* 배너 이미지 비율 유지 및 영역 맞춤 */
}


/* 로그인 폼 컨테이너 */
.container {
    display: flex;
    justify-content: center; /* 가로 중앙 정렬 */
    align-items: center; /* 세로 중앙 정렬 */
    width: 100%;
    max-width: 400px; /* 최대 너비 제한 */
    margin-top: 0px;
}

/* jumbotron 스타일 */
.jumbotron {
    width: 100%;
    background-color: #ffffff;
    border: 1px solid #ccc;
    border-radius: 10px;
    box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
    padding: 20px;
    text-align: center; /* 텍스트 중앙 정렬 */
}

/* 입력 필드 스타일 */
.jumbotron .form-control {
    margin-bottom: 15px; /* 필드 간격 */
    width: 100%; /* 입력 필드 너비를 100%로 설정 */
}

/* 로그인 버튼 스타일 */
.jumbotron .btn-primary {
    background-color: #007bff;
    border-color: #007bff;
    width: 100%; /* 버튼 너비를 100%로 설정 */
}

.jumbotron .btn-primary:hover {
    background-color: #0056b3;
    border-color: #004085;
}

/* 아이콘 스타일 */
img[alt="아이콘"] {
    display: block;
    margin: 20px auto; /* 가로 중앙 정렬 */
    width: 150px; /* 아이콘 크기 설정 */
    height: auto; /* 비율 유지 */
}
</style>
</head>
<body>
    <!-- 배너 -->
    <div class="banner">
        <img alt="배너" src="images/banner2.png">
    </div>

    <div>
    	<a href="main.do"><img src="images/icon.png" alt="아이콘" width="150px" height="auto"></a>
    </div>
    
    
    <div class="container">
        <div class="jumbotron">
        	<% 
           	 	String error = (String) request.getAttribute("error");
           		 if (error != null) { 
        	%>
            <div class="alert alert-danger" role="alert">
            <%= error %>
            </div>
       		<% } %>
            <form method="post" action="joinAction.do">
                <h3><img src="images/signup.png" alt="아이콘2" width="120px" height="auto"></h3>
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="아이디" name="username" maxlength="20">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" placeholder="비밀번호" name="password" maxlength="20">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="이름" name="name" maxlength="20">
                </div>
                
                <button type="submit" class="btn btn-primary">회원가입</button>
            </form>
        </div>
    </div>
</body>
</html>