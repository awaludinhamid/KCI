/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.util.bean;

/**
 * @created Oct 17, 2016
 * @author awal
 */
public class CellContentBean {

  private final Object value;
  private final String template;
  
  public CellContentBean(Object value, String template) {
    this.value = value;
    this.template = template;
  }

  /**
   * @return the value
   */
  public Object getValue() {
    return value;
  }

  /**
   * @return the template
   */
  public String getTemplate() {
    return template;
  }
}
