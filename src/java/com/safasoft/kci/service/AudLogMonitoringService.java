/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.service;

import com.safasoft.kci.bean.AudLogMonitoring;
import java.util.List;

/**
 * @created Nov 22, 2016
 * @author awal
 */
public interface AudLogMonitoringService {

  List<AudLogMonitoring> getByPage(int pageNo);
  List<AudLogMonitoring> getByPageJobPackStatus
        (String jobNamePattern, String packNamePattern, String status, int pageNo);
  List<AudLogMonitoring> getByPageJobPackProcStatus
        (String jobNamePattern, String packNamePattern, String dateProcess, String status, int pageNo);
  int count();
  int count (String jobNamePattern, String packNamePattern, String status);
  int count(String jobNamePattern, String packNamePattern, String dateProcess, String status);
  List<String> getDateProcess();
  int runJob(String jobName);
}
