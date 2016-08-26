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
 * @created Aug 9, 2016
 * @author awal
 */
@Controller
@RequestMapping("/error")
public class ErrorController {

 private final Logger logger = Logger.getLogger("controller");

 /**
  * Handles and retrieves the error 500 JSP page
  *
   * @param httpRequest
  * @return the name of the JSP page
  */
    @RequestMapping(value = "/error500", method = RequestMethod.GET)
    public String getError500Page(HttpServletRequest httpRequest) {
     logger.debug("Received request to show error 500 page");
     // This will resolve to /jsp/error/error500page.jsp
     return "error/error500page";
 }
}
