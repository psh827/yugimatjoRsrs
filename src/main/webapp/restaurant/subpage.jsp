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
.container {width:1000px; margin:0 auto; padding-top:0;}
.card-body {display:flex;text-align: center;height:100%;}
.sub-name-title,.sub-name-input { padding: 20px; display: flex; flex-direction: column;}
.sub-name-title {font-size: 20px;text-align: center; padding-right:20px; margin:0 auto;}
.sub-name-title ul li {padding-bottom:20px; margin-bottom: 10px;}
.sub-name-input {margin-top:12px;}
.sub-input-text{margin:5px;height: 20px;}
.resname-first{font-size:30px; font-weight:bold;}

.comment{width:100%; height:100px; margin-bottom:5px; background:#fff1db;}
.comment-Id{padding-top: 20px; margin-bottom: -5px; display: inline-block;margin-right: 20px;}
.comment-list-title{width:100%;}
.btn-like {
  color: transparent;
  border:none;
  background:none;
  text-shadow: 0 0 2px rgba(255,255,255,.7), 0 0 0 #000;
}
.btn-like:hover {
  text-shadow: 0 0 0 #ea0;
}
.btn-like.done {
  color: red;
  text-shadow: 0;
}
.btn-like.done:hover {
  color: red;
  text-shadow: 0 0 0 #777;
}
#map {
  height: 500px;
}
.resInfo{
font-weight: bold;
color: #FF853A;
}
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


<a class="btn btn-primary" href="#" role="button">Link</a>
<button class="btn btn-primary" type="submit">Button</button>
<input class="btn btn-primary" type="button" value="Input">
<input class="btn btn-primary" type="submit" value="Submit">
<input class="btn btn-primary" type="reset" value="Reset">
  


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
      src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDnHdrBDEvUp0BRbUiKE1vhmWclLRRZ400&callback=initMap&v=weekly"
      defer
    ></script>
<script>
	var lng = "${lng}"
	var lat = "${lat}"
	var resName = "${resName}"
	lng = Number(lng);
	lat = Number(lat);
	console.log(lng);
	console.log(lat);
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