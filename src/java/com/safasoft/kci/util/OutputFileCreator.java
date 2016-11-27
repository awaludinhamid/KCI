package com.safasoft.kci.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * File creator
 * Contain excel, PDF and text extention
 * @created Oct 20, 2015
 * @author awal
 */
public class OutputFileCreator {
  
  private final File file;
  private final List<String> titles;
  private final List<String> headers;
  private final List<List<String>> content;
  private final String fileExt;
  private final int maxTextWidth = 40;
  
  /**
   * Container with parameter only accepted
   * @param file
   * @param titles
   * @param headers
   * @param content
   * @param fileExt
   */
  public OutputFileCreator(File file, List<String> titles, List<String> headers, List<List<String>> content, String fileExt) {
    this.file = file;
    this.titles = titles;
    this.headers = headers;
    this.content = content;
    this.fileExt = fileExt;
  }
  
  /**
   * Created file by extention given: excel or text
   * @throws IOException
   */
  public void createFile() throws IOException {
    if(fileExt.equals("xls")) {
      createExcel();
    } else if(fileExt.equals("txt")) {
      createText();
    }    
  }
  
  //Excel file creator
  private void createExcel() throws IOException {
    //object preparation
    int rowIdx = 0;
    int cellIdx = 0;
    Workbook workbook = new HSSFWorkbook();//.xls extention
    Sheet sheet = workbook.createSheet("sheet1");
    Row row;
    Cell cell;
    CellStyle cellStyle = workbook.createCellStyle();
    Font font = workbook.createFont();
    font.setBoldweight(Font.BOLDWEIGHT_BOLD);
    cellStyle.setFont(font); 
    // add title
    for (String title : titles) {
      row = sheet.createRow(rowIdx++);
      cell = row.createCell(0);
      cell.setCellValue(title);
      cell.setCellStyle(cellStyle);
    }
    // add header
    rowIdx++;
    row = sheet.createRow(rowIdx++);
    for(String header : headers) {
      cell = row.createCell(cellIdx++);
      cell.setCellStyle(cellStyle);
      cell.setCellValue(header);
    }
    // add detail
    for (List<String> strs : content) {
      cellIdx = 0;
      row = sheet.createRow(rowIdx++);
      for(String str : strs) {
        cell = row.createCell(cellIdx++);
        cell.setCellValue(str);
      }
    }
    for(int colIdx = 1; colIdx < cellIdx; colIdx++)
      sheet.autoSizeColumn(colIdx);
    
    OutputStream outFile = new FileOutputStream(file);
    workbook.write(outFile);
    outFile.close();
  }
  
  //Text file creator
  private void createText() throws IOException {
    //object preparation
    FileWriter fileWriter = new FileWriter(file);
    String newline = "\r\n";//return key and newline
    String delimiter = "|";
    // set column width
    List<Integer> colWidthList = new ArrayList<Integer>();
    for (String hdr : headers)
      colWidthList.add(hdr.length());
    for(List<String> strs : content) {
      int strIdx = 0;
      for(String str : strs) {
        if(str.length() > maxTextWidth)
          colWidthList.set(strIdx, maxTextWidth);
        else if(str.length() > colWidthList.get(strIdx))
          colWidthList.set(strIdx, str.length());
        strIdx++;
      }
    }
    // add title
    for (String title : titles) {
      fileWriter.append(title).append(newline);
    }
    fileWriter.append(newline);
    // add header
    fileWriter.append(delimiter);
    int hdrIdx = 0;
    for(String header : headers) {
      fileWriter.append(getStringWithSpace(header,colWidthList.get(hdrIdx))).append(delimiter);
      hdrIdx++;
    }
    fileWriter.append(newline);
    fileWriter.append(newline);
    // add detail
    for(List<String> strs : content) {
      fileWriter.append(delimiter);
      int strIdx = 0;
      //add space if text length lower than column width
      for(String str : strs) {
        fileWriter.append(getStringWithSpace(str,colWidthList.get(strIdx))).append(delimiter);
        strIdx++;
      }
      fileWriter.append(newline);
    }
    fileWriter.close();
  }
  
  //text with space to equal column width
  private String getStringWithSpace(String str, int length) {
    if(str == null || str.equals("null")) {
      str = "";
    }
    //cut text if it is too long (more than 40)
    if(str.length() > length)
      str = str.substring(0,length);
    int spaceLen = length - str.length();
    StringBuilder sb = new StringBuilder(str);
    String space = " ";
    for(int idxSpace = 0; idxSpace < spaceLen; idxSpace++)
      sb.append(space);
    return sb.toString();
  }
}