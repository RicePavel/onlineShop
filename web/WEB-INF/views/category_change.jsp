<%-- 
    Document   : category_change
    Created on : 19.07.2015, 18:57:03
    Author     : Новый профиль
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%@include file="/WEB-INF/jsp/head.jsp" %>

<%@include file="/WEB-INF/jsp/errors.jsp" %>

<security:authorize url="/category/change" >
  <form action="<c:url value="/category/change" />"> 
    <input type="text" name="name" value="${category.name}" />
    <input type="hidden" name="categoryId" value="${category.categoryId}" />
    <input type="submit" name="submit" value="Сохранить" />
  </form>
</security:authorize>


<%@include file="/WEB-INF/jsp/bottom.jsp" %>
