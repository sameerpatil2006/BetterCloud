package com.bettercloud.common;

import com.bettercloud.base.TestBaseSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;

public class CommonMethods extends TestBaseSetup {

    private static WebDriverWait wait;

    public static void waitUntilVisible(int secondsToWait, WebElement webElement) {
        wait = new WebDriverWait(driver,secondsToWait);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public static void waitUntilElementClickable(int secondsToWait, WebElement webElement){
        wait = new WebDriverWait(driver,secondsToWait);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public static void waitUntilElementClickable(String webElement, int secondsToWait) {
        if (webElement != null) {
            FluentWait<WebDriver> w = new FluentWait<>(driver)
                    .ignoring(NoSuchElementException.class)
                    .withTimeout(Duration.ofSeconds(secondsToWait));
            w.until(ExpectedConditions.elementToBeClickable(By.cssSelector(webElement)));
        }
    }

    public static void scrollToElement(WebElement webElement) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", webElement);
    }
}
