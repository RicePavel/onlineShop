/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.CartService;
import service.OrderService;

/**
 *
 * @author Rice Pavel
 */
@Controller
@RequestMapping("/cart")
public class CartController {
  
  @Autowired
  private CartService cartService;
  
  @Autowired
  private OrderService orderService;
  
  @RequestMapping("/addProduct")
  public String addProduct(Map<String, Object> model, 
          @RequestParam("productId") Long productId, 
          @RequestParam("categoryId") Long categoryId,
          HttpSession session) {
    cartService.add(productId, session);
    return "redirect:/product/search?categoryId=" + categoryId;
  }
  
  @RequestMapping("/delete")
  public String delete(Map<String, Object> model, 
          @RequestParam("productId") Long productId, HttpSession session) {
    cartService.delete(productId, session);
    return "redirect:/cart/show";
  }
  
  @RequestMapping("/plus")
  public String plus(Map<String, Object> model, 
          @RequestParam("productId") Long productId, HttpSession session) {
    cartService.plus(productId, session);
    return "redirect:/cart/show";
  }
  
  @RequestMapping("/minus")
  public String minus(Long productId, HttpSession session) {
    cartService.minus(productId, session);
    return "redirect:/cart/show";
  }
  
  @RequestMapping("/show")
  public String show(Map<String, Object> model, HttpSession session) {
    Map<Long, Integer> cart = cartService.getCurrentCart(session);
    model.put("cart", cart);
    return "cart_show";
  }
  
  @RequestMapping("/makeOrder")
  public String makeOrder(Map<String, Object> model, HttpSession session,
          @RequestParam(value = "fio", required = false) String fio,
          @RequestParam(value = "fio", required = false) String address) {
    List<String> errors = new ArrayList();
    orderService.makeOrder(fio, address, errors, session);
    if (!errors.isEmpty()) {
      return "order_success";
    } else {
      model.put("errors", errors);
      return "redirect:/cart/show";
    }
  }
  
}
