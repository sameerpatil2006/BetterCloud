package com.bettercloud.companyDetails;

import com.bettercloud.base.TestBaseSetup;
import com.bettercloud.pages.CompanyDetailsPage;
import com.bettercloud.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author Sameer Patil
 * This is a test class for company details page, it has testmethod which calls method to
 * extracts board members from company page and store in csv file. It also one testmethod which fails
 * on purpose for demoing screenshot generation and extent report logging
 */
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

    /**
     * This test asserts title and also asserts that title is in h1 format. This test also calls
     * methods to extract and covert board members to CSV
     */
    @Test
    public void extractMemberData() throws InterruptedException {
        Assert.assertEquals(companyDetailsPage.getHeaderText(),"About BetterCloud");
        Assert.assertEquals(companyDetailsPage.getHeadingTag(),"h1");
        companyDetailsPage.goToBoard();
        companyDetailsPage.exportDataToCsv(companyDetailsPage.getBoardMembersNames());
    }

    /**
     * Failing this test on purpose to demo screenshots and extent report logging
     */
    @Test
    public void failTestForReportDemo(){
      Assert.assertTrue(false);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}
