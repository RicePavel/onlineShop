<%-- 
    Document   : order_search
    Created on : 19.07.2015, 18:59:09
    Author     : Новый профиль
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jsp/head.jsp" %>

<%@include file="/WEB-INF/jsp/errors.jsp" %>


<c:forEach items="${list}" var="order" > 
  <h3> ${order.fio} ${order.email} ${order.address} <fmt:formatDate value="${order.createDate}" pattern="dd.MM.yyyy HH:mm" /> </h3>
  <table class="table table-bordered" >
    <tr>
      <td>Название</td>
      <td>Цена</td>
      <td>Количество</td>
      <td>Сумма</td>
    </tr>
  <c:forEach items="${order.orderItemList}" var="item">
    <tr>
      <td>${item.product.name}</td>
      <td>${item.price}</td>
      <td>${item.quantity}</td>
      <td>${item.summPrice} р.</td>
    </tr>
  </c:forEach>
    <tr>
      <td colspan="2">Итого</td>
      <td>${order.totalQuantity}</td>
      <td>${order.totalSummPrice} р.</td>
    </tr>
  </table>
</c:forEach>

<%@include file="/WEB-INF/jsp/bottom.jsp" %>
