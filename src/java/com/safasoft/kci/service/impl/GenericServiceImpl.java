/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.service.impl;

import com.safasoft.kci.bean.support.ListBean;
import com.safasoft.kci.bean.support.MapBean;
import com.safasoft.kci.bean.support.ParameterBean;
import com.safasoft.kci.dao.GenericDAO;
import com.safasoft.kci.service.GenericService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @created Aug 17, 2016
 * @author awal
 */
@Service("genericService")
@Transactional(readOnly=true)
public class GenericServiceImpl implements GenericService {

  @Autowired
  private GenericDAO genericDAO;

  @Override
  public List<ListBean> getBranches(String areaCode) {
    return genericDAO.getBranches(areaCode);
  }

  @Override
  public List<ListBean> getAllCompanies() {
    return genericDAO.getAllCompanies();
  }

  @Override
  public List<ListBean> getAllPeriodes() {
    return genericDAO.getAllPeriodes();
  }

  @Override
  public List<ListBean> getAllDepartments() {
    return genericDAO.getAllDepartments();
  }

  @Override
  public List<ListBean> getAllRegions() {
    return genericDAO.getAllRegions();
  }

  @Override
  public List<MapBean> getMapBranch() {
    return genericDAO.getMapBranch();
  }

  @Override
  public List<MapBean> getMapPos(String officeId) {
    return genericDAO.getMapPos(officeId);
  }

  @Override
  public List<ParameterBean> getParmeters(String deptId) {
    return genericDAO.getParmeters(deptId);
  }

  @Override
  public List<ListBean> getAllBussUnits() {
    return genericDAO.getAllBussUnits();
  }
  
}
