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
public class VirtualWallActivity {
    private static VirtualWallActivity virtualWallActivity = null;

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[5]")
    private MobileElement message = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[1]")
    private MobileElement btnCancel = null;

    private VirtualWallActivity(){

    }

    public static VirtualWallActivity getInstance(){
        if (virtualWallActivity == null){
            virtualWallActivity = new VirtualWallActivity();
        }
        return virtualWallActivity;
    }

    public void init(IOSDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void clickCancel(){
        btnCancel.click();
    }

    public boolean staticUITranslation(Map<String, String> tranMap){
        boolean bmessage = message.getText().equalsIgnoreCase(tranMap.get("select_xuNiQiang_ios"));
        if (!bmessage){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "VirtualWall", message.getText(),
                    tranMap.get("select_xuNiQiang_ios"), "fail");
        }
        return bmessage;
    }




}
