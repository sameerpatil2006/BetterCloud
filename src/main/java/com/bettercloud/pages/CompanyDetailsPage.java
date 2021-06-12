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

public class CompanyDetailsPage extends TestBaseSetup {

    @FindBy(xpath = "//*[contains(text(),'About BetterCloud')]")
    WebElement aboutHeaderTag;

    @FindBy(css = "div.company-marquee>div.content>div.header")
    WebElement aboutHeader;


    @FindBy(css = "div.horizontal-tabs div.tab-list__inner>div:nth-of-type(2)")
    WebElement board;

    @FindBy(css = "div.our-awards__inner")
    WebElement ourAwards;

    // Initializing the Page Objects:
    public CompanyDetailsPage() {
        PageFactory.initElements(driver, this);
    }

    public String getHeaderText(){
        CommonMethods.waitUntilVisible(explicit_wait_time,aboutHeader);
        return aboutHeader.getText();
    }

    public void goToBoard(){
        CommonMethods.waitUntilElementClickable(explicit_wait_time, board);
        board.click();
    }

    public String getHeadingTag(){
        CommonMethods.waitUntilVisible(explicit_wait_time,aboutHeaderTag);
        return aboutHeaderTag.getTagName();
    }

    public LinkedHashMap<String, String> getBoardMembersNames() {
        LinkedHashMap<String, String> nameDescriptionMap = new LinkedHashMap<>();
        CommonMethods.scrollToElement(ourAwards);
        CommonMethods.waitUntilElementClickable("div.board-members>div:nth-of-type(6)",20);
        List<WebElement> profileNames = driver.findElements(By.cssSelector("div.board-members div.profile__name"));
        List<WebElement> profileDescription = driver.findElements(By.cssSelector("div.board-members div.profile__body"));

        for (int i=0; i<profileNames.size(); i++){
            nameDescriptionMap.put(profileNames.get(i).getText(),profileDescription.get(i).getText());
        }
        return nameDescriptionMap;
    }

    public void exportDataToCsv(LinkedHashMap<String, String> map){
        final String CSV_FILE = "./export.csv";
        BufferedWriter writer = null;
        CSVPrinter csvPrinter = null;
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
