package com.bettercloud.common;

import com.bettercloud.base.TestBaseSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.NoSuchElementException;

/**
 * @author Sameer Patil
 * This method provides common methods which are common through out project
 */
public class CommonMethods extends TestBaseSetup {

    private static WebDriverWait wait;

    /**
     * Method to wait for element to be visible
     * @param secondsToWait
     * @param webElement
     */
    public static void waitUntilVisible(int secondsToWait, WebElement webElement) {
        wait = new WebDriverWait(driver,secondsToWait);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    /**
     * Method to wait for element to be clickable
     * @param secondsToWait
     * @param webElement
     */
    public static void waitUntilElementClickable(int secondsToWait, WebElement webElement){
        wait = new WebDriverWait(driver,secondsToWait);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    /**
     * Method to wait for element to be clickable using fluentwait
     * @param webElement
     * @param secondsToWait
     */
    public static void waitUntilElementClickable(String webElement, int secondsToWait) {
        if (webElement != null) {
            FluentWait<WebDriver> w = new FluentWait<>(driver)
                    .ignoring(NoSuchElementException.class)
                    .withTimeout(Duration.ofSeconds(secondsToWait));
            w.until(ExpectedConditions.elementToBeClickable(By.cssSelector(webElement)));
        }
    }

    /**
     * Method to scroll focus of page where element is present
     * @param webElement
     */
    public static void scrollToElement(WebElement webElement) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", webElement);
    }

    /**
     * Method to get current time stamp
     * @return
     */
    public static String getTimeStamp() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        return formatter.format(date);
    }
}
