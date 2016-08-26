/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.service.impl;

import com.safasoft.kci.bean.AudMstRoles;
import com.safasoft.kci.dao.AudMstRolesDAO;
import com.safasoft.kci.service.AudMstRolesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @created Apr 22, 2016
 * @author awal
 */
@Service("audMstRolesService")
@Transactional(readOnly=true)
public class AudMstRolesServiceImpl implements AudMstRolesService {

  @Autowired
  private AudMstRolesDAO audMstRolesDAO;

  @Override
  public AudMstRoles getById(int id) {
    return audMstRolesDAO.getById(id);
  }

  @Override
  public List<AudMstRoles> getAll() {
    return audMstRolesDAO.getAll();
  }
  
}
