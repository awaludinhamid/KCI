/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.dao;

import com.safasoft.kci.bean.AudMstUsers;
import com.safasoft.kci.util.BaseDAO;
import org.springframework.stereotype.Repository;

/**
 * @created Apr 22, 2016
 * @author awal
 */
@Repository("audMstUsersDAO")
public class AudMstUsersDAO extends BaseDAO<AudMstUsers> {

  public AudMstUsers getByUserName(String userName) {
    return (AudMstUsers) sessionFactory.getCurrentSession().createQuery(
            "from " + domainClass.getName() +
            " where userName = :userName")
            .setString("userName", userName)
            .uniqueResult();    
  }
}
