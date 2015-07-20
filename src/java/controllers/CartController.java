/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.cart.CartInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.CartService;
import service.OrderService;

/**
 *
 * @author Rice Pavel
 */
@Controller
@RequestMapping("/cart")
public class CartController extends WebController {
  
  @Autowired
  private CartService cartService;
  
  @Autowired
  private OrderService orderService;
  
  @RequestMapping("/addProduct")
  public String addProduct(Map<String, Object> model, 
          @RequestParam("productId") Long productId, 
          @RequestParam(value = "categoryId", required = false) Long categoryId,
          HttpSession session) {
    cartService.add(productId, session);
    return "redirect:/product/search?categoryId=" + categoryId;
  }
  
  @ResponseBody
  @RequestMapping(value = "addProduct", params = "ajax")
  public Map<String, Object> addProduct(Map<String, Object> model, 
          @RequestParam("productId") Long productId, HttpSession session) {
    cartService.add(productId, session);
    Map<String, Object> result = new HashMap();
    result.put("status", "true");
    return result;
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
  public String minus(Map<String, Object> model, 
          @RequestParam("productId") Long productId, HttpSession session) {
    cartService.minus(productId, session);
    return "redirect:/cart/show";
  }
  
  @RequestMapping(value = "/show", params = "ajax")
  public CartInfo getCurrent(HttpSession session) {
    return cartService.getCartInfo(session);
  }
  
  @RequestMapping("/show")
  public String show(Map<String, Object> model, HttpSession session) {
    CartInfo cartInfo = cartService.getCartInfo(session);
    model.put("cartInfo", cartInfo);
    boolean notEmpty = !cartInfo.getItems().isEmpty();
    model.put("notEmpty", notEmpty);
    return "cart_show";
  }
  
  @RequestMapping("/makeOrder")
  public String makeOrder(Map<String, Object> model, HttpSession session,
          @RequestParam(value = "fio", required = false) String fio,
          @RequestParam(value = "address", required = false) String address,
          @RequestParam(value = "email", required = false) String email,
          RedirectAttributes ra) {
    List<String> errors = new ArrayList();
    orderService.makeOrder(fio, address, email, errors, session);
    if (errors.isEmpty()) {
      return "order_success";
    } else {
      ra.addFlashAttribute("errors", errors);
      return "redirect:/cart/show";
    }
  }
  
}
