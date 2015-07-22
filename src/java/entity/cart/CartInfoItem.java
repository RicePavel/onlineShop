/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.cart;

import entity.Product;
import java.math.BigDecimal;

/**
 *
 * @author Rice Pavel
 */
public class CartInfoItem {
  
  public CartInfoItem(Product product, int quantity, BigDecimal summ) {
    this.product = product;
    this.quantity = quantity;
    this.summ = summ;
  }
  
  public final Product product;
  
  public final int quantity;
  
  public final BigDecimal summ;

  public Product getProduct() {
    return product;
  }

  public int getQuantity() {
    return quantity;
  }

  public BigDecimal getSumm() {
    return summ;
  }
  
  
  
}
