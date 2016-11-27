/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.dao;

import com.safasoft.kci.bean.support.ColumnBean;
import com.safasoft.kci.bean.support.DiagFlowBean;
import com.safasoft.kci.bean.support.GaugeBean;
import com.safasoft.kci.bean.support.KciPeriodeBean;
import com.safasoft.kci.bean.support.ListBean;
import com.safasoft.kci.bean.support.ListDoubleValueBean;
import com.safasoft.kci.bean.support.ListIntValueBean;
import com.safasoft.kci.bean.support.ParameterBean;
import com.safasoft.kci.bean.support.MapBean;
import com.safasoft.kci.bean.support.ParamValueBean;
import com.safasoft.kci.bean.support.StringBean;
import com.safasoft.kci.util.GlobalIntVariable;
import com.safasoft.kci.util.GlobalStringVariable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @created Aug 17, 2016
 * @author awal
 */
@Repository("genericDAO")
public class GenericDAO {
  
  private final int resultPerPage = GlobalIntVariable.RESULT_PER_PAGE.getVar();
  private final String allDataCode = GlobalStringVariable.ALL_DATA_CODE.getStr();
  
  @Autowired
  protected SessionFactory sessionFactory;
  
  public List<ListBean> getBranchesWithAll(String areaCode) {
    return sessionFactory.getCurrentSession().createSQLQuery(
          "select :allDataCode code, :allDataCode name from dual " +
          "union all " +
          "select office_code code, name_short name " +
            "from fa_m_fifapps.fs_mst_offices " +
            "where office_code in ( " +
              "select office_id " +
                "from fifaud.aud_mst_area_office "+
                "where area_id = :areaCode) " +
            "order by 2")
            .addEntity(ListBean.class)
            .setString("areaCode", areaCode)
            .setString("allDataCode", allDataCode)
            .list();
  }
  
  public List<ListBean> getBranches(String areaCode) {
    return sessionFactory.getCurrentSession().createSQLQuery(
          "select office_code code, name_short name " +
            "from fa_m_fifapps.fs_mst_offices " +
            "where office_code in ( " +
              "select office_id " +
                "from fifaud.aud_mst_area_office "+
                "where area_id = :areaCode) " +
            "order by 1")
            .addEntity(ListBean.class)
            .setString("areaCode", areaCode)
            .list();
  }
  
  public List<ListBean> getAllBranchesWithAll() {
    return sessionFactory.getCurrentSession().createSQLQuery(
            "select :allDataCode code, :allDataCode name from dual " +
            "union all " +
            "select office_code code, name_short name " +
              "from fa_m_fifapps.fs_mst_offices " +
              "order by 2")
              .addEntity(ListBean.class)
              .setString("allDataCode", allDataCode)
              .list();
  }
  
  public String getBranchName(String officeId) {
    return (String) sessionFactory.getCurrentSession().createSQLQuery(
            "select name_short " +
              "from fa_m_fifapps.fs_mst_offices " +
              "where office_code = :officeId")
            .setString("officeId", officeId)
            .uniqueResult();
  }
  
  public List<ListBean> getAllCompaniesWithAll() {
    return sessionFactory.getCurrentSession().createSQLQuery(
            "select :allDataCode code, :allDataCode name from dual " +
            "union all " +
            "select coy_id, name_short " +
              "from fa_m_fifapps.fs_mst_company " +
              "order by 2")
            .addEntity(ListBean.class)
            .setString("allDataCode", allDataCode)
            .list();
  }
  
  public String getCompanyName(String coyId) {
    return (String) sessionFactory.getCurrentSession().createSQLQuery(
            "select name_short " +
              "from fa_m_fifapps.fs_mst_company " +
              "where coy_id = :coyId")
            .setString("coyId", coyId)
            .uniqueResult();
  }
  
  public List<ListBean> getAllPeriodes() {
    return sessionFactory.getCurrentSession().createSQLQuery(
            "select periode code, periode name " +
              "from fifaud.aud_mst_periode " +
              "order by 1 desc")
            .addEntity(ListBean.class)
            .list();
  }
  
  public List<ListBean> getAllDepartmentsWithAll() {
    return sessionFactory.getCurrentSession().createSQLQuery(
            "select :allDataCode code, :allDataCode name from dual " +
            "union all " +
            "select dept_id code, dept_desc name " +
              "from fifaud.aud_mst_department " +
              "order by 1")
            .addEntity(ListBean.class)
            .setString("allDataCode", allDataCode)
            .list();
  }
  
  public List<ListBean> getAllDepartments() {
    return sessionFactory.getCurrentSession().createSQLQuery(
            "select dept_id code, dept_desc name " +
              "from fifaud.aud_mst_department " +
              "order by 1")
            .addEntity(ListBean.class)
            .list();
  }
  
  public String getDepartement(String deptId) {
    return (String) sessionFactory.getCurrentSession().createSQLQuery(
            "select dept_desc " +
              "from fifaud.aud_mst_department " +
              "where dept_id = :deptId ")
              .setString("deptId",deptId)
              .uniqueResult();
  }
  
