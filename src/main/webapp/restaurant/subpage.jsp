<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
<link rel="stylesheet" href="../css/result_res.css">
<link rel="stylesheet" href="../css/customize.css">
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
.btn-like {
  color: transparent;
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
</style>
</head>
<body>
<header id="header">
		<jsp:include page="/incl/header.jsp"/>
	</header>
<div class="album py-5 bg-light">
    <div class="container">

      <div class="row">
        <div class="col-md-4">
          <div class="card mb-4 shadow-sm">
            <svg class="bd-placeholder-img card-img-top" width="1000px" height="500" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"/><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>
            <div class="card-body">
            	<div class="sub-name-title">
	            	<ul>
		            	<li class="resname-first">
		            		${restaurant.resName}
		            	</li>
		            	<li>
		            		${restaurant.location.regionName}
		            	</li>
		            	<li>
		            		${restaurant.resScore}
		            	</li>
		            	<li>
		            		${restaurant.foodType}
		            	</li>
		            	<li>
		            		${restaurant.foodPrice}
		            	</li>
		            	<li>
		            		${restaurant.resCapacity}
		            	</li>
	            	</ul>
            	</div>
            </div>
          </div>
        </div>
      </div>

  
  <form action="review" method="post">
<div id="form-commentInfo">
        <div id="comment-count">댓글 <span id="count">0</span></div>
        <input type="text" id="comment-input" placeholder="아이디" name="userId"><br>
        <input type="text" id="comment-input" placeholder="댓글을 입력해 주세요." name="reviewText">
        <input type="text" id="comment-input" placeholder="식당점수" name="recommandScore">
        <input type="submit" value="등록">
    </div>
    <div id=comments>

    </div>
</form>
<input type='button'
       class="btn-like"
       value='❤'/>
<div id='result'>0</div>

    <script src="index.js"></script>
    <script>
  	const btnLike = document.querySelector(".btn-like");
  	document.querySelector(".btn-like").addEventListener("click", function(){
  		const resultElement = document.getElementById('result');
  		console.log(btnLike)
  	  	// 현재 화면에 표시된 값
  	  	let number = resultElement.innerText;
  	  
  	  	// 더하기/빼기
  	 	 if(!btnLike.classList.contains("done")) {
  	 		btnLike.classList.add("done")
  	    	number = parseInt(number) + 1;
  	  	}else if(btnLike.classList.contains("done"))  {
  	  		btnLike.classList.remove("done")
	  	    number = parseInt(number) - 1;
  		  }
  	  	if(number >= 2) {
  		  	number = 1;
  	  	}else if(number <= -1)  {
  		  	number = 0;
  	  	}
  	  	// 결과 출력
  	  	resultElement.innerText = number;
  	 })
    </script>
    <script src="//code.jquery.com/jquery.min.js"></script>
<script>
$(".btn-like").click(function() {
	$(this).toggleClass("done");
})
  	const btnLike = document.querySelector(".btn-like");
  	document.querySelector(".btn-like").addEventListener("click", function(){
  		const resultElement = document.getElementById('result');
  		console.log(btnLike)
  	  	// 현재 화면에 표시된 값
  	  	let number = resultElement.innerText;
  	  
  	  	// 더하기/빼기
  	 	 if(!btnLike.classList.contains("done")) {
  	 		btnLike.classList.add("done")
  	    	number = parseInt(number) + 1;
  	  	}else if(btnLike.classList.contains("done"))  {
  	  		btnLike.classList.remove("done")
	  	    number = parseInt(number) - 1;
  		  }
  	  	if(number >= 2) {
  		  	number = 1;
  	  	}else if(number <= -1)  {
  		  	number = 0;
  	  	}
  	  	// 결과 출력
  	  	resultElement.innerText = number;
  	 })

</script>

<div class="container">
	<div class="row">
		<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
				<tr>
					<td align="left" bgcolor="beige">댓글</td>
				</tr>
				<tr>
					<div class="container">
						<div class="row">
							<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
								<c:forEach var="test" items="${reviewList}">
								<tr>	
									<td colspan="5" align="left">
									${test}
									</td>	
								</tr>
								</c:forEach>
							</table>			
						</div>
					</div>
				</tr>
		</table>
	</div>
</div>
      </div>
  </div>
  
</body>
</html>