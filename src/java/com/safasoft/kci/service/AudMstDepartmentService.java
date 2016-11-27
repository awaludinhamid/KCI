/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.service;

import com.safasoft.kci.bean.AudMstDepartment;
import java.util.List;

/**
 * @created Nov 18, 2016
 * @author awal
 */
public interface AudMstDepartmentService {

  AudMstDepartment getById(String id);
  List<AudMstDepartment> getAll();
}
