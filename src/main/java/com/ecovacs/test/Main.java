package com.ecovacs.test;

import com.ecovacs.test.common.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by ecosqa on 16/12/12.
 * main
 */
public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String args[]){
        List<String> list = JsonParse.getJsonParse().readDataFromJson("country.json", "countries");
        HandleIntl.getInstance().initAppium();
        if (!HandleIntl.getInstance().enterWelcomeActivity()){
            return;
        }
        int iLoop = 1;
        ExcelManage.getInstance().init();
        for(String strCountry:list) {
            //logger.info("***********Ready register country-- " + strCountry + "***********");
            ExcelRow row = new ExcelRow();
            row.setOrdinal(iLoop);
            row.setType("Register");
            row.setCountry(strCountry);
            if(!HandleIntl.getInstance().registerAndLogin(strCountry,
                    PropertyData.getProperty("email_type"),
                    PropertyData.getProperty("gmail_email"),
                    PropertyData.getProperty("register_pass"))){
                row.setResult("Fail");
                ExcelManage.getInstance().writeRow(iLoop, row, false, Common.getInstance().getFailType());
                logger.error("***********Register country--" + strCountry + " failed!!!***********");
                iLoop++;
                continue;
            }
            row.setResult("Pass");
            ExcelManage.getInstance().writeRow(iLoop, row, true, Common.getInstance().getFailType());
            logger.info("***********Register country -" + strCountry + " successfully!!!***********");
            logger.info("***********Ready to Forget password country-" + strCountry + "***********");
            row.setType("ForgetPassword");
            if(!HandleIntl.getInstance().forgetPassword(strCountry,
                    PropertyData.getProperty("email_type"),
                    PropertyData.getProperty("gmail_email"),
                    PropertyData.getProperty("login_pass"))){
                row.setResult("Fail");
                ExcelManage.getInstance().writeRow(iLoop, row, false, Common.getInstance().getFailType());
                logger.error("***********Forget password country--" + strCountry + " failed!!!***********");
                iLoop++;
                continue;
            }
            row.setResult("Pass");
            ExcelManage.getInstance().writeRow(iLoop, row, true, Common.getInstance().getFailType());
            logger.info("***********Forget password country -" + strCountry + " successfully!!!***********");
            iLoop++;
        }
        ExcelManage.getInstance().writeColServer();
    }
}
