/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.service.impl;

import com.safasoft.kci.bean.AudMstGrade;
import com.safasoft.kci.dao.AudMstGradeDAO;
import com.safasoft.kci.service.AudMstGradeService;
import com.safasoft.kci.util.GlobalIntVariable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @created Sep 8, 2016
 * @author awal
 */
@Service("audMstGradeService")
@Transactional(readOnly=true)
public class AudMstGradeServiceImpl implements AudMstGradeService {

  @Autowired
  private AudMstGradeDAO audMstGradeDAO;
  private final int resultPerPage = GlobalIntVariable.RESULT_PER_PAGE.getVar();

  @Override
  @Transactional(readOnly=false)
  public AudMstGrade save(AudMstGrade amg) {
    return audMstGradeDAO.save(amg);
  }

  @Override
  @Transactional(readOnly=false)
  public AudMstGrade delete(AudMstGrade amg) {
    return audMstGradeDAO.delete(amg);
  }

  @Override
  public List<AudMstGrade> getAll() {
    return audMstGradeDAO.getAll();
  }

  @Override
  public AudMstGrade getById(String id) {
    return audMstGradeDAO.getById(id);
  }

  @Override
  public List<AudMstGrade> getByPage(int pageNo) {
    return audMstGradeDAO.getByRange((pageNo - 1) * resultPerPage, resultPerPage);
  }

  @Override
  public List<AudMstGrade> getByPageIdAndName(String param1, String param2, int pageNo) {
    return audMstGradeDAO.getByRangeIdAndName(param1, param2, (pageNo - 1) * resultPerPage, resultPerPage);
  }

  @Override
  public int count() {
    return audMstGradeDAO.count();
  }

  @Override
  public int count(String param1, String param2) {
    return audMstGradeDAO.count(param2, param2);
  }
}
