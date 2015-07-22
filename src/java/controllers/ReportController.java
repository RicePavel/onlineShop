/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import datastructure.ReportData;
import entity.Category;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.CategoryService;
import service.OrderService;
import support.ConvertUtils;
import support.DateFormatUtils;
import support.DateUtils;
import support.NumberFormatUtils;

/**
 *
 * @author Rice Pavel
 */
@Controller
@RequestMapping("/report")
public class ReportController extends WebController {
  
  @Autowired
  private OrderService orderService;
  
  @Autowired
  private CategoryService categoryService;
  
  @RequestMapping("/category")
  public String byCategories(Map<String, Object> model, 
          @RequestParam(value = "date_from", required = false) String dateFromStr,
          @RequestParam(value = "date_to", required = false) String dateToStr) {
    Date dateFrom = getDateFrom(dateFromStr);
    Date dateTo = getDateTo(dateToStr);
    ReportData data = orderService.reportByCategories(dateFrom, dateTo);
    model.put("list", data.list);
    model.put("totalCount", data.totalCount);
    model.put("totalSumm", data.totalSumm);
    model.put("jsonString", getJsonString(data.list));
    model.put("dateFrom", DateFormatUtils.date(dateFrom));
    model.put("dateTo", DateFormatUtils.date(dateTo));
    return "report_category";
  }
  
  @RequestMapping("/product")
  public String byProducts(Map<String, Object> model, 
          @RequestParam(value = "date_from", required = false) String dateFromStr,
          @RequestParam(value = "date_to", required = false) String dateToStr,
          @RequestParam(value = "categoryId", required = false) Long categoryId) {
    Date dateFrom = getDateFrom(dateFromStr);
    Date dateTo = getDateTo(dateToStr);
    ReportData data = orderService.reportByProducts(categoryId, dateFrom, dateTo);
    model.put("list", data.list);
    model.put("totalCount", data.totalCount);
    model.put("totalSumm", data.totalSumm);
    model.put("category", categoryService.find(categoryId));
    model.put("dateFrom", DateFormatUtils.date(dateFrom));
    model.put("dateTo", DateFormatUtils.date(dateTo));
    return "report_product";
  }
  
  @RequestMapping("/client")
  public String byClients(Map<String, Object> model, 
         @RequestParam(value = "date_from", required = false) String dateFromStr,
          @RequestParam(value = "date_to", required = false) String dateToStr) {
    Date dateFrom = getDateFrom(dateFromStr);
    Date dateTo = getDateTo(dateToStr);
    ReportData data = orderService.reportByClients(dateFrom, dateTo);
    model.put("list", data.list);
    model.put("totalCount", data.totalCount);
    model.put("totalSumm", data.totalSumm);
    model.put("dateFrom", DateFormatUtils.date(dateFrom));
    model.put("dateTo", DateFormatUtils.date(dateTo));
    return "report_client";
  }
  

  
  private String getJsonString(List<Object[]> list) {
    String jsonStr = "";
    int n = 0;
    for (Object[] arr: list) {
      Category category = (Category) arr[0];
      BigDecimal totalSumm = (arr[1] != null ? new BigDecimal(arr[1].toString()) : new BigDecimal(0));
      totalSumm = totalSumm.setScale(2, RoundingMode.HALF_UP);
      String summString = NumberFormatUtils.many(totalSumm);
      jsonStr += "['" + category.getName() + "', " + summString + "]";
      if (n != arr.length) {
        jsonStr += ",";
      }
      n++;
    }
    return jsonStr;
  }
  
  private Date getDateFrom(String dateFromStr) {
    Date date;
    if (dateFromStr != null && !dateFromStr.isEmpty()) {
      date = ConvertUtils.getDate(dateFromStr);
    } else {
      Calendar cl = Calendar.getInstance();
      cl.set(Calendar.DAY_OF_MONTH, 1);
      date = cl.getTime();
    }
    return DateUtils.startOfDay(date);
  }
  
  private Date getDateTo(String dateToStr) {
    Date date;
    if (dateToStr != null && !dateToStr.isEmpty()) {
      date = ConvertUtils.getDate(dateToStr);
    } else {
      date = new Date();
    }
    return DateUtils.endOfDay(date);
  }
  
}
