/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.controller.support;

import com.safasoft.kci.util.bean.CellContentBean;
import com.safasoft.kci.util.bean.CellPropBean;
import com.safasoft.kci.bean.AudMstGrade;
import com.safasoft.kci.bean.support.ListBean;
import com.safasoft.kci.bean.support.ListDoubleValueBean;
import com.safasoft.kci.bean.support.ListIntValueBean;
import com.safasoft.kci.bean.support.ParamValueBean;
import com.safasoft.kci.service.AudMstGradeService;
import com.safasoft.kci.service.GenericService;
import com.safasoft.kci.util.GlobalIntVariable;
import com.safasoft.kci.util.GlobalStringVariable;
import com.safasoft.kci.util.SessionUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;

/**
 * @created Oct 17, 2016
 * @author awal
 */
public class ReportPeriodik {
  
  private final Logger logger = Logger.getLogger("controller");
  //
  private final String allDataCode = GlobalStringVariable.ALL_DATA_CODE.getStr();
  private final AudMstGrade audMstGrade = new SessionUtil<AudMstGradeService>().getAppContext("audMstGradeService").getById("GR001");
  private final int minGradeValue = GlobalIntVariable.MIN_GRADE_VALUE.getVar();
  private final int maxGradeValue = GlobalIntVariable.MAX_GRADE_VALUE.getVar();
  //
  private final String repPeriode;
  private final String scopeOfReport;
  private final String repCoyId;
  private final String repAreaId;
  private final String repDeptId;
  //
  private final List<List<CellContentBean>> titles;
  private final List<List<CellContentBean>> contents = new ArrayList<List<CellContentBean>>();
  private final List<List<Integer>> merges = new ArrayList<List<Integer>>();
  private final Map<String,CellPropBean> templates;
  private int currRow = 0;
  private int maxColIdx = 0;
  private final List<ListBean> deptList;
  private String fileName = "";
  //
  private final GenericService genServ = new SessionUtil<GenericService>().getAppContext("genericService");
  
  public ReportPeriodik(Map<String,String> params) {
    this.repPeriode = params.get("repPeriode"); 
    this.scopeOfReport = params.get("scopeOfReport");
    this.repCoyId = params.get("repCoyId");
    this.repAreaId = params.get("repAreaId");
    this.repDeptId = params.get("repDeptId");
    //
    deptList = getDeptList();
    titles = getTitle();
    templates = getTemplates();
  }
  
  public ReportParamBean getReportParam() {
    //
    if(scopeOfReport.equals("fp"))
      createReportFuncParam();
    else if(scopeOfReport.equals("ap"))
      createReportAreaParam();
    else if(scopeOfReport.equals("mp"))
      createReportMonthPrior();
    //
    ReportParamBean rpb = new ReportParamBean();
    rpb.setTitles(titles);
    rpb.setTemplates(templates);
    rpb.setContents(contents);
    rpb.setMerges(merges);
    rpb.setMaxColIdx(maxColIdx);
    rpb.setFileName(fileName);
    return rpb;
  }
  
