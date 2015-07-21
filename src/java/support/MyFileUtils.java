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
public class MyFileUtils {

  public static String getFileType(String fileName) {
    String[] str = fileName.split("\\.");
    if (str.length != 0) {
      return str[str.length - 1];
    } else {
      return "";
    }
  }

}