  public List<ListBean> getAllAreasWithAll() {
    return sessionFactory.getCurrentSession().createSQLQuery(
            "select :allDataCode code, :allDataCode name from dual " +
            "union all " +
            "select area_id code, area_name name " +
              "from fifaud.aud_mst_area " +
              "order by 2")
            .addEntity(ListBean.class)
            .setString("allDataCode", allDataCode)
            .list();
  }
  
  public List<ListBean> getAllAreas() {
    return sessionFactory.getCurrentSession().createSQLQuery(
            "select area_id code, area_name name " +
              "from fifaud.aud_mst_area " +
              "order by 2")
            .addEntity(ListBean.class)
            .list();
  }
  
  public String getAreaDesc(String areaId) {
    return (String) sessionFactory.getCurrentSession().createSQLQuery(
            "select area_name " +
              "from fifaud.aud_mst_area " +
              "where area_id = :areaId")
            .setString("areaId", areaId)
            .uniqueResult();
  }
  
  public List<ListBean> getAllBussUnitsWithAll() {
    return sessionFactory.getCurrentSession().createSQLQuery(
            "select :allDataCode code, :allDataCode name from dual " +
            "union all " +
            "select distinct buss_unit code, buss_unit name " +
              "from fa_m_fifapps.fs_mst_buss_unit " +
              //"where segment_bu2 is not null " +
              "order by 2")
            .addEntity(ListBean.class)
            .setString("allDataCode", allDataCode)
            .list();
  }
  
  public String getBussUnitDesc(String bussUnit) {
    return (String) sessionFactory.getCurrentSession().createSQLQuery(
            "select distinct buss_unit_descr " +
              "from fa_m_fifapps.fs_mst_buss_unit " +
              "where buss_unit = :bussUnit")
            .setString("bussUnit", bussUnit)
            .uniqueResult();
  }
  
  public List<ListBean> getParamByDept(String deptId) {
    return sessionFactory.getCurrentSession().createSQLQuery(
            "select parameter_id code, parameter_desc name " +
              "from fifaud.aud_mst_parameter " +
              "where dept_id = :deptId " +
                "and visible = 'Y'")
            .addEntity(ListBean.class)
            .setString("deptId", deptId)
            .list();
  }
  
  public String getParamName(String paramId) {
    return (String) sessionFactory.getCurrentSession().createSQLQuery(
            "select parameter_desc " +
              "from fifaud.aud_mst_parameter " +
              "where parameter_id = :paramId")
            .setString("paramId", paramId)
            .uniqueResult();
  }
  
  public List<MapBean> getMapBranch(int gradeNum, String periode, String coyId, String bussUnit, String areaId, String officeId, String deptId) {
    return sessionFactory.getCurrentSession().createSQLQuery(
            "select poi_id, poi_type, latitude, longitude, " +
              "measurement_p1, measurement_p2, measurement_p3, nilai_kci, " +
              "decode(grade_num,1,'GreenPin1LargeB.png',2,'YellowPin1LargeB.png',3,'RedPin1LargeB.png','BlackPin1LargeB.png') warna_icon, " +
              "'CABANG '||(select name_short from fa_m_fifapps.fs_mst_offices c where c.office_code = poi_id) poi_name, " +
              "to_char(sysdate,'dd Mon yyyy') last_audit, " +
              "decode(grade_num,1,'SATISFACTORY',2,'IMPROVEMENT NEEDED',3,'UNSATISFACTORY','NOT AVAILABLE') grade " +
              "from ( " +
              "select a.poi_id, a.poi_type, a.latitude, a.longitude, " +
                "nvl(b.measurement_p1,0) measurement_p1, " +
                "nvl(b.measurement_p2,0) measurement_p2, " +
                "nvl(b.measurement_p3,0) measurement_p3, " +
                "nvl(b.n_kci,0) nilai_kci, " +
                "nvl(( " +
                  "select case " +
                    "when (b.n_kci is null or b.n_kci < 1 or b.n_kci > 3) then 4 " +
                    "when b.n_kci < c.batas_bawah then 1 " +
                    "when b.n_kci > c.batas_atas then 3 " +
                    "else 2 end " +
                  "from fifaud.aud_mst_grade c " +
                  "where c.grade_id = b.grade_kci),4) grade_num " +
                "from ( " +
                "select poi_id, poi_type, latitude, longitude " +
                  "from fifaud.aud_mst_poi " +
                  "where poi_type = 'BRANCH' " +
                    "and poi_id = decode(:officeId,:allDataCode,poi_id,:officeId) " +
                    "and poi_id in ( " +
                      "select office_id " +
                        "from fifaud.aud_mst_area_office " +
                        "where area_id = decode(:areaId,:allDataCode,area_id,:areaId)) " +
                ") a, ( " +
                "select office_id, n_kci, measurement_p1, measurement_p2, measurement_p3, grade_kci " +
                  "from fifaud.aud_nilai_kci " +
                  "where periode = :periode " +
                    "and company_id = :coyId " +
                    "and buss_unit = :bussUnit " +
                    "and area_id = :areaId " +
                    "and dept_id = :deptId " +
                    "and office_id != :allDataCode) b " +
                "where a.poi_id = b.office_id (+)) " +
              "where " + (gradeNum == 0 ? "0" : "grade_num") + " = :gradeNum ")
            .addEntity(MapBean.class)
            .setInteger("gradeNum", gradeNum)
            .setString("periode", periode)
            .setString("coyId", coyId)
            .setString("bussUnit", bussUnit)
            .setString("areaId", areaId)
            .setString("officeId", officeId)
            .setString("deptId", deptId)
            .setString("allDataCode", allDataCode)
            .list();
  }
  
