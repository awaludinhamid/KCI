/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.controller;

import com.safasoft.kci.bean.AudMstGrade;
import com.safasoft.kci.bean.AudMstUsers;
import com.safasoft.kci.service.AudMstGradeService;
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
@RequestMapping("/delete")
public class DeleteController {
  
  @Autowired
  private AudMstUsersService amuServ;
  @Autowired
  private AudMstGradeService amgServ;

  private final Logger logger = Logger.getLogger("controller");
  
  
  @RequestMapping(value = "/user", method = RequestMethod.DELETE)
  public @ResponseBody AudMstUsers deleteUser(HttpServletRequest request) {
    logger.debug("Received request to delete user");
    //
    AudMstUsers user = amuServ.getById(Integer.parseInt(request.getParameter("userId")));
    return amuServ.delete(user);
  }
  
  @RequestMapping(value = "/grade", method = RequestMethod.DELETE)
  public @ResponseBody AudMstGrade deleteGrade(HttpServletRequest request) {
    logger.debug("Received request to delete grade");
    //
    AudMstGrade grade = amgServ.getById(request.getParameter("gradeId"));
    return amgServ.delete(grade);
  }
}
