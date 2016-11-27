/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.service.impl;

import com.safasoft.kci.bean.AudMstDepartment;
import com.safasoft.kci.dao.AudMstDepartmentDAO;
import com.safasoft.kci.service.AudMstDepartmentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @created Nov 18, 2016
 * @author awal
 */
@Service("audMstDepartmentService")
@Transactional(readOnly=true)
public class AudMstDepartmentServiceImpl implements AudMstDepartmentService {

  @Autowired
  private AudMstDepartmentDAO audMstDepartmentDAO;

  @Override
  public List<AudMstDepartment> getAll() {
    return audMstDepartmentDAO.getAll();
  }

  @Override
  public AudMstDepartment getById(String id) {
    return audMstDepartmentDAO.getById(id);
  }
}
