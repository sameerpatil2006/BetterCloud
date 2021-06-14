# BetterCloud Assignment

Selenium Page Object Model using TestNG Framework

Setup instructions for my project are as follows: 
1.  Git Setup:
    1.  To clone the repo from terminal :
    2.  Open terminal.
    3.  Go to the local folder that you want to create the project in.
    4.  Run the command : git clone https://github.com/sameerpatil2006/BetterCloud.git
2.  Intellij Setup:
    1.  Open Intellij and Click File-> New -> From Existing Sources.
    2.  Select “BetterCloud” repo you created in step 1.4.
    3.  Select gradle from the list
3.  Running Test Using Test Class:
    1.  The test (AboutBetterCloudTest) is located under src/test/java/com/bettercloud/companyDetails. Please expand the package and run the test.
    2.  Right click on the AddSheetTest and Run As TestNg test should run the test.
4. Running Test Using Suite File through IDE and terminal:
    1.  Test suite file is located under src/test/java/com/bettercloud/BetterCloudTestSuite.xml. I have added extent report listener to the xml file which will generate Extent Report after execution. The report will be stored under BetterCloud/test-output/BetterCloud.html, open report using any browser.
    2. Right click on BetterCloudTestSuite.xml and select run. It will run both the testclasses and generate Extent report after execution. 
    3. To run in terminal open project directory and type command 'gradle clean build test' this will execute BetterCloudTestSuite.xml and generate extent report.

My approach to this Project:
 
1.  src/main/java/com/bettercloud:
    1.  base: This contains “TestBaseSetup” class that all page objects inherit to. This base class reads the config file, initializes webdriver and sets global time limit before the tests start. 
    2.  common: This class provides common methods which are common to all tests and used through out project
    3.  pages: This contains the page object of Home page and CompanyDetails Page. The page object holds the identifiers on the page and methods to do the operations. It also has methods for navigation and validations.
    4.  properties: This has configuration property file configs.properties which has company url and browser name
    5.  Utility: This package contains classes for reports and testlisteners which are used to generate extent report and take screenshots
2.  Project/drivers:
    1. Chrome driver and Gecko driver are stored here.
3.  src/test/java/com/bettercloud/companyDetails:
    1.  AboutBetterCloudTest: This test class has 2 testmethods extractMemberData and failTestForReportDemo. extractMemberData asserts title about bettercloud and extracts board members from about page and stores them in csv file, failTestForReportDemo is dummy test which fails on purpose for demoing report generation and screenshot capturing.
    2.  HomeTest : This is basic testclass to verify bettercloud logo is visible.
4.  Project/test-output:
    1. BetterCloud.html : This report is generated after execution of test cases. This report is generated with the help of third party tool Extent Report. To open the report use any browser. 
5.  Project/screenshots: This directory stores all screenshots taken during execution with timestamp in its name.
6.  Project/export.csv: This csv is genrated from extractMemberData test method and it contains member name and description

CI/CD uisng Jenkins :

1.  Prerequisite :
    1.  Running instance of Jenkins Server
    2.  Jenkins node should be able to run docker command 
2.  Setup:
    1.  Create new jenkins pipeline job and configure it as per screenshots below (also added screenshots in project under BetterCloud/screenshot/jenkins)
3.  Working:
    1.  This jenkins job checks for new commits every hour and runs if there any changes.
    2.  This jenkins job can also be triggered manually.
4.  Approach :
    1.  Created one docker file from Selenium-Standalone-latest image, that file has configurations needed to run our tests.
    2.  Also, created jenkins file, on running jenkins job this file creates image from docker file and starts new container of that image and runs gradle command to start test
    3.  After test execution is complete test report link is added to jenkins. 
    
Extent Reports from Test run :

![Alt text](https://github.com/sameerpatil2006/BetterCloud/blob/master/ExtentReport_images/Screenshot%20from%202021-06-13%2013-18-53.png?raw=true "Test Run Overview")

![Alt text](https://github.com/sameerpatil2006/BetterCloud/blob/master/ExtentReport_images/Screenshot%20from%202021-06-13%2013-18-03.png?raw=true "All Tests")

![Alt text](https://github.com/sameerpatil2006/BetterCloud/blob/master/ExtentReport_images/Screenshot%20from%202021-06-13%2013-14-21.png?raw=true "Failed Test Log")
