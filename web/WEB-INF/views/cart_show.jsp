<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/head.jsp" %>
<%@include file="/WEB-INF/jsp/errors.jsp" %>

<table>
  <tr>
    <th>
      Название
    </th>
    <th>
      Описание
    </th>
    <th>
      Цена
    </th>
    <th>
      Количество
    </th>
    <th>
      Сумма
    </th>
    <th>
      
    </th>
  </tr>
<c:forEach items="${cartInfo.items}" var="item" > 
  <tr>
    <td>
      ${item.product.name}
    </td>
    <td>
      ${item.product.description}
    </td>
    <td>
      ${item.product.price}
    </td>
    <td>
      ${item.quantity}
    </td>
    <td>
      ${item.summ}
    </td>
    <td>
      <a href="<c:url value="/cart/delete?productId=${item.product.productId}" />" >Удалить</a>
      <a href="<c:url value="/cart/minus?productId=${item.product.productId}" />" >Минус</a>
      <a href="<c:url value="/cart/plus?productId=${item.product.productId}" />" >Плюс</a>
    </td>
  </tr>
</c:forEach>
</table>

<c:if test="${notEmpty}">
  <form action="<c:url value="/cart/makeOrder" />">
    Email: <input type="text" name="email" value="${param['email']}" /> <br/>
    ФИО: <input type="text" name="fio" value="${param['fio']}" /> <br/>
    Адрес: <input type="address" name="address" value="${param['address']}" /> <br/>
    <input type="submit" name="submit" value="Оформить заказ" />
  </form>
</c:if>

<%@include file="/WEB-INF/jsp/bottom.jsp" %>