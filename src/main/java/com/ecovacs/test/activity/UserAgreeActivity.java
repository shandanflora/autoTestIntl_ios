package com.ecovacs.test.activity;

import com.ecovacs.test.common.TranslateErrorReport;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;

/**
 * Created by ecosqa on 17/2/14.
 *
 */
public class UserAgreeActivity {

    private static UserAgreeActivity userAgreeActivity = null;

    private UserAgreeActivity(){

    }

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAStaticText[1]")
    private MobileElement title = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]")
    private MobileElement back = null;

    public static UserAgreeActivity getInstance(){
        if (userAgreeActivity == null){
            userAgreeActivity = new UserAgreeActivity();
        }
        return userAgreeActivity;
    }

    public void init(IOSDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public boolean staticUITranslate(Map<String, String> tranMap){
        boolean btitle = title.getText().equalsIgnoreCase(tranMap.get("User_agreement"));
        if (!btitle){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "about", title.getText(),
                    tranMap.get("User_agreement"), "fail");
        }
        return btitle;
    }

    public void clickBack(){
        back.click();
    }
}
