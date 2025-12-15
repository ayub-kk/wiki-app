package mobile;

import com.example.utils.ConfigReader;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public abstract class BasePage {
    protected static AppiumDriver driver;
    protected static WebDriverWait wait;

    public static void initializeDriver() throws MalformedURLException {
        if (driver == null) {
            URL appiumServerUrl = new URL(ConfigReader.getProperty("mobile.appium.url"));

            io.appium.java_client.android.options.UiAutomator2Options options = new io.appium.java_client.android.options.UiAutomator2Options()
                    .setPlatformName(ConfigReader.getProperty("mobile.platform.name"))
                    .setPlatformVersion(ConfigReader.getProperty("mobile.platform.version"))
                    .setDeviceName(ConfigReader.getProperty("mobile.device.name"))
                    .setAppPackage(ConfigReader.getProperty("mobile.app.package"))
                    .setAppActivity(ConfigReader.getProperty("mobile.app.activity"))
                    .setAutomationName(ConfigReader.getProperty("mobile.automation.name"))
                    .setNoReset(true)
                    .setFullReset(false);

            driver = new AndroidDriver(appiumServerUrl, options);
            wait = new WebDriverWait(driver,
                    Duration.ofSeconds(Integer.parseInt(ConfigReader.getProperty("mobile.timeout"))));
        }
    }

    public BasePage() {
        PageFactory.initElements(driver, this);
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            wait = null;
        }
    }
}