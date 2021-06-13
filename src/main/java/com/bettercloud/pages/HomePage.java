package com.bettercloud.pages;

import com.bettercloud.base.TestBaseSetup;
import com.bettercloud.common.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Sameer Patil
 * This is a page class for Home Page, it provides diffeent webelements present on home page and functions
 * to navigate through that page
 */
public class HomePage extends TestBaseSetup {

    @FindBy(css = "a.site-header__logo")
    WebElement home;

    @FindBy(linkText = "Company")
    WebElement company;

    /**
     * Initializing the Page Objects
     */
    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    public boolean isBetterCloudLogoVisible(){
        CommonMethods.waitUntilVisible(explicit_wait_time,home);
        return home.isDisplayed();
    }

    public void goToCompany(){
        CommonMethods.waitUntilElementClickable(explicit_wait_time, company);
        company.click();
    }
}
