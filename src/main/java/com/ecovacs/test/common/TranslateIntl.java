package com.ecovacs.test.common;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by ecosqa on 17/1/17.
 *
 */
public class TranslateIntl {
    private static TranslateIntl translateIntl = null;
    private static Logger logger = LoggerFactory.getLogger(TranslateIntl.class);

    private TranslateIntl(){

    }

    public static TranslateIntl getInstance(){
        if(translateIntl == null){
            translateIntl = new TranslateIntl();
        }
        return translateIntl;
    }

    private String getCurPath(){
        File directory = new File("");//set current path
        String strPath = "";
        try{
            logger.info(directory.getCanonicalPath());//get path
            strPath = directory.getCanonicalPath() + "/";
        }catch(IOException e){
            e.printStackTrace();
        }
        return strPath;
    }

    private String getCellValue(Cell cell){
        String strResult;
        CellType type = cell.getCellTypeEnum();
        if(type == CellType.BLANK){
            strResult = "";
        }else if(type == CellType.BOOLEAN){
            strResult = String.valueOf(cell.getBooleanCellValue());
        }else if(type == CellType.ERROR){
            strResult = null;
        }else if(type == CellType.FORMULA){
            strResult = cell.getCellFormula();
        }else if(type == CellType.NUMERIC){
            if (DateUtil.isCellDateFormatted(cell)) {
                Date theDate = cell.getDateCellValue();
                SimpleDateFormat sdf = new SimpleDateFormat();
                strResult = sdf.format(theDate);
            } else {
                strResult = String.valueOf(cell.getNumericCellValue());
            }
        }else if(type == CellType.STRING){
            strResult = cell.getStringCellValue();
        }else {
            strResult = null;
        }
        return strResult;
    }
/*
    private boolean findInList(List<String> list, String strFind){
        boolean bResult = false;
        if(0 == list.size()){
            return false;
        }
        for(String str: list){
            if (str.equals(strFind)){
                bResult = true;
                break;
            }
        }
        return bResult;
    }

    public void repeatRow(){
        int i = 0,j = 0;
        XSSFWorkbook workBook;
        InputStream stream;
        List<String> repeatStrList = new ArrayList();
        try {
            stream = new FileInputStream(getCurPath() + "Translate.xlsx");
            workBook = new XSSFWorkbook(stream);
            XSSFSheet sheet = workBook.getSheetAt(0);
            int rowNum = sheet.getLastRowNum();
            for(i = 1; i < rowNum; i++){
                int colNum = sheet.getRow(i).getLastCellNum();
                logger.info(String.valueOf(i) + "*************************************************");
                if(findInList(repeatStrList, getCellValue(sheet.getRow(i).getCell(0)))){
                    continue;
                }
                for (int k = i + 1; k < rowNum; k++){//row loop
                    if(!getCellValue(sheet.getRow(i).getCell(0))//column 0 not match
                            .equals(getCellValue(sheet.getRow(k).getCell(0)))){
                        continue;
                    }
                    for (j = 0; j < colNum; j++){//column loop
                        if(j == 1 || j == 2){
                            continue;
                        }
                        if (getCellValue(sheet.getRow(i).getCell(j))
                                .equals(getCellValue(sheet.getRow(k).getCell(j)))){
                            if(j == 0){
                                String strCell = sheet.getRow(i).getCell(j).getStringCellValue();
                                logger.info("The cell(0)--" + strCell);
                                repeatStrList.add(strCell);
                            }
                            logger.info("The same cell--(" + (i + 1) + "," + (j + 1) + ")" + "(" + (k + 1) + "," + (j + 1) + ")");
                        }
                    }
                }
            }
            logger.info("Check xlsx file completed!!!");
            workBook.close();
            stream.close();
        }catch (Exception e){
            e.printStackTrace();
            logger.info("Fail cell--(" + i + "," + j + ")" + "(" + (i + 1) + "," + j + ")");
            System.out.print("Check xlsx file failed!!!");
        }
    }*/

    private int getColNum(Sheet sheet, String strLanguage){
        int iColSize = sheet.getRow(0).getLastCellNum();
        int iColIndex = -1;

        for(int i = 0; i < iColSize; i++){
            if(getCellValue(sheet.getRow(0).getCell(i)).equals(strLanguage)){
                iColIndex = i;
                break;
            }
        }
        return iColIndex;
    }

    public Map<String, String> readExcel(String strFile, String strColName){
        Map<String, String> tranMap = new HashMap<String, String>();
        XSSFWorkbook workBook;
        try{
            InputStream inputstream = this.getClass().getClassLoader().getResourceAsStream(strFile);
            workBook = new XSSFWorkbook(inputstream);
            XSSFSheet sheet = workBook.getSheetAt(0);

            int iRowSize = sheet.getLastRowNum();
            int iColNum = getColNum(sheet, strColName);
            if(iColNum == -1){
                logger.error("Can not get the language, Column--" + strColName);
                return tranMap;
            }

            tranMap.put("language", strColName);

            for(int i = 1; i < iRowSize; i++){//headline(i = 0)
                if(0 != getCellValue(sheet.getRow(i).getCell(0)).length()){
                    tranMap.put(getCellValue(sheet.getRow(i).getCell(0)), getCellValue(sheet.getRow(i).getCell(iColNum)));
                }
            }
            workBook.close();
        }catch (IOException e){
            e.printStackTrace();
            logger.error("*******resource********" + this.getClass().getClassLoader().getResource("/").getPath());
        }
        return tranMap;
    }
}
