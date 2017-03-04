package com.ecovacs.test.activity;

import com.ecovacs.test.common.Common;
import com.ecovacs.test.common.TranslateErrorReport;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;

/**
 * Created by ecosqa on 17/2/17.
 * firmware version activity
 */
public class FirmVerActivity {
    private static FirmVerActivity firmVerActivity = null;

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAStaticText[1]")
    private MobileElement title = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]")
    private MobileElement back = null;
    /*@FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[2]")
    private MobileElement gettingVer = null;*/
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[2]")
    private MobileElement currentFirmVer = null;

    private FirmVerActivity(){

    }

    public static FirmVerActivity getInstance(){
        if (firmVerActivity == null){
            firmVerActivity = new FirmVerActivity();
        }
        return firmVerActivity;
    }

    public void init(IOSDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void clickBack(){
        back.click();
    }

    public boolean staticUITranslation(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        boolean bTitle = title.getText().equalsIgnoreCase(tranMap.get("The_host_Settings"));
        if(!bTitle){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "FirmwareVer", title.getText(),
                    tranMap.get("The_host_Settings"), "fail");
        }
        //may be delete
        //too fast can not catch element
        /*boolean bgettingVer = gettingVer.getText().equalsIgnoreCase(tranMap.get("fir_version_get_ios"));
        if(!bgettingVer){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "FirmwareVer", gettingVer.getText(),
                    tranMap.get("fir_version_get_ios"), "fail");
        }*/
        Common.getInstance().showActivity(currentFirmVer);
        boolean bcurrentFirmVer = currentFirmVer.getText().equalsIgnoreCase(tranMap.get("fir_version_cur_new"));
        if(!bcurrentFirmVer){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "FirmwareVer", currentFirmVer.getText(),
                    tranMap.get("fir_version_cur_new"), "fail");
        }
        return bTitle /*&& bgettingVer*/ && bcurrentFirmVer;
    }
}
