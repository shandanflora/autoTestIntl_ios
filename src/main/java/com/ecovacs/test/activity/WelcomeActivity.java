package com.ecovacs.test.activity;

import com.ecovacs.test.common.Common;
import com.ecovacs.test.common.TranslateErrorReport;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;

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

    public boolean translate(Map<String, String> tranMap){
        boolean bRegister = btnRegister.getText().equalsIgnoreCase(tranMap.get("register"));
        if(!bRegister){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "welcome", btnRegister.getText(),
                    tranMap.get("register"), "fail");
        }
        boolean bLogin = btnLogin.getText().equalsIgnoreCase(tranMap.get("login"));
        if(!bLogin){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "welcome", btnLogin.getText(),
                    tranMap.get("login"), "fail");
        }
        return  bRegister && bLogin;
    }
}
