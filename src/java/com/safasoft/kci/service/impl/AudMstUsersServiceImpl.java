/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.service.impl;

import com.safasoft.kci.bean.AudMstUsers;
import com.safasoft.kci.dao.AudMstUsersDAO;
import com.safasoft.kci.service.AudMstUsersService;
import com.safasoft.kci.util.GlobalIntVariable;
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
  private final int resultPerPage = GlobalIntVariable.RESULT_PER_PAGE.getVar();

  @Override
  @Transactional(readOnly=false)
  public AudMstUsers save(AudMstUsers user) {
    return audMstUsersDAO.save(user);
  }

  @Override
  @Transactional(readOnly=false)
  public AudMstUsers delete(AudMstUsers user) {
    return audMstUsersDAO.delete(user);
  }

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

  @Override
  public List<AudMstUsers> getByPage(int pageNo) {
    return audMstUsersDAO.getByRange((pageNo - 1) * resultPerPage, resultPerPage);
  }

  @Override
  public List<AudMstUsers> getByPageNameAndRole(String emplNamePattern, int roleId, int pageNo) {
    return audMstUsersDAO.getByRangeNameAndRole(emplNamePattern, roleId, (pageNo - 1) * resultPerPage, resultPerPage);
  }

  @Override
  public int count() {
    return audMstUsersDAO.count();
  }

  @Override
  public int count(String emplNamePattern, int roleId) {
    return audMstUsersDAO.count(emplNamePattern, roleId);
  }

  @Override
  public List<AudMstUsers> getByPageName(String emplNamePattern, int pageNo) {
    return audMstUsersDAO.getByRangeName(emplNamePattern, (pageNo - 1) * resultPerPage, resultPerPage);
  }

  @Override
  public int count(String emplNamePattern) {
    return audMstUsersDAO.count(emplNamePattern);
  }
}
