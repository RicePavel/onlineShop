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
@Table(name = "product_file")
public class ProductFile {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "product_file_id")
  private Long productFileId;

  @NotNull
  @OneToOne
  @JoinColumn(name = "product_id")
  private Product product;

  @NotNull
  @Column(name = "name")
  private String name;

  public Long getProductFileId() {
    return productFileId;
  }

  public void setProductFileId(Long productFileId) {
    this.productFileId = productFileId;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
