<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
<link rel="stylesheet" href="./css/customize.css">
<style>
.mainCon {margin:0 auto;}
.writeTitle{
    width: 35%;
    margin: auto;
    margin-top: 30px;
    text-align: center;
    font-size: 30px;
    font-weight: bold;
    color: rgba(255,113,31,1);
}
.comment-first {
	width: 1000px;
	margin: 0 auto;
}
.writeForm{
    margin: auto;
    margin-top: 30px;
    text-align: left;
}

.writeForm input[type=text]{
    font-size: 20px;
    margin-bottom: 30px;
}
.text-box {
	vertical-align: middle;
	text-align: center;
}
.writeTextarea{
    width: 1000px;
    height: 500px;
    resize: none;
    background: #fff0de;
}

.writeBtn{
    width: 100%;
    margin-top: 20px;
    text-align: center;
    
}

.writeBtn input[type=submit], .writeBtn input[type=button]{
	width: 150px;
    font-size: 16px;
    background: linear-gradient(90deg, rgba(255,113,31,1) 0%, rgba(255,202,155,1) 100%);
    border: 1px solid white;
    height: 40px;
    border-radius: 10px;
}
</style>
</head>
<body>

<header id="header">
	<jsp:include page="/incl/admin_header.jsp"/>
</header>

	<section>
        <div class="mainCon">
            <div class="writeTitle">리뷰 쓰기</div>
        <div class="comment-first"><p id="com_writer" class="comment-Id"><span style="font-weight: bold;">아이디 :</span> ${userId}</p></div>
            <form class="writeForm" action="/rsrs/restaurant/review" method="post" accept-charset="utf-8">
                <div class="text-box">
                	<textarea class="writeTextarea" name="reviewText" placeholder="리뷰를 입력해주세요"  required></textarea>
                </div>
                <div class="writeBtn">
                <input type="submit" value="작성" style="margin-bottom : 30px; color : white;">&nbsp;&nbsp;&nbsp;&nbsp;
                </div>
            </form>
        </div>
    </section>
    
<footer>
	<jsp:include page="/incl/footer.jsp"/>
</footer>

<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>

</body>
</html>