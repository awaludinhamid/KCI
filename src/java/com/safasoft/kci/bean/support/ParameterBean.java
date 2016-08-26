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
 * @created Aug 25, 2016
 * @author awal
 */
@Entity
public class ParameterBean implements Serializable {

  @Id
  @Column(name="PARAMETER_ID")
  private String parameterId;
  @Column(name="PARAMETER_DESC")
  private String parameterDesc;
  @Column(name="MEASUREMENT_P1")
  private int measurementP1;
  @Column(name="MEASUREMENT_P2")
  private int measurementP2;
  @Column(name="MEASUREMENT_P3")
  private int measurementP3;

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
   * @return the measurementP1
   */
  public int getMeasurementP1() {
    return measurementP1;
  }

  /**
   * @param measurementP1 the measurementP1 to set
   */
  public void setMeasurementP1(int measurementP1) {
    this.measurementP1 = measurementP1;
  }

  /**
   * @return the measurementP2
   */
  public int getMeasurementP2() {
    return measurementP2;
  }

  /**
   * @param measurementP2 the measurementP2 to set
   */
  public void setMeasurementP2(int measurementP2) {
    this.measurementP2 = measurementP2;
  }

  /**
   * @return the measurementP3
   */
  public int getMeasurementP3() {
    return measurementP3;
  }

  /**
   * @param measurementP3 the measurementP3 to set
   */
  public void setMeasurementP3(int measurementP3) {
    this.measurementP3 = measurementP3;
  }
}
