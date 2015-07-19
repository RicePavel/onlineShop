/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.Category;
import entity.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.ProductService;
import support.StringUtils;

/**
 *
 * @author Rice Pavel
 */
@Controller
@RequestMapping("/product")
public class ProductController {
  
  @Autowired
  private ProductService productService;
  
  @RequestMapping("/search")
  public String search(Map<String, Object> model, @RequestParam("categoryId") Long categoryId) {
    List<Product> list = productService.searchActiveByCategory(categoryId);
    model.put("list", list);
    return "product_search";
  }
  
    @RequestMapping("/add")
  public String add(Map<String, Object> model,
          @RequestParam(value = "name", required = false) String name,
          @RequestParam(value = "description", required = false) String description,
          @RequestParam(value = "price", required = false) String price,
          @RequestParam(value = "submit", required = false) String submit) {
    if (StringUtils.notEmpty(submit)) {
      List<String> errors = new ArrayList();
      productService.add(name, description, price, errors);
      model.put("errors", errors);
    }
    return "product_search";
  }
  
  @RequestMapping("/change")
  public String change(Map<String, Object> model,
          @RequestParam("productId") Long productId,
          @RequestParam(value = "name", required = false) String name,
          @RequestParam(value = "description", required = false) String description,
          @RequestParam(value = "price", required = false) String price,
          @RequestParam(value = "submit", required = false) String submit) {
    Product product = productService.find(productId);
    model.put("product", product);
    if (StringUtils.notEmpty(submit)) {
      List<String> errors = new ArrayList();
      productService.change(productId, name, description, price, errors);
      model.put("errors", errors);
      if (errors.isEmpty()) {
        return "rediret:/search";
      }
    }
    return "product_change";
  }
  
  @RequestMapping("/delete")
  public String delete(Map<String, Object> model, 
          @RequestParam("productId") Long productId,
          @RequestParam(value = "categoryId", required = false) Long categoryId) {
    productService.close(productId);
    return "redirect:/search?categoryId" + categoryId;
  }
  
}
