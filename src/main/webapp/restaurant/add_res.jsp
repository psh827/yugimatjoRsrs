<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>식당 등록하기</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
<link rel="stylesheet" href="./css/customize.css">
</head>
<body>

<header id="header">
	<jsp:include page="/incl/header.jsp"/>
</header>

<form action="add_res.do" method="post">
	<div class="container filteringbox" style="background : none; margin-bottom : 150px;">
		<div class="container filteringbox">
			<div class="row">
				식당이름: <input type="text" name="resName" style="margin :0 15px;">
				위치: <input type="text" name="resLocation" style="margin :0 15px;">
				평점: <input type="text" name="resScore" style="margin :0 15px;">				
			</div>
			<div class="row">
				음식종류: <input type="text" name="foodType" style="margin :0 15px;">
				가격: <input type="text" name="foodPrice" style="margin :0 15px;">
				인원: <input type="text" name="resCapacity" style="margin :0 15px;">			
			</div>
			<div class="row">
				<button type="submit">등록!</button>
			</div>
		</div>
	</div>
</form>


<footer>
	<jsp:include page="/incl/footer.jsp"/>
</footer>


<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>
</body>
</html>