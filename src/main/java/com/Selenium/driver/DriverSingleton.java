package com.Selenium.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

    public class DriverSingleton {

        private static WebDriver driver;

        private static final String WEBDRIVER_CHROMEDRIVER = "webdriver.chrome.driver";
        private static final String CHROMEDRIVER_CHROMEDRIVER_EXE_PATH = ".\\geckodriver\\chromedriver.exe";

        private DriverSingleton(){};


        public static WebDriver getDriver(){
            if (null == driver){
                System.setProperty(WEBDRIVER_CHROMEDRIVER, CHROMEDRIVER_CHROMEDRIVER_EXE_PATH);
                driver = new ChromeDriver();
                driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
                driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
                driver.manage().window().maximize();
            }

            return driver;
        }

        public static void closeDriver(){
            driver.quit();
            driver = null;
        }
    }


