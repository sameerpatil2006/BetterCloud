package com.bettercloud.companyDetails;

import com.bettercloud.base.TestBaseSetup;
import com.bettercloud.pages.CompanyDetailsPage;
import com.bettercloud.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class AboutBetterCloudTest extends TestBaseSetup {

    HomePage homePage;
    CompanyDetailsPage companyDetailsPage;

    @BeforeMethod
    public void setUp(){
        initialization();
        homePage = new HomePage();
        companyDetailsPage = new CompanyDetailsPage();
        homePage.goToCompany();
    }

    @Test
    public void extractMemberData() {
        Assert.assertEquals(companyDetailsPage.getHeaderText(),"About BetterCloud");
        Assert.assertEquals(companyDetailsPage.getHeadingTag(),"h1");
        companyDetailsPage.goToBoard();
        companyDetailsPage.exportDataToCsv(companyDetailsPage.getBoardMembersNames());
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
