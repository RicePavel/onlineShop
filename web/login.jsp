<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jsp/head.jsp" %>

    <c:if test="${not empty param.error}">
      <font color="red"> Ошибка:
        : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message} </font>
      </c:if>

    <form method="POST" action="<c:url value="/j_spring_security_check" />" class="standart-form" role="form" > 

      <div class="form-group">
        <label for="UserLogin_username">Логин*</label>
        <input class="form-control" tabindex="1" placeholder="Логин" required="required" name="j_username" id="UserLogin_username" type="text">
      </div>

      <div class="form-group">
        <label for="UserLogin_password">Пароль*</label>
        <input class="form-control" tabindex="2" placeholder="PASSWORD *" required="required" name="j_password" id="UserLogin_password" type="password">
      </div>
      <input class="btn btn-default" type="submit" tabindex="4" name="oversub" value="Войти">

    </form>

  <%@include file="/WEB-INF/jsp/bottom.jsp" %>