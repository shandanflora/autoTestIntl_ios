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
public class AboutActivity {
    private static AboutActivity aboutActivity = null;

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAStaticText[1]")
    private MobileElement title = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]")
    private MobileElement back = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[1]")
    private MobileElement textViewCurrentVer = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[2]")
    private MobileElement textViewUser = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[1]")
    private MobileElement btnUser = null;

    private AboutActivity(){

    }

    public static AboutActivity getInstance(){
        if (aboutActivity == null){
            aboutActivity = new AboutActivity();
        }
        return aboutActivity;
    }

    public void init(IOSDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public boolean staticUITranslate(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        boolean btitle = title.getText().equalsIgnoreCase(tranMap.get("about"));
        if(!btitle){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "About", title.getText(),
                    tranMap.get("about"), "fail");
        }
        String strCurVer = textViewCurrentVer.getText();
        System.out.println(strCurVer);
        System.out.println(tranMap.get("The_current_version_number_fill_out"));
        boolean bCurVer = strCurVer.contains(tranMap.get("The_current_version_number_fill_out"));
        if(!bCurVer){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "About", strCurVer,
                    tranMap.get("The_current_version_number_fill_out"), "fail");
        }
        boolean btextViewUser = textViewUser.getText().equalsIgnoreCase(tranMap.get("User_agreement"));
        if(!btextViewUser){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "About", textViewUser.getText(),
                    tranMap.get("User_agreement"), "fail");
        }

        return btitle && bCurVer && btextViewUser;
    }

    public void clickUserAgree(){
        btnUser.click();
    }

    public void clickBack(){
        back.click();
    }

}
