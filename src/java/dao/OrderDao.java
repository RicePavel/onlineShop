/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Order;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Rice Pavel
 */
@Repository
public class OrderDao extends Dao<Order> {

  @Override
  public Class getSupportedClass() {
    return Order.class;
  }
  
  public List<Order> getAll(String email) {
    Criteria crit = getCriteriaDistinctRootEntity(getSupportedClass());
    if (email != null && !email.isEmpty()) {
      crit.add(Restrictions.eq("email", email));
    }
    return crit.list();
  }
  
  public List<Object[]> reportByCategories(Date dateFrom, Date dateTo) {
    String hql = " select p.category.categoryId, sum(item.price), count(item.orderItemId) " +
                 " from OrderItem as item inner join item.product as p inner join item.order as o " +
                 " where o.createDate >= :dateFrom and o.createDate <= :dateTo " +
                 " group by p.category.categoryId ";
    Query query = currentSession().createQuery(hql);
    query.setTimestamp("dateFrom", dateFrom);
    query.setTimestamp("dateTo", dateTo);
    return query.list();
  }
  
   public List<Object[]> reportByProducts(Long categoryId, Date dateFrom, Date dateTo) {
    String hql = "select p.productId, sum(item.price), count(item.orderItemId) "
            + " from OrderItem as item inner join item.product as p inner join item.order as o inner join p.category as cat "
            + " where o.createDate >= :dateFrom and o.createDate <= :dateTo and cat.categoryId = :categoryId "
            + " group by p.productId ";
    Query query = currentSession().createQuery(hql);
    query.setTimestamp("dateFrom", dateFrom);
    query.setTimestamp("dateTo", dateTo);
    query.setParameter("categoryId", categoryId);
    return query.list();
  }
   
   public List<Object[]> reportByClients(Date dateFrom, Date dateTo) {
     String hql = "select o.email, sum(item.price), count(item.orderItemId) "
            + " from OrderItem as item inner join item.order as o  "
            + " where o.createDate >= :dateFrom and o.createDate <= :dateTo "
            + " group by o.email ";
    Query query = currentSession().createQuery(hql);
    query.setTimestamp("dateFrom", dateFrom);
    query.setTimestamp("dateTo", dateTo);
    return query.list();
   }
  
}
