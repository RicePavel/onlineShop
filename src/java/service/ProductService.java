/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.CategoryDao;
import dao.ProductDao;
import entity.Product;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import support.ConvertUtils;
import support.EntityValidator;
import support.Errors;

/**
 *
 * @author Rice Pavel
 */
@Service
@Transactional
public class ProductService {
  
  @Autowired
  private ProductDao productDao;
  
  @Autowired
  private CategoryDao categoryDao;
  
  @Autowired
  private EntityValidator validator;
  
  public List<Product> searchByCategory(Long categoryId) {
    return productDao.searchByCategory(categoryId);
  }
  
  public List<Product> searchActiveByCategory(Long categoryId) {
    return productDao.searchActiveByCategory(categoryId);
  }
  
  public void close(Long productId) {
    Product prod = productDao.find(productId);
    prod.setCloseDate(new Date());
    productDao.update(prod);
  }
  
  public Product find(Long productId) {
    return productDao.find(productId);
  }
  
  public void add(String name, String description, String price, Long categoryId, List<String> errors) {
    Double doublePrice = ConvertUtils.getDouble(price);
    if (doublePrice != null) {
      Product product = new Product();
      setParams(product, name, description, doublePrice, categoryId);
      if (validator.validate(errors, product)) {
        productDao.save(product);
      }
    } else {
      errors.add(Errors.notDecimal("Цена"));
    }
  }
  
  public void change(Long productId, String name, String description, String price, List<String> errors) {
    Double doublePrice = ConvertUtils.getDouble(price);
    if (doublePrice != null) {
      Product product = productDao.find(productId);
      setParams(product, name, description, doublePrice);
      if (validator.validate(errors, product)) {
        productDao.update(product);
      } else {
        productDao.evict(product);
      }
    } else {
      errors.add(Errors.notDecimal("Цена"));
    }
  }
  
  private void setParams(Product product, String name, String description, Double price, Long categoryId) {
    product.setName(name);
    product.setDescription(description);
    product.setPrice(price);
    product.setCategory(categoryDao.find(categoryId));
  }
  
  private void setParams(Product product, String name, String description, Double price) {
    product.setName(name);
    product.setDescription(description);
    product.setPrice(price);
  }
  
  
}
