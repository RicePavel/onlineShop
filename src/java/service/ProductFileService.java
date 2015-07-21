/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ProductFileDao;
import entity.Product;
import entity.ProductFile;
import entity.Setting;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import support.ImgUtils;
import support.MyFileUtils;

/**
 *
 * @author Rice Pavel
 */
@Service
@Transactional
public class ProductFileService {

  @Autowired
  private ProductFileDao productFileDao;

  @Autowired
  private SettingService settingService;

  public void save(MultipartFile file, Product product, List<String> errors) throws IOException {
    if (file != null && !file.isEmpty()) {
      Setting setting = settingService.getSetting();
      if (setting != null && setting.getFilePath() != null && !setting.getFilePath().isEmpty()) {
        String filePath = setting.getFilePath();
        if (pathExists(filePath)) {
          if (fileIsImg(file)) {
            deleteOldFiles(product);
            Long fileId = saveFileToBase(product, file);
            String fileName = getProductFileName(filePath, fileId);
            writeFile(file, fileName);
          } else {
            errors.add("Загруженный файл должен являться изображением");
          }
        } else {
          errors.add("Невозможно сохнарить файл - директория, указанная в настройках, не существует.");
        }
      } else {
        errors.add("Невозможно сохранить файл - в настройках не установлен путь к файлам.");
      }
    }
  }
  
  public String getImgContent(Product product) throws IOException {
    if (product.getFile() != null) {
      ProductFile fileEntity = product.getFile();
      Setting setting = settingService.getSetting();
      if (setting != null) {
        String filePath = setting.getFilePath();
        String fileName = getProductFileName(filePath, fileEntity.getProductFileId());
        File file = new File(fileName);
        if (file.exists()) {
          return ImgUtils.toBase64(file);
        }
      }
    }
    return "";
  }

  private boolean fileIsImg(MultipartFile file) {
    String fileName = file.getOriginalFilename();
    List<String> fileTypes = Arrays.asList(new String[]{"jpg", "png", "gif"});
    String ext = MyFileUtils.getFileType(fileName);
    return fileTypes.contains(ext);
  }

  private boolean pathExists(String filePath) {
    File file = new File(filePath);
    return (file.exists() && file.isDirectory());
  }

  private String getProductFileName(String filePath, Long fileId) {
    return filePath + System.getProperty("file.separator") + fileId;
  }

  private void writeFile(MultipartFile file, String fileName) throws IOException {
    File newFile = new File(fileName);
    FileUtils.writeByteArrayToFile(newFile, file.getBytes());
  }

  private Long saveFileToBase(Product product, MultipartFile file) {
    ProductFile fileEntity = new ProductFile();
    fileEntity.setName(file.getOriginalFilename());
    fileEntity.setProduct(product);
    return productFileDao.save(fileEntity);
  }

  private void deleteOldFiles(Product product) {
    ProductFile file = product.getFile();
    if (file != null) {
      productFileDao.delete(file);
    }
  }

}
