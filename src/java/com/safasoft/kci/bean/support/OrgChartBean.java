/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.bean.support;

import com.safasoft.kci.bean.AudTrnTracking;
import java.util.List;

/**
 * @created Nov 1, 2016
 * @author awal
 */
public class OrgChartBean {

  private String branchInfo;
  private AudTrnTracking branchHead;
  private List<AudTrnTracking> deptHead;
  private List<AudTrnTracking> posHead;

  /**
   * @return the branchHead
   */
  public AudTrnTracking getBranchHead() {
    return branchHead;
  }

  /**
   * @param branchHead the branchHead to set
   */
  public void setBranchHead(AudTrnTracking branchHead) {
    this.branchHead = branchHead;
  }

  /**
   * @return the deptHead
   */
  public List<AudTrnTracking> getDeptHead() {
    return deptHead;
  }

  /**
   * @param deptHead the deptHead to set
   */
  public void setDeptHead(List<AudTrnTracking> deptHead) {
    this.deptHead = deptHead;
  }

  /**
   * @return the posHead
   */
  public List<AudTrnTracking> getPosHead() {
    return posHead;
  }

  /**
   * @param posHead the posHead to set
   */
  public void setPosHead(List<AudTrnTracking> posHead) {
    this.posHead = posHead;
  }

  /**
   * @return the branchInfo
   */
  public String getBranchInfo() {
    return branchInfo;
  }

  /**
   * @param branchInfo the branchInfo to set
   */
  public void setBranchInfo(String branchInfo) {
    this.branchInfo = branchInfo;
  }
}
