<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // main.do로 리다이렉트
    response.sendRedirect("main.do");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>리다이렉트</title>
</head>
<body>
    <!-- 리다이렉트가 작동하지 않을 경우 이 문구가 표시됩니다. -->
    <p>Redirecting to <a href="main.do">main.do</a></p>
</body>
</html>