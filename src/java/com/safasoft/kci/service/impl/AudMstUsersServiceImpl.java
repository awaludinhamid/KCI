/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.service.impl;

import com.safasoft.kci.bean.AudMstUsers;
import com.safasoft.kci.dao.AudMstUsersDAO;
import com.safasoft.kci.service.AudMstUsersService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @created Apr 22, 2016
 * @author awal
 */
@Service("audMstUsersService")
@Transactional(readOnly=true)
public class AudMstUsersServiceImpl implements AudMstUsersService {

  @Autowired
  private AudMstUsersDAO audMstUsersDAO;

  @Override
  public AudMstUsers getById(int id) {
    return audMstUsersDAO.getById(id);
  }

  @Override
  public List<AudMstUsers> getAll() {
    return audMstUsersDAO.getAll();
  }

  @Override
  public AudMstUsers getByUserName(String userName) {
    return audMstUsersDAO.getByUserName(userName);
  }
}
