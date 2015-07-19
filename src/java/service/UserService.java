/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Rice Pavel
 */
public class UserService {
  
  @Autowired
  private UserDao userDao;
  
}
