package com.ecovacs.test.activity;

import com.ecovacs.test.common.Common;
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
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[4]/UIAAlert[1]/UIACollectionView[1]/UIACollectionCell[2]")
    private IOSElement btnConfirm = null;

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

    public void clickConfirm(){
        btnConfirm.click();
    }
}
