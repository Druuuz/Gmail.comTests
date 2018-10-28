package com.Selenium.pages;

import com.Selenium.reporting.MyLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends AbstractPage {
    @FindBy(xpath = "//div[contains(text(),\"Телефон или адрес эл. почты\")]/../input")
    private WebElement usernameField;

    @FindBy(xpath = "//div[@id=\"identifierNext\"]")
    private WebElement usernameNextButton;

    @FindBy(xpath = "//div[@id=\"passwordNext\"]")
    private WebElement passwordNextButton;

    @FindBy(xpath = "//input[@type=\"password\"]")
    private WebElement passwordField;

    private final String BASEURL = "https://accounts.google.com/signin";
    public void openPage() {
        driver.navigate().to(BASEURL);
    }

    public LoginPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private void fillUsername(String username){
        usernameField.sendKeys(username);
        usernameNextButton.click();
    }

    private void fillPassword(String password){
        waitForElementVisible(passwordField);
        passwordField.sendKeys(password);
        passwordNextButton.click();
    }

    public void logIn(String username, String password){
        fillUsername(username);
        MyLogger.info("Field username filled.");
        fillPassword(password);
        MyLogger.info("Field password filled");
        waitForUrlNotContains("signin");
        MyLogger.info("Log in success.");

    }
}
