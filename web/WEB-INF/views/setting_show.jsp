<%-- 
    Document   : setting_show
    Created on : 21.07.2015, 12:06:05
    Author     : Новый профиль
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jsp/head.jsp" %>

<%@include file="/WEB-INF/jsp/errors.jsp" %>

<h2>Настройки</h2>

<form class="form-horizontal" role="form" action="<c:url value="/setting/change" />" >
  <div class="form-group">
    <label class="col-sm-2 control-label"> Путь к файлам </label> 
    <div class="col-sm-10">
      <input type="text" class="form-control" name="filePath" value="${setting.filePath}" />
    </div>
  </div>

  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <input class="btn btn-default" type="submit" name="submit" value="Сохранить" />
    </div>
  </div>
</form>

<%@include file="/WEB-INF/jsp/bottom.jsp" %>
