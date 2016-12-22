package com.ecovacs.test.activity;

import com.ecovacs.test.common.Common;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;


/**
 * Created by ecosqa on 16/12/7.
 * login activity
 */
public class LoginActivity {
    private static LoginActivity loginActivity = null;
    //private static Logger logger = LoggerFactory.getLogger(LoginActivity.class);

    private LoginActivity(){}

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[3]")
    private IOSElement eleCountry = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATextField[3]")
    private IOSElement editEmail = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]")
    private IOSElement editPass = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[6]")
    private IOSElement btnLogin = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[7]")
    private IOSElement textViewForget = null;

    public static LoginActivity getInstance(){
        if(loginActivity == null){
            loginActivity = new LoginActivity();
        }
        return loginActivity;
    }

    public void init(IOSDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void clickForgetPass(){
        textViewForget.click();
    }

    public boolean showLoginActivity(){
        return Common.getInstance().showActivity(btnLogin);
    }

    public void clickCountry(){
        eleCountry.click();
    }

    public void fillInfoAndClick(String strEmail, String strPass){
        editEmail.sendKeys(strEmail);
        editPass.sendKeys(strPass);
        btnLogin.click();
    }



}
