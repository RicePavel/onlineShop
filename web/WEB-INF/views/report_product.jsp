<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jsp/head.jsp" %>

<%@include file="/WEB-INF/jsp/errors.jsp" %>

<form action="<c:url value="/report/product" />">
  <input type="text" name="date_from" class="date" value="${dateFrom}" />
  <input type="text" name="date_to" class="date" value="${dateTo}" />
  
  <select name="categoryId">
    <c:forEach items="${categoryList}" var="cat">
      <option value="${cat.categoryId}" ${cat.categoryId == param['categoryId'] ? 'selected' : ''} >${cat.name}</option>
    </c:forEach>
  </select>
  
  <input type="submit" name="submit" value="ОК" />
</form>

<h2>Отчет по покупкам по категории ${category.name} </h2>

<table class="table" > 
  <tr>
    <th> Товар </th>
    <th> Количество </th>
    <th> Сумма </th>
  </tr>
  <c:forEach items="${list}" var="arr">
    <c:set var="product" value="${arr[0]}" />
    <tr>
      <td>${product.name}</td>
      <td>${arr[2]}</td>
      <td><fmt:formatNumber value="${arr[1]}" pattern="###.##" minFractionDigits="2" maxFractionDigits="2" minIntegerDigits="1" /> р. </td>
    </tr>
  </c:forEach>
  <tr>
    <td>Итого</td>
    <td>${totalCount}</td>
    <td><fmt:formatNumber value="${totalSumm}" pattern="###.##" minFractionDigits="2" maxFractionDigits="2" minIntegerDigits="1" /> р. </td>
  </tr>
</table>



<%@include file="/WEB-INF/jsp/bottom.jsp" %>
