<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
<link rel="stylesheet" href="../css/customize.css">
<link rel="stylesheet" href="../css/result_res.css">
<style>
*{magin:0;padding:0;}
ul,li { list-style: none;}
.container {width:1000px; margin:0 auto;}
.card-body {display:flex;text-align: center;height:100%;}
.sub-name-title,.sub-name-input { padding: 20px; display: flex; flex-direction: column;}
.sub-name-title {font-size: 20px;text-align: center; padding-right:20px; margin:0 auto;}
.sub-name-title ul li {padding-bottom:20px;}
.sub-name-input {margin-top:12px;}
.sub-input-text{margin:5px;height: 20px;}
.resname-first{font-size:30px; font-weight:bold;}

.comment{width:100%; height:100px; margin-bottom:5px; background:#fff1db;}
.comment-Id{padding-top: 20px; margin-bottom: -5px; display: inline-block;margin-right: 20px;}
.comment-list-title{width:100%;}

.btn-primary {
background: linear-gradient(90deg, rgba(2,0,36,1) 0%, rgba(255,174,13,1) 0%, rgba(255,154,59,1) 100%, rgba(255,222,58,1) 100%);
border:none;
}
#myform input[type=radio]{
    display: none; /* 라디오박스 감춤 */
}
#myform label{
    font-size: 3em; /* 이모지 크기 */
    color: transparent; /* 기존 이모지 컬러 제거 */
    text-shadow: 0 0 0 #f0f0f0; /* 새 이모지 색상 부여 */
}
#myform label:hover, #myform label:hover ~ label{
    text-shadow: 0 0 0 yellow; /* 마우스 호버 뒤에오는 이모지들 */
}
#myform1 label:hover ~ label{
    text-shadow: 0 0 0 yellow; /* 마우스 호버 뒤에오는 이모지들 */
}
#myform .am-star.on {display: inline-block;}
#myform .am-star{
    display: none; /* 하위 별점 이미지들이 있는 영역만 자리를 차지함.*/
    direction: rtl; /* 이모지 순서 반전 */
    border: 0; /* 필드셋 테두리 제거 */
}
#myform .am-star input[type=radio]:checked ~ label{
    text-shadow: 0 0 0 yellow; /* 마우스 클릭 체크 */
}
.add-review-btn-box{margin-top: 50px;}
.add-review-btn {
	width: 500px;
    height: 50px;
	background: #ffb459;
    border: 1px solid #ffc68c;
    border-radius: 10px;
}
.add-review-btn a {color:white;}
</style>

</head>
<body>
<header id="header">
      <jsp:include page="/incl/header.jsp"/>
   </header>
<div class="album py-5 bg-light">
    <div class="container">
      <div class="row">
        <div class="col">
          <div class="card mb-4 shadow-sm">
            <div id="map"></div>
            <div class="card-body">
               <div class="sub-name-title">
                  <ul>
                     <li class="resname-first">
                     	<p class="resInfo">식당이름</p>
                        ${restaurant.resName}
                     </li>
                     <li>
                     	<p class="resInfo">식당주소</p>
                        ${restaurant.location.regionName}
                     </li>
                     <li>
                     	<p class="resInfo">식당평점</p>
                        ${restaurant.resScore}
                     </li>
                     <li>
                     	<p class="resInfo">음식종류</p>
                        ${restaurant.foodType}
                     </li>
                     <li>
                     	<p class="resInfo">예상가격</p>
                        ${restaurant.foodPrice}
                     </li>
                     <li>
                     	<p class="resInfo">최대인원</p>
                        ${restaurant.resCapacity}
                     </li>
                  </ul>
               </div>
            </div>
          </div>
        </div>
      </div>


<button class="btn btn-primary" type="submit">편안한</button>
<form name="myform" id="myform" method="post" action="./save">
    <fieldset class="am-star">
        <input type="radio" name="rating" value="5" id="rate1"><label for="rate1">⭐</label>
        <input type="radio" name="rating" value="4" id="rate2"><label for="rate2">⭐</label>
        <input type="radio" name="rating" value="3" id="rate3"><label for="rate3">⭐</label>
        <input type="radio" name="rating" value="2" id="rate4"><label for="rate4">⭐</label>
        <input type="radio" name="rating" value="1" id="rate5"><label for="rate5">⭐</label>
    </fieldset>
