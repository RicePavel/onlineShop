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
    <tr>
      <td>${arr[0]}</td>
      <td>${arr[2]}</td>
      <td>${arr[1]}</td>
    </tr>
  </c:forEach>
  <tr>
    <td>Итого</td>
    <td>${totalCount}</td>
    <td>${totalSumm}</td>
  </tr>
</table>

<%@include file="/WEB-INF/jsp/bottom.jsp" %>
