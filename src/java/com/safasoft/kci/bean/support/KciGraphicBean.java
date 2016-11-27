/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.bean.support;

import java.util.List;

/**
 * @created Sep 4, 2016
 * @author awal
 */
public class KciGraphicBean {

  private List<KciPeriodeBean> kciFifgroup;
  private List<KciPeriodeBean> kciArea;
  private List<KciPeriodeBean> kciBranch;

  /**
   * @return the kciFifgroup
   */
  public List<KciPeriodeBean> getKciFifgroup() {
    return kciFifgroup;
  }

  /**
   * @param kciFifgroup the kciFifgroup to set
   */
  public void setKciFifgroup(List<KciPeriodeBean> kciFifgroup) {
    this.kciFifgroup = kciFifgroup;
  }

  /**
   * @return the kciArea
   */
  public List<KciPeriodeBean> getKciArea() {
    return kciArea;
  }

  /**
   * @param kciArea the kciArea to set
   */
  public void setKciArea(List<KciPeriodeBean> kciArea) {
    this.kciArea = kciArea;
  }

  /**
   * @return the kciBranch
   */
  public List<KciPeriodeBean> getKciBranch() {
    return kciBranch;
  }

  /**
   * @param kciBranch the kciBranch to set
   */
  public void setKciBranch(List<KciPeriodeBean> kciBranch) {
    this.kciBranch = kciBranch;
  }
}
