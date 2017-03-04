package com.ecovacs.test.activity;

import com.ecovacs.test.common.Common;
import com.ecovacs.test.common.TranslateErrorReport;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by ecosqa on 16/12/8.
 * handle more activity
 */
public class MoreActivity {
    private static MoreActivity moreActivity = null;
    private static Logger logger = LoggerFactory.getLogger(MoreActivity.class);
    private IOSDriver driver = null;

    private MoreActivity(){

    }

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]")
    private IOSElement tableView = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[4]/UIAAlert[1]/UIAScrollView[1]/UIAStaticText[1]")
    private MobileElement promptTitle = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[4]/UIAAlert[1]/UIAScrollView[1]/UIAStaticText[2]")
    private MobileElement promptMessage = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[4]/UIAAlert[1]/UIACollectionView[1]/UIACollectionCell[2]/UIAButton[1]")
    private IOSElement btnConfirm = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[4]/UIAAlert[1]/UIACollectionView[1]/UIACollectionCell[1]/UIAButton[1]")
    private MobileElement btnCancel = null;
    /*@FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[3]/UIAStaticText[1]")
    private MobileElement rowLanguage = null;*/
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAStaticText[1]")
    private MobileElement title = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]")
    private MobileElement back = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]")
    private MobileElement textAccount = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]/UIAStaticText[1]")
    private MobileElement textChange = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[3]/UIAStaticText[1]")
    private MobileElement textLanguage = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[4]/UIAStaticText[1]")
    private MobileElement textAbout = null;

    public static MoreActivity getInstance(){
        if (moreActivity == null){
            moreActivity = new MoreActivity();
        }
        return moreActivity;
    }

    public boolean showMoreActivity(){
        return Common.getInstance().showActivity(tableView);
    }

    public void init(IOSDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.driver = driver;
    }

    public void clickBack(){
        back.click();
    }

    private Point getBtnLogOutPoint(){
        Point point = new Point(0, 0);
        Dimension dimension = new Dimension(0, 0);
        //Rectangle rect = null;
        int iCount = 0;
        List<MobileElement> cellList = null;
        while(iCount == 0) {
            cellList = tableView.findElementsByClassName("UIATableCell");
            iCount = cellList.size();
            logger.info("The count of row in tableview is:" + Integer.toString(iCount));
        }

        for(int i = 0; i < iCount; i++){
            if(i == 3){
                dimension = cellList.get(i).getSize();
                point = cellList.get(i).getLocation();
            }
        }
        int iRectX = point.getX();
        int iRectY = point.getY();
        int iWidth = dimension.getWidth();
        int iHeight = dimension.getHeight();

        point.x = iRectX + iWidth/2;
        point.y = iRectY + iHeight*2 + 20;

        return point;
    }

    public void clickLogout(){
        Point point = getBtnLogOutPoint();
        //click button Log out
        driver.tap(1, point.getX(), point.getY(), 0);
        logger.info("Click button--Logout!!!");
        btnConfirm.click();
        logger.info("Click button--Confirm Logout!!!");
        Common.getInstance().waitForSecond(500);
    }

    private boolean translatePrompt(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        Point point = getBtnLogOutPoint();
        //click button Log out
        driver.tap(1, point.getX(), point.getY(), 0);
        boolean bpromptTitle = promptTitle.getText().equalsIgnoreCase(tranMap.get("tips"));
        if(!bpromptTitle){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "More", promptTitle.getText(),
                    tranMap.get("tips"), "fail");
        }
        boolean bpromptMessage = promptMessage.getText().equalsIgnoreCase(tranMap.get("sure_quit"));
        if(!bpromptMessage){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "More", promptMessage.getText(),
                    tranMap.get("sure_quit"), "fail");
        }
        boolean bbtnCancel = btnCancel.getText().equalsIgnoreCase(tranMap.get("cancel"));
        if(!bbtnCancel){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "More", btnCancel.getText(),
                    tranMap.get("cancel"), "fail");
        }
        boolean bbtnConfirm = btnConfirm.getText().equalsIgnoreCase(tranMap.get("confirm"));
        if(!bbtnConfirm){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "More", btnConfirm.getText(),
                    tranMap.get("confirm"), "fail");
        }

        btnCancel.click();
        return bpromptTitle && bpromptMessage && bbtnCancel &&
                bbtnConfirm;
    }

    public void clickLanguage(){
        textLanguage.click();
    }

    public void clickChangePass(){
        textChange.click();
    }

    public void clickAbout(){
        textAbout.click();
    }

    private boolean staticUiTranslation(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        boolean btitle = title.getText().equalsIgnoreCase(tranMap.get("load_more"));
        if(!btitle){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "More", title.getText(),
                    tranMap.get("load_more"), "fail");
        }
        boolean btextAccount = textAccount.getText().equalsIgnoreCase(tranMap.get("accounts"));
        if(!btextAccount){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "More", textAccount.getText(),
                    tranMap.get("accounts"), "fail");
        }
        boolean btextChange = textChange.getText().equalsIgnoreCase(tranMap.get("Change_the_password"));
        if(!btextChange){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "More", textChange.getText(),
                    tranMap.get("Change_the_password"), "fail");
        }
        boolean btextLanguage = textLanguage.getText().equalsIgnoreCase(tranMap.get("multi_lingual_a"));
        if(!btextLanguage){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "More", textLanguage.getText(),
                    tranMap.get("multi_lingual_a"), "fail");
        }
        boolean btextAbout = textAbout.getText().equalsIgnoreCase(tranMap.get("about"));
        if(!btextAbout){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "More", textAbout.getText(),
                    tranMap.get("about"), "fail");
        }
        /*boolean bbtnLogout = btnLogout.getText().equalsIgnoreCase(tranMap.get("Log_out"));
        if(!bbtnLogout){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "More", textAbout.getText(),
                    tranMap.get("Log_out"), "fail");
        }*/
        return btitle && btextAccount && btextChange && btextLanguage &&
                btextAbout /*&& bbtnLogout*/;
    }

    public boolean translate(Map<String, String> tranMap){
        boolean bStatic = staticUiTranslation(tranMap);
        boolean bPrompt = translatePrompt(tranMap);
        return bStatic && bPrompt;
    }
}
