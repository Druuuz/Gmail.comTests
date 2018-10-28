package com.Selenium.pages;

import com.Selenium.reporting.MyLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class InboxPage extends AuthorizedPage {

    private final String checkBoxLocator = "//div[@role=\"checkbox\"]";
    private final String selfSendedTargetName = "я";

    @FindBy(xpath = "//div[@role=\"tabpanel\"]//tr")
    private List<WebElement> listOfMessages;

    @FindBy(xpath = "//div[@title=\"Удалить\"]/div")
    private WebElement deleteButton;

    @FindBy(xpath = "//div[@aria-live=\"assertive\"]//span[contains(text(),\"Цепочка помещена в корзину\")]")
    private WebElement messageDeletedStatus;

    @FindBy(xpath = "//div[@data-tooltip=\"Удалить\"]/..")
    private WebElement toolBar;

    private final String BASEURL = "https://mail.google.com/mail/u/0/#inbox";

    public void openPage() {
        driver.navigate().to(BASEURL);
    }

    public void deleteSelfSentMessage(String subject, String body){
        deleteMessage(selfSendedTargetName, subject, body);
    }

    public void deleteMessage(String target, String subject, String body){
        String messageText;
        for (WebElement message: listOfMessages){
            messageText = message.getText();
            if (messageText.contains(subject) && messageText.contains(target) && messageText.contains(body))  {
                message.findElement(By.xpath(checkBoxLocator)).click();
                waitForElementClickable(deleteButton);
                deleteButton.click();
                waitForElementStaleness(message);
                MyLogger.info("Message deleted successfully.");
                break;
            }
        }
    }

    public boolean isSelfSentMessageExistsInInboxList(String subject, String body){
        return isMessageExistsInInboxList(selfSendedTargetName, subject, body);
    }

    public boolean isMessageExistsInInboxList(String target, String subject, String body){
        String messageText;
        for (WebElement message: listOfMessages){
            messageText = message.getText();
            if (messageText.contains(subject) && messageText.contains(target) && messageText.contains(body))  {
                MyLogger.info("Message exists in Inbox list.");
                return true;
            }
        }
        MyLogger.info("Message does not exist in Inbox list.");
        return false;
    }

    public InboxPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }
}
