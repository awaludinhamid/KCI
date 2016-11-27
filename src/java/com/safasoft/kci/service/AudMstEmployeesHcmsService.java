/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.service;

import com.safasoft.kci.bean.AudMstEmployeesHcms;
import java.util.List;

/**
 * @created Nov 10, 2016
 * @author awal
 */
public interface AudMstEmployeesHcmsService {

  AudMstEmployeesHcms getById(String id);
  List<AudMstEmployeesHcms> getAll();
}
