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
 * reset map activity
 */
public class ResetMapActivity {
    private static ResetMapActivity resetMapActivity = null;

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAStaticText[1]")
    private MobileElement title = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]")
    private MobileElement back = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[1]")
    private MobileElement descriptionLine1 = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[2]")
    private MobileElement descriptionLine2 = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[1]")
    private MobileElement btnResetMap = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[4]/UIAAlert[1]/UIAScrollView[1]/UIAStaticText[1]")
    private MobileElement promptTitle = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[4]/UIAAlert[1]/UIAScrollView[1]/UIAStaticText[2]")
    private MobileElement promptMessage = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[4]/UIAAlert[1]/UIACollectionView[1]/UIACollectionCell[1]/UIAButton[1]")
    private MobileElement btnNo = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[4]/UIAAlert[1]/UIACollectionView[1]/UIACollectionCell[2]/UIAButton[1]")
    private MobileElement btnYes = null;

    private ResetMapActivity(){

    }

    public static ResetMapActivity getInstance(){
        if (resetMapActivity == null){
            resetMapActivity = new ResetMapActivity();
        }
        return resetMapActivity;
    }

    public void init(IOSDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void clickBack(){
        back.click();
    }

    public void showActivity(){
        Common.getInstance().showActivity(btnResetMap);
    }

    private boolean staticUITranslation(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        boolean btitle = title.getText().equalsIgnoreCase(tranMap.get("reset_map"));
        if(!btitle){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "ResetMap", title.getText(),
                    tranMap.get("reset_map"), "fail");
        }
        String str = tranMap.get("CleanMap_clean_hint_text_ios").replaceAll("\\\\n", "");
        boolean bdescriptionLine1 = descriptionLine1.getText().equalsIgnoreCase(str);
        if(!bdescriptionLine1){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "ResetMap", descriptionLine1.getText(),
                    str, "fail");
        }
        boolean bdescriptionLine2 = descriptionLine2.getText().equalsIgnoreCase(tranMap.get("zhuJi_manDian_ios"));
        if(!bdescriptionLine2){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "ResetMap", descriptionLine2.getText(),
                    tranMap.get("zhuJi_manDian_ios"), "fail");
        }
        boolean bbtnResetMap = btnResetMap.getText().equalsIgnoreCase(tranMap.get("reset_map"));
        if(!bbtnResetMap){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "ResetMap", btnResetMap.getText(),
                    tranMap.get("reset_map"), "fail");
        }
        return btitle && bdescriptionLine1 && bdescriptionLine2 && bbtnResetMap;
    }

    private boolean promptTranslate(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        btnResetMap.click();
        boolean bPromptTitle = promptTitle.getText().equalsIgnoreCase(tranMap.get("tips"));
        if (!bPromptTitle){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "ResetMap", promptTitle.getText(),
                    tranMap.get("tips"), "fail");
        }
        String str = tranMap.get("CleanMap_clean_hint_text_ios").replaceAll("\\\\n", "");
        boolean bPromptMessage = promptMessage.getText().equalsIgnoreCase(str);
        if (!bPromptMessage){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "ResetMap", promptMessage.getText(),
                    str, "fail");
        }
        boolean bbtnNo = btnNo.getText().equalsIgnoreCase(tranMap.get("cancel"));
        if (!bbtnNo){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "ResetMap", btnNo.getText(),
                    tranMap.get("cancel"), "fail");
        }
        boolean bbtnYes = btnYes.getText().equalsIgnoreCase(tranMap.get("reset_map"));
        if (!bbtnYes){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "ResetMap", btnYes.getText(),
                    tranMap.get("reset_map"), "fail");
        }
        if(strLanguage.equals("French") || strLanguage.equals("Italian")){
            System.out.println(strLanguage);
            btnYes.click();
        }else {
            System.out.println(strLanguage);
            btnNo.click();
        }
        return bPromptTitle && bPromptMessage && bbtnNo && bbtnYes;
    }

    public boolean translate(Map<String, String> tranMap){
        boolean bStatic = staticUITranslation(tranMap);
        boolean bPrompt = promptTranslate(tranMap);
        return bStatic && bPrompt;
    }

}
