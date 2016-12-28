package com.ecovacs.test.common;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ecosqa on 16/7/27.
 * description:common function
 */
public class Common {
    //parameter
    private static Logger logger = LoggerFactory.getLogger(Common.class);
    private static Common common = null;
    private FailType failType = null;


    //
    public enum FailType{
        NOT_REGISTER, //user not register
        ALREADY_REGISTER //user had registered
    }

    private Common(){

    }

    public static Common getInstance(){
        if(common == null){
            common = new Common();
        }
        return common;
    }

    public IOSDriver getDriver(){
        IOSDriver driver = null;
        // set up appium
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.VERSION, "9.2");
        capabilities.setCapability(MobileCapabilityType.ACCEPT_SSL_CERTS,true);
        capabilities.setCapability(MobileCapabilityType.PLATFORM, "Mac");
        capabilities.setCapability("deviceName","iphone 4s");
        capabilities.setCapability("platformName", "ios");
        capabilities.setCapability("newCommandTimeout", 0);
        try {
            driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        }catch (MalformedURLException e){
            e.printStackTrace();
            logger.info("exception: " + e.toString());
        }
        return driver;
    }

    boolean delAllFile(String path) {
        File file = new File(path);
        File temp;
            String[] tempList = file.list();
            if(tempList == null){
                return false;
            }
            for(String strFile:tempList){
                if (path.endsWith(File.separator)) {
                    temp = new File(path + strFile);
                } else {
                    temp = new File(path + File.separator + strFile);
                }
                if (temp.isFile()) {
                    if(!temp.delete()){
                        return false;
                    }
                }
            }
        return true;
    }

    public boolean screenShot(String strFileName, WebDriver driver){
        TakesScreenshot screen = (TakesScreenshot ) new Augmenter().augment(driver);
        String strPath = getClass().getResource("/").getPath()
                        + "../" + "screenShots/";
        //check
        File folder = new File(strPath);
        if(!folder.exists() && !folder.isDirectory()){
            if(!folder.mkdir()){
                return false;
            }
        }else {
            delAllFile(strPath);
        }
        File ss = new File(strPath + strFileName);
        return screen.getScreenshotAs(OutputType.FILE).renameTo(ss);
    }

    public void waitForSecond(int iMillSecond){
        try {
            Thread.sleep(iMillSecond);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void goBack(AppiumDriver driver, int iLoop){
        for(int i = 0; i < iLoop; i++){
            driver.navigate().back();
            waitForSecond(300);
        }

    }

    /*private boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            if(children == null){
                return false;
            }
            //recursion delete subfolder
            for(String strFile:children) {
                boolean success = deleteDir(new File(dir, strFile));
                if (!success) {
                    return false;
                }
            }
        }
        //delete empty folder or file
        return dir.delete();
    }*/

    public String executeCommand(String command) {

        StringBuilder output = new StringBuilder();
        Process process;
        try {
            //ProcessBuilder pb = new ProcessBuilder(command);
            process = Runtime.getRuntime().exec(command);
            //process = pb.start();
            process.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine())!= null) {
                output.append(line);
                output.append("\n");
                System.out.println(output.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public boolean showActivity(WebElement element){
        boolean bResult = false;
        int iLoop = 0;
        while (true) {
            if(iLoop > 50){
                break;
            }
            try {
                if (element.isEnabled()) {
                    bResult = true;
                    //logger.info("Show activity!!!");
                    break;
                }
            } catch (Exception e) {
                //logger.error(e.toString());
                logger.info("Not show activity, try again!!!");
                waitForSecond(500);
            }
            iLoop++;
        }
        return bResult;
    }

    public FailType getFailType(){
        return failType;
    }

    public void setFailType(FailType type){
        failType = type;
    }

}
