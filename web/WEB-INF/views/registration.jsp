<%-- 
    Document   : registration
    Created on : 15.07.2015, 21:38:25
    Author     : Новый профиль
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/tags.jsp" %>

<%@include file="/WEB-INF/jsp/head.jsp" %>

<c:if test="${success}" >
  <p class="bg-success standart-message">
    Вы успешно зерегистрировались! Теперь вы можете <a href="<c:url value="/login.jsp" />">войти в систему.</a>
  <p>
  </c:if>

  <c:if test="${! success}">

    <%@include file="/WEB-INF/jsp/errors.jsp" %>

  <div>
    <form action="<c:url value="/User/registration" />" role="form" class="standart-form" > 
      <div class="form-group">
        <label for="loginInput"> Логин* </label>
        <input type="text" name="login" placeholder="Логин" class="form-control" id="loginInput" /> 
      </div>

      <div class="form-group">
        <label for="passwordInput1">Пароль* </label>
        <input type="password" name="password" placeholder="Пароль" class="form-control" id="passwordInput1"/> 
      </div>

      <div class="form-group">
        <label for="passwordInput2">Повторите пароль*</label>
        <input type="password" name="password2" placeholder="Повторите пароль" class="form-control" id="passwordInput2"/> 
      </div>

      <div class="form-group">
        <label for="nameInput">Имя*</label>
        <input type="text" name="name" placeholder="Имя" class="form-control" id="nameInput"/> 
      </div>

      <div class="form-group">
        <label for="surnameInput">Фамилия*</label>
        <input type="text" name="surname" placeholder="Фамилия" class="form-control" id="surnameInput"/> 
      </div>

      <input type="submit" name="submit" value="Регистрироваться" class="btn btn-default" />
    </form>
  </div>
</c:if>

<%@include file="/WEB-INF/jsp/bottom.jsp" %>
