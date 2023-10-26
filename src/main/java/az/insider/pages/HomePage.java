package az.insider.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends ExceptionHandler { //To reuse exceptions handling methods

    private final WebDriver driver;
    private final WebDriverWait wait;

    public HomePage (WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    //Objects on the Home Page

    @FindBy (xpath = "(//*[@id='navbarDropdownMenuLink'])[5]")
    private WebElement companyTab;

    @FindBy (linkText = "Careers")
    private WebElement careersTab;


    //Methods of the Home Page

    public void acceptCookies() {
        try {
            WebElement acceptCookiesButton = driver.findElement(By.id("wt-cli-accept-all-btn"));
            acceptCookiesButton.click();
        } catch (Exception e) {
            System.out.println("Cookie acceptance button not found or encountered an error: " + e.getMessage());
        }
    }


    public void clickOnCompanyTab(){
        clickOnElement(wait, companyTab);
    }
    public void clickOnCareersTab(){
        clickOnElement(wait,careersTab);
    }
}
