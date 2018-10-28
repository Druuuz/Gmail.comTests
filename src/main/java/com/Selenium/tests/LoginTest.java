package com.Selenium.tests;

import com.Selenium.bo.Message;
import com.Selenium.bo.User;
import com.Selenium.steps.Steps;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest {

    private Steps steps;
    private final String USERNAME = "olesov198@gmail.com";
    private final String PASSWORD = "nissan@reno";
    private final String TARGET = "olesov198@gmail.com";
    private final String SUBJECT = "test subject";
    private final String BODY = "Some text...";
    private final User user = new User(USERNAME, PASSWORD);
    private final Message message = new Message(TARGET, SUBJECT, BODY);


    @BeforeClass(description = "Init browser")
    public void setUp()
    {
        steps = new Steps();
        steps.initBrowser();
        steps.initPages();
    }

    @Test(description = "Login using username and password")
    public void login()
    {
        steps.logIn(user);
        Assert.assertEquals(steps.getLoggedEmailAddress(),USERNAME);

    }

    @Test(description = "Sending email", dependsOnMethods = "login")
    public void sendMessage(){
        steps.sendMessage(message);
        Assert.assertTrue(steps.isMessageSent(message));
    }

    @Test(description = "Deleting email", dependsOnMethods = "sendMessage")
    public void deleteMessage(){
        steps.deleteMessage(message);
        Assert.assertTrue(steps.isMessageDeleted(message));
    }


    @AfterClass(description = "Stop Browser")
    public void stopBrowser()
    {
        steps.logOut();
        steps.closeDriver();
    }
}
