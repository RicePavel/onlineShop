<%-- 
    Document   : product_search
    Created on : 19.07.2015, 19:03:22
    Author     : Новый профиль
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jsp/head.jsp" %>

<%@include file="/WEB-INF/jsp/errors.jsp" %>

<h2>${category.name}</h2>

<security:authorize url="/product/add" >
  <form action="<c:url value="/product/add" />" >
    Название: <input type="text" name="name" value="${param['name']}" /> <br/>
    Описание: <textarea name="description" >${param['description']}</textarea> <br/>
    Цена: <input type="text" name="price" value="${param['price']}" /> <br/>
    <input type="hidden" name="categoryId" value="${param['categoryId']}" >
    <input type="submit" name="submit" value="Добавить" />
  </form>
</security:authorize>

<c:forEach items="${list}" var="product" > 
  <div> ${product.name} ${product.description} ${product.price}
    <security:authorize url="/product/delete" >
      <a href="<c:url value="/product/delete?productId=${product.productId}&categoryId=${product.category.categoryId}" />">Удалить</a>
    </security:authorize>
    <security:authorize url="/product/change" >
      <a href="<c:url value="/product/change?productId=${product.productId}&categoryId=${product.category.categoryId}" />">Изменить</a>
    </security:authorize>
    <security:authorize url="/cart/addProduct" >
      <a href="<c:url value="/cart/addProduct?productId=${product.productId}&categoryId=${product.category.categoryId}" />">Добавить в корзину</a>
    </security:authorize>
  </div>
</c:forEach>

<%@include file="/WEB-INF/jsp/bottom.jsp" %>

