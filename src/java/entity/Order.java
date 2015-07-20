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
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Rice Pavel
 */
@Entity
@Table(name = "orders")
public class Order {
  
  @Id
  @javax.persistence.GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "order_item_id")
  private Long orderId;
  
  @OneToMany(mappedBy = "order")
  private List<OrderItem> orderItemList;
  
  @NotEmpty(message = "ФИО - значение должно быть задано")
  @Column(name = "fio")
  private String fio;
  
  @NotEmpty(message = "Email - значение должно быть задано")
  @Column(name = "email")
  private String email;
  
  @NotEmpty(message = "ФИО - значение должно быть задано")
  @Column(name = "address")
  private String address;

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

  public String getFio() {
    return fio;
  }

  public void setFio(String fio) {
    this.fio = fio;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
  
  public double getTotalSummPrice() {
    List<OrderItem> list = getOrderItemList();
    double price = 0;
    for (OrderItem item: list) {
      price += item.getSummPrice();
    }
    return 0;
  }
  
  public double getTotalQuantity() {
    List<OrderItem> list = getOrderItemList();
    double quantity = 0;
    for (OrderItem item: list) {
      quantity += item.getQuantity();
    }
    return 0;
  }
  
}
