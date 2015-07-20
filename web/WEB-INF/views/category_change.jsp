<%-- 
    Document   : category_change
    Created on : 19.07.2015, 18:57:03
    Author     : Новый профиль
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%@include file="/WEB-INF/jsp/head.jsp" %>

<%@include file="/WEB-INF/jsp/errors.jsp" %>

<security:authorize url="/category/change" >
  <form class="form-horizontal" action="<c:url value="/category/change" />"> 
    <div class="form-group">
      <label class="col-sm-2 control-label">Название:</label>
      <div class="col-sm-10">
        <input class="form-control" type="text" name="name" value="${category.name}" />
      </div>
    </div>
    <input type="hidden" name="categoryId" value="${category.categoryId}" />

    <div class="form-group">
      <div class="col-sm-offset-2 col-sm-10">
        <input class="btn btn-default" type="submit" name="submit" value="Сохранить" />
      </div>
    </div>
  </form>
</security:authorize>


<%@include file="/WEB-INF/jsp/bottom.jsp" %>
