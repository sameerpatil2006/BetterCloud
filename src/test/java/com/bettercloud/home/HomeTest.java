package com.bettercloud.home;

import com.bettercloud.base.TestBaseSetup;
import com.bettercloud.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.*;

public class HomeTest extends TestBaseSetup{

    HomePage homePage;

    @BeforeMethod
    public void setUp(){
        initialization();
        homePage = new HomePage();
    }

    @Test
    public void testHomePage(){
        Assert.assertTrue(homePage.isBetterCloudLogoVisible());
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}