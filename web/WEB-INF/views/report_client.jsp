<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jsp/head.jsp" %>

<%@include file="/WEB-INF/jsp/errors.jsp" %>

<form action="<c:url value="/report/client" />">
  <input type="text" name="date_from" class="date" value="${dateFrom}" />
  <input type="text" name="date_to" class="date" value="${dateTo}" />
  
  <input type="submit" name="submit" value="ОК" />
</form>

<h2>Отчет по клиентам </h2>

<table class="table" > 
  <tr>
    <th> Клиент </th>
    <th> Количество </th>
    <th> Сумма </th>
  </tr>
  <c:forEach items="${list}" var="arr">
    <c:set var="email" value="${arr[0]}" />
    <tr>
      <td>  <a href="<c:url value="/order/search?email=${email}" />">${email}</a> </td>
      <td>${arr[2]}</td>
      <td><fmt:formatNumber value="${arr[1]}" pattern="###.##" minFractionDigits="2" maxFractionDigits="2" minIntegerDigits="1" /> р.</td>
    </tr>
  </c:forEach>
  <tr>
    <td>Итого</td>
    <td>${totalCount}</td>
    <td><fmt:formatNumber value="${totalSumm}" pattern="###.##" minFractionDigits="2" maxFractionDigits="2" minIntegerDigits="1" /> р.</td>
  </tr>
</table>

<%@include file="/WEB-INF/jsp/bottom.jsp" %>
