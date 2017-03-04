package com.ecovacs.test.activity;

import com.ecovacs.test.common.TranslateErrorReport;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;

/**
 * Created by ecosqa on 17/2/17.
 * voice report activity
 */
public class VoiceReportActivity {
    private static VoiceReportActivity voiceReportActivity = null;

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAStaticText[1]")
    private MobileElement title = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]")
    private MobileElement back = null;

    private VoiceReportActivity(){

    }

    public static VoiceReportActivity getInstance(){
        if (voiceReportActivity == null){
            voiceReportActivity = new VoiceReportActivity();
        }
        return voiceReportActivity;
    }

    public void init(IOSDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void clickBack(){
        back.click();
    }

    public boolean staticUITranslation(Map<String, String> tranMap){
        boolean bTitle = title.getText().equalsIgnoreCase(tranMap.get("zhuJiDuoYuYan"));
        if (!bTitle) {
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "VoiceReport", title.getText(),
                    tranMap.get("zhuJiDuoYuYan"), "fail");
        }
        return bTitle;
    }


}
