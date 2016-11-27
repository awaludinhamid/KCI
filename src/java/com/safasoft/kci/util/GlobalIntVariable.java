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
public enum GlobalIntVariable {
  
  RESULT_PER_PAGE(10),
  MIN_GRADE_VALUE(1),
  MAX_GRADE_VALUE(3);
  
  private final int var;
  
  private GlobalIntVariable(final int var) {
    this.var = var;
  }
  
  public int getVar() {
    return var;
  }
}
