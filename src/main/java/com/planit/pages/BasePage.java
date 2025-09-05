package com.planit.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));
    }

    protected WebElement waitClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected WebElement waitVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void clickLink(String linkText) {
        waitClickable(By.linkText(linkText)).click();
    }

    public void click(By locator) {
        waitClickable(locator).click();
    }

    public void type(By locator, String value) {
        WebElement el = waitVisible(locator);
        el.clear();
        el.sendKeys(value);
    }

    public String textOf(By locator) {
        return waitVisible(locator).getText().trim();
    }

    public boolean isPresent(By locator) {
        try {
            waitVisible(locator);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
