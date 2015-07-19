/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.CategoryDao;
import entity.Category;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import support.EntityValidator;

/**
 *
 * @author Rice Pavel
 */
@Service
public class CategoryService {
  
  @Autowired
  private CategoryDao categoryDao;
  
  @Autowired
  private EntityValidator validator;
  
  public List<Category> getAll() {
    return categoryDao.getAll();
  }
  
  public Category find(Long categoryId) {
    return categoryDao.find(categoryId);
  }
  
  public void add(String name, List<String> errors) {
    Category category = new Category();
    category.setName(name);
    validator.validate(errors, category);
    if (errors.isEmpty()) {
      categoryDao.save(category);
    }
  }
  
  public void change(Long categoryId, String name, List<String> errors) {
    Category cat = categoryDao.find(categoryId);
    cat.setName(name);
    validator.validate(errors, cat);
    if (errors.isEmpty()) {
      categoryDao.update(cat);
    }
  }
  
}
