<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/tags.jsp" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <link rel="stylesheet" href="<c:url value='/bootstrap/css/bootstrap.min.css' />" />
    <link rel="stylesheet" href="<c:url value='/bootstrap/css/bootstrap-theme.css' />" />
    <link rel="stylesheet" href="<c:url value='/css/project.css' />" />
    <script src="bootstrap/js/bootstrap.min.js" ></script>
  </head>
  <body>

    <div class="container">
      <div class="top-menu"> <%@include file="/WEB-INF/jsp/cart.jsp" %>
        <security:authorize ifAnyGranted="ANONYM" >
          <a href="<c:url value="/login.jsp" />">Вход</a>
        </security:authorize>
        <security:authorize ifNotGranted="ANONYM" >
          <a href="<c:url value="/logout" />">Выход</a>
        </security:authorize>
      </div>
      <div class="menu"> <%@include file="/WEB-INF/jsp/menu.jsp" %> </div>
      <div class="content"> 

