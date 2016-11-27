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
 * @created Nov 22, 2016
 * @author awal
 */
@Entity
@Table(name="AUD_LOG_MONITORING_VW")
public class AudLogMonitoring implements Serializable {

  @Id
  @Column(name="JOB_NAME")
  private String jobName;
  @Column(name="PACKAGE_NAME")
  private String packageName;
  @Column(name="DATE_PROCESS")
  private Date dateProcess;
  @Column(name="STATUS")
  private String status;
  @Column(name="COMMENTS")
  private String comments;

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
   * @return the dateProcess
   */
  public Date getDateProcess() {
    return dateProcess;
  }

  /**
   * @param dateProcess the dateProcess to set
   */
  public void setDateProcess(Date dateProcess) {
    this.dateProcess = dateProcess;
  }

  /**
   * @return the status
   */
  public String getStatus() {
    return status;
  }

  /**
   * @param status the status to set
   */
  public void setStatus(String status) {
    this.status = status;
  }

  /**
   * @return the comments
   */
  public String getComments() {
    return comments;
  }

  /**
   * @param comments the comments to set
   */
  public void setComments(String comments) {
    this.comments = comments;
  }
}
