package com.ecovacs.test.common;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by ecosqa on 17/2/10.
 *
 */
public class TranslateErrorReport {
    private static TranslateErrorReport translateErrorReport = null;

    private TranslateErrorReport(){

    }

    public static TranslateErrorReport getInstance(){
        if(translateErrorReport == null){
            translateErrorReport = new TranslateErrorReport();
        }
        return translateErrorReport;
    }

    public void init(List<String> sheetList){
        XSSFWorkbook wb = new XSSFWorkbook();
        for (String strSheet:sheetList){
            Sheet sheet = wb.createSheet(strSheet);
            sheet.setColumnWidth((short) 0, (short) 1000);
            sheet.setColumnWidth((short) 1, (short) 4500);
            sheet.setColumnWidth((short) 2, (short) 12000);
            sheet.setColumnWidth((short) 3, (short) 12000);
            sheet.setColumnWidth((short) 4, (short) 2500);

            // Row/Cell from 0
            Row row = sheet.createRow((short) 0);
            // Or do it on one line.
            row.createCell(0).setCellValue("NO.");
            row.createCell(1).setCellValue("Activity");
            row.createCell(2).setCellValue("AppShow");
            row.createCell(3).setCellValue("Translation");
            row.createCell(4).setCellValue("Result");
        }
        //save
        ExcelManage.getInstance().saveExcel(wb, "TranslateErrorReport.xlsx");
        try {
            wb.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void insetNewLine(String strSheetName, String strActivity, String strApp, String strTrans, String strResult){
        try {
            InputStream stream = new FileInputStream(ExcelManage.getInstance().getCurPath(("/report/") + "TranslateErrorReport.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(stream);

            XSSFCellStyle style = workbook.createCellStyle();
            /*style.setFillForegroundColor(HSSFColor.RED.index);
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);*/

            Sheet sheet = workbook.getSheet(strSheetName);
            Row row = sheet.createRow(sheet.getLastRowNum() + 1);

            row.createCell(0).setCellValue(sheet.getLastRowNum());
            row.createCell(1).setCellValue(strActivity);
            row.createCell(2).setCellValue(strApp);
            style.setWrapText(true);
            row.getCell(2).setCellStyle(style);
            row.createCell(3).setCellValue(strTrans);
            style.setWrapText(true);
            row.getCell(3).setCellStyle(style);
            row.createCell(4).setCellValue(strResult);
            //row.getCell(4).setCellStyle(style);
            ExcelManage.getInstance().saveExcel(workbook, "TranslateErrorReport.xlsx");
            workbook.close();
            stream.close();

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
