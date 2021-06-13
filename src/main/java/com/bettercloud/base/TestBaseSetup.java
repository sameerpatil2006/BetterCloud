package com.bettercloud.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileReader;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author Sameer Patil
 * This is base class which reads properties from property file and initializes browser.
 * Currently this class supports running from linux machine only. Running on windows machine might throw file not found exception.
 */
public class TestBaseSetup {

    public static WebDriver driver;
    private static final int IMPLICIT_WAIT_TIME = 5;
    public static int explicit_wait_time = 20;
    public static String url;
    public static String browserName;

    /**
     * This method reads property file and assign values to variables
     */
    public TestBaseSetup()  {
       try{
           FileReader reader=new FileReader(System.getProperty("user.dir")+ "/src/main/java/com/bettercloud/properties/configs.properties");
           Properties prop = new Properties();
           prop.load(reader);
           browserName = prop.getProperty("browser");
           url = prop.getProperty("url");
       }catch (Exception ex){
            ex.printStackTrace();
       }
    }

    /**
     * This method initializes webdriver
     */
    public static void initialization() {

        if (browserName.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
            driver = new ChromeDriver();
        } else if (browserName.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", "drivers/geckodriver");
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIME, TimeUnit.SECONDS);
        driver.get(url);
    }
}