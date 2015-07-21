/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package support;

import com.mchange.io.FileUtils;

import java.io.File;
import java.io.IOException;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Rice Pavel
 */
public class ImgUtils {
  
  public static String toBase64(File file) throws IOException {
    byte[] bytes = FileUtils.getBytes(file);
    String fileContent = Base64.encodeBase64String(bytes);
    return "data:image/gif;base64," + fileContent;
  }
  
}
