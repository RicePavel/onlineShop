<%-- 
    Document   : errors
    Created on : 15.07.2015, 22:17:39
    Author     : Новый профиль
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<c:if test="${errors != null && !errors.isEmpty()}" > 
  <p class="bg-danger standart-message">
    <c:forEach items="${errors}" var="error">
      ${error} <br/>
    </c:forEach>
  </p>
</c:if>
