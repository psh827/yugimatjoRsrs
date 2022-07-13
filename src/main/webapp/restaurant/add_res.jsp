<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<form action="add_res.do" method="post">
	식당이름: <input type="text" name="resName">
	위치: <input type="text" name="resLocation">
	평점: <input type="text" name="resScore">
	음식종류: <input type="text" name="foodType">
	가격: <input type="text" name="foodPrice">
	인원: <input type="text" name="resCapacity">
	<button type="submit">등록!</button>
</form>

</body>
</html>