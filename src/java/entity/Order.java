/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Rice Pavel
 */
@Entity
@Table(name = "order_item")
public class Order {
  
  @Id
  @javax.persistence.GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "order_item_id")
  private Long orderId;
  
  @OneToMany(mappedBy = "order")
  private List<OrderItem> orderItemList;

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public List<OrderItem> getOrderItemList() {
    return orderItemList;
  }

  public void setOrderItemList(List<OrderItem> orderItemList) {
    this.orderItemList = orderItemList;
  }
  
  
  
}
