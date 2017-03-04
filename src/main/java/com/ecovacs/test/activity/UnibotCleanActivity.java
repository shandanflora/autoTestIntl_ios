package com.ecovacs.test.activity;

import com.ecovacs.test.common.Common;
import com.ecovacs.test.common.TranslateErrorReport;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by ecosqa on 17/2/15.
 * unibot cleaning
 */
public class UnibotCleanActivity {
    private static UnibotCleanActivity unibotCleanActivity = null;
    private static Logger logger = LoggerFactory.getLogger(UnibotCleanActivity.class);

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]")
    private MobileElement btnBack = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[1]")
    private MobileElement textViewStatusValue = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[3]")
    private MobileElement textViewStatus = null;
    /*@FindBy(id = "com.ecovacs.ecosphere.intl:id/deebot_battery_statues")
    private AndroidElement textViewBatteryValue = null;*/
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[4]")
    private MobileElement textViewDeBattery = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[16]")
    private MobileElement btnAuto = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[10]")
    private MobileElement textViewDeAuto = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[11]")
    private MobileElement textViewDeCharge = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[17]")
    private MobileElement btnCharge = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]")
    private MobileElement imageBtnRight = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[15]")
    private MobileElement imageViewFullScreen = null;
    @FindBy(xpath ="//UIAApplication[1]/UIAWindow[1]/UIAStaticText[12]")
    private MobileElement promptTitle = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[13]")
    private MobileElement promptMessage = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[18]")
    private MobileElement promptBtnCancel = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[19]")
    private MobileElement promptBtnConfirm = null;


    private UnibotCleanActivity() {

    }

    public static UnibotCleanActivity getInstance() {
        if (unibotCleanActivity == null) {
            unibotCleanActivity = new UnibotCleanActivity();
        }
        return unibotCleanActivity;
    }

    public void init(IOSDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public boolean showHaveMapActivity(){
        return Common.getInstance().showActivity(imageViewFullScreen);
    }

    private boolean showText(String strText) {
        boolean bResult = false;
        int iLoop = 0;
        while (true) {
            if (iLoop > 50) {
                break;
            }
            if (textViewStatusValue.getText().contains(strText)) {
                logger.info(textViewStatusValue.getText());
                Common.getInstance().waitForSecond(500);
            } else {
                logger.info(textViewStatusValue.getText());
                bResult = true;
                break;
            }
            iLoop++;
        }
        return bResult;
    }

    public void clickSetting(){
        imageBtnRight.click();
    }

    public void clickBack(){
        btnBack.click();
    }

    public void clickFullScreen(){
        imageViewFullScreen.click();
    }

    private boolean staticUITranslate(Map<String, String> tranMap) {
        String strLanguage = tranMap.get("language");
        boolean btextViewStatus = textViewStatus.getText().equalsIgnoreCase(tranMap.get("current_status"));
        if (!btextViewStatus) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "UnibotClean", textViewStatus.getText(),
                    tranMap.get("current_status"), "fail");
        }
        boolean btextViewStatusValue = textViewStatusValue.getText().equalsIgnoreCase(tranMap.get("deebot_charging"));
        if (!btextViewStatusValue) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "UnibotClean", textViewStatusValue.getText(),
                    tranMap.get("deebot_charging"), "fail");
        }
        boolean btextViewDeBattery = textViewDeBattery.getText().equalsIgnoreCase(tranMap.get("deebot_battery_desc"));
        if (!btextViewDeBattery) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "UnibotClean", textViewDeBattery.getText(),
                    tranMap.get("deebot_battery_desc"), "fail");
        }
        boolean btextViewDeAuto = textViewDeAuto.getText().equalsIgnoreCase(tranMap.get("dm88_ziDong"));
        if (!btextViewDeAuto) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "UnibotClean", textViewDeAuto.getText(),
                    tranMap.get("dm88_ziDong"), "fail");
        }
        boolean btextViewDeCharge = textViewDeCharge.getText().equalsIgnoreCase(tranMap.get("ibt_return_charge_text"));
        if (!btextViewDeCharge) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "UnibotClean", textViewDeCharge.getText(),
                    tranMap.get("ibt_return_charge_text"), "fail");
        }

        return btextViewStatus && btextViewStatusValue && btextViewDeBattery &&
                btextViewDeAuto && btextViewDeCharge;
    }

    public boolean translate(Map<String, String> tranMap){
        boolean bStatic = staticUITranslate(tranMap);
        boolean bcheckStatus = checkStatus(tranMap);
        return bStatic && bcheckStatus;
    }

    private boolean promptTitle_confirm_cancel(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        boolean bpromptTitle = promptTitle.getText().equalsIgnoreCase(tranMap.get("tips"));
        if (!bpromptTitle) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "UnibotClean", promptTitle.getText(),
                    tranMap.get("tips"), "fail");
        }
        boolean bpromptBtnCancel = promptBtnCancel.getText().equalsIgnoreCase(tranMap.get("cancel"));
        if (!bpromptBtnCancel) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "UnibotClean", promptBtnCancel.getText(),
                    tranMap.get("cancel"), "fail");
        }
        boolean bpromptBtnConfirm = promptBtnConfirm.getText().equalsIgnoreCase(tranMap.get("confirm"));
        if (!bpromptBtnConfirm) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "UnibotClean", promptBtnConfirm.getText(),
                    tranMap.get("confirm"), "fail");
        }
        return bpromptTitle && bpromptBtnCancel && bpromptBtnConfirm;
    }

    private boolean checkStatus(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        btnAuto.click();
        //preparing to work
        String strValue = textViewStatusValue.getText();
        boolean btextViewStatusValue = strValue.equalsIgnoreCase(tranMap.get("zhunBeiZhong"));
        if (!btextViewStatusValue) {
            logger.info(strValue);
            logger.error("Status preparing to work is not match!!!");
            logger.info(tranMap.get("zhunBeiZhong"));
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "UnibotClean", strValue,
                    tranMap.get("zhunBeiZhong"), "fail");
        }
        //wait for cleaning
        showText(strValue);
        boolean btextViewStatusValue1 = textViewStatusValue.getText().equalsIgnoreCase(tranMap.get("auto_qingSao_zhong"));
        if (!btextViewStatusValue1) {
            logger.error("Status auto cleaning is not match!!!");
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "UnibotClean", textViewStatusValue.getText(),
                    tranMap.get("auto_qingSao_zhong"), "fail");
        }
        //auto clean to charge
        btnCharge.click();
        //prompt
        boolean bprompt = promptTitle_confirm_cancel(tranMap);
        String strTran = tranMap.get("deebot_work_to_charger");
        strTran = strTran.replaceAll("\\\\n", "");
        //logger.info(strTran);
        strTran = strTran.replace("%@", "%1$s");
        String strTranCleanCharge = String.format(strTran, tranMap.get("auto_qingSao_zhong"));
        //logger.info("excel--" + strTranCleanCharge);
        //int istrTranCleanCharge = strTranCleanCharge.length();
        //String str = promptMessage.getText();
        //logger.info("app--" + promptMessage.getText());
        //int iStr = str.length();
        boolean bpromptMessage = promptMessage.getText().equalsIgnoreCase(strTranCleanCharge);
        if (!bpromptMessage) {
            logger.error("Prompt message is not match!!!");
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "UnibotClean", promptMessage.getText(),
                    strTranCleanCharge, "fail");
        }
        promptBtnConfirm.click();
        //wait for 2.5s
        Common.getInstance().waitForSecond(2500);
        boolean btextViewStatusValue2 = textViewStatusValue.getText().equalsIgnoreCase(tranMap.get("GO_CHARGING"));
        if (!btextViewStatusValue2) {
            logger.error("Status charging is not match!!!");
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "UnibotClean", textViewStatusValue.getText(),
                    tranMap.get("GO_CHARGING"), "fail");
        }
        //charging to auto clean
        btnAuto.click();
        Common.getInstance().waitForSecond(2500);
        boolean btextViewStatusValue3 = textViewStatusValue.getText().equalsIgnoreCase(tranMap.get("auto_qingSao_zhong"));
        if (!btextViewStatusValue3) {
            logger.error("Status auto cleaning is not match!!!");
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "UnibotClean", textViewStatusValue.getText(),
                    tranMap.get("auto_qingSao_zhong"), "fail");
        }
        btnCharge.click();
        promptBtnConfirm.click();
        showText(textViewStatusValue.getText());
        return btextViewStatusValue && bprompt && bpromptMessage &&
                btextViewStatusValue1 && btextViewStatusValue2 &&
                btextViewStatusValue3 ;
    }

}
