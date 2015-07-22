/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startup;

import entity.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import service.UserService;
import static sun.security.jgss.GSSUtil.login;

/**
 *
 * @author Rice Pavel
 */
@Component
public class Startup implements ApplicationListener<ContextRefreshedEvent> {

  @Autowired
  private UserService userService;

  @Override
  public void onApplicationEvent(final ContextRefreshedEvent event) {
    addTestUsers();
  }

  private void addTestUsers() {
    String login = "admin";
    List<User> adminUsers = userService.getUsersByLogin(login);
    if (adminUsers == null || adminUsers.isEmpty()) {
      String password = "qwerty";
      List<String> errors = new ArrayList();
      userService.registration(login, password, errors);
    }
  }

}
