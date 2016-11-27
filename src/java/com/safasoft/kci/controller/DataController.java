/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.controller;

import com.safasoft.kci.bean.AudMstEmployeesHcms;
import com.safasoft.kci.bean.AudMstGrade;
import com.safasoft.kci.bean.AudMstRoles;
import com.safasoft.kci.bean.support.ColumnBean;
import com.safasoft.kci.bean.support.DiagFlowHieBean;
import com.safasoft.kci.bean.support.KciGaugeBean;
import com.safasoft.kci.bean.support.KciGraphicBean;
import com.safasoft.kci.bean.support.ListBean;
import com.safasoft.kci.bean.support.ListDoubleValueBean;
import com.safasoft.kci.bean.support.MapBean;
import com.safasoft.kci.bean.support.OrgChartBean;
import com.safasoft.kci.bean.support.ParameterBean;
import com.safasoft.kci.bean.support.StringBean;
import com.safasoft.kci.bean.support.TableContents;
import com.safasoft.kci.controller.support.DiagFlowProc;
import com.safasoft.kci.controller.support.ReportPeriodik;
import com.safasoft.kci.service.AudMstGradeService;
import com.safasoft.kci.service.GenericService;
import com.safasoft.kci.util.ExcelPrinted;
import com.safasoft.kci.util.GlobalStringVariable;
import com.safasoft.kci.util.OutputFileCreator;
import com.safasoft.kci.controller.support.ReportParamBean;
import com.safasoft.kci.service.AudLogMonitoringService;
import com.safasoft.kci.service.AudMstEmployeesHcmsService;
import com.safasoft.kci.service.AudMstParameterService;
import com.safasoft.kci.service.AudMstRolesService;
import com.safasoft.kci.service.AudMstUsersService;
import com.safasoft.kci.service.AudTrnTrackingService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @created Aug 17, 2016
 * @author awal
 */
@Controller
@RequestMapping("/data")
public class DataController {
  
  @Autowired
  private GenericService genServ;
  @Autowired
  private AudMstGradeService amgServ;
  @Autowired
  private AudMstUsersService amuServ;
  @Autowired
  private AudMstParameterService ampServ;
  @Autowired
  private AudMstEmployeesHcmsService amehServ;
  @Autowired
  private AudMstRolesService amrServ;
  @Autowired
  private AudTrnTrackingService attServ;
  @Autowired
  private AudLogMonitoringService almServ;

  private final Logger logger = Logger.getLogger("controller");
  private final String allDataCode = GlobalStringVariable.ALL_DATA_CODE.getStr();
  private String tableNameTemp = "";
  private String columnQuery = "";
  private final String colDelimit = "||'|'||";
  private List<ParameterBean> paramList = new ArrayList<ParameterBean>();
  private String periode = "";
  private String coyId = "";
  private String bussUnit = "";
  private String areaId = "";
  private String officeId = "";
  private String deptId = "";
  private String paramId = "";
  private String measure1 = "";
  private String measure2 = "";
  private String downloadStatus = "RUN";
  
  @RequestMapping(value = "/branches/{areaCode}", method = RequestMethod.GET)
  public @ResponseBody List<ListBean> getAllBranches(@PathVariable("areaCode") String areaCode) {
    logger.debug("Received request to get branches list");
    return genServ.getBranchesWithAll(areaCode);
  }
  
  @RequestMapping(value = "/allcompanies", method = RequestMethod.GET)
  public @ResponseBody List<ListBean> getAllCompanies() {
    logger.debug("Received request to get companies list");
    return genServ.getAllCompaniesWithAll();
  }
  
  @RequestMapping(value = "/allperiodes", method = RequestMethod.GET)
  public @ResponseBody List<ListBean> getAllPeriodes() {
    logger.debug("Received request to get periode list");
    return genServ.getAllPeriodes();
  }
  
  @RequestMapping(value = "/alldepartments", method = RequestMethod.GET)
  public @ResponseBody List<ListBean> getAllDepartments() {
    logger.debug("Received request to get department list");
    return genServ.getAllDepartmentsWithAll();
  }
  
  @RequestMapping(value = "/allregions", method = RequestMethod.GET)
  public @ResponseBody List<ListBean> getAllAreas() {
    logger.debug("Received request to get region list");
    return genServ.getAllAreasWithAll();
  }
  
