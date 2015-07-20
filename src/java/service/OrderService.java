/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.OrderDao;
import dao.OrderItemDao;
import dao.ProductDao;
import entity.Order;
import entity.OrderItem;
import entity.Product;
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
