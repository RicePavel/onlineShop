<%-- 
    Document   : product_change
    Created on : 19.07.2015, 19:10:32
    Author     : Новый профиль
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jsp/head.jsp" %>

<%@include file="/WEB-INF/jsp/errors.jsp" %>


<security:authorize url="/product/change" >
  <form action="<c:url value="/product/change" />" >
    Название: <input type="text" name="name" value="${product.name}" /> <br/>
    Описание: <textarea name="description">${product.description}</textarea> <br/>
    Цена: <input type="text" name="price" value="${product.price}" /> <br/>
    <input type="hidden" name="productId" value="${product.productId}" />
    <input type="hidden" name="categoryId" value="${param['categoryId']}" />
    <input type="submit" name="submit" value="Сохранить" />
  </form>
</security:authorize>

<%@include file="/WEB-INF/jsp/bottom.jsp" %>
