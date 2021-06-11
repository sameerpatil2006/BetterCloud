package com.bettercloud.pages;

import com.bettercloud.base.TestBaseSetup;
import com.bettercloud.common.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePage extends TestBaseSetup {
    private static WebElement home = driver.findElement(By.cssSelector("a.site-header__logo "));
    private static WebElement company = driver.findElement(By.linkText("Company"));

    public static boolean isBetterCloudLogoVisible(){
        CommonMethods.waitUntilVisible(explicit_wait_time,home);
        return home.isDisplayed();
    }

    public static void goToCompany(){
        CommonMethods.waitUntilElementClickable(explicit_wait_time, company);
        company.click();
    }
}
