



function addToProductToCart(button) {
  var addCartUrl = '';
  var productId = getProductId(button);
  var params = {productId: productId};
  $.ajax({
    url: addCartUrl,
    params: params,
    success: function (response) {
      if (response.status == 'true') {
        showBuy(button);
        updateCart();
      }
    }
  });
}

function updateCart() {
  var showCartUrl = '';
  $.ajax({
    url: showCartUrl,
    success: function (response) {
      if (response.status == 'true') {
        var cartInfo = response.cartInfo;
        updateShowCart(cartInfo);
      }
    }
  });
  
  function updateShowCart(cartInfo) {
    
  }
  
}

