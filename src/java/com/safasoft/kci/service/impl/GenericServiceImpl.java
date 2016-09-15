/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.service.impl;

import com.safasoft.kci.bean.support.KciGaugeBean;
import com.safasoft.kci.bean.support.ListBean;
import com.safasoft.kci.bean.support.ListValueBean;
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
  public List<MapBean> getMapBranch(int gradeNum, String periode, String coyId, String bussUnit, String areaId, String officeId, String deptId) {
    return genericDAO.getMapBranch(gradeNum, periode, coyId, bussUnit, areaId, officeId, deptId);
  }

  @Override
  public List<MapBean> getMapPos(String officeId) {
    return genericDAO.getMapPos(officeId);
  }

  @Override
  public List<ParameterBean> getParameters(String periode, String coyId, String bussUnit, String areaId, String officeId, String deptId) {
    return genericDAO.getParameters(periode, coyId, bussUnit, areaId, officeId, deptId);
  }

  @Override
  public List<ListBean> getAllBussUnits() {
    return genericDAO.getAllBussUnits();
  }

  @Override
  public KciGaugeBean getKciGauge(String periode, String coyId, String bussUnit, String areaId, String officeId, String deptId) {
    return genericDAO.getKciGauge(periode, coyId, bussUnit, areaId, officeId, deptId);
  }

  @Override
  public List<ListValueBean> getKciAreas(String periode, String coyId, String bussUnit, String deptId) {
    return genericDAO.getKciAreas(periode, coyId, bussUnit, deptId);
  }

  @Override
  public List<ListValueBean> getKciBranches(String periode, String coyId, String bussUnit, String areaId, String deptId) {
    return genericDAO.getKciBranches(periode, coyId, bussUnit, areaId, deptId);
  }

  @Override
  public List<ListValueBean> getKciDepts(String periode, String coyId, String bussUnit, String areaId, String officeId, String deptId) {
    return genericDAO.getKciDepts(periode, coyId, bussUnit, areaId, officeId, deptId);
  }
  
}