  public List<MapBean> getMapPos(String officeId) {
    return sessionFactory.getCurrentSession().createSQLQuery(
            "select a.poi_id, a.poi_type, a.latitude, a.longitude, " +
                    "0 measurement_p1, 0 measurement_p3, 0 measurement_p2, 0 nilai_kci, " +
                    "'BluePin1LargeB.png' warna_icon, " +
                    "(select pos_name from fifaud.aud_mst_pos b where b.pos_id = a.poi_id) poi_name, " +
                    //"a.poi_id poi_name, " +
                    "to_char(sysdate,'dd Mon yyyy') last_audit, " +
                    "'UNRATED' grade " +
                    "from fifaud.aud_mst_poi a " +
                    "where a.poi_type != 'BRANCH' " +
                    "and a.poi_id in ( " +
                      "select pos_id " +
                        "from fifaud.aud_mst_pos_office " +
                        "where office_id = :officeId)")
            .addEntity(MapBean.class)
            .setString("officeId", officeId)
            .list();
  }
  
  public List<ParameterBean> getParameters(String periode, String coyId, String bussUnit, String areaId, String officeId, String deptId) {
    return sessionFactory.getCurrentSession().createSQLQuery(
            "select a.parameter_id, a.parameter_desc, a.table_kci, " +
              "nvl(b.measurement_p1,0) measurement_p1, nvl(b.measurement_p2,0) measurement_p2, " +
              "nvl(b.measurement_p3,0) measurement_p3, nvl(b.count_total,0) count_total, " +
              "nvl(b.persen_measurement_p1,0) persen_measurement_p1, nvl(b.persen_measurement_p2,0) persen_measurement_p2, " +
              "nvl(b.persen_measurement_p3,0) persen_measurement_p3 " +
              "from fifaud.aud_mst_parameter a, ( " +
              "select parameter_id, measurement_p1, measurement_p2, measurement_p3, count_total, " +
                "persen_measurement_p1, persen_measurement_p2, persen_measurement_p3 " +
                "from fifaud.aud_sum_all2 " +
                "where bulan_transaksi = :periode " +
                  "and company_id = :coyId " +
                  "and buss_unit = :bussUnit " +
                  "and area_id = :areaId " +
                  "and office_id = :officeId) b " +
                "where a.dept_id = decode(:deptId,:allDataCode,a.dept_id,:deptId) " +
                  "and a.visible = 'Y'" +
                  "and a.parameter_id = b.parameter_id (+) " +
                "order by 2")
            .addEntity(ParameterBean.class)
            .setString("periode",periode)
            .setString("coyId",coyId)
            .setString("bussUnit",bussUnit)
            .setString("areaId",areaId)
            .setString("officeId",officeId)
            .setString("deptId",deptId)
            .setString("allDataCode", allDataCode)
            .list();
  }
  
  public List<ListDoubleValueBean> getKciAreas(String periode, String coyId, String bussUnit, String deptId) {
    return sessionFactory.getCurrentSession().createSQLQuery(
            "select a.area_id code, a.area_name name, nvl(b.n_kci,0) assign_value, " +
              "nvl(( " +
                "select case " +
                  "when (b.n_kci is null or b.n_kci < 1 or b.n_kci > 3) then 'black' " +
                  "when b.n_kci < c.batas_bawah then 'green' " +
                  "when b.n_kci > c.batas_atas then 'red' " +
                  "else 'yellow' end " +
                  "from fifaud.aud_mst_grade c " +
                  "where c.grade_id = b.grade_kci),'black') descr " +
              "from fifaud.aud_mst_area a, ( " +
              "select area_id, n_kci, grade_kci " +
                "from fifaud.aud_nilai_kci " +
                "where periode = :periode " +
                  "and company_id = :coyId " +
                  "and buss_unit = :bussUnit " +
                  "and office_id = :officeId " +
                  "and dept_id = :deptId) b " +
                "where a.area_id = b.area_id (+) " +
              "order by 2")
            .addEntity(ListDoubleValueBean.class)
            .setString("periode",periode)
            .setString("coyId",coyId)
            .setString("bussUnit",bussUnit)
            .setString("officeId",allDataCode)
            .setString("deptId",deptId)
            .list();
  }
  
