/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @created Sep 8, 2016
 * @author awal
 */
@Entity
@Table(name="AUD_MST_GRADE")
public class AudMstGrade implements Serializable {

  @Id
  @Column(name="GRADE_ID")
  private String gradeId;
  @Column(name="GRADE_NAME")
  private String gradeName;
  @Column(name="BATAS_BAWAH")
  private double batasBawah;
  @Column(name="BATAS_ATAS")
  private double batasAtas;

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
   * @return the gradeName
   */
  public String getGradeName() {
    return gradeName;
  }

  /**
   * @param gradeName the gradeName to set
   */
  public void setGradeName(String gradeName) {
    this.gradeName = gradeName;
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
}
