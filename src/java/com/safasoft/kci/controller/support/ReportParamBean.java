/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.controller.support;

import com.safasoft.kci.util.bean.CellContentBean;
import com.safasoft.kci.util.bean.CellPropBean;
import java.util.List;
import java.util.Map;

/**
 * @created Oct 18, 2016
 * @author awal
 */
public class ReportParamBean {

  private List<List<CellContentBean>> titles;
  private List<List<CellContentBean>> contents;
  private List<List<Integer>> merges;
  private Map<String,CellPropBean> templates;
  private int maxColIdx;
  private String fileName;

  /**
   * @return the titles
   */
  public List<List<CellContentBean>> getTitles() {
    return titles;
  }

  /**
   * @param titles the titles to set
   */
  public void setTitles(List<List<CellContentBean>> titles) {
    this.titles = titles;
  }

  /**
   * @return the contents
   */
  public List<List<CellContentBean>> getContents() {
    return contents;
  }

  /**
   * @param contents the contents to set
   */
  public void setContents(List<List<CellContentBean>> contents) {
    this.contents = contents;
  }

  /**
   * @return the merges
   */
  public List<List<Integer>> getMerges() {
    return merges;
  }

  /**
   * @param merges the merges to set
   */
  public void setMerges(List<List<Integer>> merges) {
    this.merges = merges;
  }

  /**
   * @return the templates
   */
  public Map<String,CellPropBean> getTemplates() {
    return templates;
  }

  /**
   * @param templates the templates to set
   */
  public void setTemplates(Map<String,CellPropBean> templates) {
    this.templates = templates;
  }

  /**
   * @return the maxColIdx
   */
  public int getMaxColIdx() {
    return maxColIdx;
  }

  /**
   * @param maxColIdx the maxColIdx to set
   */
  public void setMaxColIdx(int maxColIdx) {
    this.maxColIdx = maxColIdx;
  }

  /**
   * @return the fileName
   */
  public String getFileName() {
    return fileName;
  }

  /**
   * @param fileName the fileName to set
   */
  public void setFileName(String fileName) {
    this.fileName = fileName;
  }
}
