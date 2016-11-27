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
 * @created Oct 11, 2016
 * @author awal
 */
@Entity
public class ParamValueBean implements Serializable {

  @Id
  @Column(name="PARAMETER_ID")
  private String parameterId;
  @Column(name="PARAMETER_DESC")
  private String parameterDesc;
  @Column(name="N_KCI")
  private double nKci;
  @Column(name="MEASUREMENT_P1")
  private int measurementP1;
  @Column(name="MEASUREMENT_P2")
  private int measurementP2;
  @Column(name="MEASUREMENT_P3")
  private int measurementP3;
  @Column(name="AREA_PROBLEM_COUNT")
  private int areaProblemCount;
  @Column(name="BRANCH_PROBLEM_COUNT")
  private int branchProblemCount;
  @Column(name="AREA_WORST")
  private String areaWorst;
  @Column(name="AREA_WORST_SCORE")
  private int areaWorstScore;
  @Column(name="BRANCH_WORST")
  private String branchWorst;
  @Column(name="BRANCH_WORST_SCORE")
  private int branchWorstScore;
  @Column(name="AREA_MOST_TRANS_P1")
  private String areaMostTransP1;
  @Column(name="AREA_MOST_TRANS_VALUE_P1")
  private int areaMostTransValueP1;
  @Column(name="AREA_MOST_TRANS_P2")
  private String areaMostTransP2;
  @Column(name="AREA_MOST_TRANS_VALUE_P2")
  private int areaMostTransValueP2;
  @Column(name="AREA_MOST_TRANS_P1_P2")
  private String areaMostTransP1P2;
  @Column(name="AREA_MOST_TRANS_VALUE_P1_P2")
  private int areaMostTransValueP1P2;
  @Column(name="BRANCH_MOST_TRANS_P1")
  private String branchMostTransP1;
  @Column(name="BRANCH_MOST_TRANS_VALUE_P1")
  private int branchMostTransValueP1;
  @Column(name="BRANCH_MOST_TRANS_P2")
  private String branchMostTransP2;
  @Column(name="BRANCH_MOST_TRANS_VALUE_P2")
  private int branchMostTransValueP2;
  @Column(name="BRANCH_MOST_TRANS_P1_P2")
  private String branchMostTransP1P2;
  @Column(name="BRANCH_MOST_TRANS_VALUE_P1_P2")
  private int branchMostTransValueP1P2;

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
   * @return the nKci
   */
  public double getnKci() {
    return nKci;
  }

