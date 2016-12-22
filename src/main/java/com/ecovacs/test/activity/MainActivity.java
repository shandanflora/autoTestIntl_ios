package com.ecovacs.test.activity;

import com.ecovacs.test.common.Common;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * robot list activity
 * Created by ecosqa on 16/12/8.
 */
public class MainActivity {
    private static MainActivity mainActivity = null;

    private MainActivity(){

    }

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAButton[1]")
    private IOSElement btnAddNewRobot = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]")
    private IOSElement textViewMore = null;

    public static MainActivity getInstance(){
        if (mainActivity == null){
            mainActivity = new MainActivity();
        }
        return mainActivity;
    }

    public void init(IOSDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public boolean showActivity(){
       return Common.getInstance().showActivity(btnAddNewRobot);
    }

    public void clickMore(){
        textViewMore.click();
    }
}