  private void createReportFuncParam() {
    //
    logger.info("start to create report function parameter");
    //read data and rewrite it into downloadable file 
    for(ListBean lbDept : deptList) {
      //
      List<CellContentBean> colContents = new ArrayList<CellContentBean>();      
      List<Integer> mergeCells = new ArrayList<Integer>();
      //
      mergeCells.add(currRow);
      mergeCells.add(currRow);
      mergeCells.add(0);
      mergeCells.add(1);
      merges.add(mergeCells);
      //
      colContents.add(new CellContentBean("FIF " + lbDept.getName() + " Control Adequacy","boldMediumLightBlue"));
      colContents.add(new CellContentBean("","boldMediumAuto"));
      //
      Double d = genServ.getNilaiKciByPeriodeCoyDept(repPeriode, repCoyId, lbDept.getCode());
      colContents.add(new CellContentBean(d,"boldMedium"+getBackgroundColor(d)));
      for(int idx = 0; idx < 3; idx++)
        colContents.add(new CellContentBean("","boldMediumAuto"));
      //
      mergeCells = new ArrayList<Integer>();
      mergeCells.add(currRow);
      mergeCells.add(currRow);
      mergeCells.add(6);
      mergeCells.add(7);
      merges.add(mergeCells);
      //
      colContents.add(new CellContentBean("PROBLEM","boldMediumPlum"));
      colContents.add(new CellContentBean("","boldMediumAuto"));
      //
      mergeCells = new ArrayList<Integer>();
      mergeCells.add(currRow);
      mergeCells.add(currRow);
      mergeCells.add(8);
      mergeCells.add(11);
      merges.add(mergeCells);
      //
      colContents.add(new CellContentBean("WORST SCORE","boldMediumOrange"));
      for(int idx = 0; idx < 3; idx++)
        colContents.add(new CellContentBean("","boldMediumAuto"));
      //
      mergeCells = new ArrayList<Integer>();
      mergeCells.add(currRow);
      mergeCells.add(currRow);
      mergeCells.add(12);
      mergeCells.add(23);
      merges.add(mergeCells);
      //
      colContents.add(new CellContentBean("MOST TRANSACTION","boldMediumMaroon"));
      colContents.add(new CellContentBean("","boldMediumAuto"));
      //
      contents.add(colContents);
      currRow++;
      //
      colContents = new ArrayList<CellContentBean>();
      //
      colContents.add(new CellContentBean("PARAMETER","boldMediumGrey"));
      colContents.add(new CellContentBean("DESCRIPTION","boldMediumGrey"));
      colContents.add(new CellContentBean("ADEQUACY","boldMediumGrey"));
      colContents.add(new CellContentBean("P1","boldMediumRed"));
      colContents.add(new CellContentBean("P2","boldMediumYellow"));
      colContents.add(new CellContentBean("P3","boldMediumGreen"));
      colContents.add(new CellContentBean("AREA","boldMediumPlum"));
      colContents.add(new CellContentBean("BRANCH","boldMediumPlum"));
      colContents.add(new CellContentBean("AREA","boldMediumOrange"));
      colContents.add(new CellContentBean("SCORE","boldMediumOrange"));
      colContents.add(new CellContentBean("BRANCH","boldMediumOrange"));
      colContents.add(new CellContentBean("SCORE","boldMediumOrange"));
      colContents.add(new CellContentBean("AREA","boldMediumMaroon"));
      colContents.add(new CellContentBean("P1","boldMediumMaroon"));
      colContents.add(new CellContentBean("AREA","boldMediumMaroon"));
      colContents.add(new CellContentBean("P2","boldMediumMaroon"));
      colContents.add(new CellContentBean("AREA","boldMediumMaroon"));
      colContents.add(new CellContentBean("P1 + P2","boldMediumMaroon"));
      colContents.add(new CellContentBean("BRANCH","boldMediumMaroon"));
      colContents.add(new CellContentBean("P1","boldMediumMaroon"));
      colContents.add(new CellContentBean("BRANCH","boldMediumMaroon"));
      colContents.add(new CellContentBean("P2","boldMediumMaroon"));
      colContents.add(new CellContentBean("BRANCH","boldMediumMaroon"));
      colContents.add(new CellContentBean("P1 + P2","boldMediumMaroon"));
      //
      contents.add(colContents);
      maxColIdx = colContents.size() - 1 > maxColIdx ? colContents.size() - 1 : maxColIdx;
      currRow++;
      for(ParamValueBean pvb : genServ.getParamValueByPeriodeCoyDept(repPeriode,repCoyId,lbDept.getCode())) {
        colContents = new ArrayList<CellContentBean>();
        colContents.add(new CellContentBean(pvb.getParameterId(),"normalMediumLightGrey"));
        colContents.add(new CellContentBean(pvb.getParameterDesc(),"normalMediumLightGrey"));
        colContents.add(new CellContentBean(pvb.getnKci(),"normalMediumLight"+getBackgroundColor(pvb.getnKci())));
        colContents.add(new CellContentBean(pvb.getMeasurementP1(),"normalMediumLightRed"));
        colContents.add(new CellContentBean(pvb.getMeasurementP2(),"normalMediumLightYellow"));
        colContents.add(new CellContentBean(pvb.getMeasurementP3(),"normalMediumLightGreen"));
        colContents.add(new CellContentBean(pvb.getAreaProblemCount(),"normalMediumLightPlum"));
        colContents.add(new CellContentBean(pvb.getBranchProblemCount(),"normalMediumLightPlum"));
        colContents.add(new CellContentBean(pvb.getAreaWorst(),"normalMediumLightOrange"));
        colContents.add(new CellContentBean(pvb.getAreaWorstScore(),"normalMediumLightOrange"));
        colContents.add(new CellContentBean(pvb.getBranchWorst(),"normalMediumLightOrange"));
        colContents.add(new CellContentBean(pvb.getBranchWorstScore(),"normalMediumLightOrange"));
        colContents.add(new CellContentBean(pvb.getAreaMostTransP1(),"normalMediumLightMaroon"));
        colContents.add(new CellContentBean(pvb.getAreaMostTransValueP1(),"normalMediumLightMaroon"));
        colContents.add(new CellContentBean(pvb.getAreaMostTransP2(),"normalMediumLightMaroon"));
        colContents.add(new CellContentBean(pvb.getAreaMostTransValueP2(),"normalMediumLightMaroon"));
        colContents.add(new CellContentBean(pvb.getAreaMostTransP1P2(),"normalMediumLightMaroon"));
        colContents.add(new CellContentBean(pvb.getAreaMostTransValueP1P2(),"normalMediumLightMaroon"));
        colContents.add(new CellContentBean(pvb.getBranchMostTransP1(),"normalMediumLightMaroon"));
        colContents.add(new CellContentBean(pvb.getBranchMostTransValueP1(),"normalMediumLightMaroon"));
        colContents.add(new CellContentBean(pvb.getBranchMostTransP2(),"normalMediumLightMaroon"));
        colContents.add(new CellContentBean(pvb.getBranchMostTransValueP2(),"normalMediumLightMaroon"));
        colContents.add(new CellContentBean(pvb.getBranchMostTransP1P2(),"normalMediumLightMaroon"));
        colContents.add(new CellContentBean(pvb.getBranchMostTransValueP1P2(),"normalMediumLightMaroon"));
        contents.add(colContents);
	currRow++;
      }
      //
      contents.add(new ArrayList<CellContentBean>());//department separation
      currRow++;     
    }   
    //
    logger.info("end of creating report function parameter");
  }
  
