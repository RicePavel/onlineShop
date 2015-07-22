<%-- 
    Document   : menu
    Created on : 14.07.2015, 16:59:24
    Author     : Новый профиль
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jsp/tags.jsp" %>

<div class="menu list-group">

  <c:set value="${requestScope['javax.servlet.forward.request_uri']}?${requestScope['javax.servlet.forward.query_string']}" var="currentUrl" />
  
  <security:authorize ifAnyGranted="ADMIN" >
    <span class="list-group-item active">Меню администратора</span>
    </security:authorize>

    <security:authorize url="/category/search" >
      <a class="list-group-item ${currentUrl.contains('/category/search') ? 'active-item' : ''}" href="<c:url value="/category/search" />">Редактировать категории</a>
    </security:authorize>
    <security:authorize url="/order/search" >
      <a class="list-group-item ${currentUrl.contains('/order/search') ? 'active-item' : ''}" href="<c:url value="/order/search" />">Заказы клиентов</a>
    </security:authorize>
    <security:authorize url="/setting/show" >
      <a class="list-group-item ${currentUrl.contains('/setting/show') ? 'active-item' : ''}" href="<c:url value="/setting/show" />">Настройки</a>
    </security:authorize>
    <security:authorize url="/report/category">
      <a class="list-group-item ${currentUrl.contains('/report/category') ? 'active-item' : ''}" href="<c:url value="/report/category" />">Объем продаж по категориям</a>
    </security:authorize>
    <security:authorize url="/report/client">
      <a class="list-group-item ${currentUrl.contains('/report/client') ? 'active-item' : ''}" href="<c:url value="/report/client" />">Объем покупок по клиентам</a>
    </security:authorize>

    <span class="list-group-item active">Товары по категориям </span>

    <c:forEach items="${categoryList}" var="category" >
      <security:authorize url="/product/search" >
        <c:url value="/product/search?categoryId=${category.categoryId}" var="url" />
        <a class="list-group-item ${currentUrl.contains(url) ? 'active-item' : ''}" href="${url}">${category.name}</a>
      </security:authorize>
    </c:forEach>

</div>
