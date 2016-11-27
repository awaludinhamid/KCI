/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.dao;

import com.safasoft.kci.bean.AudMstParameter;
import com.safasoft.kci.util.BaseDAO;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * @created Nov 18, 2016
 * @author awal
 */
@Repository("audMstParameterDAO")
public class AudMstParameterDAO extends BaseDAO<AudMstParameter> {

  public List<AudMstParameter> getByRangeParamPack(String paramNamePattern, String packNamePattern, int start, int num) {
    return sessionFactory.getCurrentSession().createQuery(
            "from " + domainClass.getName() + " param " +
              "where param.parameterDesc like :paramNamePattern " +
                "and param.packageName like :packNamePattern")
            .setString("paramNamePattern", "%"+paramNamePattern+"%")
            .setString("packNamePattern", "%"+packNamePattern+"%")
            .setFirstResult(start)
            .setMaxResults(num)
            .list();
  }

  public List<AudMstParameter> getByRangeParamPackDept(String paramNamePattern, String packNamePattern, String deptId, int start, int num) {
    return sessionFactory.getCurrentSession().createQuery(
            "from " + domainClass.getName() + " param " +
              "where param.parameterDesc like :paramNamePattern " +
                "and param.packageName like :packNamePattern " +
                "and param.dept.deptId = :deptId")
            .setString("paramNamePattern", "%"+paramNamePattern+"%")
            .setString("packNamePattern", "%"+packNamePattern+"%")
            .setString("deptId", deptId)
            .setFirstResult(start)
            .setMaxResults(num)
            .list();
  }

  public int count(String paramNamePattern, String packNamePattern) {
    return ((Long) sessionFactory.getCurrentSession().createQuery(
            "select count(*) from " + domainClass.getName() + " param " +
              "where param.parameterDesc like :paramNamePattern " +
                "and param.packageName like :packNamePattern")
            .setString("paramNamePattern", "%"+paramNamePattern+"%")
            .setString("packNamePattern", "%"+packNamePattern+"%")
            .iterate().next()).intValue();
  }

  public int count(String paramNamePattern, String packNamePattern, String deptId) {
    return ((Long) sessionFactory.getCurrentSession().createQuery(
            "select count(*) from " + domainClass.getName() + " param " +
              "where param.parameterDesc like :paramNamePattern " +
                "and param.packageName like :packNamePattern " +
                "and param.dept.deptId = :deptId")
            .setString("paramNamePattern", "%"+paramNamePattern+"%")
            .setString("packNamePattern", "%"+packNamePattern+"%")
            .setString("deptId", deptId)
            .iterate().next()).intValue();
  }
  
  public List<String> getPackageNameList() {
    Criteria cr = sessionFactory.getCurrentSession().createCriteria(domainClass)
            .add(Restrictions.isNotNull("packageName"))
            .addOrder(Order.asc("packageName"))
            .setProjection(
                    Projections.distinct(Projections.property("packageName"))
            );
    return cr.list();
    
  }
}
