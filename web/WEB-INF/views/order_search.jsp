<%-- 
    Document   : order_search
    Created on : 19.07.2015, 18:59:09
    Author     : Новый профиль
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jsp/head.jsp" %>

<%@include file="/WEB-INF/jsp/errors.jsp" %>


<c:forEach items="${list}" var="order" > 
  <div> ${order.fio} ${order.email} ${order.address} </div>
  <table>
  <c:forEach items="${orderItemList}" var="item">
    <tr>
      <td>${item.product.name}</td>
      <td>${item.price}</td>
      <td>${item.quantity}</td>
    </tr>
  </c:forEach>
  </table>
</c:forEach>

<%@include file="/WEB-INF/jsp/bottom.jsp" %>
