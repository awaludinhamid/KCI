/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.bean.support;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @created Sep 12, 2016
 * @author awal
 */
@Entity
public class ListDoubleValueBean implements Serializable {

  @Id
  @Column(name="CODE")
  private String code;
  @Column(name="NAME")
  private String name;
  @Column(name="ASSIGN_VALUE")
  private Double assignValue;
  @Column(name="DESCR")
  private String descr;

  /**
   * @return the assignValue
   */
  public Double getAssignValue() {
    return assignValue;
  }

  /**
   * @param assignValue the assignValue to set
   */
  public void setAssignValue(Double assignValue) {
    this.assignValue = assignValue;
  }

  /**
   * @return the descr
   */
  public String getDescr() {
    return descr;
  }

  /**
   * @param descr the descr to set
   */
  public void setDescr(String descr) {
    this.descr = descr;
  }

  /**
   * @return the code
   */
  public String getCode() {
    return code;
  }

  /**
   * @param code the code to set
   */
  public void setCode(String code) {
    this.code = code;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }
}
