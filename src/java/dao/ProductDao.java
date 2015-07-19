/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Category;
import entity.Product;

/**
 *
 * @author Rice Pavel
 */
public class ProductDao extends Dao<Product> {
  
  @Override
  public Class getSupportedClass() {
    return Product.class;
  }
  
}
