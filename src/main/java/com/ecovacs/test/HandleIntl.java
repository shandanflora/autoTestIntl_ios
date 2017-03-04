package com.ecovacs.test;

import com.ecovacs.test.activity.*;
import com.ecovacs.test.common.*;
import com.ecovacs.test.common.TranslateIntl;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by ecosqa on 16/11/30.
 * handle international app
 */
class HandleIntl {
    private static Logger logger = LoggerFactory.getLogger(HandleIntl.class);
    private static HandleIntl handleIntl = null;
    private IOSDriver driver = null;
    private Map<String, String> languageMap = null;

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
        AboutActivity.getInstance().init(driver);
        ChangePassActivity.getInstance().init(driver);
        ContinueCleanActivity.getInstance().init(driver);
        CountrySelectActivity.getInstance().init(driver);
        FirmVerActivity.getInstance().init(driver);
        ForgetPassActivity.getInstance().init(driver);
        FullScreenActivity.getInstance().init(driver);
        LanguageActivity.getInstance().init(driver);
        LoginActivity.getInstance().init(driver);
        MainActivity.getInstance().init(driver);
        MoreActivity.getInstance().init(driver);
        NewScheduleActivity.getInstance().init(driver);
        RegisterActivity.getInstance().init(driver);
        RepetitionActivity.getInstance().init(driver);
        ResetMapActivity.getInstance().init(driver);
        RetrievePassActivity.getInstance().init(driver);
        SettingActivity.getInstance().init(driver);
        TimeScheduleActivity.getInstance().init(driver);
        UnibotCleanActivity.getInstance().init(driver);
        UserAgreeActivity.getInstance().init(driver);
        VirtualWallActivity.getInstance().init(driver);
        VoiceReportActivity.getInstance().init(driver);
        WelcomeActivity.getInstance().init(driver);
        WorkLogActivity.getInstance().init(driver);
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

    private boolean login(String strCountry, String strEmail, String strPass){
        if(!WelcomeActivity.getInstance().showWelcomeActivity()){
            logger.error("Can not show welcome activity!!!");
            return false;
        }
        WelcomeActivity.getInstance().clickLogin();
        logger.info("Click login in welcome activity!!!");
        return loginWithoutWelcome(strCountry, strEmail, strPass);
    }

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

    public void changeLanguage(String strLanguage){
        //return deebot clean
        SettingActivity.getInstance().clickBack();
        //return main
        UnibotCleanActivity.getInstance().clickBack();
        /*if(!login("Japan", PropertyData.getProperty("hotmail_email"), PropertyData.getProperty("login_pass"))){
            logger.error("login failed!!!");
            return;
        }*/
        MainActivity.getInstance().showActivity();
        MainActivity.getInstance().clickMore();
        MoreActivity.getInstance().clickLanguage();
        LanguageActivity.getInstance().selectLanguage(strLanguage);
        //return main
        MoreActivity.getInstance().clickBack();
        MainActivity.getInstance().showActivity();
        if(!logout()){
            logger.info("logout failed!!!");
        }
    }

    void translateErrorReport_init(){
        List<String> list = JsonParse.getJsonParse().readDataFromJson("country.json", "sheets");
        TranslateErrorReport.getInstance().init(list);
    }

    void translate_init(String strColName){
        Map<String, String> tranMap = TranslateIntl.getInstance().readExcel("Translate.xlsx", strColName);
        if(tranMap.isEmpty()){
            logger.error("The language map is empty!!!");
            return;
        }
        languageMap = tranMap;
    }

    boolean translateWelcome(){
        return WelcomeActivity.getInstance().translate(languageMap);
    }

    boolean translateLogin(){
        WelcomeActivity.getInstance().clickLogin();
        LoginActivity.getInstance().showLoginActivity();
        boolean bRes = LoginActivity.getInstance().translate(languageMap);
        LoginActivity.getInstance().clickBack();
        return bRes;
    }

