/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.bean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.codehaus.jackson.annotate.JsonBackReference;

/**
 * @created Apr 22, 2016
 * @author awal
 */
@Entity
@Table(name="AUD_MST_ROLES")
public class AudMstRoles implements Serializable {

  @Id
  @Column(name="ROLE_ID")
  private int roleId;
  @Column(name="ROLE_NAME")
  private String roleName;
  @Column(name="ROLE_SEC")
  private String roleSec;
  @JsonBackReference
  @OneToMany(mappedBy="role")
  private List<AudMstUsers> users;

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
   * @return the roleName
   */
  public String getRoleName() {
    return roleName;
  }

  /**
   * @param roleName the roleName to set
   */
  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  /**
   * @return the users
   */
  public List<AudMstUsers> getUsers() {
    return users;
  }

  /**
   * @param users the users to set
   */
  public void setUsers(List<AudMstUsers> users) {
    this.users = users;
  }

  /**
   * @return the roleSec
   */
  public String getRoleSec() {
    return roleSec;
  }

  /**
   * @param roleSec the roleSec to set
   */
  public void setRoleSec(String roleSec) {
    this.roleSec = roleSec;
  }
}
