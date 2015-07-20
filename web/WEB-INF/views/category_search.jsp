<%-- 
    Document   : category_search
    Created on : 19.07.2015, 18:47:57
    Author     : Новый профиль
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jsp/head.jsp" %>

<%@include file="/WEB-INF/jsp/errors.jsp" %>


<security:authorize url="/category/add" >
  <form class="form-horizontal" action="<c:url value="/category/add" />"> 

    <div class="form-group">
      <label class="col-sm-2 control-label">Название:</label>
      <div class="col-sm-10">
        <input class="form-control" type="text" name="name" value="${param['name']}" />
      </div>
    </div>

    <div class="form-group">
      <div class="col-sm-offset-2 col-sm-10">
        <input class="btn btn-default" type="submit" name="submit" value="Добавить" />
      </div>
    </div>
  </form>
</security:authorize>

<table class="table table-bordered" >
  <c:forEach items="${list}" var="category"> 
    <tr>
      <td>${category.name}</td>
      <td> 
        <security:authorize url="/category/delete" >
          <a onclick="return confirm('подтвердите удаление');" href="<c:url value="/category/delete?categoryId=${category.categoryId}" />">Удалить</a> 
        </security:authorize>
      </td>
      <td>
        <security:authorize url="/category/change" >
          <a  href="<c:url value="/category/change?categoryId=${category.categoryId}" />">Изменить</a> 
        </security:authorize>
      </td>
    </tr>
  </c:forEach>
</table>

<%@include file="/WEB-INF/jsp/bottom.jsp" %>
