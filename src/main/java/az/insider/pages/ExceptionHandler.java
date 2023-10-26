package az.insider.pages;

import az.insider.logs.LoggerFile;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;

public class ExceptionHandler {

    public void clickOnElement(WebDriverWait webDriverWait, WebElement webElement) {
        //Attempts to find element
        for (int i = 0; i < 1; i++) {
            try {
                webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));
                webElement.click();
                break;
            } catch (StaleElementReferenceException | TimeoutException | NoSuchElementException exception) {
                LoggerFile.warn("Another try because of: " + webElement + "\n" + Arrays.toString(exception.getStackTrace()));
            }
        }
        if (LoggerFile.isWarnEnabled()) {
            LoggerFile.warn("Failed to locate element: " + webElement);
        }
    }

    public static void scrollToElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