  public List<ListDoubleValueBean> getKciBranches(String periode, String coyId, String bussUnit, String areaId, String deptId) {
    return sessionFactory.getCurrentSession().createSQLQuery(
            "select a.office_code code, a.name_short name, nvl(b.n_kci,0) assign_value, " +
              "nvl(( " +
                "select case " +
                  "when (b.n_kci is null or b.n_kci < 1 or b.n_kci > 3) then 'black' " +
                  "when b.n_kci < c.batas_bawah then 'green' " +
                  "when b.n_kci > c.batas_atas then 'red' " +
                  "else 'yellow' end " +
                  "from fifaud.aud_mst_grade c " +
                  "where c.grade_id = b.grade_kci),'black') descr " +
              "from ( " +
                "select c.office_code, c.name_short, d.area_id " +
                  "from fa_m_fifapps.fs_mst_offices c, fifaud.aud_mst_area_office d " +
                  "where c.office_code = d.office_id " +
                    "and d.area_id = decode(:areaId,:allDataCode,d.area_id,:areaId) " +
                    ") a, ( " +
              "select area_id, office_id, n_kci, grade_kci " +
                "from fifaud.aud_nilai_kci " +
                "where periode = :periode " +
                  "and company_id = :coyId " +
                  "and buss_unit = :bussUnit " +
                  "and dept_id = :deptId) b " +
              "where a.area_id = b.area_id (+) " +
                "and a.office_code = b.office_id (+) " +
              "order by 2")
            .addEntity(ListDoubleValueBean.class)
            .setString("periode", periode)
            .setString("coyId", coyId)
            .setString("bussUnit", bussUnit)
            .setString("areaId", areaId)
            .setString("deptId", deptId)
            .setString("allDataCode", allDataCode)
            .list();
  }
  
  public List<ListDoubleValueBean> getKciDepts(String periode, String coyId, String bussUnit, String areaId, String officeId, String deptId) {
    return sessionFactory.getCurrentSession().createSQLQuery(
            "select a.dept_id code, a.dept_desc name, nvl(b.n_kci,0) assign_value, " +
              "nvl(( " +
                "select case " +
                  "when (b.n_kci is null or b.n_kci < 1 or b.n_kci > 3) then 'black' " +
                  "when b.n_kci < c.batas_bawah then 'green' " +
                  "when b.n_kci > c.batas_atas then 'red' " +
                  "else 'yellow' end " +
                  "from fifaud.aud_mst_grade c " +
                  "where c.grade_id = b.grade_kci),'black') descr " +
              "from fifaud.aud_mst_department a, ( " +
              "select dept_id, n_kci, grade_kci " +
                "from fifaud.aud_nilai_kci " +
                "where periode = :periode " +
                  "and company_id = :coyId " +
                  "and buss_unit = :bussUnit " +
                  "and area_id = :areaId " +
                  "and office_id = :officeId) b " +
              "where a.dept_id = decode(:deptId,:allDataCode,a.dept_id,:deptId) " +
                "and a.dept_id = b.dept_id (+) " +
              "order by 2")
            .addEntity(ListDoubleValueBean.class)
            .setString("periode", periode)
            .setString("coyId", coyId)
            .setString("bussUnit", bussUnit)
            .setString("areaId", areaId)
            .setString("officeId", officeId)
            .setString("deptId", deptId)
            .setString("allDataCode", allDataCode)
            .list();
  }
  
  public List<ColumnBean> getTableColumns(String tableName) {
    return sessionFactory.getCurrentSession().createSQLQuery(
                    "select column_name, data_type " +
                      "from user_tab_cols " +
                      "where table_name = :tableName " +
                      "order by column_id")
            .addEntity(ColumnBean.class)
            .setString("tableName", tableName)
            .list();
  }
  
  public List<StringBean> getTableData(
          String columnQuery, String tableName, String periode, String coyId, String bussUnit,
          String areaId, String officeId, String paramId, String measure1, String measure2) {
    return getTableQuery(columnQuery, tableName, periode, coyId, bussUnit, areaId, officeId, paramId, measure1, measure2)
            .list();
  }
  
  public List<StringBean> getTableData(
          String columnQuery, String tableName, int pageNo, String periode, String coyId, String bussUnit,
          String areaId, String officeId, String paramId, String measure1, String measure2) {   
    return getTableQuery(columnQuery, tableName, periode, coyId, bussUnit, areaId, officeId, paramId, measure1, measure2)
            .setFirstResult((pageNo - 1) * resultPerPage)
            .setMaxResults(resultPerPage)
            .list();
  }
  
  public int getTableDataCount(
          String tableName, String periode, String coyId, String bussUnit, String areaId,
          String officeId, String paramId, String measure1, String measure2) {
    StringBean sb = (StringBean) getTableQuery(
                      "TO_CHAR(count(*))", tableName, periode, coyId, bussUnit, areaId, officeId, paramId, measure1, measure2)
                    .uniqueResult();
    return Integer.parseInt(sb.getStringValue());
  }
  
