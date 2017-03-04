package com.ecovacs.test.activity;

import com.ecovacs.test.common.TranslateErrorReport;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by ecosqa on 17/2/9.
 *
 */
public class LanguageActivity {
    private static LanguageActivity languageActivity = null;
    private IOSDriver driver = null;
    private static Logger logger = LoggerFactory.getLogger(LanguageActivity.class);

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAStaticText[1]")
    private MobileElement title = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]")
    private MobileElement btnOK = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]")
    private MobileElement back = null;

    private LanguageActivity(){

    }

    public static LanguageActivity getInstance(){
        if(languageActivity == null){
            languageActivity = new LanguageActivity();
        }
        return languageActivity;
    }

    public void init(IOSDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.driver = driver;
    }

    public void clickBack(){
        back.click();
    }

    public boolean selectLanguage(String strLanguage){

        String path = "UIATarget.localTarget().frontMostApp().mainWindow()." +
                "tableViews()[0].cells()[\"" + strLanguage + "\"]";
        //driver.executeScript(path + ".scrollToVisible();");

        MobileElement textViewCountry;
        try {
            textViewCountry = (MobileElement)driver
                    .findElementByIosUIAutomation(path);
        }catch (NoSuchElementException e){
            logger.error("Can not find language: " + strLanguage);
            return false;
        }
        textViewCountry.click();
        btnOK.click();
        logger.info("Selected Language - " + strLanguage);
        return true;
    }

    public boolean staticUITranslation(Map<String, String> tranMap){
        boolean bTitle = title.getText().equalsIgnoreCase(tranMap.get("multi_lingual_a"));
        if (!bTitle){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "Language", title.getText(),
                    tranMap.get("multi_lingual_a"), "fail");
        }
        return bTitle;
    }
}
