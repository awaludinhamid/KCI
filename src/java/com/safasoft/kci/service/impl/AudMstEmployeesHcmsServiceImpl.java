/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.service.impl;

import com.safasoft.kci.bean.AudMstEmployeesHcms;
import com.safasoft.kci.dao.AudMstEmployeesHcmsDAO;
import com.safasoft.kci.service.AudMstEmployeesHcmsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @created Nov 10, 2016
 * @author awal
 */
@Service("audMstEmployeesHcmsService")
@Transactional(readOnly=true)
public class AudMstEmployeesHcmsServiceImpl implements AudMstEmployeesHcmsService {

  @Autowired
  private AudMstEmployeesHcmsDAO audMstEmployeesHcmsDAO;

  @Override
  public AudMstEmployeesHcms getById(String id) {
    return audMstEmployeesHcmsDAO.getById(id);
  }

  @Override
  public List<AudMstEmployeesHcms> getAll() {
    return audMstEmployeesHcmsDAO.getAll();
  }
}
