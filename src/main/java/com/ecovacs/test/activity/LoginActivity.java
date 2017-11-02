package com.ecovacs.test.activity;

import com.ecovacs.test.common.Common;
import com.ecovacs.test.common.PropertyData;
import com.ecovacs.test.common.TranslateErrorReport;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by ecosqa on 16/12/7.
 * login activity
 */
public class LoginActivity {
    private static LoginActivity loginActivity = null;
    private static Logger logger = LoggerFactory.getLogger(LoginActivity.class);
    private IOSDriver driver = null;

    private LoginActivity(){}

    @FindBy(xpath = " //UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAStaticText[1]")
    private IOSElement titleLogin = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]")
    private IOSElement headRight = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]")
    private MobileElement back = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[2]")
    private MobileElement line1Country_Region = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[4]")
    private MobileElement line3Email_Pass = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[3]")
    private IOSElement eleCountry = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATextField[3]")
    private IOSElement editEmail = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]")
    private IOSElement editPass = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[6]")
    private IOSElement btnLogin = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[7]")
    private IOSElement textViewForget = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[4]")
    private IOSElement staticPassword = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[5]")
    private MobileElement textViewMessage = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATextField[3]/UIAButton[1]")
    private MobileElement btnClearTextEmail = null;
    /*@FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]/UIAButton[1]")
    MobileElement btnCleartPass = null;*/


    public static LoginActivity getInstance(){
        if(loginActivity == null){
            loginActivity = new LoginActivity();
        }
        return loginActivity;
    }

    public void init(IOSDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.driver = driver;
    }

    public void clickBack(){
        back.click();
    }

    public void clickForgetPass(){
        textViewForget.click();
    }

    public boolean showLoginActivity(){
        return Common.getInstance().showActivity(editEmail);
    }

    public void clickCountry(){
        eleCountry.click();
    }

    public void fillInfoAndClick(String strEmail, String strPass){
        editEmail.sendKeys(strEmail);
        staticPassword.click();
        editPass.sendKeys(strPass);
        staticPassword.click();
        btnLogin.click();
    }

    public void fillInfoAndClick2(String strEmail, String strPass){
        driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[6]").sendKeys(strEmail);
        driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAStaticText[9]").click();
        driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[2]").sendKeys(strPass);
        driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAStaticText[9]").click();
        driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAButton[13]").click();
    }

    private boolean staticUI(Map<String, String> tranMap){
        boolean bTitleLogin = titleLogin.getText().equalsIgnoreCase(tranMap.get("login"));
        if(!bTitleLogin){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "Login", titleLogin.getText(),
                    tranMap.get("login"), "fail");
        }
        boolean bheadRight = headRight.getText().equalsIgnoreCase(tranMap.get("register"));
        if(!bTitleLogin){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "Login", headRight.getText(),
                    tranMap.get("register"), "fail");
        }
        boolean bline1Country_Region = line1Country_Region.getText().equalsIgnoreCase(tranMap.get("guojiadiqu"));
        if(!bTitleLogin){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "Login", line1Country_Region.getText(),
                    tranMap.get("guojiadiqu"), "fail");
        }
        boolean bline3Email_Pass = line3Email_Pass.getText().equalsIgnoreCase(tranMap.get("email_pass"));
        if(!bline3Email_Pass){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "Login", line3Email_Pass.getText(),
                    tranMap.get("email_pass"), "fail");
        }
        boolean beditEmail = editEmail.getText().equalsIgnoreCase(tranMap.get("Email"));
        if(!beditEmail){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "Login", editEmail.getText(),
                    tranMap.get("Email"), "fail");
        }
        logger.info(editPass.getText());
        boolean beditPass = editPass.getText().equalsIgnoreCase(tranMap.get("email_pass_8_20"));
        if(!beditPass){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "Login", editPass.getText(),
                    tranMap.get("email_pass_8_20"), "fail");
        }
        boolean bbtnLogin = btnLogin.getText().equalsIgnoreCase(tranMap.get("login"));
        if(!bbtnLogin){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "Login", btnLogin.getText(),
                    tranMap.get("login"), "fail");
        }
        boolean btextViewForget = textViewForget.getText().equalsIgnoreCase(tranMap.get("forget_password"));
        if(!btextViewForget){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "Login", textViewForget.getText(),
                    tranMap.get("forget_password"), "fail");
        }
        logger.info("Finish to check static UI!!!");
        return bTitleLogin && bheadRight && bline1Country_Region &&
                bline3Email_Pass && beditPass && bbtnLogin && btextViewForget;
    }

    private boolean emptyEmail(Map<String, String> tranMap){
        editEmail.click();
        line3Email_Pass.click();
        boolean btextViewMessage = textViewMessage.getText().equalsIgnoreCase(tranMap.get("input_account"));
        if(!btextViewMessage){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "Login", textViewMessage.getText(),
                    tranMap.get("input_account"), "fail");
        }
        logger.info("Finish to check empty e-mail!!!");
        return btextViewMessage;
    }

    private boolean invalidEmail(Map<String, String> tranMap){
        editEmail.sendKeys("ecovacs@hotmail");
        line3Email_Pass.click();
        boolean btextViewMessage = textViewMessage.getText().equalsIgnoreCase(tranMap.get("Email"));
        if(!btextViewMessage){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "Login", textViewMessage.getText(),
                    tranMap.get("Email"), "fail");
        }
        logger.info("Finish to invalid e-mail!!!");
        return btextViewMessage;
    }

    private boolean emptyPassword(Map<String, String> tranMap){
        editEmail.click();
        btnClearTextEmail.click();
        editEmail.sendKeys(PropertyData.getProperty("hotmail_email"));
        line3Email_Pass.click();
        editPass.click();
        editPass.click();
        boolean btextViewMessage = textViewMessage.getText().equalsIgnoreCase(tranMap.get("pass_null"));
        if(!btextViewMessage){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "Login", textViewMessage.getText(),
                    tranMap.get("pass_null"), "fail");
        }
        logger.info("Finish to check empty password!!!");
        return btextViewMessage;

    }

    private boolean invalidPassword(Map<String, String> tranMap){
        editEmail.click();
        btnClearTextEmail.click();
        editEmail.sendKeys(PropertyData.getProperty("hotmail_email"));
        line3Email_Pass.click();
        editPass.sendKeys("1234");
        line3Email_Pass.click();
        boolean btextViewMessage = textViewMessage.getText().equalsIgnoreCase(tranMap.get("pass_geShi"));
        if(!btextViewMessage){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "Login", textViewMessage.getText(),
                    tranMap.get("pass_geShi"), "fail");
        }
        logger.info("Finish to check invalid password!!!");
        return btextViewMessage;
    }

    public boolean translate(Map<String, String> tranMap){
        boolean bResult = staticUI(tranMap);
        boolean bResult1 = emptyEmail(tranMap);
        boolean bResult2 = invalidEmail(tranMap);
        boolean bResult3 = emptyPassword(tranMap);
        boolean bResult4 = invalidPassword(tranMap);

        return bResult && bResult1 && bResult2 && bResult3 && bResult4;
    }


}
