<%-- 
    Document   : product_change
    Created on : 19.07.2015, 19:10:32
    Author     : Новый профиль
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jsp/head.jsp" %>

<%@include file="/WEB-INF/jsp/errors.jsp" %>


<security:authorize url="/product/change" >
  <form class="form-horizontal" role="form" action="<c:url value="/product/change" />" >
    <div class="form-group">
      <label class="col-sm-2 control-label">Название:</label>
      <div class="col-sm-10">
        <input class="form-control" type="text" name="name" value="${product.name}" />
      </div>
    </div>

    <div class="form-group">
      <label class="col-sm-2 control-label">Описание:</label>
      <div class="col-sm-10">
        <textarea class="form-control" name="description">${product.description}</textarea>
      </div>
    </div>

    <div class="form-group">
      <label class="col-sm-2 control-label">Цена:</label>
      <div class="col-sm-10">
        <input class="form-control" type="text" name="price" value="${product.price}" />
      </div>
    </div>

    <input type="hidden" name="productId" value="${product.productId}" />
    <input type="hidden" name="categoryId" value="${param['categoryId']}" />

    <div class="form-group">
      <div class="col-sm-offset-2 col-sm-10">
        <input class="btn btn-default" type="submit" name="submit" value="Сохранить" />
      </div>
    </div>
  </form>
</security:authorize>

<%@include file="/WEB-INF/jsp/bottom.jsp" %>
