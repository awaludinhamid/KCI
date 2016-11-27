/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safasoft.kci.controller.support;

import com.safasoft.kci.bean.support.DiagFlowBean;
import com.safasoft.kci.bean.support.DiagFlowHieBean;
import com.safasoft.kci.service.GenericService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;

/**
 * @created Nov 21, 2016
 * @author awal
 */
public class DiagFlowProc {
  
  public DiagFlowHieBean getDiagFlowHie(String parameterId, GenericService genServ) {
    //
    List<DiagFlowBean> dfbList = genServ.getDiagFlow(parameterId);
    List<DiagFlowHieBean> dfhbList = new ArrayList<DiagFlowHieBean>();
    //
    for(DiagFlowBean dfb : dfbList) {
      DiagFlowHieBean dfhb = new DiagFlowHieBean();
      BeanUtils.copyProperties(dfb, dfhb);
      List<Integer> delList = new ArrayList<Integer>();
      List<DiagFlowHieBean> dfhbTempList = new ArrayList<DiagFlowHieBean>();
      //
      for(int idxDfhb = 0; idxDfhb < dfhbList.size(); idxDfhb++) {
        DiagFlowHieBean dfhbTemp = dfhbList.get(idxDfhb);
        if(dfb.getId() == dfhbTemp.getParentId()) {
          dfhbTempList.add(dfhbTemp);
          delList.add(idxDfhb);
        }
      }
      //
      for(int idxDel = delList.size() - 1; idxDel > -1; idxDel--) {
        dfhbList.remove(delList.get(idxDel).intValue());
      }
      //
      if(dfhbTempList.size() > 0) {
        dfhb.setChildren(dfhbTempList);
      }
      dfhbList.add(dfhb);
    }
    //
    return dfhbList.get(dfhbList.size() - 1);
  }
}
