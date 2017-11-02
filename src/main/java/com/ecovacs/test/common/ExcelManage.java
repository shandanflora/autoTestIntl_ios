package com.ecovacs.test.common;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;*/

import java.io.*;
import java.util.List;


/**
 * Created by ecosqa on 16/12/17.
 * excel opreation
 */
public class ExcelManage {

    private static ExcelManage excelManage = null;
    //private static Logger logger = LoggerFactory.getLogger(ExcelManage.class);

    private ExcelManage(){

    }

    public static ExcelManage getInstance(){
        if(excelManage == null){
            excelManage = new ExcelManage();
        }
        return excelManage;
    }

    public void init(){
        XSSFWorkbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("new sheet");
        sheet.setColumnWidth((short) 0, (short) 1000);
        sheet.setColumnWidth((short) 1, (short) 4500);
        sheet.setColumnWidth((short) 2, (short) 6000);
        sheet.setColumnWidth((short) 3, (short) 3000);
        sheet.setColumnWidth((short) 4, (short) 4000);
        sheet.setColumnWidth((short) 5, (short) 4000);
        sheet.setColumnWidth((short) 6, (short) 4000);
        sheet.setColumnWidth((short) 7, (short) 3000);
        // Row/Cell from 0
        Row row = sheet.createRow((short) 0);
        // Or do it on one line.
        row.createCell(0).setCellValue("NO.");
        row.createCell(1).setCellValue("Type");
        row.createCell(2).setCellValue("Country");
        row.createCell(3).setCellValue("Result");
        row.createCell(4).setCellValue("UserAgreement");
        row.createCell(5).setCellValue("Register");
        row.createCell(6).setCellValue("ForgetPassword");
        row.createCell(7).setCellValue("Server");

        //save
        saveExcel(wb, "TestReport.xlsx");
        try {
            wb.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    void saveExcel(Workbook wb, String strExcel){
        File folder = new File(Common.getInstance().getCurPath("/report/"));
        if(!folder.exists() && !folder.isDirectory()){
            if(!folder.mkdir()){
                return;
            }
        }else {
            Common.getInstance().delAllFile(Common.getInstance().getCurPath("/report/"));
        }
        try {
            FileOutputStream fileOut = new FileOutputStream(Common.getInstance().getCurPath("/report/") + strExcel);
            wb.write(fileOut);
            fileOut.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
/*
    public void writeColServer(){
        try {
            List<String> listOthers = JsonParse.getJsonParse().readDataFromJson("serverCountry.json", "others");
            InputStream stream = new FileInputStream(Common.getInstance().getCurPath("/report/") + "TestReport.xlsx");
            XSSFWorkbook workBook = new XSSFWorkbook(stream);

            CellStyle style = workBook.createCellStyle();
            style.setFillForegroundColor(HSSFColor.LEMON_CHIFFON.index);
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            // first sheet
            XSSFSheet sheet = workBook.getSheetAt(0);
            int iRows = sheet.getLastRowNum();

            for(int i = 1; i <= iRows; i++){
                Row row = sheet.getRow(i);
                String strValue = row.getCell(2).getStringCellValue();
                for(String strCountry: listOthers){
                    if (strValue.equals(strCountry)){
                        row.createCell(7).setCellStyle(style);
                    }
                }
            }
            //save excel
            saveExcel(workBook, "TestReport.xlsx");
            workBook.close();
            stream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
*/
    public void writeRow(int iRow, ExcelRow excelRow, boolean bPass, Common.REGISTER_RETURN_TYPE type){

        try {
            InputStream stream = new FileInputStream(Common.getInstance().getCurPath("/report/") + "TestReport.xlsx");
            XSSFWorkbook workBook = new XSSFWorkbook(stream);
            CellStyle style = workBook.createCellStyle();
            //style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
            if(bPass){
                style.setFillForegroundColor(HSSFColor.SEA_GREEN.index);
            }else {
                style.setFillForegroundColor(HSSFColor.RED.index);
            }
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            // first sheet
            Sheet sheet = workBook.getSheetAt(0);
            Row row = sheet.createRow((short) iRow);
            row.createCell(0).setCellValue(excelRow.getOrdinal());
            row.createCell(1).setCellValue(excelRow.getType());
            row.createCell(2).setCellValue(excelRow.getCountry());
            row.createCell(3).setCellValue(excelRow.getResult());
            row.getCell(3).setCellStyle(style);

            //set link style
            CellStyle hlink_style = workBook.createCellStyle();
            Font hlink_font = workBook.createFont();
            hlink_font.setUnderline(Font.U_SINGLE);
            hlink_font.setColor(IndexedColors.BLUE.getIndex());
            hlink_style.setFont(hlink_font);
            //set link address
            XSSFCreationHelper helper = new XSSFCreationHelper(workBook);
            //replace space of string
            String strCountry = excelRow.getCountry();
            if(excelRow.getCountry().contains(" ")){
                System.out.println(strCountry);
                strCountry = strCountry.replaceAll(" ", "_");
                System.out.println(strCountry);
            }

            if(type != Common.REGISTER_RETURN_TYPE.ALREADY_REGISTER && excelRow.getType().equals("Register")){
                XSSFHyperlink fileLink4 = new XSSFHyperlink(helper.createHyperlink(HyperlinkType.FILE));
                XSSFHyperlink fileLink5 = new XSSFHyperlink(helper.createHyperlink(HyperlinkType.FILE));
                //user agreement.png
                row.createCell(4).setCellValue("click for view");
                fileLink4.setAddress("screenShots/UserAgree_" + excelRow.getCountry() + ".png");
                row.getCell(4).setHyperlink(fileLink4);
                row.getCell(4).setCellStyle(hlink_style);
                //register.png
                row.createCell(5).setCellValue("click for view");
                fileLink5.setAddress("screenShots/Register-" + excelRow.getCountry() + ".png");
                row.getCell(5).setHyperlink(fileLink5);
                row.getCell(5).setCellStyle(hlink_style);
            }

            if(type != Common.REGISTER_RETURN_TYPE.NOT_REGISTER && excelRow.getType().equals("ForgetPassword")){
                XSSFHyperlink fileLink6 = new XSSFHyperlink(helper.createHyperlink(HyperlinkType.FILE));
                //reset password.png
                row.createCell(6).setCellValue("click for view");
                fileLink6.setAddress("screenShots/doResetPass-" + excelRow.getCountry() + ".png");
                row.getCell(6).setHyperlink(fileLink6);
                row.getCell(6).setCellStyle(hlink_style);
            }
            List<String> listOthers = JsonParse.getJsonParse().readDataFromJson("serverCountry.json", "notOthers");
            CellStyle style7 = workBook.createCellStyle();
            style7.setFillForegroundColor(HSSFColor.LEMON_CHIFFON.index);
            style7.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            if(!listOthers.contains(excelRow.getCountry())){
                row.createCell(7).setCellStyle(style7);
            }
            //save excel
            saveExcel(workBook, "TestReport.xlsx");
            workBook.close();
            stream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