  @RequestMapping(value = "/allbussunits", method = RequestMethod.GET)
  public @ResponseBody List<ListBean> getAllBussUnits() {
    logger.debug("Received request to get buss unit list");
    return genServ.getAllBussUnitsWithAll();
  }
  
  @RequestMapping(value = "/mapbranch/{gradeNum}", method = RequestMethod.GET)
  public @ResponseBody List<MapBean> getMapBranch(
          HttpServletRequest httpRequest,
          HttpServletResponse httpResponse,
          @PathVariable("gradeNum") int gradeNum) {
    //
    logger.debug("Received request to get map info list");    
    //no cache applicable
    setNoCache(httpResponse);
    //
    return genServ.getMapBranch(
            gradeNum,
            httpRequest.getParameter("periode"),
            httpRequest.getParameter("coyId"),
            httpRequest.getParameter("bussUnit"),
            httpRequest.getParameter("areaId"),
            httpRequest.getParameter("officeId"),
            httpRequest.getParameter("deptId")
    );
  }
  
  @RequestMapping(value = "/mappos/{branchCode}", method = RequestMethod.GET)
  public @ResponseBody List<MapBean> getMapPos(
          HttpServletResponse httpResponse,
          @PathVariable("branchCode") String branchCode) {
    //
    logger.debug("Received request to get map pos list");
    //no cache applicable
    setNoCache(httpResponse);
    //
    return genServ.getMapPos(branchCode);
  }
  
  @RequestMapping(value = "/parameters", method = RequestMethod.GET)
  public @ResponseBody List<ParameterBean> getParameters(
          HttpServletRequest httpRequest,
          HttpServletResponse httpResponse) {
    logger.debug("Received request to get parameter list");   
    //no cache applicable
    setNoCache(httpResponse);
    //        
    periode = httpRequest.getParameter("periode");
    coyId = httpRequest.getParameter("coyId");
    bussUnit = httpRequest.getParameter("bussUnit");
    areaId = httpRequest.getParameter("areaId");
    officeId = httpRequest.getParameter("officeId");
    deptId = httpRequest.getParameter("deptId");
    //
    paramList = genServ.getParameters(
            periode,
            coyId,
            bussUnit,
            areaId,
            officeId,
            deptId
    );
    return paramList;
  }
  
  @RequestMapping(value = "/kcigauge", method = RequestMethod.GET)
  public @ResponseBody KciGaugeBean getKciGauge(
          HttpServletRequest httpRequest,
          HttpServletResponse httpResponse) {
    //
    logger.debug("Received request to get kci gauge value");    
    //no cache applicable
    setNoCache(httpResponse);
    //
    KciGaugeBean kgb = new KciGaugeBean();
    kgb.setKciFifgroup(genServ.getListKciGauge(
                        httpRequest.getParameter("periode"),
                        httpRequest.getParameter("coyId"),
                        httpRequest.getParameter("bussUnit"),
                        allDataCode,
                        allDataCode,
                        httpRequest.getParameter("deptId")));
    kgb.setKciArea(genServ.getListKciGauge(
                        httpRequest.getParameter("periode"),
                        httpRequest.getParameter("coyId"),
                        httpRequest.getParameter("bussUnit"),
                        httpRequest.getParameter("areaId"),
                        allDataCode,
                        httpRequest.getParameter("deptId")));
    kgb.setKciBranch(genServ.getListKciGauge(
                        httpRequest.getParameter("periode"),
                        httpRequest.getParameter("coyId"),
                        httpRequest.getParameter("bussUnit"),
                        httpRequest.getParameter("areaId"),
                        httpRequest.getParameter("officeId"),
                        httpRequest.getParameter("deptId")));
    //
    return kgb;
  }
  
