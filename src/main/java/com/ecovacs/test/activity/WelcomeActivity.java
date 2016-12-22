package com.ecovacs.test.activity;

import com.ecovacs.test.common.Common;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by ecosqa on 16/11/30.
 * welcome activity
 */
public class WelcomeActivity {
    private static WelcomeActivity welcomeActivity = null;
    /*private static Logger logger = LoggerFactory.getLogger(WelcomeActivity.class);*/

    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[1]")
    private IOSElement btnRegister = null;
    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[2]")
    private IOSElement btnLogin = null;

    private WelcomeActivity(){

    }

    public static WelcomeActivity getInstance(){
        if(welcomeActivity == null){
            welcomeActivity = new WelcomeActivity();
        }
        return welcomeActivity;
    }

    public void init(IOSDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void clickRegister(){
        btnRegister.click();
    }

    public void clickLogin(){
        btnLogin.click();
    }

    public boolean showWelcomeActivity(){
        return Common.getInstance().showActivity(btnLogin);
    }
}
