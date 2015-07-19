<%-- 
    Document   : disk_all_my_disk
    Created on : 14.07.2015, 17:49:23
    Author     : Новый профиль
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/tags.jsp" %>

<%@include file="/WEB-INF/jsp/head.jsp" %>

<%@include file="/WEB-INF/jsp/menu.jsp" %>

<h2 class="standart-head">Все мои диски</h2>

<div class="standart-div">
<table class="table"> 
  <tr>
    <th>Диск</th>
    <th>Описание</th>
    <th>Кто взял</th>
    <th>Удалить</th>
    <th></th>
  </tr>
  <c:forEach items="${list}" var="arr" >
    <c:set value="${arr[0]}" var="disk" />
    <c:set value="${arr[1]}" var="takeItem" />
    <tr>
    <td>${disk.name}</td>
    <td>${disk.description}</td>
    <td> 
      <c:if test="${takeItem != null}" > 
        ${takeItem.user.name} ${takeItem.user.surname}
      </c:if>
    </td>
    <td> <a href="<c:url value="/Disk/delete?diskId=${disk.diskId}" />" >Удалить</a> </td>
    </tr>
  </c:forEach>
</table>
</div>

<%@include file="/WEB-INF/jsp/bottom.jsp" %>
