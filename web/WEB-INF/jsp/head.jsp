<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/tags.jsp" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <%@include file="/WEB-INF/jsp/js_css.jsp" %>
  </head>
  <body>


    <div class="navbar navbar-default navbar-static-top"> 
      <div class="container"> 

        <ul class="nav navbar-nav navbar-right">
          <li>
            <security:authorize ifAnyGranted="ANONYM" >
              <a href="<c:url value="/login.jsp" />">Вход для администратора</a>
            </security:authorize>
            <security:authorize ifNotGranted="ANONYM" >
              <a href="<c:url value="/logout" />">Выход</a>
            </security:authorize> 
          </li>
        </ul>

      </div>
    </div>

    <div class="top-menu navbar"> 
      <div class="container"> 
        <%@include file="/WEB-INF/jsp/cart.jsp" %>
      </div>
    </div>

    <div class="container">

       <%@include file="/WEB-INF/jsp/menu.jsp" %> 
      <div class="content" style="overflow: hidden;" > 

