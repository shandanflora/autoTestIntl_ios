package com.ecovacs.test.activity;

import com.ecovacs.test.common.Common;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ecosqa on 16/12/7.
 * forget password activity
 */
public class ForgetPassActivity {
    private static ForgetPassActivity forgetPassActivity = null;
    private static Logger logger = LoggerFactory.getLogger(ForgetPassActivity.class);
    private IOSDriver driver = null;

    private ForgetPassActivity(){

    }

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[1]")
    private IOSElement eleCountry = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATextField[2]")
    private IOSElement editEmail = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[2]")
    private IOSElement btnSendEmail = null;

    public static ForgetPassActivity getInstance(){
        if(forgetPassActivity == null){
            forgetPassActivity = new ForgetPassActivity();
        }
        return forgetPassActivity;
    }

    public void init(IOSDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.driver = driver;
    }

    public boolean showActivity(){
        return Common.getInstance().showActivity(btnSendEmail);
    }

    public boolean sendEmail(String strCountry, String strEmail){
        eleCountry.click();
        if(!CountrySelectActivity.getInstance().selectCountry(strCountry)){
            logger.info("Select country failed!!!");
            return false;
        }
        editEmail.sendKeys(strEmail);
        Common.getInstance().goBack(driver, 1);
        btnSendEmail.click();
        logger.info("Finished to send verify email!!!");
        return true;
    }
}
