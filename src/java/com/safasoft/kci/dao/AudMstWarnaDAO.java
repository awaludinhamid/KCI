/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.dao;

import com.safasoft.kci.bean.AudMstWarna;
import com.safasoft.kci.util.BaseDAO;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * @created Sep 5, 2016
 * @author awal
 */
@Repository
public class AudMstWarnaDAO extends BaseDAO<AudMstWarna> {

  @Override
  public List<AudMstWarna> getAll() {
    return sessionFactory.getCurrentSession().createQuery(
            "from " + domainClass.getName() + " " +
              "order by nilai_awal")
            .list();
  }
}
