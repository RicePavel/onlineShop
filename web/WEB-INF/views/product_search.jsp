<%-- 
    Document   : product_search
    Created on : 19.07.2015, 19:03:22
    Author     : Новый профиль
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jsp/head.jsp" %>

<%@include file="/WEB-INF/jsp/errors.jsp" %>

<h2>${category.name}</h2>

<security:authorize url="/product/add" >
  <form class="form-horizontal" role="form" action="<c:url value="/product/add" />" enctype="multipart/form-data" method="POST" >
    <div class="form-group">
      <label class="col-sm-2 control-label">Название:</label> 
      <div class="col-sm-10">
        <input class="form-control" type="text" name="name" value="${param['name']}" /> 
      </div>
    </div>

    <div class="form-group">
      <label  class="col-sm-2 control-label">Описание:</label> 
      <div class="col-sm-10">
        <textarea class="form-control" name="description" >${param['description']}</textarea>
      </div>
    </div>

    <div class="form-group">
      <label  class="col-sm-2 control-label">Цена:</label>  
      <div class="col-sm-10">
        <input class="form-control" type="text" name="price" value="${param['price']}" />
      </div>
    </div>

    <input type="hidden" name="categoryId" value="${param['categoryId']}" >

    <div class="form-group">
      <label  class="col-sm-2 control-label">Картинка:</label>  
      <div class="col-sm-10">
        <input  type="file" name="file" />
      </div>
    </div>

    <div class="form-group">
      <div class="col-sm-offset-2 col-sm-10">
        <input type="submit"  class="btn btn-default" name="submit" value="Добавить" />
      </div>
    </div>

  </form>
</security:authorize>

<c:forEach items="${list}" var="product" > 
  <div class="order-item">
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
        ${product.price}
      </div>
      <div class="order-button">
        <security:authorize url="/cart/addProduct" >
          <a type="button" class="btn btn-danger addToCartButton" href="<c:url value="/cart/addProduct?productId=${product.productId}&categoryId=${product.category.categoryId}" />" data-productId="${product.productId}" >Купить</a>
        </security:authorize>
        <security:authorize url="/product/delete" >
          <a type="button" class="btn btn-danger" href="<c:url value="/product/delete?productId=${product.productId}&categoryId=${product.category.categoryId}" />">Удалить</a>
        </security:authorize>
        <security:authorize url="/product/change" >
          <a type="button" class="btn btn-primary" href="<c:url value="/product/change?productId=${product.productId}&categoryId=${product.category.categoryId}" />">Изменить</a>
        </security:authorize>
      </div>
    </div>
  </div>
  <div style="clear:both;"> </div>
</c:forEach>

<%@include file="/WEB-INF/jsp/bottom.jsp" %>

