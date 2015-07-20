/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package support;

/**
 *
 * @author Rice Pavel
 */
public class ConvertUtils {
  
  public static Double getDouble(String str) {
    try {
      str = str.replaceAll(" ", "");
      return Double.parseDouble(str);
    } catch (Exception e) {
      return null;
    }
  }
  
}
