package com.ecovacs.test.activity;

import com.ecovacs.test.common.TranslateErrorReport;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;

/**
 * Created by ecosqa on 17/2/18.
 *
 */
public class FullScreenActivity {
    private static FullScreenActivity fullScreenActivity = null;

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[1]")
    private MobileElement back = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[3]/UIAStaticText[1]")
    private MobileElement currentStatus = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[4]")
    private MobileElement btnCharge = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[6]")
    private MobileElement textCharge = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[3]")
    private MobileElement btnAuto = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[5]")
    private MobileElement textAuto = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[5]")
    private MobileElement btnSpot = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[7]")
    private MobileElement textSpot = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[11]")
    private MobileElement btnArea = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[8]")
    private MobileElement textArea = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[12]")
    private MobileElement btnVirtual = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[9]")
    private MobileElement textVirtual = null;


    private FullScreenActivity(){

    }

    public static FullScreenActivity getInstance(){
        if (fullScreenActivity == null){
            fullScreenActivity = new FullScreenActivity();
        }
        return fullScreenActivity;
    }

    public void init(IOSDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
/*
    public void clickAuto(){
        btnAuto.click();
    }

    public void clickSpot(){
        btnSpot.click();
    }

    public void clickArea(){
        btnArea.click();
    }*/

    public void clickVirtual(){
        btnVirtual.click();
    }

    public void clickCharge(){
        btnCharge.click();
    }

    public void clickBack(){
        back.click();
    }

    public boolean staticUITranslation(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        boolean bbtnCharge = textCharge.getText().equalsIgnoreCase(tranMap.get("ibt_return_charge_text"));
        if (!bbtnCharge){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "FullScreen", textCharge.getText(),
                    tranMap.get("ibt_return_charge_text"), "fail");
        }
        boolean bbtnAuto = textAuto.getText().equalsIgnoreCase(tranMap.get("dm88_ziDong"));
        if (!bbtnAuto){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "FullScreen", textAuto.getText(),
                    tranMap.get("dm88_ziDong"), "fail");
        }
        boolean bbtnSpot = textSpot.getText().equalsIgnoreCase(tranMap.get("dm88_dingDian"));
        if (!bbtnSpot){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "FullScreen", textSpot.getText(),
                    tranMap.get("dm88_dingDian"), "fail");
        }
        boolean bbtnArea = textArea.getText().equalsIgnoreCase(tranMap.get("quyu"));
        if (!bbtnArea){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "FullScreen", textArea.getText(),
                    tranMap.get("quyu"), "fail");
        }
        boolean bbtnVirtual = textVirtual.getText().equalsIgnoreCase(tranMap.get("wall"));
        if (!bbtnVirtual){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "FullScreen", textVirtual.getText(),
                    tranMap.get("wall"), "fail");
        }
        return bbtnCharge && bbtnAuto && bbtnSpot && bbtnArea && bbtnVirtual;
    }



}
