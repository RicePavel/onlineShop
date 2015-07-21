 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package support;

import java.util.Calendar;
import java.util.Date;

/**
 * класс для работы с датами
 *
 * @author Rice Pavel
 */
public abstract class DateUtils {

  public static Date startOfDay(Date date) {
    if (date == null) {
      return null;
    } else {
      Calendar cl = Calendar.getInstance();
      cl.setTime(date);
      cl.set(Calendar.HOUR_OF_DAY, 0);
      cl.set(Calendar.MINUTE, 0);
      cl.set(Calendar.SECOND, 0);
      cl.set(Calendar.MILLISECOND, 0);
      return cl.getTime();
    }
  }

  public static Date endOfDay(Date date) {
    if (date == null) {
      return null;
    } else {
      Calendar cl = Calendar.getInstance();
      cl.setTime(date);
      cl.set(Calendar.HOUR_OF_DAY, 23);
      cl.set(Calendar.MINUTE, 59);
      cl.set(Calendar.SECOND, 59);
      return cl.getTime();
    }
  }

}
