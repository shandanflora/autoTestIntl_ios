package com.ecovacs.test;

import com.ecovacs.test.common.Common;
import com.ecovacs.test.common.PropertyData;
import io.appium.java_client.android.AndroidDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by ecosqa on 16/11/30.
 *
 */
public class TestIntl {
    private AndroidDriver driver = null;

    @BeforeClass
    public void setUp(){
        driver = Common.getInstance().getDriver();
        if(driver == null){
            return;
        }
        HandleIntl.getInstance().init(driver);
    }

    @AfterClass
    public void tearDown(){

    }

    @Test
    public void testRegisterAndLogin(){
        //Assert.assertTrue(HandleIntl.getInstance().RegisterAndLogin());
    }
    /*
    @Test(enabled = false)
    public void testForgetPass(){
        Assert.assertTrue(HandleIntl.getInstance()
                .forgetPassword("Japan", PropertyData.getProperty("hotmail_email")
                        , PropertyData.getProperty("login_pass")));
    }

    @Test(enabled = false)
    public void testLogin(){
        Assert.assertTrue(HandleIntl.getInstance()
                .loginAndLogout("Japan", PropertyData.getProperty("hotmail_email")
                        , PropertyData.getProperty("login_pass")));
    }*/
}
