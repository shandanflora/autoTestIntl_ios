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
 * Created by ecosqa on 17/2/18.
 * new schedule activity
 */
public class NewScheduleActivity {
    private static NewScheduleActivity newScheduleActivity = null;

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAStaticText[1]")
    private MobileElement title = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]")
    private MobileElement confirmAdd = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]")
    private MobileElement back = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[1]")
    private MobileElement scheduleTask = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]")
    private MobileElement noSelected = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]/UIAStaticText[1]")
    private MobileElement areaAll = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[3]/UIAStaticText[1]")
    private MobileElement areaSingle = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIAStaticText[1]")
    private MobileElement setTime = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[1]/UIAStaticText[1]")
    private MobileElement startTime = null;
    @FindBy(xpath = " //UIAApplication[1]/UIAWindow[1]/UIAStaticText[2]")
    private MobileElement promptStartTitle = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[1]")
    private MobileElement promptCancel = null;
    @FindBy(xpath = " //UIAApplication[1]/UIAWindow[1]/UIAButton[2]")
    private MobileElement promptOK = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[2]/UIAStaticText[1]")
    private MobileElement repeat = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[2]/UIAStaticText[2]")
    private MobileElement repeatValue = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[1]")
    private MobileElement btnDelete = null;


    private NewScheduleActivity(){

    }

    public static NewScheduleActivity getInstance(){
        if (newScheduleActivity == null){
            newScheduleActivity = new NewScheduleActivity();
        }
        return newScheduleActivity;
    }

    public void init(IOSDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void clickBack(){
        back.click();
    }

    boolean trans_edit_del(Map<String, String> tranMap) {
        boolean bbtnDelete = btnDelete.getText().equalsIgnoreCase(tranMap.get("delete_an_appointment"));
        if (!bbtnDelete) {
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "NewSchedule", btnDelete.getText(),
                    tranMap.get("delete_an_appointment"), "fail");
        }
        return bbtnDelete;
    }

    private boolean staticUITranslation(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        boolean bTitle = title.getText().equalsIgnoreCase(tranMap.get("add_an_appointment"));
        if (!bTitle){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "NewSchedule", title.getText(),
                    tranMap.get("add_an_appointment"), "fail");
        }
        boolean bscheduleTask = scheduleTask.getText().equalsIgnoreCase(tranMap.get("appointment_task"));
        if (!bscheduleTask){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "NewSchedule", scheduleTask.getText(),
                    tranMap.get("appointment_task"), "fail");
        }
        boolean bnoSelected = noSelected.getText().equalsIgnoreCase(tranMap.get("choose_no_area"));
        if (!bnoSelected){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "NewSchedule", noSelected.getText(),
                    tranMap.get("choose_no_area"), "fail");
        }
        boolean bsetTime = setTime.getText().equalsIgnoreCase(tranMap.get("set_time"));
        if (!bsetTime){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "NewSchedule", setTime.getText(),
                    tranMap.get("set_time"), "fail");
        }
        boolean bstartTime = startTime.getText().equalsIgnoreCase(tranMap.get("start_time"));
        if (!bstartTime){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "NewSchedule", startTime.getText(),
                    tranMap.get("start_time"), "fail");
        }
        boolean brepeat = repeat.getText().equalsIgnoreCase(tranMap.get("appointment_frequency"));
        if (!brepeat){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "NewSchedule", repeat.getText(),
                    tranMap.get("appointment_frequency"), "fail");
        }

        String[] weekDays = {"zhouRi", "zhouYi", "zhouEr", "zhouSan", "zhouSi", "zhouWu", "zhouLiu"};
        int iIndex = Common.getInstance().getWeekIndex();
        boolean brepeatValue = repeatValue.getText().equalsIgnoreCase(tranMap.get(weekDays[iIndex]));
        if (!brepeatValue){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "NewSchedule", repeatValue.getText(),
                    tranMap.get(weekDays[iIndex]), "fail");
        }
        return bTitle && bscheduleTask && bnoSelected &&
                bsetTime && bstartTime && brepeat && brepeatValue;
    }

    private boolean appointTask(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        noSelected.click();
        boolean bareaAll= areaAll.getText().equalsIgnoreCase(tranMap.get("area_all_choose"));
        if (!bareaAll){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "NewSchedule", areaAll.getText(),
                    tranMap.get("area_all_choose"), "fail");
        }
        boolean bareaSingle= areaSingle.getText().equalsIgnoreCase(tranMap.get("area_single_choose"));
        if (!bareaSingle){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "NewSchedule", areaSingle.getText(),
                    tranMap.get("area_single_choose"), "fail");
        }
        noSelected.click();
        return bareaAll && bareaSingle;
    }

    private boolean startTimeTranslation(Map<String, String> tranMap){
        startTime.click();
        boolean bpromptStartTitle= promptStartTitle.getText().equalsIgnoreCase(tranMap.get("start_time"));
        if (!bpromptStartTitle){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "NewSchedule", promptStartTitle.getText(),
                    tranMap.get(tranMap.get("start_time")), "fail");
        }
        promptCancel.click();
        return true;
    }

    public void clickRepeat(){
        repeat.click();
    }

    public boolean translateWeekend(Map<String, String> tranMap){
        RepetitionActivity.getInstance().clickWeekOfDate(Common.getInstance().getWeekIndex());
        RepetitionActivity.getInstance().clickWeekOfDate(0);
        RepetitionActivity.getInstance().clickWeekOfDate(6);
        RepetitionActivity.getInstance().clickBack();
        boolean brepeatValue= repeatValue.getText().equalsIgnoreCase(tranMap.get("weekday"));
        if (!brepeatValue){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "NewSchedule", repeatValue.getText(),
                    tranMap.get("weekday"), "fail");
        }
        return brepeatValue;
    }

    public boolean translateWorkday(Map<String, String> tranMap){
        RepetitionActivity.getInstance().clickWeekOfDate(Common.getInstance().getWeekIndex());
        for(int i = 1; i < 6; i++){
            RepetitionActivity.getInstance().clickWeekOfDate(i);
        }
        RepetitionActivity.getInstance().clickBack();
        boolean brepeatValue = repeatValue.getText().equalsIgnoreCase(tranMap.get("workday"));
        if (!brepeatValue){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "NewSchedule", repeatValue.getText(),
                    tranMap.get("workday"), "fail");
        }
        return brepeatValue;
    }

    public boolean translateEveryday(Map<String, String> tranMap){
        RepetitionActivity.getInstance().clickWeekOfDate(Common.getInstance().getWeekIndex());
        for(int i = 0; i < 7; i++){
            RepetitionActivity.getInstance().clickWeekOfDate(i);
        }
        RepetitionActivity.getInstance().clickBack();
        boolean brepeatValue= repeatValue.getText().equalsIgnoreCase(tranMap.get("everyday_ios"));
        if (!brepeatValue){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "NewSchedule", repeatValue.getText(),
                    tranMap.get("everyday_ios"), "fail");
        }
        return brepeatValue;
    }

    public boolean translateSelectWeekOfDate(Map<String, String> tranMap){
        String[] weekDays = {"zhouRi", "zhouYi", "zhouEr", "zhouSan", "zhouSi", "zhouWu", "zhouLiu"};
        boolean brepeatValue[] = new boolean[7];
        //click week of date
        int iOldIndex = Common.getInstance().getWeekIndex();
        for(int i = 0; i < 7; i++){
            //click repeat
            repeat.click();
            //click pre date
            RepetitionActivity.getInstance().clickWeekOfDate(iOldIndex);
            RepetitionActivity.getInstance().clickWeekOfDate(i);
            RepetitionActivity.getInstance().clickBack();
            brepeatValue[i]= repeatValue.getText().equalsIgnoreCase(tranMap.get(weekDays[i]));
            if (!brepeatValue[i]){
                TranslateErrorReport.getInstance().insetNewLine(
                        tranMap.get("language"), "NewSchedule", repeatValue.getText(),
                        tranMap.get(weekDays[i]), "fail");
            }
            //recovery
            iOldIndex = i;
        }

        return brepeatValue[0] && brepeatValue[1] && brepeatValue[2] && brepeatValue[3] &&
                brepeatValue[4] && brepeatValue[5] && brepeatValue[6];
    }

    void addSchedule(){
        noSelected.click();
        areaAll.click();
        startTime.click();
        promptOK.click();
        confirmAdd.click();
    }

    public boolean translate(Map<String, String> tranMap){
        boolean bStatic = staticUITranslation(tranMap);
        boolean bappointTask = appointTask(tranMap);
        boolean bstartTimeTranslation = startTimeTranslation(tranMap);
        return bStatic && bappointTask && bstartTimeTranslation;
    }



}
