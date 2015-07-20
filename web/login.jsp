<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/tags.jsp" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <%@include file="/WEB-INF/jsp/js_css.jsp" %>
  </head>
  <body>

    <c:if test="${not empty param.error}">
      <font color="red"> Ошибка:
      : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message} </font>
    </c:if>

    <form method="POST" action="<c:url value="/j_spring_security_check" />" class="form-signin login-form" role="form" > 
      <h2 class="form-signin-heading">Авторизуйтесь</h2>

      <input class="form-control" tabindex="1" placeholder="Логин" required="required" name="j_username" id="UserLogin_username" type="text">
      <input class="form-control" tabindex="2" placeholder="Пароль" required="required" name="j_password" id="UserLogin_password" type="password">
      <input class="btn btn-lg btn-primary btn-block" type="submit" tabindex="4" name="oversub" value="Войти">

    </form>

  </body>
</html>