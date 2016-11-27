/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.service;

import com.safasoft.kci.bean.support.ColumnBean;
import com.safasoft.kci.bean.support.DiagFlowBean;
import com.safasoft.kci.bean.support.GaugeBean;
import com.safasoft.kci.bean.support.KciPeriodeBean;
import com.safasoft.kci.bean.support.ListBean;
import com.safasoft.kci.bean.support.ListDoubleValueBean;
import com.safasoft.kci.bean.support.ListIntValueBean;
import com.safasoft.kci.bean.support.MapBean;
import com.safasoft.kci.bean.support.ParamValueBean;
import com.safasoft.kci.bean.support.ParameterBean;
import com.safasoft.kci.bean.support.StringBean;
import java.util.List;

/**
 * @created Aug 17, 2016
 * @author awal
 */
public interface GenericService {

  List<ListBean> getBranchesWithAll(String areaCode);
  List<ListBean> getBranches(String areaCode);
  String getBranchName(String officeId);
  List<ListBean> getAllCompaniesWithAll();
  String getCompanyName(String coyId);
  List<ListBean> getAllPeriodes();
  List<ListBean> getAllDepartmentsWithAll();
  List<ListBean> getAllDepartments();
  String getDepartement(String deptId);
  List<ListBean> getAllAreasWithAll();
  List<ListBean> getAllAreas();
  String getAreaDesc(String areaId);
  List<ListBean> getAllBussUnitsWithAll();
  String getBussUnitDesc(String bussUnit);
  List<ListBean> getParamByDept(String deptId);
  String getParamName(String paramId);
  List<MapBean> getMapBranch(int gradeNum, String periode, String coyId, String bussUnit, String areaId, String officeId, String deptId);
  List<MapBean> getMapPos(String officeId);
  List<ParameterBean> getParameters(String periode, String coyId, String bussUnit, String areaId, String officeId, String deptId);
  GaugeBean getListKciGauge(String periode, String coyId, String bussUnit, String areaId, String officeId, String deptId);
  List<ListDoubleValueBean> getKciAreas(String periode, String coyId, String bussUnit, String deptId);
  List<ListDoubleValueBean> getKciBranches(String periode, String coyId, String bussUnit, String areaId, String deptId);
  List<ListDoubleValueBean> getKciDepts(String periode, String coyId, String bussUnit, String areaId, String officeId, String deptId);
  List<ColumnBean> getTableColumns(String tableName);
  List<StringBean> getTableData(
          String columnQuery, String tableName, String periode, String coyId, String bussUnit,
          String areaId, String officeId, String paramId, String measure1, String measure2);
  List<StringBean> getTableData(
          String columnQuery, String tableName, int pageNo, String periode, String coyId, String bussUnit,
          String areaId, String officeId, String paramId, String measure1, String measure2);
  int getTableDataCount(String tableName, String periode, String coyId, String bussUnit,
          String areaId, String officeId, String paramId, String measure1, String measure2);
  List<KciPeriodeBean> getListKciPeriode(String periode, String coyId, String bussUnit, String areaId, String officeId, String deptId);
  double getNilaiKciByPeriodeCoyDept(String periode, String coyId, String deptId);
  List<ParamValueBean> getParamValueByPeriodeCoyDept(String periode, String coyId, String deptId);
  List<String> getKciFifByDept(String periode, String coyId, String deptId);
  List<String> getKciAreaParam(String periode, String coyId, String areaId, String deptId);
  List<ListDoubleValueBean> getWorstParamByScore(String periode, String coyId);
  List<ListIntValueBean> getWorstParamByP1(String periode, String coyId);
  List<ListIntValueBean> getWorstParamByP2(String periode, String coyId);
  List<ListDoubleValueBean> getWorstParamByProcessScore(String periode, String coyId);
  List<ListDoubleValueBean> getWorstParamByAreaScore(String periode, String coyId);
  List<ListDoubleValueBean> getWorstParamByBranchScore(String periode, String coyId);
  String getBranchInfo(String periode, String officeId);
  List<String> getRunningText();
  List<DiagFlowBean> getDiagFlow(String deptId);
}
