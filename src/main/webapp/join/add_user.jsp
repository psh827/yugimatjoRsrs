<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>회원가입</title>
  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
  <style>
  	.mb-31 {
	  text-align: center;
	  padding: 25px;
	  font-weight: bold;
  	}
    body {
      min-height: 100vh;
      background-color: linear-gradient(to left rgba(255,113,31,.8));
    }

    .input-form {
      max-width: 680px;
      margin-top: 280px;
      padding: 32px;
      background: #fff;
      -webkit-border-radius: 10px;
      -moz-border-radius: 10px;
      border-radius: 10px;
      -webkit-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
      -moz-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
      box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15)
    }
    .btn {
  	 background: linear-gradient(90deg, rgba(255,113,31,1) 0%, rgba(255,202,155,1) 100%);
     color: white;
     width: 300px;
     position: relative;
     left: 25%;
     }
  </style>
</head>
<body>
<header id="header">
      <jsp:include page="/incl/header.jsp"/>
   </header>
  <div class="container">
    <div class="input-form-backgroud row">
      <div class="input-form col-md-12 mx-auto">
        <h4 class="mb-31">회원가입</h4>
        <form class="validation-form" action="add_user.do" method="post">
          <div class="row">
            <div class="col-md-6 mb-3">
              <label for="name">이름</label>
              <input type="text" class="form-control" id="name" placeholder="이름" name="userName" required>
              <div class="invalid-feedback">
                이름을 입력해주세요.
              </div>
            </div>
             <div class="col-md-6 mb-3">
              <label for="id">아이디</label>
              <input type="text" class="form-control" id="id" placeholder="id" name="userId" required>
              <div class="invalid-feedback">
                아이디를 입력해주세요.
              </div>
            </div>
             <div class="col-md-6 mb-3">
              <label for="passwd">비밀번호</label>
              <input type="password" class="form-control" id="passwd" placeholder="비밀번호" name="passwd" required>
              <div class="invalid-feedback">
                비밀번호를 입력해주세요.
              </div>
            </div>
            <div class="col-md-6 mb-3">
              <label for="nickname">닉네임</label>
              <input type="text" class="form-control" id="nickname" placeholder="닉네임" name="nickname" required>
              <div class="invalid-feedback">
                닉네임을 입력해주세요.
              </div>
            </div>
          </div>
            <button class="btn" type="submit">가입 완료</button>
        </form>
      </div>
    </div>
    <footer class="my-3 text-center text-small">
      <p class="mb-1">&copy; 2022 YM</p>
    </footer>
  </div>
  <footer>
	<jsp:include page="/incl/footer.jsp"/>
</footer>
  <script>
    window.addEventListener('load', () => {
      const forms = document.getElementsByClassName('validation-form');

      Array.prototype.filter.call(forms, (form) => {
        form.addEventListener('submit', function (event) {
          if (form.checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation();
          }

          form.classList.add('was-validated');
        }, false);
      });
    }, false);
  </script>
  
</body>
</html>