/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.bean.support.pk;

import java.io.Serializable;
import javax.persistence.Column;

/**
 * @created Aug 24, 2016
 * @author awal
 */
public class MapBeanPk implements Serializable {

  @Column(name="POI_ID")
  private String poiId;
  @Column(name="POI_TYPE")
  private String poiType;

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
  
  @Override
  public int hashCode() {
    return (int) poiId.hashCode() + poiType.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if(obj == null) return false;
    if(obj == this) return true;
    if(!(obj instanceof MapBeanPk)) return false;
    final MapBeanPk other = (MapBeanPk) obj;
    return other.poiId.equals(poiId) && other.poiType.equals(poiType);
  }
}
