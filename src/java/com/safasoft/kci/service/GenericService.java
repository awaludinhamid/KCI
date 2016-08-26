/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.service;

import com.safasoft.kci.bean.support.ListBean;
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
  List<MapBean> getMapBranch();
  List<MapBean> getMapPos(String officeId);
  List<ParameterBean> getParmeters(String deptId);
  List<ListBean> getAllBussUnits();
}
