/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.Order;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.OrderService;

/**
 *
 * @author Rice Pavel
 */
@Controller
@RequestMapping("/order")
public class OrderController extends WebController  {
  
  @Autowired
  private OrderService orderService;
  
  @RequestMapping("/search")
  public String search(Map<String, Object> model) {
    List<Order> list = orderService.search();
    model.put("list", list);
    return "order_search";
  }
  
}
