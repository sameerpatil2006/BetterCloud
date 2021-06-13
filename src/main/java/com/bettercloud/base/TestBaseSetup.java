package com.bettercloud.base;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileReader;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author Sameer Patil
 * This is base class which reads properties from property file and initializes browser according to operating system.
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
            System.setProperty("webdriver.chrome.driver", getDriverLocation("chrome"));
            driver = new ChromeDriver();
        } else if (browserName.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", getDriverLocation("firefox"));
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIME, TimeUnit.SECONDS);
        driver.get(url);
    }

    /**
     * This method returns driver location according to operating system
     * on which test is running
     * @param browserType
     * @return
     */
    public static String getDriverLocation(String browserType) {
        String browserLocation =  System.getProperty("user.dir")+ "/drivers/";

        switch (browserType.toLowerCase()) {
            case "firefox":
                browserLocation += "geckodriver";
                break;

            case "chrome":
            default:
                browserLocation += "chromedriver";
                break;
        }
        if (getOperatingSystem().equals("windows")) {
            browserLocation += ".exe";
        }
        if (getOperatingSystem().equals("mac")) {
            browserLocation += "mac";
        }
        return browserLocation;
    }

    /**
     * This method returns operating system on which test is running
     * @return
     */
    private static String getOperatingSystem() {
        String operatingSystem = SystemUtils.OS_NAME;

        if (operatingSystem.toLowerCase().startsWith("windows")) {
            operatingSystem = "windows";
        } else if (operatingSystem.toLowerCase().startsWith("linux")) {
                operatingSystem = "linux";
            }
            else if (operatingSystem.toLowerCase().startsWith("mac")){
                operatingSystem = "mac";
            }
            else {
                System.out.println("This Operating system is not supported");
            }
        return operatingSystem;
    }
}