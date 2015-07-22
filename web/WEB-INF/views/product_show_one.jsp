<%-- 
    Document   : product_show_one
    Created on : 22.07.2015, 18:07:19
    Author     : Новый профиль
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jsp/tags.jsp" %>

<div class="order-left-part">
  <div class="order-img">
    <c:if test="${product.imgContent != null && ! empty product.imgContent}">
      <img src="${product.imgContent}" style="width: 100px; height: 100p;" />
    </c:if>
  </div>
  <div class="order-title">
    ${product.name} 
  </div>
  <div class="order-desc">
    ${product.description} 
  </div>
</div>
<div class="order-right-part">
  <div class="order-price">
    <fmt:formatNumber value="${product.price}" pattern="###.##" minFractionDigits="2" maxFractionDigits="2" minIntegerDigits="1" />&nbsp;р.
  </div>
  <div class="order-button">
    <security:authorize url="/cart/addProduct" >
      <a type="button" class="btn btn-danger addToCartButton" href="<c:url value="/cart/addProduct?productId=${product.productId}&categoryId=${product.category.categoryId}" />" data-productId="${product.productId}" >Купить</a>
    </security:authorize>
    <security:authorize url="/product/delete" >
      <a type="button" class="btn btn-danger" href="<c:url value="/product/delete?productId=${product.productId}&categoryId=${product.category.categoryId}&page=${page}" />">Удалить</a>
    </security:authorize>
    <security:authorize url="/product/change" >
      <a type="button" class="btn btn-primary changeProductButton" href="<c:url value="/product/change?productId=${product.productId}&categoryId=${product.category.categoryId}&page=${page}" />" data-productId="${product.productId}"  >Изменить</a>
    </security:authorize>
  </div>
</div>
