/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.cart;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Rice Pavel
 */
public class CartInfo {
  
    public final int count;
  
  public final BigDecimal summ;
  
  public final List<CartInfoItem> items;
  
  public CartInfo(int count, BigDecimal summ, List<CartInfoItem> items) {
    this.count = count;
    this.summ = summ;
    this.items = items;
  }

  public int getCount() {
    return count;
  }

  public BigDecimal getSumm() {
    return summ;
  }

  public List<CartInfoItem> getItems() {
    return items;
  }
  
  
  
}
