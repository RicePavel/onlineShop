/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Setting;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Rice Pavel
 */
@Repository
public class SettingDao extends Dao<Setting> {

  @Override
  public Class getSupportedClass() {
    return Setting.class;
  }

}
