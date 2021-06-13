package com.bettercloud.base;

import com.bettercloud.utility.WebEventListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.FileReader;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBaseSetup {

    public static WebDriver driver;
    private static final int IMPLICIT_WAIT_TIME = 5;
    public static int explicit_wait_time = 20;
    public static String url;
    public static String browsername;

    public TestBaseSetup()  {
       try{
           FileReader reader=new FileReader(System.getProperty("user.dir")+ "/src/main/java/com/bettercloud/properties/configs.properties");
           Properties prop = new Properties();
           prop.load(reader);
           browsername = prop.getProperty("browser");
           url = prop.getProperty("url");
       }catch (Exception ex){
            ex.printStackTrace();
       }
    }

    public static void initialization() {

        if (browsername.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
            driver = new ChromeDriver();
        } else if (browsername.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", "drivers/geckodriver");
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIME, TimeUnit.SECONDS);
        driver.get(url);
    }
}