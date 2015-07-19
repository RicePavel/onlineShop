/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.cart;

/**
 *
 * @author Rice Pavel
 */
public class CartInfo {
  
  public CartInfo(int count, double summ) {
    this.count = count;
    this.summ = summ;
  }
  
  public final int count;
  
  public final double summ;
  
}
