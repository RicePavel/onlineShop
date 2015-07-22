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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
  public String search(Map<String, Object> model, @RequestParam("categoryId") Long categoryId,
         @RequestParam(value = "page", required = false) String pageString) throws IOException {
    Category category = categoryService.find(categoryId);
    
    int page = getPage(pageString);
    int numberOnOnePage = 25;
    int start = (page -1) * numberOnOnePage;
    int count = productService.getCountByCategory(categoryId);
    int countPages = (int) Math.ceil((double) count / (double) numberOnOnePage);
    
    model.put("page", page);
    model.put("countPages", countPages);
    
    model.put("category", category);
    List<Product> list = productService.searchActiveByCategory(categoryId, start, numberOnOnePage);
    for (Product product: list) {
      product.setImgContent(fileService.getImgContent(product));
    }
    model.put("list", list);
    return "product_search";
  }
  
  private int getPage(String page) {
    try {
      return Integer.valueOf(page);
    } catch (Exception e) {
      return 1;
    }
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
      if (!errors.isEmpty()) {
        ra.addAttribute("name", name);
        ra.addAttribute("description", description);
        ra.addAttribute("price", price);
      }
    }
    return "redirect:/product/search?categoryId=" + categoryId;
  }
  
  @RequestMapping(value = "/change")
  public String change(Map<String, Object> model, @RequestParam("productId") Long productId) throws IOException {
    Product product = productService.find(productId);
    product.setImgContent(fileService.getImgContent(product));
    model.put("product", product);
    return "product_change";
  }
  
  @RequestMapping(value = "/change", params = {"ajax"})
  public String changeShowAjaxForm(Map<String, Object> model, @RequestParam("productId") Long productId) throws IOException {
    Product product = productService.find(productId);
    product.setImgContent(fileService.getImgContent(product));
    model.put("product", product);
    return "product_change_ajax";
  }
  
  @ResponseBody
  @RequestMapping(value = "/change", params = {"ajax", "submit"})
  public Map<String, String> changeByAjax(Map<String, Object> model,
          @RequestParam("productId") Long productId,
          @RequestParam(value = "name", required = false) String name,
          @RequestParam(value = "description", required = false) String description,
          @RequestParam(value = "price", required = false) String price,
          @RequestParam(value = "submit", required = false) String submit,
          @RequestParam(value = "file", required = false) MultipartFile file,
          @RequestParam(value = "categoryId", required = false) Long categoryId) {
    Map<String, String> map = new HashMap();
    boolean status;
    String error = "";
    try {
      List<String> errors = new ArrayList();
      productService.change(productId, name, description, price, file, errors);
      if (errors.isEmpty()) {
        status = true;
      } else {
        status = false;
        error = errors.toString();
      }
    } catch (Exception e) {
      status = false;
      error = (e.getMessage() != null ? e.getMessage() : "exception");
    }
    map.put("status", Boolean.toString(status));
    map.put("error", error);
    return map;
  }
  
  @RequestMapping(value = "/show")
  public String show(Map<String, Object> model,
          @RequestParam("productId") Long productId,
          @RequestParam(value = "page", required = false) String page) throws IOException {
    Product product = productService.find(productId);
    product.setImgContent(fileService.getImgContent(product));
    model.put("product", product);
    model.put("page", page);
    return "product_show_one";
  }
  
  @RequestMapping(value = "/change", params = "submit")
  public String change(Map<String, Object> model,
          @RequestParam("productId") Long productId,
          @RequestParam(value = "name", required = false) String name,
          @RequestParam(value = "description", required = false) String description,
          @RequestParam(value = "price", required = false) String price,
          @RequestParam(value = "submit", required = false) String submit,
          @RequestParam(value = "file", required = false) MultipartFile file,
          @RequestParam(value = "categoryId", required = false) Long categoryId,
          @RequestParam(value = "page", required = false) String page,
          RedirectAttributes ra) throws IOException {
    Product product = productService.find(productId);
    product.setImgContent(fileService.getImgContent(product));
    model.put("product", product);
    if (StringUtils.notEmpty(submit)) {
      List<String> errors = new ArrayList();
      productService.change(productId, name, description, price, file, errors);
      model.put("errors", errors);
      if (errors.isEmpty()) {
        if (page != null && !page.isEmpty()) {
          ra.addAttribute("page", page);
        }
        return "redirect:/product/search?categoryId=" + categoryId;
      }
    }
    return "product_change";
  }
  
  @RequestMapping("/delete")
  public String delete(Map<String, Object> model, 
          @RequestParam("productId") Long productId,
          @RequestParam(value = "categoryId", required = false) Long categoryId,
          @RequestParam(value = "page", required = false) String page,
          RedirectAttributes ra) {
    productService.close(productId);
    if (page != null && !page.isEmpty()) {
      ra.addAttribute("page", page);
    }
    return "redirect:/product/search?categoryId=" + categoryId;
  }
  
}
