/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.service.impl;

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
import com.safasoft.kci.dao.GenericDAO;
import com.safasoft.kci.service.GenericService;
import com.safasoft.kci.util.GlobalStringVariable;
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
  private final String allDataCode = GlobalStringVariable.ALL_DATA_CODE.getStr();

  @Override
  public List<ListBean> getBranchesWithAll(String areaCode) {
    if(areaCode.equals(allDataCode))
      return genericDAO.getAllBranchesWithAll();
    return genericDAO.getBranchesWithAll(areaCode);
  }

  @Override
  public List<ListBean> getBranches(String areaCode) {
      return genericDAO.getBranches(areaCode);
  }

  @Override
  public String getBranchName(String officeId) {
    return genericDAO.getBranchName(officeId);
  }

  @Override
  public List<ListBean> getAllCompaniesWithAll() {
    return genericDAO.getAllCompaniesWithAll();
  }

  @Override
  public String getCompanyName(String coyId) {
    return genericDAO.getCompanyName(coyId);
  }

  @Override
  public List<ListBean> getAllPeriodes() {
    return genericDAO.getAllPeriodes();
  }

  @Override
  public List<ListBean> getAllDepartmentsWithAll() {
    return genericDAO.getAllDepartmentsWithAll();
  }
  
  @Override
  public List<ListBean> getAllDepartments() {
    return genericDAO.getAllDepartments();
  }

  @Override
  public String getDepartement(String deptId) {
    return genericDAO.getDepartement(deptId);
  }

  @Override
  public List<ListBean> getAllAreasWithAll() {
    return genericDAO.getAllAreasWithAll();
  }

  @Override
  public List<ListBean> getAllAreas() {
    return genericDAO.getAllAreas();
  }

  @Override
  public String getAreaDesc(String areaId) {
    return genericDAO.getAreaDesc(areaId);
  }

  @Override
  public List<ListBean> getAllBussUnitsWithAll() {
    return genericDAO.getAllBussUnitsWithAll();
  }

  @Override
  public String getBussUnitDesc(String bussUnit) {
    return genericDAO.getBussUnitDesc(bussUnit);
  }

  @Override
  public List<ListBean> getParamByDept(String deptId) {
    return genericDAO.getParamByDept(deptId);
  }

  @Override
  public String getParamName(String paramId) {
    return genericDAO.getParamName(paramId);
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
  public GaugeBean getListKciGauge(String periode, String coyId, String bussUnit, String areaId, String officeId, String deptId) {
    return genericDAO.getListKciGauge(periode, coyId, bussUnit, areaId, officeId, deptId);
  }

  @Override
  public List<ListDoubleValueBean> getKciAreas(String periode, String coyId, String bussUnit, String deptId) {
    return genericDAO.getKciAreas(periode, coyId, bussUnit, deptId);
  }

  @Override
  public List<ListDoubleValueBean> getKciBranches(String periode, String coyId, String bussUnit, String areaId, String deptId) {
    return genericDAO.getKciBranches(periode, coyId, bussUnit, areaId, deptId);
  }

  @Override
  public List<ListDoubleValueBean> getKciDepts(String periode, String coyId, String bussUnit, String areaId, String officeId, String deptId) {
    return genericDAO.getKciDepts(periode, coyId, bussUnit, areaId, officeId, deptId);
  }

  @Override
  public List<ColumnBean> getTableColumns(String tableName) {
    return genericDAO.getTableColumns(tableName);
  }

  @Override
  public List<StringBean> getTableData(
          String columnQuery, String tableName, String periode, String coyId, String bussUnit,
          String areaId, String officeId, String paramId, String measure1, String measure2) {
    return genericDAO.getTableData(columnQuery, tableName, periode, coyId, bussUnit, areaId, officeId, paramId, measure1, measure2);
  }

  @Override
  public List<StringBean> getTableData(
          String columnQuery, String tableName, int pageNo, String periode, String coyId, String bussUnit,
          String areaId, String officeId, String paramId, String measure1, String measure2) {
    return genericDAO.getTableData(columnQuery, tableName, pageNo, periode, coyId, bussUnit, areaId, officeId, paramId, measure1, measure2);
  }

  @Override
  public int getTableDataCount(String tableName, String periode, String coyId, String bussUnit,
          String areaId, String officeId, String paramId, String measure1, String measure2) {
    return genericDAO.getTableDataCount(tableName, periode, coyId, bussUnit, areaId, officeId, paramId, measure1, measure2);
  }

  @Override
  public List<KciPeriodeBean> getListKciPeriode(String periode, String coyId, String bussUnit, String areaId, String officeId, String deptId) {
    return genericDAO.getListKciPeriode(periode, coyId, bussUnit, areaId, officeId, deptId);
  }

  @Override
  public double getNilaiKciByPeriodeCoyDept(String periode, String coyId, String deptId) {
    return genericDAO.getNilaiKciByPeriodeCoyDept(periode, coyId, deptId);
  }

  @Override
  public List<ParamValueBean> getParamValueByPeriodeCoyDept(String periode, String coyId, String deptId) {
    return genericDAO.getParamValueByPeriodeCoyDept(periode, coyId, deptId);
  }

  @Override
  public List<String> getKciFifByDept(String periode, String coyId, String deptId) {
    return genericDAO.getKciFifByDept(periode, coyId, deptId);
  }

  @Override
  public List<String> getKciAreaParam(String periode, String coyId, String areaId, String deptId) {
    return genericDAO.getKciAreaParam(periode, coyId, areaId, deptId);
  }

  @Override
  public List<ListDoubleValueBean> getWorstParamByScore(String periode, String coyId) {
    return genericDAO.getWorstParamByScore(periode, coyId);
  }

  @Override
  public List<ListIntValueBean> getWorstParamByP1(String periode, String coyId) {
    return genericDAO.getWorstParamByP1(periode, coyId);
  }

  @Override
  public List<ListIntValueBean> getWorstParamByP2(String periode, String coyId) {
    return genericDAO.getWorstParamByP2(periode, coyId);
  }

  @Override
  public List<ListDoubleValueBean> getWorstParamByProcessScore(String periode, String coyId) {
    return genericDAO.getWorstParamByProcessScore(periode, coyId);
  }

  @Override
  public List<ListDoubleValueBean> getWorstParamByAreaScore(String periode, String coyId) {
    return genericDAO.getWorstParamByAreaScore(periode, coyId);
  }

  @Override
  public List<ListDoubleValueBean> getWorstParamByBranchScore(String periode, String coyId) {
    return genericDAO.getWorstParamByBranchScore(periode, coyId);
  }

  @Override
  public String getBranchInfo(String periode, String officeId) {
    return genericDAO.getBranchInfo(periode, officeId);
  }

  @Override
  public List<String> getRunningText() {
    return genericDAO.getRunningText();
  }

  @Override
  public List<DiagFlowBean> getDiagFlow(String deptId) {
    return genericDAO.getDiagFlow(deptId);
  }
  
}
