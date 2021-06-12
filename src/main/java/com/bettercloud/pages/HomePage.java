package com.bettercloud.pages;

import com.bettercloud.base.TestBaseSetup;
import com.bettercloud.common.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends TestBaseSetup {

    @FindBy(css = "a.site-header__logo")
    WebElement home;

    @FindBy(linkText = "Company")
    WebElement company;

    // Initializing the Page Objects:
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
