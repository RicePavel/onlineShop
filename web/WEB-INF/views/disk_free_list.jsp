<%-- 
    Document   : disk_free_list
    Created on : 14.07.2015, 17:23:38
    Author     : Новый профиль
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/tags.jsp" %>

<%@include file="/WEB-INF/jsp/head.jsp" %>

<%@include file="/WEB-INF/jsp/menu.jsp" %>

<h2 class="standart-head">Свободные диски</h2>

<div class="standart-div">
  <table class="table"> 
    <tr>
      <th>Диск</th>
      <th>Описание</th>
      <th>Владелец</th>
      <th></th>
    </tr>
    <c:forEach items="${diskList}" var="disk" >
      <tr>
        <td>${disk.name}</td>
        <td>${disk.description}</td>
        <td>${disk.owner.surname} ${disk.owner.name}</td>
        <td> <a сlass="btn btn-default" role="button" href="<c:url value="/Disk/take?diskId=${disk.diskId}" />">Взять</a> </td>
      </tr>
    </c:forEach>
  </table>
</div>

<%@include file="/WEB-INF/jsp/bottom.jsp" %>