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
import javax.persistence.Table;

/**
 * @created Nov 1, 2016
 * @author awal
 */
@Entity
@Table(name="AUD_TRN_TRACKING_VW")
public class AudTrnTracking implements Serializable {

  @Id
  @Column(name="ID")
  private int id;
  @Column(name="PERIODE")
  private String periode;
  @Column(name="NPK")
  private String npk;
  @Column(name="EMPLOYEE_NAME")
  private String employeeName;
  @Column(name="OFFICE_ID")
  private String officeId;
  @Column(name="DEPT_ID")
  private String deptId;
  @Column(name="JOB_CODE")
  private String jobCode;
  @Column(name="POSITION")
  private String position;
  @Column(name="START_DATE")
  private Date startDate;
  @Column(name="END_DATE")
  private Date endDate;
  @Column(name="KCI_VALUE")
  private Double kciValue;
  @Column(name="ISR_VALUE")
  private Double isrValue;
  @Column(name="LAST_AUDIT_GRADING")
  private Double lastAuditGrading;
  @Column(name="LAST_AUDIT_VISIT")
  private Date lastAuditVisit;
  @Column(name="LAST_AUDIT_PIC")
  private String lastAuditPic;
  @Column(name="DAY_DIFF")
  private int dayDiff;
  @Column(name="MASA_JABATAN")
  private String masaJabatan;

  /**
   * @return the id
   */
  public int getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * @return the periode
   */
  public String getPeriode() {
    return periode;
  }

  /**
   * @param periode the periode to set
   */
  public void setPeriode(String periode) {
    this.periode = periode;
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
   * @return the officeId
   */
  public String getOfficeId() {
    return officeId;
  }

  /**
   * @param officeId the officeId to set
   */
  public void setOfficeId(String officeId) {
    this.officeId = officeId;
  }

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
   * @return the employeeName
   */
  public String getEmployeeName() {
    return employeeName;
  }

  /**
   * @param employeeName the employeeName to set
   */
  public void setEmployeeName(String employeeName) {
    this.employeeName = employeeName;
  }

  /**
   * @return the position
   */
  public String getPosition() {
    return position;
  }

  /**
   * @param position the position to set
   */
  public void setPosition(String position) {
    this.position = position;
  }

  /**
   * @return the startDate
   */
  public Date getStartDate() {
    return startDate;
  }

  /**
   * @param startDate the startDate to set
   */
  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  /**
   * @return the lastAuditGrading
   */
  public Double getLastAuditGrading() {
    return lastAuditGrading;
  }

  /**
   * @param lastAuditGrading the lastAuditGrading to set
   */
  public void setLastAuditGrading(Double lastAuditGrading) {
    this.lastAuditGrading = lastAuditGrading;
  }

  /**
   * @return the kciValue
   */
  public Double getKciValue() {
    return kciValue;
  }

  /**
   * @param kciValue the kciValue to set
   */
  public void setKciValue(Double kciValue) {
    this.kciValue = kciValue;
  }

  /**
   * @return the endDate
   */
  public Date getEndDate() {
    return endDate;
  }

  /**
   * @param endDate the endDate to set
   */
  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  /**
   * @return the lastAuditVisit
   */
  public Date getLastAuditVisit() {
    return lastAuditVisit;
  }

  /**
   * @param lastAuditVisit the lastAuditVisit to set
   */
  public void setLastAuditVisit(Date lastAuditVisit) {
    this.lastAuditVisit = lastAuditVisit;
  }

  /**
   * @return the lastAuditPic
   */
  public String getLastAuditPic() {
    return lastAuditPic;
  }

  /**
   * @param lastAuditPic the lastAuditPic to set
   */
  public void setLastAuditPic(String lastAuditPic) {
    this.lastAuditPic = lastAuditPic;
  }

  /**
   * @return the isrValue
   */
  public Double getIsrValue() {
    return isrValue;
  }

  /**
   * @param isrValue the isrValue to set
   */
  public void setIsrValue(Double isrValue) {
    this.isrValue = isrValue;
  }

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
   * @return the dayDiff
   */
  public int getDayDiff() {
    return dayDiff;
  }

  /**
   * @param dayDiff the dayDiff to set
   */
  public void setDayDiff(int dayDiff) {
    this.dayDiff = dayDiff;
  }

  /**
   * @return the masaJabatan
   */
  public String getMasaJabatan() {
    return masaJabatan;
  }

  /**
   * @param masaJabatan the masaJabatan to set
   */
  public void setMasaJabatan(String masaJabatan) {
    this.masaJabatan = masaJabatan;
  }
}
