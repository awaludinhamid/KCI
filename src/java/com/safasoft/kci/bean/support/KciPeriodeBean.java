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
 * @created Sep 23, 2016
 * @author awal
 */
@Entity
public class KciPeriodeBean implements Serializable {

  @Id
  @Column(name="PERIODE")
  private int periode;
  @Column(name="N_KCI")
  private double nKci;

  /**
   * @return the periode
   */
  public int getPeriode() {
    return periode;
  }

  /**
   * @param periode the periode to set
   */
  public void setPeriode(int periode) {
    this.periode = periode;
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
}
