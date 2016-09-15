/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.dao;

import com.safasoft.kci.bean.support.GaugeBean;
import com.safasoft.kci.bean.support.KciGaugeBean;
import com.safasoft.kci.bean.support.ListBean;
import com.safasoft.kci.bean.support.ListValueBean;
import com.safasoft.kci.bean.support.ParameterBean;
import com.safasoft.kci.bean.support.MapBean;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @created Aug 17, 2016
 * @author awal
 */
@Repository("genericDAO")
public class GenericDAO {
  
  private final String ALL_DATA_CODE = "ALL";
  
  @Autowired
  protected SessionFactory sessionFactory;
  
  public List<ListBean> getBranches(String areaCode) {
    if(areaCode.equals(ALL_DATA_CODE))
      return sessionFactory.getCurrentSession().createSQLQuery(
            "select office_code code, name_short name " +
                    "from fa_m_fifapps.fs_mst_offices " +
                    "order by 2")
              .addEntity(ListBean.class)
              .list();
    else
      return sessionFactory.getCurrentSession().createSQLQuery(
            "select office_code code, name_short name " +
                    "from fa_m_fifapps.fs_mst_offices " +
                    "where office_code in ( " +
                      "select office_id " +
                        "from fifaud.aud_mst_area_office "+
                        "where area_id = :areaCode) " +
                    "order by 2")
              .addEntity(ListBean.class)
              .setString("areaCode", areaCode)
              .list();
  }
  
  public List<ListBean> getAllCompanies() {
    return sessionFactory.getCurrentSession().createSQLQuery(
            "select coy_id code, name_short name " +
                    "from fa_m_fifapps.fs_mst_company " +
                    "order by 1")
            .addEntity(ListBean.class)
            .list();
  }
  
  public List<ListBean> getAllPeriodes() {
    return sessionFactory.getCurrentSession().createSQLQuery(
            "select periode code, periode name " +
                    "from fifaud.aud_mst_periode " +
                    "order by 1 desc")
            .addEntity(ListBean.class)
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
  
  public List<ListBean> getAllRegions() {
    return sessionFactory.getCurrentSession().createSQLQuery(
            "select area_id code, area_name name " +
                    "from fifaud.aud_mst_area " +
                    "order by 2")
            .addEntity(ListBean.class)
            .list();
  }
  
  public List<ListBean> getAllBussUnits() {
    return sessionFactory.getCurrentSession().createSQLQuery(
            "select distinct buss_unit code, buss_unit name " +
                    "from fa_m_fifapps.fs_mst_buss_unit " +
                    //"where segment_bu2 is not null " +
                    "order by 2")
            .addEntity(ListBean.class)
            .list();
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
            .setString("allDataCode", ALL_DATA_CODE)
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
            "select a.parameter_id, a.parameter_desc, " +
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
                  "and a.parameter_id = b.parameter_id (+)")
            .addEntity(ParameterBean.class)
            .setString("periode",periode)
            .setString("coyId",coyId)
            .setString("bussUnit",bussUnit)
            .setString("areaId",areaId)
            .setString("officeId",officeId)
            .setString("deptId",deptId)
            .setString("allDataCode", ALL_DATA_CODE)
            .list();
  }
  
  public KciGaugeBean getKciGauge(String periode, String coyId, String bussUnit, String areaId, String officeId, String deptId) {
    //
    KciGaugeBean kgb = new KciGaugeBean();
    kgb.setKciFifgroup(getListKciGauge(periode, coyId, bussUnit, ALL_DATA_CODE, ALL_DATA_CODE, deptId));
    kgb.setKciArea(getListKciGauge(periode, coyId, bussUnit, areaId, ALL_DATA_CODE, deptId));
    kgb.setKciBranch(getListKciGauge(periode, coyId, bussUnit, areaId, officeId, deptId));
    //
    return kgb;
  }
  
  public List<ListValueBean> getKciAreas(String periode, String coyId, String bussUnit, String deptId) {
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
            .addEntity(ListValueBean.class)
            .setString("periode",periode)
            .setString("coyId",coyId)
            .setString("bussUnit",bussUnit)
            .setString("officeId",ALL_DATA_CODE)
            .setString("deptId",deptId)
            .list();
  }
  
  public List<ListValueBean> getKciBranches(String periode, String coyId, String bussUnit, String areaId, String deptId) {
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
                "and a.area_id = b.area_id (+) " +
                "and a.office_code = b.office_id (+) " +
              "order by 2")
            .addEntity(ListValueBean.class)
            .setString("periode", periode)
            .setString("coyId", coyId)
            .setString("bussUnit", bussUnit)
            .setString("areaId", areaId)
            .setString("deptId", deptId)
            .setString("allDataCode", ALL_DATA_CODE)
            .list();
  }
  
  public List<ListValueBean> getKciDepts(String periode, String coyId, String bussUnit, String areaId, String officeId, String deptId) {
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
                "and a.dept_id = decode(:deptId,:allDataCode,a.dept_id,:deptId) " +
                "and a.dept_id = b.dept_id (+) " +
              "order by 2")
            .addEntity(ListValueBean.class)
            .setString("periode", periode)
            .setString("coyId", coyId)
            .setString("bussUnit", bussUnit)
            .setString("areaId", areaId)
            .setString("officeId", officeId)
            .setString("deptId", deptId)
            .setString("allDataCode", ALL_DATA_CODE)
            .list();
  }
  
  private GaugeBean getListKciGauge(String periode, String coyId, String bussUnit, String areaId, String officeId, String deptId) {
    return (GaugeBean) sessionFactory.getCurrentSession().createSQLQuery(
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
            .list()
            .get(0);
  }
}
