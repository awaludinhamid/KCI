/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.service;

import com.safasoft.kci.bean.AudMstUsers;
import java.util.List;

/**
 * @created Apr 22, 2016
 * @author awal
 */
public interface AudMstUsersService {

  AudMstUsers save(AudMstUsers user);
  AudMstUsers delete(AudMstUsers user);
  AudMstUsers getById(int id);
  AudMstUsers getByUserName(String userName);
  List<AudMstUsers> getAll();
  List<AudMstUsers> getByPage(int pageNo);
  List<AudMstUsers> getByPageName(String emplNamePattern, int pageNo);
  List<AudMstUsers> getByPageNameAndRole(String emplNamePattern, int roleId, int pageNo);
  int count();
  int count(String emplNamePattern);
  int count(String emplNamePattern, int roleId);
}