  public GaugeBean getListKciGauge(String periode, String coyId, String bussUnit, String areaId, String officeId, String deptId) {
    List list = sessionFactory.getCurrentSession().createSQLQuery(
                    "select a.grade_id, a.batas_bawah, a.batas_atas, b.n_kci " +
                      "from fifaud.aud_mst_grade a, fifaud.aud_nilai_kci b " +
                      "where b.periode = :periode " +
                        "and b.company_id = :coyId " +
                        "and b.buss_unit = :bussUnit " +
                        "and b.area_id = :areaId " +
                        "and b.office_id = :officeId " +
                        "and b.dept_id = :deptId " +
                        "and a.grade_id = b.grade_kci")
            .addEntity(GaugeBean.class)
            .setString("periode",periode)
            .setString("coyId",coyId)
            .setString("bussUnit",bussUnit)
            .setString("areaId",areaId)
            .setString("officeId",officeId)
            .setString("deptId",deptId)
            .list();
    if(list.isEmpty())
      return new GaugeBean();
    else
      return (GaugeBean) list.get(0);
  }
  
  public List<KciPeriodeBean> getListKciPeriode(String periode, String coyId, String bussUnit, String areaId, String officeId, String deptId) {
    return sessionFactory.getCurrentSession().createSQLQuery(
            "select to_number(substr(periode,-2,2))-1 periode, n_kci " +
              "from fifaud.aud_nilai_kci " +
              "where periode between substr(:periode,1,4)||'01' and :periode " +
                "and company_id = :coyId " +
                "and buss_unit = :bussUnit " +
                "and area_id = :areaId " +
                "and office_id = :officeId " +
                "and dept_id = :deptId")
            .addEntity(KciPeriodeBean.class)
            .setString("periode",periode)
            .setString("coyId",coyId)
            .setString("bussUnit",bussUnit)
            .setString("areaId",areaId)
            .setString("officeId",officeId)
            .setString("deptId",deptId)
            .list();
  }
  
  public double getNilaiKciByPeriodeCoyDept(String periode, String coyId, String deptId) {
    StringBean sb = (StringBean) sessionFactory.getCurrentSession().createSQLQuery(
            "select to_char(n_kci) string_value " +
              "from fifaud.aud_nilai_kci " +
              "where periode = :periode " +
                "and company_id = :coyId " +
                "and dept_id = :deptId " +
                "and buss_unit = :allDataCode " +
                "and area_id = :allDataCode " +
                "and office_id = :allDataCode")
            .addEntity(StringBean.class)
            .setString("periode",periode)
            .setString("coyId",coyId)
            .setString("deptId",deptId)
            .setString("allDataCode",allDataCode)
            .uniqueResult();
    if(sb == null)
      return 0;
    return Double.parseDouble(sb.getStringValue());
  }
  
