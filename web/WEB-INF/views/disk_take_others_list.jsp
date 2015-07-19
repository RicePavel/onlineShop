<%-- 
    Document   : disk_give_list
    Created on : 14.07.2015, 17:49:09
    Author     : Новый профиль
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/tags.jsp" %>

<%@include file="/WEB-INF/jsp/head.jsp" %>

<%@include file="/WEB-INF/jsp/menu.jsp" %>

<h2 class="standart-head" >Диски, отданные другим</h2>

<div class="standart-div">
  <table class="table"> 
    <tr>
      <th>Диск</th>
      <th>кто взял</th>
      <th> </th>
    </tr>
    <c:forEach items="${takenItemList}" var="item" >
      <tr>
        <td>${item.disk.name}</td>
        <td>${item.user.surname} ${item.user.name}</td>
      </tr>
    </c:forEach>
  </table>
</div>

<%@include file="/WEB-INF/jsp/bottom.jsp" %>