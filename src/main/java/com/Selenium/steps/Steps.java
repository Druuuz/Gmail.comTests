package com.Selenium.steps;

import com.Selenium.bo.Message;
import com.Selenium.bo.User;
import com.Selenium.driver.DriverSingleton;
import com.Selenium.pages.InboxPage;
import com.Selenium.pages.LoginPage;
import org.openqa.selenium.WebDriver;

public class Steps {

    private WebDriver driver;
    private LoginPage loginPage;
    private InboxPage inboxPage;

    public void initBrowser() {
        driver = DriverSingleton.getDriver();
    }

    public void initPages(){

        loginPage = new LoginPage(driver);
        inboxPage = new InboxPage(driver);
    }

    public void closeDriver() {
        DriverSingleton.closeDriver();
    }

    public void logIn(User user){
        loginPage.openPage();
        loginPage.logIn(user.getUsername(), user.getPassword());
    }

    public String getLoggedEmailAddress(){
        inboxPage.openPage();
        return inboxPage.getEmailAddress();
    }

    public void sendMessage(Message message){
        inboxPage.writeMessage(message.getTurget(), message.getSubject(), message.getBody());
    }

    public boolean isMessageSent(Message message){
        inboxPage.refreshPage();
        return inboxPage.isSelfSentMessageExistsInInboxList(message.getSubject(), message.getBody());
    }

    public boolean isMessageDeleted(Message message){
        return !inboxPage.isSelfSentMessageExistsInInboxList(message.getSubject(), message.getBody());
    }

    public void deleteMessage(Message message){
        inboxPage.deleteSelfSentMessage(message.getSubject(), message.getBody());
    }

    public void logOut(){
        inboxPage.logOut();
    }

}
