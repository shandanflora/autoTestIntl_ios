package com.ecovacs.test.activity;

import com.ecovacs.test.common.TranslateErrorReport;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;

/**
 * Created by ecosqa on 17/2/16.
 *
 */
public class SettingActivity {
    private static SettingActivity settingActivity = null;
    private IOSDriver driver = null;

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAStaticText[1]")
    private MobileElement title = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]")
    private MobileElement back = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]")
    private MobileElement textViewWorkLog = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]/UIAStaticText[1]")
    private MobileElement textViewContinuedClean = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[3]/UIAStaticText[1]")
    private MobileElement textViewTimeSchedule = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[4]/UIAStaticText[1]")
    private MobileElement textViewReset = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[5]/UIAStaticText[1]")
    private MobileElement textViewLanguage = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[6]/UIAStaticText[3]")
    private MobileElement textViewFirmware = null;

    private SettingActivity(){

    }

    public static SettingActivity getInstance(){
        if (settingActivity == null){
            settingActivity = new SettingActivity();
        }
        return settingActivity;
    }

    public void init(IOSDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.driver = driver;
    }

    public void clickBack(){
        back.click();
    }

    public void clickWorkLog(){
        //textViewWorkLog.click();
        driver.tap(1, textViewWorkLog, 100);
    }

    public void clickContinuedClean(){
        //textViewContinuedClean.click();
        driver.tap(1, textViewContinuedClean, 50);
    }

    public void clickTimeSchedule(){
        //textViewTimeSchedule.click();
        driver.tap(1, textViewTimeSchedule, 100);
    }

    public void clickReset(){
        //textViewReset.click();
        driver.tap(1, textViewReset, 100);
    }

    public void clickLanguage(){
        driver.tap(1, textViewLanguage, 100);
    }

    public void clickFirmware(){
        driver.tap(1, textViewFirmware, 100);
    }

    public boolean staticUiTranslate(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        boolean bTitle = title.getText().equalsIgnoreCase(tranMap.get("load_more"));
        if (!bTitle) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Settting", title.getText(),
                    tranMap.get("load_more"), "fail");
        }
        boolean btextViewWorkLog = textViewWorkLog.getText().equalsIgnoreCase(tranMap.get("work_log"));
        if (!btextViewWorkLog) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Settting", textViewWorkLog.getText(),
                    tranMap.get("work_log"), "fail");
        }
        boolean btextViewContinuedClean = textViewContinuedClean.getText().equalsIgnoreCase(tranMap.get("duanDianXuSao"));
        if (!btextViewContinuedClean) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Settting", textViewContinuedClean.getText(),
                    tranMap.get("duanDianXuSao"), "fail");
        }
        boolean btextViewTimeSchedule = textViewTimeSchedule.getText().equalsIgnoreCase(tranMap.get("Time_to_make_an_appointment"));
        if (!btextViewTimeSchedule) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Settting", textViewTimeSchedule.getText(),
                    tranMap.get("Time_to_make_an_appointment"), "fail");
        }
        boolean btextViewReset = textViewReset.getText().equalsIgnoreCase(tranMap.get("reset_map"));
        if (!btextViewReset) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Settting", textViewReset.getText(),
                    tranMap.get("reset_map"), "fail");
        }
        boolean btextViewLanguage = textViewLanguage.getText().equalsIgnoreCase(tranMap.get("zhuJiDuoYuYan"));
        if (!btextViewLanguage) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Settting", textViewLanguage.getText(),
                    tranMap.get("zhuJiDuoYuYan"), "fail");
        }
        boolean btextViewFirmware = textViewFirmware.getText().equalsIgnoreCase(tranMap.get("The_host_Settings"));
        if (!btextViewFirmware) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Settting", textViewFirmware.getText(),
                    tranMap.get("The_host_Settings"), "fail");
        }
        return bTitle && btextViewWorkLog && btextViewContinuedClean &&
                btextViewTimeSchedule && btextViewReset &&
                btextViewLanguage && btextViewFirmware;
    }


}
