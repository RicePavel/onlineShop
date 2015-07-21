/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Order;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
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
  
  public List<Object[]> reportByCategories(Date dateFrom, Date dateTo) {
    String hql = "select p.category, sum(item.price), count(item.orderItemId) "
            + " from OrderItem as item, item.product as p, item.order as o, item.product.category as cat "
            + " group by cat.categoryId "
            + " where o.createDate >= :dateFrom and o.createDate <= :dateTo ";
    Query query = currentSession().createQuery(hql);
    query.setDate("dateFrom", dateFrom);
    query.setDate("dateTo", dateTo);
    return query.list();
  }
  
   public List<Object[]> reportByProducts(Long categoryId, Date dateFrom, Date dateTo) {
    String hql = "select p.product, sum(item.price), count(item.orderItemId) "
            + " from OrderItem as item, item.product as p, item.order as o, item.product.category as cat "
            + " group by p.productId "
            + " where o.createDate >= :dateFrom and o.createDate <= :dateTo and p.categoryId = :categoryId ";
    Query query = currentSession().createQuery(hql);
    query.setDate("dateFrom", dateFrom);
    query.setDate("dateTo", dateTo);
    query.setParameter("categoryId", categoryId);
    return query.list();
  }
   
   public List<Object[]> reportByClients(Date dateFrom, Date dateTo) {
     String hql = "select o.email, sum(item.price), count(item.orderItemId) "
            + " from OrderItem as item, item.product as p, item.order as o, item.product.category as cat "
            + " group by o.email "
            + " where o.createDate >= :dateFrom and o.createDate <= :dateTo and p.categoryId = :categoryId ";
    Query query = currentSession().createQuery(hql);
    query.setDate("dateFrom", dateFrom);
    query.setDate("dateTo", dateTo);
    return query.list();
   }
  
}
