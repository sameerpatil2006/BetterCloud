package com.bettercloud.home;

import com.bettercloud.base.TestBaseSetup;
import com.bettercloud.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author Sameer Patil
 * This test class asserts that Homepage logo is visible.
 */
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