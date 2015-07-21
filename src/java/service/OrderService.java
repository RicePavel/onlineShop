/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.CategoryDao;
import dao.OrderDao;
import dao.OrderItemDao;
import dao.ProductDao;
import datastructure.ReportData;
import entity.Category;
import entity.Order;
import entity.OrderItem;
import entity.Product;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
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
public class OrderService {

  @Autowired
  private CartService cartService;

  @Autowired
  private EntityValidator validator;

  @Autowired
  private OrderDao orderDao;

  @Autowired
  private OrderItemDao orderItemDao;
  
  @Autowired
  private ProductDao productDao;
  
  @Autowired
  private CategoryDao categoryDao;

  public ReportData reportByCategories(Date dateFrom, Date dateTo) {
    List<Object[]> list = orderDao.reportByCategories(dateFrom, dateTo);
    int totalCount = 0;
    double totalSumm = 0;
    for (Object[] arr: list) {
      Long categoryId = Long.valueOf(arr[0].toString());
      Category category = categoryDao.find(categoryId);
      arr[0] = category;
      double summ = (arr[1] != null ? Double.valueOf(arr[1].toString()) : 0);
      double count = (arr[2] != null ? Double.valueOf(arr[2].toString()) : 0);
      totalCount += count;
      totalSumm += summ; 
    }
    ReportData data = new ReportData();
    data.list = list;
    data.totalSumm = totalSumm;
    data.totalCount = totalCount;
    return data;
  }
  
  public ReportData reportByProducts(Long categoryId, Date dateFrom, Date dateTo) {
    List<Object[]> list = orderDao.reportByProducts(categoryId, dateFrom, dateTo);
    int totalCount = 0;
    double totalSumm = 0;
    for (Object[] arr: list) {
      Long productId = Long.valueOf(arr[0].toString());
      Product product = productDao.find(productId);
      arr[0] = product;
      double summ = (arr[1] != null ? Double.valueOf(arr[1].toString()) : 0);
      double count = (arr[2] != null ? Double.valueOf(arr[2].toString()) : 0);
      totalCount += count;
      totalSumm += summ; 
    }
    ReportData data = new ReportData();
    data.list = list;
    data.totalSumm = totalSumm;
    data.totalCount = totalCount;
    return data;
  }
  
  public ReportData reportByClients(Date dateFrom, Date dateTo) {
    List<Object[]> list = orderDao.reportByClients(dateFrom, dateTo);
    int totalCount = 0;
    double totalSumm = 0;
    for (Object[] arr: list) {
      double summ = (arr[1] != null ? Double.valueOf(arr[1].toString()) : 0);
      double count = (arr[2] != null ? Double.valueOf(arr[2].toString()) : 0);
      totalCount += count;
      totalSumm += summ; 
    }
    ReportData data = new ReportData();
    data.list = list;
    data.totalSumm = totalSumm;
    data.totalCount = totalCount;
    return data;
  }
  
  public void makeOrder(String fio, String address, String email, List<String> errors, HttpSession session) {
    Map<Long, Integer> cart = cartService.getCurrentCart(session);
    if (cart.isEmpty()) {
      errors.add("невозможно оформить заказ - ваша корзина пуста");
    } else {
      makeOrder(fio, address, email, cart, errors);
      if (errors.isEmpty()) {
        cartService.clearCart(session);
      }
    }
  }
  
  public List<Order> search() {
    return orderDao.getAll();
  }

  private void makeOrder(String fio, String address, String email, Map<Long, Integer> cart, List<String> errors) {
    Order order = new Order();
    order.setFio(fio);
    order.setAddress(address);
    order.setEmail(email);
    order.setCreateDate(new Date());
    if (validator.validate(errors, order)) {
      orderDao.save(order);
      for (Long productId : cart.keySet()) {
        Integer quantity = cart.get(productId);
        OrderItem item = new OrderItem();
        item.setOrder(order);
        Product product = productDao.find(productId);
        Double price = product.getPrice();
        item.setPrice(price);
        item.setProduct(product);
        item.setQuantity(quantity);
        orderItemDao.save(item);
      }
    }
  }

}
