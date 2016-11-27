/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.bean.support;

import java.util.List;

/**
 * @created Sep 17, 2016
 * @author awal
 */
public class TableContents {

  private List<String> headers;
  private List<StringBean> contents;
  private int count;

  /**
   * @return the headers
   */
  public List<String> getHeaders() {
    return headers;
  }

  /**
   * @param headers the headers to set
   */
  public void setHeaders(List<String> headers) {
    this.headers = headers;
  }

  /**
   * @return the contents
   */
  public List<StringBean> getContents() {
    return contents;
  }

  /**
   * @param contents the contents to set
   */
  public void setContents(List<StringBean> contents) {
    this.contents = contents;
  }

  /**
   * @return the count
   */
  public int getCount() {
    return count;
  }

  /**
   * @param count the count to set
   */
  public void setCount(int count) {
    this.count = count;
  }
}
