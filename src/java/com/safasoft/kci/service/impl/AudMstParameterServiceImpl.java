/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.service.impl;

import com.safasoft.kci.bean.AudMstParameter;
import com.safasoft.kci.dao.AudMstParameterDAO;
import com.safasoft.kci.service.AudMstParameterService;
import com.safasoft.kci.util.GlobalIntVariable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @created Nov 18, 2016
 * @author awal
 */
@Service("audMstParameterService")
@Transactional(readOnly=true)
public class AudMstParameterServiceImpl implements AudMstParameterService {

  @Autowired
  private AudMstParameterDAO audMstParameterDAO;
  private final int resultPerPage = GlobalIntVariable.RESULT_PER_PAGE.getVar();

  @Override
  @Transactional(readOnly=false)
  public AudMstParameter save(AudMstParameter amp) {
    return audMstParameterDAO.save(amp);
  }

  @Override
  @Transactional(readOnly=false)
  public AudMstParameter delete(AudMstParameter amp) {
    return audMstParameterDAO.delete(amp);
  }

  @Override
  public List<AudMstParameter> getByPage(int pageNo) {
    return audMstParameterDAO.getByRange((pageNo - 1) * resultPerPage, resultPerPage);
  }

  @Override
  public List<AudMstParameter> getByPageParamPack(String paramNamePattern, String packNamePattern, int pageNo) {
    return audMstParameterDAO.getByRangeParamPack(paramNamePattern, packNamePattern, (pageNo - 1) * resultPerPage, resultPerPage);
  }

  @Override
  public List<AudMstParameter> getByPageParamPackDept(String paramNamePattern, String packNamePattern, String deptId, int pageNo) {
    return audMstParameterDAO.getByRangeParamPackDept(paramNamePattern, packNamePattern, deptId, (pageNo - 1) * resultPerPage, resultPerPage);
  }

  @Override
  public int count() {
    return audMstParameterDAO.count();
  }

  @Override
  public int count(String paramNamePattern, String packNamePattern) {
    return audMstParameterDAO.count(paramNamePattern, packNamePattern);
  }

  @Override
  public int count(String paramNamePattern, String packNamePattern, String deptId) {
    return audMstParameterDAO.count(paramNamePattern, packNamePattern, deptId);
  }

  @Override
  public AudMstParameter getById(String id) {
    return audMstParameterDAO.getById(id);
  }

  @Override
  public List<String> getPackageNameList() {
    return audMstParameterDAO.getPackageNameList();
  }
}
