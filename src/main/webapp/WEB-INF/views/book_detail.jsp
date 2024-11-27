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
	/* "내 서재에 등록" 버튼 스타일 */
	.btn-add-to-library {
	    background-color: #ff6700;
	    color: white;
	    border: none;
	    padding: 10px 20px;
	    font-size: 16px;
	    border-radius: 5px;
	    cursor: pointer;
	}
	
	.btn-add-to-library:hover {
	    background-color: #e05e00;
	}
	
	/* 모달(팝업) 스타일 */
	.modal {
	    display: none; /* 기본적으로 숨김 */
	    position: fixed;
	    top: 50%;
	    left: 50%;
	    transform: translate(-50%, -50%);
	    z-index: 1001; /* 오버레이보다 앞 */
	    background-color: white;
	    border-radius: 10px;
	    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
	    padding: 20px;
	    width: 500px;
	    text-align: center;
	}
	
	.modal-content input[type="text"],
	.modal-content input[type="number"] {
	    width: 100%;
	    padding: 8px;
	    margin-top: 10px;
	    border: 1px solid #ddd;
	    border-radius: 5px;
	}
	
		/* 모달(팝업) 전체 여백 조정 */
	.modal-content {
	    padding: 20px; /* 내부 여백 추가 */
	    margin: 0 10px; /* 양옆 마진 추가 */
	}
	
	/* 오버레이 스타일 */
	.overlay {
	    display: none; /* 기본적으로 숨김 */
	    position: fixed;
	    top: 0;
	    left: 0;
	    width: 100%;
	    height: 100%;
	    background-color: rgba(0, 0, 0, 0.5); /* 반투명 검정 배경 */
	    z-index: 1000; /* 모달 뒤에 위치 */
	}
	
	/* 등록하기 버튼 */
	.btn-submit {
	    background-color: #ff6700;
	    color: white;
	    border: none;
	    padding: 10px 20px;
	    font-size: 16px;
	    border-radius: 5px;
	    cursor: pointer;
	    margin-top: 10px;
	}
	
	.btn-submit:hover {
	    background-color: #e05e00;
	}
	/* 제목과 저자를 양옆으로 정렬 */
	.form-row {
	    display: flex; /* Flexbox 사용 */
	    justify-content: space-between; /* 양옆으로 배치 */
	    gap: 20px; /* 간격 설정 */
	}
	
	.form-group {
	    display: flex;
	    flex-direction: column; /* 내부 요소를 세로 정렬 */
	    flex: 1; /* 동일한 너비로 분배 */
	}
	
	.form-group label {
	    margin-bottom: 5px; /* 레이블과 입력 필드 간격 */
	    font-weight: bold; /* 레이블 강조 */
	}
	
	.form-group input {
	    padding: 8px;
	    border: 1px solid #ddd;
	    border-radius: 5px;
	}
</style>

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
    
        <% 
		    String error = (String) request.getAttribute("error");
		    if (error != null) { 
		%>
		    <script>
		        alert("<%= error %>"); // 경고창에 에러 메시지 표시
		        window.location.href = "searchBook.do";
		    </script>
		<% } %>
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
            
            <!-- 내 서재에 등록 버튼 -->
            <button id="addToLibraryBtn" class="btn-add-to-library">내 서재에 등록</button>
        </div>
    </div>

    <!-- 팝업 모달 -->
    <div id="popupModal" class="modal">
	    <div class="modal-content">
	        <h2><img src="images/submitBook.png" alt="등록 아이콘" style="width: 150px; height: auto;"></h2>
	        <!-- 등록 폼 -->
	        <form action="bookInfo.do" method="post">
	            <!-- 제목과 저자 -->
	            <div class="form-row">
	                <div class="form-group">
	                    <label for="bookTitle">제목:</label>
	                    <input type="text" id="title" name="title" value="<c:out value='${book.title}'/>" readonly>
	                </div> 
	                &nbsp;&nbsp; <!-- 간격 추가 -->
	                <div class="form-group">
	                    <label for="bookAuthor">저자:</label>
	                    <input type="text" id="author" name="bookAuthor" value="<c:out value='${book.author}'/>" readonly>
	                </div>
	            </div>
	            <br><br>
	            
	            <!-- 페이지 수 입력 -->
	            <label for="bookPages">책 페이지 수:</label>
	            <input type="number" id="pages" name="pages" placeholder="0 페이지 이상" required>
	            <br><br>
	            
	            <!-- bookId를 히든 필드로 포함 -->
	            <input type="hidden" name="book_id" value="<c:out value='${book.bookId}'/>">
	
	            <!-- 등록 버튼 -->
	            <button type="submit" class="btn-submit">등록하기</button>
	        </form>
	    </div>
	</div>

    <div id="overlay" class="overlay"></div>

    <script>
        // 모달 및 오버레이 엘리먼트
        const modal = document.getElementById("popupModal");
        const overlay = document.getElementById("overlay");
        const addToLibraryBtn = document.getElementById("addToLibraryBtn");

        // 버튼 클릭 시 모달 열기
        addToLibraryBtn.addEventListener("click", () => {
            modal.style.display = "block";
            overlay.style.display = "block";
        });

        // 오버레이 클릭 시 모달 닫기
        overlay.addEventListener("click", () => {
            modal.style.display = "none";
            overlay.style.display = "none";
        });
    </script>
</body>
</html>