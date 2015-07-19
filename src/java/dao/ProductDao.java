/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Product;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Rice Pavel
 */
@Repository
public class ProductDao extends Dao<Product> {
  
  @Override
  public Class getSupportedClass() {
    return Product.class;
  }
  
  
  
  public List<Product> searchByCategory(Long categoryId) {
    Criteria crit = currentSession().createCriteria(getSupportedClass());
    crit.setResultTransformer(crit.DISTINCT_ROOT_ENTITY);
    crit.createAlias("category", "category");
    crit.add(Restrictions.eq("category.categoryId", categoryId));
    return crit.list();
  }
  
}
