package com.bettercloud.home;

import com.bettercloud.base.TestBaseSetup;
import com.bettercloud.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.*;

public class HomeTest extends TestBaseSetup{
    @BeforeMethod
    public void setUp(){
        initialization();
    }

    @Test
    public void testHomePage(){
        Assert.assertTrue(HomePage.isBetterCloudLogoVisible());
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}