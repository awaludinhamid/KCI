/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.dao;

import com.safasoft.kci.bean.AudTrnTracking;
import com.safasoft.kci.util.BaseDAO;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * @created Nov 1, 2016
 * @author awal
 */
@Repository("audTrnTracking")
public class AudTrnTrackingDAO extends BaseDAO<AudTrnTracking> {

  public List<AudTrnTracking> getByPatternAndRange(String param1, String param2, int start, int num) {
    return sessionFactory.getCurrentSession().createQuery(
            "from " + domainClass.getName() + " " +
            "where periode like :param1 " +
              "and (officeId like :param2 " +
              "or npk like :param2 " +
              "or employeeName like :param2)")
            .setString("param1", "%"+param1+"%")
            .setString("param2", "%"+param2+"%")
            .setFirstResult(start)
            .setMaxResults(num)
            .list();
  }
  
  public AudTrnTracking getBranchHead(String periode, String officeId) {
    return (AudTrnTracking) sessionFactory.getCurrentSession().createQuery(
            "from " + domainClass.getName() + " " +
            "where deptId = 'CABANG' " +
              "and periode = :periode " +
              "and officeId = :officeId")
            .setString("periode", periode)
            .setString("officeId", officeId)
            .list().get(0);
  }
  
  public List<AudTrnTracking> getDeptHead(String periode, String officeId) {
    return sessionFactory.getCurrentSession().createQuery(
            "from " + domainClass.getName() + " " +
            "where deptId NOT IN ('CABANG','POS','KIOS') " +
              "and instr(position,'HEAD') > 0 " +
              "and periode = :periode " +
              "and officeId = :officeId")
            .setString("periode", periode)
            .setString("officeId", officeId)
            .list();
  }
  
  public List<AudTrnTracking> getPosHead(String periode, String officeId) {
    return sessionFactory.getCurrentSession().createQuery(
            "from " + domainClass.getName() + " " +
            "where deptId IN ('POS','KIOS') " +
              "and instr(position,'HEAD') > 0 " +
              "and periode = :periode " +
              "and officeId = :officeId")
            .setString("periode", periode)
            .setString("officeId", officeId)
            .list();
  }
  
  public List<AudTrnTracking> getByRangeNpk(String npk, int start, int num) {
    return sessionFactory.getCurrentSession().createQuery(
            "from " + domainClass.getName() + " " +
              "where npk = :npk")
            .setString("npk", npk)
            .setFirstResult(start)
            .setMaxResults(num)
            .list();
  }
  
  public int count(String param1, String param2) {
    List list = sessionFactory.getCurrentSession().createQuery(
            "select count(*) from " + domainClass.getName() + " " +
            "where periode like :param1 " +
              "and (officeId like :param2 " +
              "or npk like :param2 " +
              "or employeeName like :param2)")
            .setString("param1", "%"+param1+"%")
            .setString("param2", "%"+param2+"%")
            .list();
    return ((Long) list.get(0)).intValue();    
  }
  
  public int count(String npk) {
    return ((Long) sessionFactory.getCurrentSession().createQuery(
            "select count(*) from " + domainClass.getName() + " " +
              "where npk = :npk")
            .setString("npk", npk)
            .iterate().next()).intValue();
  }
}
