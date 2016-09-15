/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.controller;

import com.safasoft.kci.bean.AudMstGrade;
import com.safasoft.kci.bean.support.KciGaugeBean;
import com.safasoft.kci.bean.support.ListBean;
import com.safasoft.kci.bean.support.ListValueBean;
import com.safasoft.kci.bean.support.MapBean;
import com.safasoft.kci.bean.support.ParameterBean;
import com.safasoft.kci.service.AudMstGradeService;
import com.safasoft.kci.service.GenericService;
import com.safasoft.kci.util.SessionUtil;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
  
  @RequestMapping(value = "/mapbranch/{gradeNum}", method = RequestMethod.GET)
  public @ResponseBody List<MapBean> getMapBranch(
          HttpServletRequest httpRequest,
          HttpServletResponse httpResponse,
          @PathVariable("gradeNum") int gradeNum) {
    //
    logger.debug("Received request to get map info list");    
    //no cache applicable
    setNoCache(httpResponse);
    //
    return new SessionUtil<GenericService>().getAppContext("genericService").getMapBranch(
            gradeNum,
            httpRequest.getParameter("periode"),
            httpRequest.getParameter("coyId"),
            httpRequest.getParameter("bussUnit"),
            httpRequest.getParameter("areaId"),
            httpRequest.getParameter("officeId"),
            httpRequest.getParameter("deptId")
    );
  }
  
  @RequestMapping(value = "/mappos/{branchCode}", method = RequestMethod.GET)
  public @ResponseBody List<MapBean> getMapPos(
          HttpServletResponse httpResponse,
          @PathVariable("branchCode") String branchCode) {
    //
    logger.debug("Received request to get map pos list");
    //no cache applicable
    setNoCache(httpResponse);
    //
    return new SessionUtil<GenericService>().getAppContext("genericService").getMapPos(branchCode);
  }
  
  @RequestMapping(value = "/parameters", method = RequestMethod.GET)
  public @ResponseBody List<ParameterBean> getParameters(
          HttpServletRequest httpRequest,
          HttpServletResponse httpResponse) {
    logger.debug("Received request to get parameter list");   
    //no cache applicable
    setNoCache(httpResponse);
    //
    return new SessionUtil<GenericService>().getAppContext("genericService").getParameters(
            httpRequest.getParameter("periode"),
            httpRequest.getParameter("coyId"),
            httpRequest.getParameter("bussUnit"),
            httpRequest.getParameter("areaId"),
            httpRequest.getParameter("officeId"),
            httpRequest.getParameter("deptId")
    );
  }
  
  @RequestMapping(value = "/kcigauge", method = RequestMethod.GET)
  public @ResponseBody KciGaugeBean getKciGauge(
          HttpServletRequest httpRequest,
          HttpServletResponse httpResponse) {
    //
    logger.debug("Received request to get kci gauge value");    
    //no cache applicable
    setNoCache(httpResponse);
    //
    return new SessionUtil<GenericService>().getAppContext("genericService").getKciGauge(
            httpRequest.getParameter("periode"),
            httpRequest.getParameter("coyId"),
            httpRequest.getParameter("bussUnit"),
            httpRequest.getParameter("areaId"),
            httpRequest.getParameter("officeId"),
            httpRequest.getParameter("deptId")
    );
  }
  
  @RequestMapping(value = "/kciareas", method = RequestMethod.GET)
  public @ResponseBody List<ListValueBean> getKciAreas(
          HttpServletRequest httpRequest,
          HttpServletResponse httpResponse) {
    //
    logger.debug("Received request to get list kci area value");    
    //no cache applicable
    setNoCache(httpResponse);
    //
    return new SessionUtil<GenericService>().getAppContext("genericService").getKciAreas(
            httpRequest.getParameter("periode"),
            httpRequest.getParameter("coyId"),
            httpRequest.getParameter("bussUnit"),
            httpRequest.getParameter("deptId")
    );
  }
  
  @RequestMapping(value = "/kcibranches", method = RequestMethod.GET)
  public @ResponseBody List<ListValueBean> getKciBranches(
          HttpServletRequest httpRequest,
          HttpServletResponse httpResponse) {
    //
    logger.debug("Received request to get list kci branch value");    
    //no cache applicable
    setNoCache(httpResponse);
    //
    return new SessionUtil<GenericService>().getAppContext("genericService").getKciBranches(
            httpRequest.getParameter("periode"),
            httpRequest.getParameter("coyId"),
            httpRequest.getParameter("bussUnit"),
            httpRequest.getParameter("areaId"),
            httpRequest.getParameter("deptId")
    );
  }
  
  @RequestMapping(value = "/kcidepts", method = RequestMethod.GET)
  public @ResponseBody List<ListValueBean> getKciDepts(
          HttpServletRequest httpRequest,
          HttpServletResponse httpResponse) {
    //
    logger.debug("Received request to get list kci department value");    
    //no cache applicable
    setNoCache(httpResponse);
    //
    return new SessionUtil<GenericService>().getAppContext("genericService").getKciDepts(
            httpRequest.getParameter("periode"),
            httpRequest.getParameter("coyId"),
            httpRequest.getParameter("bussUnit"),
            httpRequest.getParameter("areaId"),
            httpRequest.getParameter("officeId"),
            httpRequest.getParameter("deptId")
    );
  }
  
  @RequestMapping(value = "/allgrades", method = RequestMethod.GET)
  public @ResponseBody List<AudMstGrade> getAllGrades() {
    logger.debug("Received request to get grades list");
    return new SessionUtil<AudMstGradeService>().getAppContext("audMstGradeService").getAll();
  }
  
  private void setNoCache(HttpServletResponse httpResponse) {
    httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
    httpResponse.setHeader("Pragma", "no-cache"); // HTTP 1.0.
    httpResponse.setDateHeader("Expires", 0); // Proxies    
  }
}
