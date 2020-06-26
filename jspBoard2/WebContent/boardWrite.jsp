<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="boardList.jsp" method="post">
<table border="1" style="border-spacing: 0px;">
<tr>
	<td>제목</td>
	<td><input type="text" name="title"></td>
</tr>
<tr>
	<td>작성자</td>
	<td><input type="text" name="writer"></td>
</tr>
<tr>
	<td>날짜</td>
	<td><input type="text" name="regdate"></td>
</tr>
<tr>
	<td colspan="2" align="center"><input type="submit"></td>
</tr>

</table>
</form>
</body>
</html>