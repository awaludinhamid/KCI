/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.controller;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Page controller
 * Handles and retrieves various page depending on the URI template.
 * A user must be login first to access these pages.
 * Specific page can be accessed by specific user, however.
 * @created Jun 19, 2015
 * @author awal
 */
@Controller
@RequestMapping("/main")
public class MainController {

 private final Logger logger = Logger.getLogger("controller");

 /**
  * Handles and retrieves the dashboard JSP page
  *
   * @param httpRequest
  * @return the name of the JSP page
  */
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String getDashboardPage(HttpServletRequest httpRequest) {
     logger.debug("Received request to show dashboard page");
     // This will resolve to /jsp/dashboardpage.jsp
     return "dashboardpage";
 }

 /**
  * Handles and retrieves the welcome JSP page
  *
   * @param httpRequest
  * @return the name of the JSP page
  */
    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String getWelcomePage(HttpServletRequest httpRequest) {
     logger.debug("Received request to show welcome page");
     // This will resolve to /jsp/welcomepage.jsp
     return "welcomepage";
 }

 /**
  * Handles and retrieves the admin JSP page
  *
   * @param httpRequest
  * @return the name of the JSP page
  */
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String getAdminPage(HttpServletRequest httpRequest) {
     logger.debug("Received request to show admin page");
     // This will resolve to /jsp/adminpage.jsp
     return "adminpage";
 }
}
