/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.User;

/**
 *
 * @author Новый профиль
 */
public class UserDao extends Dao<User> {
  
  @Override
  public Class getSupportedClass() {
    return User.class;
  }
  
}
