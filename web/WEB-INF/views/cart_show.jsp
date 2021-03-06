<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/head.jsp" %>
<%@include file="/WEB-INF/jsp/errors.jsp" %>

<table class="table" >
  <tr>
    <th>
      Название
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
        <fmt:formatNumber value="${item.product.price}" pattern="###.##" minFractionDigits="2" maxFractionDigits="2" minIntegerDigits="1" />
      </td>
      <td>
        <a type="button" class="btn btn-default" href="<c:url value="/cart/minus?productId=${item.product.productId}" />" >-</a>
        <span type="button" class="btn btn-default> "> ${item.quantity} </span>
        <a type="button" class="btn btn-default" href="<c:url value="/cart/plus?productId=${item.product.productId}" />" >+</a>
      </td>
      <td>
        <fmt:formatNumber value="${item.summ}" pattern="###.##" minFractionDigits="2" maxFractionDigits="2" minIntegerDigits="1" />
      </td>
      <td>
        <a href="<c:url value="/cart/delete?productId=${item.product.productId}" />" >Удалить</a>
      </td>
    </tr>
  </c:forEach>
  <tr>
    <td colspan="3">Итого</td>
    <td>
      <fmt:formatNumber value="${cartInfo.summ}" pattern="###.##" minFractionDigits="2" maxFractionDigits="2" minIntegerDigits="1" />
    </td>
    <td></td>
  </tr>
</table>

<c:if test="${notEmpty}">
  <form class="form-horizontal" action="<c:url value="/cart/makeOrder" />">

    <div class="form-group">
      <label class="col-sm-2 control-label">Email:</label>
      <div class="col-sm-10">
        <input class="form-control" type="email" name="email" value="${param['email']}" required />
      </div>
    </div>

    <div class="form-group">
      <label class="col-sm-2 control-label">ФИО:</label>
      <div class="col-sm-10">
        <input class="form-control" type="text" name="fio" value="${param['fio']}" required />
      </div>
    </div>

    <div class="form-group">
      <label class="col-sm-2 control-label">Адрес: </label>
      <div class="col-sm-10">
        <input class="form-control" type="address" name="address" value="${param['address']}" required />
      </div>
    </div>

    <div class="form-group">
      <div class="col-sm-offset-2 col-sm-10">
        <input class="btn btn-success" type="submit" name="submit" value="Оформить заказ" />
      </div>
    </div>

  </form>
</c:if>

<%@include file="/WEB-INF/jsp/bottom.jsp" %>