</form>
<button class="btn btn-primary" type="submit">럭셔리한</button>
<form name="myform" id="myform" method="post" action="./save">
    <fieldset class="am-star">
        <input type="radio" name="rating" value="5" id="rate6"><label for="rate6">⭐</label>
        <input type="radio" name="rating" value="4" id="rate7"><label for="rate7">⭐</label>
        <input type="radio" name="rating" value="3" id="rate8"><label for="rate8">⭐</label>
        <input type="radio" name="rating" value="2" id="rate9"><label for="rate9">⭐</label>
        <input type="radio" name="rating" value="1" id="rate10"><label for="rate10">⭐</label>
    </fieldset>
</form>
<button class="btn btn-primary" type="submit">데이트하기 좋은</button>
<form name="myform" id="myform" method="post" action="./save">
    <fieldset class="am-star">
        <input type="radio" name="rating" value="5" id="rate11"><label for="rate11">⭐</label>
        <input type="radio" name="rating" value="4" id="rate12"><label for="rate12">⭐</label>
        <input type="radio" name="rating" value="3" id="rate13"><label for="rate13">⭐</label>
        <input type="radio" name="rating" value="2" id="rate14"><label for="rate14">⭐</label>
        <input type="radio" name="rating" value="1" id="rate15"><label for="rate15">⭐</label>
    </fieldset>
</form>
<button class="btn btn-primary" type="submit">가성비 좋은</button>
<form name="myform" id="myform" method="post" action="./save">
    <fieldset class="am-star">
        <input type="radio" name="rating" value="5" id="rate16"><label for="rate16">⭐</label>
        <input type="radio" name="rating" value="4" id="rate17"><label for="rate17">⭐</label>
        <input type="radio" name="rating" value="3" id="rate18"><label for="rate18">⭐</label>
        <input type="radio" name="rating" value="2" id="rate19"><label for="rate19">⭐</label>
        <input type="radio" name="rating" value="1" id="rate20"><label for="rate20">⭐</label>
    </fieldset>
</form>
<button class="btn btn-primary" type="submit">단체가능</button>
<form name="myform" id="myform" method="post" action="./save">
    <div class="am-star">
        <input type="radio" name="rating" value="5" id="rate21"><label for="rate21">⭐</label>
        <input type="radio" name="rating" value="4" id="rate22"><label for="rate22">⭐</label>
        <input type="radio" name="rating" value="3" id="rate23"><label for="rate23">⭐</label>
        <input type="radio" name="rating" value="2" id="rate24"><label for="rate24">⭐</label>
        <input type="radio" name="rating" value="1" id="rate25"><label for="rate25">⭐</label>
    </div>
</form>
  
<div class="add-review-btn-box">
	<button class="add-review-btn"><a href="/rsrs/restaurant/review?resName=${restaurant.resName}">리뷰쓰기</a></button>
</div>

<div class="container-list">
      <table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
            <tr>
               <td class="comment-list-title" align="left" bgcolor="beige">댓글</td>
            </tr>
            <c:forEach var="text" items="${reviewList}">
            <tr>
            	<td>${text.getReviewText()}</td>
            </tr>
            </c:forEach>
      </table>

</div>
      </div>
  </div>
  
<script
      src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDnHdrBDEvUp0BRbUiKE1vhmWclLRRZ40&callback=initMap&v=weekly"
      defer
    ></script>
<script>
	var lng = "${lng}"
	var lat = "${lat}"
	var resName = "${resName}"
	lng = Number(lng);
	lat = Number(lat);
    function initMap() {
      var seoul = { lat: lat, lng: lng};
      var map = new google.maps.Map(
        document.getElementById('map'), {
          zoom: 18,
          center: seoul
        });
      new google.maps.Marker({
    	    position: seoul,
    	    map: map,
    	    label: resName
    	});
      
    }
 </script>

</body>
</html>