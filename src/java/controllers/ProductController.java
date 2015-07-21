/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.Category;
import entity.Product;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.CategoryService;
import service.ProductFileService;
import service.ProductService;
import support.StringUtils;

/**
 *
 * @author Rice Pavel
 */
@Controller
@RequestMapping("/product")
public class ProductController extends WebController  {
  
  @Autowired
  private ProductService productService;
  
  @Autowired
  private CategoryService categoryService;
  
  @Autowired
  private ProductFileService fileService;
  
  @RequestMapping("/home")
  public String home(Map<String, Object> model) {
    return "welcome";
  }
  
  @RequestMapping("/search")
  public String search(Map<String, Object> model, @RequestParam("categoryId") Long categoryId) throws IOException {
    Category category = categoryService.find(categoryId);
    model.put("category", category);
    List<Product> list = productService.searchActiveByCategory(categoryId);
    for (Product product: list) {
      product.setImgContent(fileService.getImgContent(product));
    }
    model.put("list", list);
    return "product_search";
  }
  
  @RequestMapping("/add")
  public String add(Map<String, Object> model,
          @RequestParam(value = "name", required = false) String name,
          @RequestParam(value = "description", required = false) String description,
          @RequestParam(value = "price", required = false) String price,
          @RequestParam("categoryId") Long categoryId,
          @RequestParam(value = "submit", required = false) String submit,
          @RequestParam(value = "file", required = false) MultipartFile file,
          RedirectAttributes ra) throws IOException {
    if (StringUtils.notEmpty(submit)) {
      List<String> errors = new ArrayList();
      productService.add(name, description, price, categoryId, file, errors);
      ra.addFlashAttribute("errors", errors);
    }
    return "redirect:/product/search?categoryId=" + categoryId;
  }
  
  @RequestMapping(value = "/change")
  public String change(Map<String, Object> model, @RequestParam("productId") Long productId) {
    Product product = productService.find(productId);
    model.put("product", product);
    return "product_change";
  }
  
  @RequestMapping(value = "/change", params = "submit")
  public String change(Map<String, Object> model,
          @RequestParam("productId") Long productId,
          @RequestParam(value = "name", required = false) String name,
          @RequestParam(value = "description", required = false) String description,
          @RequestParam(value = "price", required = false) String price,
          @RequestParam(value = "submit", required = false) String submit,
          @RequestParam(value = "file", required = false) MultipartFile file,
          @RequestParam(value = "categoryId", required = false) Long categoryId) throws IOException {
    Product product = productService.find(productId);
    product.setImgContent(fileService.getImgContent(product));
    model.put("product", product);
    if (StringUtils.notEmpty(submit)) {
      List<String> errors = new ArrayList();
      productService.change(productId, name, description, price, file, errors);
      model.put("errors", errors);
      if (errors.isEmpty()) {
        return "redirect:/product/search?categoryId=" + categoryId;
      }
    }
    return "product_change";
  }
  
  @RequestMapping("/delete")
  public String delete(Map<String, Object> model, 
          @RequestParam("productId") Long productId,
          @RequestParam(value = "categoryId", required = false) Long categoryId) {
    productService.close(productId);
    return "redirect:/product/search?categoryId=" + categoryId;
  }
  
}
