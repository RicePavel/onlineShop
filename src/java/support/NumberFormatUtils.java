/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package support;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 *
 * @author Rice Pavel
 */
public class NumberFormatUtils {
  
  public static String many(double value) {
    return manyFromObject(value);
  }
  
  public static String many(BigDecimal value) {
    return manyFromObject(value);
  }
  
  private static String manyFromObject(Object value) {
   DecimalFormatSymbols dfs = new DecimalFormatSymbols();
    dfs.setDecimalSeparator('.');
    DecimalFormat formatter = new DecimalFormat("###.##");
    formatter.setDecimalFormatSymbols(dfs);
    formatter.setMinimumFractionDigits(2);
    formatter.setMaximumFractionDigits(2);
    formatter.setMinimumIntegerDigits(1);
    return formatter.format(value); 
  }
  
}
