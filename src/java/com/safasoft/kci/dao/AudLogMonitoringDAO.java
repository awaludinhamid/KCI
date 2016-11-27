/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.dao;

import com.safasoft.kci.bean.AudLogMonitoring;
import com.safasoft.kci.util.BaseDAO;
import com.safasoft.kci.util.GlobalStringVariable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

/**
 * @created Nov 22, 2016
 * @author awal
 */
@Repository("audLogMonitoringDAO")
public class AudLogMonitoringDAO extends BaseDAO<AudLogMonitoring> {
  
  private final String allDataCode = GlobalStringVariable.ALL_DATA_CODE.getStr();

  public List<AudLogMonitoring> getByRangeJobPackStatus
        (String jobNamePattern, String packNamePattern, String status, int start, int num) {
    return sessionFactory.getCurrentSession().createQuery(
            "from " + domainClass.getName() + " " +
              "where jobName like :jobNamePattern " +
                "and packageName like :packNamePattern " +
                "and status = decode(:status,:allDataCode,status,:status)")
            .setString("jobNamePattern", "%"+jobNamePattern+"%")
            .setString("packNamePattern", "%"+packNamePattern+"%")
            .setString("status", status)
            .setString("allDataCode", allDataCode)
            .setFirstResult(start)
            .setMaxResults(num)
            .list();
  }

  public List<AudLogMonitoring> getByRangeJobPackProcStatus
        (String jobNamePattern, String packNamePattern, String dateProcess, String status, int start, int num) {
    return sessionFactory.getCurrentSession().createQuery(
            "from " + domainClass.getName() + " " +
              "where jobName like :jobNamePattern " +
                "and packageName like :packNamePattern " +
                "and dateProcess = to_date(:dateProcess, 'yyyy-mm-dd') " +
                "and status = decode(:status,:allDataCode,status,:status)")
            .setString("jobNamePattern", "%"+jobNamePattern+"%")
            .setString("packNamePattern", "%"+packNamePattern+"%")
            .setString("dateProcess", dateProcess)
            .setString("status", status)
            .setString("allDataCode", allDataCode)
            .setFirstResult(start)
            .setMaxResults(num)
            .list();
  }

  public int count (String jobNamePattern, String packNamePattern, String status) {
    return ((Long) sessionFactory.getCurrentSession().createQuery(
            "select count(*) from " + domainClass.getName() + " " +
              "where jobName like :jobNamePattern " +
                "and packageName like :packNamePattern " +
                "and status = decode(:status,:allDataCode,status,:status)")
            .setString("jobNamePattern", "%"+jobNamePattern+"%")
            .setString("packNamePattern", "%"+packNamePattern+"%")
            .setString("status", status)
            .setString("allDataCode", allDataCode)
            .iterate().next()).intValue();
  }

  public int count(String jobNamePattern, String packNamePattern, String dateProcess, String status) {
    return ((Long) sessionFactory.getCurrentSession().createQuery(
            "select count(*) from " + domainClass.getName() + " " +
              "where jobName like :jobNamePattern " +
                "and packageName like :packNamePattern " +
                "and dateProcess = to_date(:dateProcess, 'yyyy-mm-dd') " +
                "and status = decode(:status,:allDataCode,status,:status)")
            .setString("jobNamePattern", "%"+jobNamePattern+"%")
            .setString("packNamePattern", "%"+packNamePattern+"%")
            .setString("dateProcess", dateProcess)
            .setString("status", status)
            .setString("allDataCode", allDataCode)
            .iterate().next()).intValue();
  }
  
  public List<String> getDateProcess() {
    Criteria criteria = sessionFactory.getCurrentSession().createCriteria(domainClass)
            .setProjection(Projections.distinct(Projections.property("dateProcess")));
    return criteria.list();
  }
  
  public int runJob(String jobName) {
    return sessionFactory.getCurrentSession().createSQLQuery(
            " { call sys.dbms_scheduler.run_job(:jobName) } ")
            .setString("jobName", jobName)
            .executeUpdate();
  }
}
