/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @created Sep 5, 2016
 * @author awal
 */
@Entity
@Table(name="AUD_MST_WARNA")
public class AudMstWarna implements Serializable {
  
  @Id
  @Column(name="WARNA")
  private String warna;
  @Column(name="NILAI_AWAL")
  private double nilaiAwal;
  @Column(name="NILAI_AKHIR")
  private double nilaiAkhir;
  @Column(name="CREATED_BY")
  private String createdBy;
  @Column(name="CREATED_TIMESTAMP")
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdTimestamp;
  @Column(name="LASTUPDATE_BY")
  private String lastupdateBy;
  @Column(name="LASTUPDATE_TIMESTAMP")
  @Temporal(TemporalType.TIMESTAMP)
  private Date lastupdateTimestamp;

  /**
   * @return the warna
   */
  public String getWarna() {
    return warna;
  }

  /**
   * @param warna the warna to set
   */
  public void setWarna(String warna) {
    this.warna = warna;
  }

  /**
   * @return the nilaiAwal
   */
  public double getNilaiAwal() {
    return nilaiAwal;
  }

  /**
   * @param nilaiAwal the nilaiAwal to set
   */
  public void setNilaiAwal(double nilaiAwal) {
    this.nilaiAwal = nilaiAwal;
  }

  /**
   * @return the nilaiAkhir
   */
  public double getNilaiAkhir() {
    return nilaiAkhir;
  }

  /**
   * @param nilaiAkhir the nilaiAkhir to set
   */
  public void setNilaiAkhir(double nilaiAkhir) {
    this.nilaiAkhir = nilaiAkhir;
  }

  /**
   * @return the createdBy
   */
  public String getCreatedBy() {
    return createdBy;
  }

  /**
   * @param createdBy the createdBy to set
   */
  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  /**
   * @return the createdTimestamp
   */
  public Date getCreatedTimestamp() {
    return createdTimestamp;
  }

  /**
   * @param createdTimestamp the createdTimestamp to set
   */
  public void setCreatedTimestamp(Date createdTimestamp) {
    this.createdTimestamp = createdTimestamp;
  }

  /**
   * @return the lastupdateBy
   */
  public String getLastupdateBy() {
    return lastupdateBy;
  }

  /**
   * @param lastupdateBy the lastupdateBy to set
   */
  public void setLastupdateBy(String lastupdateBy) {
    this.lastupdateBy = lastupdateBy;
  }

  /**
   * @return the lastupdateTimestamp
   */
  public Date getLastupdateTimestamp() {
    return lastupdateTimestamp;
  }

  /**
   * @param lastupdateTimestamp the lastupdateTimestamp to set
   */
  public void setLastupdateTimestamp(Date lastupdateTimestamp) {
    this.lastupdateTimestamp = lastupdateTimestamp;
  }

}
