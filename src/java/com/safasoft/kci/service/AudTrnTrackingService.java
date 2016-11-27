/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.service;

import com.safasoft.kci.bean.AudTrnTracking;
import java.util.List;

/**
 * @created Nov 1, 2016
 * @author awal
 */
public interface AudTrnTrackingService {

  List<AudTrnTracking> getByPageNo(int pageNo);
  List<AudTrnTracking> getByPatternAndPageNo(String param1, String param2, int pageNo);
  AudTrnTracking getBranchHead(String periode, String officeId);
  List<AudTrnTracking> getDeptHead(String periode, String officeId);
  List<AudTrnTracking> getPosHead(String periode, String officeId);
  List<AudTrnTracking> getByPageNpk(String npk, int pageNo);
  int count();
  int count(String param1, String param2);
  int count(String npk);
}
