/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.service.impl;

import com.safasoft.kci.bean.AudMstGrade;
import com.safasoft.kci.dao.AudMstGradeDAO;
import com.safasoft.kci.service.AudMstGradeService;
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

  @Override
  public List<AudMstGrade> getAll() {
    return audMstGradeDAO.getAll();
  }
}
