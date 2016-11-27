/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.service.impl;

import com.safasoft.kci.bean.AudTrnTracking;
import com.safasoft.kci.dao.AudTrnTrackingDAO;
import com.safasoft.kci.service.AudTrnTrackingService;
import com.safasoft.kci.util.GlobalIntVariable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @created Nov 1, 2016
 * @author awal
 */
@Service("audTrnTrackingService")
@Transactional(readOnly=true)
public class AudTrnTrackingServiceImpl implements AudTrnTrackingService {
  
  @Autowired
  private AudTrnTrackingDAO audTrnTrackingDAO;
  private final int resultPerPage = GlobalIntVariable.RESULT_PER_PAGE.getVar();

  @Override
  public List<AudTrnTracking> getByPageNo(int pageNo) {
    return audTrnTrackingDAO.getByRange((pageNo - 1) * resultPerPage, resultPerPage);
  }

  @Override
  public List<AudTrnTracking> getByPatternAndPageNo(String param1, String param2, int pageNo) {
    return audTrnTrackingDAO.getByPatternAndRange(param1, param2, (pageNo - 1) * resultPerPage, resultPerPage);
  }

  @Override
  public AudTrnTracking getBranchHead(String periode, String officeId) {
    return audTrnTrackingDAO.getBranchHead(periode, officeId);
  }

  @Override
  public List<AudTrnTracking> getDeptHead(String periode, String officeId) {
    return audTrnTrackingDAO.getDeptHead(periode, officeId);
  }

  @Override
  public List<AudTrnTracking> getPosHead(String periode, String officeId) {
    return audTrnTrackingDAO.getPosHead(periode, officeId);
  }

  @Override
  public int count() {
    return audTrnTrackingDAO.count();
  }

  @Override
  public int count(String param1, String param2) {
    return audTrnTrackingDAO.count(param1, param2);
  }

  @Override
  public List<AudTrnTracking> getByPageNpk(String npk, int pageNo) {
    return audTrnTrackingDAO.getByRangeNpk(npk, (pageNo - 1) * resultPerPage, resultPerPage);
  }

  @Override
  public int count(String npk) {
    return audTrnTrackingDAO.count(npk);
  }

}
