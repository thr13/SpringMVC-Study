<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--JSP 는 <% %> 가 없다면 전부 println 으로 출력하는 것과 같다 --%>
<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
성공
<ul>
    <li>id=${member.id}</li>
    <li>username=${member.username}</li>
    <li>age=${member.age}</li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