  /**
   * @param nKci the nKci to set
   */
  public void setnKci(double nKci) {
    this.nKci = nKci;
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

  /**
   * @return the areaProblemCount
   */
  public int getAreaProblemCount() {
    return areaProblemCount;
  }

  /**
   * @param areaProblemCount the areaProblemCount to set
   */
  public void setAreaProblemCount(int areaProblemCount) {
    this.areaProblemCount = areaProblemCount;
  }

  /**
   * @return the branchProblemCount
   */
  public int getBranchProblemCount() {
    return branchProblemCount;
  }

  /**
   * @param branchProblemCount the branchProblemCount to set
   */
  public void setBranchProblemCount(int branchProblemCount) {
    this.branchProblemCount = branchProblemCount;
  }

  /**
   * @return the areaWorst
   */
  public String getAreaWorst() {
    return areaWorst;
  }

  /**
   * @param areaWorst the areaWorst to set
   */
  public void setAreaWorst(String areaWorst) {
    this.areaWorst = areaWorst;
  }

  /**
   * @return the areaWorstScore
   */
  public int getAreaWorstScore() {
    return areaWorstScore;
  }

  /**
   * @param areaWorstScore the areaWorstScore to set
   */
  public void setAreaWorstScore(int areaWorstScore) {
    this.areaWorstScore = areaWorstScore;
  }

  /**
   * @return the branchWorst
   */
  public String getBranchWorst() {
    return branchWorst;
  }

  /**
   * @param branchWorst the branchWorst to set
   */
  public void setBranchWorst(String branchWorst) {
    this.branchWorst = branchWorst;
  }

  /**
   * @return the branchWorstScore
   */
  public int getBranchWorstScore() {
    return branchWorstScore;
  }

  /**
   * @param branchWorstScore the branchWorstScore to set
   */
  public void setBranchWorstScore(int branchWorstScore) {
    this.branchWorstScore = branchWorstScore;
  }

  /**
   * @return the areaMostTransP1
   */
  public String getAreaMostTransP1() {
    return areaMostTransP1;
  }

  /**
   * @param areaMostTransP1 the areaMostTransP1 to set
   */
  public void setAreaMostTransP1(String areaMostTransP1) {
    this.areaMostTransP1 = areaMostTransP1;
  }

  /**
   * @return the areaMostTransValueP1
   */
  public int getAreaMostTransValueP1() {
    return areaMostTransValueP1;
  }

  /**
   * @param areaMostTransValueP1 the areaMostTransValueP1 to set
   */
  public void setAreaMostTransValueP1(int areaMostTransValueP1) {
    this.areaMostTransValueP1 = areaMostTransValueP1;
  }

  /**
   * @return the areaMostTransP2
   */
  public String getAreaMostTransP2() {
    return areaMostTransP2;
  }

  /**
   * @param areaMostTransP2 the areaMostTransP2 to set
   */
  public void setAreaMostTransP2(String areaMostTransP2) {
    this.areaMostTransP2 = areaMostTransP2;
  }

  /**
   * @return the areaMostTransValueP2
   */
  public int getAreaMostTransValueP2() {
    return areaMostTransValueP2;
  }

  /**
   * @param areaMostTransValueP2 the areaMostTransValueP2 to set
   */
  public void setAreaMostTransValueP2(int areaMostTransValueP2) {
    this.areaMostTransValueP2 = areaMostTransValueP2;
  }

  /**
   * @return the areaMostTransP1P2
   */
  public String getAreaMostTransP1P2() {
    return areaMostTransP1P2;
  }

  /**
   * @param areaMostTransP1P2 the areaMostTransP1P2 to set
   */
  public void setAreaMostTransP1P2(String areaMostTransP1P2) {
    this.areaMostTransP1P2 = areaMostTransP1P2;
  }

  /**
   * @return the areaMostTransValueP1P2
   */
  public int getAreaMostTransValueP1P2() {
    return areaMostTransValueP1P2;
  }

  /**
   * @param areaMostTransValueP1P2 the areaMostTransValueP1P2 to set
   */
  public void setAreaMostTransValueP1P2(int areaMostTransValueP1P2) {
    this.areaMostTransValueP1P2 = areaMostTransValueP1P2;
  }

  /**
   * @return the branchMostTransP1
   */
  public String getBranchMostTransP1() {
    return branchMostTransP1;
  }

  /**
   * @param branchMostTransP1 the branchMostTransP1 to set
   */
  public void setBranchMostTransP1(String branchMostTransP1) {
    this.branchMostTransP1 = branchMostTransP1;
  }

  /**
   * @return the branchMostTransValueP1
   */
  public int getBranchMostTransValueP1() {
    return branchMostTransValueP1;
  }

  /**
   * @param branchMostTransValueP1 the branchMostTransValueP1 to set
   */
  public void setBranchMostTransValueP1(int branchMostTransValueP1) {
    this.branchMostTransValueP1 = branchMostTransValueP1;
  }

  /**
   * @return the branchMostTransP2
   */
  public String getBranchMostTransP2() {
    return branchMostTransP2;
  }

  /**
   * @param branchMostTransP2 the branchMostTransP2 to set
   */
  public void setBranchMostTransP2(String branchMostTransP2) {
    this.branchMostTransP2 = branchMostTransP2;
  }

  /**
   * @return the branchMostTransValueP2
   */
  public int getBranchMostTransValueP2() {
    return branchMostTransValueP2;
  }

  /**
   * @param branchMostTransValueP2 the branchMostTransValueP2 to set
   */
  public void setBranchMostTransValueP2(int branchMostTransValueP2) {
    this.branchMostTransValueP2 = branchMostTransValueP2;
  }

  /**
   * @return the branchMostTransP1P2
   */
  public String getBranchMostTransP1P2() {
    return branchMostTransP1P2;
  }

  /**
   * @param branchMostTransP1P2 the branchMostTransP1P2 to set
   */
  public void setBranchMostTransP1P2(String branchMostTransP1P2) {
    this.branchMostTransP1P2 = branchMostTransP1P2;
  }

  /**
   * @return the branchMostTransValueP1P2
   */
  public int getBranchMostTransValueP1P2() {
    return branchMostTransValueP1P2;
  }

  /**
   * @param branchMostTransValueP1P2 the branchMostTransValueP1P2 to set
   */
  public void setBranchMostTransValueP1P2(int branchMostTransValueP1P2) {
    this.branchMostTransValueP1P2 = branchMostTransValueP1P2;
  }

}
