/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.cart.CartInfo;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import service.CartService;
import service.CategoryService;

/**
 *
 * @author Rice Pavel
 */
public class WebController {
  
  @Autowired
  private CategoryService categoryService;
  
  @Autowired
  private CartService cartService;
  
  @ModelAttribute
  public void setMenu(Map<String, Object> model) {
    model.put("categoryList", categoryService.getActive());
  }
  
  @ModelAttribute
  public void setCartData(Map<String, Object> model, HttpSession session) {
    CartInfo cartInfo = cartService.getCartInfo(session);
    model.put("cartInfo", cartInfo);
  }
  
}
