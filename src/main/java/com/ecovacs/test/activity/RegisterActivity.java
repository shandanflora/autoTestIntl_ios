package com.ecovacs.test.activity;

import com.ecovacs.test.common.Common;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ecosqa on 16/11/30.
 *
 */
public class RegisterActivity {
    private static RegisterActivity registerActivity = new RegisterActivity();
    private static Logger logger = LoggerFactory.getLogger(RegisterActivity.class);
    private IOSDriver driver = null;

    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[4]")
    private IOSElement btnRegister = null;
    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[1]")
    private IOSElement btnSelect = null;
    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATextField[2]")
    private IOSElement editEmail = null;
    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]")
    private IOSElement editPassword = null;
    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[2]")
    private IOSElement editRePassword = null;
    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[6]")
    private IOSElement textViewUserAgree = null;
    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[3]")
    private IOSElement staticTextEmail = null;

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
        this.driver = driver;
    }

    public boolean showRegisterActivity(){
        return Common.getInstance().showActivity(btnRegister);
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
            Common.getInstance().goBack(driver, 2);
            return false;
        }
        editEmail.sendKeys(strEmail);
        editPassword.sendKeys(strPass);
        editRePassword.sendKeys(strPass);
        logger.info("Filled all information of user");
        //hide keyboard
        staticTextEmail.click();
        //screen shot user agreement
        clickAgreement();
        Common.getInstance().screenShot("UserAgree_" + strCountry + ".png", driver);
        Common.getInstance().goBack(driver, 1);
        logger.info("Finished to screen shot user agreement!!!");
        btnRegister.click();
        return true;
    }



    private void clickAgreement(){
        textViewUserAgree.click();
        Common.getInstance().waitForSecond(2500);
    }
}
