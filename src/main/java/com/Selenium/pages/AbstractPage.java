package com.Selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    private final int TIMEOUT = 10;

    public abstract void openPage();

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, TIMEOUT);
    }
     public void refreshPage(){
        driver.navigate().refresh();
     }

     public void waitForElementVisible(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
     }

     public void waitForElementClickable(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
     }

     public void waitForElementInvisible(WebElement element){
        wait.until(ExpectedConditions.invisibilityOf(element));
     }

     public void waitForElementStaleness(WebElement element){
        wait.until(ExpectedConditions.stalenessOf(element));
     }

     public void waitForUrlNotContains(String subURL){
         wait.until(ExpectedConditions.not(ExpectedConditions.urlContains(subURL)));
     }
}
