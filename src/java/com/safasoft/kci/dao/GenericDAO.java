/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.dao;

import com.safasoft.kci.bean.support.ListBean;
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
  
  @Autowired
  protected SessionFactory sessionFactory;
  
  public List<ListBean> getBranches(String areaCode) {
    if(areaCode.equals("all"))
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
  
  public List<MapBean> getMapBranch() {
    return sessionFactory.getCurrentSession().createSQLQuery(
            "select a.poi_id, a.poi_type, a.latitude, a.longitude, " +
                    "0 measurement_p1, 0 measurement_p3, 0 measurement_p2, 0 nilai_kci, " +
                    "decode(mod(rownum,4),0,'RedPin1LargeB.png',1,'YellowPin1LargeB.png','GreenPin1LargeB.png') warna_icon, " +
                    "'CABANG '||(select name_short from fa_m_fifapps.fs_mst_offices b where b.office_code = a.poi_id) poi_name, " +
                    "to_char(sysdate,'dd Mon yyyy') last_audit, decode(mod(rownum,4),0,'UNSATISFACTORY',1,'IMPROVEMENT NEEDED','SATISFACTORY') grade " +
                    "from fifaud.aud_mst_poi a " +
                    "where a.poi_type = 'BRANCH'")
            .addEntity(MapBean.class)
            .list();
  }
  
  public List<MapBean> getMapPos(String officeId) {
    return sessionFactory.getCurrentSession().createSQLQuery(
            "select a.poi_id, a.poi_type, a.latitude, a.longitude, " +
                    "0 measurement_p1, 0 measurement_p3, 0 measurement_p2, 0 nilai_kci, " +
                    "decode(mod(rownum,4),0,'RedPin1LargeB.png',1,'YellowPin1LargeB.png','GreenPin1LargeB.png') warna_icon, " +
                    "(select pos_name from fifaud.aud_mst_pos b where b.pos_id = a.poi_id) poi_name, " +
                    "to_char(sysdate,'dd Mon yyyy') last_audit, decode(mod(rownum,4),0,'UNSATISFACTORY',1,'IMPROVEMENT NEEDED','SATISFACTORY') grade " +
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
  
  public List<ParameterBean> getParmeters(String deptId) {
    if(deptId.equals("all"))
      return sessionFactory.getCurrentSession().createSQLQuery(
            "select a.parameter_id, a.parameter_desc, " +
                    "NVL((select b.measurement_p1 from fifaud.aud_sum_all b where a.parameter_id = b.parameter_id and nvl(b.measurement_p1,0) != 0 and rownum = 1),0) measurement_p1, " +
                    "NVL((select b.measurement_p2 from fifaud.aud_sum_all b where a.parameter_id = b.parameter_id and nvl(b.measurement_p1,0) != 0 and rownum = 1),0) measurement_p2, " +
                    "NVL((select b.measurement_p3 from fifaud.aud_sum_all b where a.parameter_id = b.parameter_id and nvl(b.measurement_p1,0) != 0 and rownum = 1),0) measurement_p3 " +
                    "from fifaud.aud_mst_parameter a " +
                    "order by 2")
              .addEntity(ParameterBean.class)
              .list();
    else
      return sessionFactory.getCurrentSession().createSQLQuery(
            "select a.parameter_id, a.parameter_desc, " +
                    "NVL((select b.measurement_p1 from fifaud.aud_sum_all b where a.parameter_id = b.parameter_id and nvl(b.measurement_p1,0) != 0 and rownum = 1),0) measurement_p1, " +
                    "NVL((select b.measurement_p2 from fifaud.aud_sum_all b where a.parameter_id = b.parameter_id and nvl(b.measurement_p1,0) != 0 and rownum = 1),0) measurement_p2, " +
                    "NVL((select b.measurement_p3 from fifaud.aud_sum_all b where a.parameter_id = b.parameter_id and nvl(b.measurement_p1,0) != 0 and rownum = 1),0) measurement_p3 " +
                    "from fifaud.aud_mst_parameter a " +
                    "where a.dept_id = :deptId" +
                    "order by 2")
              .addEntity(ParameterBean.class)
              .setString("deptId", deptId)
              .list();
  }
}
