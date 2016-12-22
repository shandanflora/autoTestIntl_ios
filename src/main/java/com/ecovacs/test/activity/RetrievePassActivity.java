package com.ecovacs.test.activity;

import com.ecovacs.test.common.Common;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by ecosqa on 16/12/9.
 * retrieve activity
 */
public class RetrievePassActivity {
    private static RetrievePassActivity retrievePassActivity = null;

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]\n")
    private IOSElement btnLogin = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATextView[1]")
    private IOSElement textViewForgetInfo = null;

    private RetrievePassActivity(){

    }

    public static RetrievePassActivity getInstance(){
        if(retrievePassActivity == null){
            retrievePassActivity = new RetrievePassActivity();
        }
        return retrievePassActivity;
    }

    public void init(IOSDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void clickLogin(){
        btnLogin.click();
    }


    public boolean showRetrieveConfirmActivity(){
        return Common.getInstance().showActivity(textViewForgetInfo);
    }


}
