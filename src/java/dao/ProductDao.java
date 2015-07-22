/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Category;
import entity.Product;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
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

  public List<Product> searchActiveByCategory(Long categoryId) {
    return searchActiveByCategory(categoryId, null, null);
  } 
  
  public List<Product> searchActiveByCategory(Long categoryId, Integer start, Integer count) {
    Criteria crit = currentSession().createCriteria(getSupportedClass());
    crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
    crit.createAlias("category", "category");
    crit.add(Restrictions.eq("category.categoryId", categoryId));
    crit.add(Restrictions.isNull("category.closeDate"));
    crit.add(Restrictions.isNull("closeDate"));
    if (start != null && count != null) {
      crit.setFirstResult(start);
      crit.setMaxResults(count);
    }
    return crit.list();
  }
  
  public Integer getCountByCategory(Long categoryId) {
    Criteria crit = currentSession().createCriteria(getSupportedClass());
    crit.setProjection(Projections.rowCount());
    crit.createAlias("category", "category");
    crit.add(Restrictions.eq("category.categoryId", categoryId));
    crit.add(Restrictions.isNull("category.closeDate"));
    crit.add(Restrictions.isNull("closeDate"));
    Long l = (Long) crit.uniqueResult();
    if (l != null) {
      return l.intValue();
    } else {
      return 0;
    }
  }

  public List<Product> searchByCategory(Long categoryId) {
    Criteria crit = currentSession().createCriteria(getSupportedClass());
    crit.setResultTransformer(crit.DISTINCT_ROOT_ENTITY);
    crit.createAlias("category", "category");
    crit.add(Restrictions.eq("category.categoryId", categoryId));
    return crit.list();
  }

  public List<Category> getActive() {
    Criteria crit = currentSession().createCriteria(getSupportedClass());
    crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
    crit.add(Restrictions.isNull("closeDate"));
    return crit.list();
  }

}
