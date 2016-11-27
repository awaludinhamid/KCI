/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.service;

import com.safasoft.kci.bean.AudMstParameter;
import java.util.List;

/**
 * @created Nov 18, 2016
 * @author awal
 */
public interface AudMstParameterService {

  AudMstParameter save(AudMstParameter amp);
  AudMstParameter delete(AudMstParameter amp);
  AudMstParameter getById(String id);
  List<AudMstParameter> getByPage(int pageNo);
  List<AudMstParameter> getByPageParamPack(String paramNamePattern, String packNamePattern, int pageNo);
  List<AudMstParameter> getByPageParamPackDept(String paramNamePattern, String packNamePattern, String deptId, int pageNo);
  int count();
  int count(String paramNamePattern, String packNamePattern);
  int count(String paramNamePattern, String packNamePattern, String deptId);
  List<String> getPackageNameList();
}
