package web.pages;

import com.example.utils.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage() {
        this.driver = DriverManager.getWebDriver();
        this.wait = DriverManager.getWait();
        PageFactory.initElements(driver, this);
    }
}