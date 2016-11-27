/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.service;

import com.safasoft.kci.bean.AudMstGrade;
import java.util.List;

/**
 * @created Sep 8, 2016
 * @author awal
 */
public interface AudMstGradeService {

  AudMstGrade save(AudMstGrade amg);
  AudMstGrade delete(AudMstGrade amg);
  List<AudMstGrade> getAll();
  AudMstGrade getById(String id);
  List<AudMstGrade> getByPage(int pageNo);
  List<AudMstGrade> getByPageIdAndName(String param1, String param2, int pageNo);
  int count();
  int count(String param1, String param2);
}
