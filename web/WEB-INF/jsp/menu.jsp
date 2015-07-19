<%-- 
    Document   : menu
    Created on : 14.07.2015, 16:59:24
    Author     : Новый профиль
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jsp/tags.jsp" %>



<c:forEach items="${categoryList}" var="category" >
  <a href="<c:url value="/product/search?categoryId=${category.categoryId}" />">${category.name}</a> <br/>
</c:forEach>
