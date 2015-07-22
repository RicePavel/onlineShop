

$(document).ready(function() {
  $('body').on('click', '.changeProductButton', function() {
    productModal().modal();
    changeProductByLink($(this));
    return false;
  });
});

function changeProductByLink(link) {
  showLoading();
  var productId = link.attr('data-productId');
  showChangeProductForm(productId);
}

function showLoading() {
  var progress = 
  $('<div class="progress progress-striped active">' +
  '<div class="progress-bar"  role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width: 100%">' +
    '<span class="sr-only">Complete</span>' +
  '</div>' +
'</div>');
productModalBody().append(progress);
}

function showChangeProductForm(productId) {
  var url = getBaseUrl() + "/product/change";
  var params = {ajax: 1, productId: productId};
  $.ajax({
    url: url,
    data: params,
    success: function (response) {
      productModalBody().html(response);
    },
    error: function() {
      alert('error');
    }
  });
}

function productModal() {
  return $('#productModalWindow');
}

function productModalBody() {
  return $('#productModalBody');
}

function getCurrentPage() {
  return $('#currentPage').attr('data-page');
}

$(document).ready(function() {
  $('#changeIframe').load(function() {
    var iframe = $(this);
    var response = iframe.contents().find('body').text();
    var responseObj = $.parseJSON(response);
    var status = responseObj.status;
      if (status == 'true') {
        window.location.reload();
      } else {
        var error = responseObj.error;
        var errorContainer = productModalBody().find('.errorContainer');
        errorContainer.show();
        errorContainer.text(error);
        $('#fileContainer').html('<input  type="file"  name="file" />');      
      }
      iframe.contents().find('body').empty();
  });
});