  @RequestMapping(value = "/kcigraphic", method = RequestMethod.GET)
  public @ResponseBody KciGraphicBean getKciGraphic(
          HttpServletRequest httpRequest,
          HttpServletResponse httpResponse) {
    //
    logger.debug("Received request to get kci graphic value");    
    //no cache applicable
    setNoCache(httpResponse);
    //
    KciGraphicBean kgb = new KciGraphicBean();
    kgb.setKciFifgroup(genServ.getListKciPeriode(
                        httpRequest.getParameter("periode"),
                        httpRequest.getParameter("coyId"),
                        httpRequest.getParameter("bussUnit"),
                        allDataCode,
                        allDataCode,
                        httpRequest.getParameter("deptId")));
    kgb.setKciArea(genServ.getListKciPeriode(
                        httpRequest.getParameter("periode"),
                        httpRequest.getParameter("coyId"),
                        httpRequest.getParameter("bussUnit"),
                        httpRequest.getParameter("areaId"),
                        allDataCode,
                        httpRequest.getParameter("deptId")));
    kgb.setKciBranch(genServ.getListKciPeriode(
                        httpRequest.getParameter("periode"),
                        httpRequest.getParameter("coyId"),
                        httpRequest.getParameter("bussUnit"),
                        httpRequest.getParameter("areaId"),
                        httpRequest.getParameter("officeId"),
                        httpRequest.getParameter("deptId")));
    //
    return kgb;
  }
  
  @RequestMapping(value = "/kciareas", method = RequestMethod.GET)
  public @ResponseBody List<ListDoubleValueBean> getKciAreas(
          HttpServletRequest httpRequest,
          HttpServletResponse httpResponse) {
    //
    logger.debug("Received request to get list kci area value");    
    //no cache applicable
    setNoCache(httpResponse);
    //
    return genServ.getKciAreas(
            httpRequest.getParameter("periode"),
            httpRequest.getParameter("coyId"),
            httpRequest.getParameter("bussUnit"),
            httpRequest.getParameter("deptId")
    );
  }
  
  @RequestMapping(value = "/kcibranches", method = RequestMethod.GET)
  public @ResponseBody List<ListDoubleValueBean> getKciBranches(
          HttpServletRequest httpRequest,
          HttpServletResponse httpResponse) {
    //
    logger.debug("Received request to get list kci branch value");    
    //no cache applicable
    setNoCache(httpResponse);
    //
    return genServ.getKciBranches(
            httpRequest.getParameter("periode"),
            httpRequest.getParameter("coyId"),
            httpRequest.getParameter("bussUnit"),
            httpRequest.getParameter("areaId"),
            httpRequest.getParameter("deptId")
    );
  }
  
  @RequestMapping(value = "/kcidepts", method = RequestMethod.GET)
  public @ResponseBody List<ListDoubleValueBean> getKciDepts(
          HttpServletRequest httpRequest,
          HttpServletResponse httpResponse) {
    //
    logger.debug("Received request to get list kci department value");    
    //no cache applicable
    setNoCache(httpResponse);
    //
    return genServ.getKciDepts(
            httpRequest.getParameter("periode"),
            httpRequest.getParameter("coyId"),
            httpRequest.getParameter("bussUnit"),
            httpRequest.getParameter("areaId"),
            httpRequest.getParameter("officeId"),
            httpRequest.getParameter("deptId")
    );
  }
  
  @RequestMapping(value = "/tablecontents/{tableName}/{pageNo}", method = RequestMethod.GET)
  public @ResponseBody TableContents getTableContents(
          @PathVariable("tableName") String tableName,
          @PathVariable("pageNo") int pageNo,
          HttpServletRequest httpRequest,
          HttpServletResponse httpResponse) {
    //
    logger.debug("Received request to get table contents");    
    //no cache applicable
    setNoCache(httpResponse);
    //  
    paramId = httpRequest.getParameter("paramId");
    measure1 = httpRequest.getParameter("measure1");
    measure2 = httpRequest.getParameter("measure2");
    // default page number
    if(pageNo == 0)
      pageNo = 1;    
    //
    TableContents tc = new TableContents();
    List<ColumnBean> columns = genServ.getTableColumns(tableName);
    // generate column header and column query
    List<String> headers = new ArrayList<String>();
    StringBuilder sb = new StringBuilder("");
    for(ColumnBean column : columns) {
      headers.add(WordUtils.capitalizeFully(column.getColumnName().replace("_", " ")));
      sb.append(column.getDataType().equals("DATE") ? "TO_CHAR("+column.getColumnName()+",'dd Mon yyyy')" : column.getColumnName())
        .append(colDelimit);
    }
    columnQuery = sb.substring(0,sb.lastIndexOf(colDelimit));
    // escape undefine character to avoid SQL injection or phising
    String pattern = "\\w+";
    Pattern p = Pattern.compile(pattern);
    Matcher m = p.matcher(tableName);
    tableNameTemp = "";
    if(m.find())
      tableNameTemp = m.group(0);
    // set content
    tc.setHeaders(headers);
    tc.setContents(genServ.getTableData(
            columnQuery,
            tableNameTemp,
            pageNo,            
            periode,
            coyId,
            bussUnit,
            areaId,
            officeId,
            paramId,
            measure1,
            measure2));
    tc.setCount(genServ.getTableDataCount(
            tableNameTemp,            
            periode,
            coyId,
            bussUnit,
            areaId,
            officeId,
            paramId,
            measure1,
            measure2));
    return tc;
  }

