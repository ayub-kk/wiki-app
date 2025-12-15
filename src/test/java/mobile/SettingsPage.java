package mobile;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class SettingsPage extends BasePage {

    @AndroidFindBy(xpath = "//*[@text='Settings']")
    private WebElement settingsTitle;

    @AndroidFindBy(xpath = "//*[@text='Language']")
    private WebElement languageOption;

    @AndroidFindBy(xpath = "//*[@text='App theme']")
    private WebElement themeOption;

    @AndroidFindBy(id = "org.wikipedia:id/preference_primary_text")
    private WebElement currentLanguage;

    public void openLanguageSettings() {
        languageOption.click();
    }

    public void openThemeSettings() {
        themeOption.click();
    }

    public String getCurrentLanguage() {
        return currentLanguage.getText();
    }

    public boolean isSettingsPageDisplayed() {
        return settingsTitle.isDisplayed();
    }
}