  private void createReportAreaParam() {
    //
    logger.info("start to create report area parameter");
    //read data and rewrite it into downloadable file 
    List<ListBean> areaList = new ArrayList<ListBean>();
    if(repAreaId.equals(allDataCode)) {
      areaList = genServ.getAllAreas();
    } else {
      ListBean lb = new ListBean();
      lb.setCode(repAreaId);
      lb.setName(genServ.getAreaDesc(repAreaId));
      areaList.add(lb);
    }
    //    
    List<String> kciFifByDepts = genServ.getKciFifByDept(repPeriode, repCoyId, repDeptId);
    double fifAvg = 0;
    for(String fifVal : kciFifByDepts)
      fifAvg += Double.parseDouble(fifVal);
    fifAvg = Math.round(fifAvg/kciFifByDepts.size()*100.0)/100.0;
    //
    int currNumOfOff; 
    for(ListBean lbArea : areaList) {
      //
      List<CellContentBean> colContents = new ArrayList<CellContentBean>(); 
      List<List<String>> areaKciValAvgs = new ArrayList<List<String>>();
      //
      for(int idx = 0; idx < 3; idx++)
        colContents.add(new CellContentBean("","boldMediumNoFill"));
      for(ListBean lbOff : genServ.getBranches(lbArea.getCode()))
        colContents.add(new CellContentBean(lbOff.getCode(),"boldMediumGrey"));
      currNumOfOff = colContents.size() - 3;
      contents.add(colContents);
      currRow++;
      //
      colContents = new ArrayList<CellContentBean>(); 
      colContents.add(new CellContentBean("PROCESS","boldMediumGrey"));
      colContents.add(new CellContentBean("FIF","boldMediumGrey"));
      colContents.add(new CellContentBean("AREA","boldMediumGrey"));
      for(int idx = 0; idx < currNumOfOff; idx++)
        colContents.add(new CellContentBean("BRANCH","boldMediumGrey"));
      //
      maxColIdx = colContents.size() - 1 > maxColIdx ? colContents.size() - 1 : maxColIdx;
      contents.add(colContents);
      currRow++;
      //
      int deptIdx = 0;
      for(ListBean lbDept : deptList) {
        //
        colContents = new ArrayList<CellContentBean>(); 
        //
        colContents.add(new CellContentBean(lbDept.getName(),"boldMediumGrey"));
        //
        double currKciFifVal = Double.parseDouble(kciFifByDepts.get(deptIdx++));
        colContents.add(new CellContentBean(currKciFifVal,"boldMedium"+getBackgroundColor(currKciFifVal)));
        //
        List<String> kciAreaParams = genServ.getKciAreaParam(repPeriode, repCoyId, lbArea.getCode(), lbDept.getCode());
        for(String str : kciAreaParams) {
          double d = Double.parseDouble(str);
          colContents.add(new CellContentBean(d,"boldMedium"+getBackgroundColor(d)));
        }
        //
        areaKciValAvgs.add(kciAreaParams);
        contents.add(colContents);
        currRow++; 
      } 
      //
      colContents = new ArrayList<CellContentBean>();
      colContents.add(new CellContentBean(lbArea.getName(),"boldMediumGrey"));
      colContents.add(new CellContentBean(fifAvg,"boldMedium"+getBackgroundColor(fifAvg)));
      for(int idxOff = 0; idxOff <= currNumOfOff; idxOff++) {
        double kciOffVal = 0;
        for(List<String> kciOff : areaKciValAvgs)
          kciOffVal += Double.parseDouble(kciOff.get(idxOff));
        kciOffVal = Math.round(kciOffVal/areaKciValAvgs.size()*100.0)/100.0;
        colContents.add(new CellContentBean(kciOffVal,"boldMedium"+getBackgroundColor(kciOffVal)));
      }
      contents.add(colContents);
      currRow++; 
      //    
      contents.add(new ArrayList<CellContentBean>());//department separation
      currRow++;     
    }
    //
    logger.info("end of creating report area parameter");
  }
  