  /**
   * Handles download page
   * @param fileExt
   * @param httpResponse 
   */
  @RequestMapping(value = "/download/{fileExt}", method = RequestMethod.GET)
  public void download(HttpServletResponse httpResponse, @PathVariable("fileExt") String fileExt) {
    logger.debug("Received request to download file");   
    //no cache applicable
    setNoCache(httpResponse);
    //read data and rewrite it into downloadable file  
    downloadStatus = "RUN";
    List<String> titles = new ArrayList<String>();
    List<String> headers = new ArrayList<String>();
    List<List<String>> contents = new ArrayList<List<String>>();
    titles.add("Periode: " + periode);
    titles.add("Company: " + (coyId.equals(allDataCode) ? allDataCode : genServ.getCompanyName(coyId)) + " (" + coyId + ")");
    titles.add("LOB: " + (bussUnit.equals(allDataCode) ? allDataCode : genServ.getBussUnitDesc(bussUnit)) + " (" + bussUnit + ")");
    titles.add("Area: " + (areaId.equals(allDataCode) ? allDataCode : genServ.getAreaDesc(areaId)) + " (" + areaId + ")");
    titles.add("Branch: " + (officeId.equals(allDataCode) ? allDataCode : genServ.getBranchName(officeId)) + " (" + officeId + ")");
    titles.add("Department: " + (deptId.equals(allDataCode) ? allDataCode : genServ.getDepartement(deptId)) + " (" + deptId + ")");
    String joinMeasure = (measure1.equals(measure2) ? measure1 : (measure1 + "," + measure2));
    if(fileExt.equals("param")) {
      paramId = fileExt;
      fileExt = "xls";
      String[] hdrArr = {"Parameter Id","Parameter Desc","Measurement P1","Measurement P2","Measurement P3"};
      headers = Arrays.asList(hdrArr);
      for(ParameterBean pb : paramList) {
        String[] contArr = {pb.getParameterId(),pb.getParameterDesc(),pb.getMeasurementP1()+"",pb.getMeasurementP2()+"",pb.getMeasurementP3()+""};
        contents.add(Arrays.asList(contArr));
      }
    } else {
      titles.add("Parameter: " + genServ.getParamName(paramId) + " (" + paramId + ")");
      titles.add("Measurement: " + joinMeasure);
      titles.add("Table: " + tableNameTemp);
      List<ColumnBean> columns = genServ.getTableColumns(tableNameTemp);
      for(ColumnBean column : columns)
        headers.add(column.getColumnName());
      List<StringBean> sbList = genServ.getTableData(
              columnQuery,
              tableNameTemp,     
              periode,
              coyId,
              bussUnit,
              areaId,
              officeId,
              paramId,
              measure1,
              measure2);
      for(StringBean sb : sbList) {
        String[] strArr = sb.getStringValue().split("\\|");
        contents.add(Arrays.asList(strArr));
      }
    }
    String fileName = 
            paramId + "_" + periode + "_" + coyId + "_" + bussUnit + "_" + areaId + "_" + officeId + "_" + joinMeasure.replace(",","")+ "." + fileExt;  
    try {
      File downloadFile = new File(fileName);
      new OutputFileCreator(downloadFile, titles, headers, contents, fileExt).createFile();
      httpResponse.setHeader("Content-disposition","attachment; filename="+fileName);
      setNoCache(httpResponse);
      InputStream in = new FileInputStream(downloadFile);
      FileCopyUtils.copy(in, httpResponse.getOutputStream());
      httpResponse.flushBuffer();
      downloadStatus = "DONE";
    } catch (IOException ex) {
     logger.error(ex);
    }
  }
  
