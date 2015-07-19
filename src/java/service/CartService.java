/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.cart.CartInfo;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

/**
 *
 * @author Rice Pavel
 */
@Service
public class CartService {
  
  private final String CART_ATTRIBUTE = "Cart";
  
  /*
  public CartInfo getCartInfo(HttpSession session) {
    Map<Long, Integer> cart = getCart(session);
    
  }
  */
  
  public void add(Long productId, HttpSession session) {
    Map<Long, Integer> cart = getCart(session);
    cart.put(productId, 1);
    saveCart(cart, session);
  }
  
  public void delete(Long productId, HttpSession session) {
    Map<Long, Integer> cart = getCart(session);
    cart.remove(productId);
    saveCart(cart, session);
  }
  
  public void minus(Long productId, HttpSession session) {
    Map<Long, Integer> cart = getCart(session);
    Integer quantity = cart.get(productId);
    if (quantity != null && quantity > 1) {
      cart.put(productId, quantity - 1);
      saveCart(cart, session);
    } 
  }
  
  public void plus(Long productId, HttpSession session) {
    Map<Long, Integer> cart = getCart(session);
    Integer quantity = cart.get(productId);
    if (quantity != null) {
      cart.put(productId, quantity + 1);
      saveCart(cart, session);
    }
  }
  
  public void clearCart(HttpSession session) {
    saveCart(null, session);
  }
  
  public Map<Long, Integer> getCurrentCart(HttpSession session) {
    return getCart(session);
  }
  
  private void saveCart(Map<Long, Integer> cart, HttpSession session) {
    session.setAttribute(CART_ATTRIBUTE, cart);
  }
  
  private Map<Long, Integer> getCart(HttpSession session) {
    Map<Long, Integer> cart = (Map<Long, Integer>) session.getAttribute(CART_ATTRIBUTE);
    if (cart == null) {
      cart = new LinkedHashMap();
      session.setAttribute(CART_ATTRIBUTE, cart);
    }
    return cart;
  }
  
}
