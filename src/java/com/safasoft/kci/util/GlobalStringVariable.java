/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safasoft.kci.util;

/**
 *
 * @author awal
 */
public enum GlobalStringVariable {
  
  ALL_DATA_CODE("ALL");
  
  private final String str;
  
  private GlobalStringVariable(final String str) {
    this.str = str;
  }
  
  public String getStr() {
    return str;
  }
}
