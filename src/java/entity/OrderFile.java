/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Rice Pavel
 */
@Entity
@Table(name = "order_file")
public class OrderFile {
  
   @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "order_file_id")
  private Long orderFileId;
   
   @NotNull
   @OneToOne
   @JoinColumn(name = "order_id")
   private Order order;
  
   @NotNull
   @Column(name = "name")
   private String name;

  public Long getOrderFileId() {
    return orderFileId;
  }

  public void setOrderFileId(Long orderFileId) {
    this.orderFileId = orderFileId;
  }

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
   
   
   
}
