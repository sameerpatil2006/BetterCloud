package com.bettercloud.pages;

import com.bettercloud.base.TestBaseSetup;
import com.bettercloud.common.CommonMethods;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Sameer Patil
 * This is a page class for Company Details Page, it provides diffeent webelements present on company page and functions
 * to navigate through that page
 */
public class CompanyDetailsPage extends TestBaseSetup {

    @FindBy(xpath = "//*[contains(text(),'About BetterCloud')]")
    WebElement aboutHeaderTag;

    @FindBy(css = "div.company-marquee>div.content>div.header")
    WebElement aboutHeader;

    @FindBy(css = "div.horizontal-tabs div.tab-list__inner>div:nth-of-type(2)")
    WebElement board;

    @FindBy(css = "div.our-awards__inner")
    WebElement ourAwards;

    @FindBy(css = "div.board-members>div:nth-of-type(6) div.profile__social")
    WebElement lastBoardMember;

    /**
     * Initializing the Page Objects
     */
    public CompanyDetailsPage() {
        PageFactory.initElements(driver, this);
    }

    public String getHeaderText(){
        CommonMethods.waitUntilVisible(explicit_wait_time,aboutHeader);
        return aboutHeader.getText();
    }

    /**
     * Go to Board page and wait for element to load
     */
    public void goToBoard() {
        CommonMethods.waitUntilElementClickable(explicit_wait_time, board);
        board.click();
        CommonMethods.scrollToElement(ourAwards);
        CommonMethods.waitUntilElementClickable("div.board-members>div:nth-of-type(6) div.profile__social",explicit_wait_time);
    }

    public String getHeadingTag(){
        CommonMethods.waitUntilVisible(explicit_wait_time,aboutHeaderTag);
        return aboutHeaderTag.getTagName();
    }

    /**
     * This method collects name and description of all the board members present on page and stores in LinkedHashMap.
     * Using different fluent waits for dynamic loading of page, avoiding thread.sleep which will halt complete execution
     * @return nameDescriptionMap
     */
    public LinkedHashMap<String, String> getBoardMembersNames() {
        LinkedHashMap<String, String> nameDescriptionMap = new LinkedHashMap<>();
        CommonMethods.scrollToElement(lastBoardMember);
        CommonMethods.waitUntilElementClickable("div.board-members>div:nth-of-type(1)",explicit_wait_time);
        List<WebElement> profileNames = driver.findElements(By.cssSelector("div.board-members div.profile__name"));
        List<WebElement> profileDescription = driver.findElements(By.cssSelector("div.board-members div.profile__body"));

        for (int i=0; i<profileNames.size(); i++){
            nameDescriptionMap.put(profileNames.get(i).getText(),profileDescription.get(i).getText());
        }
        return nameDescriptionMap;
    }

    /**
     * This method takes linkedhashmap as parameter and creates CSV file from it. This csv file is stored
     * under project directory with export.csv name
     * @param map
     */
    public void exportDataToCsv(LinkedHashMap<String, String> map){
        final String CSV_FILE = "./export.csv";
        BufferedWriter writer;
        CSVPrinter csvPrinter;
        try {
            writer = Files.newBufferedWriter(Paths.get(CSV_FILE));
            csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("Name", "Description"));
            for (Map.Entry<String, String> entry : map.entrySet()) {
                csvPrinter.printRecord(Arrays.asList(entry.getKey(), entry.getValue()));
            }
            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
