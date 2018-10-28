package com.Selenium.pages;

import com.Selenium.reporting.MyLogger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public abstract class AuthorizedPage extends AbstractPage {

    @FindBy(xpath = "//div[@aria-label=\"Информация об аккаунте\"]//div[@dir=\"ltr\"]")
    private WebElement authorizedEmailAddress;

    @FindBy(xpath = "//a[@aria-label=\"Приложения Google\"]")
    protected WebElement appsGoogleLink;

    @FindBy(xpath = "//div[contains(text(),\"Написать\")]")
    private WebElement writeMessageButton;

    @FindBy(xpath = "//div[@aria-label=\"Новое сообщение\"]")
    private WebElement newMessageLabel;

    @FindBy(xpath = "//textarea[@name=\"to\"]")
    private WebElement targetField;

    @FindBy(xpath = "//input[@name=\"subjectbox\"]")
    private WebElement subjectField;

    @FindBy(xpath = "//div[@aria-label=\"Тело письма\"]")
    private WebElement messageBodyField;

    @FindBy(xpath = "//div[contains(@data-tooltip,\"Отправить\")]")
    private WebElement sendButton;

    @FindBy(xpath = "//div[@aria-live=\"assertive\"]//span[contains(text(),\"Письмо отправлено.\")]")
    private WebElement messageStatus;

    @FindBy(xpath = "//a[contains(text(),\"Выйти\")]")
    private WebElement logoutButton;

    @FindBy(xpath = "//a[contains(@aria-label,\"Аккаунт Google\")]")
    private WebElement accLogo;

    public void openPage() {
    }

    public void logOut(){
        accLogo.click();
        waitForElementVisible(logoutButton);
        logoutButton.click();
        MyLogger.info("Log out success");
    }

    public AuthorizedPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getEmailAddress(){
        MyLogger.info("Authorized user's email is "+ authorizedEmailAddress.getAttribute("innerHTML").split(" ")[0] + ".");
        return authorizedEmailAddress.getAttribute("innerHTML").split(" ")[0];
    }

    public void openWriteMessageWindow(){
        writeMessageButton.click();
        waitForElementVisible(newMessageLabel);
    }
    public void writeMessage(String target, String subject, String body){
        openWriteMessageWindow();
        MyLogger.info("Opened window for writing email.");
        targetField.sendKeys(target);
        MyLogger.info("Target filed was filled.");
        subjectField.sendKeys(subject);
        MyLogger.info("Subject filed was filled.");
        messageBodyField.sendKeys(body);
        MyLogger.info("Body filed was filled.");
        sendButton.click();
        waitForElementVisible(messageStatus);
        MyLogger.info("Message sent.");

    }
}
