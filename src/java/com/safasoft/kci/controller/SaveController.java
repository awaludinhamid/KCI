/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.controller;

import com.safasoft.kci.bean.AudMstGrade;
import com.safasoft.kci.bean.AudMstParameter;
import com.safasoft.kci.bean.AudMstUsers;
import com.safasoft.kci.service.AudMstDepartmentService;
import com.safasoft.kci.service.AudMstEmployeesHcmsService;
import com.safasoft.kci.service.AudMstGradeService;
import com.safasoft.kci.service.AudMstParameterService;
import com.safasoft.kci.service.AudMstRolesService;
import com.safasoft.kci.service.AudMstUsersService;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @created Nov 15, 2016
 * @author awal
 */
@Controller
@RequestMapping("/save")
public class SaveController {
  
  @Autowired
  private AudMstUsersService amuServ;
  @Autowired
  private AudMstEmployeesHcmsService amehServ;
  @Autowired
  private AudMstRolesService amrServ;
  @Autowired
  private AudMstDepartmentService amdServ;
  @Autowired
  private AudMstParameterService ampServ;
  @Autowired
  private AudMstGradeService amgServ;

  private final Logger logger = Logger.getLogger("controller");  
  
  @RequestMapping(value = "/user", method = RequestMethod.POST)
  public @ResponseBody AudMstUsers saveUser(HttpServletRequest request) {
    logger.debug("Received request to save user");
    //
    AudMstUsers user = new AudMstUsers();
    user.setRole(amrServ.getById(2));//role user default
    user.setEmployee(amehServ.getById(request.getParameter("npk")));
    user.setHasCpts(request.getParameter("hasCpts"));
    user.setHasOrgChart(request.getParameter("hasOrgChart"));
    user.setHasReport(request.getParameter("hasReport"));
    return amuServ.save(user);
  }  
  
  @RequestMapping(value = "/parameter", method = RequestMethod.POST)
  public @ResponseBody AudMstParameter saveParameter(HttpServletRequest request) {
    logger.debug("Received request to save parameter");
    //
    AudMstParameter param = new AudMstParameter();    
    param.setParameterId(request.getParameter("parameterId"));
    param.setParameterDesc(request.getParameter("parameterDesc"));
    param.setDept(amdServ.getById(request.getParameter("deptId")));
    param.setPackageName(request.getParameter("packageName"));
    param.setVisible(request.getParameter("visible"));    
    return ampServ.save(param);
  } 
  
  @RequestMapping(value = "/grade", method = RequestMethod.POST)
  public @ResponseBody AudMstGrade saveGrade(HttpServletRequest request) {
    logger.debug("Received request to save grade");
    //
    AudMstGrade grade = new AudMstGrade();   
    grade.setGradeId(request.getParameter("gradeId"));
    grade.setGradeName(request.getParameter("gradeName"));
    grade.setBatasBawah(Double.parseDouble(request.getParameter("batasBawah")));
    grade.setBatasAtas(Double.parseDouble(request.getParameter("batasAtas")));
    return amgServ.save(grade);
  }
}
