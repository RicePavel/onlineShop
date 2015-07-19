/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.cart;

import entity.Order;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Rice Pavel
 */
public class Cart {
  
  private final Map<Long, CartItem> map = new LinkedHashMap();
  
  public void addItem(Order order, int quantity) {
    map.put(order.getOrderId(), new CartItem(order, quantity));
  }
  
}
