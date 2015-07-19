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
public class CarInfoItem {
  
  public CarInfoItem(Product product, int quantity, int summ) {
    this.product = product;
    this.quantity = quantity;
    this.summ = summ;
  }
  
  public final Product product;
  
  public final int quantity;
  
  public final int summ;
  
}