  @RequestMapping(value = "/downloadstatus", method = RequestMethod.GET)
  public @ResponseBody String getDownloadStatus() {
    logger.debug("Received request to get grades list");
    return downloadStatus;
  }

  /**
   * Handles download report periodic page
   * @param repPeriode
   * @param scopeOfReport
   * @param repCoyId
   * @param repAreaId
   * @param repDeptId
   * @param httpResponse 
   */
  @RequestMapping(value = "/downloadreport", method = RequestMethod.GET)
  public void downloadReport(
          @RequestParam("repPeriode") String repPeriode,
          @RequestParam("scopeOfReport") String scopeOfReport,
          @RequestParam("repCoyId") String repCoyId,
          @RequestParam("repAreaId") String repAreaId,
          @RequestParam("repDeptId") String repDeptId,
          HttpServletResponse httpResponse) {
    logger.debug("Received request to download file");   
    //no cache applicable
    setNoCache(httpResponse);
    //
    downloadStatus = "RUN";
    Map<String,String> reportParams = new HashMap<String,String>();
    reportParams.put("repPeriode", repPeriode);
    reportParams.put("scopeOfReport", scopeOfReport);
    reportParams.put("repCoyId", repCoyId);
    reportParams.put("repAreaId", repAreaId);
    reportParams.put("repDeptId", repDeptId);
    //read data and rewrite it into downloadable file 
    ReportPeriodik repPeriodik = new ReportPeriodik(reportParams);
    ReportParamBean rpb = repPeriodik.getReportParam();
    //
    try { 
      File downloadFile = new File(rpb.getFileName());
      new ExcelPrinted(downloadFile, rpb.getTitles(), rpb.getContents(), rpb.getMerges(), rpb.getTemplates(), rpb.getMaxColIdx()).createFile();
      httpResponse.setHeader("Content-disposition","attachment; filename="+rpb.getFileName());
      setNoCache(httpResponse);
      InputStream in = new FileInputStream(downloadFile);
      FileCopyUtils.copy(in, httpResponse.getOutputStream());
      httpResponse.flushBuffer();
      downloadStatus = "DONE";
    } catch (IOException ex) {
     logger.error(ex);
    }
  }
  
  @RequestMapping(value = "/allgrades", method = RequestMethod.GET)
  public @ResponseBody List<AudMstGrade> getAllGrades() {
    logger.debug("Received request to get grades list");
    return amgServ.getAll();
  }
  
  @RequestMapping(value = "/grade/{id}", method = RequestMethod.GET)
  public @ResponseBody AudMstGrade getGrade(@PathVariable String id) {
    logger.debug("Received request to get grade by id");
    return amgServ.getById(id);
  }
  
  @RequestMapping(value = "/orgchart", method = RequestMethod.GET)
  public @ResponseBody OrgChartBean getOrgChart(HttpServletRequest httpRequest) {
    logger.debug("Received request to get organization chart data");
    //
    OrgChartBean ocb = new OrgChartBean();
    String orgChartPeriode = httpRequest.getParameter("periode");
    String orgChartOffice = httpRequest.getParameter("officeId");
    ocb.setBranchInfo(genServ.getBranchInfo(orgChartPeriode, orgChartOffice));
    ocb.setBranchHead(attServ.getBranchHead(orgChartPeriode, orgChartOffice));
    ocb.setDeptHead(attServ.getDeptHead(orgChartPeriode, orgChartOffice));
    ocb.setPosHead(attServ.getPosHead(orgChartPeriode, orgChartOffice));
    return ocb;
  }
  
  @RequestMapping(value = "/runningtext", method = RequestMethod.GET)
  public @ResponseBody List<String> getRunningText() {
    logger.debug("Received request to get running text data");
    //
    return genServ.getRunningText();
  }
  
