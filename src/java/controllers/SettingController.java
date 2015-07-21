/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.Setting;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.SettingService;

/**
 *
 * @author Rice Pavel
 */
@Controller
@RequestMapping("/setting")
public class SettingController extends WebController {
  
  @Autowired
  private SettingService settingService;
  
  @RequestMapping("/show")
  public String show(Map<String, Object> model)  {
    Setting setting = settingService.getSetting();
    model.put("setting", setting);
    return "setting_show";
  }
  
  @RequestMapping("/change")
  public String change(Map<String, Object> model,
         @RequestParam(value = "filePath", required = false) String filePath,
         RedirectAttributes ra) {
    List<String> errors = new ArrayList();
    settingService.setSetting(filePath, errors);
    if (!errors.isEmpty()) {
      ra.addFlashAttribute("errors", errors);
    }
    return "redirect:/setting/show";
  }
  
}
