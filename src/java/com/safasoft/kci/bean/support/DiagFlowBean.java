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
 * @created Nov 21, 2016
 * @author awal
 */
@Entity
public class DiagFlowBean implements Serializable {

  @Id
  @Column(name="ID")
  private int id;
  @Column(name="PARENT_ID")
  private int parentId;
  @Column(name="NAME")
  private String name;
  @Column(name="LVL")
  private int lvl;

  /**
   * @return the id
   */
  public int getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the lvl
   */
  public int getLvl() {
    return lvl;
  }

  /**
   * @param lvl the lvl to set
   */
  public void setLvl(int lvl) {
    this.lvl = lvl;
  }

  /**
   * @return the parentId
   */
  public int getParentId() {
    return parentId;
  }

  /**
   * @param parentId the parentId to set
   */
  public void setParentId(int parentId) {
    this.parentId = parentId;
  }
  
}