  private void createReportMonthPrior() {
    //
    logger.info("start to create report monthly priority");
    //read data and rewrite it into downloadable file 
    //
    List<CellContentBean> colContents = new ArrayList<CellContentBean>();      
    List<Integer> mergeCells = new ArrayList<Integer>();
    int numOfBest = 5;
    //
    colContents.add(new CellContentBean("PARAMETER PRIORITY","boldMediumBlack"));
    colContents.add(new CellContentBean("RANK","boldMediumBlack"));
    colContents.add(new CellContentBean("CODE","boldMediumBlack"));
    colContents.add(new CellContentBean("DESCRIPTION","boldMediumBlack"));
    colContents.add(new CellContentBean("SCORE / TRANSACTION","boldMediumBlack"));
    colContents.add(new CellContentBean("RISK","boldMediumBlack"));
    //
    maxColIdx = colContents.size() - 1 > maxColIdx ? colContents.size() - 1 : maxColIdx;
    contents.add(colContents);
    currRow++;
    //
    mergeCells.add(currRow);
    mergeCells.add(currRow + 4);
    mergeCells.add(0);
    mergeCells.add(0);
    merges.add(mergeCells);
    //
    List<ListDoubleValueBean> worstParamByScores = genServ.getWorstParamByScore(repPeriode, repCoyId);
    int worstParamByScoreSize = worstParamByScores.size();
    for(int idxScore = 0; idxScore < numOfBest; idxScore++) {
      colContents = new ArrayList<CellContentBean>();
      if(idxScore == 0)
        colContents.add(new CellContentBean("By Score of Control Adequacy","normalMediumDarkGrey"));
      else
        colContents.add(new CellContentBean("","normalMediumDarkGrey"));
      colContents.add(new CellContentBean((idxScore + 1),"normalMediumDarkGrey"));
      if(idxScore < worstParamByScoreSize) {        
        ListDoubleValueBean ldvb = worstParamByScores.get(idxScore);
        colContents.add(new CellContentBean(ldvb.getCode(),"normalMediumDarkGrey"));
        colContents.add(new CellContentBean(ldvb.getName(),"normalMediumDarkGrey"));
        colContents.add(new CellContentBean(ldvb.getAssignValue(),"boldMedium"+getBackgroundColor(ldvb.getAssignValue())));
        colContents.add(new CellContentBean(ldvb.getDescr(),"normalMediumDarkGrey"));
      } else {
        for(int idxCol = 0; idxCol < 4; idxCol++)
          colContents.add(new CellContentBean("","normalMediumDarkGrey"));
      }
      contents.add(colContents);
      currRow++;
    } 
    //
    mergeCells = new ArrayList<Integer>();
    mergeCells.add(currRow);
    mergeCells.add(currRow + 4);
    mergeCells.add(0);
    mergeCells.add(0);
    merges.add(mergeCells);
    //
    List<ListIntValueBean> worstParamByP1 = genServ.getWorstParamByP1(repPeriode, repCoyId);
    int worstParamByP1Size = worstParamByP1.size();
    for(int idxScore = 0; idxScore < numOfBest; idxScore++) {
      colContents = new ArrayList<CellContentBean>();  
      if(idxScore == 0)
        colContents.add(new CellContentBean("By Total P1 Transactions","normalMediumGrey"));
      else
        colContents.add(new CellContentBean("","normalMediumGrey"));
      colContents.add(new CellContentBean((idxScore + 1),"normalMediumGrey"));
      if(idxScore < worstParamByP1Size) {        
        ListIntValueBean livb = worstParamByP1.get(idxScore);
        colContents.add(new CellContentBean(livb.getCode(),"normalMediumGrey"));
        colContents.add(new CellContentBean(livb.getName(),"normalMediumGrey"));
        colContents.add(new CellContentBean(livb.getAssignValue(),"boldMediumRed"));
        colContents.add(new CellContentBean(livb.getDescr(),"normalMediumGrey"));
      } else {
        for(int idxCol = 0; idxCol < 4; idxCol++)
          colContents.add(new CellContentBean("","normalMediumGrey"));
      }
      contents.add(colContents);
      currRow++;
    } 
    //
    mergeCells = new ArrayList<Integer>();
    mergeCells.add(currRow);
    mergeCells.add(currRow + 4);
    mergeCells.add(0);
    mergeCells.add(0);
    merges.add(mergeCells);
    //
    List<ListIntValueBean> worstParamByP2 = genServ.getWorstParamByP2(repPeriode, repCoyId);
    int worstParamByP2Size = worstParamByP2.size();
    for(int idxScore = 0; idxScore < numOfBest; idxScore++) {
      colContents = new ArrayList<CellContentBean>();  
      if(idxScore == 0)
        colContents.add(new CellContentBean("By Total P2 Transactions","normalMediumLightGrey"));
      else
        colContents.add(new CellContentBean("","normalMediumLightGrey"));
      colContents.add(new CellContentBean((idxScore + 1),"normalMediumLightGrey"));
      if(idxScore < worstParamByP2Size) {        
        ListIntValueBean livb = worstParamByP2.get(idxScore);
        colContents.add(new CellContentBean(livb.getCode(),"normalMediumLightGrey"));
        colContents.add(new CellContentBean(livb.getName(),"normalMediumLightGrey"));
        colContents.add(new CellContentBean(livb.getAssignValue(),"boldMediumYellow"));
        colContents.add(new CellContentBean(livb.getDescr(),"normalMediumLightGrey"));
      } else {
        for(int idxCol = 0; idxCol < 4; idxCol++)
          colContents.add(new CellContentBean("","normalMediumLightGrey"));
      }
      contents.add(colContents);
      currRow++;
    } 
    //
    contents.add(new ArrayList<CellContentBean>());//perspective separation
    currRow++; 
    //
    colContents.add(new CellContentBean("PARAMETER PRIORITY","boldMediumBlack"));
    colContents.add(new CellContentBean("RANK","boldMediumBlack"));
    colContents.add(new CellContentBean("CODE","boldMediumBlack"));
    colContents.add(new CellContentBean("DESCRIPTION","boldMediumBlack"));
    colContents.add(new CellContentBean("SCORE / TRANSACTION","boldMediumBlack"));
    colContents.add(new CellContentBean("RISK","boldMediumBlack"));
    contents.add(colContents);
    currRow++;
    //
    mergeCells = new ArrayList<Integer>();
    mergeCells.add(currRow);
    mergeCells.add(currRow + 4);
    mergeCells.add(0);
    mergeCells.add(0);
    merges.add(mergeCells);
    //    
    List<ListDoubleValueBean> worstParamByProcess = genServ.getWorstParamByProcessScore(repPeriode, repCoyId);
    int worstParamByProcessSize = worstParamByProcess.size();
    for(int idxScore = 0; idxScore < numOfBest; idxScore++) {
      colContents = new ArrayList<CellContentBean>();  
      if(idxScore == 0)
        colContents.add(new CellContentBean("By Process Score","normalMediumDarkGrey"));
      else
        colContents.add(new CellContentBean("","normalMediumDarkGrey"));
      colContents.add(new CellContentBean((idxScore + 1),"normalMediumDarkGrey"));
      if(idxScore < worstParamByProcessSize) {        
        ListDoubleValueBean ldvb = worstParamByProcess.get(idxScore);
        colContents.add(new CellContentBean(ldvb.getCode(),"normalMediumDarkGrey"));
        colContents.add(new CellContentBean(ldvb.getName(),"normalMediumDarkGrey"));
        colContents.add(new CellContentBean(ldvb.getAssignValue(),"boldMedium"+getBackgroundColor(ldvb.getAssignValue())));
        colContents.add(new CellContentBean(ldvb.getDescr(),"normalMediumDarkGrey"));
      } else {
        for(int idxCol = 0; idxCol < 4; idxCol++)
          colContents.add(new CellContentBean("","normalMediumDarkGrey"));
      }
      contents.add(colContents);
      currRow++;
    } 
    //
    mergeCells = new ArrayList<Integer>();
    mergeCells.add(currRow);
    mergeCells.add(currRow + 4);
    mergeCells.add(0);
    mergeCells.add(0);
    merges.add(mergeCells);
    //    
    List<ListDoubleValueBean> worstParamByArea = genServ.getWorstParamByAreaScore(repPeriode, repCoyId);
    int worstParamByAreaSize = worstParamByArea.size();
    for(int idxScore = 0; idxScore < numOfBest; idxScore++) {
      colContents = new ArrayList<CellContentBean>();  
      if(idxScore == 0)
        colContents.add(new CellContentBean("By Area Score","normalMediumGrey"));
      else
        colContents.add(new CellContentBean("","normalMediumGrey"));
      colContents.add(new CellContentBean((idxScore + 1),"normalMediumGrey"));
      if(idxScore < worstParamByAreaSize) {        
        ListDoubleValueBean ldvb = worstParamByArea.get(idxScore);
        colContents.add(new CellContentBean(ldvb.getCode(),"normalMediumGrey"));
        colContents.add(new CellContentBean(ldvb.getName(),"normalMediumGrey"));
        colContents.add(new CellContentBean(ldvb.getAssignValue(),"boldMedium"+getBackgroundColor(ldvb.getAssignValue())));
        colContents.add(new CellContentBean(ldvb.getDescr(),"normalMediumGrey"));
      } else {
        for(int idxCol = 0; idxCol < 4; idxCol++)
          colContents.add(new CellContentBean("","normalMediumGrey"));
      }
      contents.add(colContents);
      currRow++;
    } 
    //
    mergeCells = new ArrayList<Integer>();
    mergeCells.add(currRow);
    mergeCells.add(currRow + 4);
    mergeCells.add(0);
    mergeCells.add(0);
    merges.add(mergeCells);
    //    
    List<ListDoubleValueBean> worstParamByBranch = genServ.getWorstParamByBranchScore(repPeriode, repCoyId);
    int worstParamByBranchSize = worstParamByBranch.size();
    for(int idxScore = 0; idxScore < numOfBest; idxScore++) {
      colContents = new ArrayList<CellContentBean>();  
      if(idxScore == 0)
        colContents.add(new CellContentBean("By Branch Score","normalMediumLightGrey"));
      else
        colContents.add(new CellContentBean("","normalMediumLightGrey"));
      colContents.add(new CellContentBean((idxScore + 1),"normalMediumLightGrey"));
      if(idxScore < worstParamByBranchSize) {        
        ListDoubleValueBean ldvb = worstParamByBranch.get(idxScore);
        colContents.add(new CellContentBean(ldvb.getCode(),"normalMediumLightGrey"));
        colContents.add(new CellContentBean(ldvb.getName(),"normalMediumLightGrey"));
        colContents.add(new CellContentBean(ldvb.getAssignValue(),"boldMedium"+getBackgroundColor(ldvb.getAssignValue())));
        colContents.add(new CellContentBean(ldvb.getDescr(),"normalMediumLightGrey"));
      } else {
        for(int idxCol = 0; idxCol < 4; idxCol++)
          colContents.add(new CellContentBean("","normalMediumLightGrey"));
      }
      contents.add(colContents);
      currRow++;
    } 
    //
    logger.info("end of creating report monthly priority");
  }
  
