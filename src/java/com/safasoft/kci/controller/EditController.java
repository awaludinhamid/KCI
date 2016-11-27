/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.controller;

import com.safasoft.kci.bean.AudMstGrade;
import com.safasoft.kci.bean.AudMstParameter;
import com.safasoft.kci.bean.AudMstUsers;
import com.safasoft.kci.service.AudLogMonitoringService;
import com.safasoft.kci.service.AudMstGradeService;
import com.safasoft.kci.service.AudMstParameterService;
import com.safasoft.kci.service.AudMstUsersService;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @created Nov 17, 2016
 * @author awal
 */
@Controller
@RequestMapping("/edit")
public class EditController {
  
  @Autowired
  private AudMstUsersService amuServ;
  @Autowired
  private AudMstParameterService ampServ;
  @Autowired
  private AudLogMonitoringService almServ;
  @Autowired
  private AudMstGradeService amgServ;

  private final Logger logger = Logger.getLogger("controller");  
  
  @RequestMapping(value = "/user", method = RequestMethod.PUT)
  public @ResponseBody AudMstUsers saveUser(HttpServletRequest request) {
    logger.debug("Received request to save user");
    //
    AudMstUsers user = amuServ.getById(Integer.parseInt(request.getParameter("userId")));
    user.setHasCpts(request.getParameter("hasCpts"));
    user.setHasOrgChart(request.getParameter("hasOrgChart"));
    user.setHasReport(request.getParameter("hasReport"));
    return amuServ.save(user);
  }
  
  @RequestMapping(value = "/parameter", method = RequestMethod.PUT)
  public @ResponseBody AudMstParameter saveParameter(HttpServletRequest request) {
    logger.debug("Received request to save parameter");
    //
    AudMstParameter param = ampServ.getById(request.getParameter("parameterId"));
    param.setPackageName(request.getParameter("packageName"));
    param.setVisible(request.getParameter("visible"));    
    return ampServ.save(param);
  }
  
  @RequestMapping(value = "/runjob/{jobName}", method = RequestMethod.PUT)
  public @ResponseBody Integer runJob(@PathVariable("jobName") String jobName) {
    logger.debug("Received request to run job");
    //    
    return almServ.runJob(jobName);
  }
  
  @RequestMapping(value = "/grade", method = RequestMethod.PUT)
  public @ResponseBody AudMstGrade saveGrade(HttpServletRequest request) {
    logger.debug("Received request to save grade");
    //
    AudMstGrade grade = amgServ.getById(request.getParameter("gradeId"));
    grade.setGradeName(request.getParameter("gradeName"));
    grade.setBatasBawah(Double.parseDouble(request.getParameter("batasBawah")));
    grade.setBatasAtas(Double.parseDouble(request.getParameter("batasAtas")));
    return amgServ.save(grade);
  }

}
