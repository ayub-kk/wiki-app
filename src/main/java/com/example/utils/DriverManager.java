package com.example.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DriverManager {
    private static WebDriver driver;
    private static WebDriverWait wait;

    public static WebDriver getWebDriver() {
        if (driver == null) {
            String browser = ConfigReader.getProperty("web.browser");

            switch (browser.toLowerCase()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "safari":
                    driver = new SafariDriver();
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(
                    Duration.ofSeconds(Integer.parseInt(ConfigReader.getProperty("web.timeout")))
            );
        }
        return driver;
    }

    public static WebDriverWait getWait() {
        if (wait == null) {
            wait = new WebDriverWait(getWebDriver(),
                    Duration.ofSeconds(Integer.parseInt(ConfigReader.getProperty("web.timeout"))));
        }
        return wait;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            wait = null;
        }
    }
}