  private String getBackgroundColor(double value) {
    if(value >= minGradeValue && value < audMstGrade.getBatasBawah())
      return "Green";
    else if (value >= audMstGrade.getBatasBawah() && value <= audMstGrade.getBatasAtas())
      return "Yellow";
    else if (value > audMstGrade.getBatasAtas() && value <= maxGradeValue)
      return "Red";
    else
      return "Grey";
  }
  
  private List<List<CellContentBean>> getTitle() {
    //
    Map<String,String> scopeOfReportDescMap = new HashMap<String,String>();
    scopeOfReportDescMap.put("fp", "Function Parameter");
    scopeOfReportDescMap.put("ap", "Area Parameter");
    scopeOfReportDescMap.put("mp", "Monthly Priority");
    //
    List<List<CellContentBean>> titlesTmp = new ArrayList<List<CellContentBean>>();
    List<CellContentBean> cellTitle = new ArrayList<CellContentBean>();
    //
    cellTitle.add(new CellContentBean("KCI - Monthly Report","boldLargeNoFill"));
    titlesTmp.add(cellTitle); currRow++;
    cellTitle = new ArrayList<CellContentBean>();
    cellTitle.add(new CellContentBean("Reporting Month","boldLargeNoFill"));
    cellTitle.add(new CellContentBean(": " + repPeriode,"boldLargeNoFill"));
    titlesTmp.add(cellTitle); currRow++;
    cellTitle = new ArrayList<CellContentBean>();
    cellTitle.add(new CellContentBean("Scope of Report","boldLargeNoFill"));
    cellTitle.add(new CellContentBean(": " + scopeOfReportDescMap.get(scopeOfReport),"boldLargeNoFill"));
    titlesTmp.add(cellTitle); currRow++;
    cellTitle = new ArrayList<CellContentBean>();
    cellTitle.add(new CellContentBean("Company","boldLargeNoFill"));
    cellTitle.add(new CellContentBean(": " + (repCoyId.equals(allDataCode) ? allDataCode : genServ.getCompanyName(repCoyId)),
            "boldLargeNoFill"));
    titlesTmp.add(cellTitle); currRow++;
    if(scopeOfReport.equals("ap")) {
      cellTitle = new ArrayList<CellContentBean>();
      cellTitle.add(new CellContentBean("Area","boldLargeNoFill"));
      cellTitle.add(new CellContentBean(": " + (repAreaId.equals(allDataCode) ? allDataCode : genServ.getAreaDesc(repAreaId)),
              "boldLargeNoFill"));
      titlesTmp.add(cellTitle); currRow++;      
    }
    titlesTmp.add(new ArrayList<CellContentBean>()); currRow++; //title separator
    //
    fileName = 
            "Report_Periodik_" + scopeOfReportDescMap.get(scopeOfReport).replace(" ", "")
            + "_" + repPeriode + "_" + repCoyId + ".xls";
    //
    return titlesTmp;
  }
  
