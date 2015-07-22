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
        <input class="form-control" type="text" name="name" value="${param['name']}" required /> 
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
        <input class="form-control" type="text" name="price" value="${param['price']}" pattern="[0-9\s\.\,]+" required title="Введите число" />
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
  <div class="order-item" data-productId="${product.productId}">
    <%@include file="/WEB-INF/views/product_show_one.jsp" %>
  </div>
  <div style="clear:both;"> </div>
</c:forEach>

<c:if test="${countPages != null && countPages > 1}">
  <div id="paginator" class="paginator"> </div>
</c:if>

<c:url value="/product/search?categoryId=${param['categoryId']}" var="urlForPaginator" />

<script type="text/javascript">
  paginator_example = new Paginator(
          "paginator", // id контейнера, куда ляжет пагинатор
  ${countPages}, // общее число страниц
          3, // число страниц, видимых одновременно
  ${page}, // номер текущей страницы
          '${urlForPaginator}' // url страниц
          );
</script>

<div id="currentPage" data-page="${page}" style="display: hidden;" > </div>

<!-- модальное окно -->
<div id="productModalWindow" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" > 
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="myModalLabel">Изменение товара</h3>
      </div>
      <div class="modal-body" id="productModalBody"> 

      </div>
    </div>
  </div>
</div>

<iframe id="changeIframe" name="change_form" style="display: none;"> </iframe>

<%@include file="/WEB-INF/jsp/bottom.jsp" %>

