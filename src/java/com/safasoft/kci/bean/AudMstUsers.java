/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.codehaus.jackson.annotate.JsonManagedReference;

/**
 * @created Apr 22, 2016
 * @author awal
 */
@Entity
@Table(name="AUD_MST_USERS")
public class AudMstUsers implements Serializable {

  @Id
  @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AUD_MST_USERS_GEN")
  @SequenceGenerator(name="AUD_MST_USERS_GEN",sequenceName="AUD_MST_USERS_SEQ")
  @Column(name="USER_ID")
  private int userId;
  @Column(name="USER_REAL_NAME")
  private String userRealName;
  @Column(name="HAS_CPTS")
  private String hasCpts;
  @Column(name="HAS_ORG_CHART")
  private String hasOrgChart;
  @Column(name="HAS_REPORT")
  private String hasReport;
  @JsonManagedReference
  @OneToOne
  @JoinColumn(name="USER_NAME")
  private AudMstEmployeesHcms employee;
  @JsonManagedReference
  @ManyToOne
  @JoinColumn(name="ROLE_ID")
  private AudMstRoles role;
  

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

  /**
   * @return the role
   */
  public AudMstRoles getRole() {
    return role;
  }

  /**
   * @param role the role to set
   */
  public void setRole(AudMstRoles role) {
    this.role = role;
  }

  /**
   * @return the employee
   */
  public AudMstEmployeesHcms getEmployee() {
    return employee;
  }

  /**
   * @param employee the employee to set
   */
  public void setEmployee(AudMstEmployeesHcms employee) {
    this.employee = employee;
  }

  /**
   * @return the hasCpts
   */
  public String getHasCpts() {
    return hasCpts;
  }

  /**
   * @param hasCpts the hasCpts to set
   */
  public void setHasCpts(String hasCpts) {
    this.hasCpts = hasCpts;
  }

  /**
   * @return the hasOrgChart
   */
  public String getHasOrgChart() {
    return hasOrgChart;
  }

  /**
   * @param hasOrgChart the hasOrgChart to set
   */
  public void setHasOrgChart(String hasOrgChart) {
    this.hasOrgChart = hasOrgChart;
  }

  /**
   * @return the hasReport
   */
  public String getHasReport() {
    return hasReport;
  }

  /**
   * @param hasReport the hasReport to set
   */
  public void setHasReport(String hasReport) {
    this.hasReport = hasReport;
  }
}
