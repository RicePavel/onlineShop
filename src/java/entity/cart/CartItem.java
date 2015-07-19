/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.cart;

import entity.Order;

/**
 *
 * @author Rice Pavel
 */
public class CartItem {
  
  private Order order;
  
  private int quantity;
  
  public CartItem(Order order, int quantity) {
    this.order = order;
    this.quantity = quantity;
  }

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
  
  
  
}
