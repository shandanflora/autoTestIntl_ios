package com.ecovacs.test;

import com.ecovacs.test.activity.*;
import com.ecovacs.test.common.Common;
import io.appium.java_client.ios.IOSDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;

/**
 * Created by ecosqa on 16/11/30.
 * handle international app
 */
class HandleIntl {
    private static Logger logger = LoggerFactory.getLogger(HandleIntl.class);
    private static HandleIntl handleIntl = null;
    private IOSDriver driver = null;

    private HandleIntl(){

    }

    public static HandleIntl getInstance(){
        if(handleIntl == null){
            handleIntl = new HandleIntl();
        }
        return handleIntl;
    }

    void initAppium(){
        IOSDriver driver = Common.getInstance().getDriver();
        if(driver == null){
            return;
        }
        HandleIntl.getInstance().init(driver);
    }

    public void init(IOSDriver driver){
        this.driver = driver;
        CountrySelectActivity.getInstance().init(driver);
        RegisterActivity.getInstance().init(driver);
        WelcomeActivity.getInstance().init(driver);
        LoginActivity.getInstance().init(driver);
        ForgetPassActivity.getInstance().init(driver);
        MainActivity.getInstance().init(driver);
        MoreActivity.getInstance().init(driver);
        RetrievePassActivity.getInstance().init(driver);
    }

    boolean enterWelcomeActivity(){
        if (!WelcomeActivity.getInstance().showWelcomeActivity()) {
            logger.error("Can not show welcome activity!!!");
            return false;
        }
        return true;
    }

    private boolean enterRegisterActivity(){
        WelcomeActivity.getInstance().clickRegister();
        if (!RegisterActivity.getInstance().showRegisterActivity()) {
            logger.error("Can not show register activity!!!");
            return false;
        }
        logger.info("Show register activity!!!");
        return true;
    }

    /**
     * verify email
     * @param strJar jar file
     * @param strCountry country
     * @param strMail email
     * @param strType handle type, ex:Register
     */

    private void verifyEmail(String strJar, String strCountry, String strMail, String strType){
        String strPath = RegisterActivity.class.getResource("/").getPath();
        strPath = strPath + "../../" + strJar;
        File fileApp = new File(strPath);
        logger.info(strPath);
        Common.getInstance().executeCommand("java -jar " + fileApp.getName() + " " + strCountry + " " + strMail + " " + strType);
        logger.info("********exec command finished!!!");
    }

    /*public void goBack(int iNum){
        Common.getInstance().goBack(androidDriver, iNum);
    }*/

    boolean registerAndLogin(String strCountry, String strEmailType, String strEmail, String strPass){
        //register
        if(!enterRegisterActivity()){
            return false;
        }
        if (!RegisterActivity.getInstance().fill_Screenshot_Click(strCountry, strEmail, strPass)) {
            logger.error("Register failed!!! country--" + strCountry);
            return false;
        }
        if(!RetrievePassActivity.getInstance().showRetrieveConfirmActivity()){
            Common.getInstance().setFailType(Common.FailType.ALREADY_REGISTER);
            Common.getInstance().goBack(driver, 1);
            logger.error("Not show Retrieve confirm activity!!!");
            return false;
        }
        logger.info("Show active email activity!!!");
        //verify email
        verifyEmail("VerifyEmail.one-jar.jar", strCountry, strEmailType, "Register");
        //check--login with new password
        RetrievePassActivity.getInstance().clickLogin();
        if (!loginWithoutWelcome(strCountry, strEmail, strPass)) {
            logger.info("Login failed after forget password country- " + strCountry);
            return false;
        }
        if (!logout()) {
            logger.info("Logout failed after forget password country- " + strCountry);
            Common.getInstance().goBack(driver, 1);
            return false;
        }
        return true;
    }

    private boolean enterLoginAcivity(){
        WelcomeActivity.getInstance().clickLogin();
        if (!LoginActivity.getInstance().showLoginActivity()) {
            logger.info("Not show login activity!!!");
            return false;
        }
        logger.info("Show login activity!!!");
        return true;
    }

    boolean forgetPassword(String strCountry, String strEmailType, String strEmail, String strPass){
        if(!enterLoginAcivity()){
            return false;
        }
        LoginActivity.getInstance().clickForgetPass();
        logger.info("Click forget password!!!");
        if (!ForgetPassActivity.getInstance().showActivity()) {
            Common.getInstance().goBack(driver, 1);
            logger.error("Not show forget password activity!!!");
            return false;
        }
        if (!ForgetPassActivity.getInstance().sendEmail(strCountry, strEmail)) {
            Common.getInstance().goBack(driver, 2);
            Common.getInstance().setFailType(Common.FailType.NOT_REGISTER);
            logger.error("Not show retrieve password activity!!!");
            return false;
        }
        logger.info("Click send verify email!!!");
        if (!RetrievePassActivity.getInstance().showRetrieveConfirmActivity()) {
            logger.error("Not show retrieve password activity!!!");
            Common.getInstance().goBack(driver, 2);
            return false;
            //invalid email
        }
        logger.info("Show retrieve password activity!!!");
        verifyEmail("VerifyEmail.one-jar.jar", strCountry, strEmailType, "DoFindPass");
        //check--login with new password
        RetrievePassActivity.getInstance().clickLogin();
        if (!loginWithoutWelcome(strCountry, strEmail, strPass)) {
            logger.info("Login failed after forget password country- " + strCountry);
            Common.getInstance().goBack(driver, 1);
            return false;
        }
        if (!logout()) {
            logger.info("Logout failed after forget password country- " + strCountry);
            return false;
        }
        return true;
    }

    private boolean loginWithoutWelcome(String strCountry, String strEmail, String strPass){
        if(!LoginActivity.getInstance().showLoginActivity()){
            logger.error("Can not show login activity!!!");
            return false;
        }
        logger.info("Show login activity!!!");
        LoginActivity.getInstance().clickCountry();
        if(!CountrySelectActivity.getInstance().selectCountry(strCountry)){
            return false;
        }
        logger.info("Finished to select country!!!");
        LoginActivity.getInstance().fillInfoAndClick(strEmail, strPass);
        logger.info("Finished to click login!!!");
        if(!MainActivity.getInstance().showActivity()){
            logger.info("Can not show robot list activity!!!");
            return false;
        }
        logger.info("login successfully country- " + strCountry);
        return true;
    }

    /*private boolean login(String strCountry, String strEmail, String strPass){
        if(!WelcomeActivity.getInstance().showWelcomeActivity()){
            logger.error("Can not show welcome activity!!!");
            return false;
        }
        WelcomeActivity.getInstance().clickLogin();
        logger.info("Click login in welcome activity!!!");
        return loginWithoutWelcome(strCountry, strEmail, strPass);
    }*/

    private boolean logout(){
        MainActivity.getInstance().clickMore();
        if(!MoreActivity.getInstance().showMoreActivity()){
            logger.info("Can not show more activity!!!");
            return false;
        }
        MoreActivity.getInstance().clickLogout();
        return WelcomeActivity.getInstance().showWelcomeActivity();
    }

    /*public boolean loginAndLogout(String strCountry, String strEmail, String strPass){
        if(!login(strCountry, strEmail, strPass)){
            logger.info("login failed country- " + strCountry);
            return false;
        }
        if(!logout()){
            logger.info("logout successfully country- " + strCountry);
            return false;
        }
        return true;
    }*/
}
