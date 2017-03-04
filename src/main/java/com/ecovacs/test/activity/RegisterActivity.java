package com.ecovacs.test.activity;

import com.ecovacs.test.common.Common;
import com.ecovacs.test.common.PropertyData;
import com.ecovacs.test.common.TranslateErrorReport;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by ecosqa on 16/11/30.
 *
 */
public class RegisterActivity {
    private static RegisterActivity registerActivity = new RegisterActivity();
    private static Logger logger = LoggerFactory.getLogger(RegisterActivity.class);
    //private IOSDriver driver = null;

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAStaticText[1]")
    private MobileElement titleBarTitle = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]")
    private MobileElement titleBarRight = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]")
    private MobileElement back = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[1]")
    private MobileElement line1Country_Region = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[3]")
    private MobileElement line3Email = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[4]")
    private MobileElement line5Password = null;
    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[4]")
    private IOSElement btnRegister = null;
    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[1]")
    private IOSElement btnSelect = null;
    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATextField[2]")
    private IOSElement editEmail = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATextField[2]/UIAButton[1]")
    private MobileElement btnClearEmail = null;
    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]")
    private IOSElement editPassword = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]/UIAButton[1]")
    private MobileElement btnClearPass = null;
    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[2]")
    private IOSElement editRePassword = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[2]/UIAButton[1]")
    private MobileElement btnClearPassRe = null;
    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[6]")
    private IOSElement textViewUserAgree = null;
    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[3]")
    private IOSElement staticTextEmail = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[6]")
    private MobileElement textViewAccept = null;


    private RegisterActivity(){

    }

    public static RegisterActivity getInstance(){
        if(registerActivity == null){
            registerActivity = new RegisterActivity();
        }
        return registerActivity;
    }

    public void init(IOSDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        //this.driver = driver;
    }

    public boolean showRegisterActivity(){
        return Common.getInstance().showActivity(btnRegister);
    }

    public void clickBack(){
        back.click();
    }

    public boolean fill_Screenshot_Click(String strCountry, String strEmail, String strPass){
        btnSelect.click();
        logger.info("Click 'Country/Region'!!!'");
        Common.getInstance().waitForSecond(1000);
        if(!CountrySelectActivity.getInstance().selectCountry(strCountry)){
            return false;
        }
        if(!RegisterActivity.getInstance().showRegisterActivity()){
            logger.error("Can not find country--" + strCountry);
            //Common.getInstance().goBack(driver, 2);
            return false;
        }
        editEmail.sendKeys(strEmail);
        //hide keyboard
        staticTextEmail.click();
        editPassword.sendKeys(strPass);
        //hide keyboard
        staticTextEmail.click();
        editRePassword.sendKeys(strPass);
        //hide keyboard
        staticTextEmail.click();
        logger.info("Filled all information of user");
        //hide keyboard
        staticTextEmail.click();
        //screen shot user agreement
        /*clickAgreement();
        Common.getInstance().screenShot("UserAgree_" + strCountry + ".png", driver);
        Common.getInstance().goBack(driver, 1);
        logger.info("Finished to screen shot user agreement!!!");*/
        btnRegister.click();
        return true;
    }

    private void clickAgreement(){
        textViewUserAgree.click();
        Common.getInstance().waitForSecond(2500);
    }

    private boolean staticUITranslation(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        boolean bTitle = titleBarTitle.getText().equalsIgnoreCase(tranMap.get("register"));
        if(!bTitle){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Register", titleBarTitle.getText(),
                    tranMap.get("register"), "fail");
        }
        boolean bRight = titleBarRight.getText().equalsIgnoreCase(tranMap.get("login"));
        if(!bRight){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Register", titleBarRight.getText(),
                    tranMap.get("login"), "fail");
        }
        boolean bline1Country_Region = line1Country_Region.getText().equalsIgnoreCase(tranMap.get("guojiadiqu"));
        if(!bline1Country_Region){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Register", line1Country_Region.getText(),
                    tranMap.get("guojiadiqu"), "fail");
        }
        boolean bline3Email = line3Email.getText().equalsIgnoreCase(tranMap.get("email"));
        if(!bline3Email){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Register", line3Email.getText(),
                    tranMap.get("email"), "fail");
        }
        boolean beditEmail = editEmail.getText().equalsIgnoreCase(tranMap.get("Email"));
        if(!beditEmail){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Register", editEmail.getText(),
                    tranMap.get("Email"), "fail");
        }
        boolean bline5Password = line5Password.getText().equalsIgnoreCase(tranMap.get("password"));
        if(!bline5Password){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Register", line5Password.getText(),
                    tranMap.get("password"), "fail");
        }
        boolean beditPassword = editPassword.getText().equalsIgnoreCase(tranMap.get("email_pass_8_20"));
        logger.info("**register**" + editPassword.getText());
        if(!beditPassword){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Register", editPassword.getText(),
                    tranMap.get("email_pass_8_20"), "fail");
        }
        boolean beditRePassword = editRePassword.getText().equalsIgnoreCase(tranMap.get("zai_ci_shu_ru_pass"));
        if(!beditRePassword){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Register", editRePassword.getText(),
                    tranMap.get("zai_ci_shu_ru_pass"), "fail");
        }
        boolean bbtnRegister = btnRegister.getText().equalsIgnoreCase(tranMap.get("register"));
        if(!bbtnRegister){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Register", editRePassword.getText(),
                    tranMap.get("register"), "fail");
        }
        /*boolean btextViewAccept = textViewAccept.getText().equalsIgnoreCase(tranMap.get("my_read"));
        if(!btextViewAccept){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "register", textViewAccept.getText(),
                    tranMap.get("my_read"), "fail");
        }
        boolean btextViewUserAgree = textViewUserAgree.getText().equalsIgnoreCase(tranMap.get("vip_register_http"));
        if(!btextViewUserAgree){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "register", textViewUserAgree.getText(),
                    tranMap.get("vip_register_http"), "fail");
        }*/

        return bTitle && bRight && bline1Country_Region && bline3Email
                && beditEmail && bline5Password && beditPassword &&
                beditRePassword && bbtnRegister /*&& btextViewAccept &&
                btextViewUserAgree*/;
    }

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[5]")
    private MobileElement textViewMessage = null;

    private boolean emptyEmail(Map<String, String> tranMap){
        editEmail.click();
        line3Email.click();
        boolean btextViewMessage = textViewMessage.getText().equalsIgnoreCase(tranMap.get("Email"));
        if(!btextViewMessage){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "Register", textViewMessage.getText(),
                    tranMap.get("Email"), "fail");
        }
        return btextViewMessage;
    }

    private boolean invalidEmail(Map<String, String> tranMap){
        editEmail.sendKeys("e");
        line3Email.click();
        boolean btextViewMessage = textViewMessage.getText().equalsIgnoreCase(tranMap.get("Email"));
        if(!btextViewMessage){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "Register", textViewMessage.getText(),
                    tranMap.get("Email"), "fail");
        }
        return btextViewMessage;
    }

    private boolean emptyPassword(Map<String, String> tranMap){
        editEmail.click();
        btnClearEmail.click();
        editEmail.sendKeys(PropertyData.getProperty("hotmail_email"));
        line3Email.click();
        editPassword.click();
        line5Password.click();
        boolean btextViewMessage = textViewMessage.getText().equalsIgnoreCase(tranMap.get("pass_null"));
        if(!btextViewMessage){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "Register", textViewMessage.getText(),
                    tranMap.get("pass_null"), "fail");
        }
        return btextViewMessage;
    }

    private boolean invalidPassword(Map<String, String> tranMap){
        editPassword.sendKeys("1");
        line5Password.click();
        boolean btextViewMessage = textViewMessage.getText().equalsIgnoreCase(tranMap.get("pass_geShi"));
        if(!btextViewMessage){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "Register", textViewMessage.getText(),
                    tranMap.get("pass_geShi"), "fail");
        }
        return btextViewMessage;
    }

    private boolean emptyRePassword(Map<String, String> tranMap){
        editPassword.click();
        btnClearPass.click();
        editPassword.sendKeys(PropertyData.getProperty("login_pass"));
        line5Password.click();
        btnRegister.click();
        boolean btextViewMessage = textViewMessage.getText().equalsIgnoreCase(tranMap.get("zai_ci_shu_ru_pass"));
        if(!btextViewMessage){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "Register", textViewMessage.getText(),
                    tranMap.get("zai_ci_shu_ru_pass"), "fail");
        }
        return btextViewMessage;
    }

    private boolean invalidRePassword(Map<String, String> tranMap){
        editRePassword.sendKeys("12");
        line5Password.click();
        boolean btextViewMessage = textViewMessage.getText().equalsIgnoreCase(tranMap.get("pass_geShi"));
        if(!btextViewMessage){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "Register", textViewMessage.getText(),
                    tranMap.get("pass_geShi"), "fail");
        }
        return btextViewMessage;
    }

    public boolean translate(Map<String, String> tranMap){
        //**must do case in order
        boolean bStaticUI = staticUITranslation(tranMap);
        boolean bEmptyEmail = emptyEmail(tranMap);
        boolean bInvalidEmail = invalidEmail(tranMap);
        boolean bbemptyPassword = emptyPassword(tranMap);
        boolean binvalidPassword = invalidPassword(tranMap);
        boolean bemptyRePassword = emptyRePassword(tranMap);
        boolean binvalidRePassword = invalidRePassword(tranMap);
        return bStaticUI && bEmptyEmail && bInvalidEmail &&
                bbemptyPassword && binvalidPassword &&
                bemptyRePassword && binvalidRePassword;
    }
}
