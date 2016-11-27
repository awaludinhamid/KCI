/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.dao;

import com.safasoft.kci.bean.AudMstUsers;
import com.safasoft.kci.util.BaseDAO;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * @created Apr 22, 2016
 * @author awal
 */
@Repository("audMstUsersDAO")
public class AudMstUsersDAO extends BaseDAO<AudMstUsers> {

  public AudMstUsers getByUserName(String userName) {
    return (AudMstUsers) sessionFactory.getCurrentSession().createQuery(
            "from " + domainClass.getName() + " usr " +
            "where usr.employee.npk = :userName")
            .setString("userName", userName)
            .uniqueResult();    
  }
  
  public List<AudMstUsers> getByRangeName(String emplNamePattern, int start, int num) {
    return sessionFactory.getCurrentSession().createQuery(
            "from " + domainClass.getName() + " usr " +
              "where usr.employee.namaEmployee like :emplNamePattern")
            .setString("emplNamePattern", "%"+emplNamePattern+"%")
            .setFirstResult(start)
            .setMaxResults(num)
            .list();
  }
  
  public List<AudMstUsers> getByRangeNameAndRole(String emplNamePattern, int roleId, int start, int num) {
    return sessionFactory.getCurrentSession().createQuery(
            "from " + domainClass.getName() + " usr " +
              "where usr.employee.namaEmployee like :emplNamePattern " +
                "and usr.role.roleId = :roleId")
            .setString("emplNamePattern", "%"+emplNamePattern+"%")
            .setInteger("roleId", roleId)
            .setFirstResult(start)
            .setMaxResults(num)
            .list();
  }
  
  public int count(String emplNamePattern) {
    return ((Long) sessionFactory.getCurrentSession().createQuery(
            "select count(*) from " + domainClass.getName() + " usr " +
              "where usr.employee.namaEmployee like :emplNamePattern")
            .setString("emplNamePattern", "%"+emplNamePattern+"%")
            .iterate().next()).intValue();
  }
  
  public int count(String emplNamePattern, int roleId) {
    return ((Long) sessionFactory.getCurrentSession().createQuery(
            "select count(*) from " + domainClass.getName() + " usr " +
               "where usr.employee.namaEmployee like :emplNamePattern " +
                "and usr.role.roleId = :roleId")
            .setString("emplNamePattern", "%"+emplNamePattern+"%")
            .setInteger("roleId", roleId)
            .iterate().next()).intValue();
  }
}
