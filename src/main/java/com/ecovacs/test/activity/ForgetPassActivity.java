package com.ecovacs.test.activity;

import com.ecovacs.test.common.Common;
import com.ecovacs.test.common.TranslateErrorReport;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by ecosqa on 16/12/7.
 * forget password activity
 */
public class ForgetPassActivity {
    private static ForgetPassActivity forgetPassActivity = null;
    private static Logger logger = LoggerFactory.getLogger(ForgetPassActivity.class);
    //private IOSDriver driver = null;

    private ForgetPassActivity(){

    }

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAStaticText[1]")
    private MobileElement titleForget = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]")
    private MobileElement back = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[1]")
    private MobileElement line1Country_Region = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[1]")
    private IOSElement eleCountry = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATextField[2]")
    private IOSElement editEmail = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[2]")
    private IOSElement btnSendEmail = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[3]")
    private IOSElement staticTextEmail = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[3]")
    private MobileElement line3Email = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[4]")
    private MobileElement textMessage = null;

    public static ForgetPassActivity getInstance(){
        if(forgetPassActivity == null){
            forgetPassActivity = new ForgetPassActivity();
        }
        return forgetPassActivity;
    }

    public void init(IOSDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        //this.driver = driver;
    }

    public boolean showActivity(){
        return Common.getInstance().showActivity(editEmail);
    }

    public void clickBack(){
        back.click();
    }

    public boolean sendEmail(String strCountry, String strEmail){
        eleCountry.click();
        if(!CountrySelectActivity.getInstance().selectCountry(strCountry)){
            logger.info("Select country failed!!!");
            return false;
        }
        editEmail.sendKeys(strEmail);
        //hide keyboard
        staticTextEmail.click();
        btnSendEmail.click();
        logger.info("Finished to send verify email!!!");
        return true;
    }

    private boolean staticUI(Map<String, String> tranMap){
        boolean btitleForget = titleForget.getText().equalsIgnoreCase(tranMap.get("forget_password"));
        if(!btitleForget){
            logger.error("forget password activity--titleBar--forget--App--" + titleForget.getText());
            logger.error("forget password activity--titleBar--forget--Excel--" + tranMap.get("forget_password"));
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "Forgot password", titleForget.getText(),
                    tranMap.get("forget_password"), "fail");
        }
        boolean bline1Country_Region = line1Country_Region.getText().equalsIgnoreCase(tranMap.get("guojiadiqu"));
        if(!bline1Country_Region){
            logger.error("forget password activity--line1--Country_Region--App--" + line1Country_Region.getText());
            logger.error("forget password activity--line1--Country_Region--Excel--" + tranMap.get("guojiadiqu"));
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "Forgot password", line1Country_Region.getText(),
                    tranMap.get("guojiadiqu"), "fail");
        }
        boolean bline3Email = line3Email.getText().equalsIgnoreCase(tranMap.get("email"));
        if(!bline3Email){
            logger.error("forget password activity--line3--Email--App--" + line3Email.getText());
            logger.error("forget password activity--line3--Email--Excel--" + tranMap.get("email"));
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "Forgot password", line3Email.getText(),
                    tranMap.get("email"), "fail");
        }
        boolean beditEmail = editEmail.getText().equalsIgnoreCase(tranMap.get("Email"));
        if(!beditEmail){
            logger.error("forget password activity--edit--Email--App--" + editEmail.getText());
            logger.error("forget password activity--edit--Email--Excel--" + tranMap.get("Email"));
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "Forgot password", editEmail.getText(),
                    tranMap.get("Email"), "fail");
        }
        boolean bbtnSendEmail = btnSendEmail.getText().equalsIgnoreCase(tranMap.get("verification_email"));
        if(!bbtnSendEmail){
            logger.error("forget password activity--btn--SendEmail--App--" + btnSendEmail.getText());
            logger.error("forget password activity--btn--SendEmail--Excel--" + tranMap.get("verification_email"));
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "Forgot password", btnSendEmail.getText(),
                    tranMap.get("verification_email"), "fail");
        }

        return btitleForget && bline1Country_Region && bline3Email
                && beditEmail && bbtnSendEmail;
    }
/*
    private boolean emptyEmail(Map<String, String> tranMap){
        btnSendEmail.click();
        boolean btextMessage = textMessage.getText().equalsIgnoreCase(tranMap.get("email_null"));
        if(!btextMessage){
            logger.error("forget password activity--btn--SendEmail--App--" + textMessage.getText());
            logger.error("forget password activity--btn--SendEmail--Excel--" + tranMap.get("email_null"));
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "Forgot password", textMessage.getText(),
                    tranMap.get("email_null"), "fail");
        }
        return btextMessage;
    }*/

    private boolean invalidEmail(Map<String, String> tranMap){
        editEmail.sendKeys("e");
        line3Email.click();
        boolean btextMessage = textMessage.getText().equalsIgnoreCase(tranMap.get("Email"));
        if(!btextMessage){
            logger.error("forget password activity--btn--SendEmail--App--" + textMessage.getText());
            logger.error("forget password activity--btn--SendEmail--Excel--" + tranMap.get("Email"));
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "Forgot password", textMessage.getText(),
                    tranMap.get("Email"), "fail");
        }
        return btextMessage;
    }

    public boolean translate(Map<String, String> tranMap){
        boolean bstaticUI = staticUI(tranMap);
        //boolean bemptyEmail = emptyEmail(tranMap);
        boolean binvalidEmail = invalidEmail(tranMap);
        //too fast show, can not catch element
        //boolean bnotExistEmailprompt = notExistEmailprompt(tranMap);
        return bstaticUI /*&& bemptyEmail*/ && binvalidEmail;
    }
}
