package com.ecovacs.test.activity;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by ecosqa on 16/9/18.
 *
 */
public class CountrySelectActivity {

    private static Logger logger = LoggerFactory.getLogger(CountrySelectActivity.class);
    private static CountrySelectActivity countrySelectActivity = null;

    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]")
    private IOSElement btnOK = null;
    /*@AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/title_back")
    private AndroidElement btnBack = null;*/

    private IOSDriver driver = null;

    private CountrySelectActivity(){

    }

    public static CountrySelectActivity getInstance(){
        if(countrySelectActivity == null){
            countrySelectActivity = new CountrySelectActivity();
        }
        return countrySelectActivity;
    }

    public void init(IOSDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.driver = driver;
    }

    /*public void back(){
        btnBack.click();
    }*/

    public boolean selectCountry(String strCountry){

        String path = "UIATarget.localTarget().frontMostApp().mainWindow()." +
                "tableViews()[0].cells()[\"" + strCountry + "\"]";
        //driver.executeScript(path + ".scrollToVisible();");

        MobileElement textViewCountry;
        try {
            textViewCountry = (MobileElement) driver
                    .findElementByIosUIAutomation(path);
        }catch (NoSuchElementException e){
            logger.error("Can not find country: " + strCountry);
            return false;
        }
        textViewCountry.click();
        btnOK.click();
        logger.info("Selected country - " + strCountry);
        return true;
    }
}
