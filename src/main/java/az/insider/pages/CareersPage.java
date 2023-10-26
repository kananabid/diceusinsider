package az.insider.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.List;

public class CareersPage extends ExceptionHandler { //To reuse exceptions handling methods

    private final WebDriver driver;
    private final WebDriverWait wait;

    public CareersPage(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    //Objects on the Careers Page

    @FindBy(className = "career-load-more")
    private WebElement teamsOnCareerPage;

    @FindBy(className = "glide__track")
    private WebElement locationsOnCareersPage;

    @FindBy(className = "elementor-widget-container")
    private WebElement lifeAtInsiderOnCareersPage;

    @FindBy(xpath = "//div[contains(@class, 'thumbnail-wrapper')]")
    private List<WebElement> booksList;

    //Methods of the Careers Page

    public boolean checkTeamsOnCareerPage(){
        scrollToElement(driver, teamsOnCareerPage);
        wait.until(ExpectedConditions.visibilityOf(teamsOnCareerPage));
        return teamsOnCareerPage.isDisplayed();
    }
    public boolean checkLocationsOnCareersPage(){
        scrollToElement(driver, locationsOnCareersPage);
        wait.until(ExpectedConditions.visibilityOf(locationsOnCareersPage));
        return locationsOnCareersPage.isDisplayed();
    }
    public boolean checkLifeAtInsiderOnCareersPage(){
        scrollToElement(driver, lifeAtInsiderOnCareersPage);
        wait.until(ExpectedConditions.visibilityOf(lifeAtInsiderOnCareersPage));
        return lifeAtInsiderOnCareersPage.isDisplayed();
    }
}
