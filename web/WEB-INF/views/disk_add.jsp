<%-- 
    Document   : disk_add
    Created on : 14.07.2015, 17:17:21
    Author     : Новый профиль
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/tags.jsp" %>

<%@include file="/WEB-INF/jsp/head.jsp" %>

<%@include file="/WEB-INF/jsp/menu.jsp" %>

<c:if test="${success}" > 
  <p class="bg-success standart-message">
    Диск успешно добавлен!
  <p>
  </c:if>

  <%@include file="/WEB-INF/jsp/errors.jsp" %>
 
<form action="<c:url value="/Disk/add" />" class="standart-form" > 
  <div class="form-group">
    <label for="nameInput"> Название диска: </label>
    <input type="text" name="name"  placeholder="Название диска" class="form-control" id="nameInput" />
  </div>
  <div class="form-group">
    <label for="description"> Описание диска: </label>
    <textarea name="description" class="form-control"> </textarea>
  </div>
  <input type="submit" name="submit" value="Добавить диск"  class="btn btn-default" />
</form>

<%@include file="/WEB-INF/jsp/bottom.jsp" %>