  private Map<String,CellPropBean> getTemplates() {
    //
    short colorNoFill = 0;
    short colorAuto = IndexedColors.AUTOMATIC.getIndex();
    short colorBlack = IndexedColors.BLACK.getIndex();
    short colorDarkGrey = IndexedColors.GREY_50_PERCENT.getIndex();
    short colorGreen = IndexedColors.GREEN.getIndex();
    short colorGrey = IndexedColors.GREY_40_PERCENT.getIndex();
    short colorLightBlue = IndexedColors.LIGHT_BLUE.getIndex();
    short colorLightGreen = IndexedColors.LIGHT_GREEN.getIndex();
    short colorLightGrey = IndexedColors.GREY_25_PERCENT.getIndex();
    short colorLightMaroon = IndexedColors.ROSE.getIndex();
    short colorLightOrange = IndexedColors.LIGHT_ORANGE.getIndex();
    short colorLightPlum = IndexedColors.LAVENDER.getIndex();
    short colorLightRed = IndexedColors.CORAL.getIndex();
    short colorLightYellow = IndexedColors.LIGHT_YELLOW.getIndex();
    short colorMaroon = IndexedColors.MAROON.getIndex();
    short colorOrange = IndexedColors.ORANGE.getIndex();
    short colorPlum = IndexedColors.VIOLET.getIndex();
    short colorRed = IndexedColors.RED.getIndex();
    short colorYellow = IndexedColors.YELLOW.getIndex();
    //
    short fontNormal = Font.BOLDWEIGHT_NORMAL;
    short fontBold = Font.BOLDWEIGHT_BOLD;
    short fontHeightMedium = 10;
    short fontHeightLarge = 11;
    //
    Map<String,CellPropBean> templatesTmp = new HashMap<String,CellPropBean>();
    templatesTmp.put("boldLargeNoFill", new CellPropBean(fontBold,fontHeightLarge,colorNoFill));
    templatesTmp.put("boldMediumNoFill", new CellPropBean(fontBold,fontHeightMedium,colorNoFill));
    templatesTmp.put("boldMediumAuto", new CellPropBean(fontBold,fontHeightMedium,colorAuto));
    templatesTmp.put("boldMediumBlack", new CellPropBean(fontBold,fontHeightMedium,colorBlack));
    templatesTmp.put("boldMediumDarkGrey", new CellPropBean(fontBold,fontHeightMedium,colorDarkGrey));
    templatesTmp.put("boldMediumGreen", new CellPropBean(fontBold,fontHeightMedium,colorGreen));
    templatesTmp.put("boldMediumGrey", new CellPropBean(fontBold,fontHeightMedium,colorGrey));
    templatesTmp.put("boldMediumLightBlue", new CellPropBean(fontBold,fontHeightMedium,colorLightBlue));
    templatesTmp.put("boldMediumLightGrey", new CellPropBean(fontBold,fontHeightMedium,colorLightGrey));
    templatesTmp.put("boldMediumMaroon", new CellPropBean(fontBold,fontHeightMedium,colorMaroon));
    templatesTmp.put("boldMediumOrange", new CellPropBean(fontBold,fontHeightMedium,colorOrange));
    templatesTmp.put("boldMediumPlum", new CellPropBean(fontBold,fontHeightMedium,colorPlum));
    templatesTmp.put("boldMediumRed", new CellPropBean(fontBold,fontHeightMedium,colorRed));
    templatesTmp.put("boldMediumYellow", new CellPropBean(fontBold,fontHeightMedium,colorYellow));
    templatesTmp.put("normalMediumDarkGrey", new CellPropBean(fontNormal,fontHeightMedium,colorDarkGrey));
    templatesTmp.put("normalMediumGrey", new CellPropBean(fontNormal,fontHeightMedium,colorGrey));
    templatesTmp.put("normalMediumLightGrey", new CellPropBean(fontNormal,fontHeightMedium,colorLightGrey));
    templatesTmp.put("normalMediumLightGreen", new CellPropBean(fontNormal,fontHeightMedium,colorLightGreen));
    templatesTmp.put("normalMediumLightMaroon", new CellPropBean(fontNormal,fontHeightMedium,colorLightMaroon));
    templatesTmp.put("normalMediumLightOrange", new CellPropBean(fontNormal,fontHeightMedium,colorLightOrange));
    templatesTmp.put("normalMediumLightPlum", new CellPropBean(fontNormal,fontHeightMedium,colorLightPlum));
    templatesTmp.put("normalMediumLightRed", new CellPropBean(fontNormal,fontHeightMedium,colorLightRed));
    templatesTmp.put("normalMediumLightYellow", new CellPropBean(fontNormal,fontHeightMedium,colorLightYellow));
    return templatesTmp;
  }
  
  private List<ListBean> getDeptList() { 
    List<ListBean> deptListTmp = new ArrayList<ListBean>();
    //
    if(repDeptId.equals(allDataCode)) {
      deptListTmp = genServ.getAllDepartments();
    } else {
      ListBean lbDept = new ListBean();
      lbDept.setCode(repDeptId);
      lbDept.setName(genServ.getDepartement(repDeptId));
      deptListTmp.add(lbDept);
    }      
    return deptListTmp;
  }
}
