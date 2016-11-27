/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.dao;

import com.safasoft.kci.bean.AudMstGrade;
import com.safasoft.kci.util.BaseDAO;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * @created Sep 8, 2016
 * @author awal
 */
@Repository("audMstGradeDAO")
public class AudMstGradeDAO extends BaseDAO<AudMstGrade> {

  public List<AudMstGrade> getByRangeIdAndName(String gradeIdPattern, String gradeNamePattern, int start, int num) {
    return sessionFactory.getCurrentSession().createQuery(
            "from " + domainClass.getName() + " " +
              "where gradeId like :gradeIdPattern " +
                "and gradeName like :gradeNamePattern")
            .setString("gradeIdPattern", "%"+gradeIdPattern+"%")
            .setString("gradeNamePattern", "%"+gradeNamePattern+"%")
            .setFirstResult(start)
            .setMaxResults(num)
            .list();
  }
  
  public int count(String gradeIdPattern, String gradeNamePattern) {
    List list = sessionFactory.getCurrentSession().createQuery(
            "select count(*) from " + domainClass.getName() + " " +
              "where gradeId like :gradeIdPattern " +
                "and gradeName like :gradeNamePattern")
            .setString("gradeIdPattern", "%"+gradeIdPattern+"%")
            .setString("gradeNamePattern", "%"+gradeNamePattern+"%")
            .list();
    return ((Long) list.get(0)).intValue();
  }
}
