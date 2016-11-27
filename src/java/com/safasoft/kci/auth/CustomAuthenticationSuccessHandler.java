/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.auth;

import com.safasoft.kci.bean.AudMstUsers;
import com.safasoft.kci.service.AudMstUsersService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * To do action when user have logged successfully
 * Currently used to create user session info and record user login
 * @created Apr 16, 2016
 * @author awal
 */
@Component("customAuthenticationSuccessHandler")
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler { //SimpleUrlAuthenticationSuccessHandler
 
  private final Logger authLogger = Logger.getLogger("auth");
  
  @Autowired
  private AudMstUsersService amuServ;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
          throws IOException, ServletException {    
    //login info
    HttpSession session = request.getSession();
    String cnname = (String) session.getAttribute("cnname");
    if(cnname == null || cnname.equals("")) {
      try {
        String principal = auth.getPrincipal().toString();
        int start = principal.indexOf("cn=");
        String tmp = principal.substring(start + 3);
        int end = tmp.indexOf(",");
        cnname = tmp.substring(0,end);
        session.setAttribute("cnname", cnname);
        session.setAttribute("uid", auth.getName());
        session.setAttribute("sessionid", session.getId());
        //
        AudMstUsers user = amuServ.getByUserName(auth.getName());
        session.setAttribute("hasCpts", user.getHasCpts());
        session.setAttribute("hasOrgChart", user.getHasOrgChart());
        session.setAttribute("hasReport", user.getHasReport());
      } catch(Exception ex) {
        authLogger.error(ex);
      }
    }
    //redirect
    setDefaultTargetUrl("/apps/main/welcome");
    super.onAuthenticationSuccess(request, response, auth);
  }
}
