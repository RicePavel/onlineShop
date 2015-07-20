/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ProductDao;
import entity.Product;
import entity.cart.CartInfo;
import entity.cart.CartInfoItem;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rice Pavel
 */
@Service
@Transactional
public class CartService {

  private final String CART_ATTRIBUTE = "Cart";

  @Autowired
  private ProductDao productDao;

  public CartInfo getCartInfo(HttpSession session) {
    Map<Long, Integer> cart = getCart(session);
    List<CartInfoItem> infoList = new ArrayList();
    int totalQuantity = 0;
    double totalSumm = 0;
    for (Long productId : cart.keySet()) {
      Integer quantity = cart.get(productId);
      Product prod = productDao.find(productId);
      double summ = prod.getPrice() * ((double) quantity);
      infoList.add(new CartInfoItem(prod, quantity, summ));
      totalQuantity += quantity;
      totalSumm += summ;
    }
    return new CartInfo(totalQuantity, totalSumm, infoList);
  }

  public void add(Long productId, HttpSession session) {
    Map<Long, Integer> cart = getCart(session);
    if (cart.get(productId) == null) {
      cart.put(productId, 1);
    } else {
      cart.put(productId, cart.get(productId) + 1);
    }
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
