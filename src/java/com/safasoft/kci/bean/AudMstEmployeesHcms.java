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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.codehaus.jackson.annotate.JsonBackReference;

/**
 * @created Nov 10, 2016
 * @author awal
 */
@Entity
@Table(name="AUD_MST_EMPLOYEES_HCMS")
public class AudMstEmployeesHcms implements Serializable {

  @Id
  @Column(name="NPK")
  private String npk;
  @Column(name="NAMA_EMPLOYEE")
  private String namaEmployee;
  @Column(name="BRANCH_ID")
  private String branchId;
  @Column(name="BRANCH_NAME")
  private String branchName;
  @Column(name="JOB_ID")
  private int jobId;
  @Column(name="JOB_CODE")
  private String jobCode;
  @Column(name="JOB_NAME")
  private String jobName;
  @Column(name="EFFECTIVE_START_DATE")
  private Date effectiveStartDate;
  @Column(name="EFFECTIVE_END_DATE")
  private Date effectiveEndDate;
  @Column(name="COMPANY_ID")
  private String companyId;
  @Column(name="HIRE_DATE")
  private Date hireDate;
  @JsonBackReference
  @OneToOne(mappedBy="employee")
  private AudMstUsers user;

  /**
   * @return the npk
   */
  public String getNpk() {
    return npk;
  }

  /**
   * @param npk the npk to set
   */
  public void setNpk(String npk) {
    this.npk = npk;
  }

  /**
   * @return the namaEmployee
   */
  public String getNamaEmployee() {
    return namaEmployee;
  }

  /**
   * @param namaEmployee the namaEmployee to set
   */
  public void setNamaEmployee(String namaEmployee) {
    this.namaEmployee = namaEmployee;
  }

  /**
   * @return the branchId
   */
  public String getBranchId() {
    return branchId;
  }

  /**
   * @param branchId the branchId to set
   */
  public void setBranchId(String branchId) {
    this.branchId = branchId;
  }

  /**
   * @return the branchName
   */
  public String getBranchName() {
    return branchName;
  }

  /**
   * @param branchName the branchName to set
   */
  public void setBranchName(String branchName) {
    this.branchName = branchName;
  }

  /**
   * @return the jobId
   */
  public int getJobId() {
    return jobId;
  }

  /**
   * @param jobId the jobId to set
   */
  public void setJobId(int jobId) {
    this.jobId = jobId;
  }

  /**
   * @return the jobCode
   */
  public String getJobCode() {
    return jobCode;
  }

  /**
   * @param jobCode the jobCode to set
   */
  public void setJobCode(String jobCode) {
    this.jobCode = jobCode;
  }

  /**
   * @return the jobName
   */
  public String getJobName() {
    return jobName;
  }

  /**
   * @param jobName the jobName to set
   */
  public void setJobName(String jobName) {
    this.jobName = jobName;
  }

  /**
   * @return the effectiveStartDate
   */
  public Date getEffectiveStartDate() {
    return effectiveStartDate;
  }

  /**
   * @param effectiveStartDate the effectiveStartDate to set
   */
  public void setEffectiveStartDate(Date effectiveStartDate) {
    this.effectiveStartDate = effectiveStartDate;
  }

  /**
   * @return the effectiveEndDate
   */
  public Date getEffectiveEndDate() {
    return effectiveEndDate;
  }

  /**
   * @param effectiveEndDate the effectiveEndDate to set
   */
  public void setEffectiveEndDate(Date effectiveEndDate) {
    this.effectiveEndDate = effectiveEndDate;
  }

  /**
   * @return the companyId
   */
  public String getCompanyId() {
    return companyId;
  }

  /**
   * @param companyId the companyId to set
   */
  public void setCompanyId(String companyId) {
    this.companyId = companyId;
  }

  /**
   * @return the hireDate
   */
  public Date getHireDate() {
    return hireDate;
  }

  /**
   * @param hireDate the hireDate to set
   */
  public void setHireDate(Date hireDate) {
    this.hireDate = hireDate;
  }

  /**
   * @return the user
   */
  public AudMstUsers getUser() {
    return user;
  }

  /**
   * @param user the user to set
   */
  public void setUser(AudMstUsers user) {
    this.user = user;
  }
}
