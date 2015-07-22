<%-- 
    Document   : cart
    Created on : 19.07.2015, 19:18:00
    Author     : Новый профиль
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jsp/tags.jsp" %>

<security:authorize url="/cart/show">
  <div style="
       float: right;
       font-size: 16px;
       "> 
    Моя корзина: <br/>
    Товаров: <span id="cartInfoCount">${cartInfo.count}</span> <br/>
    Сумма: <span id="cartInfoSumm">${cartInfo.summ}</span> р. <br/>
    <a type="button" class="btn btn-success" href="<c:url value="/cart/show" />">Оформить заказ</a>
  </div>
</security:authorize>
