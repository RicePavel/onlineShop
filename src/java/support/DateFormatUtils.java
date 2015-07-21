/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package support;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Rice Pavel
 */
public class DateFormatUtils {

  public static String date(Date date) {
    if (date != null) {
      SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy");
      return f.format(date);
    } else {
      return "";
    }
  }

  public static String datetime(Date date) {
    if (date != null) {
      SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy HH:mm");
      return f.format(date);
    } else {
      return "";
    }
  }

  public static String format(Date date, String pattern) {
    if (date != null) {
      SimpleDateFormat f = new SimpleDateFormat(pattern);
      return f.format(date);
    } else {
      return "";
    }
  }

}