  public List<ParamValueBean> getParamValueByPeriodeCoyDept(String periode, String coyId, String deptId) {
    return sessionFactory.getCurrentSession().createSQLQuery(
            "select a.parameter_id, a.parameter_desc, nvl(b.n_kci,0) n_kci, " +
              "nvl(c.measurement_p1,0) measurement_p1, nvl(c.measurement_p2,0) measurement_p2, nvl(c.measurement_p3,0) measurement_p3, " +
              "nvl(d.area_problem_count,0) area_problem_count, nvl(e.branch_problem_count,0) branch_problem_count, " +
              "aud_get_serial_name_param_func(:periode,:coyId,'AREA_MAX_KCI',a.parameter_id) area_worst, " +
              "aud_get_max_value_param_func(:periode,:coyId,'AREA_MAX_KCI',a.parameter_id) area_worst_score, " +
              "aud_get_serial_name_param_func(:periode,:coyId,'OFFICE_MAX_KCI',a.parameter_id) branch_worst, " +
              "aud_get_max_value_param_func(:periode,:coyId,'OFFICE_MAX_KCI',a.parameter_id) branch_worst_score, " +
              "aud_get_serial_name_param_func(:periode,:coyId,'AREA_MAX_P1',a.parameter_id) area_most_trans_p1, " +
              "aud_get_max_value_param_func(:periode,:coyId,'AREA_MAX_P1',a.parameter_id) area_most_trans_value_p1, " +
              "aud_get_serial_name_param_func(:periode,:coyId,'AREA_MAX_P2',a.parameter_id) area_most_trans_p2, " +
              "aud_get_max_value_param_func(:periode,:coyId,'AREA_MAX_P2',a.parameter_id) area_most_trans_value_p2, " +
              "aud_get_serial_name_param_func(:periode,:coyId,'AREA_MAX_P1_P2',a.parameter_id) area_most_trans_p1_p2, " +
              "aud_get_max_value_param_func(:periode,:coyId,'AREA_MAX_P1_P2',a.parameter_id) area_most_trans_value_p1_p2, " +
              "aud_get_serial_name_param_func(:periode,:coyId,'OFFICE_MAX_P1',a.parameter_id) branch_most_trans_p1, " +
              "aud_get_max_value_param_func(:periode,:coyId,'OFFICE_MAX_P1',a.parameter_id) branch_most_trans_value_p1, " +
              "aud_get_serial_name_param_func(:periode,:coyId,'OFFICE_MAX_P2',a.parameter_id) branch_most_trans_p2, " +
              "aud_get_max_value_param_func(:periode,:coyId,'OFFICE_MAX_P2',a.parameter_id) branch_most_trans_value_p2, " +
              "aud_get_serial_name_param_func(:periode,:coyId,'OFFICE_MAX_P1_P2',a.parameter_id) branch_most_trans_p1_p2, " +
              "aud_get_max_value_param_func(:periode,:coyId,'OFFICE_MAX_P1_P2',a.parameter_id) branch_most_trans_value_p1_p2 " +
              "from fifaud.aud_mst_parameter a, ( " +
              "select parameter_id, n_kci " +
                "from fifaud.aud_nilai_parameter3 " +
                "where monyear = :periode " +
                  "and company_id = :coyId " +
                  "and buss_unit = :allDataCode " +
                  "and area_id = :allDataCode " +
                  "and office_id = :allDataCode) b, ( " +
              "select parameter_id, measurement_p1, measurement_p2, measurement_p3 " +
                "from fifaud.aud_sum_all2 " +
                "where bulan_transaksi = :periode " +
                  "and company_id = :coyId " +
                  "and buss_unit = :allDataCode " +
                  "and area_id = :allDataCode " +
                  "and office_id = :allDataCode) c, ( " +
              "select parameter_id, count(*) area_problem_count " +
                "from fifaud.aud_report_periodik " +
                "where flag IN ('AREA_MAX_KCI','AREA_MAX_P1','AREA_MAX_P2','AREA_MAX_P1_P2') " +
                  "and monyear = :periode " +
                  "and company_id = :coyId " +
                "group by parameter_id) d, ( " +
              "select parameter_id, count(*) branch_problem_count " +
                "from fifaud.aud_report_periodik " +
                "where flag IN ('OFFICE_MAX_KCI','OFFICE_MAX_P1','OFFICE_MAX_P2','OFFICE_MAX_P1_P2') " +
                  "and monyear = :periode " +
                  "and company_id = :coyId " +
                "group by parameter_id) e " +
              "where a.dept_id = :deptId " +
                "and a.visible = 'Y' " +
                "and a.parameter_id = b.parameter_id (+) " +
                "and a.parameter_id = c.parameter_id (+) " +
                "and a.parameter_id = d.parameter_id (+) " +
                "and a.parameter_id = e.parameter_id (+)")
            .addEntity(ParamValueBean.class)
            .setString("periode",periode)
            .setString("coyId",coyId)
            .setString("deptId", deptId)
            .setString("allDataCode",allDataCode)
            .list();
  }
  
  public List<String> getKciFifByDept(String periode, String coyId, String deptId) {
    return sessionFactory.getCurrentSession().createSQLQuery(
            "select to_char(n_kci) " +
              "from fifaud.aud_nilai_kci " +
              "where periode = :periode " +
                "and company_id = :coyId " +
                "and buss_unit = :allDataCode " +
                "and area_id = :allDataCode " +
                "and office_id = :allDataCode " +
                "and dept_id = decode(:deptId, :allDataCode, dept_id, :deptId) " +
                "and dept_id != :allDataCode " +
              "order by dept_id")
            .setString("periode",periode)
            .setString("coyId",coyId)
            .setString("deptId", deptId)
            .setString("allDataCode",allDataCode)
            .list();            
  }
  
  public List<String> getKciAreaParam(String periode, String coyId, String areaId, String deptId) {
    return sessionFactory.getCurrentSession().createSQLQuery(
            "select to_char(n_kci) " +
              "from fifaud.aud_nilai_kci " +
              "where periode = :periode " +
                "and company_id = :coyId " +
                "and buss_unit = :allDataCode " +
                "and area_id = :areaId " +
                "and office_id = :allDataCode " +
                "and dept_id = :deptId " +
            "union all " +
            "select to_char(n_kci) from ( " +
              "select a.n_kci " +
                "from fifaud.aud_nilai_kci a, fifaud.aud_mst_area_office b " +
                "where a.periode = :periode " +
                  "and a.company_id = :coyId " +
                  "and a.buss_unit = :allDataCode " +
                  "and a.area_id = :areaId " +
                  "and a.dept_id = :deptId " +
                  "and b.area_id = :areaId " +
                  "and a.office_id = b.office_id " +
                "order by a.office_id)")
            .setString("periode",periode)
            .setString("coyId",coyId)
            .setString("areaId",areaId)
            .setString("deptId", deptId)
            .setString("allDataCode",allDataCode)
            .list();
  }
  
  public List<ListDoubleValueBean> getWorstParamByScore(String periode, String coyId) {
    return sessionFactory.getCurrentSession().createSQLQuery(
            "select code, name, assign_value, descr from ( " +
              "select a.parameter_id code, a.parameter_desc name, b.n_kci assign_value, a.risk descr " +
                "from fifaud.aud_mst_parameter a, fifaud.aud_nilai_parameter3 b " +
                "where b.monyear = :periode " +
                  "and b.company_id = :coyId " +
                  "and b.buss_unit = :allDataCode " +
                  "and b.area_id = :allDataCode " +
                  "and b.office_id = :allDataCode " +
                  "and a.visible = 'Y' " +
                  "and a.parameter_id = b.parameter_id " +
                "order by assign_value desc) " +
              "where rownum <= 5 ")
            .addEntity(ListDoubleValueBean.class)
            .setString("periode",periode)
            .setString("coyId",coyId)
            .setString("allDataCode",allDataCode)
            .list();
  }
  
