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
 * @created Sep 1, 2016
 * @author awal
 */
@Entity
public class GaugeBean implements Serializable {

  @Id
  @Column(name="GRADE_ID")
  private String gradeId;
  @Column(name="BATAS_BAWAH")
  private double batasBawah;
  @Column(name="BATAS_ATAS")
  private double batasAtas;
  @Column(name="N_KCI")
  private double nilaiKci;

  /**
   * @return the gradeId
   */
  public String getGradeId() {
    return gradeId;
  }

  /**
   * @param gradeId the gradeId to set
   */
  public void setGradeId(String gradeId) {
    this.gradeId = gradeId;
  }

  /**
   * @return the batasBawah
   */
  public double getBatasBawah() {
    return batasBawah;
  }

  /**
   * @param batasBawah the batasBawah to set
   */
  public void setBatasBawah(double batasBawah) {
    this.batasBawah = batasBawah;
  }

  /**
   * @return the batasAtas
   */
  public double getBatasAtas() {
    return batasAtas;
  }

  /**
   * @param batasAtas the batasAtas to set
   */
  public void setBatasAtas(double batasAtas) {
    this.batasAtas = batasAtas;
  }

  /**
   * @return the nilaiKci
   */
  public double getNilaiKci() {
    return nilaiKci;
  }

  /**
   * @param nilaiKci the nilaiKci to set
   */
  public void setNilaiKci(double nilaiKci) {
    this.nilaiKci = nilaiKci;
  }
}
