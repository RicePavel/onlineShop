/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.validator.constraints.Email;
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

  @Email(message = "Email - значение должно представлять собой email-адрес")
  @NotEmpty(message = "Email - значение должно быть задано")
  @Column(name = "email")
  private String email;

  @NotEmpty(message = "ФИО - значение должно быть задано")
  @Column(name = "address")
  private String address;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "create_date")
  private Date createDate;

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

  public BigDecimal getTotalSummPrice() {
    List<OrderItem> list = getOrderItemList();
    BigDecimal price = new BigDecimal("0");
    for (OrderItem item : list) {
      price = price.add(item.getSummPrice());
    }
    return price;
  }

  public int getTotalQuantity() {
    List<OrderItem> list = getOrderItemList();
    int quantity = 0;
    for (OrderItem item : list) {
      quantity += item.getQuantity();
    }
    return quantity;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }
  
  

}