  public List<ListIntValueBean> getWorstParamByP1(String periode, String coyId) {
    return sessionFactory.getCurrentSession().createSQLQuery(
            "select code, name, assign_value, descr from ( " +
              "select a.parameter_id code, a.parameter_desc name, b.measurement_p1 assign_value, a.risk descr " +
                "from fifaud.aud_mst_parameter a, fifaud.aud_sum_all2 b " +
                "where b.bulan_transaksi = :periode " +
                  "and b.company_id = :coyId " +
                  "and b.buss_unit = :allDataCode " +
                  "and b.area_id = :allDataCode " +
                  "and b.office_id = :allDataCode " +
                  "and a.visible = 'Y' " +
                  "and a.parameter_id = b.parameter_id " +
                "order by assign_value desc) " +
              "where rownum <= 5 ")
            .addEntity(ListIntValueBean.class)
            .setString("periode",periode)
            .setString("coyId",coyId)
            .setString("allDataCode",allDataCode)
            .list();
  }
  
  public List<ListIntValueBean> getWorstParamByP2(String periode, String coyId) {
    return sessionFactory.getCurrentSession().createSQLQuery(
            "select code, name, assign_value, descr from ( " +
              "select a.parameter_id code, a.parameter_desc name, b.measurement_p2 assign_value, a.risk descr " +
                "from fifaud.aud_mst_parameter a, fifaud.aud_sum_all2 b " +
                "where b.bulan_transaksi = :periode " +
                  "and b.company_id = :coyId " +
                  "and b.buss_unit = :allDataCode " +
                  "and b.area_id = :allDataCode " +
                  "and b.office_id = :allDataCode " +
                  "and a.visible = 'Y' " +
                  "and a.parameter_id = b.parameter_id " +
                "order by assign_value desc) " +
              "where rownum <= 5 ")
            .addEntity(ListIntValueBean.class)
            .setString("periode",periode)
            .setString("coyId",coyId)
            .setString("allDataCode",allDataCode)
            .list();
  }
  
  public List<ListDoubleValueBean> getWorstParamByProcessScore(String periode, String coyId) {
    return sessionFactory.getCurrentSession().createSQLQuery(
            "select code, name, assign_value, descr from ( " +
              "select a.dept_id code, a.dept_desc name, b.n_kci assign_value, null descr " +
                "from fifaud.aud_mst_department a, fifaud.aud_nilai_kci b " +
                "where b.periode = :periode " +
                  "and b.company_id = :coyId " +
                  "and b.buss_unit = :allDataCode " +
                  "and b.area_id = :allDataCode " +
                  "and b.office_id = :allDataCode " +
                  "and b.dept_id != :allDataCode " +
                  "and a.dept_id = b.dept_id " +
                "order by assign_value desc) " +
              "where rownum <= 5 ")
            .addEntity(ListDoubleValueBean.class)
            .setString("periode",periode)
            .setString("coyId",coyId)
            .setString("allDataCode",allDataCode)
            .list();
  }
  
  public List<ListDoubleValueBean> getWorstParamByAreaScore(String periode, String coyId) {
    return sessionFactory.getCurrentSession().createSQLQuery(
            "select code, name, assign_value, descr from ( " +
              "select a.area_id code, a.area_name name, b.n_kci assign_value, null descr " +
                "from fifaud.aud_mst_area a, fifaud.aud_nilai_kci b " +
                "where b.periode = :periode " +
                  "and b.company_id = :coyId " +
                  "and b.buss_unit = :allDataCode " +
                  "and b.office_id = :allDataCode " +
                  "and b.dept_id = :allDataCode " +
                  "and b.area_id != :allDataCode " +
                  "and a.area_id = b.area_id " +
                "order by assign_value desc) " +
              "where rownum <= 5 ")
            .addEntity(ListDoubleValueBean.class)
            .setString("periode",periode)
            .setString("coyId",coyId)
            .setString("allDataCode",allDataCode)
            .list();
  }
  
  public List<ListDoubleValueBean> getWorstParamByBranchScore(String periode, String coyId) {
    return sessionFactory.getCurrentSession().createSQLQuery(
            "select code, name, assign_value, descr from ( " +
              "select a.office_code code, a.name_short name, b.n_kci assign_value, null descr " +
                "from fa_m_fifapps.fs_mst_offices a, fifaud.aud_nilai_kci b " +
                "where b.periode = :periode " +
                  "and b.company_id = :coyId " +
                  "and b.buss_unit = :allDataCode " +
                  "and b.area_id = :allDataCode " +
                  "and b.dept_id = :allDataCode " +
                  "and b.office_id != :allDataCode " +
                  "and a.office_code = b.office_id " +
                "order by assign_value desc) " +
              "where rownum <= 5 ")
            .addEntity(ListDoubleValueBean.class)
            .setString("periode",periode)
            .setString("coyId",coyId)
            .setString("allDataCode",allDataCode)
            .list();
  }
  
