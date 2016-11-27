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
public class CellPropBean {

  private final short fontWeight;
  private final short fontHeight;
  private final short backgroundColor;
  
  public CellPropBean(short fontWeight, short fontHeight, short backgroundColor) {
    this.fontWeight = fontWeight;
    this.fontHeight = fontHeight;
    this.backgroundColor = backgroundColor;
  }
  
  /**
   * @return the fontWeight
   */
  public short getFontWeight() {
    return fontWeight;
  }

  /**
   * @return the backgroundColor
   */
  public short getBackgroundColor() {
    return backgroundColor;
  }

  /**
   * @return the fontHeight
   */
  public short getFontHeight() {
    return fontHeight;
  }
}
