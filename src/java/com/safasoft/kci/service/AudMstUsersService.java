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

  AudMstUsers getById(int id);
  AudMstUsers getByUserName(String userName);
  List<AudMstUsers> getAll();
}
