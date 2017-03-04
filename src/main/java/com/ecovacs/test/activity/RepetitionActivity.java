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
public class RepetitionActivity {
    private static RepetitionActivity repetitionActivity = null;

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAStaticText[1]")
    private MobileElement title = null;
    @FindBy(xpath = " //UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]")
    private MobileElement back = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]")
    private MobileElement sunday = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]/UIAStaticText[1]")
    private MobileElement monday = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[3]/UIAStaticText[1]")
    private MobileElement tuesday = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[4]/UIAStaticText[1]")
    private MobileElement wednesday = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[5]/UIAStaticText[1]")
    private MobileElement thursday = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[6]/UIAStaticText[1]")
    private MobileElement friday = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[7]/UIAStaticText[1]")
    private MobileElement saturday = null;

    private RepetitionActivity(){

    }

    public static RepetitionActivity getInstance(){
        if (repetitionActivity == null){
            repetitionActivity = new RepetitionActivity();
        }
        return repetitionActivity;
    }

    public void init(IOSDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    private void clickSun(){
        sunday.click();
    }

    private void clickMon(){
        monday.click();
    }

    private void clickTues(){
        tuesday.click();
    }

    private void clickWed(){
        wednesday.click();
    }

    private void clickThurs(){
        thursday.click();
    }

    private void clickFri(){
        friday.click();
    }

    private void clickSat(){
        saturday.click();
    }

    void clickWeekOfDate(int iIndex){
        switch (iIndex){
            case 0:
                clickSun();
                break;
            case 1:
                clickMon();
                break;
            case 2:
                clickTues();
                break;
            case 3:
                clickWed();
                break;
            case 4:
                clickThurs();
                break;
            case 5:
                clickFri();
                break;
            case 6:
                clickSat();
                break;
            default:
                break;
        }
    }

    public void clickBack(){
        back.click();
    }

    public boolean staticUITranslation(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        boolean bTitle = title.getText().equalsIgnoreCase(tranMap.get("repeat"));
        if (!bTitle){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Repetition", title.getText(),
                    tranMap.get("repeat"), "fail");
        }
        boolean bsunday = sunday.getText().equalsIgnoreCase(tranMap.get("zhouRi"));
        if (!bsunday){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Repetition", sunday.getText(),
                    tranMap.get("zhouRi"), "fail");
        }
        boolean bmonday = monday.getText().equalsIgnoreCase(tranMap.get("zhouYi"));
        if (!bmonday){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Repetition", monday.getText(),
                    tranMap.get("zhouYi"), "fail");
        }
        boolean btuesday = tuesday.getText().equalsIgnoreCase(tranMap.get("zhouEr"));
        if (!btuesday){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Repetition", tuesday.getText(),
                    tranMap.get("zhouEr"), "fail");
        }
        boolean bwednesday = wednesday.getText().equalsIgnoreCase(tranMap.get("zhouSan"));
        if (!bwednesday){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Repetition", wednesday.getText(),
                    tranMap.get("zhouSan"), "fail");
        }
        boolean bthursday = thursday.getText().equalsIgnoreCase(tranMap.get("zhouSi"));
        if (!bthursday){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Repetition", thursday.getText(),
                    tranMap.get("zhouSi"), "fail");
        }
        boolean bfriday = friday.getText().equalsIgnoreCase(tranMap.get("zhouWu"));
        if (!bfriday){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Repetition", friday.getText(),
                    tranMap.get("zhouWu"), "fail");
        }
        boolean bsaturday = saturday.getText().equalsIgnoreCase(tranMap.get("zhouLiu"));
        if (!bsaturday){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Repetition", saturday.getText(),
                    tranMap.get("zhouLiu"), "fail");
        }
        return bTitle && bsunday && bmonday && btuesday &&
                bwednesday && bthursday && bfriday && bsaturday;
    }


}
