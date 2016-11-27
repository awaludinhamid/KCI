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
 * @created Sep 20, 2016
 * @author awal
 */
@Entity
public class StringBean implements Serializable {

  @Id
  @Column(name="STRING_VALUE")
  private String stringValue;

  /**
   * @return the stringValue
   */
  public String getStringValue() {
    return stringValue;
  }

  /**
   * @param stringValue the stringValue to set
   */
  public void setStringValue(String stringValue) {
    this.stringValue = stringValue;
  }
}
