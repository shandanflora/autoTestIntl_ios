package com.ecovacs.test.activity;

import com.ecovacs.test.common.Common;
import com.ecovacs.test.common.TranslateErrorReport;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;

/**
 * Created by ecosqa on 17/2/18.
 * time schedule activity
 */
public class TimeScheduleActivity {
    private static TimeScheduleActivity timeScheduleActivity = null;
    private IOSDriver driver = null;

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAStaticText[1]")
    private MobileElement title = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]")
    private MobileElement back = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[1]")
    private MobileElement emptyList = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[1]")
    private MobileElement btnAddTime = null;
    //new time schedule
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[2]")
    private MobileElement textTask = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[3]")
    private MobileElement textDate = null;
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAButton[1]")
    private MobileElement btnDelete = null;

    private enum DIRECTION{
        RIGHT_TO_LEFT,
        LEFT_TO_RIGHT
    }

    private TimeScheduleActivity(){

    }

    public static TimeScheduleActivity getInstance(){
        if (timeScheduleActivity == null){
            timeScheduleActivity = new TimeScheduleActivity();
        }
        return timeScheduleActivity;
    }

    public void init(IOSDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.driver = driver;
    }

    public void clickBack(){
        back.click();
    }

    public boolean showActivity(){
        return Common.getInstance().showActivity(btnAddTime);
    }

    private boolean staticUITranslate(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        boolean bTitle = title.getText().equalsIgnoreCase(tranMap.get("Time_to_make_an_appointment"));
        if (!bTitle){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "TimeSchedule", title.getText(),
                    tranMap.get("Time_to_make_an_appointment"), "fail");
        }
        boolean bemptyList = emptyList.getText().equalsIgnoreCase(tranMap.get("No_list_of_the_current_period"));
        if (!bemptyList){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "TimeSchedule", emptyList.getText(),
                    tranMap.get("No_list_of_the_current_period"), "fail");
        }
        boolean bbtnAddTime = btnAddTime.getText().equalsIgnoreCase(tranMap.get("add_an_appointment"));
        if (!bbtnAddTime){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "TimeSchedule", btnAddTime.getText(),
                    tranMap.get("add_an_appointment"), "fail");
        }
        return bTitle && bemptyList && bbtnAddTime;
    }

    public boolean translate(Map<String, String> tranMap){
        boolean bStatic = staticUITranslate(tranMap);
        boolean bAdd = translateAddNewSchedule(tranMap);
        boolean bEdit = translateDel_Edit(tranMap);
        boolean bSwipe = translateDel_Swipe(tranMap);
        clickDel();
        return bStatic && bAdd && bEdit && bSwipe;
    }

    public void clickAddSchedule(){
        btnAddTime.click();
    }

    private boolean translateAddNewSchedule(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        btnAddTime.click();
        NewScheduleActivity.getInstance().addSchedule();
        Common.getInstance().showActivity(textTask);
        boolean btextTask = textTask.getText().equalsIgnoreCase(tranMap.get("area_all_choose"));
        if (!btextTask){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "TimeSchedule", textTask.getText(),
                    tranMap.get("area_all_choose"), "fail");
        }
        String[] weekDays = {"zhouRi", "zhouYi", "zhouEr", "zhouSan", "zhouSi", "zhouWu", "zhouLiu"};
        int iOldIndex = Common.getInstance().getWeekIndex();
        boolean btextDate = textDate.getText().equalsIgnoreCase(tranMap.get(weekDays[iOldIndex]));
        if (!btextDate){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "TimeSchedule", textDate.getText(),
                    tranMap.get(weekDays[iOldIndex]), "fail");
        }
        return btextTask && btextDate;
    }

    private void swipeNewTask(DIRECTION direction){
        //swipe
        Point point = textTask.getLocation();
        Dimension dimension = textTask.getSize();
        int iRectX = point.getX();
        int iRectY = point.getY();
        int iWidth = dimension.getWidth();
        int iHeight = dimension.getHeight();

        point.x = iRectX + iWidth/2;
        point.y = iRectY + iHeight/2;

        if (direction == DIRECTION.RIGHT_TO_LEFT){
            driver.swipe(point.x + iWidth/4, point.y ,
                    point.x - iWidth/4, point.y, 100);
        }else if(direction == DIRECTION.LEFT_TO_RIGHT){
            driver.swipe(point.x - iWidth/4, point.y ,
                    point.x + iWidth/4, point.y, 100);
        }
    }

    private void clickDel(){
        btnDelete.click();
    }

    private boolean translateDel_Swipe(Map<String, String> tranMap){
        swipeNewTask(DIRECTION.RIGHT_TO_LEFT);
        boolean bbtnDelete = btnDelete.getText().equalsIgnoreCase(tranMap.get("delete"));
        if (!bbtnDelete){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "TimeSchedule", btnDelete.getText(),
                    tranMap.get("delete"), "fail");
        }
        return bbtnDelete;
    }

    private boolean translateDel_Edit(Map<String, String> tranMap){
        textTask.click();
        boolean bRes = NewScheduleActivity.getInstance().trans_edit_del(tranMap);
        NewScheduleActivity.getInstance().clickBack();
        return bRes;
    }


}
