/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @created Apr 22, 2016
 * @author awal
 */
@Entity
@Table(name="AUD_MST_USERS")
public class AudMstUsers implements Serializable {

  @Id
  @Column(name="USER_ID")
  private int userId;
  @Column(name="ROLE_ID")
  private int roleId;
  @Column(name="USER_NAME")
  private String userName;
  @Column(name="USER_REAL_NAME")
  private String userRealName;

  /**
   * @return the userId
   */
  public int getUserId() {
    return userId;
  }

  /**
   * @param userId the userId to set
   */
  public void setUserId(int userId) {
    this.userId = userId;
  }

  /**
   * @return the roleId
   */
  public int getRoleId() {
    return roleId;
  }

  /**
   * @param roleId the roleId to set
   */
  public void setRoleId(int roleId) {
    this.roleId = roleId;
  }

  /**
   * @return the userName
   */
  public String getUserName() {
    return userName;
  }

  /**
   * @param userName the userName to set
   */
  public void setUserName(String userName) {
    this.userName = userName;
  }

  /**
   * @return the userRealName
   */
  public String getUserRealName() {
    return userRealName;
  }

  /**
   * @param userRealName the userRealName to set
   */
  public void setUserRealName(String userRealName) {
    this.userRealName = userRealName;
  }
}
