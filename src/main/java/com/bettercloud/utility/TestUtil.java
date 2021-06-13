package com.bettercloud.utility;

import com.bettercloud.base.TestBaseSetup;
import com.bettercloud.common.CommonMethods;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

/**
 * @author Sameer Patil
 * This class screenshots in jpeg format and stores them in screenshots folder under project
 * and names screenshot with timestamp
 */
public class TestUtil extends TestBaseSetup {

	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + "ScreenShot_" + CommonMethods.getTimeStamp() +".jpeg"));
	}
}