    boolean translateRegister(){
        WelcomeActivity.getInstance().clickRegister();
        RegisterActivity.getInstance().showRegisterActivity();
        boolean bRes = RegisterActivity.getInstance().translate(languageMap);
        RegisterActivity.getInstance().clickBack();
        return bRes;
    }

    boolean translateForget(){
        WelcomeActivity.getInstance().clickLogin();
        LoginActivity.getInstance().showLoginActivity();
        LoginActivity.getInstance().clickForgetPass();
        ForgetPassActivity.getInstance().showActivity();
        boolean bRes = ForgetPassActivity.getInstance().translate(languageMap);
        ForgetPassActivity.getInstance().clickBack();
        LoginActivity.getInstance().clickBack();
        return bRes;
    }

    boolean translateMain(){
        login("Japan", PropertyData.getProperty("hotmail_email"), PropertyData.getProperty("login_pass"));
        //
        MainActivity.getInstance().showActivity();
        //
        return MainActivity.getInstance().translate(languageMap);
    }

    boolean translateMore(){
        MainActivity.getInstance().clickMore();
        boolean bTranlate = MoreActivity.getInstance().translate(languageMap);
        //MoreActivity.getInstance().clickBack();
        return bTranlate;
    }

    boolean translateAbout(){
        //MainActivity.getInstance().clickMore();
        MoreActivity.getInstance().clickAbout();
        boolean bTrans =  AboutActivity.getInstance().staticUITranslate(languageMap);
        AboutActivity.getInstance().clickBack();
        //MoreActivity.getInstance().clickBack();
        return bTrans;
    }

    boolean translateUserAgree(){
        //MainActivity.getInstance().clickMore();
        MoreActivity.getInstance().clickAbout();
        AboutActivity.getInstance().clickUserAgree();
        boolean bTrans =  UserAgreeActivity.getInstance().staticUITranslate(languageMap);
        UserAgreeActivity.getInstance().clickBack();
        AboutActivity.getInstance().clickBack();
        //MoreActivity.getInstance().clickBack();
        return bTrans;
    }

    boolean translateLanguage(){
        //MainActivity.getInstance().clickMore();
        MoreActivity.getInstance().clickLanguage();
        boolean bRes = LanguageActivity.getInstance().staticUITranslation(languageMap);
        LanguageActivity.getInstance().clickBack();
        //MoreActivity.getInstance().clickBack();
        return bRes;
    }

    boolean translateChangePass(){
        //MainActivity.getInstance().clickMore();
        MoreActivity.getInstance().clickChangePass();
        ChangePassActivity.getInstance().showActivity();
        boolean bTrans =  ChangePassActivity.getInstance().translate(languageMap);
        ChangePassActivity.getInstance().clickBack();
        MoreActivity.getInstance().clickBack();
        return bTrans;
    }

    boolean translateUnibotClean(){
        MainActivity.getInstance().showActivity();
        MainActivity.getInstance().clickDR95();
        UnibotCleanActivity.getInstance().showHaveMapActivity();
        //UnibotCleanActivity.getInstance().showText("-");
        return UnibotCleanActivity.getInstance().translate(languageMap);
    }

    boolean translateFullScreen(){
        ////delete
        //MainActivity.getInstance().clickDR95();
        //UnibotCleanActivity.getInstance().showHaveMapActivity();
        /////
        UnibotCleanActivity.getInstance().clickFullScreen();
        boolean bRes = FullScreenActivity.getInstance().staticUITranslation(languageMap);
        FullScreenActivity.getInstance().clickBack();
        return bRes;
    }

    boolean translateVirtual(){
        UnibotCleanActivity.getInstance().clickFullScreen();
        FullScreenActivity.getInstance().clickVirtual();
        boolean bRes = VirtualWallActivity.getInstance().staticUITranslation(languageMap);
        VirtualWallActivity.getInstance().clickCancel();
        FullScreenActivity.getInstance().clickBack();
        return bRes;
    }

