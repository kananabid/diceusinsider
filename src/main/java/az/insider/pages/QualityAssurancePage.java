package az.insider.pages;

import az.insider.logs.LoggerFile;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.List;

public class QualityAssurancePage extends ExceptionHandler { //To reuse exceptions handling methods

    private final WebDriver driver;
    private final WebDriverWait wait;

    public QualityAssurancePage(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    //Objects on the Quality Assurance Page

    @FindBy (xpath = "//a[@href='https://useinsider.com/careers/open-positions/?department=qualityassurance']")
    private WebElement seeAllQaJobsBtn;

    @FindBy (id = "select2-filter-by-location-container")
    private WebElement filterLocation;

    @FindBy(xpath = ("//*[contains(text(),'Istanbul, Turkey')]"))
    private WebElement filterIstanbulTurkey;

    @FindBy (id = "select2-filter-by-department-container")
    private WebElement filterDepartment;

    @FindBy(xpath = ("//*[contains(text(),'Quality Assurance')]"))
    private WebElement filterQualityAssurance;

    @FindBy(linkText = ("View Role"))
    private WebElement viewRoleBtn;

    @FindBy(xpath = ("//*[contains(text(),'APPLY FOR THIS JOB')]"))
    private WebElement applyBtn;

    //Methods of the Quality Assurance Page

    public void clickOnAllQaJobsBtn(){
        wait.until(ExpectedConditions.visibilityOf(seeAllQaJobsBtn));
        clickOnElement(wait, seeAllQaJobsBtn);
    }
    public void clickOnFilterLocation(){
        wait.until(ExpectedConditions.visibilityOf(filterLocation));
        clickOnElement(wait, filterLocation);
    }
    public void clickOnFilterIstanbul(){
        wait.until(ExpectedConditions.visibilityOf(filterIstanbulTurkey));
        clickOnElement(wait, filterIstanbulTurkey);
    }
    public void clickOnFilterDepartment(){
        wait.until(ExpectedConditions.visibilityOf(filterDepartment));
        clickOnElement(wait, filterDepartment);
    }
    public boolean checkFilterQAIsPresent() {
        wait.until(ExpectedConditions.elementToBeClickable(filterDepartment));
        clickOnElement(wait, filterDepartment);
        return filterDepartment.isDisplayed();
    }
    public void checkJobsDetails() {
        List<WebElement> jobElements = driver.findElements(By.id("career-position-list"));

        boolean allJobsMeetCriteria = true;

        for (WebElement jobElement : jobElements) {
            scrollToElement(driver, jobElement);
            String position = jobElement.findElement(By.xpath(".//p")).getText();
            String department = jobElement.findElement(By.xpath("//span[contains(text(), 'Quality Assurance')]")).getText();
            String location = jobElement.findElement(By.xpath("//div[contains(text(), 'Istanbul, Turkey')]")).getText();

            // Check if the job details meet the criteria
            if (!(position.contains("Quality Assurance") &&
                    department.contains("Quality Assurance") &&
                    location.contains("Istanbul, Turkey"))) {
                LoggerFile.error("Job does not meet criteria - Position: " + position + ", Department: " + department + ", Location: " + location);
                allJobsMeetCriteria = false;
            }
        }

        if (allJobsMeetCriteria) {
            LoggerFile.info("All jobs meet the criteria.");
        } else {
            LoggerFile.error("Not all jobs meet the criteria. Check logs for details.");
        }
    }

    public void clickOnViewRoleBtn(){
        WebElement elementToHover = driver.findElement(By.cssSelector(".position-list-item-wrapper"));
        Actions actions = new Actions(driver);
        actions.moveToElement(elementToHover).build().perform();
        wait.until(ExpectedConditions.elementToBeClickable(viewRoleBtn));
        clickOnElement(wait, viewRoleBtn);
    }

    public void checkIfThePageIsRedirected() {
        String parentWindow = driver.getWindowHandle();
        String expectedURL = "https://jobs.lever.co/useinsider/78ddbec0-16bf-4eab-b5a6-04facb993ddc";
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(parentWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        wait.until(ExpectedConditions.urlToBe(expectedURL));

        String currentURL = driver.getCurrentUrl();
        if (currentURL.equals(expectedURL)) {
            System.out.println("The expected URL is opened: " + currentURL);
        } else {
            System.out.println("The expected URL is not opened. Current URL: " + currentURL);
        }
        driver.close();
        driver.switchTo().window(parentWindow);
    }
}
