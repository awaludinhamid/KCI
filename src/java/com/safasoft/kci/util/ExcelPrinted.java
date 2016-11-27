package com.safasoft.kci.util;

import com.safasoft.kci.util.bean.CellContentBean;
import com.safasoft.kci.util.bean.CellPropBean;
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * File creator
 * Contain excel, PDF and text extention
 * @created Oct 2, 2016
 * @author awal
 */
public class ExcelPrinted {
  
  private final File file;
  private final List<List<CellContentBean>> titles;
  private final List<List<CellContentBean>> contents;
  private final List<List<Integer>> merges;
  private final Map<String,CellPropBean> templates;
  private final int maxColIdx;
  
  /**
   * Container with parameter only accepted
   * @param file
   * @param titles
   * @param contents
   * @param merges
   * @param templates
   * @param maxColIdx
   */
  public ExcelPrinted(
          File file,
          List<List<CellContentBean>> titles,
          List<List<CellContentBean>> contents,
          List<List<Integer>> merges,
          Map<String,CellPropBean> templates,
          int maxColIdx) {
    this.file = file;
    this.titles = titles;
    this.contents = contents;
    this.merges = merges;
    this.templates = templates;
    this.maxColIdx = maxColIdx;
  }
  
  /**
   * Created file by extention given: excel
   * @throws IOException
   */
  public void createFile() throws IOException {
    createExcel();
  }
  
  //Excel file creator
  private void createExcel() throws IOException {
    
    // excel object
    Workbook workbook = new HSSFWorkbook();//.xls extention
    
    // styling
    Map<String,CellStyle> styles = new HashMap<String,CellStyle>();
    for(Map.Entry<String,CellPropBean> cpbMap : templates.entrySet()) {
      CellStyle cellStyle = workbook.createCellStyle();
      Font font = workbook.createFont();
      CellPropBean cpb = cpbMap.getValue();
      font.setBoldweight(cpb.getFontWeight());
      font.setFontHeightInPoints(cpb.getFontHeight());
      if(cpb.getBackgroundColor() == IndexedColors.BLACK.getIndex())
        font.setColor(HSSFColor.WHITE.index);
      cellStyle.setFont(font);
      if(cpb.getBackgroundColor() == 0)
        cellStyle.setFillPattern(CellStyle.NO_FILL);
      else
        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
      cellStyle.setFillForegroundColor(cpb.getBackgroundColor());
      styles.put(cpbMap.getKey(), cellStyle);
    }  
    
    //object preparation
    int rowIdx = 0;
    int cellIdx;
    Sheet sheet = workbook.createSheet("sheet1");
    Row row;
    
    // add title
    for (List<CellContentBean> title : titles) {
      row = sheet.createRow(rowIdx++);
      cellIdx = 0;
      for(CellContentBean ccb : title) {
        Cell cell = row.createCell(cellIdx++);
        cell.setCellValue((String) ccb.getValue());        
        cell.setCellStyle(styles.get(ccb.getTemplate()));
      }
    }
    
    // add content
    for (List<CellContentBean> content : contents) {
      cellIdx = 0;
      row = sheet.createRow(rowIdx++);
      for(CellContentBean ccb : content) {
        Cell cell = row.createCell(cellIdx++);
        cell.setCellStyle(styles.get(ccb.getTemplate()));
        Object value = ccb.getValue();
        if(value instanceof Double) {
          cell.setCellValue((Double) value);
        } else if (value instanceof Integer) {
          cell.setCellValue((Integer) value);
        } else {
          cell.setCellValue((String) value);
        }
      }
    }
    
    // merge and auto size
    for(List<Integer> ints : merges) {
      sheet.addMergedRegion(new CellRangeAddress(ints.get(0),ints.get(1),ints.get(2),ints.get(3)));
    }
    for(int colIdx = 0; colIdx < maxColIdx; colIdx++)
      sheet.autoSizeColumn(colIdx);
    
    OutputStream outFile = new FileOutputStream(file);
    workbook.write(outFile);
    outFile.close();
  }
}