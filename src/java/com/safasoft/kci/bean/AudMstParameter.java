/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.bean;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.codehaus.jackson.annotate.JsonManagedReference;

/**
 * @created Nov 18, 2016
 * @author awal
 */
@Entity
@Table(name="AUD_MST_PARAMETER")
public class AudMstParameter implements Serializable {

  @Id
  @Column(name="PARAMETER_ID")
  private String parameterId;
  @Column(name="PARAMETER_DESC")
  private String parameterDesc;
  @Column(name="CREATED_BY")
  private String createdBy;
  @Column(name="CREATED_TIMESTAMP")
  private Date createdTimestamp;
  @Column(name="LASTUPDATE_BY")
  private String lastupdateBy;
  @Column(name="LASTUPDATE_TIMESTAMP")
  private Date lastupdateTimestamp;
  @Column(name="DEPT_DESC")
  private String deptDesc;
  @Column(name="TABLE_KCI")
  private String tableKci;
  @Column(name="FILTER_KCI")
  private String filterKci;
  @Column(name="FILTER_KCI2")
  private String filterKci2;
  @Column(name="VISIBLE")
  private String visible;
  @Column(name="RISK")
  private String risk;
  @Column(name="TABLE_KCI_OLD")
  private String tableKciOld;
  @Column(name="PACKAGE_NAME")
  private String packageName;
  @JsonManagedReference
  @ManyToOne
  @JoinColumn(name="DEPT_ID")
  private AudMstDepartment dept;

  /**
   * @return the parameterId
   */
  public String getParameterId() {
    return parameterId;
  }

  /**
   * @param parameterId the parameterId to set
   */
  public void setParameterId(String parameterId) {
    this.parameterId = parameterId;
  }

  /**
   * @return the parameterDesc
   */
  public String getParameterDesc() {
    return parameterDesc;
  }

  /**
   * @param parameterDesc the parameterDesc to set
   */
  public void setParameterDesc(String parameterDesc) {
    this.parameterDesc = parameterDesc;
  }

  /**
   * @return the createdBy
   */
  public String getCreatedBy() {
    return createdBy;
  }

  /**
   * @param createdBy the createdBy to set
   */
  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  /**
   * @return the createdTimestamp
   */
  public Date getCreatedTimestamp() {
    return createdTimestamp;
  }

  /**
   * @param createdTimestamp the createdTimestamp to set
   */
  public void setCreatedTimestamp(Date createdTimestamp) {
    this.createdTimestamp = createdTimestamp;
  }

  /**
   * @return the lastupdateBy
   */
  public String getLastupdateBy() {
    return lastupdateBy;
  }

  /**
   * @param lastupdateBy the lastupdateBy to set
   */
  public void setLastupdateBy(String lastupdateBy) {
    this.lastupdateBy = lastupdateBy;
  }

  /**
   * @return the lastupdateTimestamp
   */
  public Date getLastupdateTimestamp() {
    return lastupdateTimestamp;
  }

  /**
   * @param lastupdateTimestamp the lastupdateTimestamp to set
   */
  public void setLastupdateTimestamp(Date lastupdateTimestamp) {
    this.lastupdateTimestamp = lastupdateTimestamp;
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
   * @return the tableKci
   */
  public String getTableKci() {
    return tableKci;
  }

  /**
   * @param tableKci the tableKci to set
   */
  public void setTableKci(String tableKci) {
    this.tableKci = tableKci;
  }

  /**
   * @return the filterKci
   */
  public String getFilterKci() {
    return filterKci;
  }

  /**
   * @param filterKci the filterKci to set
   */
  public void setFilterKci(String filterKci) {
    this.filterKci = filterKci;
  }

  /**
   * @return the filterKci2
   */
  public String getFilterKci2() {
    return filterKci2;
  }

  /**
   * @param filterKci2 the filterKci2 to set
   */
  public void setFilterKci2(String filterKci2) {
    this.filterKci2 = filterKci2;
  }

  /**
   * @return the visible
   */
  public String getVisible() {
    return visible;
  }

  /**
   * @param visible the visible to set
   */
  public void setVisible(String visible) {
    this.visible = visible;
  }

  /**
   * @return the risk
   */
  public String getRisk() {
    return risk;
  }

  /**
   * @param risk the risk to set
   */
  public void setRisk(String risk) {
    this.risk = risk;
  }

  /**
   * @return the tableKciOld
   */
  public String getTableKciOld() {
    return tableKciOld;
  }

  /**
   * @param tableKciOld the tableKciOld to set
   */
  public void setTableKciOld(String tableKciOld) {
    this.tableKciOld = tableKciOld;
  }

  /**
   * @return the packageName
   */
  public String getPackageName() {
    return packageName;
  }

  /**
   * @param packageName the packageName to set
   */
  public void setPackageName(String packageName) {
    this.packageName = packageName;
  }

  /**
   * @return the dept
   */
  public AudMstDepartment getDept() {
    return dept;
  }

  /**
   * @param dept the dept to set
   */
  public void setDept(AudMstDepartment dept) {
    this.dept = dept;
  }
}
