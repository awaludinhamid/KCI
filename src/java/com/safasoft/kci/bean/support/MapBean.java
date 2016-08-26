/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.bean.support;

import com.safasoft.kci.bean.support.pk.MapBeanPk;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 * @created Aug 24, 2016
 * @author awal
 */
@Entity
@IdClass(MapBeanPk.class)
public class MapBean implements Serializable {

  @Id
  private String poiId;
  @Id
  private String poiType;
  @Column(name="LATITUDE")
  private double latitude;
  @Column(name="LONGITUDE")
  private double longitude;
  @Column(name="MEASUREMENT_P1")
  private int measurementP1;
  @Column(name="MEASUREMENT_P2")
  private int measurementP2;
  @Column(name="MEASUREMENT_P3")
  private int measurementP3;
  @Column(name="NILAI_KCI")
  private int nilaiKci;
  @Column(name="WARNA_ICON")
  private String warnaIcon;
  @Column(name="POI_NAME")
  private String poiName;
  @Column(name="LAST_AUDIT")
  private String lastAudit;
  @Column(name="GRADE")
  private String grade;

  /**
   * @return the poiId
   */
  public String getPoiId() {
    return poiId;
  }

  /**
   * @param poiId the poiId to set
   */
  public void setPoiId(String poiId) {
    this.poiId = poiId;
  }

  /**
   * @return the poiType
   */
  public String getPoiType() {
    return poiType;
  }

  /**
   * @param poiType the poiType to set
   */
  public void setPoiType(String poiType) {
    this.poiType = poiType;
  }

  /**
   * @return the latitude
   */
  public double getLatitude() {
    return latitude;
  }

  /**
   * @param latitude the latitude to set
   */
  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  /**
   * @return the longitude
   */
  public double getLongitude() {
    return longitude;
  }

  /**
   * @param longitude the longitude to set
   */
  public void setLongitude(double longitude) {
    this.longitude = longitude;
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
   * @return the nilaiKci
   */
  public int getNilaiKci() {
    return nilaiKci;
  }

  /**
   * @param nilaiKci the nilaiKci to set
   */
  public void setNilaiKci(int nilaiKci) {
    this.nilaiKci = nilaiKci;
  }

  /**
   * @return the warnaIcon
   */
  public String getWarnaIcon() {
    return warnaIcon;
  }

  /**
   * @param warnaIcon the warnaIcon to set
   */
  public void setWarnaIcon(String warnaIcon) {
    this.warnaIcon = warnaIcon;
  }

  /**
   * @return the poiName
   */
  public String getPoiName() {
    return poiName;
  }

  /**
   * @param poiName the poiName to set
   */
  public void setPoiName(String poiName) {
    this.poiName = poiName;
  }

  /**
   * @return the lastAudit
   */
  public String getLastAudit() {
    return lastAudit;
  }

  /**
   * @param lastAudit the lastAudit to set
   */
  public void setLastAudit(String lastAudit) {
    this.lastAudit = lastAudit;
  }

  /**
   * @return the grade
   */
  public String getGrade() {
    return grade;
  }

  /**
   * @param grade the grade to set
   */
  public void setGrade(String grade) {
    this.grade = grade;
  }
}
