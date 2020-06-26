<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	request.setCharacterEncoding("UTF-8");
	int no = 1;
	String title = request.getParameter("title");
	String writer = request.getParameter("writer");
	String regdate = request.getParameter("regdate");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1" style="border-spacing: 0px;">
	<tr>
		<td>번호</td>
		<td>제목</td>
		<td>작성자</td>
		<td>날짜</td>
	</tr>
	<tr>
		<td><%=no %></td>
		<td><%=title %></td>
		<td><%=writer %></td>
		<td><%=regdate %></td>
	</tr>
</table>
<a href="boardWrite.jsp">글쓰기</a>
</body>
</html>