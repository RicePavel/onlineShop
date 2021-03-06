<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jsp/head.jsp" %>

<%@include file="/WEB-INF/jsp/errors.jsp" %>

<form action="<c:url value="/report/category" />">
  <input type="text" name="date_from" class="date" value="${dateFrom}" />
  <input type="text" name="date_to" class="date" value="${dateTo}" />
  <input type="submit" name="submit" value="ОК" />
</form>

<h2>Отчет по покупкам</h2>

<script type="text/javascript">
  google.load("visualization", "1", {packages: ["corechart"]});
  google.setOnLoadCallback(drawChart);
  function drawChart() {
    var data = google.visualization.arrayToDataTable([
      ['Category', 'summ'],
  ${jsonString}
    ]);

    var options = {
      title: 'Суммы заказов',
      is3D: true,
    };

    var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
    chart.draw(data, options);
  }
</script>

<div id="piechart_3d" style="height: 300px;" > </div>

<table class="table"> 
  <tr>
    <th> Категория </th>
    <th> Количество </th>
    <th> Сумма </th>
  </tr>
  <c:forEach items="${list}" var="arr">
    <c:set var="category" value="${arr[0]}" />
    <tr>
      <td> <a href="<c:url value="/report/product?categoryId=${category.categoryId}&dateFrom=${dateFrom}&dateTo=${dateTo}" />"> ${category.name} </a> </td>
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
