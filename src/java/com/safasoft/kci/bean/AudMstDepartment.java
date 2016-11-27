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
 * @created Nov 18, 2016
 * @author awal
 */
@Entity
@Table(name="AUD_MST_DEPARTMENT")
public class AudMstDepartment implements Serializable {

  @Id
  @Column(name="DEPT_ID")
  private String deptId;
  @Column(name="DEPT_DESC")
  private String deptDesc;
  @JsonBackReference
  @OneToMany(mappedBy="dept")
  private List<AudMstParameter> params;

  /**
   * @return the deptId
   */
  public String getDeptId() {
    return deptId;
  }

  /**
   * @param deptId the deptId to set
   */
  public void setDeptId(String deptId) {
    this.deptId = deptId;
  }

  /**
   * @return the deptDesc
   */
  public String getDeptDesc() {
    return deptDesc;
  }

  /**
   * @param deptDesc the deptDesc to set
   */
  public void setDeptDesc(String deptDesc) {
    this.deptDesc = deptDesc;
  }

  /**
   * @return the params
   */
  public List<AudMstParameter> getParams() {
    return params;
  }

  /**
   * @param params the params to set
   */
  public void setParams(List<AudMstParameter> params) {
    this.params = params;
  }
}