    boolean translateUnibotSetting(){
        //will delete
        //MainActivity.getInstance().clickDR95();
        //
        UnibotCleanActivity.getInstance().showHaveMapActivity();
        UnibotCleanActivity.getInstance().clickSetting();
        return SettingActivity.getInstance().staticUiTranslate(languageMap);
    }

    boolean translateWorkLog(){
        //after check return to setting
        SettingActivity.getInstance().clickWorkLog();
        boolean bResult = WorkLogActivity.getInstance().translate(languageMap);
        WorkLogActivity.getInstance().clickBack();
        return bResult;
    }

    boolean translateContinueClean(){
        //after check return to setting
        SettingActivity.getInstance().clickContinuedClean();
        ContinueCleanActivity.getInstance().showActivity();
        boolean bResult = ContinueCleanActivity.getInstance().translate(languageMap);
        ContinueCleanActivity.getInstance().clickBack();
        return bResult;
    }

    boolean translateVoiceReport(){
        //after check return to setting
        SettingActivity.getInstance().clickLanguage();
        boolean bResult = VoiceReportActivity.getInstance().staticUITranslation(languageMap);
        VoiceReportActivity.getInstance().clickBack();
        //SettingActivity.getInstance().clickBack();
        return bResult;
    }

    boolean translateFirmVer(){
        //start click more
        //MainActivity.getInstance().clickMore();
        SettingActivity.getInstance().clickFirmware();
        boolean bResult = FirmVerActivity.getInstance().staticUITranslation(languageMap);
        FirmVerActivity.getInstance().clickBack();
        return bResult;
    }

    boolean translateResetMap(){
        SettingActivity.getInstance().clickReset();
        ResetMapActivity.getInstance().showActivity();
        boolean bResult = ResetMapActivity.getInstance().translate(languageMap);
        ResetMapActivity.getInstance().clickBack();
        return bResult;
    }

    boolean translateTimeSchedule(){
        SettingActivity.getInstance().clickTimeSchedule();
        TimeScheduleActivity.getInstance().clickAddSchedule();
        boolean bResult = NewScheduleActivity.getInstance().translate(languageMap);
        return bResult;
    }

    boolean translateNewTimeTranslation(){
        //will delete
        //SettingActivity.getInstance().clickTimeSchedule();
        //
        boolean bResult = TimeScheduleActivity.getInstance().translate(languageMap);
        TimeScheduleActivity.getInstance().clickBack();
        return bResult;
    }

    boolean translateRepetition(){
        NewScheduleActivity.getInstance().clickRepeat();
        boolean bRes = RepetitionActivity.getInstance().staticUITranslation(languageMap);
        RepetitionActivity.getInstance().clickBack();
        NewScheduleActivity.getInstance().clickBack();
        return bRes;
    }

    boolean translateSelectWeekOfDate(){
        TimeScheduleActivity.getInstance().clickAddSchedule();
        //NewScheduleActivity.getInstance().clickRepeat();
        boolean bResult = NewScheduleActivity.getInstance().translateSelectWeekOfDate(languageMap);
        NewScheduleActivity.getInstance().clickBack();
        return bResult;
    }

    boolean translateSelectWeekend(){
        TimeScheduleActivity.getInstance().clickAddSchedule();
        NewScheduleActivity.getInstance().clickRepeat();
        boolean bResult = NewScheduleActivity.getInstance().translateWeekend(languageMap);
        NewScheduleActivity.getInstance().clickBack();
        return bResult;
    }

    boolean translateSelectWorkday(){
        TimeScheduleActivity.getInstance().clickAddSchedule();
        NewScheduleActivity.getInstance().clickRepeat();
        boolean bResult = NewScheduleActivity.getInstance().translateWorkday(languageMap);
        NewScheduleActivity.getInstance().clickBack();
        return bResult;
    }

    boolean translateSelectEveryday(){
        TimeScheduleActivity.getInstance().clickAddSchedule();
        NewScheduleActivity.getInstance().clickRepeat();
        boolean bResult = NewScheduleActivity.getInstance().translateEveryday(languageMap);
        NewScheduleActivity.getInstance().clickBack();
        return bResult;
    }
}
