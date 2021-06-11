package com.bettercloud.companyDetails;

import com.bettercloud.base.TestBaseSetup;
import com.bettercloud.pages.CompanyDetailsPage;
import com.bettercloud.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class AboutBetterCloudTest extends TestBaseSetup {

    @BeforeMethod
    public void setUp(){
        initialization();
        HomePage.goToCompany();
    }

    @Test
    public void extractMemberData() {
        Assert.assertEquals(CompanyDetailsPage.getHeaderText(),"About BetterCloud");
        Assert.assertEquals(CompanyDetailsPage.getHeadingTag(),"h1");
        CompanyDetailsPage.goToBoard();
        CompanyDetailsPage.exportDataToCsv(CompanyDetailsPage.getBoardMembersNames());
    }

    @Test
    public void failTestForReportDemo(){
      Assert.assertTrue(false);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}
