/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.cart;

import entity.Product;

/**
 *
 * @author Rice Pavel
 */
public class CartInfoItem {
  
  public CartInfoItem(Product product, int quantity, double summ) {
    this.product = product;
    this.quantity = quantity;
    this.summ = summ;
  }
  
  public final Product product;
  
  public final int quantity;
  
  public final double summ;

  public Product getProduct() {
    return product;
  }

  public int getQuantity() {
    return quantity;
  }

  public double getSumm() {
    return summ;
  }
  
  
  
}
