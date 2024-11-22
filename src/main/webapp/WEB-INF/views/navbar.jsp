<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <style>
        /* Reset 기본 여백 제거 */
        body {
            margin: 0;
            padding: 0;
        }

        nav {
            height: 60px; /* 원하는 높이 */
            display: flex;
            justify-content: flex-start; /* 전체 내용을 왼쪽 정렬 */
            align-items: center;
            background-color: #f4f4f4;
            padding: 10px 20px; /* 위, 아래 10px, 좌, 우 20px 여백 */
            border-bottom: 1px solid #ccc;
        }

        nav .logo {
            font-size: 24px;
            font-weight: bold;
            color: #333;
            margin-right: 50px; /* 로고와 메뉴 간격 */
        }

        nav ul {
            list-style: none;
            display: flex;
            margin: 0;
            padding: 0;
        }

        nav ul li {
            margin: 0 10px;
        }

        nav ul li a {
            text-decoration: none;
            color: #333;
            font-weight: bold;
        }

        nav ul li a:hover {
            color: #ff6700;
        }

        nav .user-icon {
            margin-left: auto; /* 아이콘을 오른쪽 끝으로 밀기 */
            font-size: 20px;
            color: #333;
            cursor: pointer;
            font-weight: bold;   
        }
        .user-icon a {
		    font-family: 'Arial', sans-serif; /* 기본 폰트 설정 */
		    font-size: 16px; /* 글자 크기 */
		    text-decoration: none; /* 링크 밑줄 제거 */
		    color: #007bff; /* 링크 색상 (블루톤) */
		    font-weight: bold; /* 텍스트 굵기 */
		}

		.user-icon a:hover {
		    color: #0056b3; /* 링크 호버 시 색상 */
		}
		.welcome-text {
		    font-family: 'Arial', sans-serif; /* 기본 폰트 설정 */
		    font-weight: bold; /* 텍스트 굵기 */
		    font-size: 16px; /* 글자 크기 */
		    color: #333; /* 일반 텍스트 색상 */
		    vertical-align: middle; /* 텍스트와 아이콘 정렬 */
		}
		

        nav img {
            width: 130px; /* 이미지 너비 조정 */
            height: auto; /* 비율을 유지하면서 높이 자동 조정 */
            margin-right: 10px; /* 이미지와 텍스트 간 여백 */
        }
    </style>
</head>
<body>
    <nav>
        <a href="main.do">
    		<img alt="logo" src="images/icon.png">	
		</a>
        <ul>
            <li><a href="bookshelf.do">서재</a></li>
            <li><a href="searchBook.do">책 찾기</a></li>
            <li><a href="notice.do">공지사항</a></li>
        </ul>
        <%
		    session = request.getSession(false); // 세션 가져오기 (있을 경우만)
		    String username = null;
		    if (session != null) {
		        username = (String) session.getAttribute("username");
		    }
		%>
        
        <% if (username != null) { %>
            <div class="user-icon"><a href="logout.do">로그아웃</a></div>
            <span class="welcome-text">&nbsp;&nbsp; 환영합니다, 👤 <%= username %>님!</span>
        <% } else { %>
            <div class="user-icon"><a href="login.do">👤 로그인</a></div>
        <% } %>
    </nav>
</body>
</html>