/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.service.impl;

import com.safasoft.kci.bean.AudLogMonitoring;
import com.safasoft.kci.dao.AudLogMonitoringDAO;
import com.safasoft.kci.service.AudLogMonitoringService;
import com.safasoft.kci.util.GlobalIntVariable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @created Nov 22, 2016
 * @author awal
 */
@Service("audLogMonitoringService")
@Transactional(readOnly=true)
public class AudLogMonitoringServiceImpl implements AudLogMonitoringService {

  @Autowired
  private AudLogMonitoringDAO audLogMonitoringDAO;
  private final int resultPerPage = GlobalIntVariable.RESULT_PER_PAGE.getVar();

  @Override
  public List<AudLogMonitoring> getByPageJobPackStatus(String jobNamePattern, String packNamePattern, String status, int pageNo) {
    return audLogMonitoringDAO.getByRangeJobPackStatus(jobNamePattern, packNamePattern, status, (pageNo - 1) * resultPerPage, resultPerPage);
  }

  @Override
  public List<AudLogMonitoring> getByPageJobPackProcStatus(String jobNamePattern, String packNamePattern, String dateProcess, String status, int pageNo) {
    return audLogMonitoringDAO.getByRangeJobPackProcStatus(jobNamePattern, packNamePattern, dateProcess, status, (pageNo - 1) * resultPerPage, resultPerPage);
  }

  @Override
  public int count(String jobNamePattern, String packNamePattern, String status) {
    return audLogMonitoringDAO.count(jobNamePattern, packNamePattern, status);
  }

  @Override
  public int count(String jobNamePattern, String packNamePattern, String dateProcess, String status) {
    return audLogMonitoringDAO.count(jobNamePattern, packNamePattern, dateProcess, status);
  }

  @Override
  public List<AudLogMonitoring> getByPage(int pageNo) {
    return audLogMonitoringDAO.getByRange((pageNo - 1) * resultPerPage, resultPerPage);
  }

  @Override
  public int count() {
    return audLogMonitoringDAO.count();
  }

  @Override
  public List<String> getDateProcess() {
    return audLogMonitoringDAO.getDateProcess();
  }

  @Override
  public int runJob(String jobName) {
    return audLogMonitoringDAO.runJob(jobName);
  }
}
