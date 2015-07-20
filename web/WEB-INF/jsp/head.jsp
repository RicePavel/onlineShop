<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
        <a href="<c:url value="/login.jsp" />"> </a></div>
      <div class="menu"> <%@include file="/WEB-INF/jsp/menu.jsp" %> </div>
      <div class="content"> 
    
