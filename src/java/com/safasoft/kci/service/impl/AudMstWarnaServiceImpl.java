/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.service.impl;

import com.safasoft.kci.bean.AudMstWarna;
import com.safasoft.kci.dao.AudMstWarnaDAO;
import com.safasoft.kci.service.AudMstWarnaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @created Sep 5, 2016
 * @author awal
 */
@Service("audMstWarnaService")
@Transactional(readOnly=true)
public class AudMstWarnaServiceImpl implements AudMstWarnaService {

  @Autowired
  private AudMstWarnaDAO audMstWarnaDAO;

  @Override
  public List<AudMstWarna> getAll() {
    return audMstWarnaDAO.getAll();
  }
}
