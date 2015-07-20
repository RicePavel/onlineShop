/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.CategoryDao;
import entity.Category;
import entity.Product;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import support.EntityValidator;

/**
 *
 * @author Rice Pavel
 */
@Service
@Transactional
public class CategoryService {

  @Autowired
  private CategoryDao categoryDao;

  private ProductService productService;
  
  @Autowired
  private EntityValidator validator;

  public List<Category> getAll() {
    return categoryDao.getAll();
  }

  public List<Category> getActive() {
    return categoryDao.getActive();
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
    } else {
      categoryDao.evict(cat);
    }
  }

  public void close(Long categoryId) {
    Category cat = categoryDao.find(categoryId);
    cat.setCloseDate(new Date());
    categoryDao.update(cat);
    closeProducts(cat);
  }

  private void closeProducts(Category cat) {
    if (cat.getProductList() != null) {
      for (Product product : cat.getProductList()) {
        productService.close(product.getProductId());
      }
    }
  }

}
