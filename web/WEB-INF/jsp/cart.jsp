<%-- 
    Document   : cart
    Created on : 19.07.2015, 19:18:00
    Author     : Новый профиль
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jsp/tags.jsp" %>

<div> 
  Моя корзина: <br/>
  Товаров: ${cartInfo.count} <br/>
  Сумма: ${cartInfo.summ} <br/>
  <a href="<c:url value="/cart/show" />">Оформить заказ</a>
</div>