  @RequestMapping(value = "/cpts/{pageNo}", method = RequestMethod.GET)
  public @ResponseBody Map<String,Object> getCpts(@PathVariable("pageNo") int pageNo, HttpServletRequest httpRequest) {
    logger.debug("Received request to get cpts data");
    //
    String param1 = httpRequest.getParameter("param1") == null ? "" : httpRequest.getParameter("param1");
    String param2 = httpRequest.getParameter("param2") == null ? "" : httpRequest.getParameter("param2");
    String npk = httpRequest.getParameter("npk") == null ? "" : httpRequest.getParameter("npk");
    Map<String,Object> cptsMap = new HashMap<String,Object>();
    if(!npk.equals("")) {      
      cptsMap.put("content", attServ.getByPageNpk(npk,pageNo));
      cptsMap.put("count", attServ.count(npk));
    } else if(param1.equals("") && param2.equals("")) {
      cptsMap.put("content", attServ.getByPageNo(pageNo));
      cptsMap.put("count", attServ.count());
    } else {
      cptsMap.put("content", attServ.getByPatternAndPageNo(param1, param2, pageNo));
      cptsMap.put("count", attServ.count(param1, param2));      
    }
    return cptsMap;
  }
  
  @RequestMapping(value = "/grades/{pageNo}", method = RequestMethod.GET)
  public @ResponseBody Map<String,Object> getGrades(@PathVariable("pageNo") int pageNo, HttpServletRequest httpRequest) {
    logger.debug("Received request to get grade data");
    //
    String gradeIdPattern = httpRequest.getParameter("gradeIdPattern") == null ? "" : httpRequest.getParameter("gradeIdPattern");
    String gradeNamePattern = httpRequest.getParameter("gradeNamePattern") == null ? "" : httpRequest.getParameter("gradeNamePattern");
    Map<String,Object> gradesMap = new HashMap<String,Object>();
    if(gradeIdPattern.equals("") && gradeNamePattern.equals("")) {
      gradesMap.put("content", amgServ.getByPage(pageNo));
      gradesMap.put("count", amgServ.count());
    } else {
      gradesMap.put("content", amgServ.getByPageIdAndName(gradeIdPattern, gradeNamePattern, pageNo));
      gradesMap.put("count", amgServ.count(gradeIdPattern, gradeNamePattern));      
    }
    return gradesMap;
  }
  
  @RequestMapping(value = "/roleacc/{pageNo}", method = RequestMethod.GET)
  public @ResponseBody Map<String,Object> getRoleAccess(@PathVariable("pageNo") int pageNo, HttpServletRequest httpRequest) {
    logger.debug("Received request to get role access data");
    //
    String emplNamePattern = httpRequest.getParameter("emplNamePattern") == null ? "" : httpRequest.getParameter("emplNamePattern");
    Integer roleId = Integer.parseInt(httpRequest.getParameter("roleId"));
    Map<String,Object> gradesMap = new HashMap<String,Object>();
    if(emplNamePattern.equals("") && roleId == 0) {
      gradesMap.put("content", amuServ.getByPage(pageNo));
      gradesMap.put("count", amuServ.count());
    } else if(roleId == 0) {
      gradesMap.put("content", amuServ.getByPageName(emplNamePattern, pageNo));
      gradesMap.put("count", amuServ.count(emplNamePattern));
    } else {
      gradesMap.put("content", amuServ.getByPageNameAndRole(emplNamePattern, roleId, pageNo));
      gradesMap.put("count", amuServ.count(emplNamePattern, roleId));      
    }
    return gradesMap;
  }
  
  @RequestMapping(value = "/parameter/{pageNo}", method = RequestMethod.GET)
  public @ResponseBody Map<String,Object> getParams(@PathVariable("pageNo") int pageNo, HttpServletRequest httpRequest) {
    logger.debug("Received request to get parameter data");
    //
    String paramNamePattern = httpRequest.getParameter("paramNamePattern") == null ? "" : httpRequest.getParameter("paramNamePattern");
    String packNamePattern = httpRequest.getParameter("packNamePattern") == null ? "" : httpRequest.getParameter("packNamePattern");
    String deptIdParam = httpRequest.getParameter("deptId") == null ? "" : httpRequest.getParameter("deptId");
    Map<String,Object> paramMap = new HashMap<String,Object>();
    if(paramNamePattern.equals("") && packNamePattern.equals("") && deptIdParam.equals(allDataCode)) {
      paramMap.put("content", ampServ.getByPage(pageNo));
      paramMap.put("count", ampServ.count());
    } else if(deptIdParam.equals(allDataCode)) {
      paramMap.put("content", ampServ.getByPageParamPack(paramNamePattern, packNamePattern, pageNo));
      paramMap.put("count", ampServ.count(paramNamePattern, packNamePattern));
    } else {
      paramMap.put("content", ampServ.getByPageParamPackDept(paramNamePattern, packNamePattern, deptIdParam, pageNo));
      paramMap.put("count", ampServ.count(paramNamePattern, packNamePattern, deptIdParam));      
    }
    return paramMap;
  }
  
