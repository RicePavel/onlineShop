/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Category;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Rice Pavel
 */
@Repository
public class CategoryDao extends Dao<Category> {

  @Override
  public Class getSupportedClass() {
    return Category.class;
  }
  
  public List<Category> getActive() {
    Criteria crit = currentSession().createCriteria(getSupportedClass());
    crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
    crit.add(Restrictions.isNull("closeDate"));
    return crit.list();
  }
  
}
