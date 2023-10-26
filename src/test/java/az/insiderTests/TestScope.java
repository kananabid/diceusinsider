package az.insiderTests;

import az.insider.drivers.DriverFactoryChrome;
import az.insider.logs.LoggerFile;
import az.insider.pages.QualityAssurancePage;
import az.insider.pages.HomePage;
import az.insider.pages.CareersPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

@Epic("InsiderTest Task")
@Feature("See and filter all QA roles")
public class TestScope {


    @BeforeMethod
    public void setupChromeDriver(){
        DriverFactoryChrome.initializeDriver();
    }

    @Test
    public void viewAllQaRoles() throws InterruptedException {

        LoggerFile.info("Initializing things");
        WebDriver driver = DriverFactoryChrome.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        HomePage homePage = new HomePage(driver, wait);
        CareersPage careersPage = new CareersPage(driver, wait);
        QualityAssurancePage qualityAssurancePage = new QualityAssurancePage(driver, wait);

        LoggerFile.info("Navigate to https://useinsider.com and execute test. " +
                "Any additional information on execution will be added on methods implementation level");
        driver.get("https://useinsider.com");
        driver.manage().window().maximize();
        //Confirm page title
        System.out.println("Actual Page Title: " + driver.getTitle());
        Assert.assertTrue(driver.getTitle().contains("#1 Leader in Individualized, Cross-Channel CX — Insider"));
        //Accept cookies
        homePage.acceptCookies();
        //Click to Company tab
        homePage.clickOnCompanyTab();
        //Click to Careers tab
        homePage.clickOnCareersTab();
        //Confirm that Careers page is visible
        System.out.println("Actual Page Title: " + driver.getTitle());
        Assert.assertTrue(driver.getTitle().contains("Insider Careers"));
        //Confirm that Teams block is visible
        Assert.assertTrue(careersPage.checkTeamsOnCareerPage());
        TimeUnit.SECONDS.sleep(2);
        //Confirm that Locations block is visible
        Assert.assertTrue(careersPage.checkLocationsOnCareersPage());
        TimeUnit.SECONDS.sleep(2);
        //Confirm that Life at Insider block is visible
        Assert.assertTrue(careersPage.checkLifeAtInsiderOnCareersPage());
        LoggerFile.info("Navigate to to https://useinsider.com/careers/quality-assurance");
        driver.get("https://useinsider.com/careers/quality-assurance/");
        //Click on All QA jobs button
        qualityAssurancePage.clickOnAllQaJobsBtn();
        //Confirm page title
        System.out.println("Actual Page Title: " + driver.getTitle());
        Assert.assertTrue(driver.getTitle().contains("Insider Open Positions | Insider"));
        //Click on Filter Location
        TimeUnit.SECONDS.sleep(2);
        qualityAssurancePage.clickOnFilterLocation();
        //Click to Filter Istanbul
        qualityAssurancePage.clickOnFilterIstanbul();
        //Click to Filter Department
        qualityAssurancePage.clickOnFilterDepartment();
        TimeUnit.SECONDS.sleep(2);
        //Check if Quality Assurance filter is present
        qualityAssurancePage.checkFilterQAIsPresent();
        //Check for jobs details: Position" “Quality Assurance”, Department: “Quality Assurance”, Location: “Istanbul, Turkey”
        qualityAssurancePage.checkJobsDetails();
        //CLick on View Role button
        qualityAssurancePage.clickOnViewRoleBtn();
        //Confirm Lever Application Form is opened
        qualityAssurancePage.checkIfThePageIsRedirected();
        LoggerFile.info("Test is finished. You can find results in test-output folder");
    }

    @AfterMethod
    public void quitDriver(){
        WebDriver driver = DriverFactoryChrome.getDriver();
        driver.quit();
        LoggerFile.info("Closing driver");
    }
}
