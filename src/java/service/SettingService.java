/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.SettingDao;
import entity.Setting;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import support.EntityValidator;

/**
 *
 * @author Rice Pavel
 */
@Service
@Transactional
public class SettingService {
  
  @Autowired
  private SettingDao settingDao;
  
  @Autowired
  private EntityValidator validator;
  
  public Setting getSetting() {
    List<Setting> list = settingDao.getAll();
    if (!list.isEmpty()) {
      return list.get(0);
    } else {
      return new Setting();
    }
  }
  
  public void setSetting(String filePath, List<String> errors) {
    List<Setting> list = settingDao.getAll();
    Setting setting;
    if (!list.isEmpty()) {
      setting = list.get(0);
    } else {
      setting = new Setting();
    }
    setting.setFilePath(filePath);
    boolean ok = validator.validate(errors, setting);
    if (ok) {
      settingDao.saveOrUpdate(setting);
    } else {
      settingDao.evict(setting);
    }
  }
  
}
