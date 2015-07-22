<%-- 
    Document   : product_change
    Created on : 19.07.2015, 19:10:32
    Author     : Новый профиль
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jsp/tags.jsp" %>

<%@include file="/WEB-INF/jsp/errors.jsp" %>

<table>
  <tr>
    <td>
      <c:if test="${product.imgContent != null && ! empty product.imgContent}">
        <img src="${product.imgContent}" style="max-width: 100px; max-height: 100p;" />
      </c:if>
    </td>
    <td style="width: 100%;">
      <div>

        <div class="errorContainer bg-danger standart-message" style="display: none;"> </div>

        <security:authorize url="/product/change" >
          <form class="form-horizontal productChangeForm" target="change_form" role="form" action="<c:url value="/product/change" />" enctype="multipart/form-data" method="POST" data-productId="${product.productId}" >
            <div class="form-group">
              <label class="col-sm-2 control-label">Название:</label>
              <div class="col-sm-10">
                <input class="form-control" type="text" name="name" value="${product.name}" required />
              </div>
            </div>

            <div class="form-group">
              <label class="col-sm-2 control-label">Описание:</label>
              <div class="col-sm-10">
                <textarea class="form-control" name="description">${product.description}</textarea>
              </div>
            </div>

            <div class="form-group">
              <label class="col-sm-2 control-label">Цена:</label>
              <div class="col-sm-10">
                <input class="form-control" type="text" name="price" value="<fmt:formatNumber value="${product.price}" pattern="###.##" minFractionDigits="2" maxFractionDigits="2" minIntegerDigits="1" />" required pattern="[0-9\s\.\,]+" title="Введите число" />
              </div>
            </div>

            <input type="hidden" name="productId" value="${product.productId}" />
            <input type="hidden" name="categoryId" value="${param['categoryId']}" />
            <input type="hidden" name="page" value="${param['page']}" />
            <input type="hidden" name="ajax" value="1" />

            <div class="form-group">
              <label  class="col-sm-2 control-label">Картинка:</label>  
              <div class="col-sm-10" id="fileContainer" >
                <input  type="file"  name="file" />
              </div>
            </div>

            <div class="form-group">
              <div class="col-sm-offset-2 col-sm-10">
                <input class="btn btn-default" type="submit" name="submit" value="Сохранить" />
              </div>
            </div>
          </form>
        </security:authorize>

      </div>
    </td>
  <tr>
</table>
