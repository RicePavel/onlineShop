/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import service.CategoryService;

/**
 *
 * @author Rice Pavel
 */
public class WebController {
  
  @Autowired
  private CategoryService categoryService;
  
  public void setMenu(Map<String, Object> model) {
    model.put("categoryList", categoryService.getActive());
  }
  
}
