<%-- 
    Document   : loginpage
    Created on : Jun 19, 2015, 1:00:45 PM
    Author     : awal
--%>
<%@include file="support/header.jsp" %>

<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="../../css/login.css"/>
  </head>
<body>
  <div class="container login-form-style">
    <div class="row">
      <form class="form-signin" method="POST" action="../../apps/auth/security">
        <h3 class="form-signin-heading">Please sign in to continue:</h3>
        <img src="../../img/user.png" alt="user"/>
        <div>
          <label for="j_username" class="sr-only">Username</label>
          <input type="text" name="j_username" class="form-control" placeholder="Username" required autofocus>
          <label for="j_password" class="sr-only">Password</label>
          <input type="password" name="j_password" class="form-control" placeholder="Password" required>
          <span class="error-login">${error}</span>
        </div>
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> Remember me
          </label>
        </div>
        <input class="btn btn-lg btn-primary btn-block" type="submit" value="Sign in" />
      </form>
    </div>
  </div>
</body>
</html>