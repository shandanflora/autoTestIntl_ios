package com.ecovacs.test.common;

/**
 * Created by ecosqa on 16/12/19.
 */
public class ExcelRow {
    /*
     row.createCell(0).setCellValue("NO.");
        row.createCell(1).setCellValue("Type");
        row.createCell(2).setCellValue("Country");
        row.createCell(3).setCellValue("Result");
        row.createCell(4).setCellValue("UserAgreement");
        row.createCell(5).setCellValue("Register");
        row.createCell(6).setCellValue("ForgetPassword");
     */
    private static ExcelRow excelRow = null;
    private int ordinal;
    private String type;
    private String country;
    private String result;
    private String useragreePath;
    private String resPath;
    private String forgetPassPath;

    /*private ExcelRow(){

    }*/

    public static ExcelRow getInstance(){
        if (excelRow == null){
            excelRow = new ExcelRow();
        }
        return excelRow;
    }

    public int getOrdinal(){
        return ordinal;
    }

    public String getType(){
        return type;
    }

    public String getCountry(){
        return country;
    }

    public String getResult(){
        return result;
    }

    public String getUseragreePath(){
        return useragreePath;
    }

    public String getResPath(){
        return resPath;
    }

    public String getForgetPassPath(){
        return forgetPassPath;
    }

    public void setOrdinal(int ordinal){
        this.ordinal = ordinal;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setCountry(String country){
        this.country = country;
    }

    public void setResult(String result){
        this.result = result;
    }

    public void setUseragreePath(String useragreePath){
        this.useragreePath = useragreePath;
    }

    public void setResPath(String resPath){
        this.resPath = resPath;
    }

    public void setForgetPassPath(String forgetPassPath){
        this.forgetPassPath = forgetPassPath;
    }




}
