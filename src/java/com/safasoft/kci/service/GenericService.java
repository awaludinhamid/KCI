/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.service;

import com.safasoft.kci.bean.support.KciGaugeBean;
import com.safasoft.kci.bean.support.ListBean;
import com.safasoft.kci.bean.support.ListValueBean;
import com.safasoft.kci.bean.support.MapBean;
import com.safasoft.kci.bean.support.ParameterBean;
import java.util.List;

/**
 * @created Aug 17, 2016
 * @author awal
 */
public interface GenericService {

  List<ListBean> getBranches(String areaCode);
  List<ListBean> getAllCompanies();
  List<ListBean> getAllPeriodes();
  List<ListBean> getAllDepartments();
  List<ListBean> getAllRegions();
  List<MapBean> getMapBranch(int gradeNum, String periode, String coyId, String bussUnit, String areaId, String officeId, String deptId);
  List<MapBean> getMapPos(String officeId);
  List<ParameterBean> getParameters(String periode, String coyId, String bussUnit, String areaId, String officeId, String deptId);
  List<ListBean> getAllBussUnits();
  KciGaugeBean getKciGauge(String periode, String coyId, String bussUnit, String areaId, String officeId, String deptId);
  List<ListValueBean> getKciAreas(String periode, String coyId, String bussUnit, String deptId);
  List<ListValueBean> getKciBranches(String periode, String coyId, String bussUnit, String areaId, String deptId);
  List<ListValueBean> getKciDepts(String periode, String coyId, String bussUnit, String areaId, String officeId, String deptId);
}
