
$(document).ready(function () {
  $('body').on('click', '.addToCartButton', function () {
    var link = $(this);
    addProductToCart(link);
    return false;
  })
});

function addProductToCart(link) {
  var addCartUrl = getBaseUrl() + "/cart/addProduct";
  var productId = link.attr('data-productId');
  var params = {productId: productId, ajax: 1};
  $.ajax({
    url: addCartUrl,
    data: params,
    success: function (response) {
      if (response.status == 'true') {
        showBuy(link);
        updateCart();
      }
    }
  });
}

function showBuy(link) {
  link.removeClass('btn-danger');
  link.addClass('btn-success');
  link.text('Товар в корзине!');
}


function updateCart() {
  var showCartUrl = getBaseUrl() + "/cart/show?ajax=1";
  $.ajax({
    url: showCartUrl,
    success: function (response) {
      var count = response.count;
      var summ = response.summ;
      if (count != undefined && summ != undefined) {
        updateShowCart(count, summ);
      }
    }
  });

  function updateShowCart(count, summ) {
    $('#cartInfoCount').text(count);
    $('#cartInfoSumm').text(summ);
  }

}

