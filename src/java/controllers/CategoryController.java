/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.Category;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.CategoryService;
import support.StringUtils;

/**
 *
 * @author Rice Pavel
 */
@Controller
@RequestMapping("/category")
public class CategoryController extends WebController  {
  
  @Autowired
  private CategoryService categoryService;
  
  @RequestMapping("/add")
  public String add(Map<String, Object> model,
          @RequestParam(value = "name", required = false) String name,
          @RequestParam(value = "submit", required = false) String submit,
          RedirectAttributes ra) {
    if (StringUtils.notEmpty(submit)) {
      List<String> errors = new ArrayList();
      categoryService.add(name, errors);
      //model.put("errors", errors);
      ra.addFlashAttribute("errors", errors);
    }
    return "redirect:/category/search";
  }
  
  @RequestMapping("/search")
  public String search(Map<String, Object> model) {
    List<Category> list = categoryService.getActive();
    model.put("list", list);
    return "category_search";
  }
  
  @RequestMapping("/change")
  public String change(Map<String, Object> model,
          @RequestParam("categoryId") Long categoryId,
          @RequestParam(value = "name", required = false) String name,
          @RequestParam(value = "submit", required = false) String submit) {
    Category category = categoryService.find(categoryId);
    model.put("category", category);
    if (StringUtils.notEmpty(submit)) {
      List<String> errors = new ArrayList();
      categoryService.change(categoryId, name, errors);
      model.put("errors", errors);
      if (errors.isEmpty()) {
        return "redirect:/category/search";
      }
    }
    return "category_change";
  }
  
  @RequestMapping("/delete")
  public String delete(Map<String, Object> model, @RequestParam("categoryId") Long categoryId) {
    categoryService.close(categoryId);
    return "redirect:/category/search";
  }
  
}
