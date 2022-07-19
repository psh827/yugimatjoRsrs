<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="container-fluid">
      <nav class="navbar navbar-light bg-primary">

        <div class="col-sm">
             <a class="navbar-brand" href="/rsrs/main/main.jsp"><img src="./img/logo/yugi.png" alt="logo">YUGIMATJO</a>         
        </div>
        <div class="col col-md-4">
        </div>
        <div class="col-sm">
          <nav class="nav justify-content-end ">
            <a class="nav-link text-white" href="#">#앙비앙스</a>
            <a class="nav-link text-white" href="#">#분위기 맛집</a>
          </nav>
        </div>

      </nav>
      <ul class="nav border-bottom shadow-sm p-3 bg-white rounded" style="align-items:center; justify-content: space-around;">
        <li class="nav-item dropdown">
          <a class="nav-link text-dark dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-expanded="false">맛집 찾기</a>
          <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
            <a class="dropdown-item" href="#">편안한</a>
            <a class="dropdown-item" href="#">럭셔리한</a>
            <a class="dropdown-item" href="#">데이트하기 좋은</a>
            <a class="dropdown-item" href="#">가성비 좋은</a>
            <a class="dropdown-item" href="#">단체가능</a>
          </div>
        </li>
        <%if(session.getAttribute("userId")==null){ %>
        <li class="nav-item">
          <a class="nav-link text-dark" href="/rsrs/login/login">마이페이지</a>
        </li>
        <li class="nav-item">
          <a class="nav-link text-dark" href=" /rsrs/login/login">로그인</a>
        </li>
        <li class="nav-item">
          <a class="nav-link color-primary" href="/rsrs/join/add_user.jsp">회원가입</a>
        </li>
        <%}else{ %>
        <li class="nav-item">
          <a class="nav-link text-dark" href="/rsrs/login/mypage.jsp">마이페이지</a>
        </li>
        <li class="nav-item">
           <%=session.getAttribute("nickName") %>님 환영합니다!
        </li>
        <li class="nav-item">
          <a class="nav-link color-primary" href="/rsrs/login/logout">로그아웃</a>
        </li>
        <%} %>
        
        <!-- Button trigger modal  200,400,600 -->
      <button type="button"  style="background-color: #ffb562; border: 1px solid; border-color: blanchedalmond; border-radius: 5px; padding: 5px; " class="btn-primary" data-toggle="modal" data-target="#exampleModal">
           알 보기
      </button>
      <!-- Modal -->
      <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
           <h5 class="modal-title" id="exampleModalLabel">나의 알은?</h5>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
             <span aria-hidden="true">&times;</span>
           </button>
            </div>
            <div class="modal-body" style="text-align: center;">
              <div>point : 0 ~ 200점</div>
               <img class="egg1" alt="egg1" style="width:80px; margin:30px 0;" src="../img/egg/egg1.png"><br>
               <p>메추리알</p>
              <div>point : 200점 ~ 400점</div> 
              <img class="egg1" alt="egg2" style="width:80px; margin:30px 0;" src="../img/egg/egg2.png"><br>
              <p>계란</p>
              <div>point : 400점 ~ 600점</div>
               <img class="egg1" alt="egg3" style="width:80px; margin:30px 0;" src="../img/egg/egg3.png"><br>
               <p>타조알</p>
              <div>point : 600점 ~</div> 
              <img class="egg1" alt="egg4" style="width:80px; margin:30px 0;" src="../img/egg/egg4.png"><br>
              <p>독수리알</p>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn-secondary" data-dismiss="modal">Close</button>
               </div>
          </div>
          </div>
      </div>
      </ul>
    </div>