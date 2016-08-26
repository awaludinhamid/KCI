/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.controller;

import com.safasoft.kci.bean.support.ListBean;
import com.safasoft.kci.bean.support.MapBean;
import com.safasoft.kci.bean.support.ParameterBean;
import com.safasoft.kci.service.GenericService;
import com.safasoft.kci.util.SessionUtil;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @created Aug 17, 2016
 * @author awal
 */
@Controller
@RequestMapping("/data")
public class DataController {

  private final Logger logger = Logger.getLogger("controller");
  
  @RequestMapping(value = "/branches/{areaCode}", method = RequestMethod.GET)
  public @ResponseBody List<ListBean> getAllBranches(@PathVariable("areaCode") String areaCode) {
    logger.debug("Received request to get branches list");
    return new SessionUtil<GenericService>().getAppContext("genericService").getBranches(areaCode);
  }
  
  @RequestMapping(value = "/allcompanies", method = RequestMethod.GET)
  public @ResponseBody List<ListBean> getAllCompanies() {
    logger.debug("Received request to get companies list");
    return new SessionUtil<GenericService>().getAppContext("genericService").getAllCompanies();
  }
  
  @RequestMapping(value = "/allperiodes", method = RequestMethod.GET)
  public @ResponseBody List<ListBean> getAllPeriodes() {
    logger.debug("Received request to get periode list");
    return new SessionUtil<GenericService>().getAppContext("genericService").getAllPeriodes();
  }
  
  @RequestMapping(value = "/alldepartments", method = RequestMethod.GET)
  public @ResponseBody List<ListBean> getAllDepartments() {
    logger.debug("Received request to get department list");
    return new SessionUtil<GenericService>().getAppContext("genericService").getAllDepartments();
  }
  
  @RequestMapping(value = "/allregions", method = RequestMethod.GET)
  public @ResponseBody List<ListBean> getAllRegions() {
    logger.debug("Received request to get region list");
    return new SessionUtil<GenericService>().getAppContext("genericService").getAllRegions();
  }
  
  @RequestMapping(value = "/allbussunits", method = RequestMethod.GET)
  public @ResponseBody List<ListBean> getAllBussUnits() {
    logger.debug("Received request to get buss unit list");
    return new SessionUtil<GenericService>().getAppContext("genericService").getAllBussUnits();
  }
  
  @RequestMapping(value = "/mapbranch", method = RequestMethod.GET)
  public @ResponseBody List<MapBean> getMapBranch() {
    logger.debug("Received request to get map info list");
    return new SessionUtil<GenericService>().getAppContext("genericService").getMapBranch();
  }
  
  @RequestMapping(value = "/mappos/{branchCode}", method = RequestMethod.GET)
  public @ResponseBody List<MapBean> getMapPos(@PathVariable("branchCode") String branchCode) {
    logger.debug("Received request to get map pos list");
    return new SessionUtil<GenericService>().getAppContext("genericService").getMapPos(branchCode);
  }
  
  @RequestMapping(value = "/parameter/{deptId}", method = RequestMethod.GET)
  public @ResponseBody List<ParameterBean> getParameters(@PathVariable("deptId") String deptId) {
    logger.debug("Received request to get parameter list");
    return new SessionUtil<GenericService>().getAppContext("genericService").getParmeters(deptId);
  }
}
