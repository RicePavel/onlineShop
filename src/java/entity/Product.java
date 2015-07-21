/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Новый профиль
 */
@Entity
@Table(name = "product")
public class Product {

  @Id
  @javax.persistence.GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "product_id")
  private Long productId;

  @NotEmpty(message = "не передан параметр - название")
  @Column(name = "name")
  private String name;

  @Type(type = "text")
  @Column(name = "description")
  private String description;

  @NotNull(message = "не передан параметр - цена")
  @Column(name = "price")
  private Double price;

  @NotNull(message = "не передан параметр - категория")
  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "close_date")
  private Date closeDate;

  @OneToOne(mappedBy = "product")
  private ProductFile file;

  private String imgContent;
  
  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public Date getCloseDate() {
    return closeDate;
  }

  public void setCloseDate(Date closeDate) {
    this.closeDate = closeDate;
  }

  public ProductFile getFile() {
    return file;
  }

  public void setFile(ProductFile file) {
    this.file = file;
  }

  public String getImgContent() {
    return imgContent;
  }

  public void setImgContent(String imgContent) {
    this.imgContent = imgContent;
  }
  
  

}
