/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.auth;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Login controller
 * @created Jun 19, 2015
 * @author awal
 */

/**
 * Handles and retrieves the login or denied page depending on the URI template
 */
@Controller
@RequestMapping("/auth")
public class LoginLogoutController {

 private final Logger logger = Logger.getLogger("auth");

 /**
  * Handles and retrieves the login JSP page
  *
  * @param error
  * @param model
  * @param httpRequest
  * @return the name of the JSP page
  */
 @RequestMapping(value = "/login", method = RequestMethod.GET)
 public String getLoginPage(@RequestParam(value="error", required=false) boolean error,
   ModelMap model, HttpServletRequest httpRequest) {
  logger.debug("Received request to show login page");

  // Add an error message to the model if login is unsuccessful
  // The 'error' parameter is set to true based on the when the authentication has failed.
  // We declared this under the authentication-failure-url attribute inside the spring-security.xml
  /* See below:
   <form-login
    login-page="/apps/auth/login"
    authentication-failure-url="/apps/auth/login?error=true"
    default-target-url="/apps/main/home"/>
   */
  if (error == true) {
   // Assign an error message
   model.put("error", "You have entered an invalid username or password!");
  } else {
   model.put("error", "");
   httpRequest.getSession().setAttribute("cnname","");
  }

  // This will resolve to /jsp/loginpage.jsp
  return "loginpage";
 }

 /**
  * Handles and retrieves the denied JSP page. This is shown whenever user tries to access the unauthorized page.
  *
  * @return the name of the JSP page
  */
 @RequestMapping(value = "/denied", method = RequestMethod.GET)
  public String getDeniedPage() {
  logger.debug("Received request to show denied page");

  // This will resolve to /jsp/deniedpage.jsp
  return "deniedpage";
 }
  
  @RequestMapping(value = "/currentsession", method = RequestMethod.GET)
  public @ResponseBody String getCurrentSession(HttpServletRequest httpRequest) {
    logger.debug("Received request to get current session");
    return httpRequest.getSession().getId();
  }

}