  public String getBranchInfo(String periode, String officeId) {
    return (String) sessionFactory.getCurrentSession().createSQLQuery(
            "select '{\"code\": \"'||a.office_code||'\", \"name\": \"'||a.name_short||'\", \"address\": \"'||a.address1||" +
              "'\", \"lastKciValue\": \"'||b.kci_value||'\", \"lastAuditVisit\": \"'||b.last_audit_visit||" +
              "'\", \"lastAuditPic\": \"'||b.last_audit_pic||'\", \"isrValue\": \"'||b.isr_value||'\"}' " + 
              "from fa_m_fifapps.fs_mst_offices a, fifaud.aud_trn_tracking_vw b " +
              "where b.periode = :periode " +
                "and b.office_id = :officeId " +
                "and b.dept_id = 'CABANG' " +
                "and a.office_code = b.office_id")
            .setString("periode",periode)
            .setString("officeId",officeId)
            .list().get(0);
  }
  
  public List<String> getRunningText() {
    return sessionFactory.getCurrentSession().createSQLQuery(
            "select '{\"name\": \"'||name_short||'\", \"value\": \"'||to_char(n_kci,'0.00')||'\", \"devValue\": \"'||to_char(abs(dev_val),'0.00')|| " +
              "'\", \"imgName\": \"'||(case when dev_val > 0 then 'triangle-up.png' else 'triangle-down.png' end)||'\"}' " +      
              "from ( " +
              "select a.name_short, b.n_kci, (b.n_kci - nvl(c.n_kci,0)) dev_val, b.flag " +
                "from fa_m_fifapps.fs_mst_offices a, fifaud.aud_running_text b, ( " +
                "select id, n_kci " +
                  "from fifaud.aud_running_text " +
                  "where periode = to_char(add_months(sysdate,-3),'yyyymm') " +
                    "and flag = 'OFFICE') c " +
                "where b.periode = to_char(add_months(sysdate,-2),'yyyymm') " +
                  "and b.flag = 'OFFICE' " +
                  "and a.office_code = b.id " +
                  "and b.id = c.id (+) " +
              "union all " +
              "select d.area_name, e.n_kci, (e.n_kci - nvl(f.n_kci,0)) dev_val, e.flag " +
                "from fifaud.aud_mst_area d, fifaud.aud_running_text e, ( " +
                "select id, n_kci " +
                  "from fifaud.aud_running_text " +
                  "where periode = to_char(add_months(sysdate,-3),'yyyymm') " +
                    "and flag = 'AREA') f " +
                "where e.periode = to_char(add_months(sysdate,-2),'yyyymm') " +
                  "and e.flag = 'AREA' " +
                  "and d.area_id = e.id " +
                  "and e.id = f.id (+) " +
                "order by 4,1)")
            .list();
  }
  
  public List<DiagFlowBean> getDiagFlow(String deptId) {
    return sessionFactory.getCurrentSession().createSQLQuery(
            "select distinct b.id, a.parent_id, b.name, level lvl " +
              "from fifaud.aud_mst_diagram_flow_asc a, " +
                "fifaud.aud_mst_diagram_flow b " +
              "where a.child_id = b.id " +
              "connect by child_id = prior parent_id " +
              "start with b.name in ( " +
                "select parameter_id " +
                  "from fifaud.aud_mst_parameter " +
                  "where dept_id = :deptId " +
                    "and visible = 'Y')" +
              "order by lvl, name")
            .addEntity(DiagFlowBean.class)
            .setString("deptId", deptId)
            .list();
  }
  
  private Query getTableQuery(
          String columnQuery, String tableName, String periode, String coyId, String bussUnit, String areaId,
          String officeId, String paramId, String measure1, String measure2) {
    return sessionFactory.getCurrentSession().createSQLQuery(
                    "select " + columnQuery + " string_value " +
                      "from fifaud." + tableName + " " +
                      "where monyear = :periode " +
                        "and company_id = decode(:coyId,:allDataCode,company_id,:coyId) " +
                        "and buss_unit = decode(:bussUnit,:allDataCode,buss_unit,:bussUnit) " +
                        "and area_id = decode(:areaId,:allDataCode,area_id,:areaId) " +
                        "and office_id = decode(:officeId,:allDataCode,office_id,:officeId) " +
                        "and parameter_id = :paramId " +
                        "and (measurement = :measure1 or measurement = :measure2)")
                    .addEntity(StringBean.class)
                    .setString("periode",periode)
                    .setString("coyId",coyId)
                    .setString("bussUnit",bussUnit)
                    .setString("areaId",areaId)
                    .setString("officeId",officeId)
                    .setString("paramId",paramId)
                    .setString("measure1",measure1)
                    .setString("measure2",measure2)
                    .setString("allDataCode", allDataCode);
  }
}
