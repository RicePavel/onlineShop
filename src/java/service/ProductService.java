/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.CategoryDao;
import dao.ProductDao;
import entity.Product;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.multipart.MultipartFile;
import support.ConvertUtils;
import support.EntityValidator;
import support.Errors;

/**
 *
 * @author Rice Pavel
 */
@Service
public class ProductService {

  @Autowired
  private ProductDao productDao;

  @Autowired
  private CategoryDao categoryDao;

  @Autowired
  private EntityValidator validator;

  @Autowired
  private ProductFileService fileService;

  @Autowired
  private PlatformTransactionManager transactionManager;

  @Transactional
  public List<Product> searchByCategory(Long categoryId) {
    return productDao.searchByCategory(categoryId);
  }

  @Transactional
  public int getCountByCategory(Long categoryId) {
    return productDao.getCountByCategory(categoryId);
  }

  @Transactional
  public List<Product> searchActiveByCategory(Long categoryId, Integer start, Integer count) {
    return productDao.searchActiveByCategory(categoryId, start, count);
  }

  @Transactional
  public List<Product> searchActiveByCategory(Long categoryId) {
    return productDao.searchActiveByCategory(categoryId);
  }

  @Transactional
  public void close(Long productId) {
    Product prod = productDao.find(productId);
    prod.setCloseDate(new Date());
    productDao.update(prod);
  }

  @Transactional
  public Product find(Long productId) {
    return productDao.find(productId);
  }

  public void add(final String name, final String description, final String price, final Long categoryId, final MultipartFile file, final List<String> errors) throws IOException {
    final TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
    transactionTemplate.execute(new TransactionCallbackWithoutResult() {

      @Override
      protected void doInTransactionWithoutResult(TransactionStatus ts) {
        try {
          BigDecimal priceDecimal = ConvertUtils.getBigDecimal(price);
          if (priceDecimal != null) {
            priceDecimal = priceDecimal.setScale(2, RoundingMode.HALF_UP);
            Product product = new Product();
            setParams(product, name, description, priceDecimal, categoryId);
            if (validator.validate(errors, product)) {
              productDao.save(product);
              fileService.save(file, product, errors);
            }
          } else {
            errors.add(Errors.notDecimal("Цена"));
          }
          if (!errors.isEmpty()) {
            ts.setRollbackOnly();
          }
        } catch (Throwable e) {
          ts.setRollbackOnly();
          throw new Error(e.getMessage());
        }
      }

    }
    );
  }

  public void change(final Long productId, final String name, final String description, final String price, final MultipartFile file, final List<String> errors) throws IOException {
    final TransactionTemplate temp = new TransactionTemplate(transactionManager);
    temp.execute(new TransactionCallbackWithoutResult() {

      @Override
      protected void doInTransactionWithoutResult(TransactionStatus ts) {
        try {
          BigDecimal priceDecimal = ConvertUtils.getBigDecimal(price);
          if (priceDecimal != null) {
            priceDecimal = priceDecimal.setScale(2, RoundingMode.HALF_UP);
            Product product = productDao.find(productId);
            setParams(product, name, description, priceDecimal);
            if (validator.validate(errors, product)) {
              productDao.update(product);
              fileService.save(file, product, errors);
            } else {
              productDao.evict(product);
            }
          } else {
            errors.add(Errors.notDecimal("Цена"));
          }
          if (!errors.isEmpty()) {
            ts.isRollbackOnly();
          }
        } catch (Throwable exc) {
          throw new Error(exc.getMessage());
        }
      }
    }
    );
  }

  private void setParams(Product product, String name, String description, BigDecimal price, Long categoryId) {
    product.setName(name);
    product.setDescription(description);
    product.setPrice(price);
    product.setCategory(categoryDao.find(categoryId));
  }

  private void setParams(Product product, String name, String description, BigDecimal price) {
    product.setName(name);
    product.setDescription(description);
    product.setPrice(price);
  }

}