  @RequestMapping(value = "/logmonitor/{pageNo}", method = RequestMethod.GET)
  public @ResponseBody Map<String,Object> getLogMonitors(@PathVariable("pageNo") int pageNo, HttpServletRequest httpRequest) {
    logger.debug("Received request to get log monitoring");
    //
    String jobNamePattern = httpRequest.getParameter("jobNamePattern") == null ? "" : httpRequest.getParameter("jobNamePattern");
    String packNamePattern = httpRequest.getParameter("packNamePattern") == null ? "" : httpRequest.getParameter("packNamePattern");
    String dateProcess = httpRequest.getParameter("dateProcess") == null ? "" : httpRequest.getParameter("dateProcess");
    String status = httpRequest.getParameter("status") == null ? "" : httpRequest.getParameter("status");
    Map<String,Object> logMap = new HashMap<String,Object>();
    if(jobNamePattern.equals("") && packNamePattern.equals("") && dateProcess.equals("") && status.equals(allDataCode)) {
      logMap.put("content", almServ.getByPage(pageNo));
      logMap.put("count", almServ.count());
    } else if(dateProcess.equals("")) {
      logMap.put("content", almServ.getByPageJobPackStatus(jobNamePattern, packNamePattern, status, pageNo));
      logMap.put("count", almServ.count(jobNamePattern, packNamePattern, status));
    } else {
      logMap.put("content", almServ.getByPageJobPackProcStatus(jobNamePattern, packNamePattern, dateProcess, status, pageNo));
      logMap.put("count", almServ.count(jobNamePattern, packNamePattern, dateProcess, status));   
    }
    return logMap;
  }
  
  @RequestMapping(value = "/employee/{npk}", method = RequestMethod.GET)
  public @ResponseBody AudMstEmployeesHcms getEmployeeByNpk(@PathVariable("npk") String npk, HttpServletRequest httpRequest) {
    logger.debug("Received request to get employee");
    //
    return amehServ.getById(npk);
  }
  
  @RequestMapping(value = "/departments", method = RequestMethod.GET)
  public @ResponseBody List<ListBean> getDepartments() {
    logger.debug("Received request to get departments");
    //
    return genServ.getAllDepartments();
  }
  
  @RequestMapping(value = "/roles", method = RequestMethod.GET)
  public @ResponseBody List<AudMstRoles> getRoles() {
    logger.debug("Received request to get roles");
    //
    return amrServ.getAll();
  }
  
  @RequestMapping(value = "/packagenames", method = RequestMethod.GET)
  public @ResponseBody List<String> getPackageNames() {
    logger.debug("Received request to get package names");
    //
    return ampServ.getPackageNameList();
  }
  
  @RequestMapping(value = "/diagramflow/{deptId}", method = RequestMethod.GET)
  public @ResponseBody DiagFlowHieBean getDiagramFlow(@PathVariable("deptId") String deptId) {
    logger.debug("Received request to get diagram flow");
    //
    logger.info("parameterId: " + deptId);
    return new DiagFlowProc().getDiagFlowHie(deptId, genServ);
  }
  
  @RequestMapping(value = "/dateprocess", method = RequestMethod.GET)
  public @ResponseBody List<String> getDateProcess() {
    logger.debug("Received request to get date process list");
    //
    return almServ.getDateProcess();
  }
  
  private void setNoCache(HttpServletResponse httpResponse) {
    httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
    httpResponse.setHeader("Pragma", "no-cache"); // HTTP 1.0.
    httpResponse.setDateHeader("Expires", 0); // Proxies    
  }
}
